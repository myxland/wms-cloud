package com.zlsrj.wms.admin.controller;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zlsrj.wms.api.client.service.TenantSmsClientService;
import com.zlsrj.wms.api.dto.TenantSmsQueryParam;
import com.zlsrj.wms.api.entity.TenantSms;
import com.zlsrj.wms.api.vo.TenantSmsVo;
import com.zlsrj.wms.common.api.CommonPage;
import com.zlsrj.wms.common.api.CommonResult;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

@Api(value = "租户短信配置", tags = { "租户短信配置操作接口" })
@Controller
@RequestMapping("/tenantSms")
@Slf4j
public class TenantSmsController {

	@Autowired
	private TenantSmsClientService tenantSmsClientService;

	@ApiOperation(value = "根据参数查询租户短信配置列表")
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	@ResponseBody
	public CommonResult<CommonPage<TenantSmsVo>> list(TenantSmsQueryParam tenantSmsQueryParam, int pageNum,
			int pageSize) {
		Page<TenantSmsVo> tenantSmsVoPage = tenantSmsClientService.page(tenantSmsQueryParam, pageNum, pageSize, "id", "desc");

		CommonPage<TenantSmsVo> tenantSmsCommonPage = CommonPage.restPage(tenantSmsVoPage);

		return CommonResult.success(tenantSmsCommonPage);
	}

	@ApiOperation(value = "更新租户短信配置启用抄表账单通知")
	@RequestMapping(value = "/update/smsReadSendOn", method = RequestMethod.POST)
	@ResponseBody
	public CommonResult<Boolean> updateSmsReadSendOn(@RequestParam("ids") String ids,
			@RequestParam("smsReadSendOn") Integer smsReadSendOn) {
		Arrays.asList(ids.split(",")).forEach(n -> {
			Long id = Long.parseLong(n);
			TenantSms tenantSms = new TenantSms();
			tenantSms.setSmsReadSendOn(smsReadSendOn);
			tenantSmsClientService.updatePatchById(id, tenantSms);
		});

		return CommonResult.success(true);
	}

	@ApiOperation(value = "更新租户短信配置启用缴费成功通知")
	@RequestMapping(value = "/update/smsChargeSendOn", method = RequestMethod.POST)
	@ResponseBody
	public CommonResult<Boolean> updateSmsChargeSendOn(@RequestParam("ids") String ids,
			@RequestParam("smsChargeSendOn") Integer smsChargeSendOn) {
		Arrays.asList(ids.split(",")).forEach(n -> {
			Long id = Long.parseLong(n);
			TenantSms tenantSms = new TenantSms();
			tenantSms.setSmsChargeSendOn(smsChargeSendOn);
			tenantSmsClientService.updatePatchById(id, tenantSms);
		});

		return CommonResult.success(true);
	}

	@ApiOperation(value = "更新租户短信配置启用欠费通知")
	@RequestMapping(value = "/update/smsQfSendOn", method = RequestMethod.POST)
	@ResponseBody
	public CommonResult<Boolean> updateSmsQfSendOn(@RequestParam("ids") String ids,
			@RequestParam("smsQfSendOn") Integer smsQfSendOn) {
		Arrays.asList(ids.split(",")).forEach(n -> {
			Long id = Long.parseLong(n);
			TenantSms tenantSms = new TenantSms();
			tenantSms.setSmsQfSendOn(smsQfSendOn);
			tenantSmsClientService.updatePatchById(id, tenantSms);
		});

		return CommonResult.success(true);
	}

	@ApiOperation(value = "新增租户短信配置")
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	@ResponseBody
	public CommonResult<TenantSmsVo> create(@RequestBody TenantSms tenantSms) {
		TenantSmsVo tenantSmsVo = tenantSmsClientService.save(tenantSms);

		return CommonResult.success(tenantSmsVo);
	}

	@ApiOperation(value = "根据ID查询租户短信配置")
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ResponseBody
	public CommonResult<TenantSmsVo> getById(@PathVariable("id") Long id) {
		TenantSmsVo tenantSmsVo = tenantSmsClientService.getById(id);

		return CommonResult.success(tenantSmsVo);
	}

	@ApiOperation(value = "根据参数更新租户短信配置信息")
	@RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
	@ResponseBody
	public CommonResult<TenantSmsVo> getById(@RequestBody TenantSms tenantSms) {
		Long id = tenantSms.getId();
		TenantSmsVo tenantSmsVo = tenantSmsClientService.updatePatchById(id, tenantSms);

		return CommonResult.success(tenantSmsVo);
	}
	
	@ApiOperation(value = "根据ID删除租户短信配置")
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
	@ResponseBody
	public CommonResult<Object> removeById(@PathVariable("id") Long id) {
		CommonResult<Object> commonResult = tenantSmsClientService.removeById(id);

		return commonResult;
	}

}
