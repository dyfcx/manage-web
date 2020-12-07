package com.bder.manage.security.entity;

import lombok.Data;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * @author Felix YF Dong
 * @date 2020/12/7
 */
@Entity
@Data
@Table(name = "sys_access_token")
public class SysAccessToken {

    @Id
    @Column(name = "token_id")
    private String tokenId;

    @Column(name=" token", columnDefinition="longtext")
    private String token;

    @Column(name = "user_name")
    private String userName;

    @Column(name = "client_id")
    private String clientId;

    /**
     * 根据当前的username、client_id与scope通过MD5加密生成该字段的值
     */
    @Column(name="authentication_id", columnDefinition="longtext")
    private String authenticationId;

    /**
     * OAuth2Authentication.java对象序列化内容
     */
    @Lob
    @Basic(fetch = FetchType.LAZY)
    @Column(name="authentication", columnDefinition="longblob", nullable=true)
    private byte[] authentication;

    /**
     * RefreshToken标识：通过MD5加密refresh_token的值
     */
    @Column(name=" refresh_token", columnDefinition="longtext")
    private String refreshToken;

    @Column(name = "create_on")
    @CreatedDate
    private LocalDateTime createOn;

}
