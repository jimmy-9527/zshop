package com.ken.zshop.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ken.zshop.common.utils.PageUtils;
import com.ken.zshop.product.entity.AttrEntity;

import java.util.Map;

/**
 * 属性表
 *
 * @author Ken
 * @email jimmyw3950@gmail.com
 * @date 2021-05-14 21:43:00
 */
public interface AttrService extends IService<AttrEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

