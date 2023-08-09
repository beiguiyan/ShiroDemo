package com.shiro.me.shirodemo.config;

import org.hibernate.dialect.MySQL5InnoDBDialect;

/**
 * author：yunshiyu
 * Date: 2023/8/915:50
 **/
public class MySQLConfig extends MySQL5InnoDBDialect {

    @Override
    public String getTableTypeString() {
        return "ENGINE=InnoDB DEFAULT CHARSET=utf8";
    }

}
