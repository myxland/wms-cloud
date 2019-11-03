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
import com.zlsrj.wms.mbg.dto.TenantPriceDetailQueryParam;
import com.zlsrj.wms.mbg.entity.TenantPriceDetail;
import com.zlsrj.wms.mbg.service.ITenantPriceDetailService;

@RestController
@RequestMapping("/tenantPriceDetail")
public class TenantPriceDetailController {

	@Autowired
	private ITenantPriceDetailService tenantPriceDetailService;

	@RequestMapping(value = "/select/{id}", method = RequestMethod.GET)
	public Object getById(@PathVariable("id") Long id) {
		TenantPriceDetail tenantPriceDetail = tenantPriceDetailService.getById(id);

		return CommonResult.success(tenantPriceDetail);
	}

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public Object list() {
		List<TenantPriceDetail> tenantPriceDetailList = tenantPriceDetailService.list();

		return tenantPriceDetailList;
	}

	@RequestMapping(value = "/page", method = RequestMethod.GET)
	public Object page(TenantPriceDetailQueryParam tenantPriceDetailQueryParam,
			@RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
			@RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize) {

		IPage<TenantPriceDetail> page = new Page<TenantPriceDetail>(pageNum, pageSize);
		QueryWrapper<TenantPriceDetail> queryWrapperTenantPriceDetail = new QueryWrapper<TenantPriceDetail>();
		queryWrapperTenantPriceDetail.lambda()
				.eq(tenantPriceDetailQueryParam.getId() != null, TenantPriceDetail::getId, tenantPriceDetailQueryParam.getId())
				.eq(tenantPriceDetailQueryParam.getTenantId() != null, TenantPriceDetail::getTenantId, tenantPriceDetailQueryParam.getTenantId())
				.eq(tenantPriceDetailQueryParam.getPriceTypeId() != null, TenantPriceDetail::getPriceTypeId, tenantPriceDetailQueryParam.getPriceTypeId())
				.eq(tenantPriceDetailQueryParam.getPriceItemId() != null, TenantPriceDetail::getPriceItemId, tenantPriceDetailQueryParam.getPriceItemId())
				.eq(tenantPriceDetailQueryParam.getCalcType() != null, TenantPriceDetail::getCalcType, tenantPriceDetailQueryParam.getCalcType())
				.eq(tenantPriceDetailQueryParam.getPrice() != null, TenantPriceDetail::getPrice, tenantPriceDetailQueryParam.getPrice())
				;

		IPage<TenantPriceDetail> tenantPriceDetailPage = tenantPriceDetailService.page(page, queryWrapperTenantPriceDetail);

		return CommonPage.restPage(tenantPriceDetailPage);
	}

	@RequestMapping(value = "/insert", method = RequestMethod.POST)
	public Object insert(@RequestBody TenantPriceDetail tenantPriceDetail) {
		boolean success = tenantPriceDetailService.save(tenantPriceDetail);

		return success ? CommonResult.success(success) : CommonResult.failed();
	}

	@RequestMapping(value = "/update", method = RequestMethod.PUT)
	public Object update(@RequestBody TenantPriceDetail tenantPriceDetail) {
		boolean success = tenantPriceDetailService.updateById(tenantPriceDetail);

		return success ? CommonResult.success(success) : CommonResult.failed();
	}

	@RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
	public Object delete(@PathVariable("id") Long id) {
		boolean success = tenantPriceDetailService.removeById(id);
		
		return success ? CommonResult.success(success) : CommonResult.failed();
	}

	@RequestMapping(value = "/delete/ids/{ids}", method = RequestMethod.DELETE)
	public Object deleteByIds(@PathVariable("ids") String ids) {
		List<Long> idList = Arrays.asList(ids.split(",")).stream().map(id -> Long.parseLong(id))
				.collect(Collectors.toList());
		boolean success = tenantPriceDetailService.removeByIds(idList);
		
		return success ? CommonResult.success(success) : CommonResult.failed();
	}
	
}
