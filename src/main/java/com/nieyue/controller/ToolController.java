package com.nieyue.controller;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

import com.nieyue.comments.RequestToMethdoItemUtils;
import com.nieyue.thirdparty.qiniu.QiniuUtil;
import com.nieyue.util.ResultUtil;
import com.nieyue.util.StateResultList;
import com.nieyue.util.barcode.QRCodeUtil;
import com.nieyue.verification.VerificationCode;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;



/**
 * 工具控制类
 * @author yy
 *
 */
@Api(tags={"tool"},value="工具",description="工具接口管理")
@RestController
public class ToolController {
	@Resource
	RequestToMethdoItemUtils requestToMethdoItemUtils;
	@Resource
	VerificationCode verificationCode;
	@Resource
	QiniuUtil qiniuUtil;
	@Value("${myPugin.projectName}")
	String projectName;
	/**
	 * 首页
	 * @return
	 */
	@ApiOperation(value = "首页", notes = "首页/seller/index.html")
	@RequestMapping(value={"/"}, method = {RequestMethod.GET,RequestMethod.POST})
	public RedirectView index(){
		return new RedirectView("/seller/index.html");
		
	}
	/**
	 * 验证码
	 * @param date
	 * @return
	 * @throws Exception 
	 */
	@ApiOperation(value = "验证码", notes = "验证码")
	@RequestMapping("/getVerificationCode")
	public void getVerificationCode(
			HttpSession session,HttpServletRequest request,HttpServletResponse response) throws Exception{
			ByteArrayOutputStream vc = verificationCode.execute(session);
			response.getOutputStream().write(vc.toByteArray());
		return ;
	}

	/**
	 * 获取Session
	 * @return
	 */
	@RequestMapping(value={"/tool/getSession"}, method = {RequestMethod.GET,RequestMethod.POST})
	public String getSession(
			HttpSession	 session
			){
		System.err.println(session.getAttribute("acount"));
		System.err.println(session.getAttribute("role"));
		System.err.println(session.getAttribute("finance"));
		return session.getId();
		
	}
	/**
	 * 获取七牛token
	 * @return
	 */
	@RequestMapping(value={"/tool/qiniuToken"}, method = {RequestMethod.GET,RequestMethod.POST})
	public StateResultList getQiniuToken(
			){
		List<Object> list = new ArrayList<Object>();
		list.add(qiniuUtil.getQiniuUploadToken());
		return ResultUtil.getSlefSRSuccessList(list);
		
	}
	

	/**
	 * 二维码
	 * @param date
	 * @return
	 * @throws Exception 
	 */
	@ApiOperation(value = "二维码", notes = "二维码")
	@RequestMapping(value="/getBarcode", method = {RequestMethod.GET,RequestMethod.POST})
	public void getBarcode(
			@RequestParam("acountId")Integer acountId,
			HttpSession session,HttpServletRequest request,HttpServletResponse response) throws Exception{
		//BoundValueOperations<String, String> bvo=stringRedisTemplate.boundValueOps(projectName+"ShareDomain");
		String text = "http://www.laoyeshuo.cn/share.html?acountId="+acountId;
		QRCodeUtil.encode(text, response.getOutputStream());
		return ;
	}
	/**
	 * 二维码Url
	 * @param date
	 * @return
	 * @throws Exception 
	 */
	@ApiOperation(value = "二维码Url", notes = "二维码Url")
	@RequestMapping(value="/getBarcodeUrl", method = {RequestMethod.GET,RequestMethod.POST})
	public StateResultList getBarcodeUrl(
			@RequestParam("acountId")Integer acountId,
			HttpSession session,HttpServletRequest request,HttpServletResponse response) throws Exception{
		List<String> list = new ArrayList<String>();
		//BoundValueOperations<String, String> bvo=stringRedisTemplate.boundValueOps(projectName+"ShareDomain");
		String text = "http://www.laoyeshuo.cn/share.html?acountId="+acountId;
		list.add(text);
		return ResultUtil.getSlefSRSuccessList(list);
	}
	
	
}
