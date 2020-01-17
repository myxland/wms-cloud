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
import com.zlsrj.wms.api.client.service.TenantWaterAreaClientService;
import com.zlsrj.wms.api.client.service.TenantInfoClientService;
import com.zlsrj.wms.api.dto.TenantWaterAreaQueryParam;
import com.zlsrj.wms.api.entity.TenantWaterArea;
import com.zlsrj.wms.api.vo.TenantWaterAreaVo;
import com.zlsrj.wms.api.vo.TenantInfoVo;
import com.zlsrj.wms.common.api.CommonPage;
import com.zlsrj.wms.common.api.CommonResult;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

@Api(value = "供水区域", tags = { "供水区域操作接口" })
@Controller
@RequestMapping("/tenantWaterArea")
@Slf4j
public class TenantWaterAreaController {

	@Autowired
	private TenantWaterAreaClientService tenantWaterAreaClientService;
	@Autowired
	private TenantInfoClientService tenantInfoClientService;

	@ApiOperation(value = "根据参数查询供水区域列表")
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	@ResponseBody
	public CommonResult<CommonPage<TenantWaterAreaVo>> list(TenantWaterAreaQueryParam tenantWaterAreaQueryParam, int pageNum,
			int pageSize) {
		Page<TenantWaterAreaVo> tenantWaterAreaVoPage = tenantWaterAreaClientService.page(tenantWaterAreaQueryParam, pageNum, pageSize, "id", "desc");
		tenantWaterAreaVoPage.getRecords().stream().forEach(v->wrappperVo(v));

		CommonPage<TenantWaterAreaVo> tenantWaterAreaCommonPage = CommonPage.restPage(tenantWaterAreaVoPage);

		return CommonResult.success(tenantWaterAreaCommonPage);
	}

	@ApiOperation(value = "新增供水区域")
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	@ResponseBody
	public CommonResult<TenantWaterAreaVo> create(@RequestBody TenantWaterArea tenantWaterArea) {
		TenantWaterAreaVo tenantWaterAreaVo = tenantWaterAreaClientService.save(tenantWaterArea);

		return CommonResult.success(tenantWaterAreaVo);
	}

	@ApiOperation(value = "根据ID查询供水区域")
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ResponseBody
	public CommonResult<TenantWaterAreaVo> getById(@PathVariable("id") Long id) {
		TenantWaterAreaVo tenantWaterAreaVo = tenantWaterAreaClientService.getById(id);
		wrappperVo(tenantWaterAreaVo);

		return CommonResult.success(tenantWaterAreaVo);
	}

	@ApiOperation(value = "根据参数更新供水区域信息")
	@RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
	@ResponseBody
	public CommonResult<TenantWaterAreaVo> getById(@RequestBody TenantWaterArea tenantWaterArea) {
		Long id = tenantWaterArea.getId();
		TenantWaterAreaVo tenantWaterAreaVo = tenantWaterAreaClientService.updatePatchById(id, tenantWaterArea);
		wrappperVo(tenantWaterAreaVo);

		return CommonResult.success(tenantWaterAreaVo);
	}
	
	@ApiOperation(value = "根据ID删除供水区域")
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
	@ResponseBody
	public CommonResult<Object> removeById(@PathVariable("id") Long id) {
		CommonResult<Object> commonResult = tenantWaterAreaClientService.removeById(id);

		return commonResult;
	}

	private void wrappperVo(TenantWaterAreaVo tenantWaterAreaVo) {
		if (StringUtils.isEmpty(tenantWaterAreaVo.getTenantName())) {
			TenantInfoVo tenantInfoVo = tenantInfoClientService.getById(tenantWaterAreaVo.getTenantId());
			if (tenantInfoVo != null) {
				tenantWaterAreaVo.setTenantName(tenantInfoVo.getTenantName());
			}
		}
		boolean hasChildren = tenantWaterAreaVo.isHasChildren();
		if(hasChildren == false) {
			Long parentId = tenantWaterAreaVo.getId();
			TenantWaterAreaQueryParam tenantWaterAreaQueryParam = new TenantWaterAreaQueryParam();
			tenantWaterAreaQueryParam.setParentId(parentId);
			Page<TenantWaterAreaVo> tenantWaterAreaVoPage = tenantWaterAreaClientService.page(tenantWaterAreaQueryParam, 1, 500, "id", "desc");
			if(tenantWaterAreaVoPage!=null && tenantWaterAreaVoPage.getTotal()>0) {
				tenantWaterAreaVo.setHasChildren(true);
			}
		}
	}

}
