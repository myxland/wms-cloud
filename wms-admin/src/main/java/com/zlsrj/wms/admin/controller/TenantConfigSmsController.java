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
import com.zlsrj.wms.api.client.service.TenantConfigSmsClientService;
import com.zlsrj.wms.api.client.service.TenantInfoClientService;
import com.zlsrj.wms.api.dto.TenantConfigSmsQueryParam;
import com.zlsrj.wms.api.entity.TenantConfigSms;
import com.zlsrj.wms.api.vo.TenantConfigSmsVo;
import com.zlsrj.wms.api.vo.TenantInfoVo;
import com.zlsrj.wms.common.api.CommonPage;
import com.zlsrj.wms.common.api.CommonResult;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

@Api(value = "短信参数设置", tags = { "短信参数设置操作接口" })
@Controller
@RequestMapping("/tenantConfigSms")
@Slf4j
public class TenantConfigSmsController {

	@Autowired
	private TenantConfigSmsClientService tenantConfigSmsClientService;
	@Autowired
	private TenantInfoClientService tenantInfoClientService;

	@ApiOperation(value = "根据参数查询短信参数设置列表")
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	@ResponseBody
	public CommonResult<CommonPage<TenantConfigSmsVo>> list(TenantConfigSmsQueryParam tenantConfigSmsQueryParam, int pageNum,
			int pageSize) {
		Page<TenantConfigSmsVo> tenantConfigSmsVoPage = tenantConfigSmsClientService.page(tenantConfigSmsQueryParam, pageNum, pageSize, "id", "desc");
		tenantConfigSmsVoPage.getRecords().stream().forEach(v->wrappperVo(v));

		CommonPage<TenantConfigSmsVo> tenantConfigSmsCommonPage = CommonPage.restPage(tenantConfigSmsVoPage);

		return CommonResult.success(tenantConfigSmsCommonPage);
	}

	@ApiOperation(value = "新增短信参数设置")
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	@ResponseBody
	public CommonResult<TenantConfigSmsVo> create(@RequestBody TenantConfigSms tenantConfigSms) {
		TenantConfigSmsVo tenantConfigSmsVo = tenantConfigSmsClientService.save(tenantConfigSms);

		return CommonResult.success(tenantConfigSmsVo);
	}

	@ApiOperation(value = "根据ID查询短信参数设置")
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ResponseBody
	public CommonResult<TenantConfigSmsVo> getById(@PathVariable("id") String id) {
		TenantConfigSmsVo tenantConfigSmsVo = tenantConfigSmsClientService.getById(id);
		wrappperVo(tenantConfigSmsVo);

		return CommonResult.success(tenantConfigSmsVo);
	}

	@ApiOperation(value = "根据参数更新短信参数设置信息")
	@RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
	@ResponseBody
	public CommonResult<TenantConfigSmsVo> getById(@RequestBody TenantConfigSms tenantConfigSms) {
		String id = tenantConfigSms.getId();
		TenantConfigSmsVo tenantConfigSmsVo = tenantConfigSmsClientService.updatePatchById(id, tenantConfigSms);
		wrappperVo(tenantConfigSmsVo);

		return CommonResult.success(tenantConfigSmsVo);
	}
	
	@ApiOperation(value = "根据ID删除短信参数设置")
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
	@ResponseBody
	public CommonResult<Object> removeById(@PathVariable("id") String id) {
		CommonResult<Object> commonResult = tenantConfigSmsClientService.removeById(id);

		return commonResult;
	}

	private void wrappperVo(TenantConfigSmsVo tenantConfigSmsVo) {
		if (StringUtils.isEmpty(tenantConfigSmsVo.getTenantName())) {
			TenantInfoVo tenantInfoVo = tenantInfoClientService.getById(tenantConfigSmsVo.getTenantId());
			if (tenantInfoVo != null) {
				tenantConfigSmsVo.setTenantName(tenantInfoVo.getTenantName());
			}
		}
	}

}
