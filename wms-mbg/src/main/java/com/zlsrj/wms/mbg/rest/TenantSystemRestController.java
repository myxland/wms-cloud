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
import com.zlsrj.wms.api.dto.TenantSystemQueryParam;
import com.zlsrj.wms.api.entity.SystemDesign;
import com.zlsrj.wms.api.entity.TenantInfo;
import com.zlsrj.wms.api.entity.TenantSystem;
import com.zlsrj.wms.api.vo.TenantSystemVo;
import com.zlsrj.wms.common.api.CommonResult;
import com.zlsrj.wms.mbg.service.IIdService;
import com.zlsrj.wms.mbg.service.ISystemDesignService;
import com.zlsrj.wms.mbg.service.ITenantInfoService;
import com.zlsrj.wms.mbg.service.ITenantSystemService;

import cn.hutool.core.date.DateUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

@Api(value = "租户模块", tags = { "租户模块操作接口" })
@RestController
@Slf4j
public class TenantSystemRestController {

	@Autowired
	private ITenantSystemService tenantSystemService;
	@Autowired
	private ISystemDesignService systemDesignService;
	@Autowired
	private ITenantInfoService tenantInfoService;
	@Autowired
	private IIdService idService;

	@ApiOperation(value = "根据ID查询租户模块")
	@RequestMapping(value = "/tenant-systems/{id}", method = RequestMethod.GET)
	public TenantSystemVo getById(@PathVariable("id") Long id) {
		TenantSystem tenantSystem = tenantSystemService.getById(id);

		return entity2vo(tenantSystem);
	}

