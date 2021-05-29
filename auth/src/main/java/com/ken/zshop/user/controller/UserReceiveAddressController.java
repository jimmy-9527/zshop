package com.ken.zshop.user.controller;

import java.util.Arrays;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ken.zshop.user.entity.UserReceiveAddressEntity;
import com.ken.zshop.user.service.UserReceiveAddressService;
import com.ken.zshop.common.utils.PageUtils;
import com.ken.zshop.common.utils.R;



/**
 * 会员收货地址
 *
 * @author ithubin
 * @email ithubin@gmail.com
 * @date 2021-05-18 11:22:47
 */
@RestController
@RequestMapping("user/userreceiveaddress")
public class UserReceiveAddressController {
    @Autowired
    private UserReceiveAddressService userReceiveAddressService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    //@RequiresPermissions("user:userreceiveaddress:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = userReceiveAddressService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    //@RequiresPermissions("user:userreceiveaddress:info")
    public R info(@PathVariable("id") Long id){
		UserReceiveAddressEntity userReceiveAddress = userReceiveAddressService.getById(id);

        return R.ok().put("userReceiveAddress", userReceiveAddress);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    //@RequiresPermissions("user:userreceiveaddress:save")
    public R save(@RequestBody UserReceiveAddressEntity userReceiveAddress){
		userReceiveAddressService.save(userReceiveAddress);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    //@RequiresPermissions("user:userreceiveaddress:update")
    public R update(@RequestBody UserReceiveAddressEntity userReceiveAddress){
		userReceiveAddressService.updateById(userReceiveAddress);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    //@RequiresPermissions("user:userreceiveaddress:delete")
    public R delete(@RequestBody Long[] ids){
		userReceiveAddressService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
