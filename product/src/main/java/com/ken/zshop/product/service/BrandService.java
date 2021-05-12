package com.ken.zshop.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ken.zshop.common.utils.PageUtils;
import com.ken.zshop.product.entity.BrandEntity;

import java.util.Map;

/**
 * 品牌表
 *
 * @author Ken
 * @email jimmyw3950@gmail.com
 * @date 2021-05-11 22:47:06
 */
public interface BrandService extends IService<BrandEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

