package com.zlsrj.wms.admin.controller;

import org.apache.commons.lang3.StringUtils;
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
import com.zlsrj.wms.api.client.service.TenantCustomerMeterChangeLogClientService;
import com.zlsrj.wms.api.client.service.TenantInfoClientService;
import com.zlsrj.wms.api.dto.TenantCustomerMeterChangeLogQueryParam;
import com.zlsrj.wms.api.entity.TenantCustomerMeterChangeLog;
import com.zlsrj.wms.api.vo.TenantCustomerMeterChangeLogVo;
import com.zlsrj.wms.api.vo.TenantInfoVo;
import com.zlsrj.wms.common.api.CommonPage;
import com.zlsrj.wms.common.api.CommonResult;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

@Api(value = "信息变更", tags = { "信息变更操作接口" })
@Controller
@RequestMapping("/tenantCustomerMeterChangeLog")
@Slf4j
public class TenantCustomerMeterChangeLogController {

	@Autowired
	private TenantCustomerMeterChangeLogClientService tenantCustomerMeterChangeLogClientService;
	@Autowired
	private TenantInfoClientService tenantInfoClientService;

	@ApiOperation(value = "根据参数查询信息变更列表")
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	@ResponseBody
	public CommonResult<CommonPage<TenantCustomerMeterChangeLogVo>> list(TenantCustomerMeterChangeLogQueryParam tenantCustomerMeterChangeLogQueryParam, int pageNum,
			int pageSize) {
		Page<TenantCustomerMeterChangeLogVo> tenantCustomerMeterChangeLogVoPage = tenantCustomerMeterChangeLogClientService.page(tenantCustomerMeterChangeLogQueryParam, pageNum, pageSize, "id", "desc");
		tenantCustomerMeterChangeLogVoPage.getRecords().stream().forEach(v->wrappperVo(v));

		CommonPage<TenantCustomerMeterChangeLogVo> tenantCustomerMeterChangeLogCommonPage = CommonPage.restPage(tenantCustomerMeterChangeLogVoPage);

		return CommonResult.success(tenantCustomerMeterChangeLogCommonPage);
	}

	@ApiOperation(value = "新增信息变更")
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	@ResponseBody
	public CommonResult<TenantCustomerMeterChangeLogVo> create(@RequestBody TenantCustomerMeterChangeLog tenantCustomerMeterChangeLog) {
		TenantCustomerMeterChangeLogVo tenantCustomerMeterChangeLogVo = tenantCustomerMeterChangeLogClientService.save(tenantCustomerMeterChangeLog);

		return CommonResult.success(tenantCustomerMeterChangeLogVo);
	}

	@ApiOperation(value = "根据ID查询信息变更")
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ResponseBody
	public CommonResult<TenantCustomerMeterChangeLogVo> getById(@PathVariable("id") String id) {
		TenantCustomerMeterChangeLogVo tenantCustomerMeterChangeLogVo = tenantCustomerMeterChangeLogClientService.getById(id);
		wrappperVo(tenantCustomerMeterChangeLogVo);

		return CommonResult.success(tenantCustomerMeterChangeLogVo);
	}

	@ApiOperation(value = "根据参数更新信息变更信息")
	@RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
	@ResponseBody
	public CommonResult<TenantCustomerMeterChangeLogVo> getById(@RequestBody TenantCustomerMeterChangeLog tenantCustomerMeterChangeLog) {
		String id = tenantCustomerMeterChangeLog.getId();
		TenantCustomerMeterChangeLogVo tenantCustomerMeterChangeLogVo = tenantCustomerMeterChangeLogClientService.updatePatchById(id, tenantCustomerMeterChangeLog);
		wrappperVo(tenantCustomerMeterChangeLogVo);

		return CommonResult.success(tenantCustomerMeterChangeLogVo);
	}
	
	@ApiOperation(value = "根据ID删除信息变更")
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
	@ResponseBody
	public CommonResult<Object> removeById(@PathVariable("id") String id) {
		CommonResult<Object> commonResult = tenantCustomerMeterChangeLogClientService.removeById(id);

		return commonResult;
	}

	private void wrappperVo(TenantCustomerMeterChangeLogVo tenantCustomerMeterChangeLogVo) {
		if (StringUtils.isEmpty(tenantCustomerMeterChangeLogVo.getTenantName())) {
			TenantInfoVo tenantInfoVo = tenantInfoClientService.getById(tenantCustomerMeterChangeLogVo.getTenantId());
			if (tenantInfoVo != null) {
				tenantCustomerMeterChangeLogVo.setTenantName(tenantInfoVo.getTenantName());
			}
		}
	}

}
