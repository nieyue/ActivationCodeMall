package com.nieyue.controller;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import java.util.regex.Pattern;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.BoundValueOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.exceptions.ClientException;
import com.nieyue.bean.Account;
import com.nieyue.business.AccountBusiness;
import com.nieyue.exception.AccountAlreadyAuthException;
import com.nieyue.exception.AccountAuthAuditException;
import com.nieyue.exception.AccountIsExistException;
import com.nieyue.exception.AccountIsNotExistException;
import com.nieyue.exception.AccountIsNotLoginException;
import com.nieyue.exception.AccountMessageException;
import com.nieyue.exception.AccountPhoneIsExistException;
import com.nieyue.exception.CommonNotRollbackException;
import com.nieyue.exception.CommonRollbackException;
import com.nieyue.exception.MySessionException;
import com.nieyue.exception.NotAnymoreException;
import com.nieyue.exception.RequestLimitException;
import com.nieyue.exception.VerifyCodeErrorException;
import com.nieyue.mail.SendMailDemo;
import com.nieyue.service.AccountLevelService;
import com.nieyue.service.AccountService;
import com.nieyue.service.FinanceService;
import com.nieyue.service.IntegralService;
import com.nieyue.service.NoticeService;
import com.nieyue.service.RoleService;
import com.nieyue.service.SincerityService;
import com.nieyue.thirdparty.yun.AliyunSms;
import com.nieyue.util.DateUtil;
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
	private AccountBusiness accountBusiness;
	@Resource
	private RoleService roleService;
	@Resource
	private FinanceService financeService;
	@Resource
	private AccountLevelService accountLevelService;
	@Resource
	private IntegralService integralService;
	@Resource
	private SincerityService sincerityService;
	@Resource
	private NoticeService noticeService;
	@Resource
	private  AliyunSms aliyunSms;
	@Resource
	private  StringRedisTemplate stringRedisTemplate;
	@Value("${myPugin.projectName}")
	String projectName;
	@Value("${myPugin.activationCodeMallProjectDomainUrl}")
	String activationCodeMallProjectDomainUrl;
	@Value("${myPugin.activationCodeMallClientDomainUrl}")
	String activationCodeMallClientDomainUrl;

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
	  @ApiImplicitParam(name="email",value="email，模糊查询",dataType="string", paramType = "query"),
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
	public @ResponseBody StateResultList<List<Account>> browsePagingAccount(
			@RequestParam(value="accountId",required=false)Integer  accountId,
			@RequestParam(value="auth",required=false)Integer auth,
			@RequestParam(value="phone",required=false)String phone,
			@RequestParam(value="email",required=false)String email,
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
			list= accountService.browsePagingAccount(accountId,auth,phone,email,realname,roleId,status,createDate,loginDate,pageNum, pageSize, orderName, orderWay);
			if(list.size()>0){
				return ResultUtil.getSlefSRSuccessList(list);
				
			}else{
				throw new NotAnymoreException();//没有更多
			}
	}

	/**
	 * 账户修改
	 * @return
	 */
	@ApiOperation(value = "账户修改", notes = "账户修改")
	@RequestMapping(value = "/update", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody StateResultList<List<Account>> updateAccount(
		@ModelAttribute Account account,
			HttpSession session)  {
		List<Account> list=new ArrayList<>();
		//账户已经存在
		if(accountService.loginAccount(account.getPhone(), null,account.getAccountId())!=null
				||accountService.loginAccount(account.getEmail(), null,account.getAccountId())!=null
				){
			throw new AccountIsExistException();//账户已经存在
		}
		boolean um = accountService.updateAccount(account);
		if(um){
		//session.setAttribute("account", account);
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
		  @ApiImplicitParam(name="validCode",value="手机验证码",dataType="string", paramType = "query")
		  })
	@RequestMapping(value = "/updatePassword", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody StateResultList<List<Account>> updateAccountPassword(
			@RequestParam("adminName")String adminName,
			@RequestParam("password")String password,
			@RequestParam(value="validCode",required=false) String validCode,
			HttpSession session) throws VerifyCodeErrorException  {
		List<Account> list=new ArrayList<>();
		//判断是否存在
		Account ac = accountService.loginAccount(adminName, null, null);
		if(ac==null || ac.getAccountId()==null){
			throw new AccountIsNotExistException();//账户不存在
		}
		//手机验证码
		/*String vc=(String) session.getAttribute("validCode");
		if(!vc.equals(validCode)){
			throw new VerifyCodeErrorException();//验证码错误
		}*/
		
		//邮箱验证码
		BoundValueOperations<String, String> validCodeEmailuuid = stringRedisTemplate.boundValueOps(projectName+"validCodeEmail"+adminName);
		if(!"1".equals(validCodeEmailuuid.get())){
			throw new CommonRollbackException("email没有验证");
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
		@ApiImplicitParam(name="nickname",value="昵称",dataType="string", paramType = "query"),
		@ApiImplicitParam(name="sex",value="性别,默认为0未知，为1男性，为2女性",dataType="int", paramType = "query"),
		@ApiImplicitParam(name="birthday",value="出生年月",dataType="date-time", paramType = "query"),
		@ApiImplicitParam(name="country",value="国家",dataType="string", paramType = "query"),
		@ApiImplicitParam(name="cardSecretReceive",value="卡密接受方式",dataType="int", paramType = "query"),
	})
	@RequestMapping(value = "/updateInfo", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody StateResultList<List<Account>> updateInfoAccount(
			@RequestParam(value="accountId",required=false)Integer accountId,
			@RequestParam(value="icon",required=false)String icon,
			@RequestParam(value="nickname",required=false)String nickname,
			@RequestParam(value="sex",required=false)Integer sex,
			@RequestParam(value="birthday",required=false)Date birthday,
			@RequestParam(value="country",required=false)String country,
			@RequestParam(value="cardSecretReceive",required=false)Integer cardSecretReceive,
			HttpSession session)  {
		List<Account> list=new ArrayList<>();
		Account newa = accountService.loadAccount(accountId);
		if(!((Account)session.getAttribute("account")).getAccountId().equals(accountId)){
			throw new MySessionException();//没有权限
		}
		if(icon!=null){
			newa.setIcon(icon);			
		}
		if(nickname!=null){
			newa.setNickname(nickname);			
		}
		if(sex!=null){
		newa.setSex(sex);
		}
		if(birthday!=null){
			newa.setBirthday(birthday);
		}
		if(country!=null){
		newa.setCountry(country);
		}
		if(cardSecretReceive!=null){
		newa.setCardSecretReceive(cardSecretReceive);
		}
		boolean um = accountService.updateAccount(newa);
		if(um){
			//session.setAttribute("account", newa);
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
	  @ApiImplicitParam(name="identityCardsHoldImg",value="手持身份证上半身照",dataType="string", paramType = "query",required=true),
	  @ApiImplicitParam(name="identityCardsFrontImg",value="身份证正面",dataType="string", paramType = "query",required=true),
	  @ApiImplicitParam(name="identityCardsBackImg",value="身份证反面",dataType="string", paramType = "query",required=true)
	 	  })
	@RequestMapping(value = "/auth", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody StateResultList<List<Account>> authAccount(
			@RequestParam("accountId") Integer accountId,
			@RequestParam("realname") String realname,
			@RequestParam("identityCards") String identityCards,
			@RequestParam("identityCardsHoldImg") String identityCardsHoldImg,
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
			account.setIdentityCardsHoldImg(identityCardsHoldImg);
			account.setIdentityCardsFrontImg(identityCardsFrontImg);
			account.setIdentityCardsBackImg(identityCardsBackImg);
			boolean b = accountService.updateAccount(account);
			if(b){
				list.add(account);
				return ResultUtil.getSlefSRSuccessList(list);
			}
			return ResultUtil.getSlefSRFailList(list);
			}else{
				if(account.getAuth().equals(1)){
					throw new AccountAuthAuditException();//审核中
					
				}else if(account.getAuth().equals(2)){
					throw new AccountAlreadyAuthException();//已经认证
				}
				return ResultUtil.getSlefSRFailList(list);
			}
	}
	/**
	 * 账户增加
	 * @return 
	 */
	
	@ApiOperation(value = "账户增加", notes = "账户增加")
	@RequestMapping(value = "/add", method = {RequestMethod.GET,RequestMethod.POST})
	public  @ResponseBody StateResultList<List<Account>> addAccount(@ModelAttribute Account account, HttpSession session) {
		List<Account> list=new ArrayList<>();
		//账户已经存在
		if(accountService.loginAccount(account.getPhone(), null,null)!=null
				||accountService.loginAccount(account.getEmail(), null,null)!=null
				){
			throw new AccountIsExistException();//账户已经存在
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
		  @ApiImplicitParam(name="email",value="email，模糊查询",dataType="string", paramType = "query"),
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
			@RequestParam(value="email",required=false)String email,
			@RequestParam(value="realname",required=false)String realname,
			@RequestParam(value="roleId",required=false)Integer roleId,
			@RequestParam(value="status",required=false)Integer status,
			@RequestParam(value="createDate",required=false)Date createDate,
			@RequestParam(value="loginDate",required=false)Date loginDate,
			HttpSession session)  {
		int count = accountService.countAll(accountId,auth,phone,email,realname,roleId,status,createDate,loginDate);
		return count;
	}
	/**
	 * 账户单个加载
	 * @return
	 */
	@ApiOperation(value = "账户单个加载", notes = "账户单个加载")
	@ApiImplicitParams({
	  @ApiImplicitParam(name="accountId",value="账户ID",dataType="int", paramType="query",required=true)
	 	  })
	@RequestMapping(value = "/load", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody StateResultList<List<Account>> loadAccount(@RequestParam("accountId") Integer accountId,HttpSession session)  {
		List<Account> list = new ArrayList<Account>();
		Account account = accountService.loadAccount(accountId);
		if(account!=null &&!account.equals("")){
				list.add(account);
				return ResultUtil.getSlefSRSuccessList(list);
			}else{
				throw new AccountIsNotExistException();//账户不存在
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
	public @ResponseBody StateResultList<List<Map<String,Object>>> loginAccount(
			@RequestParam(value="adminName") String adminName,
			@RequestParam(value="password") String password,
			@RequestParam(value="random",required=false) String random,
			HttpSession session) throws MySessionException  {
		List<Map<String, Object>> list = accountBusiness.login(adminName, password,random, session);
		 return ResultUtil.getSlefSRSuccessList(list);
	}
	
	/**
	 * 手机验证码发送/邮箱验证链接
	 * 
	 * @param adminName
	 * @param 模板码 1用户注册，2修改密码，3修改提现密码，4修改手机号，5身份验证
	 * @return
	 * @throws RequestLimitException 
	 * @throws AccountIsExistException 
	 * @throws CommonNotRollbackException 
	 * @throws ParseException 
	 */
	@ApiOperation(value = "手机验证码发送/邮箱验证链接", notes = "手机验证码发送/邮箱验证链接")
	@ApiImplicitParams({
		  @ApiImplicitParam(name="adminName",value="手机号/邮箱号码",dataType="string", paramType = "query",required=true),
		  @ApiImplicitParam(name="templateCode",value="模板码 1用户注册，2修改密码，3修改提现密码，4修改手机号，5身份验证",dataType="int", paramType = "query",required=true)
		  })
	@RequestMapping(value = "/validCode", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody
	StateResultList<List<String>> validCode(
			HttpServletRequest request,
			HttpSession session,
			@RequestParam("adminName")  String adminName,
			@RequestParam(value="templateCode",required=false,defaultValue="1")  Integer templateCode//默认注册
			) throws AccountIsExistException, RequestLimitException, CommonNotRollbackException
			{
		List<String> l=new ArrayList<String>();
		//注册，账户已经存在
		if(templateCode==1 && accountService.loginAccount(adminName, null,null)!=null ){
			throw new  AccountIsExistException();//账户已经存在异常
		}
		//其他，账户不存在
		if(templateCode!=1 && accountService.loginAccount(adminName, null,null)==null ){
			throw new  AccountIsNotExistException();//账户不存在
		}
		if(!Pattern.matches(MyValidator.REGEX_PHONE,adminName)
				&&!Pattern.matches(MyValidator.REGEX_EMAIL,adminName)){
			throw new CommonNotRollbackException("手机或者邮箱错误");
		}
		//手机号发送验证码
		if(Pattern.matches(MyValidator.REGEX_PHONE,adminName)){
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
				SendSmsResponse res = aliyunSms.sendSms(userValidCode.toString(),adminName, templateCode);
					if(res.getCode().equals("OK")){
						return ResultUtil.getSlefSRSuccessList(l);
					}
				} catch (ClientException e) {
					throw new AccountMessageException();//短信发送异常
				}
		 //l.add(userValidCode.toString());			
		}else if(Pattern.matches(MyValidator.REGEX_EMAIL,adminName)){
			//模板码 1用户注册，2修改密码，3修改提现密码，4修改手机号，5身份验证
			String content="";
			if(templateCode==1){
				content="用户注册";
			}else if(templateCode==2){
				content="修改密码";
			}else if(templateCode==3){
				content="修改提现密码";
			}else if(templateCode==4){
				content="修改手机号";
			}else if(templateCode==5){
				content="身份验证";
			}
			String uuid=UUID.randomUUID().toString();
			String link=activationCodeMallProjectDomainUrl+"/account/validCodeEmail?email="+adminName+"&validCodeEmail="+uuid+"&referer="+request.getHeader("Referer");
			//限制请求速度的
			BoundValueOperations<String, String> validCodeEmailLimit = stringRedisTemplate.boundValueOps(projectName+"validCodeEmailLimit"+adminName);
			BoundValueOperations<String, String> validCodeEmailuuid = stringRedisTemplate.boundValueOps(projectName+"validCodeEmail"+adminName);
			//已经验证过
			if("1".equals(validCodeEmailuuid.get())){
				return ResultUtil.getSlefSRList(200, "已经验证", l);
			}else if(StringUtils.isEmpty(validCodeEmailLimit.get())){//30s
				//邮箱验证，发送链接到邮箱
				boolean b = SendMailDemo.sendLinkMail(adminName, link, "激活码商城", content);
				if(b){
				validCodeEmailLimit.set(DateUtil.getCurrentTime(), 30, TimeUnit.SECONDS);//30s
				validCodeEmailuuid.set(uuid, 1, TimeUnit.HOURS);//先放uuid,验证成功放入1,验证码时间是1小时
				return ResultUtil.getSlefSRSuccessList(l);
				}
			}else{
				throw new RequestLimitException("请求过快，相同邮箱30秒一次");////30s
			}
		}
			throw new CommonNotRollbackException("发送验证异常");
	}
	/**
	 * 邮箱验证请求地址
	 * @return
	 */
	@ApiOperation(value = "邮箱验证请求地址", notes = "邮箱验证请求地址")
	@ApiImplicitParams({
		@ApiImplicitParam(name="email",value="邮箱",dataType="string", paramType = "query",required=true),
		  @ApiImplicitParam(name="validCodeEmail",value="验证码",dataType="string", paramType = "query",required=true),
		  @ApiImplicitParam(name="referer",value="回跳地址",dataType="string", paramType = "query",required=true),
		  })
	@RequestMapping(value = "/validCodeEmail", method = {RequestMethod.GET,RequestMethod.POST})
	public  ModelAndView validCodeEmail(
			@RequestParam("email") String email,
			@RequestParam("validCodeEmail") String validCodeEmail,
			@RequestParam("referer") String referer,
			HttpSession session,
			HttpServletRequest request)  {
		BoundValueOperations<String, String> validCodeEmailuuid = stringRedisTemplate.boundValueOps(projectName+"validCodeEmail"+email);
		String uuid = validCodeEmailuuid.get();
		//验证成功
		if(validCodeEmail!=null&&uuid!=null&&uuid.equals(validCodeEmail)){
			validCodeEmailuuid.set("1", 1, TimeUnit.HOURS);//1是验证通过了
			return new ModelAndView(new RedirectView(referer));
		}
		StringBuffer url = request.getRequestURL();  
		String redirect_url = url.delete(url.length() - request.getRequestURI().length(), url.length()).toString(); 
		return new ModelAndView(new RedirectView(redirect_url));
	}
	/**
	 * web用户登录
	 * @return
	 */
	@ApiOperation(value = "web用户登录", notes = "web用户登录")
	@ApiImplicitParams({
		  @ApiImplicitParam(name="adminName",value="手机号/电子邮箱",dataType="string", paramType = "query",required=true),
		  @ApiImplicitParam(name="password",value="密码",dataType="string", paramType = "query",required=true),
		  @ApiImplicitParam(name="random",value="验证码",dataType="string", paramType = "query")
		  })
	@RequestMapping(value = "/weblogin", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody StateResultList<List<Map<String,Object>>> webLoginAccount(
			@RequestParam("adminName") String adminName,
			@RequestParam("password") String password,
			@RequestParam(value="random",required=false) String random,
			HttpSession session)  {
		List<Map<String, Object>> list = accountBusiness.webLogin(adminName, password,random, session);
		 return ResultUtil.getSlefSRSuccessList(list);
	}
	/**
	 * web用户注册
	 * @return
	 * @throws AccountIsExistException 
	 * @throws VerifyCodeErrorException 
	 * @throws CommonNotRollbackException 
	 */
	@ApiOperation(value = "web用户注册", notes = "web用户注册")
	@ApiImplicitParams({
		@ApiImplicitParam(name="roleType",value="角色类型，1商户，2推广户，3用户",dataType="string", paramType = "query",required=true),
		  @ApiImplicitParam(name="nickname",value="用户名称",dataType="string", paramType = "query",required=true),
		  @ApiImplicitParam(name="email",value="邮箱号",dataType="string", paramType = "query",required=true),
		  @ApiImplicitParam(name="phone",value="手机号",dataType="string", paramType = "query"),
		  @ApiImplicitParam(name="password",value="密码",dataType="string", paramType = "query",required=true),
		  @ApiImplicitParam(name="validCode",value="验证码",dataType="string", paramType = "query")
		  })
	@RequestMapping(value = "/webregister", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody StateResultList<List<Map<String,Object>>> webRegisterAccount(
			@RequestParam("roleType") Integer roleType,
			@RequestParam("nickname") String nickname,
			@RequestParam("email") String email,
			@RequestParam(value="phone",required=false) String phone,
			@RequestParam("password") String password,
			@RequestParam(value="validCode",required=false) String validCode,
			HttpServletRequest request,
			HttpSession session) throws AccountIsExistException, VerifyCodeErrorException, CommonNotRollbackException  {
		List<Map<String,Object>> list = accountBusiness.webRegister(roleType, nickname, email, phone, password, validCode, session);
		return ResultUtil.getSlefSRSuccessList(list);
				
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
	public @ResponseBody StateResultList<List<String>> accountIsExist(
			@RequestParam("phone") String phone,
			HttpSession session)  {
		List<String> list = new ArrayList<>();
		//判断是否存在
		Account ac = accountService.loginAccount(phone, null, null);
		if(ac!=null&&ac.getAccountId()!=null){
		   throw new AccountPhoneIsExistException();//手机号已存在
		 }
		return ResultUtil.getSlefSRSuccessList(list);
	}
	/**
	 * 是否登录
	 * @return
	 */
	@ApiOperation(value = "是否登录", notes = "是否登录")
	@RequestMapping(value = "/islogin", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody StateResultList<List<Account>> isLoginAccount(
			HttpSession session)  {
		Account account = (Account) session.getAttribute("account");
		List<Account> list = new ArrayList<Account>();
		if(account!=null && !account.equals("")){
			Account a = accountService.loadAccount(account.getAccountId());
			list.add(a);
			return ResultUtil.getSlefSRSuccessList(list);
		}
		throw new AccountIsNotLoginException();//没有登录
	}
	/**
	 * 登出
	 * @return
	 */
	@ApiOperation(value = "登出", notes = "登出")
	@RequestMapping(value = "/loginout", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody StateResultList<List<Account>> loginoutAccount(
			HttpSession session)  {
		session.invalidate();
		return ResultUtil.getSlefSRSuccessList(null);
	}
	
}
