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
import com.zlsrj.wms.api.dto.SystemDesignQueryParam;
import com.zlsrj.wms.api.entity.SystemDesign;
import com.zlsrj.wms.common.api.CommonPage;
import com.zlsrj.wms.common.api.CommonResult;
import com.zlsrj.wms.mbg.service.ISystemDesignService;

@RestController
@RequestMapping("/systemDesign")
public class SystemDesignController {

	@Autowired
	private ISystemDesignService systemDesignService;

	@RequestMapping(value = "/select/{id}", method = RequestMethod.GET)
	public Object getById(@PathVariable("id") Long id) {
		SystemDesign systemDesign = systemDesignService.getById(id);

		return CommonResult.success(systemDesign);
	}

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public Object list() {
		List<SystemDesign> systemDesignList = systemDesignService.list();

		return systemDesignList;
	}

	@RequestMapping(value = "/page", method = RequestMethod.GET)
	public Object page(SystemDesignQueryParam systemDesignQueryParam,
			@RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
			@RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize) {

		IPage<SystemDesign> page = new Page<SystemDesign>(pageNum, pageSize);
		QueryWrapper<SystemDesign> queryWrapperSystemDesign = new QueryWrapper<SystemDesign>();
		queryWrapperSystemDesign.lambda()
				.eq(systemDesignQueryParam.getId() != null, SystemDesign::getId, systemDesignQueryParam.getId())
				.eq(systemDesignQueryParam.getRelyId() != null, SystemDesign::getRelyId, systemDesignQueryParam.getRelyId())
				.eq(systemDesignQueryParam.getModuleName() != null, SystemDesign::getModuleName, systemDesignQueryParam.getModuleName())
				.eq(systemDesignQueryParam.getOpenTenantType() != null, SystemDesign::getOpenTenantType, systemDesignQueryParam.getOpenTenantType())
				.eq(systemDesignQueryParam.getRunEnvType() != null, SystemDesign::getRunEnvType, systemDesignQueryParam.getRunEnvType())
				.eq(systemDesignQueryParam.getPricePolicyType() != null, SystemDesign::getPricePolicyType, systemDesignQueryParam.getPricePolicyType())
				.eq(systemDesignQueryParam.getBillCycleType() != null, SystemDesign::getBillCycleType, systemDesignQueryParam.getBillCycleType())
				.eq(systemDesignQueryParam.getBasicOn() != null, SystemDesign::getBasicOn, systemDesignQueryParam.getBasicOn())
				.eq(systemDesignQueryParam.getAdvanceOn() != null, SystemDesign::getAdvanceOn, systemDesignQueryParam.getAdvanceOn())
				.eq(systemDesignQueryParam.getUltimateOn() != null, SystemDesign::getUltimateOn, systemDesignQueryParam.getUltimateOn())
				.eq(systemDesignQueryParam.getModuleReleaseOn() != null, SystemDesign::getModuleReleaseOn, systemDesignQueryParam.getModuleReleaseOn())
				;

		IPage<SystemDesign> systemDesignPage = systemDesignService.page(page, queryWrapperSystemDesign);

		return CommonPage.restPage(systemDesignPage);
	}

	@RequestMapping(value = "/insert", method = RequestMethod.POST)
	public Object insert(@RequestBody SystemDesign systemDesign) {
		boolean success = systemDesignService.save(systemDesign);

		return success ? CommonResult.success(success) : CommonResult.failed();
	}

	@RequestMapping(value = "/update", method = RequestMethod.PUT)
	public Object update(@RequestBody SystemDesign systemDesign) {
		boolean success = systemDesignService.updateById(systemDesign);

		return success ? CommonResult.success(success) : CommonResult.failed();
	}

	@RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
	public Object delete(@PathVariable("id") Long id) {
		boolean success = systemDesignService.removeById(id);
		
		return success ? CommonResult.success(success) : CommonResult.failed();
	}

	@RequestMapping(value = "/delete/ids/{ids}", method = RequestMethod.DELETE)
	public Object deleteByIds(@PathVariable("ids") String ids) {
		List<Long> idList = Arrays.asList(ids.split(",")).stream().map(id -> Long.parseLong(id))
				.collect(Collectors.toList());
		boolean success = systemDesignService.removeByIds(idList);
		
		return success ? CommonResult.success(success) : CommonResult.failed();
	}
	
}
