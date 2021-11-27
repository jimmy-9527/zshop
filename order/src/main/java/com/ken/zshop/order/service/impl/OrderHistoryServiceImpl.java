package com.ken.zshop.order.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ken.zshop.common.utils.PageUtils;
import com.ken.zshop.common.utils.Query;
import com.ken.zshop.order.dao.OrderHistoryDao;
import com.ken.zshop.order.entity.OrderHistoryEntity;
import com.ken.zshop.order.service.OrderHistoryService;
import org.springframework.stereotype.Service;

import java.util.Map;


@Service("orderHistoryService")
public class OrderHistoryServiceImpl extends ServiceImpl<OrderHistoryDao, OrderHistoryEntity> implements OrderHistoryService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<OrderHistoryEntity> page = this.page(
                new Query<OrderHistoryEntity>().getPage(params),
                new QueryWrapper<OrderHistoryEntity>()
        );

        return new PageUtils(page);
    }

}