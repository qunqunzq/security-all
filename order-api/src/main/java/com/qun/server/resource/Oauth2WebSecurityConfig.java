package com.qun.server.resource;

import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationManager;
import org.springframework.security.oauth2.provider.token.RemoteTokenServices;
import org.springframework.security.oauth2.provider.token.ResourceServerTokenServices;
import org.springframework.stereotype.Component;

@Component
@EnableWebSecurity
public class Oauth2WebSecurityConfig extends WebSecurityConfigurerAdapter {

    /**
     * 验证token的服务是什么，这里配置
     * @return
     */
    @Bean
    public ResourceServerTokenServices resourceServerTokenServices(){
        RemoteTokenServices remoteTokenServices = new RemoteTokenServices();
        remoteTokenServices.setClientId("orderService");
        remoteTokenServices.setClientSecret("123456");
        remoteTokenServices.setCheckTokenEndpointUrl("http://127.0.0.1:9090/oauth/check_token");
        return remoteTokenServices;
    }

    @Bean
    @Override
    protected AuthenticationManager authenticationManager() throws Exception {
        OAuth2AuthenticationManager oAuth2AuthenticationManager = new OAuth2AuthenticationManager();
        oAuth2AuthenticationManager.setTokenServices(resourceServerTokenServices());
        return  oAuth2AuthenticationManager;
    }
}