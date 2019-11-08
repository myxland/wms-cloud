package com.zlsrj.wms.admin.controller;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.zlsrj.wms.api.client.service.TenantInfoClientService;
import com.zlsrj.wms.api.vo.TenantInfoVo;
import com.zlsrj.wms.common.api.CommonResult;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class HelloController {
//
//	@Autowired
//	private IdClientService idClientService;

	@Autowired
	private TenantInfoClientService tenantInfoClientService;

//	@Autowired
//	private TenantEmployeeClientService tenantEmployeeClientService;

	@RequestMapping(value = "/hello", method = RequestMethod.GET)
	public TenantInfoVo hello() {
//		Long id = idClientService.select();
//		CommonResult<TenantEmployee> tenantEmployeeCommonResult = tenantEmployeeClientService.getByEmpName("董天宇");
		TenantInfoVo tenantInfoVo = tenantInfoClientService.getById(1190481958942150656L);
		log.info(tenantInfoVo.getDisplayName());
		log.info(ToStringBuilder.reflectionToString(tenantInfoVo, ToStringStyle.MULTI_LINE_STYLE));
		// return ToStringBuilder.reflectionToString(tenantInfoVo,
		// ToStringStyle.MULTI_LINE_STYLE);
		return tenantInfoVo;
	}

	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public Long delete() {
		CommonResult<Object> commonResult = tenantInfoClientService.removeById(1190481958942150656L);
		return commonResult.getCode();
	}

}
