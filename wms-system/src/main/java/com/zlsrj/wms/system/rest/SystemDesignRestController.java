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
import com.zlsrj.wms.api.dto.SystemDesignQueryParam;
import com.zlsrj.wms.api.entity.SystemDesign;
import com.zlsrj.wms.api.vo.SystemDesignVo;
import com.zlsrj.wms.common.api.CommonResult;
import com.zlsrj.wms.system.service.IIdService;
import com.zlsrj.wms.system.service.ISystemDesignService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

@Api(value = "系统定义", tags = { "系统定义操作接口" })
@RestController
@Slf4j
public class SystemDesignRestController {

	@Autowired
	private ISystemDesignService systemDesignService;
	@Autowired
	private IIdService idService;

	@ApiOperation(value = "根据ID查询系统定义")
	@RequestMapping(value = "/system-designs/{id}", method = RequestMethod.GET)
	public SystemDesignVo getById(@PathVariable("id") Long id) {
		SystemDesign systemDesign = systemDesignService.getById(id);

		return entity2vo(systemDesign);
	}

	@ApiOperation(value = "根据参数查询系统定义列表")
	@RequestMapping(value = "/system-designs", method = RequestMethod.GET)
	public Page<SystemDesignVo> page(@RequestBody SystemDesignQueryParam systemDesignQueryParam,
			@RequestParam(value = "page", defaultValue = "1") int page, //
			@RequestParam(value = "rows", defaultValue = "10") int rows, //
			@RequestParam(value = "sort") String sort, // 排序列字段名
			@RequestParam(value = "order") String order // 可以是 'asc' 或者 'desc'，默认值是 'asc'
	) {
		IPage<SystemDesign> pageSystemDesign = new Page<SystemDesign>(page, rows);
		QueryWrapper<SystemDesign> queryWrapperSystemDesign = new QueryWrapper<SystemDesign>();
		queryWrapperSystemDesign.orderBy(StringUtils.isNotEmpty(sort), "desc".equals(order), sort);
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

		IPage<SystemDesign> systemDesignPage = systemDesignService.page(pageSystemDesign, queryWrapperSystemDesign);

		Page<SystemDesignVo> systemDesignVoPage = new Page<SystemDesignVo>(page, rows);
		systemDesignVoPage.setCurrent(systemDesignPage.getCurrent());
		systemDesignVoPage.setPages(systemDesignPage.getPages());
		systemDesignVoPage.setSize(systemDesignPage.getSize());
		systemDesignVoPage.setTotal(systemDesignPage.getTotal());
		systemDesignVoPage.setRecords(systemDesignPage.getRecords().stream()//
				.map(e -> entity2vo(e))//
				.collect(Collectors.toList()));

		return systemDesignVoPage;
	}

	@ApiOperation(value = "新增系统定义")
	@RequestMapping(value = "/system-designs", method = RequestMethod.POST)
	public SystemDesignVo save(@RequestBody SystemDesign systemDesign) {
		if (systemDesign.getId() == null || systemDesign.getId().compareTo(0L) <= 0) {
			systemDesign.setId(idService.selectId());
		}
		boolean success = systemDesignService.save(systemDesign);
		if (success) {
			SystemDesign systemDesignDatabase = systemDesignService.getById(systemDesign.getId());
			return entity2vo(systemDesignDatabase);
		}
		log.info("save SystemDesign fail，{}", ToStringBuilder.reflectionToString(systemDesign, ToStringStyle.JSON_STYLE));
		return null;
	}

	@ApiOperation(value = "更新系统定义全部信息")
	@RequestMapping(value = "/system-designs/{id}", method = RequestMethod.PUT)
	public SystemDesignVo updateById(@PathVariable("id") Long id, @RequestBody SystemDesign systemDesign) {
		systemDesign.setId(id);
		boolean success = systemDesignService.updateById(systemDesign);
		if (success) {
			SystemDesign systemDesignDatabase = systemDesignService.getById(id);
			return entity2vo(systemDesignDatabase);
		}
		log.info("update SystemDesign fail，{}", ToStringBuilder.reflectionToString(systemDesign, ToStringStyle.JSON_STYLE));
		return null;
	}

	@ApiOperation(value = "根据参数更新系统定义信息")
	@RequestMapping(value = "/system-designs/{id}", method = RequestMethod.PATCH)
	public SystemDesignVo updatePatchById(@PathVariable("id") Long id, @RequestBody SystemDesign systemDesign) {
		UpdateWrapper<SystemDesign> updateWrapperSystemDesign = new UpdateWrapper<SystemDesign>();
		updateWrapperSystemDesign.lambda()//
				.eq(SystemDesign::getId, id)
				// .set(systemDesign.getId() != null, SystemDesign::getId, systemDesign.getId())
				.set(systemDesign.getRelyId() != null, SystemDesign::getRelyId, systemDesign.getRelyId())
				.set(systemDesign.getModuleName() != null, SystemDesign::getModuleName, systemDesign.getModuleName())
				.set(systemDesign.getOpenTenantType() != null, SystemDesign::getOpenTenantType, systemDesign.getOpenTenantType())
				.set(systemDesign.getRunEnvType() != null, SystemDesign::getRunEnvType, systemDesign.getRunEnvType())
				.set(systemDesign.getPricePolicyType() != null, SystemDesign::getPricePolicyType, systemDesign.getPricePolicyType())
				.set(systemDesign.getBillCycleType() != null, SystemDesign::getBillCycleType, systemDesign.getBillCycleType())
				.set(systemDesign.getBasicOn() != null, SystemDesign::getBasicOn, systemDesign.getBasicOn())
				.set(systemDesign.getAdvanceOn() != null, SystemDesign::getAdvanceOn, systemDesign.getAdvanceOn())
				.set(systemDesign.getUltimateOn() != null, SystemDesign::getUltimateOn, systemDesign.getUltimateOn())
				.set(systemDesign.getModuleReleaseOn() != null, SystemDesign::getModuleReleaseOn, systemDesign.getModuleReleaseOn())
				;

		boolean success = systemDesignService.update(updateWrapperSystemDesign);
		if (success) {
			SystemDesign systemDesignDatabase = systemDesignService.getById(id);
			return entity2vo(systemDesignDatabase);
		}
		log.info("partial update SystemDesign fail，{}",
				ToStringBuilder.reflectionToString(systemDesign, ToStringStyle.JSON_STYLE));
		return null;
	}

	@ApiOperation(value = "根据ID删除系统定义")
	@RequestMapping(value = "/system-designs/{id}", method = RequestMethod.DELETE)
	public CommonResult<Object> removeById(@PathVariable("id") Long id) {
		boolean success = systemDesignService.removeById(id);
		return success ? CommonResult.success(success) : CommonResult.failed();
	}

	private SystemDesignVo entity2vo(SystemDesign systemDesign) {
		String jsonString = JSON.toJSONString(systemDesign);
		SystemDesignVo systemDesignVo = JSON.parseObject(jsonString, SystemDesignVo.class);
		return systemDesignVo;
	}

}
