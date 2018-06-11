package com.nieyue.business;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.BoundValueOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.util.ObjectUtils;

import com.nieyue.bean.Account;
import com.nieyue.bean.AccountLevel;
import com.nieyue.bean.Finance;
import com.nieyue.bean.Integral;
import com.nieyue.bean.Role;
import com.nieyue.bean.RolePermission;
import com.nieyue.bean.Sincerity;
import com.nieyue.exception.AccountIsExistException;
import com.nieyue.exception.AccountLockException;
import com.nieyue.exception.AccountLoginException;
import com.nieyue.exception.AccountPhoneException;
import com.nieyue.exception.CommonRollbackException;
import com.nieyue.exception.MySessionException;
import com.nieyue.exception.VerifyCodeErrorException;
import com.nieyue.service.AccountLevelService;
import com.nieyue.service.AccountService;
import com.nieyue.service.FinanceService;
import com.nieyue.service.IntegralService;
import com.nieyue.service.RolePermissionService;
import com.nieyue.service.RoleService;
import com.nieyue.service.SincerityService;
import com.nieyue.util.MyDESutil;
/**
 * 账户业务逻辑
 * @author 聂跃
 * @date 2017年8月19日
 */
@Configuration
public class AccountBusiness {
	@Resource
	AccountService accountService;
    @Autowired
    private RoleService roleService;
    @Autowired
    private FinanceService financeService;
    @Autowired
    private AccountLevelService accountLevelService;
    @Autowired
    private IntegralService integralService;
    @Autowired
    private SincerityService sincerityService;
    @Autowired
    private RolePermissionService rolePermissionService;
    @Autowired
    private StringRedisTemplate stringRedisTemplate;
	@Value("${myPugin.projectName}")
	String projectName;
    /**
     * 存储账户会话信息
     */
    public List<Map<String,Object>> accountSession(
    		Account account
    		){
    	Session session=SecurityUtils.getSubject().getSession();
    	List<Map<String,Object>> list = new ArrayList<>();
	        //将用户信息放入session中
			session.setAttribute("account", account);
			//角色
	        Integer roleId = account.getRoleId();
			Role r = roleService.loadRole(roleId);
			session.setAttribute("role", r);
			//财务
			List<Finance> f = financeService.browsePagingFinance(null,account.getAccountId(), 1, 1, "finance_id", "asc");
			session.setAttribute("finance", f.get(0));
			//权限放入session
	        List<RolePermission> rolePermissionList = rolePermissionService.browsePagingRolePermission(null, account.getRoleId(), null, 1, Integer.MAX_VALUE, "role_permission_id", "asc");
	        session.setAttribute("rolePermissionList", rolePermissionList);
	        //map放入session
	        Map<String,Object> map=new HashMap<>();
	        //账户
			map.put("account", account);
			//自身角色
			map.put("role", r);
			//自身权限
			map.put("rolePermissionList", rolePermissionList);
			//财务
			map.put("finance",  f.get(0));
			//账户等级
			List<AccountLevel> all = accountLevelService.browsePagingAccountLevel(null,  1, Integer.MAX_VALUE, "account_level_id", "desc");
			if(all.size()>0){
				map.put("accountLevelList",  all);
				}else{
				map.put("accountLevelList",  new ArrayList<AccountLevel>());
			}
			//积分
			List<Integral> integrall = integralService.browsePagingIntegral(account.getAccountId(), null, null, 1, 1, "integral_id", "asc");
			map.put("integrall",  integrall.get(0));
			//诚信
			List<Sincerity> sinceritylist = sincerityService.browsePagingSincerity(account.getAccountId(), null, null, 1, 1, "sincerity_id", "asc");
			map.put("sincerity",  sinceritylist.get(0));
			session.setAttribute("loginMap", map);//可以放入session
			list.add(map);
			return list;
    	
    }
	/**
	 * 登录通用
	 */
	public List<Map<String,Object>> loginCommon(
			String adminName,
			String password
			){
		List<Map<String,Object>> list = new ArrayList<>();
		 Account account = accountService.loginAccount(adminName, password, null);
	    	//自动登陆
//			if(account==null|| account.equals("")){
//				account=accountService.loginAccount(adminName, password, null);
//			}
	        if (ObjectUtils.isEmpty(account)) {
	            throw new AccountLoginException();//账户或密码错误
	        }
	        if(account.getStatus().equals(1)){
				throw new AccountLockException();//账户锁定
			}
	        //更新登陆时间
	        account.setLoginDate(new Date());
			boolean b = accountService.updateAccount(account);
			if(b){
				list=accountSession(account);
			}
			return list;
	}
	/**
	 * web登录
	 * @return false 不包含，  true 包含
	 */
	@SuppressWarnings("unchecked")
	public List<Map<String,Object>> webLogin(
			String adminName,
			String password,
			String random,
			HttpSession session
			){
		//1代验证码
//		String ran= (String) session.getAttribute("random");
//		if(ran==null||!ran.equals(random)){
//			throw new VerifyCodeErrorException();
//		}
		List<Map<String,Object>> list = new ArrayList<>();
		 Subject currentUser = SecurityUtils.getSubject();
		 UsernamePasswordToken token = new UsernamePasswordToken(adminName, MyDESutil.getMD5(password));
		 try {
	            currentUser.login(token);
	        } catch (AuthenticationException e) {
	            throw new AccountLoginException();//
	        }
		 Map<String,Object> loginMap=(Map<String, Object>) session.getAttribute("loginMap");
		 list.add(loginMap);
		 session.removeAttribute("loginMap");//去掉
		return list;
	}
	/**
	 * 管理员登录
	 * @return false 不包含，  true 包含
	 */
	@SuppressWarnings("unchecked")
	public List<Map<String,Object>> login(
			String adminName,
			String password,
			String random,
			HttpSession session
			){
		//1代验证码
		String ran= (String) session.getAttribute("random");
		if(ran==null||!ran.equals(random)){
			throw new VerifyCodeErrorException();//验证码
		}
		List<Map<String,Object>> list = new ArrayList<>();
		Subject currentUser = SecurityUtils.getSubject();
		 UsernamePasswordToken token = new UsernamePasswordToken(adminName, MyDESutil.getMD5(password));
		 try {
	            currentUser.login(token);
	        } catch (AuthenticationException e) {
	            throw new AccountLoginException();//
	        }
		 Role r = (Role) session.getAttribute("role");
		 if(!ObjectUtils.isEmpty(r)){
			 if(r.getName().indexOf("管理员")<0){
				 session.invalidate();//不能登录
				 throw new MySessionException();
			 }
		 }
		 Map<String,Object> loginMap=(Map<String, Object>) session.getAttribute("loginMap");
		 list.add(loginMap);
		 session.removeAttribute("loginMap");//去掉
		 return list;
		}
	/**
	 * 账户注册
	 * roleType 角色类型，1商户，2推广户，3用户
	 * nickname 用户名称
	 * email 邮箱号
	 * phone 手机号
	 * password 密码
	 * validCode 验证码
	 */ 
	public List<Map<String,Object>> webRegister(
			Integer roleType,
			String nickname,
			String email,
			String phone,
			String password,
			String validCode,
			HttpSession session
			){
		List<Map<String,Object>> list = new ArrayList<>();
		if(roleType!=3 &&phone==null){//不是用户，必须有phone
			throw new AccountPhoneException();//手机号异常
		}
		//手机验证码
		if(phone!=null){
		String vc = (String) session.getAttribute("validCode");
		if(validCode==null||!validCode.equals(vc)){
			throw new VerifyCodeErrorException();//验证码错误
		}
		}
		//邮箱验证码
		BoundValueOperations<String, String> validCodeEmailuuid = stringRedisTemplate.boundValueOps(projectName+"validCodeEmail"+email);
		if(!"1".equals(validCodeEmailuuid.get())){
			throw new CommonRollbackException("email没有验证");
		}
		//判断是否存在
		Account ac = accountService.loginAccount(email, null, null);
		if(ac!=null&&ac.getAccountId()!=null){
			throw new AccountIsExistException();//账户已经存在
		}
		//判断是否存在
		Account acp = accountService.loginAccount(phone, null, null);
		if(acp!=null&&acp.getAccountId()!=null){
			throw new AccountIsExistException();//账户已经存在
		}
			//新用户注册登录
				Account account=new Account();
				if(phone!=null){
					account.setPhone(phone);					
				}
					account.setEmail(email);
					account.setNickname(nickname);
					account.setPassword(MyDESutil.getMD5(password));
					List<Role> roleList = roleService.browsePagingRole(1, Integer.MAX_VALUE, "role_id","asc");
					for (Role role : roleList) {
							if(role!=null  ){
								if( roleType.equals(1) && role.getName().equals("商户") ){
									account.setRoleId(role.getRoleId());
									account.setRoleName(role.getName());
								}else if( roleType.equals(2) && role.getName().equals("推广户") ){
									account.setRoleId(role.getRoleId());
									account.setRoleName(role.getName());
								}else if( roleType.equals(3) && role.getName().equals("用户") ){
									account.setRoleId(role.getRoleId());
									account.setRoleName(role.getName());
								}
								
							}
						}
					boolean b = accountService.addAccount(account);
				if(b){
//				//当前sessionId放入单例map
//				if(role.getName().equals("用户")){
//					HashMap<String,Object> smap=  SingletonHashMap.getInstance(); 
//					smap.put("accountId"+account.getAccountId(), session.getId());
//					
//				 }
					list=accountSession(account);
				}
					return list;
					
		}
	}
