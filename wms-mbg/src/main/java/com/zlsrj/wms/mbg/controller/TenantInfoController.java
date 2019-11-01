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
import com.zlsrj.wms.mbg.dto.TenantInfoQueryParam;
import com.zlsrj.wms.mbg.entity.TenantInfo;
import com.zlsrj.wms.mbg.service.ITenantInfoService;

/**
 * <p>
 * 租户 前端控制器
 * </p>
 *
 * @author wms
 * @since 2019-10-31
 */
@RestController
@RequestMapping("/tenantInfo")
public class TenantInfoController {

	@Autowired
	private ITenantInfoService tenantInfoService;

	@RequestMapping(value = "/select/{id}", method = RequestMethod.GET)
	public Object getById(@PathVariable("id") Long id) {
		TenantInfo tenantInfo = tenantInfoService.getById(id);

		return CommonResult.success(tenantInfo);
	}

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public Object list() {
		List<TenantInfo> tenantInfoList = tenantInfoService.list();

		return tenantInfoList;
	}

	@RequestMapping(value = "/page", method = RequestMethod.GET)
	public Object page(TenantInfoQueryParam tenantInfoQueryParam,
			@RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
			@RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize) {

		IPage<TenantInfo> page = new Page<TenantInfo>(pageNum, pageSize);
		QueryWrapper<TenantInfo> queryWrapperTenantInfo = new QueryWrapper<TenantInfo>();
		queryWrapperTenantInfo.lambda()
				.eq(tenantInfoQueryParam.getId() != null, TenantInfo::getId, tenantInfoQueryParam.getId())
				.eq(tenantInfoQueryParam.getTenantStatus() != null, TenantInfo::getTenantStatus,
						tenantInfoQueryParam.getTenantStatus());

		IPage<TenantInfo> tenantInfoPage = tenantInfoService.page(page, queryWrapperTenantInfo);

		return CommonPage.restPage(tenantInfoPage);
	}

	@RequestMapping(value = "/insert", method = RequestMethod.POST)
	public Object insert(@RequestBody TenantInfo tenantInfo) {
		boolean success = tenantInfoService.save(tenantInfo);

		return success ? CommonResult.success(success) : CommonResult.failed();
	}

	@RequestMapping(value = "/update", method = RequestMethod.PUT)
	public Object update(@RequestBody TenantInfo tenantInfo) {
		boolean success = tenantInfoService.updateById(tenantInfo);

		return success ? CommonResult.success(success) : CommonResult.failed();
	}

	@RequestMapping(value = "/update/{id}/status/{status}", method = RequestMethod.PUT)
	public Object update(@PathVariable("id") Long id, @PathVariable("status") Integer status) {
		UpdateWrapper<TenantInfo> updateWrapperTenantInfo = new UpdateWrapper<TenantInfo>();
		updateWrapperTenantInfo.lambda().set(TenantInfo::getTenantStatus, status).eq(TenantInfo::getId, id);
		boolean success = tenantInfoService.update(updateWrapperTenantInfo);

		return success ? CommonResult.success(success) : CommonResult.failed();
	}

	@RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
	public Object delete(@PathVariable("id") Long id) {
		boolean success = tenantInfoService.removeById(id);
		
		return success ? CommonResult.success(success) : CommonResult.failed();
	}

	@RequestMapping(value = "/delete/ids/{ids}", method = RequestMethod.DELETE)
	public Object deleteByIds(@PathVariable("ids") String ids) {
		List<Long> idList = Arrays.asList(ids.split(",")).stream().map(id -> Long.parseLong(id))
				.collect(Collectors.toList());
		boolean success = tenantInfoService.removeByIds(idList);
		
		return success ? CommonResult.success(success) : CommonResult.failed();
	}
}
