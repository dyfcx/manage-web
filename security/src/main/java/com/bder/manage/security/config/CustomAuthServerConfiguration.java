package com.bder.manage.security.config;

import com.bder.manage.security.helper.PasswordHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.store.InMemoryTokenStore;

import java.util.concurrent.TimeUnit;

/**
 * @author Felix YF Dong
 * @date 2020/11/26
 */
@Configuration
@EnableAuthorizationServer
public class CustomAuthServerConfiguration extends AuthorizationServerConfigurerAdapter {

    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    UserDetailsService userDetailsService;

    private static final String DEMO_RESOURCE_ID = "storicid";

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        //Oauth2 默认使用BCryptPasswordEncoder作为加密方法，需要在加密之后的字符前加{bcrypt}
        //String finalSecret = "{bcrypt}" + new BCryptPasswordEncoder().encode("storic-secret");
        String finalSecret = PasswordHelper.encryptPassword("storic-secret");
        //这里通过实现 ClientDetailsService接口 clients.withClientDetails(new BaseClientDetailService());
        //配置客户端,一个用于password认证一个用于client认证
        clients
                .inMemory()
                .withClient("client_app")
                .resourceIds(DEMO_RESOURCE_ID)
                .authorizedGrantTypes("client_credentials", "refresh_token", "password")
                .scopes("select")
                .authorities("oauth2")
                .secret(finalSecret)
                .accessTokenValiditySeconds((int) TimeUnit.DAYS.toSeconds(1))
                .refreshTokenValiditySeconds((int)TimeUnit.DAYS.toSeconds(1))
                .and()
                .withClient("client_web")
                .resourceIds(DEMO_RESOURCE_ID)
                .authorizedGrantTypes("authorization_code", "client_credentials", "refresh_token",
                        "password", "implicit")
                .scopes("read", "write", "trust")
                .authorities("oauth2")
                .secret(finalSecret)
                .accessTokenValiditySeconds((int)TimeUnit.DAYS.toSeconds(1))
                .refreshTokenValiditySeconds((int)TimeUnit.DAYS.toSeconds(1));
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints
                .tokenStore(new InMemoryTokenStore())
                .authenticationManager(authenticationManager)
                .userDetailsService(userDetailsService) // 不添加无法正常使用refresh_token
                .allowedTokenEndpointRequestMethods(HttpMethod.GET, HttpMethod.POST, HttpMethod.PUT, HttpMethod.DELETE);
    }

    @Override
    public void configure(AuthorizationServerSecurityConfigurer oauthServer) throws Exception {
        //允许表单认证
        //这里增加拦截器到安全认证链中，实现自定义认证，包括图片验证，短信验证，微信小程序，第三方系统，CAS单点登录
        //addTokenEndpointAuthenticationFilter(IntegrationAuthenticationFilter())
        //IntegrationAuthenticationFilter 采用 @Component 注入
        oauthServer
                .realm(DEMO_RESOURCE_ID)
                .tokenKeyAccess("permitAll()")
                .checkTokenAccess("isAuthenticated()")
                .allowFormAuthenticationForClients();
    }

}
