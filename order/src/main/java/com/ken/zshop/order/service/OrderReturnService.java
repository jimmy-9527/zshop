package com.ken.zshop.order.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ken.zshop.common.utils.PageUtils;
import com.ken.zshop.order.entity.OrderReturnEntity;

import java.util.Map;

/**
 * 订单退货申请
 *
 * @author ithubin
 * @email ithubin@gmail.com
 * @date 2021-05-28 18:50:53
 */
public interface OrderReturnService extends IService<OrderReturnEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

