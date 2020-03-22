package com.qun.server.resource;

import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.stereotype.Component;

/**
 * 声明这是一个资源服务项目
 */
@Component
@EnableResourceServer
public class Oauth2ResourceServerConfig extends ResourceServerConfigurerAdapter {
    /**
     * 配置资源服务器的id 资源服务的一些配置
     * @param resources
     * @throws Exception
     */
    @Override
    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
     resources.resourceId("order-server");
    }

    /**
     * 那些请求需要认证，这里配置规则
     * @param http
     * @throws Exception
     */
    @Override
    public void configure(HttpSecurity http) throws Exception {
        //这里就是说除了haha的请求 之外的都要进行token认证
       http.authorizeRequests().antMatchers("/haha").permitAll()
               //增加权限控制
               .antMatchers(HttpMethod.GET).access("#oauth2.hasScope('read')")
               .antMatchers(HttpMethod.POST).access("#oauth2.hasScope('write')")
               .anyRequest().authenticated();
    }
}
