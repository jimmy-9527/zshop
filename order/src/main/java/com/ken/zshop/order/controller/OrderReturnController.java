package com.ken.zshop.order.controller;

import com.ken.zshop.common.utils.PageUtils;
import com.ken.zshop.common.utils.R;
import com.ken.zshop.order.entity.OrderReturnEntity;
import com.ken.zshop.order.service.OrderReturnService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Map;


/**
 * 订单退货申请
 *
 * @author ithubin
 * @email ithubin@gmail.com
 * @date 2021-05-28 18:50:53
 */
@RestController
@RequestMapping("user/orderreturn")
public class OrderReturnController {
    @Autowired
    private OrderReturnService orderReturnService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    //@RequiresPermissions("user:orderreturn:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = orderReturnService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    //@RequiresPermissions("user:orderreturn:info")
    public R info(@PathVariable("id") Long id){
		OrderReturnEntity orderReturn = orderReturnService.getById(id);

        return R.ok().put("orderReturn", orderReturn);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    //@RequiresPermissions("user:orderreturn:save")
    public R save(@RequestBody OrderReturnEntity orderReturn){
		orderReturnService.save(orderReturn);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    //@RequiresPermissions("user:orderreturn:update")
    public R update(@RequestBody OrderReturnEntity orderReturn){
		orderReturnService.updateById(orderReturn);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    //@RequiresPermissions("user:orderreturn:delete")
    public R delete(@RequestBody Long[] ids){
		orderReturnService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
