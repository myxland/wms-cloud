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
import com.zlsrj.wms.api.client.service.TenantConfigWxClientService;
import com.zlsrj.wms.api.client.service.TenantInfoClientService;
import com.zlsrj.wms.api.dto.TenantConfigWxQueryParam;
import com.zlsrj.wms.api.entity.TenantConfigWx;
import com.zlsrj.wms.api.vo.TenantConfigWxVo;
import com.zlsrj.wms.api.vo.TenantInfoVo;
import com.zlsrj.wms.common.api.CommonPage;
import com.zlsrj.wms.common.api.CommonResult;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

@Api(value = "微信参数设置", tags = { "微信参数设置操作接口" })
@Controller
@RequestMapping("/tenantConfigWx")
@Slf4j
public class TenantConfigWxController {

	@Autowired
	private TenantConfigWxClientService tenantConfigWxClientService;
	@Autowired
	private TenantInfoClientService tenantInfoClientService;

	@ApiOperation(value = "根据参数查询微信参数设置列表")
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	@ResponseBody
	public CommonResult<CommonPage<TenantConfigWxVo>> list(TenantConfigWxQueryParam tenantConfigWxQueryParam, int pageNum,
			int pageSize) {
		Page<TenantConfigWxVo> tenantConfigWxVoPage = tenantConfigWxClientService.page(tenantConfigWxQueryParam, pageNum, pageSize, "id", "desc");
		tenantConfigWxVoPage.getRecords().stream().forEach(v->wrappperVo(v));

		TenantConfigWxVo tenantConfigWxVoAggregation = tenantConfigWxClientService.aggregation(tenantConfigWxQueryParam);

		CommonPage<TenantConfigWxVo> tenantConfigWxCommonPage = CommonPage.restPage(tenantConfigWxVoPage,tenantConfigWxVoAggregation);
		return CommonResult.success(tenantConfigWxCommonPage);
	}

	@ApiOperation(value = "新增微信参数设置")
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	@ResponseBody
	public CommonResult<TenantConfigWxVo> create(@RequestBody TenantConfigWx tenantConfigWx) {
		TenantConfigWxVo tenantConfigWxVo = tenantConfigWxClientService.save(tenantConfigWx);

		return CommonResult.success(tenantConfigWxVo);
	}

	@ApiOperation(value = "根据ID查询微信参数设置")
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ResponseBody
	public CommonResult<TenantConfigWxVo> getById(@PathVariable("id") String id) {
		TenantConfigWxVo tenantConfigWxVo = tenantConfigWxClientService.getById(id);
		wrappperVo(tenantConfigWxVo);

		return CommonResult.success(tenantConfigWxVo);
	}

	@ApiOperation(value = "根据参数更新微信参数设置信息")
	@RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
	@ResponseBody
	public CommonResult<TenantConfigWxVo> getById(@RequestBody TenantConfigWx tenantConfigWx) {
		String id = tenantConfigWx.getId();
		TenantConfigWxVo tenantConfigWxVo = tenantConfigWxClientService.updatePatchById(id, tenantConfigWx);
		wrappperVo(tenantConfigWxVo);

		return CommonResult.success(tenantConfigWxVo);
	}
	
	@ApiOperation(value = "根据ID删除微信参数设置")
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
	@ResponseBody
	public CommonResult<Object> removeById(@PathVariable("id") String id) {
		CommonResult<Object> commonResult = tenantConfigWxClientService.removeById(id);

		return commonResult;
	}

	private void wrappperVo(TenantConfigWxVo tenantConfigWxVo) {
		if (StringUtils.isEmpty(tenantConfigWxVo.getTenantName())) {
			TenantInfoVo tenantInfoVo = tenantInfoClientService.getById(tenantConfigWxVo.getTenantId());
			if (tenantInfoVo != null) {
				tenantConfigWxVo.setTenantName(tenantInfoVo.getTenantName());
			}
		}
	}

}
