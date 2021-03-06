package com.zlsrj.wms.tenant.rest;

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
import com.zlsrj.wms.api.dto.TenantModuleQueryParam;
import com.zlsrj.wms.api.entity.TenantInfo;
import com.zlsrj.wms.api.entity.TenantModule;
import com.zlsrj.wms.api.entity.TenantModuleBatch;
import com.zlsrj.wms.api.vo.TenantModuleVo;
import com.zlsrj.wms.common.api.CommonResult;
import com.zlsrj.wms.tenant.service.IIdService;
import com.zlsrj.wms.tenant.service.ITenantInfoService;
import com.zlsrj.wms.tenant.service.ITenantModuleService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

@Api(value = "租户模块", tags = { "租户模块操作接口" })
@RestController
@Slf4j
public class TenantModuleRestController {

	@Autowired
	private ITenantModuleService tenantModuleService;
	@Autowired
	private ITenantInfoService tenantInfoService;
	@Autowired
	private IIdService idService;

	@ApiOperation(value = "根据ID查询租户模块")
	@RequestMapping(value = "/tenant-modules/{id}", method = RequestMethod.GET)
	public TenantModuleVo getById(@PathVariable("id") Long id) {
		TenantModule tenantModule = tenantModuleService.getById(id);

		return entity2vo(tenantModule);
	}

	@ApiOperation(value = "根据参数查询租户模块列表")
	@RequestMapping(value = "/tenant-modules", method = RequestMethod.GET)
	public Page<TenantModuleVo> page(@RequestBody TenantModuleQueryParam tenantModuleQueryParam,
			@RequestParam(value = "page", defaultValue = "1") int page, //
			@RequestParam(value = "rows", defaultValue = "10") int rows, //
			@RequestParam(value = "sort") String sort, // 排序列字段名
			@RequestParam(value = "order") String order // 可以是 'asc' 或者 'desc'，默认值是 'asc'
	) {
		IPage<TenantModule> pageTenantModule = new Page<TenantModule>(page, rows);
		QueryWrapper<TenantModule> queryWrapperTenantModule = new QueryWrapper<TenantModule>();
		queryWrapperTenantModule.orderBy(StringUtils.isNotEmpty(sort), "desc".equals(order), sort);
		queryWrapperTenantModule.lambda()
				.eq(tenantModuleQueryParam.getId() != null, TenantModule::getId, tenantModuleQueryParam.getId())
				.eq(tenantModuleQueryParam.getTenantId() != null, TenantModule::getTenantId, tenantModuleQueryParam.getTenantId())
				.eq(tenantModuleQueryParam.getModuleId() != null, TenantModule::getModuleId, tenantModuleQueryParam.getModuleId())
				;

		IPage<TenantModule> tenantModulePage = tenantModuleService.page(pageTenantModule, queryWrapperTenantModule);

		Page<TenantModuleVo> tenantModuleVoPage = new Page<TenantModuleVo>(page, rows);
		tenantModuleVoPage.setCurrent(tenantModulePage.getCurrent());
		tenantModuleVoPage.setPages(tenantModulePage.getPages());
		tenantModuleVoPage.setSize(tenantModulePage.getSize());
		tenantModuleVoPage.setTotal(tenantModulePage.getTotal());
		tenantModuleVoPage.setRecords(tenantModulePage.getRecords().stream()//
				.map(e -> entity2vo(e))//
				.collect(Collectors.toList()));

		return tenantModuleVoPage;
	}

	@ApiOperation(value = "新增租户模块")
	@RequestMapping(value = "/tenant-modules", method = RequestMethod.POST)
	public TenantModuleVo save(@RequestBody TenantModule tenantModule) {
		if (tenantModule.getId() == null || tenantModule.getId().compareTo(0L) <= 0) {
			tenantModule.setId(idService.selectId());
		}
		boolean success = tenantModuleService.save(tenantModule);
		if (success) {
			TenantModule tenantModuleDatabase = tenantModuleService.getById(tenantModule.getId());
			return entity2vo(tenantModuleDatabase);
		}
		log.info("save TenantModule fail，{}", ToStringBuilder.reflectionToString(tenantModule, ToStringStyle.JSON_STYLE));
		return null;
	}
	
