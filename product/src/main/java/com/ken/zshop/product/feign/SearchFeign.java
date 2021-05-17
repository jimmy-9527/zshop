package com.ken.zshop.product.feign;

import com.ken.zshop.common.utils.R;
import com.ken.zshop.product.entity.Blog;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient("zshop-search")
public interface SearchFeign {

    @GetMapping("hello/{name}")
    R sayHello(@PathVariable("name") String name);

    @GetMapping("hello")
    R sayHello2(@RequestParam("name") String name);

    @PostMapping("/blog")
    public R getBlog(@RequestBody Blog blog);

    @PostMapping("/blog2")
    public R getBlog2(@RequestBody Blog blog, @RequestParam("name") String name);

    @GetMapping("/spuinfo/putonsale/{spuId}")
    R putOnSale(@PathVariable("spuId") Long spuId);
}
