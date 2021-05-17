package com.ken.zshop.search.controller;

import com.ken.zshop.search.service.SpuInfoService;
import com.ken.zshop.common.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: sublun
 * @Date: 2021/4/27 11:53
 */
@RestController
@RequestMapping("/spuinfo")
public class SpuInfoController {
    @Autowired
    private SpuInfoService spuInfoService;
    private volatile boolean executeFlag = false;


    @GetMapping("putonsale/{spuId}")
    public R putOnSale(@PathVariable("spuId") Long spuId) {
        R r = spuInfoService.putOnSale(spuId);
        return r;
    }

    /**
     * 商品数据全量同步
     * @return
     */
    @GetMapping("/syncSpuInfo")
    public R syncSpuInfo() {
        if (!executeFlag) {
            synchronized (this) {
                if (!executeFlag) {
                    executeFlag = true;
                    R r = spuInfoService.syncSpuInfo();
                    return r;
                }
            }
        }
        return R.ok("数据正导入中，请勿重复执行");
    }
}
