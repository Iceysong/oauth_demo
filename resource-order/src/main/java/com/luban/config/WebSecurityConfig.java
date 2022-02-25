package com.luban.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationManager;
import org.springframework.security.oauth2.provider.token.RemoteTokenServices;
import org.springframework.security.oauth2.provider.token.ResourceServerTokenServices;
import org.springframework.web.client.RestTemplate;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private RestTemplate restTemplate;

    /* @Autowired
    private TulingUserDetailService tulingUserDetailService;*/


    /**
     * 资源服务器 要去资源服务器校验的的bean
     * @return
     */
    @Bean
    public ResourceServerTokenServices resourceServerTokenServices() {
        RemoteTokenServices remoteTokenServices = new RemoteTokenServices();
        remoteTokenServices.setClientId("order_app");
        remoteTokenServices.setClientSecret("order_app");
        remoteTokenServices.setCheckTokenEndpointUrl("http://auth-server/oauth/check_token");
        remoteTokenServices.setRestTemplate(restTemplate);
        //校验令牌只会拿到用户名,但是我需要获取到整个的用户信息  就需要这个转换器
        //remoteTokenServices.setAccessTokenConverter(accessTokenConverter());
        return remoteTokenServices;
    }


    /**
     * 资源服务器 要去资源服务器校验的的bean  该bean会作为属性塞入到
     * OAuth2AuthenticationProcessingFilter过滤器，该过滤器就会通过AuthenticationManager
     * 中的resourceServerTokenServices组件去 认证服务器 校验我们的token。
     * @return
     */
    @Bean
    public AuthenticationManager authenticationManagerBean() {
        OAuth2AuthenticationManager manager = new OAuth2AuthenticationManager();
        manager.setTokenServices(resourceServerTokenServices());
        return manager;
    }
}
