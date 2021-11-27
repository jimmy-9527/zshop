package com.ken.zshop.order.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ken.zshop.common.utils.PageUtils;
import com.ken.zshop.common.utils.Query;
import com.ken.zshop.order.dao.OrderReturnDao;
import com.ken.zshop.order.entity.OrderReturnEntity;
import com.ken.zshop.order.service.OrderReturnService;
import org.springframework.stereotype.Service;

import java.util.Map;


@Service("orderReturnService")
public class OrderReturnServiceImpl extends ServiceImpl<OrderReturnDao, OrderReturnEntity> implements OrderReturnService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<OrderReturnEntity> page = this.page(
                new Query<OrderReturnEntity>().getPage(params),
                new QueryWrapper<OrderReturnEntity>()
        );

        return new PageUtils(page);
    }

}