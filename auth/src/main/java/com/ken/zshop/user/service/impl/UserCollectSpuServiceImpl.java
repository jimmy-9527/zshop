package com.ken.zshop.user.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ken.zshop.common.utils.PageUtils;
import com.ken.zshop.common.utils.Query;

import com.ken.zshop.user.dao.UserCollectSpuDao;
import com.ken.zshop.user.entity.UserCollectSpuEntity;
import com.ken.zshop.user.service.UserCollectSpuService;


@Service("userCollectSpuService")
public class UserCollectSpuServiceImpl extends ServiceImpl<UserCollectSpuDao, UserCollectSpuEntity> implements UserCollectSpuService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<UserCollectSpuEntity> page = this.page(
                new Query<UserCollectSpuEntity>().getPage(params),
                new QueryWrapper<UserCollectSpuEntity>()
        );

        return new PageUtils(page);
    }

}