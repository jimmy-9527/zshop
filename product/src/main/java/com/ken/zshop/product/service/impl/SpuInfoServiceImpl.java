package com.ken.zshop.product.service.impl;

import com.ken.zshop.product.entity.*;
import com.ken.zshop.product.service.*;
import com.ken.zshop.product.vo.*;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ken.zshop.common.utils.PageUtils;
import com.ken.zshop.common.utils.Query;

import com.ken.zshop.product.dao.SpuInfoDao;
import org.springframework.transaction.annotation.Transactional;


@Service("spuInfoService")
public class SpuInfoServiceImpl extends ServiceImpl<SpuInfoDao, SpuInfoEntity> implements SpuInfoService {

    @Autowired
    SpuInfoDescService spuInfoDescService;

    @Autowired
    SpuImagesService spuImagesService;

    @Autowired
    AttrService attrService;

    @Autowired
    ProductAttrValueService ProductAttrValueService;

    @Autowired
    SkuInfoService skuInfoService;

    @Autowired
    SkuImagesService skuImagesService;

    @Autowired
    SkuSaleAttrValueService skuSaleAttrValueService;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<SpuInfoEntity> page = this.page(
                new Query<SpuInfoEntity>().getPage(params),
                new QueryWrapper<SpuInfoEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public void saveBaseSpuInfo(SpuInfoEntity infoEntity) {
        this.baseMapper.insert(infoEntity);
    }

    //对7张数据库表进行存储
    @Transactional
    @Override
    public void saveSpuInfo(SpuSaveVo vo) {
        //1 保存spu基本信息表 tb_spu_info
        SpuInfoEntity infoEntity = new SpuInfoEntity();
        BeanUtils.copyProperties(vo,infoEntity);
        Date date = new Date();
        infoEntity.setCreateTime(date);
        infoEntity.setUpdateTime(date);
        this.saveBaseSpuInfo(infoEntity);

        //2 保存spu的描述信息 tb_spu_info_desc
        String descript = vo.getDecript();
        SpuInfoDescEntity descEntity = new SpuInfoDescEntity();
        descEntity.setSpuId(infoEntity.getId());
        descEntity.setDecript(descript);
        spuInfoDescService.saveSpuInfoDesc(descEntity);

        //3 保存spu的图片集 tb_spu_imags
        List<String> images = vo.getImages();
        spuImagesService.saveImages(infoEntity.getId(),images);

        //4 保存的spu的基本信息 tb_cubegoods_attr_value
        List<BaseAttrs> baseAttrs = vo.getBaseAttrs();
        List<ProductAttrValueEntity> collect = baseAttrs.stream().map(attr->{
            ProductAttrValueEntity entity = new ProductAttrValueEntity();
            entity.setAttrId(attr.getAttrId());
            AttrEntity attrEntity = attrService.getById(attr.getAttrId());
            entity.setAttrName(attrEntity.getName());
            entity.setAttrValue(attr.getAttrValues());
            entity.setQuickShow(attr.getShowDesc());
            entity.setSpuId(infoEntity.getId());
            return entity;
        }).collect(Collectors.toList());

        ProductAttrValueService.saveProductAttr(collect);

        //5. 保存当前spu对应的所有的sku信息
        List<Skus> skus = vo.getSkus();
        if(skus !=null && skus.size() >0 ){
            skus.forEach(item->{
                String defaultImg = "";
                for(Images image: item.getImages()){
                    if(image.getDefaultImg()==1){
                        defaultImg = image.getImgUrl();
                    }
                }
                SkuInfoEntity skuInfoEntity = new SkuInfoEntity();
                BeanUtils.copyProperties(item,skuInfoEntity);
                skuInfoEntity.setBrandId(infoEntity.getBrandId());
                skuInfoEntity.setCategoryId(infoEntity.getCategoryId());
                skuInfoEntity.setSaleCount(0L);
                skuInfoEntity.setSpuId(infoEntity.getId());
                skuInfoEntity.setSkuDefaultImg(defaultImg);

                //5.1 保存到sku的基本信息 tb_sku_info
                skuInfoService.saveSkuInfo(skuInfoEntity);
                //获取skuID
                Long skuId = skuInfoEntity.getId();

                if(item.getImages()!=null&&item.getImages().size()>0){
                    List<SkuImagesEntity> imagesEntities = item.getImages().stream().map(img->{
                        SkuImagesEntity skuImagesEntity = new SkuImagesEntity();
                        skuImagesEntity.setSkuId(skuId);
                        skuImagesEntity.setImgUrl(img.getImgUrl());
                        skuImagesEntity.setDefaultImg(img.getDefaultImg());
                        return skuImagesEntity;
                    }).filter(entity->{
                        return StringUtils.isEmpty(entity.getImgUrl());
                    }).collect(Collectors.toList());
                    //5.2 保存sku的图片集合 tb_sku_image
                    skuImagesService.saveBatch(imagesEntities);
                }

                List<Attr> attr = item.getAttr();
                if(attr!=null&&attr.size()>0){
                    List<SkuSaleAttrValueEntity> skuSaleAttrValueEntities = attr.stream().map(item2->{
                        SkuSaleAttrValueEntity attrValueEntity = new SkuSaleAttrValueEntity();
                        BeanUtils.copyProperties(item2,attrValueEntity);
                        attrValueEntity.setSkuId(skuId);
                        return attrValueEntity;
                    }).collect(Collectors.toList());

                    //5.3 保存sku的销售属性值 tb_sku_sale_attr_value
                    skuSaleAttrValueService.saveBatch(skuSaleAttrValueEntities);
                }
            });
        }
    }


}