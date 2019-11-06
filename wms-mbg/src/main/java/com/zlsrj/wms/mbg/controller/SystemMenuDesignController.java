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
import com.zlsrj.wms.api.dto.SystemMenuDesignQueryParam;
import com.zlsrj.wms.api.entity.SystemMenuDesign;
import com.zlsrj.wms.common.api.CommonPage;
import com.zlsrj.wms.common.api.CommonResult;
import com.zlsrj.wms.mbg.service.ISystemMenuDesignService;

@RestController
@RequestMapping("/systemMenuDesign")
public class SystemMenuDesignController {

	@Autowired
	private ISystemMenuDesignService systemMenuDesignService;

	@RequestMapping(value = "/select/{id}", method = RequestMethod.GET)
	public Object getById(@PathVariable("id") Long id) {
		SystemMenuDesign systemMenuDesign = systemMenuDesignService.getById(id);

		return CommonResult.success(systemMenuDesign);
	}

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public Object list() {
		List<SystemMenuDesign> systemMenuDesignList = systemMenuDesignService.list();

		return systemMenuDesignList;
	}

	@RequestMapping(value = "/page", method = RequestMethod.GET)
	public Object page(SystemMenuDesignQueryParam systemMenuDesignQueryParam,
			@RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
			@RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize) {

		IPage<SystemMenuDesign> page = new Page<SystemMenuDesign>(pageNum, pageSize);
		QueryWrapper<SystemMenuDesign> queryWrapperSystemMenuDesign = new QueryWrapper<SystemMenuDesign>();
		queryWrapperSystemMenuDesign.lambda()
				.eq(systemMenuDesignQueryParam.getId() != null, SystemMenuDesign::getId, systemMenuDesignQueryParam.getId())
				.eq(systemMenuDesignQueryParam.getParentMenuId() != null, SystemMenuDesign::getParentMenuId, systemMenuDesignQueryParam.getParentMenuId())
				.eq(systemMenuDesignQueryParam.getSysId() != null, SystemMenuDesign::getSysId, systemMenuDesignQueryParam.getSysId())
				.eq(systemMenuDesignQueryParam.getMenuName() != null, SystemMenuDesign::getMenuName, systemMenuDesignQueryParam.getMenuName())
				.eq(systemMenuDesignQueryParam.getMenuOrder() != null, SystemMenuDesign::getMenuOrder, systemMenuDesignQueryParam.getMenuOrder())
				.eq(systemMenuDesignQueryParam.getMenuIcon() != null, SystemMenuDesign::getMenuIcon, systemMenuDesignQueryParam.getMenuIcon())
				.eq(systemMenuDesignQueryParam.getBasicOn() != null, SystemMenuDesign::getBasicOn, systemMenuDesignQueryParam.getBasicOn())
				.eq(systemMenuDesignQueryParam.getAdvanceOn() != null, SystemMenuDesign::getAdvanceOn, systemMenuDesignQueryParam.getAdvanceOn())
				.eq(systemMenuDesignQueryParam.getUltimateOn() != null, SystemMenuDesign::getUltimateOn, systemMenuDesignQueryParam.getUltimateOn())
				.eq(systemMenuDesignQueryParam.getBasicUrl() != null, SystemMenuDesign::getBasicUrl, systemMenuDesignQueryParam.getBasicUrl())
				.eq(systemMenuDesignQueryParam.getAdvanceUrl() != null, SystemMenuDesign::getAdvanceUrl, systemMenuDesignQueryParam.getAdvanceUrl())
				.eq(systemMenuDesignQueryParam.getUltimateUrl() != null, SystemMenuDesign::getUltimateUrl, systemMenuDesignQueryParam.getUltimateUrl())
				;

		IPage<SystemMenuDesign> systemMenuDesignPage = systemMenuDesignService.page(page, queryWrapperSystemMenuDesign);

		return CommonPage.restPage(systemMenuDesignPage);
	}

	@RequestMapping(value = "/insert", method = RequestMethod.POST)
	public Object insert(@RequestBody SystemMenuDesign systemMenuDesign) {
		boolean success = systemMenuDesignService.save(systemMenuDesign);

		return success ? CommonResult.success(success) : CommonResult.failed();
	}

	@RequestMapping(value = "/update", method = RequestMethod.PUT)
	public Object update(@RequestBody SystemMenuDesign systemMenuDesign) {
		boolean success = systemMenuDesignService.updateById(systemMenuDesign);

		return success ? CommonResult.success(success) : CommonResult.failed();
	}

	@RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
	public Object delete(@PathVariable("id") Long id) {
		boolean success = systemMenuDesignService.removeById(id);
		
		return success ? CommonResult.success(success) : CommonResult.failed();
	}

	@RequestMapping(value = "/delete/ids/{ids}", method = RequestMethod.DELETE)
	public Object deleteByIds(@PathVariable("ids") String ids) {
		List<Long> idList = Arrays.asList(ids.split(",")).stream().map(id -> Long.parseLong(id))
				.collect(Collectors.toList());
		boolean success = systemMenuDesignService.removeByIds(idList);
		
		return success ? CommonResult.success(success) : CommonResult.failed();
	}
	
}
