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

import com.zlsrj.wms.api.client.service.TenantMeterModelClientService;
import com.zlsrj.wms.api.client.service.TenantInfoClientService;
import com.zlsrj.wms.api.dto.TenantMeterModelAddParam;
import com.zlsrj.wms.api.dto.TenantMeterModelQueryParam;
import com.zlsrj.wms.api.dto.TenantMeterModelUpdateParam;
import com.zlsrj.wms.api.vo.TenantMeterModelVo;
import com.zlsrj.wms.api.vo.TenantInfoVo;
import com.zlsrj.wms.common.api.CommonResult;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

@Api(value = "水表型号", tags = { "水表型号操作接口" })
@Controller
@RequestMapping("/tenantMeterModel")
@Slf4j
public class TenantMeterModelController {

	@Autowired
	private TenantMeterModelClientService tenantMeterModelClientService;
	@Autowired
	private TenantInfoClientService tenantInfoClientService;

	@ApiOperation(value = "根据参数查询水表型号列表")
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	@ResponseBody
	public CommonResult<List<TenantMeterModelVo>> list(TenantMeterModelQueryParam tenantMeterModelQueryParam) {
		List<TenantMeterModelVo> tenantMeterModelVoList = tenantMeterModelClientService.list(tenantMeterModelQueryParam);
		tenantMeterModelVoList.stream().forEach(v->wrappperVo(v));

		return CommonResult.success(tenantMeterModelVoList);
	}

	@ApiOperation(value = "新增水表型号")
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	@ResponseBody
	public CommonResult<Object> create(@RequestBody TenantMeterModelAddParam tenantMeterModelAddParam) {
		String id = tenantMeterModelClientService.save(tenantMeterModelAddParam);

		return CommonResult.success(id);
	}

	@ApiOperation(value = "根据ID查询水表型号")
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ResponseBody
	public CommonResult<TenantMeterModelVo> getById(@PathVariable("id") String id) {
		TenantMeterModelVo tenantMeterModelVo = tenantMeterModelClientService.getById(id);
		wrappperVo(tenantMeterModelVo);

		return CommonResult.success(tenantMeterModelVo);
	}

	@ApiOperation(value = "根据参数更新水表型号信息")
	@RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
	@ResponseBody
	public CommonResult<Object> updateById(@PathVariable("id") String id,@RequestBody TenantMeterModelUpdateParam tenantMeterModelUpdateParam) {
		boolean success = tenantMeterModelClientService.updateById(id, tenantMeterModelUpdateParam);

		return CommonResult.success(success);
	}
	
	@ApiOperation(value = "根据ID删除水表型号")
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
	@ResponseBody
	public CommonResult<Object> removeById(@PathVariable("id") String id) {
		CommonResult<Object> commonResult = tenantMeterModelClientService.removeById(id);

		return commonResult;
	}

	private void wrappperVo(TenantMeterModelVo tenantMeterModelVo) {
		if (StringUtils.isEmpty(tenantMeterModelVo.getTenantName())) {
			TenantInfoVo tenantInfoVo = tenantInfoClientService.getById(tenantMeterModelVo.getTenantId());
			if (tenantInfoVo != null) {
				tenantMeterModelVo.setTenantName(tenantInfoVo.getTenantName());
			}
		}
	}

}
