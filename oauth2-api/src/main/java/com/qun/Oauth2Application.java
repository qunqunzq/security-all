package com.qun;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * 获取token的路径/oauth/toke
 *
 * 用何种验证方式
 * grant_type   password
 * 要获得的权限，就是配置服务时候的scopes
 * scope        read write
 * @author qun
 */

@SpringBootApplication
public class Oauth2Application {
    public static void main(String[] args) {
        SpringApplication.run(Oauth2Application.class, args);
    }
}
