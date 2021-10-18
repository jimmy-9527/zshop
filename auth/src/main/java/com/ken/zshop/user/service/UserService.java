package com.ken.zshop.user.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ken.zshop.common.utils.PageUtils;
import com.ken.zshop.common.utils.R;
import com.ken.zshop.user.entity.UserEntity;
import com.ken.zshop.user.pojo.UserDTO;

import java.util.Map;

public interface UserService extends IService<UserEntity> {

    PageUtils queryPage(Map<String, Object> params);
    public R login(UserDTO userDTO);

    /**
     * @Description: 根据token查询用户身份信息
     * @url : http://localhost:8082/user/info/" + token
     */
    R userInfo(String token);
}

