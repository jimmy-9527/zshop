package com.ken.zshop.order.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ken.zshop.common.utils.PageUtils;
import com.ken.zshop.order.entity.OrderHistoryEntity;

import java.util.Map;

/**
 * 订单操作历史记录
 *
 * @author ithubin
 * @email ithubin@gmail.com
 * @date 2021-05-28 18:50:53
 */
public interface OrderHistoryService extends IService<OrderHistoryEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

