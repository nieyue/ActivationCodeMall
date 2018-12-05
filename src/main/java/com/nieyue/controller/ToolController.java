package com.nieyue.controller;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
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
import org.springframework.web.multipart.MultipartFile;

import com.nieyue.comments.RequestToMethdoItemUtils;
import com.nieyue.poi.MyExcel;
import com.nieyue.thirdparty.qiniu.QiniuUtil;
import com.nieyue.util.DateUtil;
import com.nieyue.util.FileUploadUtil;
import com.nieyue.util.ResultUtil;
import com.nieyue.util.SnowflakeIdWorker;
import com.nieyue.util.StateResultList;
import com.nieyue.util.barcode.QRCodeUtil;
import com.nieyue.verification.VerificationCode;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import net.sf.json.JSONArray;



/**
 * 工具控制类
 * @author yy
 *
 */
@Api(tags={"tool"},value="工具",description="工具接口管理")
@RestController
@RequestMapping("/tool")
public class ToolController {
	@Resource
	RequestToMethdoItemUtils requestToMethdoItemUtils;
	@Resource
	VerificationCode verificationCode;
	@Resource
	QiniuUtil qiniuUtil;
	@Value("${myPugin.projectName}")
	String projectName;
	@Value("${myPugin.uploaderPath.rootPath}")
	String rootPath;
	@Value("${myPugin.uploaderPath.locationPath}")
	String locationPath;
	/**
	 * 验证码
	 * @param date
	 * @return
	 * @throws Exception 
	 */
	@ApiOperation(value = "验证码", notes = "验证码")
	@RequestMapping(value = "/getVerificationCode", method = {RequestMethod.GET,RequestMethod.POST})
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
	@RequestMapping(value = "/getSession", method = {RequestMethod.GET,RequestMethod.POST})
	public String getSession(
			HttpSession	 session
			){
		System.err.println(session.getAttribute("account"));
		System.err.println(session.getAttribute("role"));
		System.err.println(session.getAttribute("finance"));
		return JSONArray.fromObject(session.getAttribute("rolePermissionList")).toString();
		
	}
	/**
	 * 获取七牛token
	 * @return
	 */
	@RequestMapping(value = "/qiniuToken", method = {RequestMethod.GET,RequestMethod.POST})
	public StateResultList<List<String>> getQiniuToken(
			){
		List<String> list = new ArrayList<>();
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
	@RequestMapping(value = "/getBarcode", method = {RequestMethod.GET,RequestMethod.POST})
	public void getBarcode(
			@RequestParam("url")String url,
			HttpSession session,HttpServletRequest request,HttpServletResponse response) throws Exception{
		//BoundValueOperations<String, String> bvo=stringRedisTemplate.boundValueOps(projectName+"ShareDomain");
		QRCodeUtil.encode(url, response.getOutputStream());
		return ;
	}
	/**
	 * 二维码Url
	 * @param date
	 * @return
	 * @throws Exception 
	 */
	@ApiOperation(value = "二维码Url", notes = "二维码Url")
	@RequestMapping(value = "/getBarcodeUrl", method = {RequestMethod.GET,RequestMethod.POST})
	public StateResultList<List<String>> getBarcodeUrl(
			@RequestParam("acountId")Integer acountId,
			HttpSession session,HttpServletRequest request,HttpServletResponse response) throws Exception{
		List<String> list = new ArrayList<String>();
		//BoundValueOperations<String, String> bvo=stringRedisTemplate.boundValueOps(projectName+"ShareDomain");
		String text = "http://www.laoyeshuo.cn/share.html?acountId="+acountId;
		list.add(text);
		return ResultUtil.getSlefSRSuccessList(list);
	}
	/**
	 * 文件增加、修改
	 * @param editorUpload 上传参数
	 * @param width 固定图片宽度
	 * @param height 固定图片高度
	 * @return
	 * @throws IOException 
	 */
	@ApiOperation(value = "上传文件", notes = "上传文件")
	@RequestMapping(value = "/file/add", method = {RequestMethod.GET,RequestMethod.POST})
	public StateResultList<List<String>> addFile(
			@RequestParam("editorUpload") MultipartFile file,
			HttpServletRequest request,HttpSession session ) throws IOException  {
		String fileUrl = null;
		String filedir=DateUtil.getImgDir();
		try{
			fileUrl = FileUploadUtil.FormDataMerImgFileUpload(file, session,rootPath,locationPath,filedir);
		}catch (IOException e) {
			throw new IOException();
		}
		StringBuffer url=request.getRequestURL();
		String redirect_url = url.delete(url.length() - request.getRequestURI().length(), url.length()).toString(); 
		List<String> list=new ArrayList<>();
		list.add(redirect_url+fileUrl);
		return ResultUtil.getSlefSRSuccessList(list);
		
	}

	/**
	 * 导入Excel
	 * @return
	 * @throws IOException 
	 * @throws IllegalStateException 
	 */
	@RequestMapping(value = "/importExcel", method = {RequestMethod.GET,RequestMethod.POST})
	public StateResultList<List<?>> importExcel(
			@RequestParam("excel") MultipartFile multipartFile,
			HttpSession	 session
			) throws IllegalStateException, IOException{
		String name="";
		name=rootPath+locationPath+"/"+SnowflakeIdWorker.getId().toString()+multipartFile.getOriginalFilename();
		File file = new File(name);
		multipartFile.transferTo(file);
		List<?> list = MyExcel.importData(file);
		
		return ResultUtil.getSlefSRSuccessList(list);
	}
}
