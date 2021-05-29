package com.ken.zshop.user.controller;

import java.util.Arrays;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ken.zshop.user.entity.UserCollectSpuEntity;
import com.ken.zshop.user.service.UserCollectSpuService;
import com.ken.zshop.common.utils.PageUtils;
import com.ken.zshop.common.utils.R;

@RestController
@RequestMapping("user/usercollectspu")
public class UserCollectSpuController {
    @Autowired
    private UserCollectSpuService userCollectSpuService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    //@RequiresPermissions("user:usercollectspu:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = userCollectSpuService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    //@RequiresPermissions("user:usercollectspu:info")
    public R info(@PathVariable("id") Long id){
		UserCollectSpuEntity userCollectSpu = userCollectSpuService.getById(id);

        return R.ok().put("userCollectSpu", userCollectSpu);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    //@RequiresPermissions("user:usercollectspu:save")
    public R save(@RequestBody UserCollectSpuEntity userCollectSpu){
		userCollectSpuService.save(userCollectSpu);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    //@RequiresPermissions("user:usercollectspu:update")
    public R update(@RequestBody UserCollectSpuEntity userCollectSpu){
		userCollectSpuService.updateById(userCollectSpu);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    //@RequiresPermissions("user:usercollectspu:delete")
    public R delete(@RequestBody Long[] ids){
		userCollectSpuService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
