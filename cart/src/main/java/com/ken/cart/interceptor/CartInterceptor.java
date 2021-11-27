package com.ken.cart.interceptor;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;

import com.ken.cart.config.SpringBeansUtils;
import com.ken.cart.dto.UserInfoDTO;
import com.ken.cart.entity.UserEntity;
import com.ken.cart.rpc.AuthRemoteClient;
import com.ken.cart.utils.Constants;
import com.ken.cart.utils.CookieUtils;
import com.ken.zshop.common.utils.R;
import org.apache.commons.lang3.StringUtils;
import org.springframework.lang.Nullable;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

public class CartInterceptor implements HandlerInterceptor{
    public static ThreadLocal<UserInfoDTO> dtoThreadLocal = new ThreadLocal<>();

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        // 注入远程调用rpc接口
        AuthRemoteClient remoteClient = (AuthRemoteClient) SpringBeansUtils.getBeanClass(AuthRemoteClient.class);

        // 创建对象，判断用户处于状态
        UserInfoDTO userInfoDTO = new UserInfoDTO();

        // 获取cookie中token，判断token是否存在，如果token不存在，表示用户没有登录
        String token = CookieUtils.getCookieValue(request, Constants.COOKIE_LOGIN_KEY, true);
        if(StringUtils.isNotBlank(token)){
            // 根据接口远程调用认证服务，获取用户身份信息
            String jsonUser = remoteClient.userInfoByToken(token);

            if(StringUtils.isNotBlank(jsonUser)){
                // 转换对象
                R r = JSON.parseObject(jsonUser, R.class);
                // 判断
                if(r.getCode() == 0){

                    // 获取用户身份信息对象
                    UserEntity result = r.getData("result", new TypeReference<UserEntity>() {
                    });
                    // 用户处于登录状态
                    userInfoDTO.setUserId(result.getId());
                }

            }
        }

        // 如果没有登录，标记此用户为一个临时用户
        // 1、从cookie中获取购物车临时用户信息，判断此用户是否存在
        String userKey = CookieUtils.getCookieValue(request, Constants.COOKIE_TEMP_CART_KEY, true);
        // 如果这个key存在，标记此用户为一个临时用户
        if(StringUtils.isNotBlank(userKey)){
            userInfoDTO.setUserKey(userKey);
            userInfoDTO.setTempUser(true);
        }

        // 如果用户第一次来网站，没有登录，必须分配一个临时用户
        if(StringUtils.isBlank(userInfoDTO.getUserKey())){
            String userToken = UUID.randomUUID().toString();
            userInfoDTO.setUserKey(userToken);
        }

        // 使用ThreadLocal存储用户信息
        dtoThreadLocal.set(userInfoDTO);

        // 用户无论登录与否
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable ModelAndView modelAndView) throws Exception {
        // 获取用户当前身份信息
        UserInfoDTO userInfoDTO = dtoThreadLocal.get();
        // 如果没有临时用户，需要保存临时用户
        if(!userInfoDTO.getTempUser()){
            // 把用户身份信息存储在cookie
            CookieUtils.setCookie(request,
                    response,
                    Constants.COOKIE_TEMP_CART_KEY,
                    userInfoDTO.getUserKey(),
                    Constants.COOKIE_TEMP_CART_KEY_TIMEOUT,
                    true);

        }


    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable Exception ex) throws Exception {

    }
}

