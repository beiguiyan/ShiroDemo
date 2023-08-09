package com.shiro.me.shirodemo.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import java.util.List;

/**
 * author：yunshiyu
 * Date: 2023/8/915:25
 **/
@Entity
@Data
public class UserInfo {
    /**
     * 主键
     */
    @Id
    @GeneratedValue
    private long id;

    /**
     * 登录账户，唯一
     */
    private String username;

    /**
     * 匿名或真实名称
     */
    private String name;

    /**
     * 登录密码
     */
    private String password;

    /**
     * 加密密码的盐
     */
    private String salt;

    /**
     * 一个用户具有多个角色
     */
    @ManyToMany(fetch = FetchType.EAGER)
    @JsonIgnoreProperties(value = {"userInfos"})
    @JoinTable(name = "SysUserRole",
            joinColumns = @JoinColumn(name = "uid"),
            inverseJoinColumns = @JoinColumn(name = "roleId"))
    private List<SysRole> roles;

}
