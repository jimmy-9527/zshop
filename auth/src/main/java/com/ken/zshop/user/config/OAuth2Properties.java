package com.ken.zshop.user.config;

import lombok.Data;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@ToString
@Component
// @ConfigurationProperties(prefix = "github")
public class OAuth2Properties {

    //客户端ID
    private String clientId;
    //客户端秘钥
    private String clientSecret;
    //用户授权地址（返回授权码）
    private String authorizationUrl;
    //回调地址，获取access_token
    private String redirectUrl;
    //认证服务器生成access_token
    private String accessTokenUrl;
    //获取用户身份信息
    private String userInfoUrl;

}

