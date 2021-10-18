package com.ken.zshop.user.pojo;

import lombok.Data;
import lombok.ToString;

@ToString
@Data
public class UserDTO {
    private String username;
    private String password;

    // 是否自动登录
    private String auto;
}