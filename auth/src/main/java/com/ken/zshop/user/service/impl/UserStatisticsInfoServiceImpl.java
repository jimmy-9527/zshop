package com.ken.zshop.user.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ken.zshop.common.utils.PageUtils;
import com.ken.zshop.common.utils.Query;

import com.ken.zshop.user.dao.UserStatisticsInfoDao;
import com.ken.zshop.user.entity.UserStatisticsInfoEntity;
import com.ken.zshop.user.service.UserStatisticsInfoService;


@Service("userStatisticsInfoService")
public class UserStatisticsInfoServiceImpl extends ServiceImpl<UserStatisticsInfoDao, UserStatisticsInfoEntity> implements UserStatisticsInfoService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<UserStatisticsInfoEntity> page = this.page(
                new Query<UserStatisticsInfoEntity>().getPage(params),
                new QueryWrapper<UserStatisticsInfoEntity>()
        );

        return new PageUtils(page);
    }

}