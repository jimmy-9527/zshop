package com.ken.zshop.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ken.zshop.common.utils.PageUtils;
import com.ken.zshop.product.entity.SkuImagesEntity;

import java.util.Map;

/**
 * sku图片表
 *
 * @author Ken
 * @email jimmyw3950@gmail.com
 * @date 2021-05-15 16:20:49
 */
public interface SkuImagesService extends IService<SkuImagesEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

