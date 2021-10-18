package com.ken.zshop.user.pojo.vo;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class OAuthUser {
    private String id;
    private String login;
    private String name;
    private String email;
}

