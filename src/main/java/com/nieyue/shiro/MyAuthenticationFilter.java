package com.nieyue.shiro;

import java.io.PrintWriter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.AuthenticationFilter;

import com.nieyue.util.ResultUtil;

import net.sf.json.JSONObject;

/**
 * Created by 聂跃 on 2018/4/2.
 * 自定义登陆过滤器
 */
public class MyAuthenticationFilter extends AuthenticationFilter {

    @Override
    protected boolean onAccessDenied(ServletRequest servletRequest, ServletResponse servletResponse) throws Exception {
        //HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        Subject subject = getSubject(servletRequest, servletResponse);
        //System.out.println(subject.getPrincipal());
        if (subject.getPrincipal() == null) {
//            //ajax判断
//            if(!ObjectUtils.isEmpty(request.getHeader("x-requested-with")) && request.getHeader("x-requested-with").equals("XMLHttpRequest")){
//                PrintWriter pw=response.getWriter();
//                pw.print( "您尚未登录或登录时间过长,请重新登录!");
//            } else {
//                saveRequestAndRedirectToLogin(request, response);
//            }
            PrintWriter pw=response.getWriter();
            pw.print(JSONObject.fromObject(ResultUtil.getSlefSR(40004, "账户没登录")));
            if (null != pw) {
                pw.flush();
                pw.close();
            }
        }else{
            //        String unauthorizedUrl = getUnauthorizedUrl();
//             //ajax判断
//        if(!ObjectUtils.isEmpty(request.getHeader("x-requested-with")) && request.getHeader("x-requested-with").equals("XMLHttpRequest")){
//            PrintWriter pw=response.getWriter();
//            pw.print( "您尚未登录或登录时间过长,请重新登录!");
//        } else {
//            if(StringUtils.hasText(unauthorizedUrl)) {
//                WebUtils.issueRedirect(request, response, unauthorizedUrl);
//            }
//            else {
//                WebUtils.toHttp(response).sendError(401);
//            }
//        }
            PrintWriter pw=response.getWriter();
            pw.print( JSONObject.fromObject(ResultUtil.getSlefSR(70000, "没有权限")));
            if (null != pw) {
                pw.flush();
                pw.close();
            }
        }
        return false;
    }

}
