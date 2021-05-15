package com.ken.zshop.product.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ken.zshop.common.utils.PageUtils;
import com.ken.zshop.product.entity.CategoryBrandEntity;

import java.util.List;
import java.util.Map;

/**
 * 分类品牌关系表
 *
 * @author Ken
 * @email jimmyw3950@gmail.com
 * @date 2021-05-15 09:26:12
 */
public interface CategoryBrandService extends IService<CategoryBrandEntity> {

    PageUtils queryPage(Map<String, Object> params);

    List<CategoryBrandEntity> getCategoryBrands(QueryWrapper<CategoryBrandEntity> brand_id);
}

