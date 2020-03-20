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

import com.zlsrj.wms.api.client.service.TenantMeterTypeClientService;
import com.zlsrj.wms.api.client.service.TenantInfoClientService;
import com.zlsrj.wms.api.dto.TenantMeterTypeAddParam;
import com.zlsrj.wms.api.dto.TenantMeterTypeQueryParam;
import com.zlsrj.wms.api.dto.TenantMeterTypeUpdateParam;
import com.zlsrj.wms.api.vo.TenantMeterTypeVo;
import com.zlsrj.wms.api.vo.TenantInfoVo;
import com.zlsrj.wms.common.api.CommonResult;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

@Api(value = "水表类型", tags = { "水表类型操作接口" })
@Controller
@RequestMapping("/tenantMeterType")
@Slf4j
public class TenantMeterTypeController {

	@Autowired
	private TenantMeterTypeClientService tenantMeterTypeClientService;
	@Autowired
	private TenantInfoClientService tenantInfoClientService;

	@ApiOperation(value = "根据参数查询水表类型列表")
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	@ResponseBody
	public CommonResult<List<TenantMeterTypeVo>> list(TenantMeterTypeQueryParam tenantMeterTypeQueryParam) {
		List<TenantMeterTypeVo> tenantMeterTypeVoList = tenantMeterTypeClientService.list(tenantMeterTypeQueryParam);
		tenantMeterTypeVoList.stream().forEach(v->wrappperVo(v));

		return CommonResult.success(tenantMeterTypeVoList);
	}

	@ApiOperation(value = "新增水表类型")
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	@ResponseBody
	public CommonResult<Object> create(@RequestBody TenantMeterTypeAddParam tenantMeterTypeAddParam) {
		String id = tenantMeterTypeClientService.save(tenantMeterTypeAddParam);

		return CommonResult.success(id);
	}

	@ApiOperation(value = "根据ID查询水表类型")
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ResponseBody
	public CommonResult<TenantMeterTypeVo> getById(@PathVariable("id") String id) {
		TenantMeterTypeVo tenantMeterTypeVo = tenantMeterTypeClientService.getById(id);
		wrappperVo(tenantMeterTypeVo);

		return CommonResult.success(tenantMeterTypeVo);
	}

	@ApiOperation(value = "根据参数更新水表类型信息")
	@RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
	@ResponseBody
	public CommonResult<Object> updateById(@PathVariable("id") String id,@RequestBody TenantMeterTypeUpdateParam tenantMeterTypeUpdateParam) {
		boolean success = tenantMeterTypeClientService.updateById(id, tenantMeterTypeUpdateParam);

		return CommonResult.success(success);
	}
	
	@ApiOperation(value = "根据ID删除水表类型")
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
	@ResponseBody
	public CommonResult<Object> removeById(@PathVariable("id") String id) {
		CommonResult<Object> commonResult = tenantMeterTypeClientService.removeById(id);

		return commonResult;
	}

	private void wrappperVo(TenantMeterTypeVo tenantMeterTypeVo) {
		if (StringUtils.isEmpty(tenantMeterTypeVo.getTenantName())) {
			TenantInfoVo tenantInfoVo = tenantInfoClientService.getDictionaryById(tenantMeterTypeVo.getTenantId());
			if (tenantInfoVo != null) {
				tenantMeterTypeVo.setTenantName(tenantInfoVo.getTenantName());
			}
		}
	}

}
