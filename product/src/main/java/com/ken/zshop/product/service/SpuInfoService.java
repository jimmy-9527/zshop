package com.ken.zshop.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ken.zshop.common.utils.PageUtils;
import com.ken.zshop.common.utils.R;
import com.ken.zshop.product.entity.SpuInfoEntity;
import com.ken.zshop.product.exception.RemoteServiceCallExeption;
import com.ken.zshop.product.vo.SpuSaveVo;

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

    void saveBaseSpuInfo(SpuInfoEntity infoEntity);

    void saveSpuInfo(SpuSaveVo vo);

    R putOnSale(Long spuId)  throws RemoteServiceCallExeption;
}

