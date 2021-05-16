package com.ken.zshop.product.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

// import org.apache.shiro.authz.annotation.RequiresPermissions;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ken.zshop.product.entity.BrandEntity;
import com.ken.zshop.product.vo.BrandVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.ken.zshop.product.entity.CategoryBrandEntity;
import com.ken.zshop.product.service.CategoryBrandService;
import com.ken.zshop.common.utils.PageUtils;
import com.ken.zshop.common.utils.R;



/**
 * 分类品牌关系表
 *
 * @author Ken
 * @email jimmyw3950@gmail.com
 * @date 2021-05-15 09:26:12
 */
@RestController
@RequestMapping("product/categorybrand")
public class CategoryBrandController {
    @Autowired
    private CategoryBrandService categoryBrandService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    // @RequiresPermissions("product:categorybrand:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = categoryBrandService.queryPage(params);

        return R.ok().put("page", page);
    }

    /**
     * 根据品牌ID查询分类列表
     * @param brandId 品牌ID
     * @return
     */
    @GetMapping("/category/list")
    public R categorylist(@RequestParam("brandId") Integer brandId){

        QueryWrapper<CategoryBrandEntity> queryWrapper = new QueryWrapper<>();

        List<CategoryBrandEntity> data = categoryBrandService.getCategoryBrands(queryWrapper.eq("brand_id",brandId));

        return R.ok().put("data", data);
    }

    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    // @RequiresPermissions("product:categorybrand:info")
    public R info(@PathVariable("id") Integer id){
		CategoryBrandEntity categoryBrand = categoryBrandService.getById(id);

        return R.ok().put("categoryBrand", categoryBrand);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    // @RequiresPermissions("product:categorybrand:save")
    public R save(@RequestBody CategoryBrandEntity categoryBrand){
		categoryBrandService.save(categoryBrand);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    // @RequiresPermissions("product:categorybrand:update")
    public R update(@RequestBody CategoryBrandEntity categoryBrand){
		categoryBrandService.updateById(categoryBrand);

        return R.ok();
    }

    @RequestMapping("/delete")
    public R delete(@RequestBody CategoryBrandEntity categoryBrand){
        QueryWrapper<CategoryBrandEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("brand_id",categoryBrand.getBrandId()).
                eq("category_id",categoryBrand.getCategoryId());
        categoryBrandService.removeCategoryBrandEntity(queryWrapper);

        return R.ok();
    }

    /**
     * 根据分类id查询品牌
     * @param categoryId
     * @return
     */
    @GetMapping("/brands/list")
    //@RequiresPermissions("cubegoods:categorybrand:info")
    public R relationBrandsList(@RequestParam("categoryId") Integer categoryId){
        List<BrandEntity> vos = categoryBrandService.getBrandsByCategoryId(categoryId);

        //返回brandVo
        List<BrandVo> collect = vos.stream().map(item->{
            BrandVo brandVo = new BrandVo();
            brandVo.setBrandId(item.getId());
            brandVo.setBrandName(item.getName());
            return brandVo;
        }).collect(Collectors.toList());

        return R.ok().put("data", collect);
    }
}
