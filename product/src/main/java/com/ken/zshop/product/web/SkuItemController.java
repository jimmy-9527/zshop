package com.ken.zshop.product.web;

import com.ken.zshop.product.service.SkuInfoService;
import com.ken.zshop.product.vo.SkuItemVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Slf4j
@Controller
public class SkuItemController {

    @Autowired
    private SkuInfoService skuInfoService;

    @GetMapping("/{skuId}.html")
    public String skuItem(@PathVariable Long skuId, Model model){
        // 调用服务层商品详情接口
        SkuItemVo itemVo = skuInfoService.skuItem(skuId);

        // 输入日志
        log.info("商品详情接口，查询的数据：{}",itemVo);


        // 把数据放入模型驱动
        model.addAttribute("item",itemVo);

        // 返回视图页面，做视图数据渲染
        return "item";
    }


}

