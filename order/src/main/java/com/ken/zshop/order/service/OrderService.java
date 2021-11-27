package com.ken.zshop.order.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ken.zshop.common.utils.PageUtils;
import com.ken.zshop.order.entity.OrderEntity;
import com.ken.zshop.order.service.dto.SubmitOrderDTO;
import com.ken.zshop.order.web.vo.OrderConfirmVo;
import com.ken.zshop.order.web.vo.OrderResultVo;
import com.ken.zshop.order.web.vo.PayVo;

import java.util.Map;

public interface OrderService extends IService<OrderEntity> {

    PageUtils queryPage(Map<String, Object> params);

    /**
     * @Description: 查询订单结算页相关数据
     *
     * 1、地址收货信息
     * 2、购物清单
     * @Author: hubin
     * @CreateDate: 2021/5/31 15:02
     * @UpdateUser: hubin
     * @UpdateDate: 2021/5/31 15:02
     * @UpdateRemark: 修改内容
     * @Version: 1.0
     */
    OrderConfirmVo confirmOrder();

    /**
     * @Description: 提交订单的方法
     * @Author: hubin
     * @CreateDate: 2021/6/4 15:53
     * @UpdateUser: hubin
     * @UpdateDate: 2021/6/4 15:53
     * @UpdateRemark: 修改内容
     * @Version: 1.0
     */
    OrderResultVo submitOrder(SubmitOrderDTO submitOrderDTO);

    /**
     * @Description: 根据订单id查询订单信息
     * @Author: hubin
     * @CreateDate: 2021/6/8 18:40
     * @UpdateUser: hubin
     * @UpdateDate: 2021/6/8 18:40
     * @UpdateRemark: 修改内容
     * @Version: 1.0
     */
    PayVo getOrderById(String orderId);
}

