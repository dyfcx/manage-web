package com.bder.manage.security.config;

/**
 * @author Felix YF Dong
 * @date 2020/11/26
 */

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;

/**
 * Spring Security 中的ResourceServerConfigurerAdapter配置会覆盖WebSecurityConfigurerAdapter
 * protected void configure(HttpSecurity http) 中的配置会以ResourceServerConfigurerAdapter为准
 * @author chenzhiling
 *
 */
@Configuration
@EnableResourceServer
public class CustomResServerConfiguration extends ResourceServerConfigurerAdapter {

    private static final String RESOURCE_ID = "storicid";

    @Override
    public void configure(ResourceServerSecurityConfigurer resources) {
        resources.resourceId(RESOURCE_ID).stateless(true);
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        // Since we want the protected resources to be accessible in the UI as well we need
        // session creation to be allowed (it's disabled by default in 2.0.6)
        http
                .httpBasic()
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
                .and()
                .authorizeRequests()
                .antMatchers("/")
                .permitAll()
                .and()
                .logout()
                .permitAll()
                .and()
                .authorizeRequests()
                .antMatchers("/oauth/*")
                .permitAll()
                .and()
                .authorizeRequests()
                .antMatchers("/v2/api-docs", "/configuration/**", "/swagger*/**", "/webjars/**", "/**/*.js", "/**/*.css", "/**/*.png")
                .permitAll()
                .and()
                .authorizeRequests()
                .regexMatchers(HttpMethod.POST, "/user")
                .permitAll()
                .and()
                .authorizeRequests()
                .antMatchers("/admin/**")
                .access("#oauth2.hasScope('read')")
                .antMatchers("/admin/**")
                .hasAnyAuthority("ROLE_ADMIN")
                .and()
                .authorizeRequests()
                .anyRequest()
                .authenticated()
                .and()
                //关闭默认的csrf认证
                .csrf()
                .disable();
    }
}
