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
import com.zlsrj.wms.api.dto.TenantDeptQueryParam;
import com.zlsrj.wms.api.entity.TenantDept;
import com.zlsrj.wms.common.api.CommonPage;
import com.zlsrj.wms.common.api.CommonResult;
import com.zlsrj.wms.mbg.service.ITenantDeptService;

@RestController
@RequestMapping("/tenantDept")
public class TenantDeptController {

	@Autowired
	private ITenantDeptService tenantDeptService;

	@RequestMapping(value = "/select/{id}", method = RequestMethod.GET)
	public Object getById(@PathVariable("id") Long id) {
		TenantDept tenantDept = tenantDeptService.getById(id);

		return CommonResult.success(tenantDept);
	}

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public Object list() {
		List<TenantDept> tenantDeptList = tenantDeptService.list();

		return tenantDeptList;
	}

	@RequestMapping(value = "/page", method = RequestMethod.GET)
	public Object page(TenantDeptQueryParam tenantDeptQueryParam,
			@RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
			@RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize) {

		IPage<TenantDept> page = new Page<TenantDept>(pageNum, pageSize);
		QueryWrapper<TenantDept> queryWrapperTenantDept = new QueryWrapper<TenantDept>();
		queryWrapperTenantDept.lambda()
				.eq(tenantDeptQueryParam.getId() != null, TenantDept::getId, tenantDeptQueryParam.getId())
				.eq(tenantDeptQueryParam.getParentDeptId() != null, TenantDept::getParentDeptId, tenantDeptQueryParam.getParentDeptId())
				.eq(tenantDeptQueryParam.getTenantId() != null, TenantDept::getTenantId, tenantDeptQueryParam.getTenantId())
				.eq(tenantDeptQueryParam.getDeptName() != null, TenantDept::getDeptName, tenantDeptQueryParam.getDeptName())
				;

		IPage<TenantDept> tenantDeptPage = tenantDeptService.page(page, queryWrapperTenantDept);

		return CommonPage.restPage(tenantDeptPage);
	}

	@RequestMapping(value = "/insert", method = RequestMethod.POST)
	public Object insert(@RequestBody TenantDept tenantDept) {
		boolean success = tenantDeptService.save(tenantDept);

		return success ? CommonResult.success(success) : CommonResult.failed();
	}

	@RequestMapping(value = "/update", method = RequestMethod.PUT)
	public Object update(@RequestBody TenantDept tenantDept) {
		boolean success = tenantDeptService.updateById(tenantDept);

		return success ? CommonResult.success(success) : CommonResult.failed();
	}

	@RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
	public Object delete(@PathVariable("id") Long id) {
		boolean success = tenantDeptService.removeById(id);
		
		return success ? CommonResult.success(success) : CommonResult.failed();
	}

	@RequestMapping(value = "/delete/ids/{ids}", method = RequestMethod.DELETE)
	public Object deleteByIds(@PathVariable("ids") String ids) {
		List<Long> idList = Arrays.asList(ids.split(",")).stream().map(id -> Long.parseLong(id))
				.collect(Collectors.toList());
		boolean success = tenantDeptService.removeByIds(idList);
		
		return success ? CommonResult.success(success) : CommonResult.failed();
	}
	
}
