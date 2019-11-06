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
import com.zlsrj.wms.api.dto.TenantInfoQueryParam;
import com.zlsrj.wms.api.entity.TenantInfo;
import com.zlsrj.wms.common.api.CommonPage;
import com.zlsrj.wms.common.api.CommonResult;
import com.zlsrj.wms.mbg.service.ITenantInfoService;

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
	public CommonPage<TenantInfo> page(TenantInfoQueryParam tenantInfoQueryParam,
			@RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
			@RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize) {

		IPage<TenantInfo> page = new Page<TenantInfo>(pageNum, pageSize);
		QueryWrapper<TenantInfo> queryWrapperTenantInfo = new QueryWrapper<TenantInfo>();
		queryWrapperTenantInfo.lambda()
				.eq(tenantInfoQueryParam.getId() != null, TenantInfo::getId, tenantInfoQueryParam.getId())
				.eq(tenantInfoQueryParam.getTenantName() != null, TenantInfo::getTenantName,
						tenantInfoQueryParam.getTenantName())
				.eq(tenantInfoQueryParam.getDisplayName() != null, TenantInfo::getDisplayName,
						tenantInfoQueryParam.getDisplayName())
				.eq(tenantInfoQueryParam.getTenantProvince() != null, TenantInfo::getTenantProvince,
						tenantInfoQueryParam.getTenantProvince())
				.eq(tenantInfoQueryParam.getTenantCity() != null, TenantInfo::getTenantCity,
						tenantInfoQueryParam.getTenantCity())
				.eq(tenantInfoQueryParam.getTenantArea() != null, TenantInfo::getTenantArea,
						tenantInfoQueryParam.getTenantArea())
				.eq(tenantInfoQueryParam.getTenantAddress() != null, TenantInfo::getTenantAddress,
						tenantInfoQueryParam.getTenantAddress())
				.eq(tenantInfoQueryParam.getTenantLinkman() != null, TenantInfo::getTenantLinkman,
						tenantInfoQueryParam.getTenantLinkman())
				.eq(tenantInfoQueryParam.getTenantMobile() != null, TenantInfo::getTenantMobile,
						tenantInfoQueryParam.getTenantMobile())
				.eq(tenantInfoQueryParam.getTenantTel() != null, TenantInfo::getTenantTel,
						tenantInfoQueryParam.getTenantTel())
				.eq(tenantInfoQueryParam.getTenantEmail() != null, TenantInfo::getTenantEmail,
						tenantInfoQueryParam.getTenantEmail())
				.eq(tenantInfoQueryParam.getTenantQq() != null, TenantInfo::getTenantQq,
						tenantInfoQueryParam.getTenantQq())
				.eq(tenantInfoQueryParam.getTenantType() != null, TenantInfo::getTenantType,
						tenantInfoQueryParam.getTenantType())
				.eq(tenantInfoQueryParam.getTenantStatus() != null, TenantInfo::getTenantStatus,
						tenantInfoQueryParam.getTenantStatus())
				.eq(tenantInfoQueryParam.getRegTime() != null, TenantInfo::getRegTime,
						tenantInfoQueryParam.getRegTime())
				.eq(tenantInfoQueryParam.getEndDate() != null, TenantInfo::getEndDate,
						tenantInfoQueryParam.getEndDate())
				.eq(tenantInfoQueryParam.getCreditNumber() != null, TenantInfo::getCreditNumber,
						tenantInfoQueryParam.getCreditNumber())
				.eq(tenantInfoQueryParam.getInvoiceAddress() != null, TenantInfo::getInvoiceAddress,
						tenantInfoQueryParam.getInvoiceAddress())
				.eq(tenantInfoQueryParam.getBankNo() != null, TenantInfo::getBankNo, tenantInfoQueryParam.getBankNo())
				.eq(tenantInfoQueryParam.getBankName() != null, TenantInfo::getBankName,
						tenantInfoQueryParam.getBankName())
				.eq(tenantInfoQueryParam.getAccountNo() != null, TenantInfo::getAccountNo,
						tenantInfoQueryParam.getAccountNo())
				.eq(tenantInfoQueryParam.getPartChargeOn() != null, TenantInfo::getPartChargeOn,
						tenantInfoQueryParam.getPartChargeOn())
				.eq(tenantInfoQueryParam.getOverDuefineOn() != null, TenantInfo::getOverDuefineOn,
						tenantInfoQueryParam.getOverDuefineOn())
				.eq(tenantInfoQueryParam.getOverDuefineDay() != null, TenantInfo::getOverDuefineDay,
						tenantInfoQueryParam.getOverDuefineDay())
				.eq(tenantInfoQueryParam.getOverDuefineRatio() != null, TenantInfo::getOverDuefineRatio,
						tenantInfoQueryParam.getOverDuefineRatio())
				.eq(tenantInfoQueryParam.getOverDuefineTopRatio() != null, TenantInfo::getOverDuefineTopRatio,
						tenantInfoQueryParam.getOverDuefineTopRatio())
				.eq(tenantInfoQueryParam.getYcdkType() != null, TenantInfo::getYcdkType,
						tenantInfoQueryParam.getYcdkType());

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
	public Object updateStatus(@PathVariable("id") Long id, @PathVariable("status") Integer status) {
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
