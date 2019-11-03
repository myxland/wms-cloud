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
import com.zlsrj.wms.mbg.dto.TenantPriceItemQueryParam;
import com.zlsrj.wms.mbg.entity.TenantPriceItem;
import com.zlsrj.wms.mbg.service.ITenantPriceItemService;

@RestController
@RequestMapping("/tenantPriceItem")
public class TenantPriceItemController {

	@Autowired
	private ITenantPriceItemService tenantPriceItemService;

	@RequestMapping(value = "/select/{id}", method = RequestMethod.GET)
	public Object getById(@PathVariable("id") Long id) {
		TenantPriceItem tenantPriceItem = tenantPriceItemService.getById(id);

		return CommonResult.success(tenantPriceItem);
	}

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public Object list() {
		List<TenantPriceItem> tenantPriceItemList = tenantPriceItemService.list();

		return tenantPriceItemList;
	}

	@RequestMapping(value = "/page", method = RequestMethod.GET)
	public Object page(TenantPriceItemQueryParam tenantPriceItemQueryParam,
			@RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
			@RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize) {

		IPage<TenantPriceItem> page = new Page<TenantPriceItem>(pageNum, pageSize);
		QueryWrapper<TenantPriceItem> queryWrapperTenantPriceItem = new QueryWrapper<TenantPriceItem>();
		queryWrapperTenantPriceItem.lambda()
				.eq(tenantPriceItemQueryParam.getId() != null, TenantPriceItem::getId, tenantPriceItemQueryParam.getId())
				.eq(tenantPriceItemQueryParam.getTenantId() != null, TenantPriceItem::getTenantId, tenantPriceItemQueryParam.getTenantId())
				.eq(tenantPriceItemQueryParam.getPriceItemName() != null, TenantPriceItem::getPriceItemName, tenantPriceItemQueryParam.getPriceItemName())
				.eq(tenantPriceItemQueryParam.getTaxRate() != null, TenantPriceItem::getTaxRate, tenantPriceItemQueryParam.getTaxRate())
				.eq(tenantPriceItemQueryParam.getTaxId() != null, TenantPriceItem::getTaxId, tenantPriceItemQueryParam.getTaxId())
				;

		IPage<TenantPriceItem> tenantPriceItemPage = tenantPriceItemService.page(page, queryWrapperTenantPriceItem);

		return CommonPage.restPage(tenantPriceItemPage);
	}

	@RequestMapping(value = "/insert", method = RequestMethod.POST)
	public Object insert(@RequestBody TenantPriceItem tenantPriceItem) {
		boolean success = tenantPriceItemService.save(tenantPriceItem);

		return success ? CommonResult.success(success) : CommonResult.failed();
	}

	@RequestMapping(value = "/update", method = RequestMethod.PUT)
	public Object update(@RequestBody TenantPriceItem tenantPriceItem) {
		boolean success = tenantPriceItemService.updateById(tenantPriceItem);

		return success ? CommonResult.success(success) : CommonResult.failed();
	}

	@RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
	public Object delete(@PathVariable("id") Long id) {
		boolean success = tenantPriceItemService.removeById(id);
		
		return success ? CommonResult.success(success) : CommonResult.failed();
	}

	@RequestMapping(value = "/delete/ids/{ids}", method = RequestMethod.DELETE)
	public Object deleteByIds(@PathVariable("ids") String ids) {
		List<Long> idList = Arrays.asList(ids.split(",")).stream().map(id -> Long.parseLong(id))
				.collect(Collectors.toList());
		boolean success = tenantPriceItemService.removeByIds(idList);
		
		return success ? CommonResult.success(success) : CommonResult.failed();
	}
	
}
