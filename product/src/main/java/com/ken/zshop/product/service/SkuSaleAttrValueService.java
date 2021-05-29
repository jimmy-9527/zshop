package com.ken.zshop.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ken.zshop.common.utils.PageUtils;
import com.ken.zshop.product.entity.SkuSaleAttrValueEntity;
import com.ken.zshop.product.vo.SkuItemSaleAttrVo;

import java.util.List;
import java.util.Map;

/**
 * sku销售属性值
 *
 * @author Ken
 * @email jimmyw3950@gmail.com
 * @date 2021-05-15 16:20:49
 */
public interface SkuSaleAttrValueService extends IService<SkuSaleAttrValueEntity> {

    PageUtils queryPage(Map<String, Object> params);

    List<SkuItemSaleAttrVo> getSaleAttrs(Long spuId);
}

