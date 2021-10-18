package com.ken.zshop.user.web;

import com.alibaba.fastjson.JSON;
import com.ken.zshop.user.config.OAuth2Properties;
import com.ken.zshop.user.entity.UserEntity;
import com.ken.zshop.user.pojo.vo.OAuthUser;
import com.ken.zshop.user.utils.Constants;
import com.ken.zshop.user.utils.CookieUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Slf4j
@Controller
public class OAuth2Controller {
    @Autowired
    private OAuth2Properties auth2Properties;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private RedisTemplate<String, String> redisTemplate;


    @GetMapping("/oauth/authorize")
    public String authorize() {
        String url = auth2Properties.getAuthorizationUrl()
                + "?client_id=" + auth2Properties.getClientId()
                + "&redirect_uri=" + auth2Properties.getRedirectUrl();
        log.info("redirect to github authorization url:{}", url);  // to get authorization code
        return "redirect:" + url;
    }

    /**
     * @Description: 根据回调地址调用第三方网站授权认证接口
     */
    @GetMapping("/auth2/success")
    public String callback(@RequestParam("code") String code,
                           Model model,
                           HttpServletRequest request,
                           HttpServletResponse response) {
        // get access_token
        // https://github.com/login/oauth/access_token?client_id...
        String url = auth2Properties.getAccessTokenUrl()
                + "?client_id=" + auth2Properties.getClientId()
                + "&client_secret=" + auth2Properties.getClientSecret()
                + "&code=" + code
                + "&grant_type=authorization_code";
        log.info("Get access_token url：{}", url);

        HttpHeaders headers = new HttpHeaders();
        headers.add("accept","application/json");
        HttpEntity<String> httpEntity = new HttpEntity<>(headers);
        ResponseEntity<String> responseEntity = restTemplate.postForEntity(url, httpEntity, String.class);

        String result = responseEntity.getBody();
        log.info("access_token: {}", result);
        Map<String,String> maps = JSON.parseObject(result, Map.class);
        String access_token = maps.get("access_token");

        // get user info via access_token
        OAuthUser oAuthUser = this.getUserInfo(access_token);

        UserEntity userEntity = new UserEntity();
        userEntity.setUsername(oAuthUser.getName());
        userEntity.setEmail(oAuthUser.getEmail());
        String token = DigestUtils.md5DigestAsHex(userEntity.getUsername().getBytes());
        redisTemplate.opsForValue().set(Constants.REDIS_LOGIN_KEY + token, JSON.toJSONString(userEntity));
        redisTemplate.expire(Constants.REDIS_LOGIN_KEY + token,24, TimeUnit.HOURS);

        CookieUtils.setCookie(request, response, Constants.COOKIE_LOGIN_KEY, token,
                86400, true);

        return "redirect:http://localhost:10000/15.html";
    }

    private OAuthUser getUserInfo(String access_token) {
        String url = auth2Properties.getUserInfoUrl();

        HttpHeaders headers = new HttpHeaders();
        headers.add("accept","application/json");
        headers.add("Authorization","token " + access_token);
        HttpEntity<String> httpEntity = new HttpEntity<>(headers);
        ResponseEntity<String> responseEntity = restTemplate.exchange(url,
                HttpMethod.GET, httpEntity, String.class);
        String result = responseEntity.getBody();

        OAuthUser oAuthUser = JSON.parseObject(result, OAuthUser.class);
        return oAuthUser;
    }
}

