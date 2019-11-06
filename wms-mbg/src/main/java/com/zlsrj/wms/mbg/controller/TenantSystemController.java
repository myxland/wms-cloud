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
import com.zlsrj.wms.api.dto.TenantSystemQueryParam;
import com.zlsrj.wms.api.entity.TenantSystem;
import com.zlsrj.wms.common.api.CommonPage;
import com.zlsrj.wms.common.api.CommonResult;
import com.zlsrj.wms.mbg.service.ITenantSystemService;

@RestController
@RequestMapping("/tenantSystem")
public class TenantSystemController {

	@Autowired
	private ITenantSystemService tenantSystemService;

	@RequestMapping(value = "/select/{id}", method = RequestMethod.GET)
	public Object getById(@PathVariable("id") Long id) {
		TenantSystem tenantSystem = tenantSystemService.getById(id);

		return CommonResult.success(tenantSystem);
	}

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public Object list() {
		List<TenantSystem> tenantSystemList = tenantSystemService.list();

		return tenantSystemList;
	}

	@RequestMapping(value = "/page", method = RequestMethod.GET)
	public Object page(TenantSystemQueryParam tenantSystemQueryParam,
			@RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
			@RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize) {

		IPage<TenantSystem> page = new Page<TenantSystem>(pageNum, pageSize);
		QueryWrapper<TenantSystem> queryWrapperTenantSystem = new QueryWrapper<TenantSystem>();
		queryWrapperTenantSystem.lambda()
				.eq(tenantSystemQueryParam.getId() != null, TenantSystem::getId, tenantSystemQueryParam.getId())
				.eq(tenantSystemQueryParam.getTenantId() != null, TenantSystem::getTenantId, tenantSystemQueryParam.getTenantId())
				.eq(tenantSystemQueryParam.getSysId() != null, TenantSystem::getSysId, tenantSystemQueryParam.getSysId())
				.eq(tenantSystemQueryParam.getSysDispName() != null, TenantSystem::getSysDispName, tenantSystemQueryParam.getSysDispName())
				.eq(tenantSystemQueryParam.getSysOrder() != null, TenantSystem::getSysOrder, tenantSystemQueryParam.getSysOrder())
				.eq(tenantSystemQueryParam.getSysEdition() != null, TenantSystem::getSysEdition, tenantSystemQueryParam.getSysEdition())
				.eq(tenantSystemQueryParam.getSysStatus() != null, TenantSystem::getSysStatus, tenantSystemQueryParam.getSysStatus())
				.eq(tenantSystemQueryParam.getSysPriceType() != null, TenantSystem::getSysPriceType, tenantSystemQueryParam.getSysPriceType())
				.eq(tenantSystemQueryParam.getSysOpenDate() != null, TenantSystem::getSysOpenDate, tenantSystemQueryParam.getSysOpenDate())
				.eq(tenantSystemQueryParam.getSysEndDate() != null, TenantSystem::getSysEndDate, tenantSystemQueryParam.getSysEndDate())
				.eq(tenantSystemQueryParam.getSysStartChargeDate() != null, TenantSystem::getSysStartChargeDate, tenantSystemQueryParam.getSysStartChargeDate())
				.eq(tenantSystemQueryParam.getSysArrears() != null, TenantSystem::getSysArrears, tenantSystemQueryParam.getSysArrears())
				.eq(tenantSystemQueryParam.getSysOverdraft() != null, TenantSystem::getSysOverdraft, tenantSystemQueryParam.getSysOverdraft())
				;

		IPage<TenantSystem> tenantSystemPage = tenantSystemService.page(page, queryWrapperTenantSystem);

		return CommonPage.restPage(tenantSystemPage);
	}

	@RequestMapping(value = "/insert", method = RequestMethod.POST)
	public Object insert(@RequestBody TenantSystem tenantSystem) {
		boolean success = tenantSystemService.save(tenantSystem);

		return success ? CommonResult.success(success) : CommonResult.failed();
	}

	@RequestMapping(value = "/update", method = RequestMethod.PUT)
	public Object update(@RequestBody TenantSystem tenantSystem) {
		boolean success = tenantSystemService.updateById(tenantSystem);

		return success ? CommonResult.success(success) : CommonResult.failed();
	}

	@RequestMapping(value = "/update/{id}/status/{status}", method = RequestMethod.PUT)
	public Object updateStatus(@PathVariable("id") Long id, @PathVariable("status") Integer status) {
		UpdateWrapper<TenantSystem> updateWrapperTenantSystem = new UpdateWrapper<TenantSystem>();
		updateWrapperTenantSystem.lambda().set(TenantSystem::getSysStatus, status).eq(TenantSystem::getId, id);
		boolean success = tenantSystemService.update(updateWrapperTenantSystem);

		return success ? CommonResult.success(success) : CommonResult.failed();
	}

	@RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
	public Object delete(@PathVariable("id") Long id) {
		boolean success = tenantSystemService.removeById(id);
		
		return success ? CommonResult.success(success) : CommonResult.failed();
	}

	@RequestMapping(value = "/delete/ids/{ids}", method = RequestMethod.DELETE)
	public Object deleteByIds(@PathVariable("ids") String ids) {
		List<Long> idList = Arrays.asList(ids.split(",")).stream().map(id -> Long.parseLong(id))
				.collect(Collectors.toList());
		boolean success = tenantSystemService.removeByIds(idList);
		
		return success ? CommonResult.success(success) : CommonResult.failed();
	}
	
}
