package com.ken.zshop.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ken.zshop.common.utils.PageUtils;
import com.ken.zshop.product.entity.CategoryEntity;

import java.util.List;
import java.util.Map;

/**
 * 商品类目
 *
 * @author Ken
 * @email jimmyw3950@gmail.com
 * @date 2021-05-13 21:24:29
 */
public interface CategoryService extends IService<CategoryEntity> {

    PageUtils queryPage(Map<String, Object> params);

    List<CategoryEntity> listTree();

    void removeNodesByIds(List<Integer> asList);
}