	@ApiOperation(value = "根据参数查询租户模块列表")
	@RequestMapping(value = "/tenant-systems", method = RequestMethod.GET)
	public Page<TenantSystemVo> page(@RequestBody TenantSystemQueryParam tenantSystemQueryParam,
			@RequestParam(value = "page", defaultValue = "1") int page, //
			@RequestParam(value = "rows", defaultValue = "10") int rows, //
			@RequestParam(value = "sort") String sort, // 排序列字段名
			@RequestParam(value = "order") String order // 可以是 'asc' 或者 'desc'，默认值是 'asc'
	) {
		IPage<TenantSystem> pageTenantSystem = new Page<TenantSystem>(page, rows);
		QueryWrapper<TenantSystem> queryWrapperTenantSystem = new QueryWrapper<TenantSystem>();
		queryWrapperTenantSystem.orderBy(StringUtils.isNotEmpty(sort), "desc".equals(order), sort);
		queryWrapperTenantSystem.lambda()
						.eq(tenantSystemQueryParam.getId() != null, TenantSystem::getId, tenantSystemQueryParam.getId())
						.eq(tenantSystemQueryParam.getTenantId() != null, TenantSystem::getTenantId, tenantSystemQueryParam.getTenantId())
						.eq(tenantSystemQueryParam.getSysId() != null, TenantSystem::getSysId, tenantSystemQueryParam.getSysId())
						.eq(tenantSystemQueryParam.getSysDispName() != null, TenantSystem::getSysDispName, tenantSystemQueryParam.getSysDispName())
						.eq(tenantSystemQueryParam.getSysOrder() != null, TenantSystem::getSysOrder, tenantSystemQueryParam.getSysOrder())
						.eq(tenantSystemQueryParam.getSysEdition() != null, TenantSystem::getSysEdition, tenantSystemQueryParam.getSysEdition())
						.eq(tenantSystemQueryParam.getSysStatus() != null, TenantSystem::getSysStatus, tenantSystemQueryParam.getSysStatus())
						.eq(tenantSystemQueryParam.getSysPriceType() != null, TenantSystem::getSysPriceType, tenantSystemQueryParam.getSysPriceType())
						.eq(tenantSystemQueryParam.getSysOpenDate() != null, TenantSystem::getSysOpenDate, tenantSystemQueryParam.getSysOpenDate())
						.ge(tenantSystemQueryParam.getSysOpenDateStart() != null, TenantSystem::getSysOpenDate, tenantSystemQueryParam.getSysOpenDateStart() != null?DateUtil.beginOfDay(tenantSystemQueryParam.getSysOpenDateStart()):null)
						.le(tenantSystemQueryParam.getSysOpenDateEnd() != null, TenantSystem::getSysOpenDate, tenantSystemQueryParam.getSysOpenDateEnd() != null?DateUtil.endOfDay(tenantSystemQueryParam.getSysOpenDateEnd()):null)
						.eq(tenantSystemQueryParam.getSysEndDate() != null, TenantSystem::getSysEndDate, tenantSystemQueryParam.getSysEndDate())
						.ge(tenantSystemQueryParam.getSysEndDateStart() != null, TenantSystem::getSysEndDate, tenantSystemQueryParam.getSysEndDateStart() != null?DateUtil.beginOfDay(tenantSystemQueryParam.getSysEndDateStart()):null)
						.le(tenantSystemQueryParam.getSysEndDateEnd() != null, TenantSystem::getSysEndDate, tenantSystemQueryParam.getSysEndDateEnd() != null?DateUtil.endOfDay(tenantSystemQueryParam.getSysEndDateEnd()):null)
						.eq(tenantSystemQueryParam.getSysStartChargeDate() != null, TenantSystem::getSysStartChargeDate, tenantSystemQueryParam.getSysStartChargeDate())
						.ge(tenantSystemQueryParam.getSysStartChargeDateStart() != null, TenantSystem::getSysStartChargeDate, tenantSystemQueryParam.getSysStartChargeDateStart() != null?DateUtil.beginOfDay(tenantSystemQueryParam.getSysStartChargeDateStart()):null)
						.le(tenantSystemQueryParam.getSysStartChargeDateEnd() != null, TenantSystem::getSysStartChargeDate, tenantSystemQueryParam.getSysStartChargeDateEnd() != null?DateUtil.endOfDay(tenantSystemQueryParam.getSysStartChargeDateEnd()):null)
						.eq(tenantSystemQueryParam.getSysArrears() != null, TenantSystem::getSysArrears, tenantSystemQueryParam.getSysArrears())
						.eq(tenantSystemQueryParam.getSysOverdraft() != null, TenantSystem::getSysOverdraft, tenantSystemQueryParam.getSysOverdraft())
				;

		IPage<TenantSystem> tenantSystemPage = tenantSystemService.page(pageTenantSystem, queryWrapperTenantSystem);

		Page<TenantSystemVo> tenantSystemVoPage = new Page<TenantSystemVo>(page, rows);
		tenantSystemVoPage.setCurrent(tenantSystemPage.getCurrent());
		tenantSystemVoPage.setPages(tenantSystemPage.getPages());
		tenantSystemVoPage.setSize(tenantSystemPage.getSize());
		tenantSystemVoPage.setTotal(tenantSystemPage.getTotal());
		tenantSystemVoPage.setRecords(tenantSystemPage.getRecords().stream()//
				.map(e -> entity2vo(e))//
				.collect(Collectors.toList()));

		return tenantSystemVoPage;
	}

	@ApiOperation(value = "新增租户模块")
	@RequestMapping(value = "/tenant-systems", method = RequestMethod.POST)
	public TenantSystemVo save(@RequestBody TenantSystem tenantSystem) {
		if (tenantSystem.getId() == null || tenantSystem.getId().compareTo(0L) <= 0) {
			tenantSystem.setId(idService.selectId());
		}
		boolean success = tenantSystemService.save(tenantSystem);
		if (success) {
			TenantSystem tenantSystemDatabase = tenantSystemService.getById(tenantSystem.getId());
			return entity2vo(tenantSystemDatabase);
		}
		log.info("save TenantSystem fail，{}", ToStringBuilder.reflectionToString(tenantSystem, ToStringStyle.JSON_STYLE));
		return null;
	}

	@ApiOperation(value = "更新租户模块全部信息")
	@RequestMapping(value = "/tenant-systems/{id}", method = RequestMethod.PUT)
	public TenantSystemVo updateById(@PathVariable("id") Long id, @RequestBody TenantSystem tenantSystem) {
		tenantSystem.setId(id);
		boolean success = tenantSystemService.updateById(tenantSystem);
		if (success) {
			TenantSystem tenantSystemDatabase = tenantSystemService.getById(id);
			return entity2vo(tenantSystemDatabase);
		}
		log.info("update TenantSystem fail，{}", ToStringBuilder.reflectionToString(tenantSystem, ToStringStyle.JSON_STYLE));
		return null;
	}

