package com.nieyue.controller;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.nieyue.bean.Account;
import com.nieyue.bean.AccountLevel;
import com.nieyue.bean.AccountParent;
import com.nieyue.bean.Finance;
import com.nieyue.bean.Integral;
import com.nieyue.bean.Role;
import com.nieyue.bean.Vip;
import com.nieyue.exception.AccountIsExistException;
import com.nieyue.exception.MySessionException;
import com.nieyue.exception.RequestLimitException;
import com.nieyue.exception.VerifyCodeErrorException;
import com.nieyue.service.AccountLevelService;
import com.nieyue.service.AccountParentService;
import com.nieyue.service.AccountService;
import com.nieyue.service.FinanceService;
import com.nieyue.service.IntegralService;
import com.nieyue.service.NoticeService;
import com.nieyue.service.RoleService;
import com.nieyue.service.VideoCacheService;
import com.nieyue.service.VideoPlayRecordService;
import com.nieyue.service.VideoSetCollectService;
import com.nieyue.service.VipService;
import com.nieyue.thirdparty.yun.YunSms;
import com.nieyue.util.MyDESutil;
import com.nieyue.util.MyValidator;
import com.nieyue.util.ResultUtil;
import com.nieyue.util.StateResult;
import com.nieyue.util.StateResultList;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;


/**
 * 账户控制类
 * @author yy
 *
 */
@Api(tags={"account"},value="账户",description="账户管理")
@RestController
@RequestMapping("/account")
public class AccountController {
	@Resource
	private AccountService accountService;
	@Resource
	private RoleService roleService;
	@Resource
	private FinanceService financeService;
	@Resource
	private AccountParentService accountParentService;
	@Resource
	private AccountLevelService accountLevelService;
	@Resource
	private VideoPlayRecordService videoPlayRecordService;
	@Resource
	private VideoCacheService videoCacheService;
	@Resource
	private VideoSetCollectService videoSetCollectService;
	@Resource
	private VipService vipService;
	@Resource
	private IntegralService integralService;
	@Resource
	private NoticeService noticeService;
	@Resource
	private YunSms yunSms;
	@Value("${myPugin.projectName}")
	String projectName;

