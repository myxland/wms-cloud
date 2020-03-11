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
import com.zlsrj.wms.api.client.service.TenantManufactorClientService;
import com.zlsrj.wms.api.client.service.TenantInfoClientService;
import com.zlsrj.wms.api.dto.TenantManufactorQueryParam;
import com.zlsrj.wms.api.entity.TenantManufactor;
import com.zlsrj.wms.api.vo.TenantManufactorVo;
import com.zlsrj.wms.api.vo.TenantInfoVo;
import com.zlsrj.wms.common.api.CommonPage;
import com.zlsrj.wms.common.api.CommonResult;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

@Api(value = "水表厂商", tags = { "水表厂商操作接口" })
@Controller
@RequestMapping("/tenantManufactor")
@Slf4j
public class TenantManufactorController {

	@Autowired
	private TenantManufactorClientService tenantManufactorClientService;
	@Autowired
	private TenantInfoClientService tenantInfoClientService;

	@ApiOperation(value = "根据参数查询水表厂商列表")
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	@ResponseBody
	public CommonResult<CommonPage<TenantManufactorVo>> list(TenantManufactorQueryParam tenantManufactorQueryParam, int pageNum,
			int pageSize) {
		Page<TenantManufactorVo> tenantManufactorVoPage = tenantManufactorClientService.page(tenantManufactorQueryParam, pageNum, pageSize, "id", "desc");
		tenantManufactorVoPage.getRecords().stream().forEach(v->wrappperVo(v));

		CommonPage<TenantManufactorVo> tenantManufactorCommonPage = CommonPage.restPage(tenantManufactorVoPage);

		return CommonResult.success(tenantManufactorCommonPage);
	}

	@ApiOperation(value = "新增水表厂商")
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	@ResponseBody
	public CommonResult<TenantManufactorVo> create(@RequestBody TenantManufactor tenantManufactor) {
		TenantManufactorVo tenantManufactorVo = tenantManufactorClientService.save(tenantManufactor);

		return CommonResult.success(tenantManufactorVo);
	}

	@ApiOperation(value = "根据ID查询水表厂商")
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ResponseBody
	public CommonResult<TenantManufactorVo> getById(@PathVariable("id") String id) {
		TenantManufactorVo tenantManufactorVo = tenantManufactorClientService.getById(id);
		wrappperVo(tenantManufactorVo);

		return CommonResult.success(tenantManufactorVo);
	}

	@ApiOperation(value = "根据参数更新水表厂商信息")
	@RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
	@ResponseBody
	public CommonResult<TenantManufactorVo> getById(@RequestBody TenantManufactor tenantManufactor) {
		String id = tenantManufactor.getId();
		TenantManufactorVo tenantManufactorVo = tenantManufactorClientService.updatePatchById(id, tenantManufactor);
		wrappperVo(tenantManufactorVo);

		return CommonResult.success(tenantManufactorVo);
	}
	
	@ApiOperation(value = "根据ID删除水表厂商")
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
	@ResponseBody
	public CommonResult<Object> removeById(@PathVariable("id") String id) {
		CommonResult<Object> commonResult = tenantManufactorClientService.removeById(id);

		return commonResult;
	}

	private void wrappperVo(TenantManufactorVo tenantManufactorVo) {
		if (StringUtils.isEmpty(tenantManufactorVo.getTenantName())) {
			TenantInfoVo tenantInfoVo = tenantInfoClientService.getById(tenantManufactorVo.getTenantId());
			if (tenantInfoVo != null) {
				tenantManufactorVo.setTenantName(tenantInfoVo.getTenantName());
			}
		}
	}

}
