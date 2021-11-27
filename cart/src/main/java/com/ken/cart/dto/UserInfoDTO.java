package com.ken.cart.dto;

import lombok.Data;
import lombok.ToString;

@ToString
@Data
public class UserInfoDTO {
    private Long userId; // 用户登录使用的
    private String userKey; // 标识临时用户的key,未登录使用这个值
    private Boolean tempUser = false;
}

