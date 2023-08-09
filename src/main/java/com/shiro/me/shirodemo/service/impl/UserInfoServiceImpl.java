package com.shiro.me.shirodemo.service.impl;

import com.shiro.me.shirodemo.dao.UserInfoDao;
import com.shiro.me.shirodemo.entity.UserInfo;
import com.shiro.me.shirodemo.service.IUserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * author：yunshiyu
 * Date: 2023/8/916:52
 **/
@Service
public class UserInfoServiceImpl implements IUserInfoService {
    @Autowired
    private UserInfoDao userInfoDao;

    @Override
    public UserInfo findByUsername(String username) {
        return userInfoDao.findByUsername(username);
    }
}
