package com.ken.zshop.product.dao;

import com.ken.zshop.product.entity.SkuSaleAttrValueEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ken.zshop.product.vo.SkuItemSaleAttrVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * sku销售属性值
 * 
 * @author Ken
 * @email jimmyw3950@gmail.com
 * @date 2021-05-15 16:20:49
 */
@Mapper
public interface SkuSaleAttrValueDao extends BaseMapper<SkuSaleAttrValueEntity> {
    public List<SkuItemSaleAttrVo> getSaleAttrs(Long spuId);
    public List<String> getSaleAttrsString(Long skuId);
}
