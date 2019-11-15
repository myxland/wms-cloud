package com.zlsrj.wms.system.rest;

import java.util.stream.Collectors;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zlsrj.wms.api.dto.SystemMenuDesignQueryParam;
import com.zlsrj.wms.api.entity.SystemDesign;
import com.zlsrj.wms.api.entity.SystemMenuDesign;
import com.zlsrj.wms.api.vo.SystemMenuDesignVo;
import com.zlsrj.wms.common.api.CommonResult;
import com.zlsrj.wms.system.service.IIdService;
import com.zlsrj.wms.system.service.ISystemDesignService;
import com.zlsrj.wms.system.service.ISystemMenuDesignService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

@Api(value = "模块菜单", tags = { "模块菜单操作接口" })
@RestController
@Slf4j
public class SystemMenuDesignRestController {

	@Autowired
	private ISystemMenuDesignService systemMenuDesignService;
	@Autowired
	private ISystemDesignService systemDesignService;
	@Autowired
	private IIdService idService;

	@ApiOperation(value = "根据ID查询模块菜单")
	@RequestMapping(value = "/system-menu-designs/{id}", method = RequestMethod.GET)
	public SystemMenuDesignVo getById(@PathVariable("id") Long id) {
		SystemMenuDesign systemMenuDesign = systemMenuDesignService.getById(id);

		return entity2vo(systemMenuDesign);
	}

	@ApiOperation(value = "根据参数查询模块菜单列表")
	@RequestMapping(value = "/system-menu-designs", method = RequestMethod.GET)
	public Page<SystemMenuDesignVo> page(@RequestBody SystemMenuDesignQueryParam systemMenuDesignQueryParam,
			@RequestParam(value = "page", defaultValue = "1") int page, //
			@RequestParam(value = "rows", defaultValue = "10") int rows, //
			@RequestParam(value = "sort") String sort, // 排序列字段名
			@RequestParam(value = "order") String order // 可以是 'asc' 或者 'desc'，默认值是 'asc'
	) {
		IPage<SystemMenuDesign> pageSystemMenuDesign = new Page<SystemMenuDesign>(page, rows);
		QueryWrapper<SystemMenuDesign> queryWrapperSystemMenuDesign = new QueryWrapper<SystemMenuDesign>();
		queryWrapperSystemMenuDesign.orderBy(StringUtils.isNotEmpty(sort), "desc".equals(order), sort);
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

		IPage<SystemMenuDesign> systemMenuDesignPage = systemMenuDesignService.page(pageSystemMenuDesign, queryWrapperSystemMenuDesign);

		Page<SystemMenuDesignVo> systemMenuDesignVoPage = new Page<SystemMenuDesignVo>(page, rows);
		systemMenuDesignVoPage.setCurrent(systemMenuDesignPage.getCurrent());
		systemMenuDesignVoPage.setPages(systemMenuDesignPage.getPages());
		systemMenuDesignVoPage.setSize(systemMenuDesignPage.getSize());
		systemMenuDesignVoPage.setTotal(systemMenuDesignPage.getTotal());
		systemMenuDesignVoPage.setRecords(systemMenuDesignPage.getRecords().stream()//
				.map(e -> entity2vo(e))//
				.collect(Collectors.toList()));

		return systemMenuDesignVoPage;
	}

	@ApiOperation(value = "新增模块菜单")
	@RequestMapping(value = "/system-menu-designs", method = RequestMethod.POST)
	public SystemMenuDesignVo save(@RequestBody SystemMenuDesign systemMenuDesign) {
		if (systemMenuDesign.getId() == null || systemMenuDesign.getId().compareTo(0L) <= 0) {
			systemMenuDesign.setId(idService.selectId());
		}
		boolean success = systemMenuDesignService.save(systemMenuDesign);
		if (success) {
			SystemMenuDesign systemMenuDesignDatabase = systemMenuDesignService.getById(systemMenuDesign.getId());
			return entity2vo(systemMenuDesignDatabase);
		}
		log.info("save SystemMenuDesign fail，{}", ToStringBuilder.reflectionToString(systemMenuDesign, ToStringStyle.JSON_STYLE));
		return null;
	}

