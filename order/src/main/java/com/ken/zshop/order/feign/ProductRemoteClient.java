package com.ken.zshop.order.feign;

import com.ken.zshop.common.utils.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @ClassName ProductRemoteClient
 * @Description
 * @Author hubin
 * @Date 2021/6/7 10:46
 * @Version V1.0
 **/
@FeignClient("product")
public interface ProductRemoteClient {

    @RequestMapping("/product/spuinfo/spu/{spuId}")
    public R getSpuInfoBySkuId(@PathVariable("spuId") Long skuId);

}