	/**
	 * 账户分页浏览
	 * @param orderName 商品排序数据库字段
	 * @param orderWay 商品排序方法 asc升序 desc降序
	 * @return
	 */
	@ApiOperation(value = "账户列表", notes = "账户分页浏览")
	@ApiImplicitParams({
	  @ApiImplicitParam(name="accountId",value="账户ID",dataType="int", paramType = "query"),
	  @ApiImplicitParam(name="auth",value="认证，0没认证，1审核中，2已认证",dataType="int", paramType = "query"),
	  @ApiImplicitParam(name="phone",value="手机号，模糊查询",dataType="string", paramType = "query"),
	  @ApiImplicitParam(name="realname",value="真实姓名，模糊查询",dataType="string", paramType = "query"),
	  @ApiImplicitParam(name="roleId",value="角色ID",dataType="int", paramType = "query"),
	  @ApiImplicitParam(name="status",value="状态，0正常，1锁定，2，异常",dataType="int", paramType = "query"),
	  @ApiImplicitParam(name="createDate",value="创建时间",dataType="date-time", paramType = "query"),
	  @ApiImplicitParam(name="loginDate",value="最后登陆时间",dataType="date-time", paramType = "query"),
	  @ApiImplicitParam(name="pageNum",value="页头数位",dataType="int", paramType = "query",defaultValue="1"),
	  @ApiImplicitParam(name="pageSize",value="每页数目",dataType="int", paramType = "query",defaultValue="10"),
	  @ApiImplicitParam(name="orderName",value="排序字段",dataType="string", paramType = "query",defaultValue="account_id"),
	  @ApiImplicitParam(name="orderWay",value="排序方式",dataType="string", paramType = "query",defaultValue="desc")
	  })
	@RequestMapping(value = "/list", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody StateResultList browsePagingAccount(
			@RequestParam(value="accountId",required=false)Integer  accountId,
			@RequestParam(value="auth",required=false)Integer auth,
			@RequestParam(value="phone",required=false)String phone,
			@RequestParam(value="realname",required=false)String realname,
			@RequestParam(value="roleId",required=false)Integer roleId,
			@RequestParam(value="status",required=false)Integer status,
			@RequestParam(value="createDate",required=false)Date createDate,
			@RequestParam(value="loginDate",required=false)Date loginDate,
			@RequestParam(value="pageNum",defaultValue="1",required=false)int pageNum,
			@RequestParam(value="pageSize",defaultValue="10",required=false) int pageSize,
			@RequestParam(value="orderName",required=false,defaultValue="account_id") String orderName,
			@RequestParam(value="orderWay",required=false,defaultValue="desc") String orderWay,HttpSession session)  {
			List<Account> list = new ArrayList<Account>();
			list= accountService.browsePagingAccount(accountId,auth,phone,realname,roleId,status,createDate,loginDate,pageNum, pageSize, orderName, orderWay);
			if(list.size()>0){
				return ResultUtil.getSlefSRSuccessList(list);
				
			}else{
				return ResultUtil.getSlefSRFailList(list);
			}
	}

	/**
	 * 账户修改
	 * @return
	 */
	@ApiOperation(value = "账户修改", notes = "账户修改")
	@RequestMapping(value = "/update", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody StateResultList updateAccount(
		@ModelAttribute Account account,
			HttpSession session)  {
		List<Account> list=new ArrayList<>();
		//账户已经存在
		if(accountService.loginAccount(account.getPhone(), null,account.getAccountId())!=null
				//||accountService.loginAccount(account.getEmail(), null,Account.getAccountId())!=null
				){
			return ResultUtil.getSlefSRFailList(list);
		}
		if(account.getPassword()!=null){
			account.setPassword(MyDESutil.getMD5(account.getPassword()));
		}
		boolean um = accountService.updateAccount(account);
		System.err.println(111123);
		if(um){
		session.setAttribute("account", account);
		list.add(account);
		return ResultUtil.getSlefSRSuccessList(list);
		}
		return ResultUtil.getSlefSRFailList(list);
	}
	/**
	 * 账户修改密码
	 * @param adminName 手机号/电子邮箱
	 * @param password  新密码
	 * @param validCode 短信验证码
	 * @return
	 * @throws VerifyCodeErrorException 
	 */
	@ApiOperation(value = "账户修改密码", notes = "账户修改密码")
	@ApiImplicitParams({
		  @ApiImplicitParam(name="adminName",value="手机号/电子邮箱",dataType="string", paramType = "query",required=true),
		  @ApiImplicitParam(name="password",value="新密码",dataType="string", paramType = "query",required=true),
		  @ApiImplicitParam(name="validCode",value="短信验证码",dataType="string", paramType = "query",required=true)
		  })
	@RequestMapping(value = "/updatePassword", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody StateResultList updateAccountPassword(
			@RequestParam("adminName")String adminName,
			@RequestParam("password")String password,
			@RequestParam(value="validCode",required=false) String validCode,
			HttpSession session) throws VerifyCodeErrorException  {
		List<Object> list=new ArrayList<Object>();
		//判断是否存在
		Account ac = accountService.loginAccount(adminName, null, null);
		if(ac==null || ac.getAccountId()==null){
			list.add("账户不存在");
			return ResultUtil.getSlefSRFailList(list); 
		}
		//手机验证码
		String vc=(String) session.getAttribute("validCode");
		if(!vc.equals(validCode)){
			throw new VerifyCodeErrorException();//验证码错误
		}
		ac.setPassword(MyDESutil.getMD5(password));
		boolean um = accountService.updateAccount(ac);
		if(um){
		list.add(ac);
		return ResultUtil.getSlefSRSuccessList(list);
		}
		return ResultUtil.getSlefSRFailList(list);
		
	}
	/**
	 * 账户修改用户信息
	 * @return
	 */
	@ApiOperation(value = "账户修改用户信息", notes = "账户修改用户信息")
	@ApiImplicitParams({
		@ApiImplicitParam(name="accountId",value="账户ID",dataType="int", paramType = "query",required=true),
		@ApiImplicitParam(name="icon",value="头像",dataType="string", paramType = "query"),
		@ApiImplicitParam(name="contactPhone",value="联系手机号",dataType="string", paramType = "query"),
		@ApiImplicitParam(name="nickname",value="昵称",dataType="string", paramType = "query"),
		@ApiImplicitParam(name="sex",value="性别,默认为0未知，为1男性，为2女性",dataType="int", paramType = "query"),
		@ApiImplicitParam(name="age",value="年龄",dataType="int", paramType = "query"),
		@ApiImplicitParam(name="country",value="国家",dataType="string", paramType = "query"),
		@ApiImplicitParam(name="province",value="省",dataType="string", paramType = "query"),
		@ApiImplicitParam(name="city",value="城市",dataType="string", paramType = "query"),
		@ApiImplicitParam(name="sign",value="签名",dataType="string", paramType = "query"),
		@ApiImplicitParam(name="wechat",value="微信号",dataType="string", paramType = "query"),
		@ApiImplicitParam(name="alipay",value="支付宝账号",dataType="string", paramType = "query")
	})
	@RequestMapping(value = "/updateInfo", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody StateResultList updateInfoAccount(
			@RequestParam(value="accountId",required=false)Integer accountId,
			@RequestParam(value="icon",required=false)String icon,
			@RequestParam(value="contactPhone",required=false)String contactPhone,
			@RequestParam(value="nickname",required=false)String nickname,
			@RequestParam(value="sex",required=false)Integer sex,
			@RequestParam(value="age",required=false)Integer age,
			@RequestParam(value="country",required=false)String country,
			@RequestParam(value="province",required=false)String province,
			@RequestParam(value="city",required=false)String city,
			@RequestParam(value="sign",required=false)String sign,
			@RequestParam(value="wechat",required=false)String wechat,
			@RequestParam(value="alipay",required=false)String alipay,
			HttpSession session)  {
		List<Account> list=new ArrayList<>();
		Account newa = accountService.loadAccount(accountId);
		if(!((Account)session.getAttribute("account")).getAccountId().equals(accountId)){
			return ResultUtil.getSlefSRFailList(list);
		}
		if(icon!=null){
			newa.setIcon(icon);			
		}
		if(contactPhone!=null){
			newa.setContactPhone(contactPhone);			
		}
		if(nickname!=null){
			newa.setNickname(nickname);			
		}
		if(sex!=null){
		newa.setSex(sex);
		}
		if(age!=null){
		newa.setAge(age);
		}
		if(country!=null){
		newa.setCountry(country);
		}
		if(province!=null){
		newa.setProvince(province);
		}
		if(city!=null){
		newa.setCity(city);
		}
		if(sign!=null){
		newa.setSign(sign);
		}
		if(wechat!=null){
		newa.setWechat(wechat);
		}
		if(alipay!=null){
		newa.setAlipay(alipay);
		}
		boolean um = accountService.updateAccount(newa);
		if(um){
			session.setAttribute("account", newa);
			list.add(newa);
			return ResultUtil.getSlefSRSuccessList(list);
		}
		return ResultUtil.getSlefSRFailList(list);
	}
	/**
	 * 账户实名认证
	 * @return
	 */
	@ApiOperation(value = "账户实名认证", notes = "账户实名认证")
	@ApiImplicitParams({
	  @ApiImplicitParam(name="accountId",value="账户ID",dataType="int", paramType = "query",required=true),
	  @ApiImplicitParam(name="realname",value="真实姓名",dataType="string", paramType = "query",required=true),
	  @ApiImplicitParam(name="identityCards",value="身份证",dataType="string", paramType = "query",required=true),
	  @ApiImplicitParam(name="identityCardsFrontImg",value="身份证正面",dataType="string", paramType = "query",required=true),
	  @ApiImplicitParam(name="identityCardsBackImg",value="身份证反面",dataType="string", paramType = "query",required=true)
	 	  })
	@RequestMapping(value = "/auth", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody StateResultList authAccount(
			@RequestParam("accountId") Integer accountId,
			@RequestParam("realname") String realname,
			@RequestParam("identityCards") String identityCards,
			@RequestParam("identityCardsFrontImg") String identityCardsFrontImg,
			@RequestParam("identityCardsBackImg") String identityCardsBackImg,
			HttpSession session)  {
		List<Account> list = new ArrayList<Account>();
		Account account = accountService.loadAccount(accountId);
		//必须是没认证的
		if(account!=null &&!account.equals("")&& account.getAuth().equals(0)){
			account.setAuth(1);//审核中
			account.setRealname(realname);
			account.setIdentityCards(identityCards);
			account.setIdentityCardsFrontImg(identityCardsFrontImg);
			account.setIdentityCardsBackImg(identityCardsBackImg);
			boolean b = accountService.updateAccount(account);
			if(b){
				List<AccountParent> apl = accountParentService.browsePagingAccountParent(null,null, accountId, null, null, null, null, 1, 1, "account_parent_id","asc");
				if(apl.size()==1){
				AccountParent accountParent=apl.get(0);
				accountParent.setRealname(realname);
				accountParent.setCreateDate(new Date());
				accountParent.setUpdateDate(new Date());
				accountParentService.updateAccountParent(accountParent);
				}
				list.add(account);
				return ResultUtil.getSlefSRSuccessList(list);
			}
			return ResultUtil.getSlefSRFailList(list);
			}else{
				return ResultUtil.getSlefSRFailList(list);
			}
	}
	/**
	 * 账户增加
	 * @return 
	 */
	
	@ApiOperation(value = "账户增加", notes = "账户增加")
	@RequestMapping(value = "/add", method = {RequestMethod.GET,RequestMethod.POST})
	public  @ResponseBody StateResultList addAccount(@ModelAttribute Account account, HttpSession session) {
		List<Account> list=new ArrayList<>();
		//账户已经存在
		if(accountService.loginAccount(account.getPhone(), null,null)!=null ){
			return ResultUtil.getSlefSRFailList(list);
		}
		account.setPassword(MyDESutil.getMD5(account.getPassword()));
		boolean am = accountService.addAccount(account);
		if(am){
			list.add(account);
			return ResultUtil.getSlefSRSuccessList(list);
		}
		return ResultUtil.getSlefSRFailList(list);
	}
	/**
	 * 账户删除
	 * @return
	 */
	@ApiOperation(value = "账户删除", notes = "账户删除")
	@ApiImplicitParams({
		  @ApiImplicitParam(name="accountId",value="账户ID",dataType="int", paramType = "query",required=true)
		 	  })
	@RequestMapping(value = "/delete", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody StateResult delAccount(
			@RequestParam("accountId") Integer accountId,
			HttpSession session)  {
		boolean dm = accountService.delAccount(accountId);
		return ResultUtil.getSR(dm);
	}
	/**
	 * 账户浏览数量
	 * @return
	 */
	@ApiOperation(value = "账户数量", notes = "根据参数获取账户数量")
	@ApiImplicitParams({
		  @ApiImplicitParam(name="accountId",value="账户ID",dataType="int", paramType = "query"),
		  @ApiImplicitParam(name="auth",value="认证，0没认证，1审核中，2已认证",dataType="int", paramType = "query"),
		  @ApiImplicitParam(name="phone",value="手机号，模糊查询",dataType="string", paramType = "query"),
		  @ApiImplicitParam(name="realname",value="真实姓名，模糊查询",dataType="string", paramType = "query"),
		  @ApiImplicitParam(name="roleId",value="角色ID",dataType="int", paramType = "query"),
		  @ApiImplicitParam(name="status",value="状态，0正常，1锁定，2，异常",dataType="int", paramType = "query"),
		  @ApiImplicitParam(name="createDate",value="创建时间",dataType="date-time", paramType = "query"),
		  @ApiImplicitParam(name="loginDate",value="最后登陆时间",dataType="date-time", paramType = "query")
		  })
	@RequestMapping(value = "/count", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody int countAll(
			@RequestParam(value="accountId",required=false)Integer  accountId,
			@RequestParam(value="auth",required=false)Integer auth,
			@RequestParam(value="phone",required=false)String phone,
			@RequestParam(value="realname",required=false)String realname,
			@RequestParam(value="roleId",required=false)Integer roleId,
			@RequestParam(value="status",required=false)Integer status,
			@RequestParam(value="createDate",required=false)Date createDate,
			@RequestParam(value="loginDate",required=false)Date loginDate,
			HttpSession session)  {
		int count = accountService.countAll(accountId,auth,phone,realname,roleId,status,createDate,loginDate);
		return count;
	}
	/**
	 * 账户单个加载
	 * @return
	 */
	@ApiOperation(value = "账户单个加载", notes = "账户单个加载")
	@ApiImplicitParams({
	  @ApiImplicitParam(name="accountId",value="账户ID",dataType="int", paramType="path",required=true)
	 	  })
	@RequestMapping(value = "/{accountId}", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody StateResultList loadAccount(@PathVariable("accountId") Integer accountId,HttpSession session)  {
		List<Account> list = new ArrayList<Account>();
		Account account = accountService.loadAccount(accountId);
		if(account!=null &&!account.equals("")){
				list.add(account);
				return ResultUtil.getSlefSRSuccessList(list);
			}else{
				return ResultUtil.getSlefSRFailList(list);
			}
	}
	/**
	 * 管理员登录
	 * @return
	 * @throws MySessionException 
	 */
	@ApiOperation(value = "管理员登录", notes = "管理员登录")
	@ApiImplicitParams({
		  @ApiImplicitParam(name="adminName",value="手机号/电子邮箱",dataType="string", paramType = "query",required=true),
		  @ApiImplicitParam(name="password",value="新密码",dataType="string", paramType = "query",required=true),
		  @ApiImplicitParam(name="random",value="验证码",dataType="string", paramType = "query",required=true)
		  })
	@RequestMapping(value = "/login", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody StateResultList loginAccount(
			@RequestParam(value="adminName") String adminName,
			@RequestParam(value="password") String password,
			@RequestParam(value="random",required=false) String random,
			HttpSession session) throws MySessionException  {
		//1代验证码
		String ran= (String) session.getAttribute("random");
		List<Account> list = new ArrayList<Account>();
		if(!ran.equals(random)){
			return ResultUtil.getSlefSRFailList(list);
		}
		Account account = accountService.loginAccount(adminName, MyDESutil.getMD5(password),null);
		if(account.getStatus().equals(1)){
			List<String> l1 = new ArrayList<String>();
			l1.add("账户锁定");
			return ResultUtil.getSlefSRFailList(l1);
		}else if(account!=null&&!account.equals("")){
			account.setLoginDate(new Date());
			boolean b = accountService.updateAccount(account);
			if(b){
			Integer roleId = account.getRoleId();
			Role r = roleService.loadRole(roleId);
			if(r.getName().equals("用户")){
			throw new MySessionException();//没权限	
			}
			session.setAttribute("account", account);
			session.setAttribute("role", r);
			List<Finance> f = financeService.browsePagingFinance(null,account.getAccountId(), 1, 1, "finance_id", "asc");
			session.setAttribute("finance", f.get(0));
			list.add(account);
			return ResultUtil.getSlefSRSuccessList(list);
			}
		}
		return ResultUtil.getSlefSRFailList(list);
	}
	
	/**
	 * 手机验证码发送
	 * 
	 * @param adminName
	 * @param templateCode 1注册，2修改密码，3修改交易密码
	 * @return
	 * @throws RequestLimitException 
	 * @throws AccountIsExistException 
	 * @throws ParseException 
	 */
	@ApiOperation(value = "手机验证码发送", notes = "手机验证码发送")
	@ApiImplicitParams({
		  @ApiImplicitParam(name="adminName",value="手机号/电子邮箱",dataType="string", paramType = "query",required=true),
		  @ApiImplicitParam(name="templateCode",value="模板 默认1注册，2修改密码,3修改交易密码",dataType="int", paramType = "query",required=true)
		  })
	@RequestMapping(value = "/validCode", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody
	StateResultList validCode(
			HttpSession session,
			@RequestParam("adminName")  String adminName,
			@RequestParam(value="templateCode",required=false,defaultValue="1")  Integer templateCode//默认注册
			) throws AccountIsExistException, RequestLimitException
					  {
		List<String> l=new ArrayList<String>();
		//注册，账户已经存在
		if(accountService.loginAccount(adminName, null,null)!=null && templateCode==1){
			throw new  AccountIsExistException();//账户已经存在异常
		}
		//修改密码
		if(accountService.loginAccount(adminName, null,null)==null && templateCode==2){
			return ResultUtil.getSlefSRFailList(l);
		}
		//修改交易密码
		if(accountService.loginAccount(adminName, null,null)==null && templateCode==3){
			return ResultUtil.getSlefSRFailList(l);
		}
		if(!Pattern.matches(MyValidator.REGEX_PHONE,adminName)){
					return ResultUtil.getSlefSRFailList(l);
		}
		Integer userValidCode=(int) (Math.random()*9000)+1000;
		if(session.getAttribute("validCodeDate")==null){//验证时间
			session.setAttribute("validCodeDate", new Date());
		}else{
		Date validCodeDate= (Date) session.getAttribute("validCodeDate");
		if(validCodeDate.after(new Date(new Date().getTime()-1000*30))){
			throw new RequestLimitException();//请求过快30s
		}else{
			session.setAttribute("validCodeDate", new Date());
		}
		}
		
		session.setAttribute("validCode", userValidCode.toString());
		 
			try {
				yunSms.sendMsg(adminName,templateCode,String.valueOf(userValidCode));
			} catch (IOException e) {
				return ResultUtil.getSlefSRFailList(l);
			}
		 //l.add(userValidCode.toString());			
		
		
		 return ResultUtil.getSlefSRSuccessList(l);
	}
	/**
	 * app用户登录
	 * @return
	 */
	@ApiOperation(value = "app用户登录", notes = "app用户登录")
	@ApiImplicitParams({
		  @ApiImplicitParam(name="adminName",value="手机号/电子邮箱",dataType="string", paramType = "query",required=true),
		  @ApiImplicitParam(name="password",value="密码",dataType="string", paramType = "query",required=true),
		  @ApiImplicitParam(name="random",value="验证码",dataType="string", paramType = "query")
		  })
	@RequestMapping(value = "/weblogin", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody StateResultList webLoginAccount(
			@RequestParam("adminName") String adminName,
			@RequestParam("password") String password,
			@RequestParam(value="random",required=false) String random,
			HttpSession session)  {
		//1代验证码
//		String ran= (String) session.getAttribute("random");
//		List<Account> list = new ArrayList<Account>();
//		if(!ran.equals(random)){
//			return ResultUtil.getSlefSRFailList(list);
//		}
		List<Object> list = new ArrayList<Object>();
		Account account = accountService.loginAccount(adminName, MyDESutil.getMD5(password),null);
		//自动登陆
		if(account==null|| account.equals("")){
			account=accountService.loginAccount(adminName, password, null);
		}
		if(account!=null&&!account.equals("")&&(account.getStatus().equals(0)||account.getStatus().equals(2))){
			account.setLoginDate(new Date());
			boolean b = accountService.updateAccount(account);
			if(b){
			session.setAttribute("account", account);
			Integer roleId = account.getRoleId();
			Role r = roleService.loadRole(roleId);
			session.setAttribute("role", r);
			List<Finance> f = financeService.browsePagingFinance(null,account.getAccountId(), 1, 1, "finance_id", "asc");
			session.setAttribute("finance", f.get(0));
			Map<Object,Object> map=new HashMap<>();
			//账户
			map.put("account", account);
			//财务
			map.put("finance",  f.get(0));
			//账户等级
			List<AccountLevel> all = accountLevelService.browsePagingAccountLevel(null, null, 1, Integer.MAX_VALUE, "account_level_id", "desc");
			if(all.size()>0){
				map.put("accountLevelList",  all);
				}else{
				map.put("accountLevelList",  new ArrayList<AccountLevel>());
				}
			//账户上级
			List<AccountParent> accountParentl = accountParentService.browsePagingAccountParent(null,null, account.getAccountId(), null, null, null, null, 1, 1, "account_parent_id", "asc");
			if(accountParentl.size()>0){
				map.put("accountParent",  accountParentl.get(0));
				}else{
				map.put("accountParent",  new Object());
				}
			//vip
			List<Vip> vipl = vipService.browsePagingVip(account.getAccountId(), null, null, 1, 1, "vip_id", "asc");
			map.put("vip",  vipl.get(0));
			//积分
			List<Integral> integrall = integralService.browsePagingIntegral(account.getAccountId(), null, null, 1, 1, "integral_id", "asc");
			map.put("integrall",  integrall.get(0));
			//视频播放记录数
			int videoPlayRecordCount = videoPlayRecordService.countAll(null,account.getAccountId());
			map.put("videoPlayRecordCount", videoPlayRecordCount);
			//视频缓存数
			int videoCacheCount = videoCacheService.countAll(null,account.getAccountId());
			map.put("videoCacheCount", videoCacheCount);
			//视频集收藏数
			int videoSetCollectCount = videoSetCollectService.countAll(null,account.getAccountId());
			map.put("videoSetCollectCount", videoSetCollectCount);
			//未读通知数
			int noticeCount = noticeService.countAll(null, 0, account.getAccountId());
			map.put("notice0", noticeCount);
			list.add(map);
			//return ResultUtil.getSlefSRSuccessList(list);
			return ResultUtil.getSlefSRSuccessList(list);
			}
		}else if(account.getStatus().equals(1)){
			List<String> l1 = new ArrayList<String>();
			l1.add("账户锁定");
			return ResultUtil.getSlefSRFailList(l1);
		}
		return ResultUtil.getSlefSRFailList(list);
	}
	/**
	 * app用户注册
	 * @return
	 * @throws AccountIsExistException 
	 * @throws VerifyCodeErrorException 
	 */
	@ApiOperation(value = "app用户注册", notes = "app用户注册")
	@ApiImplicitParams({
		  @ApiImplicitParam(name="adminName",value="手机号",dataType="string", paramType = "query",required=true),
		  @ApiImplicitParam(name="password",value="密码",dataType="string", paramType = "query",required=true),
		  @ApiImplicitParam(name="masterId",value="推荐码ID",dataType="int", paramType = "query"),
		  @ApiImplicitParam(name="validCode",value="验证码",dataType="string", paramType = "query",required=true)
		  })
	@RequestMapping(value = "/webregister", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody StateResultList webRegisterAccount(
			@RequestParam("adminName") String adminName,
			@RequestParam("password") String password,
			HttpServletRequest request,
			@RequestParam(value="masterId",required=false) Integer masterId,
			@RequestParam(value="validCode",required=false) String validCode,
			HttpSession session) throws AccountIsExistException, VerifyCodeErrorException  {
		//手机验证码
		String vc = (String) session.getAttribute("validCode");
		List<Map<Object,Object>> list = new ArrayList<>();
		if(!vc.equals(validCode)){
			throw new VerifyCodeErrorException();//验证码错误
		}
		//判断是否存在
		Account ac = accountService.loginAccount(adminName, null, null);
		if(ac!=null&&ac.getAccountId()!=null){
			throw new AccountIsExistException();
		}
			//新用户注册登录
				Account account=new Account();
				if(masterId==null){
					throw new MySessionException();
				}
				//获取masterId
				if(masterId!=null&&!masterId.equals("")){
					Account masterAcount = accountService.loadAccount(masterId);
					if(masterAcount==null){
						throw new MySessionException();
					}
					account.setMasterId(masterId);
				}
					account.setPhone(adminName);
					account.setContactPhone(adminName);
					//Account.setNickname(adminName);
					account.setPassword(MyDESutil.getMD5(password));
					account.setCreateDate(new Date());
					List<Role> roleList = roleService.browsePagingRole(1, Integer.MAX_VALUE, "role_id","asc");
					for (Role role : roleList) {
							if(role!=null && role.getName().equals("用户") ){
								account.setRoleId(role.getRoleId());
								account.setRoleName(role.getName());
							}
						}
					account.setLoginDate(new Date());
					boolean b = accountService.addAccount(account);
				if(b){
				
				session.setAttribute("account", account);
				Role role = roleService.loadRole(account.getRoleId());
				session.setAttribute("role", role);
				List<Finance> f = financeService.browsePagingFinance(null,account.getAccountId(), 1, 1, "finance_id", "asc");
				//System.out.println(f.get(0).toString());
				session.setAttribute("finance", f.get(0));
				Map<Object,Object> map=new HashMap<>();
				//账户
				map.put("account", account);
				//财务
				map.put("finance",  f.get(0));
				//账户等级
				List<AccountLevel> all = accountLevelService.browsePagingAccountLevel(null, null, 1, Integer.MAX_VALUE, "account_level_id", "desc");
				if(all.size()>0){
					map.put("accountLevelList",  all);
					}else{
					map.put("accountLevelList",  new ArrayList<AccountLevel>());
					}
				//账户上级
				List<AccountParent> accountParentl = accountParentService.browsePagingAccountParent(null,null, account.getAccountId(), null, null, null, null, 1, 1, "account_parent_id", "asc");
				if(accountParentl.size()>0){
				map.put("accountParent",  accountParentl.get(0));
				}else{
				map.put("accountParent",  new Object());
				}
				//vip
				List<Vip> vipl = vipService.browsePagingVip(account.getAccountId(), null, null, 1, 1, "vip_id", "asc");
				map.put("vip",  vipl.get(0));
				//积分
				List<Integral> integrall = integralService.browsePagingIntegral(account.getAccountId(), null, null, 1, 1, "integral_id", "asc");
				map.put("integrall",  integrall.get(0));
				//视频播放记录
				map.put("videoPlayRecordeCount", 0);
				//视频缓存
				map.put("videoCacheCount", 0);
				//视频集
				map.put("videoSetCount", 0);
				//未读
				map.put("notice0", 0);
				list.add(map);
				return ResultUtil.getSlefSRSuccessList(list);
				}else{
					return ResultUtil.getSlefSRFailList(list);
					
				}	
	}

	/**
	 * 手机号账户是否存在
	 * @return
	 */
	@ApiOperation(value = "手机号账户是否存在", notes = "手机号账户是否存在")
	@ApiImplicitParams({
		  @ApiImplicitParam(name="phone",value="手机号",dataType="string", paramType = "query",required=true)
		  })
	@RequestMapping(value = "/phoneIsExist", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody StateResultList accountIsExist(
			@RequestParam("phone") String phone,
			HttpSession session)  {
		List<String> list = new ArrayList<>();
		//判断是否存在
		Account ac = accountService.loginAccount(phone, null, null);
		if(ac!=null&&ac.getAccountId()!=null){
		   list.add("已经存在");
		   return ResultUtil.getSlefSRFailList(list);
		 }else{
		   list.add("不存在");			 
		 }
		return ResultUtil.getSlefSRSuccessList(list);
	}
	/**
	 * 登录
	 * @return
	 */
	@ApiOperation(value = "是否登录", notes = "是否登录")
	@RequestMapping(value = "/islogin", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody StateResultList isLoginAccount(
			HttpSession session)  {
		Account Account = (Account) session.getAttribute("account");
		List<Account> list = new ArrayList<Account>();
		if(Account!=null && !Account.equals("")){
			list.add(Account);
			return ResultUtil.getSlefSRSuccessList(list);
		}
		return ResultUtil.getSlefSRFailList(list);
	}
	/**
	 * 登出
	 * @return
	 */
	@ApiOperation(value = "登出", notes = "登出")
	@RequestMapping(value = "/loginout", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody StateResultList loginoutAccount(
			HttpSession session)  {
		session.invalidate();
		return ResultUtil.getSlefSRSuccessList(null);
	}
	
}
