package com.ken.zshop.user.config;

import lombok.Data;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@ToString
@Component
@ConfigurationProperties(prefix = "github")
public class OAuth2Properties {
    private String clientId;
    private String clientSecret;
    private String authorizationUrl;
    private String redirectUrl;
    private String accessTokenUrl;
    private String userInfoUrl;
}

