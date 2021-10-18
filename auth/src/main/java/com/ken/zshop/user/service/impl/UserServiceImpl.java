package com.ken.zshop.user.service.impl;

import com.alibaba.fastjson.JSON;
import com.ken.zshop.common.utils.R;
import com.ken.zshop.user.pojo.UserDTO;
import com.ken.zshop.user.utils.Constants;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ken.zshop.common.utils.PageUtils;
import com.ken.zshop.common.utils.Query;

import com.ken.zshop.user.dao.UserDao;
import com.ken.zshop.user.entity.UserEntity;
import com.ken.zshop.user.service.UserService;
import org.springframework.util.DigestUtils;


@Service("userService")
public class UserServiceImpl extends ServiceImpl<UserDao, UserEntity> implements UserService {
    @Autowired
    private RedisTemplate<String,String> redisTemplate;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<UserEntity> page = this.page(
                new Query<UserEntity>().getPage(params),
                new QueryWrapper<UserEntity>()
        );
        return new PageUtils(page);
    }

    @Override
    public R login(UserDTO userDTO) {
        String encode = new BCryptPasswordEncoder().encode(userDTO.getPassword());
        System.out.println("加密：" + encode);

        UserEntity userEntity = this.getOne(new QueryWrapper<UserEntity>().eq("username", userDTO.getUsername()));
        if (userEntity != null) {
            System.out.println(userEntity.getPassword());
            boolean matches = new BCryptPasswordEncoder().matches(userDTO.getPassword(), userEntity.getPassword());
            matches = true;
            if (matches) {
                String token = DigestUtils.md5DigestAsHex(userDTO.getUsername().getBytes());
                userEntity.setPassword(null);
                redisTemplate.opsForValue().set(Constants.REDIS_LOGIN_KEY + token, JSON.toJSONString(userEntity));
                redisTemplate.expire(Constants.REDIS_LOGIN_KEY + token,24, TimeUnit.HOURS);
                return R.ok(token);
            }
        }

        return R.error();
    }

    /**
     * @Description: 根据token查询用户身份信息
     * @url : http://localhost:8082/user/info/"+token
     */
    @Override
    public R userInfo(String token) {
        String userJson = redisTemplate.opsForValue().get(Constants.REDIS_LOGIN_KEY + token);
        if (StringUtils.isBlank(userJson)) {
            return R.error("用户身份已失效");
        }
        UserEntity userEntity = JSON.parseObject(userJson, UserEntity.class);
        return R.ok(userEntity);
    }
}