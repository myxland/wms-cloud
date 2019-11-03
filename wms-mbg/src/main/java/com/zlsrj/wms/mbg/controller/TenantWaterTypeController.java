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
import com.zlsrj.wms.mbg.dto.TenantWaterTypeQueryParam;
import com.zlsrj.wms.mbg.entity.TenantWaterType;
import com.zlsrj.wms.mbg.service.ITenantWaterTypeService;

@RestController
@RequestMapping("/tenantWaterType")
public class TenantWaterTypeController {

	@Autowired
	private ITenantWaterTypeService tenantWaterTypeService;

	@RequestMapping(value = "/select/{id}", method = RequestMethod.GET)
	public Object getById(@PathVariable("id") Long id) {
		TenantWaterType tenantWaterType = tenantWaterTypeService.getById(id);

		return CommonResult.success(tenantWaterType);
	}

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public Object list() {
		List<TenantWaterType> tenantWaterTypeList = tenantWaterTypeService.list();

		return tenantWaterTypeList;
	}

	@RequestMapping(value = "/page", method = RequestMethod.GET)
	public Object page(TenantWaterTypeQueryParam tenantWaterTypeQueryParam,
			@RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
			@RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize) {

		IPage<TenantWaterType> page = new Page<TenantWaterType>(pageNum, pageSize);
		QueryWrapper<TenantWaterType> queryWrapperTenantWaterType = new QueryWrapper<TenantWaterType>();
		queryWrapperTenantWaterType.lambda()
				.eq(tenantWaterTypeQueryParam.getId() != null, TenantWaterType::getId, tenantWaterTypeQueryParam.getId())
				.eq(tenantWaterTypeQueryParam.getTenantId() != null, TenantWaterType::getTenantId, tenantWaterTypeQueryParam.getTenantId())
				.eq(tenantWaterTypeQueryParam.getWaterTypeName() != null, TenantWaterType::getWaterTypeName, tenantWaterTypeQueryParam.getWaterTypeName())
				;

		IPage<TenantWaterType> tenantWaterTypePage = tenantWaterTypeService.page(page, queryWrapperTenantWaterType);

		return CommonPage.restPage(tenantWaterTypePage);
	}

	@RequestMapping(value = "/insert", method = RequestMethod.POST)
	public Object insert(@RequestBody TenantWaterType tenantWaterType) {
		boolean success = tenantWaterTypeService.save(tenantWaterType);

		return success ? CommonResult.success(success) : CommonResult.failed();
	}

	@RequestMapping(value = "/update", method = RequestMethod.PUT)
	public Object update(@RequestBody TenantWaterType tenantWaterType) {
		boolean success = tenantWaterTypeService.updateById(tenantWaterType);

		return success ? CommonResult.success(success) : CommonResult.failed();
	}

	@RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
	public Object delete(@PathVariable("id") Long id) {
		boolean success = tenantWaterTypeService.removeById(id);
		
		return success ? CommonResult.success(success) : CommonResult.failed();
	}

	@RequestMapping(value = "/delete/ids/{ids}", method = RequestMethod.DELETE)
	public Object deleteByIds(@PathVariable("ids") String ids) {
		List<Long> idList = Arrays.asList(ids.split(",")).stream().map(id -> Long.parseLong(id))
				.collect(Collectors.toList());
		boolean success = tenantWaterTypeService.removeByIds(idList);
		
		return success ? CommonResult.success(success) : CommonResult.failed();
	}
	
}
