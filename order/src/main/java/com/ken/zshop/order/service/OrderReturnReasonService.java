package com.ken.zshop.order.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ken.zshop.common.utils.PageUtils;
import com.ken.zshop.order.entity.OrderReturnReasonEntity;

import java.util.Map;

/**
 * 退货原因
 *
 * @author ithubin
 * @email ithubin@gmail.com
 * @date 2021-05-28 18:50:54
 */
public interface OrderReturnReasonService extends IService<OrderReturnReasonEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

