package com.zlsrj.wms.admin.controller;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zlsrj.wms.api.client.service.TenantBusinessRulesClientService;
import com.zlsrj.wms.api.client.service.TenantInfoClientService;
import com.zlsrj.wms.api.dto.TenantBusinessRulesAddParam;
import com.zlsrj.wms.api.dto.TenantBusinessRulesQueryParam;
import com.zlsrj.wms.api.dto.TenantBusinessRulesUpdateParam;
import com.zlsrj.wms.api.vo.TenantBusinessRulesVo;
import com.zlsrj.wms.api.vo.TenantInfoVo;
import com.zlsrj.wms.common.api.CommonResult;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

@Api(value = "业务规则", tags = { "业务规则操作接口" })
@Controller
@RequestMapping("/tenantBusinessRules")
@Slf4j
public class TenantBusinessRulesController {

	@Autowired
	private TenantBusinessRulesClientService tenantBusinessRulesClientService;
	@Autowired
	private TenantInfoClientService tenantInfoClientService;

	@ApiOperation(value = "根据参数查询业务规则列表")
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	@ResponseBody
	public CommonResult<List<TenantBusinessRulesVo>> list(TenantBusinessRulesQueryParam tenantBusinessRulesQueryParam) {
		List<TenantBusinessRulesVo> tenantBusinessRulesVoList = tenantBusinessRulesClientService.list(tenantBusinessRulesQueryParam);
		tenantBusinessRulesVoList.stream().forEach(v->wrappperVo(v));

		return CommonResult.success(tenantBusinessRulesVoList);
	}

	@ApiOperation(value = "新增业务规则")
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	@ResponseBody
	public CommonResult<Object> create(@RequestBody TenantBusinessRulesAddParam tenantBusinessRulesAddParam) {
		String id = tenantBusinessRulesClientService.save(tenantBusinessRulesAddParam);

		return CommonResult.success(id);
	}

	@ApiOperation(value = "根据ID查询业务规则")
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ResponseBody
	public CommonResult<TenantBusinessRulesVo> getById(@PathVariable("id") String id) {
		TenantBusinessRulesVo tenantBusinessRulesVo = tenantBusinessRulesClientService.getById(id);
		wrappperVo(tenantBusinessRulesVo);

		return CommonResult.success(tenantBusinessRulesVo);
	}

	@ApiOperation(value = "根据参数更新业务规则信息")
	@RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
	@ResponseBody
	public CommonResult<Object> updateById(@PathVariable("id") String id,@RequestBody TenantBusinessRulesUpdateParam tenantBusinessRulesUpdateParam) {
		boolean success = tenantBusinessRulesClientService.updateById(id, tenantBusinessRulesUpdateParam);

		return CommonResult.success(success);
	}
	
	@ApiOperation(value = "根据ID删除业务规则")
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
	@ResponseBody
	public CommonResult<Object> removeById(@PathVariable("id") String id) {
		CommonResult<Object> commonResult = tenantBusinessRulesClientService.removeById(id);

		return commonResult;
	}

	private void wrappperVo(TenantBusinessRulesVo tenantBusinessRulesVo) {
		if (StringUtils.isEmpty(tenantBusinessRulesVo.getTenantName())) {
			TenantInfoVo tenantInfoVo = tenantInfoClientService.getById(tenantBusinessRulesVo.getTenantId());
			if (tenantInfoVo != null) {
				tenantBusinessRulesVo.setTenantName(tenantInfoVo.getTenantName());
			}
		}
	}

}
