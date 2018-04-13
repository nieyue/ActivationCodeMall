package com.nieyue.shiro;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;

import com.nieyue.bean.RolePermission;
import com.nieyue.business.AccountBusiness;

/**
 * @author: 聂跃
 * @description: 自定义Realm
 * @date: 2017/10/24 10:06
 */
public class AccountRealm extends AuthorizingRealm {
    //private Logger logger = LoggerFactory.getLogger(AccountRealm.class);
    @Resource
   AccountBusiness accountBusiness;

    /**
     * 登陆之后调用,注册当前用户角色、权限
     * @param principals
     * @return
     */
    @SuppressWarnings("unchecked")
	@Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        Session session = SecurityUtils.getSubject().getSession();
        //查询用户的权限
       // Account account = (Account) session.getAttribute("account");
        //Object account = session.getAttribute("account");
        //logger.info(account.toString());
        //为当前用户设置角色和权限
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        //获取账户权限
        //将自身权限列表放入session
        List<RolePermission> rpl = (ArrayList<RolePermission>)session.getAttribute("rolePermissionList");
        List<String> rolePermissionList=new ArrayList<>();
        rpl.forEach((rp)->{
            rolePermissionList.add(rp.getPermission().getRoute());//放置自身权限路径
        });
      // authorizationInfo.addRoles(set);
        authorizationInfo.addStringPermissions(rolePermissionList);
        return authorizationInfo;
    }

    /**
     * 验证当前登录的Subject
     * LoginController.login()方法中执行Subject.login()时 执行此方法
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authcToken) throws AuthenticationException {
        // 获取用户密码
        String loginName = (String) authcToken.getPrincipal();
        String password = new String((char[]) authcToken.getCredentials());
        //交给AuthenticatingRealm使用CredentialsMatcher进行密码匹配，如果觉得人家的不好可以自定义实现
        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(
        		loginName,
        		password,
        		//ByteSource.Util.bytes("salt"), //salt=username+salt,采用明文访问时，不需要此句
        		getName()
        		);
        //业务通用登陆
         accountBusiness.loginCommon(loginName, password);
        //处理session
//        SessionsSecurityManager securityManager = (SessionsSecurityManager) SecurityUtils.getSecurityManager();
//        DefaultSessionManager sessionManager = (DefaultSessionManager) securityManager.getSessionManager();
//        Collection<Session> sessions = sessionManager.getSessionDAO().getActiveSessions();//获取当前已登录的用户session列表
//        for (Session session : sessions) {
//            //清除该用户以前登录时保存的session
//            //如果和当前session是同一个session，则不剔除
//            if (SecurityUtils.getSubject().getSession().getId().equals(session.getId())) {
//                continue;
//            }
//            Account a = (Account)(session.getAttribute("account"));
//            if (a != null) {
//                Integer aId = a.getAccountId();
//                if (account.getAccountId().equals(aId)) {
//                    sessionManager.getSessionDAO().delete(session);
//                }
//            }
//        }
        return authenticationInfo;
    }
}
