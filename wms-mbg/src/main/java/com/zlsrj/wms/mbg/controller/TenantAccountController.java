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
import com.zlsrj.wms.mbg.dto.TenantAccountQueryParam;
import com.zlsrj.wms.mbg.entity.TenantAccount;
import com.zlsrj.wms.mbg.service.ITenantAccountService;

@RestController
@RequestMapping("/tenantAccount")
public class TenantAccountController {

	@Autowired
	private ITenantAccountService tenantAccountService;

	@RequestMapping(value = "/select/{id}", method = RequestMethod.GET)
	public Object getById(@PathVariable("id") Long id) {
		TenantAccount tenantAccount = tenantAccountService.getById(id);

		return CommonResult.success(tenantAccount);
	}

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public Object list() {
		List<TenantAccount> tenantAccountList = tenantAccountService.list();

		return tenantAccountList;
	}

	@RequestMapping(value = "/page", method = RequestMethod.GET)
	public Object page(TenantAccountQueryParam tenantAccountQueryParam,
			@RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
			@RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize) {

		IPage<TenantAccount> page = new Page<TenantAccount>(pageNum, pageSize);
		QueryWrapper<TenantAccount> queryWrapperTenantAccount = new QueryWrapper<TenantAccount>();
		queryWrapperTenantAccount.lambda()
				.eq(tenantAccountQueryParam.getId() != null, TenantAccount::getId, tenantAccountQueryParam.getId())
				.eq(tenantAccountQueryParam.getTenantId() != null, TenantAccount::getTenantId, tenantAccountQueryParam.getTenantId())
				.eq(tenantAccountQueryParam.getAccountBalance() != null, TenantAccount::getAccountBalance, tenantAccountQueryParam.getAccountBalance())
				;

		IPage<TenantAccount> tenantAccountPage = tenantAccountService.page(page, queryWrapperTenantAccount);

		return CommonPage.restPage(tenantAccountPage);
	}

	@RequestMapping(value = "/insert", method = RequestMethod.POST)
	public Object insert(@RequestBody TenantAccount tenantAccount) {
		boolean success = tenantAccountService.save(tenantAccount);

		return success ? CommonResult.success(success) : CommonResult.failed();
	}

	@RequestMapping(value = "/update", method = RequestMethod.PUT)
	public Object update(@RequestBody TenantAccount tenantAccount) {
		boolean success = tenantAccountService.updateById(tenantAccount);

		return success ? CommonResult.success(success) : CommonResult.failed();
	}

	@RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
	public Object delete(@PathVariable("id") Long id) {
		boolean success = tenantAccountService.removeById(id);
		
		return success ? CommonResult.success(success) : CommonResult.failed();
	}

	@RequestMapping(value = "/delete/ids/{ids}", method = RequestMethod.DELETE)
	public Object deleteByIds(@PathVariable("ids") String ids) {
		List<Long> idList = Arrays.asList(ids.split(",")).stream().map(id -> Long.parseLong(id))
				.collect(Collectors.toList());
		boolean success = tenantAccountService.removeByIds(idList);
		
		return success ? CommonResult.success(success) : CommonResult.failed();
	}
	
}
