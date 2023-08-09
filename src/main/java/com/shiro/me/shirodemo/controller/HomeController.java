package com.shiro.me.shirodemo.controller;

import com.shiro.me.shirodemo.service.IUserInfoService;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * author：yunshiyu
 * Date: 2023/8/916:54
 **/
@RestController
@Slf4j
public class HomeController {
    @GetMapping("/")
    public String home() {
        return "/index";
    }

    @GetMapping("/login")
    public String login(HttpServletRequest request, Map<String, Object> map) {
        log.info("===HomeController.login()===");
        String exception = (String) request.getAttribute("shiroLoginFailure");
        log.info("===HomeController.exception==={}" + exception);
        String msg = "";

        if (null != exception) {
            if (UnknownAccountException.class.getName().equals(exception)) {
                msg = "UnknownAccountException -- > 账号不存在：";
            } else if (IncorrectCredentialsException.class.getName().equals(exception)) {
                msg = "IncorrectCredentialsException -- > 密码不正确：";
            } else if ("kaptchaValidateFailed".equals(exception)) {
                msg = "kaptchaValidateFailed -- > 验证码错误";
            } else {
                msg = "else >> "+exception;
            }
        }

        log.info(msg);
        map.put("msg", msg);

        // 此方法不处理登录成功,由shiro进行处理
        return "/login";
    }

    @GetMapping("/403")
    public String unauthorizedRole(){
        log.info("------没有权限-------");
        return "403";
    }

}