	@ApiOperation(value = "根据参数更新租户模块信息")
	@RequestMapping(value = "/tenant-systems/{id}", method = RequestMethod.PATCH)
	public TenantSystemVo updatePatchById(@PathVariable("id") Long id, @RequestBody TenantSystem tenantSystem) {
		UpdateWrapper<TenantSystem> updateWrapperTenantSystem = new UpdateWrapper<TenantSystem>();
		updateWrapperTenantSystem.lambda()//
				.eq(TenantSystem::getId, id)
				// .set(tenantSystem.getId() != null, TenantSystem::getId, tenantSystem.getId())
				.set(tenantSystem.getTenantId() != null, TenantSystem::getTenantId, tenantSystem.getTenantId())
				.set(tenantSystem.getSysId() != null, TenantSystem::getSysId, tenantSystem.getSysId())
				.set(tenantSystem.getSysDispName() != null, TenantSystem::getSysDispName, tenantSystem.getSysDispName())
				.set(tenantSystem.getSysOrder() != null, TenantSystem::getSysOrder, tenantSystem.getSysOrder())
				.set(tenantSystem.getSysEdition() != null, TenantSystem::getSysEdition, tenantSystem.getSysEdition())
				.set(tenantSystem.getSysStatus() != null, TenantSystem::getSysStatus, tenantSystem.getSysStatus())
				.set(tenantSystem.getSysPriceType() != null, TenantSystem::getSysPriceType, tenantSystem.getSysPriceType())
				.set(tenantSystem.getSysOpenDate() != null, TenantSystem::getSysOpenDate, tenantSystem.getSysOpenDate())
				.set(tenantSystem.getSysEndDate() != null, TenantSystem::getSysEndDate, tenantSystem.getSysEndDate())
				.set(tenantSystem.getSysStartChargeDate() != null, TenantSystem::getSysStartChargeDate, tenantSystem.getSysStartChargeDate())
				.set(tenantSystem.getSysArrears() != null, TenantSystem::getSysArrears, tenantSystem.getSysArrears())
				.set(tenantSystem.getSysOverdraft() != null, TenantSystem::getSysOverdraft, tenantSystem.getSysOverdraft())
				;

		boolean success = tenantSystemService.update(updateWrapperTenantSystem);
		if (success) {
			TenantSystem tenantSystemDatabase = tenantSystemService.getById(id);
			return entity2vo(tenantSystemDatabase);
		}
		log.info("partial update TenantSystem fail，{}",
				ToStringBuilder.reflectionToString(tenantSystem, ToStringStyle.JSON_STYLE));
		return null;
	}

	@ApiOperation(value = "根据ID删除租户模块")
	@RequestMapping(value = "/tenant-systems/{id}", method = RequestMethod.DELETE)
	public CommonResult<Object> removeById(@PathVariable("id") Long id) {
		boolean success = tenantSystemService.removeById(id);
		return success ? CommonResult.success(success) : CommonResult.failed();
	}

	private TenantSystemVo entity2vo(TenantSystem tenantSystem) {
		String jsonString = JSON.toJSONString(tenantSystem);
		TenantSystemVo tenantSystemVo = JSON.parseObject(jsonString, TenantSystemVo.class);
		if (StringUtils.isEmpty(tenantSystemVo.getModuleName())) {
			SystemDesign systemDesign = systemDesignService.getById(tenantSystem.getSysId());
			if (systemDesign != null) {
				tenantSystemVo.setModuleName(systemDesign.getModuleName());
			}
		}
		if (StringUtils.isEmpty(tenantSystemVo.getTenantName())) {
			TenantInfo tenantInfo = tenantInfoService.getById(tenantSystem.getTenantId());
			if (tenantInfo != null) {
				tenantSystemVo.setTenantName(tenantInfo.getTenantName());
			}
		}
		return tenantSystemVo;
	}

}
