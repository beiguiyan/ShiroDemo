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
 * Date: 2023/8/915:42
 **/
@Entity
@Data
public class SysPermission {
    /**
     * 主键
     */
    @Id
    @GeneratedValue
    private long id;

    /**
     * 权限名称,如 user:select
     */
    private String name;

    /**
     * 权限描述，UI显示
     */
    private String description;

    /**
     * 权限地址
     */
    private String url;

    @ManyToMany(fetch = FetchType.EAGER)
    @JsonIgnoreProperties(value = {"permissions"})
    @JoinTable(name = "SysRolePermission",
            joinColumns = {@JoinColumn(name = "permissionId")},
            inverseJoinColumns = {@JoinColumn(name = "roleId")})
    private List<SysRole> roles;

}
