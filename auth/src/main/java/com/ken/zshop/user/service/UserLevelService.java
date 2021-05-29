package com.ken.zshop.user.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ken.zshop.common.utils.PageUtils;
import com.ken.zshop.user.entity.UserLevelEntity;

import java.util.Map;

/**
 * 会员等级
 *
 * @author ithubin
 * @email ithubin@gmail.com
 * @date 2021-05-18 11:22:48
 */
public interface UserLevelService extends IService<UserLevelEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

