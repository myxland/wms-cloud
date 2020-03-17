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

import com.zlsrj.wms.api.client.service.TenantMeterBrandClientService;
import com.zlsrj.wms.api.client.service.TenantInfoClientService;
import com.zlsrj.wms.api.dto.TenantMeterBrandAddParam;
import com.zlsrj.wms.api.dto.TenantMeterBrandQueryParam;
import com.zlsrj.wms.api.dto.TenantMeterBrandUpdateParam;
import com.zlsrj.wms.api.vo.TenantMeterBrandVo;
import com.zlsrj.wms.api.vo.TenantInfoVo;
import com.zlsrj.wms.common.api.CommonResult;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

@Api(value = "水表品牌", tags = { "水表品牌操作接口" })
@Controller
@RequestMapping("/tenantMeterBrand")
@Slf4j
public class TenantMeterBrandController {

	@Autowired
	private TenantMeterBrandClientService tenantMeterBrandClientService;
	@Autowired
	private TenantInfoClientService tenantInfoClientService;

	@ApiOperation(value = "根据参数查询水表品牌列表")
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	@ResponseBody
	public CommonResult<List<TenantMeterBrandVo>> list(TenantMeterBrandQueryParam tenantMeterBrandQueryParam) {
		List<TenantMeterBrandVo> tenantMeterBrandVoList = tenantMeterBrandClientService.list(tenantMeterBrandQueryParam);
		tenantMeterBrandVoList.stream().forEach(v->wrappperVo(v));

		return CommonResult.success(tenantMeterBrandVoList);
	}

	@ApiOperation(value = "新增水表品牌")
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	@ResponseBody
	public CommonResult<Object> create(@RequestBody TenantMeterBrandAddParam tenantMeterBrandAddParam) {
		String id = tenantMeterBrandClientService.save(tenantMeterBrandAddParam);

		return CommonResult.success(id);
	}

	@ApiOperation(value = "根据ID查询水表品牌")
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ResponseBody
	public CommonResult<TenantMeterBrandVo> getById(@PathVariable("id") String id) {
		TenantMeterBrandVo tenantMeterBrandVo = tenantMeterBrandClientService.getById(id);
		wrappperVo(tenantMeterBrandVo);

		return CommonResult.success(tenantMeterBrandVo);
	}

	@ApiOperation(value = "根据参数更新水表品牌信息")
	@RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
	@ResponseBody
	public CommonResult<Object> updateById(@PathVariable("id") String id,@RequestBody TenantMeterBrandUpdateParam tenantMeterBrandUpdateParam) {
		boolean success = tenantMeterBrandClientService.updateById(id, tenantMeterBrandUpdateParam);

		return CommonResult.success(success);
	}
	
	@ApiOperation(value = "根据ID删除水表品牌")
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
	@ResponseBody
	public CommonResult<Object> removeById(@PathVariable("id") String id) {
		CommonResult<Object> commonResult = tenantMeterBrandClientService.removeById(id);

		return commonResult;
	}

	private void wrappperVo(TenantMeterBrandVo tenantMeterBrandVo) {
		if (StringUtils.isEmpty(tenantMeterBrandVo.getTenantName())) {
			TenantInfoVo tenantInfoVo = tenantInfoClientService.getById(tenantMeterBrandVo.getTenantId());
			if (tenantInfoVo != null) {
				tenantMeterBrandVo.setTenantName(tenantInfoVo.getTenantName());
			}
		}
	}

}
