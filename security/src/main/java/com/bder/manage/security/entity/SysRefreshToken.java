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
@Table(name = "sys_refresh_token")
public class SysRefreshToken {

    @Id
    @Column(name = "token_id")
    private String tokenId;

    @Column(name=" token", columnDefinition="longtext")
    private String token;

    /**
     * OAuth2Authentication.java对象序列化内容
     */
    @Lob
    @Basic(fetch = FetchType.LAZY)
    @Column(name="authentication", columnDefinition="longblob")
    private byte[] authentication;

    @Column(name = "create_on")
    @CreatedDate
    private LocalDateTime createOn;

}
