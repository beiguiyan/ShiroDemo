package com.shiro.me.shirodemo.controller;

import com.shiro.me.shirodemo.entity.UserInfo;
import com.shiro.me.shirodemo.service.IUserInfoService;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * author：yunshiyu
 * Date: 2023/8/917:09
 **/
@RestController
@Slf4j
public class UserInfoController {
    @Autowired
    private IUserInfoService userInfoService;

    /**
     * 按username账户从数据库中取出用户信息
     *
     * @param username 登录用户名
     * @return userInfo对象
     */
    @GetMapping("/userList")
    @RequiresPermissions("userInfo:view")  // 权限管理
    public UserInfo findUserInfoByUsername(@RequestParam String username) {
        return userInfoService.findByUsername(username);
    }

    @PostMapping("/userAdd")
    @RequiresPermissions("userInfo:add")
    public String addUserInfo() {
        return "addUserInfo success!";
    }

    @PostMapping("/userDelete")
    @RequiresPermissions("userInfo:delete")
    public String deleteUserInfo() {
        return "deleteUserInfo success!";
    }

}
