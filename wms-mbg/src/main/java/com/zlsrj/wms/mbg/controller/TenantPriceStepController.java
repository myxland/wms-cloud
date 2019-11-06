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
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zlsrj.wms.api.dto.TenantPriceStepQueryParam;
import com.zlsrj.wms.api.entity.TenantPriceStep;
import com.zlsrj.wms.common.api.CommonPage;
import com.zlsrj.wms.common.api.CommonResult;
import com.zlsrj.wms.mbg.service.ITenantPriceStepService;

@RestController
@RequestMapping("/tenantPriceStep")
public class TenantPriceStepController {

	@Autowired
	private ITenantPriceStepService tenantPriceStepService;

	@RequestMapping(value = "/select/{id}", method = RequestMethod.GET)
	public Object getById(@PathVariable("id") Long id) {
		TenantPriceStep tenantPriceStep = tenantPriceStepService.getById(id);

		return CommonResult.success(tenantPriceStep);
	}

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public Object list() {
		List<TenantPriceStep> tenantPriceStepList = tenantPriceStepService.list();

		return tenantPriceStepList;
	}

	@RequestMapping(value = "/page", method = RequestMethod.GET)
	public Object page(TenantPriceStepQueryParam tenantPriceStepQueryParam,
			@RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
			@RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize) {

		IPage<TenantPriceStep> page = new Page<TenantPriceStep>(pageNum, pageSize);
		QueryWrapper<TenantPriceStep> queryWrapperTenantPriceStep = new QueryWrapper<TenantPriceStep>();
		queryWrapperTenantPriceStep.lambda()
				.eq(tenantPriceStepQueryParam.getId() != null, TenantPriceStep::getId, tenantPriceStepQueryParam.getId())
				.eq(tenantPriceStepQueryParam.getTenantId() != null, TenantPriceStep::getTenantId, tenantPriceStepQueryParam.getTenantId())
				.eq(tenantPriceStepQueryParam.getPriceTypeId() != null, TenantPriceStep::getPriceTypeId, tenantPriceStepQueryParam.getPriceTypeId())
				.eq(tenantPriceStepQueryParam.getPriceItemId() != null, TenantPriceStep::getPriceItemId, tenantPriceStepQueryParam.getPriceItemId())
				.eq(tenantPriceStepQueryParam.getStepId() != null, TenantPriceStep::getStepId, tenantPriceStepQueryParam.getStepId())
				.eq(tenantPriceStepQueryParam.getStartNum() != null, TenantPriceStep::getStartNum, tenantPriceStepQueryParam.getStartNum())
				.eq(tenantPriceStepQueryParam.getEndNum() != null, TenantPriceStep::getEndNum, tenantPriceStepQueryParam.getEndNum())
				.eq(tenantPriceStepQueryParam.getPrice() != null, TenantPriceStep::getPrice, tenantPriceStepQueryParam.getPrice())
				;

		IPage<TenantPriceStep> tenantPriceStepPage = tenantPriceStepService.page(page, queryWrapperTenantPriceStep);

		return CommonPage.restPage(tenantPriceStepPage);
	}

	@RequestMapping(value = "/insert", method = RequestMethod.POST)
	public Object insert(@RequestBody TenantPriceStep tenantPriceStep) {
		boolean success = tenantPriceStepService.save(tenantPriceStep);

		return success ? CommonResult.success(success) : CommonResult.failed();
	}

	@RequestMapping(value = "/update", method = RequestMethod.PUT)
	public Object update(@RequestBody TenantPriceStep tenantPriceStep) {
		boolean success = tenantPriceStepService.updateById(tenantPriceStep);

		return success ? CommonResult.success(success) : CommonResult.failed();
	}

	@RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
	public Object delete(@PathVariable("id") Long id) {
		boolean success = tenantPriceStepService.removeById(id);
		
		return success ? CommonResult.success(success) : CommonResult.failed();
	}

	@RequestMapping(value = "/delete/ids/{ids}", method = RequestMethod.DELETE)
	public Object deleteByIds(@PathVariable("ids") String ids) {
		List<Long> idList = Arrays.asList(ids.split(",")).stream().map(id -> Long.parseLong(id))
				.collect(Collectors.toList());
		boolean success = tenantPriceStepService.removeByIds(idList);
		
		return success ? CommonResult.success(success) : CommonResult.failed();
	}
	
}
