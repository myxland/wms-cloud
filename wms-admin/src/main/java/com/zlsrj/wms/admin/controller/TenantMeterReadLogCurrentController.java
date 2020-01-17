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
import com.zlsrj.wms.api.client.service.TenantMeterReadLogCurrentClientService;
import com.zlsrj.wms.api.client.service.TenantInfoClientService;
import com.zlsrj.wms.api.dto.TenantMeterReadLogCurrentQueryParam;
import com.zlsrj.wms.api.entity.TenantMeterReadLogCurrent;
import com.zlsrj.wms.api.vo.TenantMeterReadLogCurrentVo;
import com.zlsrj.wms.api.vo.TenantInfoVo;
import com.zlsrj.wms.common.api.CommonPage;
import com.zlsrj.wms.common.api.CommonResult;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

@Api(value = "当期抄表计划", tags = { "当期抄表计划操作接口" })
@Controller
@RequestMapping("/tenantMeterReadLogCurrent")
@Slf4j
public class TenantMeterReadLogCurrentController {

	@Autowired
	private TenantMeterReadLogCurrentClientService tenantMeterReadLogCurrentClientService;
	@Autowired
	private TenantInfoClientService tenantInfoClientService;

	@ApiOperation(value = "根据参数查询当期抄表计划列表")
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	@ResponseBody
	public CommonResult<CommonPage<TenantMeterReadLogCurrentVo>> list(TenantMeterReadLogCurrentQueryParam tenantMeterReadLogCurrentQueryParam, int pageNum,
			int pageSize) {
		Page<TenantMeterReadLogCurrentVo> tenantMeterReadLogCurrentVoPage = tenantMeterReadLogCurrentClientService.page(tenantMeterReadLogCurrentQueryParam, pageNum, pageSize, "id", "desc");
		tenantMeterReadLogCurrentVoPage.getRecords().stream().forEach(v->wrappperVo(v));

		CommonPage<TenantMeterReadLogCurrentVo> tenantMeterReadLogCurrentCommonPage = CommonPage.restPage(tenantMeterReadLogCurrentVoPage);

		return CommonResult.success(tenantMeterReadLogCurrentCommonPage);
	}

	@ApiOperation(value = "新增当期抄表计划")
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	@ResponseBody
	public CommonResult<TenantMeterReadLogCurrentVo> create(@RequestBody TenantMeterReadLogCurrent tenantMeterReadLogCurrent) {
		TenantMeterReadLogCurrentVo tenantMeterReadLogCurrentVo = tenantMeterReadLogCurrentClientService.save(tenantMeterReadLogCurrent);

		return CommonResult.success(tenantMeterReadLogCurrentVo);
	}

	@ApiOperation(value = "根据ID查询当期抄表计划")
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ResponseBody
	public CommonResult<TenantMeterReadLogCurrentVo> getById(@PathVariable("id") Long id) {
		TenantMeterReadLogCurrentVo tenantMeterReadLogCurrentVo = tenantMeterReadLogCurrentClientService.getById(id);
		wrappperVo(tenantMeterReadLogCurrentVo);

		return CommonResult.success(tenantMeterReadLogCurrentVo);
	}

	@ApiOperation(value = "根据参数更新当期抄表计划信息")
	@RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
	@ResponseBody
	public CommonResult<TenantMeterReadLogCurrentVo> getById(@RequestBody TenantMeterReadLogCurrent tenantMeterReadLogCurrent) {
		Long id = tenantMeterReadLogCurrent.getId();
		TenantMeterReadLogCurrentVo tenantMeterReadLogCurrentVo = tenantMeterReadLogCurrentClientService.updatePatchById(id, tenantMeterReadLogCurrent);
		wrappperVo(tenantMeterReadLogCurrentVo);

		return CommonResult.success(tenantMeterReadLogCurrentVo);
	}
	
	@ApiOperation(value = "根据ID删除当期抄表计划")
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
	@ResponseBody
	public CommonResult<Object> removeById(@PathVariable("id") Long id) {
		CommonResult<Object> commonResult = tenantMeterReadLogCurrentClientService.removeById(id);

		return commonResult;
	}

	private void wrappperVo(TenantMeterReadLogCurrentVo tenantMeterReadLogCurrentVo) {
		if (StringUtils.isEmpty(tenantMeterReadLogCurrentVo.getTenantName())) {
			TenantInfoVo tenantInfoVo = tenantInfoClientService.getById(tenantMeterReadLogCurrentVo.getTenantId());
			if (tenantInfoVo != null) {
				tenantMeterReadLogCurrentVo.setTenantName(tenantInfoVo.getTenantName());
			}
		}
	}

}
