package com.zlsrj.wms.mbg.controller;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zlsrj.wms.common.api.CommonPage;
import com.zlsrj.wms.common.api.CommonResult;
import com.zlsrj.wms.mbg.dto.TenantCustTypeQueryParam;
import com.zlsrj.wms.mbg.entity.TenantCustType;
import com.zlsrj.wms.mbg.service.ITenantCustTypeService;

@RestController
@RequestMapping("/tenantCustType")
public class TenantCustTypeController {

	@Autowired
	private ITenantCustTypeService tenantCustTypeService;

	@RequestMapping(value = "/select/{id}", method = RequestMethod.GET)
	public Object getById(@PathVariable("id") Long id) {
		TenantCustType tenantCustType = tenantCustTypeService.getById(id);

		return CommonResult.success(tenantCustType);
	}

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public Object list() {
		List<TenantCustType> tenantCustTypeList = tenantCustTypeService.list();

		return tenantCustTypeList;
	}

	@RequestMapping(value = "/page", method = RequestMethod.GET)
	public Object page(TenantCustTypeQueryParam tenantCustTypeQueryParam,
			@RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
			@RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize) {

		IPage<TenantCustType> page = new Page<TenantCustType>(pageNum, pageSize);
		QueryWrapper<TenantCustType> queryWrapperTenantCustType = new QueryWrapper<TenantCustType>();
		queryWrapperTenantCustType.lambda()
				.eq(tenantCustTypeQueryParam.getId() != null, TenantCustType::getId, tenantCustTypeQueryParam.getId())
				.eq(tenantCustTypeQueryParam.getTenantId() != null, TenantCustType::getTenantId, tenantCustTypeQueryParam.getTenantId())
				.eq(tenantCustTypeQueryParam.getCustTypeName() != null, TenantCustType::getCustTypeName, tenantCustTypeQueryParam.getCustTypeName())
				;

		IPage<TenantCustType> tenantCustTypePage = tenantCustTypeService.page(page, queryWrapperTenantCustType);

		return CommonPage.restPage(tenantCustTypePage);
	}

	@RequestMapping(value = "/insert", method = RequestMethod.POST)
	public Object insert(@RequestBody TenantCustType tenantCustType) {
		boolean success = tenantCustTypeService.save(tenantCustType);

		return success ? CommonResult.success(success) : CommonResult.failed();
	}

	@RequestMapping(value = "/update", method = RequestMethod.PUT)
	public Object update(@RequestBody TenantCustType tenantCustType) {
		boolean success = tenantCustTypeService.updateById(tenantCustType);

		return success ? CommonResult.success(success) : CommonResult.failed();
	}

	@RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
	public Object delete(@PathVariable("id") Long id) {
		boolean success = tenantCustTypeService.removeById(id);
		
		return success ? CommonResult.success(success) : CommonResult.failed();
	}

	@RequestMapping(value = "/delete/ids/{ids}", method = RequestMethod.DELETE)
	public Object deleteByIds(@PathVariable("ids") String ids) {
		List<Long> idList = Arrays.asList(ids.split(",")).stream().map(id -> Long.parseLong(id))
				.collect(Collectors.toList());
		boolean success = tenantCustTypeService.removeByIds(idList);
		
		return success ? CommonResult.success(success) : CommonResult.failed();
	}
	
}
