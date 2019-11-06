package com.zlsrj.wms.admin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zlsrj.wms.api.client.service.TenantInfoClientService;
import com.zlsrj.wms.api.dto.TenantInfoQueryParam;
import com.zlsrj.wms.api.entity.TenantInfo;
import com.zlsrj.wms.common.api.CommonPage;
import com.zlsrj.wms.common.api.CommonResult;

import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/tenantInfo")
@Slf4j
public class TenantInfoController {

	@Autowired
	private TenantInfoClientService tenantInfoClientService;

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	@ResponseBody
	public CommonResult<CommonPage<TenantInfo>> list(TenantInfoQueryParam tenantInfoQueryParam, int pageNum,
			int pageSize) {
		CommonPage<TenantInfo> tenantInfoCommonPage = tenantInfoClientService.page(tenantInfoQueryParam, pageNum,
				pageSize);

		return CommonResult.success(tenantInfoCommonPage);
	}

}
