package com.luban.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;

/**
 * 资源中心配置
 */
@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {
    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
        resources.resourceId("order-service");
    }

    public void configure(HttpSecurity http) throws Exception {
            //访问”/order/find/**“要有‘read’权限
        http.authorizeRequests().antMatchers("/order/find/**").access("#oauth2.hasScope('read')")
                //访问”/order/save/**“要有‘write’权限
            .and().authorizeRequests().antMatchers("/order/save/**").access("#oauth2.hasScope('write')");
    }
}
