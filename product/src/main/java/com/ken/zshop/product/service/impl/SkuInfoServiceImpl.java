package com.ken.zshop.product.service.impl;

import com.ken.zshop.product.entity.SkuImagesEntity;
import com.ken.zshop.product.entity.SpuInfoDescEntity;
import com.ken.zshop.product.service.*;
import com.ken.zshop.product.vo.SkuItemSaleAttrVo;
import com.ken.zshop.product.vo.SkuItemVo;
import com.ken.zshop.product.vo.SpuAttrGroupVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ThreadPoolExecutor;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ken.zshop.common.utils.PageUtils;
import com.ken.zshop.common.utils.Query;

import com.ken.zshop.product.dao.SkuInfoDao;
import com.ken.zshop.product.entity.SkuInfoEntity;


@Service("skuInfoService")
public class SkuInfoServiceImpl extends ServiceImpl<SkuInfoDao, SkuInfoEntity> implements SkuInfoService {
    @Autowired
    SkuImagesService skuImagesService;

    @Autowired
    SkuSaleAttrValueService skuSaleAttrValueService;

    @Autowired
    SpuInfoDescService spuInfoDescService;

    @Autowired
    private AttrGroupService attrGroupService;

    @Autowired
    private ThreadPoolExecutor executor;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<SkuInfoEntity> page = this.page(
                new Query<SkuInfoEntity>().getPage(params),
                new QueryWrapper<SkuInfoEntity>()
        );

        return new PageUtils(page);
    }


    @Override
    public void saveSkuInfo(SkuInfoEntity skuInfoEntity) {
        this.baseMapper.insert(skuInfoEntity);
    }

    @Override
    public SkuItemVo skuItemSync(Long skuId) {


        // 新建一个包装类对象
        SkuItemVo itemVo = new SkuItemVo();

        /*1、sku基本信息
        2、sku图片信息（多个图片）
        3、spu的销售属性
        4、spu的描述信息
        5、sku分组规格参数属性值*/

        // 开启异步编排实现，提升服务性能
        // 1、根据skuId 查询 sku基本信息
        SkuInfoEntity skuInfoEntity = this.getById(skuId);
        itemVo.setInfo(skuInfoEntity);


        // 2、根据skuId查询sku图片信息（多个图片），skuId是外键
        List<SkuImagesEntity> imageList = skuImagesService.list(new QueryWrapper<SkuImagesEntity>().eq("sku_id", skuId));
        itemVo.setImages(imageList);


        //3、根据spuID获取spu的销售属性
        // 获取sku与之对应的spuId
        Long spuId = skuInfoEntity.getSpuId();
        List<SkuItemSaleAttrVo> saleAttrVos = skuSaleAttrValueService.getSaleAttrs(spuId);
        if(saleAttrVos.size()>0 && saleAttrVos!=null){

            itemVo.setAttrSales(saleAttrVos);
        }


        //4、根据spuId查询spu的描述信息
        // 获取sku与之对应的spuId
        SpuInfoDescEntity spuInfoDescEntity = spuInfoDescService.getOne(new QueryWrapper<SpuInfoDescEntity>()
                .eq("spu_id",spuId));
        if(spuInfoDescEntity!=null){

            itemVo.setDesc(spuInfoDescEntity);
        }


        //5、根据spuID,categoryId 查询 sku分组规格参数属性值
        // 获取分类id
        Long categoryId = skuInfoEntity.getCategoryId();
        List<SpuAttrGroupVo> attrGroupVos = attrGroupService.getGroupAttr(spuId,categoryId);
        if(attrGroupVos.size()>0){

            itemVo.setAttrGroups(attrGroupVos);
        }

        //6、根据SkuID查询商品秒杀详情
        //查询sku的秒杀详情
      /*  R seckSkuInfo = seckillFeignService.getSeckillSkuRedisTo(skuId);
        if(seckSkuInfo.getCode()==0){
            SeckillSkuVo seckillSkuVo = seckSkuInfo.getData("data",new TypeReference<SeckillSkuVo>(){});
            if(seckillSkuVo!=null){
                long currentTime = System.currentTimeMillis();
                if(seckillSkuVo.getEndTime()>currentTime){
                    itemVo.setSeckillSkuVo(seckillSkuVo);
                }
            }
        }*/

        return itemVo;
    }


    @Override
    public SkuItemVo skuItem(Long skuId) {

        // 新建一个包装类对象
        SkuItemVo itemVo = new SkuItemVo();

        /*1、sku基本信息
        2、sku图片信息（多个图片）
        3、spu的销售属性
        4、spu的描述信息
        5、sku分组规格参数属性值*/

        // 开启异步编排实现，提升服务性能
        // 1、根据skuId 查询 sku基本信息
        CompletableFuture<SkuInfoEntity> infoFuture = CompletableFuture.supplyAsync(() -> {
            SkuInfoEntity skuInfoEntity = this.getById(skuId);
            itemVo.setInfo(skuInfoEntity);
            return skuInfoEntity;
        }, executor);


        // 2、根据skuId查询sku图片信息（多个图片），skuId是外键
        CompletableFuture<Void> imagesFuture = CompletableFuture.runAsync(() -> {
            List<SkuImagesEntity> imageList = skuImagesService.list(new QueryWrapper<SkuImagesEntity>().eq("sku_id", skuId));
            itemVo.setImages(imageList);
        }, executor);


        //3、根据spuID获取spu的销售属性

        CompletableFuture<Void> salesFuture = infoFuture.thenAcceptAsync((res) -> {
            // 获取sku与之对应的spuId
            Long spuId = res.getSpuId();
            List<SkuItemSaleAttrVo> saleAttrVos = skuSaleAttrValueService.getSaleAttrs(spuId);
            if(saleAttrVos.size()>0 && saleAttrVos!=null){

                itemVo.setAttrSales(saleAttrVos);
            }
        }, executor);


        //4、根据spuId查询spu的描述信息
        CompletableFuture<Void> descFuture = infoFuture.thenAcceptAsync((res) -> {
            // 获取sku与之对应的spuId
            Long spuId = res.getSpuId();
            SpuInfoDescEntity spuInfoDescEntity = spuInfoDescService.getOne(new QueryWrapper<SpuInfoDescEntity>()
                    .eq("spu_id",spuId));
            if(spuInfoDescEntity!=null){

                itemVo.setDesc(spuInfoDescEntity);
            }
        }, executor);

        //5、根据spuID,categoryId 查询 sku分组规格参数属性值


        CompletableFuture<Void> groupFuture = infoFuture.thenAcceptAsync((res) -> {
            // 获取sku与之对应的spuId
            Long spuId = res.getSpuId();

            // 获取分类id
            Long categoryId = res.getCategoryId();
            List<SpuAttrGroupVo> attrGroupVos = attrGroupService.getGroupAttr(spuId,categoryId);
            if(attrGroupVos.size()>0){

                itemVo.setAttrGroups(attrGroupVos);
            }

        }, executor);

        // 等待所有的任务完成后，才能返回结果
        try {
            CompletableFuture.allOf(infoFuture,imagesFuture,salesFuture,descFuture,groupFuture).get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return itemVo;
    }


}