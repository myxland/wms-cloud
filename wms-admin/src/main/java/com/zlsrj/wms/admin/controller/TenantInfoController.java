package com.zlsrj.wms.admin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zlsrj.wms.api.client.service.TenantInfoClientService;
import com.zlsrj.wms.api.dto.TenantInfoAddParam;
import com.zlsrj.wms.api.vo.TenantInfoVo;
import com.zlsrj.wms.common.api.CommonResult;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

@Api(value = "信息", tags = { "信息操作接口" })
@Controller
@RequestMapping("/tenantInfo")
@Slf4j
public class TenantInfoController {

	@Autowired
	private TenantInfoClientService tenantInfoClientService;
	
	@ApiOperation(value = "新增信息")
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	@ResponseBody
	public CommonResult<Object> create(@RequestBody TenantInfoAddParam tenantInfo) {
		String id = tenantInfoClientService.save(tenantInfo);
		return CommonResult.success(id);
	}
	

	private void wrappperVo(TenantInfoVo tenantInfoVo) {
	}

}
