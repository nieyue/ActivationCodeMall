package com.nieyue.shiro;

import com.nieyue.bean.Account;
import com.nieyue.bean.RolePermission;
import com.nieyue.util.ResultUtil;
import net.sf.json.JSONObject;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authz.PermissionsAuthorizationFilter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

/**
 * @author: 聂跃
 * @description: 对权限过滤
 * @date: 2017/10/24 10:11
 * isAccessAllowed->onAccessDenied
 */
public class MyPermissionsAuthorizationFilter extends PermissionsAuthorizationFilter {

    //重写接受验证规则
    @Override
    public boolean isAccessAllowed(ServletRequest servletRequest, ServletResponse servletResponse, Object mappedValue) throws IOException {
//        boolean b = super.isAccessAllowed(servletRequest, servletResponse, mappedValue);
//        System.out.println(999999);
//        System.out.println(b);
//        return b;
        HttpServletRequest request = (HttpServletRequest) servletRequest;
      //  HttpServletResponse response = (HttpServletResponse) servletResponse;
        //获取当前账户
        Subject subject = this.getSubject(servletRequest, servletResponse);
        //获取当前访问路径
        String[] perms = (String[])((String[])mappedValue);
//        for (int i = 0; i < perms.length; i++) {
//            System.out.println(perms[i]);
//        }
        //System.out.println(222);
        //System.out.println(request.getParameter("accountId"));
        Account account = (Account)subject.getSession().getAttribute("account");
       //超级管理员拥有所有权限
        if(account.getRoleName().equals("超级管理员")){
        	return true;
        }
        @SuppressWarnings("unchecked")
		List<RolePermission> rolePermissionList = (List<RolePermission>) subject.getSession().getAttribute("rolePermissionList");
        //默认有权限
        boolean isPermitted = true;
        if(perms != null && perms.length > 0) {
            if(perms.length == 1) {
                //当前账户没有当前路径权限
                if(!subject.isPermitted(perms[0])) {
                    isPermitted = false;
                }
                //当前账户没有当前所有路径权限
            } else if(!subject.isPermittedAll(perms)) {
                isPermitted = false;
            }
            for (RolePermission rp : rolePermissionList) {
                for (int i = 0; i < perms.length; i++) {
                /*当前账户拥有的权限路径与来路路径一致，且是 自身 操作权限的情况下，
                 ** accountId必须与session中的account.getAccountId不一致，
                 * 表示当前账户只能操作自身id下的权限。
                */
                if(rp.getPermission().getRoute().equals(perms[i]) && rp.getRegion().equals(2) && !account.getAccountId().toString().equals( request.getParameter("accountId"))){
                   // System.out.println(account.getAccountId());
                    //System.err.println(request.getParameter("accountId"));
                    isPermitted = false;
                    }
                }
            }
        }

        return isPermitted;
    }

    /**
     * 权限级别的重写， 没有权限的返回
     * @param servletRequest
     * @param servletResponse
     * @return
     * @throws IOException
     */
    @Override
    protected boolean onAccessDenied(ServletRequest servletRequest, ServletResponse servletResponse) throws IOException {
        //boolean b=super.onAccessDenied(servletRequest, servletResponse);
       // HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        PrintWriter pw=response.getWriter();
        pw.print( JSONObject.fromObject(ResultUtil.getSlefSR(70000, "没有权限")));
        if (null != pw) {
            pw.flush();
            pw.close();
        }
        //true与上面的PrintWriter冲突
        return false;
    }
}
