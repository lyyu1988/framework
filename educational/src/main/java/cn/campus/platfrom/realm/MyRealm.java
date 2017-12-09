package cn.campus.platfrom.realm;

import cn.campus.platfrom.Constants;
import cn.campus.platfrom.entity.SysUser;
import cn.campus.platfrom.service.SysUserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collection;
import java.util.List;

public class MyRealm extends AuthorizingRealm {

    @Autowired
    private SysUserService sysUserService;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        Collection collection = principals.fromRealm(getName());
        String username = (String)principals.getPrimaryPrincipal();

        PrincipalCollection previousPrincipals = SecurityUtils.getSubject().getPreviousPrincipals();

        SimpleAuthorizationInfo simpleAuthorizationInfo=new SimpleAuthorizationInfo();
        simpleAuthorizationInfo.addRole("admin");
        return simpleAuthorizationInfo;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        UsernamePasswordToken usernamePasswordToken=(UsernamePasswordToken)token;
        String username = usernamePasswordToken.getUsername();

        List<SysUser> list=sysUserService.getByUsername(username);
        if(list==null || list.size()==0){
            throw new UnknownAccountException(); //如果用户名错误
        }

        SysUser sysUser=list.get(0);
        if(sysUser.getIsLock()==Constants.IS_LOCK || sysUser.getErrorCount()>=Constants.LOGIN_ERROR_COUNT){
            throw new AuthenticationException();
        }

        //char[] password = usernamePasswordToken.getPassword();
        //String password="8d969eef6ecad3c29a3a629280e686cf0c3f5d5a86aff3ca12020c923adc6c92";
        ByteSource credentialsSalt = ByteSource.Util.bytes(sysUser.getSalt());
        return new SimpleAuthenticationInfo(username, sysUser.getPassword(), credentialsSalt, getName());
    }

    @Override
    public void clearCache(PrincipalCollection principals) {
        super.clearCache(principals);
    }
}