	@ApiOperation(value = "根据租户批量新增租户模块")
	@RequestMapping(value = "/tenant-modules/batch/tenant/{tenantId}", method = RequestMethod.POST)
	public CommonResult<Object> saveBatchByTenantId(@PathVariable("tenantId") Long tenantId,@RequestBody TenantModuleBatch tenantModuleBatch) {
		boolean success = tenantModuleService.saveBatchTenantModuleByTenantId(tenantId,tenantModuleBatch);
		return success ? CommonResult.success(success) : CommonResult.failed();
	}
	
	@ApiOperation(value = "根据模块批量新增租户模块")
	@RequestMapping(value = "/tenant-modules/batch/module/{moduleId}", method = RequestMethod.POST)
	public CommonResult<Object> saveBatchByModuleId(@PathVariable("moduleId") Long moduleId,@RequestBody TenantModuleBatch tenantModuleBatch) {
		boolean success = tenantModuleService.saveBatchTenantModuleByModuleId(moduleId,tenantModuleBatch);
		return success ? CommonResult.success(success) : CommonResult.failed();
	}

	@ApiOperation(value = "更新租户模块全部信息")
	@RequestMapping(value = "/tenant-modules/{id}", method = RequestMethod.PUT)
	public TenantModuleVo updateById(@PathVariable("id") Long id, @RequestBody TenantModule tenantModule) {
		tenantModule.setId(id);
		boolean success = tenantModuleService.updateById(tenantModule);
		if (success) {
			TenantModule tenantModuleDatabase = tenantModuleService.getById(id);
			return entity2vo(tenantModuleDatabase);
		}
		log.info("update TenantModule fail，{}", ToStringBuilder.reflectionToString(tenantModule, ToStringStyle.JSON_STYLE));
		return null;
	}

	@ApiOperation(value = "根据参数更新租户模块信息")
	@RequestMapping(value = "/tenant-modules/{id}", method = RequestMethod.PATCH)
	public TenantModuleVo updatePatchById(@PathVariable("id") Long id, @RequestBody TenantModule tenantModule) {
        TenantModule tenantModuleWhere = TenantModule.builder()//
				.id(id)//
				.build();
		UpdateWrapper<TenantModule> updateWrapperTenantModule = new UpdateWrapper<TenantModule>();
		updateWrapperTenantModule.setEntity(tenantModuleWhere);
		updateWrapperTenantModule.lambda()//
				//.eq(TenantModule::getId, id)
				// .set(tenantModule.getId() != null, TenantModule::getId, tenantModule.getId())
				.set(tenantModule.getTenantId() != null, TenantModule::getTenantId, tenantModule.getTenantId())
				.set(tenantModule.getModuleId() != null, TenantModule::getModuleId, tenantModule.getModuleId())
				;

		boolean success = tenantModuleService.update(updateWrapperTenantModule);
		if (success) {
			TenantModule tenantModuleDatabase = tenantModuleService.getById(id);
			return entity2vo(tenantModuleDatabase);
		}
		log.info("partial update TenantModule fail，{}",
				ToStringBuilder.reflectionToString(tenantModule, ToStringStyle.JSON_STYLE));
		return null;
	}

	@ApiOperation(value = "根据ID删除租户模块")
	@RequestMapping(value = "/tenant-modules/{id}", method = RequestMethod.DELETE)
	public CommonResult<Object> removeById(@PathVariable("id") Long id) {
		boolean success = tenantModuleService.removeById(id);
		return success ? CommonResult.success(success) : CommonResult.failed();
	}

	private TenantModuleVo entity2vo(TenantModule tenantModule) {
		if (tenantModule == null) {
			return null;
		}

		String jsonString = JSON.toJSONString(tenantModule);
		TenantModuleVo tenantModuleVo = JSON.parseObject(jsonString, TenantModuleVo.class);
		if (StringUtils.isEmpty(tenantModuleVo.getTenantName())) {
			TenantInfo tenantInfo = tenantInfoService.getById(tenantModule.getTenantId());
			if (tenantInfo != null) {
				tenantModuleVo.setTenantName(tenantInfo.getTenantName());
			}
		}
		return tenantModuleVo;
	}

}
