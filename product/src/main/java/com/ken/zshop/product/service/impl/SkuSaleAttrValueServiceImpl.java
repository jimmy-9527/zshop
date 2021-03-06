package com.ken.zshop.product.service.impl;

import com.ken.zshop.product.vo.SkuItemSaleAttrVo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ken.zshop.common.utils.PageUtils;
import com.ken.zshop.common.utils.Query;

import com.ken.zshop.product.dao.SkuSaleAttrValueDao;
import com.ken.zshop.product.entity.SkuSaleAttrValueEntity;
import com.ken.zshop.product.service.SkuSaleAttrValueService;


@Service("skuSaleAttrValueService")
public class SkuSaleAttrValueServiceImpl extends ServiceImpl<SkuSaleAttrValueDao, SkuSaleAttrValueEntity> implements SkuSaleAttrValueService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<SkuSaleAttrValueEntity> page = this.page(
                new Query<SkuSaleAttrValueEntity>().getPage(params),
                new QueryWrapper<SkuSaleAttrValueEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public List<SkuItemSaleAttrVo> getSaleAttrs(Long spuId) {
        //List<SkuItemSaleAttrVo> saleAttrVos = skuSaleAttrValueService.getSaleAttrs(spuId);
        List<SkuItemSaleAttrVo> saleAttrVos = this.baseMapper.getSaleAttrs(spuId);

        return saleAttrVos;
    }

    @Override
    public List<String> getSaleAttrsString(Long skuId) {
        List<String> saleAttrsString = this.baseMapper.getSaleAttrsString(skuId);
        return saleAttrsString;
    }

}