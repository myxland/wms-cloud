package com.zlsrj.wms.admin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zlsrj.wms.api.client.service.TenantSmsClientService;
import com.zlsrj.wms.api.dto.TenantSmsQueryParam;
import com.zlsrj.wms.api.vo.TenantSmsVo;
import com.zlsrj.wms.common.api.CommonPage;
import com.zlsrj.wms.common.api.CommonResult;

import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/tenantSms")
@Slf4j
public class TenantSmsController {

	@Autowired
	private TenantSmsClientService tenantSmsClientService;

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	@ResponseBody
	public CommonResult<CommonPage<TenantSmsVo>> list(TenantSmsQueryParam tenantSmsQueryParam, int pageNum,
			int pageSize) {
		Page<TenantSmsVo> tenantSmsVoPage = tenantSmsClientService.page(tenantSmsQueryParam, 1, 10, "id", "desc");

		CommonPage<TenantSmsVo> tenantSmsCommonPage = CommonPage.restPage(tenantSmsVoPage);

		return CommonResult.success(tenantSmsCommonPage);
	}

}
