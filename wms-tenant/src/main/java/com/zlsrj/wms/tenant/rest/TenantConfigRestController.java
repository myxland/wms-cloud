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
import com.zlsrj.wms.api.dto.TenantConfigQueryParam;
import com.zlsrj.wms.api.entity.TenantInfo;
import com.zlsrj.wms.api.entity.TenantConfig;
import com.zlsrj.wms.api.vo.TenantConfigVo;
import com.zlsrj.wms.common.api.CommonResult;
import com.zlsrj.wms.tenant.service.IIdService;
import com.zlsrj.wms.tenant.service.ITenantInfoService;
import com.zlsrj.wms.tenant.service.ITenantConfigService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

@Api(value = "租户基础配置", tags = { "租户基础配置操作接口" })
@RestController
@Slf4j
public class TenantConfigRestController {

	@Autowired
	private ITenantConfigService tenantConfigService;
	@Autowired
	private ITenantInfoService tenantInfoService;
	@Autowired
	private IIdService idService;

	@ApiOperation(value = "根据ID查询租户基础配置")
	@RequestMapping(value = "/tenant-configs/{id}", method = RequestMethod.GET)
	public TenantConfigVo getById(@PathVariable("id") Long id) {
		TenantConfig tenantConfig = tenantConfigService.getById(id);

		return entity2vo(tenantConfig);
	}

	@ApiOperation(value = "根据ID查询租户基础配置")
	@RequestMapping(value = "/tenant-configs/tenant-id/{tenant-id}", method = RequestMethod.GET)
	public TenantConfigVo getByTenantId(@PathVariable("tenant-id") Long tenantId) {
		QueryWrapper<TenantConfig> queryWrapperTenantConfig = new QueryWrapper<TenantConfig>();
		queryWrapperTenantConfig.lambda().eq(TenantConfig::getTenantId, tenantId);
		TenantConfig tenantConfig = tenantConfigService.getOne(queryWrapperTenantConfig);

		return entity2vo(tenantConfig);
	}

	@ApiOperation(value = "根据参数查询租户基础配置列表")
	@RequestMapping(value = "/tenant-configs", method = RequestMethod.GET)
	public Page<TenantConfigVo> page(@RequestBody TenantConfigQueryParam tenantConfigQueryParam,
			@RequestParam(value = "page", defaultValue = "1") int page, //
			@RequestParam(value = "rows", defaultValue = "10") int rows, //
			@RequestParam(value = "sort") String sort, // 排序列字段名
			@RequestParam(value = "order") String order // 可以是 'asc' 或者 'desc'，默认值是 'asc'
	) {
		IPage<TenantConfig> pageTenantConfig = new Page<TenantConfig>(page, rows);
		QueryWrapper<TenantConfig> queryWrapperTenantConfig = new QueryWrapper<TenantConfig>();
		queryWrapperTenantConfig.orderBy(StringUtils.isNotEmpty(sort), "desc".equals(order), sort);
		queryWrapperTenantConfig.lambda()
				.eq(tenantConfigQueryParam.getId() != null, TenantConfig::getId, tenantConfigQueryParam.getId())
				.eq(tenantConfigQueryParam.getTenantId() != null, TenantConfig::getTenantId, tenantConfigQueryParam.getTenantId())
				.eq(tenantConfigQueryParam.getPartChargeOn() != null, TenantConfig::getPartChargeOn, tenantConfigQueryParam.getPartChargeOn())
				.eq(tenantConfigQueryParam.getOverDuefineOn() != null, TenantConfig::getOverDuefineOn, tenantConfigQueryParam.getOverDuefineOn())
				.eq(tenantConfigQueryParam.getOverDuefineDay() != null, TenantConfig::getOverDuefineDay, tenantConfigQueryParam.getOverDuefineDay())
				.eq(tenantConfigQueryParam.getOverDuefineRatio() != null, TenantConfig::getOverDuefineRatio, tenantConfigQueryParam.getOverDuefineRatio())
				.eq(tenantConfigQueryParam.getOverDuefineTopRatio() != null, TenantConfig::getOverDuefineTopRatio, tenantConfigQueryParam.getOverDuefineTopRatio())
				.eq(tenantConfigQueryParam.getYcdkType() != null, TenantConfig::getYcdkType, tenantConfigQueryParam.getYcdkType())
				;

		IPage<TenantConfig> tenantConfigPage = tenantConfigService.page(pageTenantConfig, queryWrapperTenantConfig);

		Page<TenantConfigVo> tenantConfigVoPage = new Page<TenantConfigVo>(page, rows);
		tenantConfigVoPage.setCurrent(tenantConfigPage.getCurrent());
		tenantConfigVoPage.setPages(tenantConfigPage.getPages());
		tenantConfigVoPage.setSize(tenantConfigPage.getSize());
		tenantConfigVoPage.setTotal(tenantConfigPage.getTotal());
		tenantConfigVoPage.setRecords(tenantConfigPage.getRecords().stream()//
				.map(e -> entity2vo(e))//
				.collect(Collectors.toList()));

		return tenantConfigVoPage;
	}

