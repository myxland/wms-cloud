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
import com.zlsrj.wms.api.dto.TenantSmsQueryParam;
import com.zlsrj.wms.api.entity.TenantInfo;
import com.zlsrj.wms.api.entity.TenantSms;
import com.zlsrj.wms.api.vo.TenantSmsVo;
import com.zlsrj.wms.common.api.CommonResult;
import com.zlsrj.wms.tenant.service.IIdService;
import com.zlsrj.wms.tenant.service.ITenantInfoService;
import com.zlsrj.wms.tenant.service.ITenantSmsService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

@Api(value = "租户短信配置", tags = { "租户短信配置操作接口" })
@RestController
@Slf4j
public class TenantSmsRestController {

	@Autowired
	private ITenantSmsService tenantSmsService;
	@Autowired
	private ITenantInfoService tenantInfoService;
	@Autowired
	private IIdService idService;

	@ApiOperation(value = "根据ID查询租户短信配置")
	@RequestMapping(value = "/tenant-smss/{id}", method = RequestMethod.GET)
	public TenantSmsVo getById(@PathVariable("id") Long id) {
		TenantSms tenantSms = tenantSmsService.getById(id);

		return entity2vo(tenantSms);
	}

	@ApiOperation(value = "根据参数查询租户短信配置列表")
	@RequestMapping(value = "/tenant-smss", method = RequestMethod.GET)
	public Page<TenantSmsVo> page(@RequestBody TenantSmsQueryParam tenantSmsQueryParam,
			@RequestParam(value = "page", defaultValue = "1") int page, //
			@RequestParam(value = "rows", defaultValue = "10") int rows, //
			@RequestParam(value = "sort") String sort, // 排序列字段名
			@RequestParam(value = "order") String order // 可以是 'asc' 或者 'desc'，默认值是 'asc'
	) {
		IPage<TenantSms> pageTenantSms = new Page<TenantSms>(page, rows);
		QueryWrapper<TenantSms> queryWrapperTenantSms = new QueryWrapper<TenantSms>();
		queryWrapperTenantSms.orderBy(StringUtils.isNotEmpty(sort), "desc".equals(order), sort);
		queryWrapperTenantSms.lambda()
				.eq(tenantSmsQueryParam.getId() != null, TenantSms::getId, tenantSmsQueryParam.getId())
				.eq(tenantSmsQueryParam.getTenantId() != null, TenantSms::getTenantId, tenantSmsQueryParam.getTenantId())
				.eq(tenantSmsQueryParam.getSmsSignature() != null, TenantSms::getSmsSignature, tenantSmsQueryParam.getSmsSignature())
				.eq(tenantSmsQueryParam.getSmsSpService() != null, TenantSms::getSmsSpService, tenantSmsQueryParam.getSmsSpService())
				.eq(tenantSmsQueryParam.getSmsReadSendOn() != null, TenantSms::getSmsReadSendOn, tenantSmsQueryParam.getSmsReadSendOn())
				.eq(tenantSmsQueryParam.getSmsChargeSendOn() != null, TenantSms::getSmsChargeSendOn, tenantSmsQueryParam.getSmsChargeSendOn())
				.eq(tenantSmsQueryParam.getSmsQfSendOn() != null, TenantSms::getSmsQfSendOn, tenantSmsQueryParam.getSmsQfSendOn())
				.eq(tenantSmsQueryParam.getSmsQfSendAfterDays() != null, TenantSms::getSmsQfSendAfterDays, tenantSmsQueryParam.getSmsQfSendAfterDays())
				;

		IPage<TenantSms> tenantSmsPage = tenantSmsService.page(pageTenantSms, queryWrapperTenantSms);

		Page<TenantSmsVo> tenantSmsVoPage = new Page<TenantSmsVo>(page, rows);
		tenantSmsVoPage.setCurrent(tenantSmsPage.getCurrent());
		tenantSmsVoPage.setPages(tenantSmsPage.getPages());
		tenantSmsVoPage.setSize(tenantSmsPage.getSize());
		tenantSmsVoPage.setTotal(tenantSmsPage.getTotal());
		tenantSmsVoPage.setRecords(tenantSmsPage.getRecords().stream()//
				.map(e -> entity2vo(e))//
				.collect(Collectors.toList()));

		return tenantSmsVoPage;
	}

