package com.zlsrj.wms.admin.advice;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.InitBinder;

/**
 * 日期字符串参数自动转日期类型
 */
@ControllerAdvice
public class DateControllerAdvice {
	private final static String DATE_FORMAT = "yyyy-MM-dd";

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT);
		dateFormat.setLenient(false);// 是否严格解析时间 false则严格解析 true宽松解析
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
	}
}
