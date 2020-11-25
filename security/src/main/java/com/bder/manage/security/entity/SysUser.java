package com.bder.manage.security.entity;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

import static javax.persistence.TemporalType.TIMESTAMP;

/**
 * @author Felix YF Dong
 * @date 2020/11/25
 */
@Entity
@Data
@Table(name = "sys_user")
public class SysUser {

    @Id
    @GeneratedValue
    private String id;

    @Column(name = "username", nullable = false, unique = true)
    private String username;

    @Column(name = "real_name", nullable = false)
    private String realName;

    @Column(name = "age")
    private String age;

    @Column(name = "gender")
    private String gender;

    @Column(name = "phone", nullable = false)
    private String phone;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "create_by")
    private String createBy;

    @Column(name = "create_on")
    @Temporal(value = TIMESTAMP)
    private LocalDateTime createOn;

    @Column(name = "update_by")
    private String updateBy;

    @Column(name = "update_on")
    @Temporal(value = TIMESTAMP)
    private LocalDateTime updateOn;
}