	@ApiOperation(value = "新增租户基础配置")
	@RequestMapping(value = "/tenant-configs", method = RequestMethod.POST)
	public TenantConfigVo save(@RequestBody TenantConfig tenantConfig) {
		if (tenantConfig.getId() == null || tenantConfig.getId().compareTo(0L) <= 0) {
			tenantConfig.setId(idService.selectId());
		}
		boolean success = tenantConfigService.save(tenantConfig);
		if (success) {
			TenantConfig tenantConfigDatabase = tenantConfigService.getById(tenantConfig.getId());
			return entity2vo(tenantConfigDatabase);
		}
		log.info("save TenantConfig fail，{}", ToStringBuilder.reflectionToString(tenantConfig, ToStringStyle.JSON_STYLE));
		return null;
	}

	@ApiOperation(value = "更新租户基础配置全部信息")
	@RequestMapping(value = "/tenant-configs/{id}", method = RequestMethod.PUT)
	public TenantConfigVo updateById(@PathVariable("id") Long id, @RequestBody TenantConfig tenantConfig) {
		tenantConfig.setId(id);
		boolean success = tenantConfigService.updateById(tenantConfig);
		if (success) {
			TenantConfig tenantConfigDatabase = tenantConfigService.getById(id);
			return entity2vo(tenantConfigDatabase);
		}
		log.info("update TenantConfig fail，{}", ToStringBuilder.reflectionToString(tenantConfig, ToStringStyle.JSON_STYLE));
		return null;
	}

	@ApiOperation(value = "根据参数更新租户基础配置信息")
	@RequestMapping(value = "/tenant-configs/{id}", method = RequestMethod.PATCH)
	public TenantConfigVo updatePatchById(@PathVariable("id") Long id, @RequestBody TenantConfig tenantConfig) {
        TenantConfig tenantConfigWhere = TenantConfig.builder()//
				.id(id)//
				.build();
		UpdateWrapper<TenantConfig> updateWrapperTenantConfig = new UpdateWrapper<TenantConfig>();
		updateWrapperTenantConfig.setEntity(tenantConfigWhere);
		updateWrapperTenantConfig.lambda()//
				//.eq(TenantConfig::getId, id)
				// .set(tenantConfig.getId() != null, TenantConfig::getId, tenantConfig.getId())
				.set(tenantConfig.getTenantId() != null, TenantConfig::getTenantId, tenantConfig.getTenantId())
				.set(tenantConfig.getPartChargeOn() != null, TenantConfig::getPartChargeOn, tenantConfig.getPartChargeOn())
				.set(tenantConfig.getOverDuefineOn() != null, TenantConfig::getOverDuefineOn, tenantConfig.getOverDuefineOn())
				.set(tenantConfig.getOverDuefineDay() != null, TenantConfig::getOverDuefineDay, tenantConfig.getOverDuefineDay())
				.set(tenantConfig.getOverDuefineRatio() != null, TenantConfig::getOverDuefineRatio, tenantConfig.getOverDuefineRatio())
				.set(tenantConfig.getOverDuefineTopRatio() != null, TenantConfig::getOverDuefineTopRatio, tenantConfig.getOverDuefineTopRatio())
				.set(tenantConfig.getYcdkType() != null, TenantConfig::getYcdkType, tenantConfig.getYcdkType())
				;

		boolean success = tenantConfigService.update(updateWrapperTenantConfig);
		if (success) {
			TenantConfig tenantConfigDatabase = tenantConfigService.getById(id);
			return entity2vo(tenantConfigDatabase);
		}
		log.info("partial update TenantConfig fail，{}",
				ToStringBuilder.reflectionToString(tenantConfig, ToStringStyle.JSON_STYLE));
		return null;
	}

	@ApiOperation(value = "根据ID删除租户基础配置")
	@RequestMapping(value = "/tenant-configs/{id}", method = RequestMethod.DELETE)
	public CommonResult<Object> removeById(@PathVariable("id") Long id) {
		boolean success = tenantConfigService.removeById(id);
		return success ? CommonResult.success(success) : CommonResult.failed();
	}

	private TenantConfigVo entity2vo(TenantConfig tenantConfig) {
		if (tenantConfig == null) {
			return null;
		}

		String jsonString = JSON.toJSONString(tenantConfig);
		TenantConfigVo tenantConfigVo = JSON.parseObject(jsonString, TenantConfigVo.class);
		if (StringUtils.isEmpty(tenantConfigVo.getTenantName())) {
			TenantInfo tenantInfo = tenantInfoService.getById(tenantConfig.getTenantId());
			if (tenantInfo != null) {
				tenantConfigVo.setTenantName(tenantInfo.getTenantName());
			}
		}
		return tenantConfigVo;
	}

}
