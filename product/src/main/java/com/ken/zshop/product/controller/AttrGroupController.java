package com.ken.zshop.product.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

// import org.apache.shiro.authz.annotation.RequiresPermissions;
import com.ken.zshop.product.entity.AttrEntity;
import com.ken.zshop.product.service.AttrAttrgroupRelationService;
import com.ken.zshop.product.service.AttrService;
import com.ken.zshop.product.service.CategoryService;
import com.ken.zshop.product.vo.AttrGroupReationVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.ken.zshop.product.entity.AttrGroupEntity;
import com.ken.zshop.product.service.AttrGroupService;
import com.ken.zshop.common.utils.PageUtils;
import com.ken.zshop.common.utils.R;



/**
 * 属性分组表
 *
 * @author Ken
 * @email jimmyw3950@gmail.com
 * @date 2021-05-14 21:43:00
 */
@RestController
@RequestMapping("product/attrgroup")
public class AttrGroupController {
    @Autowired
    private AttrGroupService attrGroupService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private AttrService attrService;

    @Autowired
    private AttrAttrgroupRelationService attrAttrgroupRelationService;

    /**
     * 列表，不同分类加载不同的属性分组
     * categoryId 分类ID
     */
    @RequestMapping("/list/{categoryId}")
    public R list(@RequestParam Map<String, Object> params, @PathVariable Integer categoryId){
        PageUtils page = attrGroupService.queryPage(params, categoryId);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    // @RequiresPermissions("product:attrgroup:info")
    public R info(@PathVariable("id") Long id){
        AttrGroupEntity attrGroup = attrGroupService.getById(id);
        attrGroup.setCategoryPath(categoryService.findCategoryPath(attrGroup.getCategoryId()));
        return R.ok().put("attrGroup", attrGroup);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    // @RequiresPermissions("product:attrgroup:save")
    public R save(@RequestBody AttrGroupEntity attrGroup){
		attrGroupService.save(attrGroup);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    // @RequiresPermissions("product:attrgroup:update")
    public R update(@RequestBody AttrGroupEntity attrGroup){
		attrGroupService.updateById(attrGroup);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    // @RequiresPermissions("product:attrgroup:delete")
    public R delete(@RequestBody Long[] ids){
		attrGroupService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

    @PostMapping("attr/relation/delete")
    public R deleteRelation(@RequestBody AttrGroupReationVo[] vos){
        attrService.deleteRelation(vos);
        return R.ok();

    }

    @GetMapping("{attrGroupId}/noattr/relation")
    public R attrRelation(@PathVariable("attrGroupId") Long attrGroupId,
                          @RequestParam Map<String,Object> params){
        PageUtils page = attrService.getNoRelationAttr(params,attrGroupId);
        return R.ok().put("page",page);
    }

    @PostMapping("/attr/relation")
    public R relationAttr(@RequestBody List<AttrGroupReationVo> vos){
        attrAttrgroupRelationService.saveBatch(vos);

        return R.ok();
    }

}
