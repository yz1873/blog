package org.blog.security.realm;

import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.blog.entity.Author;
import org.blog.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Zhang Yu on 2017/3/16.
 */
public class PermissionsRealm extends AuthorizingRealm {
    @Autowired
    private BlogService blogService;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        // 根据用户配置用户与权限
        if (principalCollection == null) {
            throw new AuthorizationException("PrincipalCollection method argument cannot be null.");
        }
        String username=(String) principalCollection.fromRealm(getName()).iterator().next();
        //此处连库匹配了登录用户的数据，具体怎么做，需要根据个人需求而定
        System.out.println("author asd asd asd ");
        Author author = blogService.getByUsername(username);
        System.out.println(author);
        if(author != null){
            List<String> roles = new ArrayList<String>();
            SimpleAuthorizationInfo info=new SimpleAuthorizationInfo();
            if (blogService.getuserRoleName(author.getAuthorId()) != null) {
                roles.add(blogService.getuserRoleName(author.getAuthorId()));
            }
            info.addRoles(roles);
        }
        return null;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        Author author = blogService.getByUsername(token.getUsername());
        System.out.println(author);
        if (author == null) {
            throw new AuthorizationException();
        }
        SimpleAuthenticationInfo info = null;
        if (author.getUsername().equals(token.getUsername())) {
            info = new SimpleAuthenticationInfo(author.getUsername(), author.getPassword(), getName());
        }
        return info;
    }
}
