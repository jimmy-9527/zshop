package com.ken.zshop.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ken.zshop.common.utils.PageUtils;
import com.ken.zshop.product.entity.SpuInfoEntity;

import java.util.Map;

/**
 * spu信息
 *
 * @author Ken
 * @email jimmyw3950@gmail.com
 * @date 2021-05-15 15:46:37
 */
public interface SpuInfoService extends IService<SpuInfoEntity> {

    PageUtils queryPage(Map<String, Object> params);
}