	@ApiOperation(value = "更新模块菜单全部信息")
	@RequestMapping(value = "/system-menu-designs/{id}", method = RequestMethod.PUT)
	public SystemMenuDesignVo updateById(@PathVariable("id") Long id, @RequestBody SystemMenuDesign systemMenuDesign) {
		systemMenuDesign.setId(id);
		boolean success = systemMenuDesignService.updateById(systemMenuDesign);
		if (success) {
			SystemMenuDesign systemMenuDesignDatabase = systemMenuDesignService.getById(id);
			return entity2vo(systemMenuDesignDatabase);
		}
		log.info("update SystemMenuDesign fail，{}", ToStringBuilder.reflectionToString(systemMenuDesign, ToStringStyle.JSON_STYLE));
		return null;
	}

	@ApiOperation(value = "根据参数更新模块菜单信息")
	@RequestMapping(value = "/system-menu-designs/{id}", method = RequestMethod.PATCH)
	public SystemMenuDesignVo updatePatchById(@PathVariable("id") Long id, @RequestBody SystemMenuDesign systemMenuDesign) {
		UpdateWrapper<SystemMenuDesign> updateWrapperSystemMenuDesign = new UpdateWrapper<SystemMenuDesign>();
		updateWrapperSystemMenuDesign.lambda()//
				.eq(SystemMenuDesign::getId, id)
				// .set(systemMenuDesign.getId() != null, SystemMenuDesign::getId, systemMenuDesign.getId())
				.set(systemMenuDesign.getParentMenuId() != null, SystemMenuDesign::getParentMenuId, systemMenuDesign.getParentMenuId())
				.set(systemMenuDesign.getSysId() != null, SystemMenuDesign::getSysId, systemMenuDesign.getSysId())
				.set(systemMenuDesign.getMenuName() != null, SystemMenuDesign::getMenuName, systemMenuDesign.getMenuName())
				.set(systemMenuDesign.getMenuOrder() != null, SystemMenuDesign::getMenuOrder, systemMenuDesign.getMenuOrder())
				.set(systemMenuDesign.getMenuIcon() != null, SystemMenuDesign::getMenuIcon, systemMenuDesign.getMenuIcon())
				.set(systemMenuDesign.getBasicOn() != null, SystemMenuDesign::getBasicOn, systemMenuDesign.getBasicOn())
				.set(systemMenuDesign.getAdvanceOn() != null, SystemMenuDesign::getAdvanceOn, systemMenuDesign.getAdvanceOn())
				.set(systemMenuDesign.getUltimateOn() != null, SystemMenuDesign::getUltimateOn, systemMenuDesign.getUltimateOn())
				.set(systemMenuDesign.getBasicUrl() != null, SystemMenuDesign::getBasicUrl, systemMenuDesign.getBasicUrl())
				.set(systemMenuDesign.getAdvanceUrl() != null, SystemMenuDesign::getAdvanceUrl, systemMenuDesign.getAdvanceUrl())
				.set(systemMenuDesign.getUltimateUrl() != null, SystemMenuDesign::getUltimateUrl, systemMenuDesign.getUltimateUrl())
				;

		boolean success = systemMenuDesignService.update(updateWrapperSystemMenuDesign);
		if (success) {
			SystemMenuDesign systemMenuDesignDatabase = systemMenuDesignService.getById(id);
			return entity2vo(systemMenuDesignDatabase);
		}
		log.info("partial update SystemMenuDesign fail，{}",
				ToStringBuilder.reflectionToString(systemMenuDesign, ToStringStyle.JSON_STYLE));
		return null;
	}

	@ApiOperation(value = "根据ID删除模块菜单")
	@RequestMapping(value = "/system-menu-designs/{id}", method = RequestMethod.DELETE)
	public CommonResult<Object> removeById(@PathVariable("id") Long id) {
		boolean success = systemMenuDesignService.removeById(id);
		return success ? CommonResult.success(success) : CommonResult.failed();
	}

	private SystemMenuDesignVo entity2vo(SystemMenuDesign systemMenuDesign) {
		String jsonString = JSON.toJSONString(systemMenuDesign);
		SystemMenuDesignVo systemMenuDesignVo = JSON.parseObject(jsonString, SystemMenuDesignVo.class);
		if (StringUtils.isEmpty(systemMenuDesignVo.getModuleName())) {
			SystemDesign systemDesign = systemDesignService.getById(systemMenuDesign.getSysId());
			if (systemDesign != null) {
				systemMenuDesignVo.setModuleName(systemDesign.getModuleName());
			}
		}
		return systemMenuDesignVo;
	}

}
