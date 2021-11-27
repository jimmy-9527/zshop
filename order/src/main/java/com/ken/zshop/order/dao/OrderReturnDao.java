package com.ken.zshop.order.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ken.zshop.order.entity.OrderReturnEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * 订单退货申请
 * 
 * @author ithubin
 * @email ithubin@gmail.com
 * @date 2021-05-28 18:50:53
 */
@Mapper
public interface OrderReturnDao extends BaseMapper<OrderReturnEntity> {
	
}
