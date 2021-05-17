package com.ken.zshop.search.service;

import com.ken.zshop.common.utils.R;

/**
 * @Author: sublun
 * @Date: 2021/4/27 11:48
 */
public interface SpuInfoService {
    public R putOnSale(long spuId);
    public R syncSpuInfo();
}
