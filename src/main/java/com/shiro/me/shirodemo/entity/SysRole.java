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
 * Date: 2023/8/915:34
 **/
@Entity
@Data
public class SysRole {
    /**
     * 主键
     */
    @Id
    @GeneratedValue
    private long id;

    /**
     * 角色名称,如 admin/user
     */
    private String name;

    /**
     * 角色描述
     */
    private String description;

    /**
     * 角色 -- 权限关系：多对多
     */
    @ManyToMany
    @JsonIgnoreProperties(value = {"roles"})
    @JoinTable(name = "SysRolePermission",
            joinColumns = {@JoinColumn(name = "roleId")},
            inverseJoinColumns = {@JoinColumn(name = "permissionId")})
    private List<SysPermission> permissions;

    /**
     * 用户 -- 角色关系：多对多
     */
    @ManyToMany(fetch = FetchType.EAGER)
    @JsonIgnoreProperties(value = {"roles"})
    @JoinTable(name = "SysUserRole",
            joinColumns = {@JoinColumn(name = "roleId")},
            inverseJoinColumns = {@JoinColumn(name = "uid")})
    private List<UserInfo> userInfos;
}
