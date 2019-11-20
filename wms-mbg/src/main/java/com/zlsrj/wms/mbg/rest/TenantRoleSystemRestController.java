package com.zlsrj.wms.mbg.rest;

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
import com.zlsrj.wms.api.dto.TenantRoleSystemQueryParam;
import com.zlsrj.wms.api.entity.SystemDesign;
import com.zlsrj.wms.api.entity.TenantInfo;
import com.zlsrj.wms.api.entity.TenantRoleSystem;
import com.zlsrj.wms.api.vo.TenantRoleSystemVo;
import com.zlsrj.wms.common.api.CommonResult;
import com.zlsrj.wms.mbg.service.IIdService;
import com.zlsrj.wms.mbg.service.ISystemDesignService;
import com.zlsrj.wms.mbg.service.ITenantInfoService;
import com.zlsrj.wms.mbg.service.ITenantRoleSystemService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

@Api(value = "角色模块", tags = { "角色模块操作接口" })
@RestController
@Slf4j
public class TenantRoleSystemRestController {

	@Autowired
	private ITenantRoleSystemService tenantRoleSystemService;
	@Autowired
	private ISystemDesignService systemDesignService;
	@Autowired
	private ITenantInfoService tenantInfoService;
	@Autowired
	private IIdService idService;

	@ApiOperation(value = "根据ID查询角色模块")
	@RequestMapping(value = "/tenant-role-systems/{id}", method = RequestMethod.GET)
	public TenantRoleSystemVo getById(@PathVariable("id") Long id) {
		TenantRoleSystem tenantRoleSystem = tenantRoleSystemService.getById(id);

		return entity2vo(tenantRoleSystem);
	}

	@ApiOperation(value = "根据参数查询角色模块列表")
	@RequestMapping(value = "/tenant-role-systems", method = RequestMethod.GET)
	public Page<TenantRoleSystemVo> page(@RequestBody TenantRoleSystemQueryParam tenantRoleSystemQueryParam,
			@RequestParam(value = "page", defaultValue = "1") int page, //
			@RequestParam(value = "rows", defaultValue = "10") int rows, //
			@RequestParam(value = "sort") String sort, // 排序列字段名
			@RequestParam(value = "order") String order // 可以是 'asc' 或者 'desc'，默认值是 'asc'
	) {
		IPage<TenantRoleSystem> pageTenantRoleSystem = new Page<TenantRoleSystem>(page, rows);
		QueryWrapper<TenantRoleSystem> queryWrapperTenantRoleSystem = new QueryWrapper<TenantRoleSystem>();
		queryWrapperTenantRoleSystem.orderBy(StringUtils.isNotEmpty(sort), "desc".equals(order), sort);
		queryWrapperTenantRoleSystem.lambda()
						.eq(tenantRoleSystemQueryParam.getId() != null, TenantRoleSystem::getId, tenantRoleSystemQueryParam.getId())
						.eq(tenantRoleSystemQueryParam.getTenantId() != null, TenantRoleSystem::getTenantId, tenantRoleSystemQueryParam.getTenantId())
						.eq(tenantRoleSystemQueryParam.getRoleId() != null, TenantRoleSystem::getRoleId, tenantRoleSystemQueryParam.getRoleId())
						.eq(tenantRoleSystemQueryParam.getSysId() != null, TenantRoleSystem::getSysId, tenantRoleSystemQueryParam.getSysId())
						.eq(tenantRoleSystemQueryParam.getRoleSysOn() != null, TenantRoleSystem::getRoleSysOn, tenantRoleSystemQueryParam.getRoleSysOn())
				;

		IPage<TenantRoleSystem> tenantRoleSystemPage = tenantRoleSystemService.page(pageTenantRoleSystem, queryWrapperTenantRoleSystem);

		Page<TenantRoleSystemVo> tenantRoleSystemVoPage = new Page<TenantRoleSystemVo>(page, rows);
		tenantRoleSystemVoPage.setCurrent(tenantRoleSystemPage.getCurrent());
		tenantRoleSystemVoPage.setPages(tenantRoleSystemPage.getPages());
		tenantRoleSystemVoPage.setSize(tenantRoleSystemPage.getSize());
		tenantRoleSystemVoPage.setTotal(tenantRoleSystemPage.getTotal());
		tenantRoleSystemVoPage.setRecords(tenantRoleSystemPage.getRecords().stream()//
				.map(e -> entity2vo(e))//
				.collect(Collectors.toList()));

		return tenantRoleSystemVoPage;
	}