	@ApiOperation(value = "新增租户短信配置")
	@RequestMapping(value = "/tenant-smss", method = RequestMethod.POST)
	public TenantSmsVo save(@RequestBody TenantSms tenantSms) {
		if (tenantSms.getId() == null || tenantSms.getId().compareTo(0L) <= 0) {
			tenantSms.setId(idService.selectId());
		}
		boolean success = tenantSmsService.save(tenantSms);
		if (success) {
			TenantSms tenantSmsDatabase = tenantSmsService.getById(tenantSms.getId());
			return entity2vo(tenantSmsDatabase);
		}
		log.info("save TenantSms fail，{}", ToStringBuilder.reflectionToString(tenantSms, ToStringStyle.JSON_STYLE));
		return null;
	}

	@ApiOperation(value = "更新租户短信配置全部信息")
	@RequestMapping(value = "/tenant-smss/{id}", method = RequestMethod.PUT)
	public TenantSmsVo updateById(@PathVariable("id") Long id, @RequestBody TenantSms tenantSms) {
		tenantSms.setId(id);
		boolean success = tenantSmsService.updateById(tenantSms);
		if (success) {
			TenantSms tenantSmsDatabase = tenantSmsService.getById(id);
			return entity2vo(tenantSmsDatabase);
		}
		log.info("update TenantSms fail，{}", ToStringBuilder.reflectionToString(tenantSms, ToStringStyle.JSON_STYLE));
		return null;
	}

	@ApiOperation(value = "根据参数更新租户短信配置信息")
	@RequestMapping(value = "/tenant-smss/{id}", method = RequestMethod.PATCH)
	public TenantSmsVo updatePatchById(@PathVariable("id") Long id, @RequestBody TenantSms tenantSms) {
        TenantSms tenantSmsWhere = TenantSms.builder()//
				.id(id)//
				.build();
		UpdateWrapper<TenantSms> updateWrapperTenantSms = new UpdateWrapper<TenantSms>();
		updateWrapperTenantSms.setEntity(tenantSmsWhere);
		updateWrapperTenantSms.lambda()//
				//.eq(TenantSms::getId, id)
				// .set(tenantSms.getId() != null, TenantSms::getId, tenantSms.getId())
				.set(tenantSms.getTenantId() != null, TenantSms::getTenantId, tenantSms.getTenantId())
				.set(tenantSms.getSmsSignature() != null, TenantSms::getSmsSignature, tenantSms.getSmsSignature())
				.set(tenantSms.getSmsSpService() != null, TenantSms::getSmsSpService, tenantSms.getSmsSpService())
				.set(tenantSms.getSmsReadSendOn() != null, TenantSms::getSmsReadSendOn, tenantSms.getSmsReadSendOn())
				.set(tenantSms.getSmsChargeSendOn() != null, TenantSms::getSmsChargeSendOn, tenantSms.getSmsChargeSendOn())
				.set(tenantSms.getSmsQfSendOn() != null, TenantSms::getSmsQfSendOn, tenantSms.getSmsQfSendOn())
				.set(tenantSms.getSmsQfSendAfterDays() != null, TenantSms::getSmsQfSendAfterDays, tenantSms.getSmsQfSendAfterDays())
				;

		boolean success = tenantSmsService.update(updateWrapperTenantSms);
		if (success) {
			TenantSms tenantSmsDatabase = tenantSmsService.getById(id);
			return entity2vo(tenantSmsDatabase);
		}
		log.info("partial update TenantSms fail，{}",
				ToStringBuilder.reflectionToString(tenantSms, ToStringStyle.JSON_STYLE));
		return null;
	}

	@ApiOperation(value = "根据ID删除租户短信配置")
	@RequestMapping(value = "/tenant-smss/{id}", method = RequestMethod.DELETE)
	public CommonResult<Object> removeById(@PathVariable("id") Long id) {
		boolean success = tenantSmsService.removeById(id);
		return success ? CommonResult.success(success) : CommonResult.failed();
	}

	private TenantSmsVo entity2vo(TenantSms tenantSms) {
		String jsonString = JSON.toJSONString(tenantSms);
		TenantSmsVo tenantSmsVo = JSON.parseObject(jsonString, TenantSmsVo.class);
		if (StringUtils.isEmpty(tenantSmsVo.getTenantName())) {
			TenantInfo tenantInfo = tenantInfoService.getById(tenantSms.getTenantId());
			if (tenantInfo != null) {
				tenantSmsVo.setTenantName(tenantInfo.getTenantName());
			}
		}
		return tenantSmsVo;
	}

}
