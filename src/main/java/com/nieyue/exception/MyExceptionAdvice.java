package com.nieyue.exception;

import java.lang.reflect.UndeclaredThrowableException;

import org.springframework.validation.BindException;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import com.nieyue.util.ResultUtil;
import com.nieyue.util.StateResult;

/**
 * 统一异常处理
 * @author 聂跃
 * @date 2017年4月12日
 */
@RestControllerAdvice
public class MyExceptionAdvice {
	
	@ExceptionHandler(value=Exception.class)
	@ResponseBody
	public StateResult jsonErrorHandler( Exception e) throws Exception {
	       return ResultUtil.getFail();
	    }
	@ExceptionHandler(value=AccountIsExistException.class)
	@ResponseBody
	public StateResult acountIsExistErrorHandler( Exception e) throws Exception {
		return ResultUtil.getSlefSR(40001, "账户已存在");
	}
	@ExceptionHandler(value=VerifyCodeErrorException.class)
	@ResponseBody
	public StateResult verifyCodeErrorErrorHandler( Exception e) throws Exception {
		return ResultUtil.getSlefSR(40002, "验证码错误");
	}
	@ExceptionHandler(value=BindException.class)
	@ResponseBody
	public StateResult paramsErrorHandler( Exception e) throws Exception {
		return ResultUtil.getSlefSR(50000, "参数绑定错误");
	}
	@ExceptionHandler(value=MissingServletRequestParameterException.class)
	@ResponseBody
	public StateResult requiredParamsErrorHandler( Exception e) throws Exception {
		return ResultUtil.getSlefSR(50001, "缺少参数");
	}
	@ExceptionHandler(value={MethodArgumentTypeMismatchException.class,NumberFormatException.class})
	@ResponseBody
	public StateResult paramsFormatErrorHandler( Exception e) throws Exception {
		return ResultUtil.getSlefSR(50002, "参数格式错误");
	}
	@ExceptionHandler(value={UndeclaredThrowableException.class,RequestLimitException.class})
	@ResponseBody
	public StateResult requestLimitErrorHandler( Exception e) throws Exception {
		return ResultUtil.getSlefSR(60000, "请求频次过快");
	}
	@ExceptionHandler(value={HttpMediaTypeNotAcceptableException.class,MySessionException.class})
	@ResponseBody
	public StateResult requestSessionErrorHandler( Exception e) throws Exception {
		return ResultUtil.getSlefSR(70000, "没有权限");
	}
	@ExceptionHandler(value={MyCertificateException.class})
	@ResponseBody
	public StateResult requestCertificateErrorHandler( Exception e) throws Exception {
		return ResultUtil.getSlefSR(80000, "没有认证");
	}
	@ExceptionHandler(value={PayException.class})
	@ResponseBody
	public StateResult payExceptionErrorHandler( Exception e) throws Exception {
		return ResultUtil.getSlefSR(90001, "支付异常");
	}
	
}
