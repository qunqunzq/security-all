package com.qun.oauth;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;

/**
 * 这认证服务器的配置
 */
@Configuration
@EnableAuthorizationServer
public class OauthConfig extends AuthorizationServerConfigurerAdapter {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    /**
     * 配置用户的认证，哪些用户可以获取到token。这里就需要用到authenticationManager，
     * 它在配置WebSecurityConfigurerAdapter里配就行
     * @param endpoints
     * @throws Exception
     */
    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints.authenticationManager(authenticationManager);
    }

    /**
     * 配置哪些客户端
     * @param clients
     * @throws Exception
     */
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        //首先是从内存中取，最后改成数据库
        clients.inMemory()
                //授权的应用名称，就是哪个客户端
                .withClient("orderApp")
                //这个客户端也需要密码认证
                .secret(passwordEncoder.encode("123456"))
                //这个客户端有什么样的权限
                .scopes("read", "write")
                //发的这个token的过期时间
                .accessTokenValiditySeconds(3600)
                //就是这个客户端可以访问的资源服务器
                .resourceIds("order-server")
                //这个客户端的认证方式
                .authorizedGrantTypes("password")
                .and()
                //授权的应用名称，就是哪个客户端
                .withClient("orderService")
                //这个客户端也需要密码认证
                .secret(passwordEncoder.encode("123456"))
                //这个客户端有什么样的权限
                .scopes("read", "write")
                //发的这个token的过期时间
                .accessTokenValiditySeconds(3600)
                //就是这个客户端可以访问的资源服务器
                .resourceIds("order-server")
                //这个客户端的认证方式
                .authorizedGrantTypes("password");
    }

    /**
     * 客户端要来验证token 需要有什么样的规则
     * @param security
     * @throws Exception
     */
    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        //需要有用户名密码的验证才能来验证token
        security.checkTokenAccess("isAuthenticated()");
    }
}
