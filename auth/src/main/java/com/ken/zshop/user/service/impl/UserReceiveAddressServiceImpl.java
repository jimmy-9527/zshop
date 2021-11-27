package com.ken.zshop.user.service.impl;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ken.zshop.common.utils.PageUtils;
import com.ken.zshop.common.utils.Query;

import com.ken.zshop.user.dao.UserReceiveAddressDao;
import com.ken.zshop.user.entity.UserReceiveAddressEntity;
import com.ken.zshop.user.service.UserReceiveAddressService;


@Service("userReceiveAddressService")
public class UserReceiveAddressServiceImpl extends ServiceImpl<UserReceiveAddressDao, UserReceiveAddressEntity> implements UserReceiveAddressService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<UserReceiveAddressEntity> page = this.page(
                new Query<UserReceiveAddressEntity>().getPage(params),
                new QueryWrapper<UserReceiveAddressEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public List<UserReceiveAddressEntity> addressList(Long userId) {
        List<UserReceiveAddressEntity> addressEntityList = this.baseMapper.selectList(new QueryWrapper<UserReceiveAddressEntity>().eq("user_id", userId));
        return addressEntityList;
    }

}