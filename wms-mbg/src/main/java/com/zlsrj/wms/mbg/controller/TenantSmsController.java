package com.zlsrj.wms.mbg.controller;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zlsrj.wms.api.dto.TenantSmsQueryParam;
import com.zlsrj.wms.api.entity.TenantSms;
import com.zlsrj.wms.common.api.CommonPage;
import com.zlsrj.wms.common.api.CommonResult;
import com.zlsrj.wms.mbg.service.ITenantSmsService;

@RestController
@RequestMapping("/tenantSms")
public class TenantSmsController {

	@Autowired
	private ITenantSmsService tenantSmsService;

	@RequestMapping(value = "/select/{id}", method = RequestMethod.GET)
	public Object getById(@PathVariable("id") Long id) {
		TenantSms tenantSms = tenantSmsService.getById(id);

		return CommonResult.success(tenantSms);
	}

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public Object list() {
		List<TenantSms> tenantSmsList = tenantSmsService.list();

		return tenantSmsList;
	}

	@RequestMapping(value = "/page", method = RequestMethod.GET)
	public Object page(TenantSmsQueryParam tenantSmsQueryParam,
			@RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
			@RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize) {

		IPage<TenantSms> page = new Page<TenantSms>(pageNum, pageSize);
		QueryWrapper<TenantSms> queryWrapperTenantSms = new QueryWrapper<TenantSms>();
		queryWrapperTenantSms.lambda()
				.eq(tenantSmsQueryParam.getId() != null, TenantSms::getId, tenantSmsQueryParam.getId())
				.eq(tenantSmsQueryParam.getTenantId() != null, TenantSms::getTenantId, tenantSmsQueryParam.getTenantId())
				.eq(tenantSmsQueryParam.getSmsSignature() != null, TenantSms::getSmsSignature, tenantSmsQueryParam.getSmsSignature())
				.eq(tenantSmsQueryParam.getSmsSpService() != null, TenantSms::getSmsSpService, tenantSmsQueryParam.getSmsSpService())
				.eq(tenantSmsQueryParam.getSmsReadSendOn() != null, TenantSms::getSmsReadSendOn, tenantSmsQueryParam.getSmsReadSendOn())
				.eq(tenantSmsQueryParam.getSmsChargeSendOn() != null, TenantSms::getSmsChargeSendOn, tenantSmsQueryParam.getSmsChargeSendOn())
				.eq(tenantSmsQueryParam.getSmsQfSendOn() != null, TenantSms::getSmsQfSendOn, tenantSmsQueryParam.getSmsQfSendOn())
				.eq(tenantSmsQueryParam.getSmsQfSendAfterDays() != null, TenantSms::getSmsQfSendAfterDays, tenantSmsQueryParam.getSmsQfSendAfterDays())
				;

		IPage<TenantSms> tenantSmsPage = tenantSmsService.page(page, queryWrapperTenantSms);

		return CommonPage.restPage(tenantSmsPage);
	}

	@RequestMapping(value = "/insert", method = RequestMethod.POST)
	public Object insert(@RequestBody TenantSms tenantSms) {
		boolean success = tenantSmsService.save(tenantSms);

		return success ? CommonResult.success(success) : CommonResult.failed();
	}

	@RequestMapping(value = "/update", method = RequestMethod.PUT)
	public Object update(@RequestBody TenantSms tenantSms) {
		boolean success = tenantSmsService.updateById(tenantSms);

		return success ? CommonResult.success(success) : CommonResult.failed();
	}

	@RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
	public Object delete(@PathVariable("id") Long id) {
		boolean success = tenantSmsService.removeById(id);
		
		return success ? CommonResult.success(success) : CommonResult.failed();
	}

	@RequestMapping(value = "/delete/ids/{ids}", method = RequestMethod.DELETE)
	public Object deleteByIds(@PathVariable("ids") String ids) {
		List<Long> idList = Arrays.asList(ids.split(",")).stream().map(id -> Long.parseLong(id))
				.collect(Collectors.toList());
		boolean success = tenantSmsService.removeByIds(idList);
		
		return success ? CommonResult.success(success) : CommonResult.failed();
	}
	
}
