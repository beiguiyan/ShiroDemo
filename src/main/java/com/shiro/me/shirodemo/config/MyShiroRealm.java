package com.shiro.me.shirodemo.config;

import com.shiro.me.shirodemo.entity.SysPermission;
import com.shiro.me.shirodemo.entity.SysRole;
import com.shiro.me.shirodemo.entity.UserInfo;
import com.shiro.me.shirodemo.service.IUserInfoService;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;

import javax.annotation.Resource;

/**
 * 自定义的 Realm
 *
 * author：yunshiyu
 * Date: 2023/8/915:52
 **/
@Slf4j
public class MyShiroRealm extends AuthorizingRealm {
    @Resource
    private IUserInfoService userInfoService;

    /**
     * 授权
     *
     * @param principalCollection
     * @return SimpleAuthorizationInfo对象
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        // 能进入这里说明用户已经通过验证了
        UserInfo user = (UserInfo) principalCollection.getPrimaryPrincipal();
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();

        // 遍历角色
        for (SysRole role : user.getRoles()) {
            authorizationInfo.addRole(role.getName());
            // 遍历权限
            for (SysPermission permission : role.getPermissions()) {
                authorizationInfo.addStringPermission(permission.getName());
            }

        }

        return authorizationInfo;
    }

    /**
     * 登录
     *
     * @param authenticationToken
     * @return 是否登录成功
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken)
            throws AuthenticationException {
        // 获取用户输入的账户
        String username = (String) authenticationToken.getPrincipal();
        log.info("==={}===", username);
        // 通过username从数据库中查找 UserInfo 对象
        /*
         * 实际项目中，这里可以根据实际情况做缓存。
         * 如果不做，Shiro自己也是有时间间隔机制，2分钟内不会重复执行该方法
         */
        UserInfo userInfo = userInfoService.findByUsername(username);
        if (null == userInfo) {
            return null;
        }

        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(
                userInfo,
                userInfo.getPassword(),
                ByteSource.Util.bytes(userInfo.getSalt()), // salt=username+salt
                userInfo.getName()
        );

        return authenticationInfo;
    }

}
