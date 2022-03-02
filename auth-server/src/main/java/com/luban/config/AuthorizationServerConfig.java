package com.luban.config;

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
 * 认证中心配置
 */
@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

    @Autowired
    private PasswordEncoder passwordEncoder;//加密工具类

    @Autowired
    private AuthenticationManager authenticationManager;
    /**
     * 配置令牌端点(Token Endpoint)的安全约束,设置一些规则
     * @param security
     * @throws Exception
     */
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        security.checkTokenAccess("isAuthenticated()");//check toke必须要去认证
    }

    /**
     * 配置客户端详情服务，客户端详情信息在这里进行初始化，你能够把客户端详情信息写死在这里或者是通过数据库来存储调取详情信息
     * @param clients
     * @throws Exception
     */
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        //resourceIds与资源中心配置的resourceId保持一致
        clients.inMemory()
                //设置访问客户端
                .withClient("front_app").secret(passwordEncoder.encode("front_app")) //设置客户端密码
                .authorizedGrantTypes("password","authorization_code")//设置授权模式
                .scopes("read")//权限
                .accessTokenValiditySeconds(3600)//过期时间
                .resourceIds("order-service")//访问哪些资源
                .redirectUris("https://vip.tulingxueyuan.cn/index")//重定向地址
                //设置资源中心
                .and()
                .withClient("order_app")
                .secret(passwordEncoder.encode("order_app"))
                .accessTokenValiditySeconds(1800)
                .scopes("read")
                .authorizedGrantTypes("password")
                .resourceIds("order-service");
                //
             /*   .and()
                .withClient("product_app")
                .secret(passwordEncoder.encode("product_app"))
                .accessTokenValiditySeconds(1800)
                .scopes("read")
                .authorizedGrantTypes("password")
                .resourceIds("product-service");*/
    }

    /**
     * 配置授权（authorization）以及令牌（token）的访问端点和令牌服务(token services)，还有token的存储方式(tokenStore)
     * @param endpoints
     * @throws Exception
     */
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        //这里，认证服务器委托一个AuthenticationManager 来验证我们的用户信息
        endpoints.authenticationManager(authenticationManager);
    }
}
