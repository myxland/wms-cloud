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
import com.zlsrj.wms.api.dto.TenantPriceTypeQueryParam;
import com.zlsrj.wms.api.entity.TenantPriceType;
import com.zlsrj.wms.common.api.CommonPage;
import com.zlsrj.wms.common.api.CommonResult;
import com.zlsrj.wms.mbg.service.ITenantPriceTypeService;

@RestController
@RequestMapping("/tenantPriceType")
public class TenantPriceTypeController {

	@Autowired
	private ITenantPriceTypeService tenantPriceTypeService;

	@RequestMapping(value = "/select/{id}", method = RequestMethod.GET)
	public Object getById(@PathVariable("id") Long id) {
		TenantPriceType tenantPriceType = tenantPriceTypeService.getById(id);

		return CommonResult.success(tenantPriceType);
	}

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public Object list() {
		List<TenantPriceType> tenantPriceTypeList = tenantPriceTypeService.list();

		return tenantPriceTypeList;
	}

	@RequestMapping(value = "/page", method = RequestMethod.GET)
	public Object page(TenantPriceTypeQueryParam tenantPriceTypeQueryParam,
			@RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
			@RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize) {

		IPage<TenantPriceType> page = new Page<TenantPriceType>(pageNum, pageSize);
		QueryWrapper<TenantPriceType> queryWrapperTenantPriceType = new QueryWrapper<TenantPriceType>();
		queryWrapperTenantPriceType.lambda()
				.eq(tenantPriceTypeQueryParam.getId() != null, TenantPriceType::getId, tenantPriceTypeQueryParam.getId())
				.eq(tenantPriceTypeQueryParam.getTenantId() != null, TenantPriceType::getTenantId, tenantPriceTypeQueryParam.getTenantId())
				.eq(tenantPriceTypeQueryParam.getPriceTypeName() != null, TenantPriceType::getPriceTypeName, tenantPriceTypeQueryParam.getPriceTypeName())
				.eq(tenantPriceTypeQueryParam.getBottomOn() != null, TenantPriceType::getBottomOn, tenantPriceTypeQueryParam.getBottomOn())
				.eq(tenantPriceTypeQueryParam.getBottomNum() != null, TenantPriceType::getBottomNum, tenantPriceTypeQueryParam.getBottomNum())
				.eq(tenantPriceTypeQueryParam.getTopOn() != null, TenantPriceType::getTopOn, tenantPriceTypeQueryParam.getTopOn())
				.eq(tenantPriceTypeQueryParam.getTopNum() != null, TenantPriceType::getTopNum, tenantPriceTypeQueryParam.getTopNum())
				.eq(tenantPriceTypeQueryParam.getReduceOn() != null, TenantPriceType::getReduceOn, tenantPriceTypeQueryParam.getReduceOn())
				.eq(tenantPriceTypeQueryParam.getReduceNum() != null, TenantPriceType::getReduceNum, tenantPriceTypeQueryParam.getReduceNum())
				.eq(tenantPriceTypeQueryParam.getReduceLowerLimit() != null, TenantPriceType::getReduceLowerLimit, tenantPriceTypeQueryParam.getReduceLowerLimit())
				.eq(tenantPriceTypeQueryParam.getFixedOn() != null, TenantPriceType::getFixedOn, tenantPriceTypeQueryParam.getFixedOn())
				.eq(tenantPriceTypeQueryParam.getFixedNum() != null, TenantPriceType::getFixedNum, tenantPriceTypeQueryParam.getFixedNum())
				;

		IPage<TenantPriceType> tenantPriceTypePage = tenantPriceTypeService.page(page, queryWrapperTenantPriceType);

		return CommonPage.restPage(tenantPriceTypePage);
	}

	@RequestMapping(value = "/insert", method = RequestMethod.POST)
	public Object insert(@RequestBody TenantPriceType tenantPriceType) {
		boolean success = tenantPriceTypeService.save(tenantPriceType);

		return success ? CommonResult.success(success) : CommonResult.failed();
	}

	@RequestMapping(value = "/update", method = RequestMethod.PUT)
	public Object update(@RequestBody TenantPriceType tenantPriceType) {
		boolean success = tenantPriceTypeService.updateById(tenantPriceType);

		return success ? CommonResult.success(success) : CommonResult.failed();
	}

	@RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
	public Object delete(@PathVariable("id") Long id) {
		boolean success = tenantPriceTypeService.removeById(id);
		
		return success ? CommonResult.success(success) : CommonResult.failed();
	}

	@RequestMapping(value = "/delete/ids/{ids}", method = RequestMethod.DELETE)
	public Object deleteByIds(@PathVariable("ids") String ids) {
		List<Long> idList = Arrays.asList(ids.split(",")).stream().map(id -> Long.parseLong(id))
				.collect(Collectors.toList());
		boolean success = tenantPriceTypeService.removeByIds(idList);
		
		return success ? CommonResult.success(success) : CommonResult.failed();
	}
	
}
