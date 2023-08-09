package com.shiro.me.shirodemo.dao;

import com.shiro.me.shirodemo.entity.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * author：yunshiyu
 * Date: 2023/8/916:47
 **/
public interface UserInfoDao extends JpaRepository<UserInfo, Long> {

    /**
     * 通过用户登录名称查询用户对象
     *
     * @param username 登录用户名
     * @return UserInfo对象
     */
    UserInfo findByUsername(String username);
}
