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

import com.zlsrj.wms.api.client.service.TenantCustomerTypeClientService;
import com.zlsrj.wms.api.client.service.TenantInfoClientService;
import com.zlsrj.wms.api.dto.TenantCustomerTypeAddParam;
import com.zlsrj.wms.api.dto.TenantCustomerTypeQueryParam;
import com.zlsrj.wms.api.dto.TenantCustomerTypeUpdateParam;
import com.zlsrj.wms.api.vo.TenantCustomerTypeVo;
import com.zlsrj.wms.api.vo.TenantInfoVo;
import com.zlsrj.wms.common.api.CommonResult;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

@Api(value = "用户类别", tags = { "用户类别操作接口" })
@Controller
@RequestMapping("/tenantCustomerType")
@Slf4j
public class TenantCustomerTypeController {

	@Autowired
	private TenantCustomerTypeClientService tenantCustomerTypeClientService;
	@Autowired
	private TenantInfoClientService tenantInfoClientService;

	@ApiOperation(value = "根据参数查询用户类别列表")
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	@ResponseBody
	public CommonResult<List<TenantCustomerTypeVo>> list(TenantCustomerTypeQueryParam tenantCustomerTypeQueryParam) {
		List<TenantCustomerTypeVo> tenantCustomerTypeVoList = tenantCustomerTypeClientService.list(tenantCustomerTypeQueryParam);
		tenantCustomerTypeVoList.stream().forEach(v->wrappperVo(v));

		return CommonResult.success(tenantCustomerTypeVoList);
	}

	@ApiOperation(value = "新增用户类别")
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	@ResponseBody
	public CommonResult<Object> create(@RequestBody TenantCustomerTypeAddParam tenantCustomerTypeAddParam) {
		String id = tenantCustomerTypeClientService.save(tenantCustomerTypeAddParam);

		return CommonResult.success(id);
	}

	@ApiOperation(value = "根据ID查询用户类别")
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ResponseBody
	public CommonResult<TenantCustomerTypeVo> getById(@PathVariable("id") String id) {
		TenantCustomerTypeVo tenantCustomerTypeVo = tenantCustomerTypeClientService.getById(id);
		wrappperVo(tenantCustomerTypeVo);

		return CommonResult.success(tenantCustomerTypeVo);
	}

	@ApiOperation(value = "根据参数更新用户类别信息")
	@RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
	@ResponseBody
	public CommonResult<Object> updateById(@PathVariable("id") String id,@RequestBody TenantCustomerTypeUpdateParam tenantCustomerTypeUpdateParam) {
		boolean success = tenantCustomerTypeClientService.updateById(id, tenantCustomerTypeUpdateParam);

		return CommonResult.success(success);
	}
	
	@ApiOperation(value = "根据ID删除用户类别")
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
	@ResponseBody
	public CommonResult<Object> removeById(@PathVariable("id") String id) {
		CommonResult<Object> commonResult = tenantCustomerTypeClientService.removeById(id);

		return commonResult;
	}

	private void wrappperVo(TenantCustomerTypeVo tenantCustomerTypeVo) {
		if (StringUtils.isEmpty(tenantCustomerTypeVo.getTenantName())) {
			TenantInfoVo tenantInfoVo = tenantInfoClientService.getDictionaryById(tenantCustomerTypeVo.getTenantId());
			if (tenantInfoVo != null) {
				tenantCustomerTypeVo.setTenantName(tenantInfoVo.getTenantName());
			}
		}
	}

}
