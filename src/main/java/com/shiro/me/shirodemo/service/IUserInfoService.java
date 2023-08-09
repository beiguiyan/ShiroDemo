package com.shiro.me.shirodemo.service;

import com.shiro.me.shirodemo.entity.UserInfo;

/**
 * author：yunshiyu
 * Date: 2023/8/916:50
 **/
public interface IUserInfoService {
    /**
     * 通过用户登录名称查询用户对象
     *
     * @param username 登录用户名
     * @return UserInfo对象
     */
    UserInfo findByUsername(String username);
}
