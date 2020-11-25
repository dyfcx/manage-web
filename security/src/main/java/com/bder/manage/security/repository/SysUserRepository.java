package com.bder.manage.security.repository;

import com.bder.manage.security.entity.SysUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Felix YF Dong
 * @date 2020/11/25
 */
@Repository
public interface SysUserRepository extends JpaRepository<SysUser, String> {

    SysUser findSysUserByUsername(String username);
}
