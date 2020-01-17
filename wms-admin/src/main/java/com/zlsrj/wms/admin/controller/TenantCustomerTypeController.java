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
import com.zlsrj.wms.api.client.service.TenantCustomerTypeClientService;
import com.zlsrj.wms.api.client.service.TenantInfoClientService;
import com.zlsrj.wms.api.dto.TenantCustomerTypeQueryParam;
import com.zlsrj.wms.api.entity.TenantCustomerType;
import com.zlsrj.wms.api.vo.TenantCustomerTypeVo;
import com.zlsrj.wms.api.vo.TenantInfoVo;
import com.zlsrj.wms.common.api.CommonPage;
import com.zlsrj.wms.common.api.CommonResult;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

@Api(value = "用户分类", tags = { "用户分类操作接口" })
@Controller
@RequestMapping("/tenantCustomerType")
@Slf4j
public class TenantCustomerTypeController {

	@Autowired
	private TenantCustomerTypeClientService tenantCustomerTypeClientService;
	@Autowired
	private TenantInfoClientService tenantInfoClientService;

	@ApiOperation(value = "根据参数查询用户分类列表")
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	@ResponseBody
	public CommonResult<CommonPage<TenantCustomerTypeVo>> list(TenantCustomerTypeQueryParam tenantCustomerTypeQueryParam, int pageNum,
			int pageSize) {
		Page<TenantCustomerTypeVo> tenantCustomerTypeVoPage = tenantCustomerTypeClientService.page(tenantCustomerTypeQueryParam, pageNum, pageSize, "id", "desc");
		tenantCustomerTypeVoPage.getRecords().stream().forEach(v->wrappperVo(v));

		CommonPage<TenantCustomerTypeVo> tenantCustomerTypeCommonPage = CommonPage.restPage(tenantCustomerTypeVoPage);

		return CommonResult.success(tenantCustomerTypeCommonPage);
	}

	@ApiOperation(value = "新增用户分类")
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	@ResponseBody
	public CommonResult<TenantCustomerTypeVo> create(@RequestBody TenantCustomerType tenantCustomerType) {
		TenantCustomerTypeVo tenantCustomerTypeVo = tenantCustomerTypeClientService.save(tenantCustomerType);

		return CommonResult.success(tenantCustomerTypeVo);
	}

	@ApiOperation(value = "根据ID查询用户分类")
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ResponseBody
	public CommonResult<TenantCustomerTypeVo> getById(@PathVariable("id") Long id) {
		TenantCustomerTypeVo tenantCustomerTypeVo = tenantCustomerTypeClientService.getById(id);
		wrappperVo(tenantCustomerTypeVo);

		return CommonResult.success(tenantCustomerTypeVo);
	}

	@ApiOperation(value = "根据参数更新用户分类信息")
	@RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
	@ResponseBody
	public CommonResult<TenantCustomerTypeVo> getById(@RequestBody TenantCustomerType tenantCustomerType) {
		Long id = tenantCustomerType.getId();
		TenantCustomerTypeVo tenantCustomerTypeVo = tenantCustomerTypeClientService.updatePatchById(id, tenantCustomerType);
		wrappperVo(tenantCustomerTypeVo);

		return CommonResult.success(tenantCustomerTypeVo);
	}
	
	@ApiOperation(value = "根据ID删除用户分类")
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
	@ResponseBody
	public CommonResult<Object> removeById(@PathVariable("id") Long id) {
		CommonResult<Object> commonResult = tenantCustomerTypeClientService.removeById(id);

		return commonResult;
	}

	private void wrappperVo(TenantCustomerTypeVo tenantCustomerTypeVo) {
		if (StringUtils.isEmpty(tenantCustomerTypeVo.getTenantName())) {
			TenantInfoVo tenantInfoVo = tenantInfoClientService.getById(tenantCustomerTypeVo.getTenantId());
			if (tenantInfoVo != null) {
				tenantCustomerTypeVo.setTenantName(tenantInfoVo.getTenantName());
			}
		}
		boolean hasChildren = tenantCustomerTypeVo.isHasChildren();
		if(hasChildren == false) {
			Long parentId = tenantCustomerTypeVo.getId();
			TenantCustomerTypeQueryParam tenantCustomerTypeQueryParam = new TenantCustomerTypeQueryParam();
			tenantCustomerTypeQueryParam.setParentId(parentId);
			Page<TenantCustomerTypeVo> tenantCustomerTypeVoPage = tenantCustomerTypeClientService.page(tenantCustomerTypeQueryParam, 1, 500, "id", "desc");
			if(tenantCustomerTypeVoPage!=null && tenantCustomerTypeVoPage.getTotal()>0) {
				tenantCustomerTypeVo.setHasChildren(true);
			}
		}
	}

}
