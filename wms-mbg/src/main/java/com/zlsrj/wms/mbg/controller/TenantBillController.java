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
import com.zlsrj.wms.api.dto.TenantBillQueryParam;
import com.zlsrj.wms.api.entity.TenantBill;
import com.zlsrj.wms.mbg.service.ITenantBillService;

@RestController
@RequestMapping("/tenantBill")
public class TenantBillController {

	@Autowired
	private ITenantBillService tenantBillService;

	@RequestMapping(value = "/select/{id}", method = RequestMethod.GET)
	public Object getById(@PathVariable("id") Long id) {
		TenantBill tenantBill = tenantBillService.getById(id);

		return CommonResult.success(tenantBill);
	}

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public Object list() {
		List<TenantBill> tenantBillList = tenantBillService.list();

		return tenantBillList;
	}

	@RequestMapping(value = "/page", method = RequestMethod.GET)
	public Object page(TenantBillQueryParam tenantBillQueryParam,
			@RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
			@RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize) {

		IPage<TenantBill> page = new Page<TenantBill>(pageNum, pageSize);
		QueryWrapper<TenantBill> queryWrapperTenantBill = new QueryWrapper<TenantBill>();
		queryWrapperTenantBill.lambda()
				.eq(tenantBillQueryParam.getId() != null, TenantBill::getId, tenantBillQueryParam.getId())
				.eq(tenantBillQueryParam.getTenantId() != null, TenantBill::getTenantId, tenantBillQueryParam.getTenantId())
				.eq(tenantBillQueryParam.getBillPrintType() != null, TenantBill::getBillPrintType, tenantBillQueryParam.getBillPrintType())
				.eq(tenantBillQueryParam.getBillRemark() != null, TenantBill::getBillRemark, tenantBillQueryParam.getBillRemark())
				.eq(tenantBillQueryParam.getBillService() != null, TenantBill::getBillService, tenantBillQueryParam.getBillService())
				.eq(tenantBillQueryParam.getBillJrdm() != null, TenantBill::getBillJrdm, tenantBillQueryParam.getBillJrdm())
				.eq(tenantBillQueryParam.getBillQmcs() != null, TenantBill::getBillQmcs, tenantBillQueryParam.getBillQmcs())
				.eq(tenantBillQueryParam.getBillSkpbh() != null, TenantBill::getBillSkpbh, tenantBillQueryParam.getBillSkpbh())
				.eq(tenantBillQueryParam.getBillSkpkl() != null, TenantBill::getBillSkpkl, tenantBillQueryParam.getBillSkpkl())
				.eq(tenantBillQueryParam.getBillKeypwd() != null, TenantBill::getBillKeypwd, tenantBillQueryParam.getBillKeypwd())
				;

		IPage<TenantBill> tenantBillPage = tenantBillService.page(page, queryWrapperTenantBill);

		return CommonPage.restPage(tenantBillPage);
	}

	@RequestMapping(value = "/insert", method = RequestMethod.POST)
	public Object insert(@RequestBody TenantBill tenantBill) {
		boolean success = tenantBillService.save(tenantBill);

		return success ? CommonResult.success(success) : CommonResult.failed();
	}

	@RequestMapping(value = "/update", method = RequestMethod.PUT)
	public Object update(@RequestBody TenantBill tenantBill) {
		boolean success = tenantBillService.updateById(tenantBill);

		return success ? CommonResult.success(success) : CommonResult.failed();
	}

	@RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
	public Object delete(@PathVariable("id") Long id) {
		boolean success = tenantBillService.removeById(id);
		
		return success ? CommonResult.success(success) : CommonResult.failed();
	}

	@RequestMapping(value = "/delete/ids/{ids}", method = RequestMethod.DELETE)
	public Object deleteByIds(@PathVariable("ids") String ids) {
		List<Long> idList = Arrays.asList(ids.split(",")).stream().map(id -> Long.parseLong(id))
				.collect(Collectors.toList());
		boolean success = tenantBillService.removeByIds(idList);
		
		return success ? CommonResult.success(success) : CommonResult.failed();
	}
	
}
