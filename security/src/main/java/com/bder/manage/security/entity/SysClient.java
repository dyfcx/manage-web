package com.bder.manage.security.entity;

import lombok.Data;

import javax.persistence.*;

/**
 * @author Felix YF Dong
 * @date 2020/12/7
 */
@Entity
@Data
@Table(name = "sys_client")
public class SysClient {

    @Id
    @GeneratedValue
    @Column(name = "client_id")
    private String clientId;

    @Column(name = "client_secret")
    private String clientSecret;

    @Column(name = "scope")
    private String scope;

    @Column(name = "authorized_grant_types")
    private String authorizedGrantTypes;

    @Column(name = "authorities")
    private String authorities;

    @Column(name = "access_token_validity")
    private String accessTokenValidity;

    @Column(name = "refresh_token_validity")
    private String refreshTokenValidity;
}