	@ApiOperation(value = "新增角色模块")
	@RequestMapping(value = "/tenant-role-systems", method = RequestMethod.POST)
	public TenantRoleSystemVo save(@RequestBody TenantRoleSystem tenantRoleSystem) {
		if (tenantRoleSystem.getId() == null || tenantRoleSystem.getId().compareTo(0L) <= 0) {
			tenantRoleSystem.setId(idService.selectId());
		}
		boolean success = tenantRoleSystemService.save(tenantRoleSystem);
		if (success) {
			TenantRoleSystem tenantRoleSystemDatabase = tenantRoleSystemService.getById(tenantRoleSystem.getId());
			return entity2vo(tenantRoleSystemDatabase);
		}
		log.info("save TenantRoleSystem fail，{}", ToStringBuilder.reflectionToString(tenantRoleSystem, ToStringStyle.JSON_STYLE));
		return null;
	}

	@ApiOperation(value = "更新角色模块全部信息")
	@RequestMapping(value = "/tenant-role-systems/{id}", method = RequestMethod.PUT)
	public TenantRoleSystemVo updateById(@PathVariable("id") Long id, @RequestBody TenantRoleSystem tenantRoleSystem) {
		tenantRoleSystem.setId(id);
		boolean success = tenantRoleSystemService.updateById(tenantRoleSystem);
		if (success) {
			TenantRoleSystem tenantRoleSystemDatabase = tenantRoleSystemService.getById(id);
			return entity2vo(tenantRoleSystemDatabase);
		}
		log.info("update TenantRoleSystem fail，{}", ToStringBuilder.reflectionToString(tenantRoleSystem, ToStringStyle.JSON_STYLE));
		return null;
	}

	@ApiOperation(value = "根据参数更新角色模块信息")
	@RequestMapping(value = "/tenant-role-systems/{id}", method = RequestMethod.PATCH)
	public TenantRoleSystemVo updatePatchById(@PathVariable("id") Long id, @RequestBody TenantRoleSystem tenantRoleSystem) {
		UpdateWrapper<TenantRoleSystem> updateWrapperTenantRoleSystem = new UpdateWrapper<TenantRoleSystem>();
		updateWrapperTenantRoleSystem.lambda()//
				.eq(TenantRoleSystem::getId, id)
				// .set(tenantRoleSystem.getId() != null, TenantRoleSystem::getId, tenantRoleSystem.getId())
				.set(tenantRoleSystem.getTenantId() != null, TenantRoleSystem::getTenantId, tenantRoleSystem.getTenantId())
				.set(tenantRoleSystem.getRoleId() != null, TenantRoleSystem::getRoleId, tenantRoleSystem.getRoleId())
				.set(tenantRoleSystem.getSysId() != null, TenantRoleSystem::getSysId, tenantRoleSystem.getSysId())
				.set(tenantRoleSystem.getRoleSysOn() != null, TenantRoleSystem::getRoleSysOn, tenantRoleSystem.getRoleSysOn())
				;

		boolean success = tenantRoleSystemService.update(updateWrapperTenantRoleSystem);
		if (success) {
			TenantRoleSystem tenantRoleSystemDatabase = tenantRoleSystemService.getById(id);
			return entity2vo(tenantRoleSystemDatabase);
		}
		log.info("partial update TenantRoleSystem fail，{}",
				ToStringBuilder.reflectionToString(tenantRoleSystem, ToStringStyle.JSON_STYLE));
		return null;
	}

	@ApiOperation(value = "根据ID删除角色模块")
	@RequestMapping(value = "/tenant-role-systems/{id}", method = RequestMethod.DELETE)
	public CommonResult<Object> removeById(@PathVariable("id") Long id) {
		boolean success = tenantRoleSystemService.removeById(id);
		return success ? CommonResult.success(success) : CommonResult.failed();
	}

	private TenantRoleSystemVo entity2vo(TenantRoleSystem tenantRoleSystem) {
		String jsonString = JSON.toJSONString(tenantRoleSystem);
		TenantRoleSystemVo tenantRoleSystemVo = JSON.parseObject(jsonString, TenantRoleSystemVo.class);
		if (StringUtils.isEmpty(tenantRoleSystemVo.getModuleName())) {
			SystemDesign systemDesign = systemDesignService.getById(tenantRoleSystem.getSysId());
			if (systemDesign != null) {
				tenantRoleSystemVo.setModuleName(systemDesign.getModuleName());
			}
		}
		if (StringUtils.isEmpty(tenantRoleSystemVo.getTenantName())) {
			TenantInfo tenantInfo = tenantInfoService.getById(tenantRoleSystem.getTenantId());
			if (tenantInfo != null) {
				tenantRoleSystemVo.setTenantName(tenantInfo.getTenantName());
			}
		}
		return tenantRoleSystemVo;
	}

}
