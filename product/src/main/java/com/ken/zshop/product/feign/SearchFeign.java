package com.ken.zshop.product.feign;

import com.ken.zshop.common.utils.R;
//import com.ken.zshop.product.entity.Blog;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient("zshop-search")
public interface SearchFeign {

    @GetMapping("hello/{name}")
    R sayHello(@PathVariable("name") String name);
}
