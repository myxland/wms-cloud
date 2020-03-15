package com.zlsrj.wms.admin.advice;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zlsrj.wms.common.api.CommonResult;

@ControllerAdvice(basePackages = "com.zlsrj.wms.admin.controller")
public class ExceptionControllerAdvice {

	@ExceptionHandler(RuntimeException.class)
	@ResponseBody
	public CommonResult<Object> errorResult(RuntimeException runtimeException) {
		if (runtimeException != null && runtimeException.getMessage() != null
				&& runtimeException.getMessage().trim().length() > 0) {
			return CommonResult.failed(runtimeException.getMessage());
		}
		return CommonResult.failed();
	}
}
