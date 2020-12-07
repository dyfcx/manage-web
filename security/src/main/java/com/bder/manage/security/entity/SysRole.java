package com.bder.manage.security.entity;

import lombok.Data;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * @author Felix YF Dong
 * @date 2020/12/7
 */
@Entity
@Data
@Table(name = "sys_role")
public class SysRole {

    @Id
    @GeneratedValue
    private String id;

    @Column(name = "user_code", nullable = false, unique = true)
    private String roleCode;

    @Column(name = "role_name", nullable = false)
    private String roleName;

    @Column(name = "description")
    private String description;

    @Column(name = "create_by")
    @CreatedBy
    private String createBy;

    @Column(name = "create_on")
    @CreatedDate
    private LocalDateTime createOn;

    @Column(name = "update_by")
    @LastModifiedBy
    private String updateBy;

    @Column(name = "update_on")
    @LastModifiedDate
    private LocalDateTime updateOn;

}
