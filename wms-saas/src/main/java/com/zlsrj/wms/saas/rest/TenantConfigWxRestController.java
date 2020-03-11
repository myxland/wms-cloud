package com.zlsrj.wms.saas.rest;

import java.util.Date;
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
import com.zlsrj.wms.api.dto.TenantConfigWxQueryParam;
import com.zlsrj.wms.api.entity.TenantInfo;
import com.zlsrj.wms.api.entity.TenantConfigWx;
import com.zlsrj.wms.api.vo.TenantConfigWxVo;
import com.zlsrj.wms.common.api.CommonResult;
import com.zlsrj.wms.saas.service.IIdService;
import com.zlsrj.wms.saas.service.ITenantInfoService;
import com.zlsrj.wms.saas.service.ITenantConfigWxService;

import cn.hutool.core.date.DateUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

@Api(value = "微信参数设置", tags = { "微信参数设置操作接口" })
@RestController
@Slf4j
public class TenantConfigWxRestController {

	@Autowired
	private ITenantConfigWxService tenantConfigWxService;
	@Autowired
	private ITenantInfoService tenantInfoService;
	@Autowired
	private IIdService idService;

	@ApiOperation(value = "根据ID查询微信参数设置")
	@RequestMapping(value = "/tenant-config-wxs/{id}", method = RequestMethod.GET)
	public TenantConfigWxVo getById(@PathVariable("id") String id) {
		TenantConfigWx tenantConfigWx = tenantConfigWxService.getById(id);

		return entity2vo(tenantConfigWx);
	}

	@ApiOperation(value = "根据参数查询微信参数设置列表")
	@RequestMapping(value = "/tenant-config-wxs", method = RequestMethod.GET)
	public Page<TenantConfigWxVo> page(@RequestBody TenantConfigWxQueryParam tenantConfigWxQueryParam,
			@RequestParam(value = "page", defaultValue = "1") int page, //
			@RequestParam(value = "rows", defaultValue = "10") int rows, //
			@RequestParam(value = "sort") String sort, // 排序列字段名
			@RequestParam(value = "order") String order // 可以是 'asc' 或者 'desc'，默认值是 'asc'
	) {
		IPage<TenantConfigWx> pageTenantConfigWx = new Page<TenantConfigWx>(page, rows);
		QueryWrapper<TenantConfigWx> queryWrapperTenantConfigWx = new QueryWrapper<TenantConfigWx>();
		queryWrapperTenantConfigWx.orderBy(StringUtils.isNotEmpty(sort), "asc".equals(order), sort);
		queryWrapperTenantConfigWx.lambda()
				.eq(tenantConfigWxQueryParam.getId() != null, TenantConfigWx::getId, tenantConfigWxQueryParam.getId())
				.eq(tenantConfigWxQueryParam.getTenantId() != null, TenantConfigWx::getTenantId, tenantConfigWxQueryParam.getTenantId())
				.eq(tenantConfigWxQueryParam.getWxAppid() != null, TenantConfigWx::getWxAppid, tenantConfigWxQueryParam.getWxAppid())
				.eq(tenantConfigWxQueryParam.getWxAppsecret() != null, TenantConfigWx::getWxAppsecret, tenantConfigWxQueryParam.getWxAppsecret())
				.eq(tenantConfigWxQueryParam.getWxAccountId() != null, TenantConfigWx::getWxAccountId, tenantConfigWxQueryParam.getWxAccountId())
				.eq(tenantConfigWxQueryParam.getWxAccountApiKey() != null, TenantConfigWx::getWxAccountApiKey, tenantConfigWxQueryParam.getWxAccountApiKey())
				.eq(tenantConfigWxQueryParam.getWxTitlePictureFileName() != null, TenantConfigWx::getWxTitlePictureFileName, tenantConfigWxQueryParam.getWxTitlePictureFileName())
				.eq(tenantConfigWxQueryParam.getWxServiceTel() != null, TenantConfigWx::getWxServiceTel, tenantConfigWxQueryParam.getWxServiceTel())
				.eq(tenantConfigWxQueryParam.getWxReceivableNoticeOn() != null, TenantConfigWx::getWxReceivableNoticeOn, tenantConfigWxQueryParam.getWxReceivableNoticeOn())
				.eq(tenantConfigWxQueryParam.getWxPaymentNoticeOn() != null, TenantConfigWx::getWxPaymentNoticeOn, tenantConfigWxQueryParam.getWxPaymentNoticeOn())
				.eq(tenantConfigWxQueryParam.getWxBalanceMoneyChangeNoticeOn() != null, TenantConfigWx::getWxBalanceMoneyChangeNoticeOn, tenantConfigWxQueryParam.getWxBalanceMoneyChangeNoticeOn())
				.eq(tenantConfigWxQueryParam.getWxArrearsNoticeOn() != null, TenantConfigWx::getWxArrearsNoticeOn, tenantConfigWxQueryParam.getWxArrearsNoticeOn())
				.eq(tenantConfigWxQueryParam.getWxReceivableNoticeTemplate() != null, TenantConfigWx::getWxReceivableNoticeTemplate, tenantConfigWxQueryParam.getWxReceivableNoticeTemplate())
				.eq(tenantConfigWxQueryParam.getWxPaymentNoticeTemplate() != null, TenantConfigWx::getWxPaymentNoticeTemplate, tenantConfigWxQueryParam.getWxPaymentNoticeTemplate())
				.eq(tenantConfigWxQueryParam.getWxBalanceMoneyChangeNoticeTemplate() != null, TenantConfigWx::getWxBalanceMoneyChangeNoticeTemplate, tenantConfigWxQueryParam.getWxBalanceMoneyChangeNoticeTemplate())
				.eq(tenantConfigWxQueryParam.getWxArrearsNoticeTemplate() != null, TenantConfigWx::getWxArrearsNoticeTemplate, tenantConfigWxQueryParam.getWxArrearsNoticeTemplate())
				.eq(tenantConfigWxQueryParam.getWxArrearsDays() != null, TenantConfigWx::getWxArrearsDays, tenantConfigWxQueryParam.getWxArrearsDays())
				.eq(tenantConfigWxQueryParam.getWxArrearsNoticeDay() != null, TenantConfigWx::getWxArrearsNoticeDay, tenantConfigWxQueryParam.getWxArrearsNoticeDay())
				.eq(tenantConfigWxQueryParam.getWxArrearsNoticeStartTime() != null, TenantConfigWx::getWxArrearsNoticeStartTime, tenantConfigWxQueryParam.getWxArrearsNoticeStartTime())
				.ge(tenantConfigWxQueryParam.getWxArrearsNoticeStartTimeStart() != null, TenantConfigWx::getWxArrearsNoticeStartTime,tenantConfigWxQueryParam.getWxArrearsNoticeStartTimeStart() == null ? null: DateUtil.beginOfDay(tenantConfigWxQueryParam.getWxArrearsNoticeStartTimeStart()))
				.le(tenantConfigWxQueryParam.getWxArrearsNoticeStartTimeEnd() != null, TenantConfigWx::getWxArrearsNoticeStartTime,tenantConfigWxQueryParam.getWxArrearsNoticeStartTimeEnd() == null ? null: DateUtil.endOfDay(tenantConfigWxQueryParam.getWxArrearsNoticeStartTimeEnd()))
				.eq(tenantConfigWxQueryParam.getWxArrearsNoticeEndTime() != null, TenantConfigWx::getWxArrearsNoticeEndTime, tenantConfigWxQueryParam.getWxArrearsNoticeEndTime())
				.ge(tenantConfigWxQueryParam.getWxArrearsNoticeEndTimeStart() != null, TenantConfigWx::getWxArrearsNoticeEndTime,tenantConfigWxQueryParam.getWxArrearsNoticeEndTimeStart() == null ? null: DateUtil.beginOfDay(tenantConfigWxQueryParam.getWxArrearsNoticeEndTimeStart()))
				.le(tenantConfigWxQueryParam.getWxArrearsNoticeEndTimeEnd() != null, TenantConfigWx::getWxArrearsNoticeEndTime,tenantConfigWxQueryParam.getWxArrearsNoticeEndTimeEnd() == null ? null: DateUtil.endOfDay(tenantConfigWxQueryParam.getWxArrearsNoticeEndTimeEnd()))
				;

		IPage<TenantConfigWx> tenantConfigWxPage = tenantConfigWxService.page(pageTenantConfigWx, queryWrapperTenantConfigWx);

		Page<TenantConfigWxVo> tenantConfigWxVoPage = new Page<TenantConfigWxVo>(page, rows);
		tenantConfigWxVoPage.setCurrent(tenantConfigWxPage.getCurrent());
		tenantConfigWxVoPage.setPages(tenantConfigWxPage.getPages());
		tenantConfigWxVoPage.setSize(tenantConfigWxPage.getSize());
		tenantConfigWxVoPage.setTotal(tenantConfigWxPage.getTotal());
		tenantConfigWxVoPage.setRecords(tenantConfigWxPage.getRecords().stream()//
				.map(e -> entity2vo(e))//
				.collect(Collectors.toList()));

		return tenantConfigWxVoPage;
	}
	
	@ApiOperation(value = "根据参数查询微信参数设置统计")
	@RequestMapping(value = "/tenant-config-wxs/aggregation", method = RequestMethod.GET)
	public TenantConfigWxVo aggregation(@RequestBody TenantConfigWxQueryParam tenantConfigWxQueryParam) {
		QueryWrapper<TenantConfigWx> queryWrapperTenantConfigWx = new QueryWrapper<TenantConfigWx>();
		queryWrapperTenantConfigWx.lambda()
				.eq(tenantConfigWxQueryParam.getId() != null, TenantConfigWx::getId, tenantConfigWxQueryParam.getId())
				.eq(tenantConfigWxQueryParam.getTenantId() != null, TenantConfigWx::getTenantId, tenantConfigWxQueryParam.getTenantId())
				.eq(tenantConfigWxQueryParam.getWxAppid() != null, TenantConfigWx::getWxAppid, tenantConfigWxQueryParam.getWxAppid())
				.eq(tenantConfigWxQueryParam.getWxAppsecret() != null, TenantConfigWx::getWxAppsecret, tenantConfigWxQueryParam.getWxAppsecret())
				.eq(tenantConfigWxQueryParam.getWxAccountId() != null, TenantConfigWx::getWxAccountId, tenantConfigWxQueryParam.getWxAccountId())
				.eq(tenantConfigWxQueryParam.getWxAccountApiKey() != null, TenantConfigWx::getWxAccountApiKey, tenantConfigWxQueryParam.getWxAccountApiKey())
				.eq(tenantConfigWxQueryParam.getWxTitlePictureFileName() != null, TenantConfigWx::getWxTitlePictureFileName, tenantConfigWxQueryParam.getWxTitlePictureFileName())
				.eq(tenantConfigWxQueryParam.getWxServiceTel() != null, TenantConfigWx::getWxServiceTel, tenantConfigWxQueryParam.getWxServiceTel())
				.eq(tenantConfigWxQueryParam.getWxReceivableNoticeOn() != null, TenantConfigWx::getWxReceivableNoticeOn, tenantConfigWxQueryParam.getWxReceivableNoticeOn())
				.eq(tenantConfigWxQueryParam.getWxPaymentNoticeOn() != null, TenantConfigWx::getWxPaymentNoticeOn, tenantConfigWxQueryParam.getWxPaymentNoticeOn())
				.eq(tenantConfigWxQueryParam.getWxBalanceMoneyChangeNoticeOn() != null, TenantConfigWx::getWxBalanceMoneyChangeNoticeOn, tenantConfigWxQueryParam.getWxBalanceMoneyChangeNoticeOn())
				.eq(tenantConfigWxQueryParam.getWxArrearsNoticeOn() != null, TenantConfigWx::getWxArrearsNoticeOn, tenantConfigWxQueryParam.getWxArrearsNoticeOn())
				.eq(tenantConfigWxQueryParam.getWxReceivableNoticeTemplate() != null, TenantConfigWx::getWxReceivableNoticeTemplate, tenantConfigWxQueryParam.getWxReceivableNoticeTemplate())
				.eq(tenantConfigWxQueryParam.getWxPaymentNoticeTemplate() != null, TenantConfigWx::getWxPaymentNoticeTemplate, tenantConfigWxQueryParam.getWxPaymentNoticeTemplate())
				.eq(tenantConfigWxQueryParam.getWxBalanceMoneyChangeNoticeTemplate() != null, TenantConfigWx::getWxBalanceMoneyChangeNoticeTemplate, tenantConfigWxQueryParam.getWxBalanceMoneyChangeNoticeTemplate())
				.eq(tenantConfigWxQueryParam.getWxArrearsNoticeTemplate() != null, TenantConfigWx::getWxArrearsNoticeTemplate, tenantConfigWxQueryParam.getWxArrearsNoticeTemplate())
				.eq(tenantConfigWxQueryParam.getWxArrearsDays() != null, TenantConfigWx::getWxArrearsDays, tenantConfigWxQueryParam.getWxArrearsDays())
				.eq(tenantConfigWxQueryParam.getWxArrearsNoticeDay() != null, TenantConfigWx::getWxArrearsNoticeDay, tenantConfigWxQueryParam.getWxArrearsNoticeDay())
				.eq(tenantConfigWxQueryParam.getWxArrearsNoticeStartTime() != null, TenantConfigWx::getWxArrearsNoticeStartTime, tenantConfigWxQueryParam.getWxArrearsNoticeStartTime())
				.ge(tenantConfigWxQueryParam.getWxArrearsNoticeStartTimeStart() != null, TenantConfigWx::getWxArrearsNoticeStartTime,tenantConfigWxQueryParam.getWxArrearsNoticeStartTimeStart() == null ? null: DateUtil.beginOfDay(tenantConfigWxQueryParam.getWxArrearsNoticeStartTimeStart()))
				.le(tenantConfigWxQueryParam.getWxArrearsNoticeStartTimeEnd() != null, TenantConfigWx::getWxArrearsNoticeStartTime,tenantConfigWxQueryParam.getWxArrearsNoticeStartTimeEnd() == null ? null: DateUtil.endOfDay(tenantConfigWxQueryParam.getWxArrearsNoticeStartTimeEnd()))
				.eq(tenantConfigWxQueryParam.getWxArrearsNoticeEndTime() != null, TenantConfigWx::getWxArrearsNoticeEndTime, tenantConfigWxQueryParam.getWxArrearsNoticeEndTime())
				.ge(tenantConfigWxQueryParam.getWxArrearsNoticeEndTimeStart() != null, TenantConfigWx::getWxArrearsNoticeEndTime,tenantConfigWxQueryParam.getWxArrearsNoticeEndTimeStart() == null ? null: DateUtil.beginOfDay(tenantConfigWxQueryParam.getWxArrearsNoticeEndTimeStart()))
				.le(tenantConfigWxQueryParam.getWxArrearsNoticeEndTimeEnd() != null, TenantConfigWx::getWxArrearsNoticeEndTime,tenantConfigWxQueryParam.getWxArrearsNoticeEndTimeEnd() == null ? null: DateUtil.endOfDay(tenantConfigWxQueryParam.getWxArrearsNoticeEndTimeEnd()))
				;

		TenantConfigWx tenantConfigWx = tenantConfigWxService.getAggregation(queryWrapperTenantConfigWx);
		
		return entity2vo(tenantConfigWx);
	}

	@ApiOperation(value = "新增微信参数设置")
	@RequestMapping(value = "/tenant-config-wxs", method = RequestMethod.POST)
	public TenantConfigWxVo save(@RequestBody TenantConfigWx tenantConfigWx) {
		if (tenantConfigWx.getId() == null || tenantConfigWx.getId().trim().length() <= 0) {
			tenantConfigWx.setId(idService.selectId());
		}
		boolean success = tenantConfigWxService.save(tenantConfigWx);
		if (success) {
			TenantConfigWx tenantConfigWxDatabase = tenantConfigWxService.getById(tenantConfigWx.getId());
			return entity2vo(tenantConfigWxDatabase);
		}
		log.info("save TenantConfigWx fail，{}", ToStringBuilder.reflectionToString(tenantConfigWx, ToStringStyle.JSON_STYLE));
		return null;
	}

	@ApiOperation(value = "更新微信参数设置全部信息")
	@RequestMapping(value = "/tenant-config-wxs/{id}", method = RequestMethod.PUT)
	public TenantConfigWxVo updateById(@PathVariable("id") String id, @RequestBody TenantConfigWx tenantConfigWx) {
		tenantConfigWx.setId(id);
		boolean success = tenantConfigWxService.updateById(tenantConfigWx);
		if (success) {
			TenantConfigWx tenantConfigWxDatabase = tenantConfigWxService.getById(id);
			return entity2vo(tenantConfigWxDatabase);
		}
		log.info("update TenantConfigWx fail，{}", ToStringBuilder.reflectionToString(tenantConfigWx, ToStringStyle.JSON_STYLE));
		return null;
	}

	@ApiOperation(value = "根据参数更新微信参数设置信息")
	@RequestMapping(value = "/tenant-config-wxs/{id}", method = RequestMethod.PATCH)
	public TenantConfigWxVo updatePatchById(@PathVariable("id") String id, @RequestBody TenantConfigWx tenantConfigWx) {
        TenantConfigWx tenantConfigWxWhere = TenantConfigWx.builder()//
				.id(id)//
				.build();
		UpdateWrapper<TenantConfigWx> updateWrapperTenantConfigWx = new UpdateWrapper<TenantConfigWx>();
		updateWrapperTenantConfigWx.setEntity(tenantConfigWxWhere);
		updateWrapperTenantConfigWx.lambda()//
				//.eq(TenantConfigWx::getId, id)
				// .set(tenantConfigWx.getId() != null, TenantConfigWx::getId, tenantConfigWx.getId())
				.set(tenantConfigWx.getTenantId() != null, TenantConfigWx::getTenantId, tenantConfigWx.getTenantId())
				.set(tenantConfigWx.getWxAppid() != null, TenantConfigWx::getWxAppid, tenantConfigWx.getWxAppid())
				.set(tenantConfigWx.getWxAppsecret() != null, TenantConfigWx::getWxAppsecret, tenantConfigWx.getWxAppsecret())
				.set(tenantConfigWx.getWxAccountId() != null, TenantConfigWx::getWxAccountId, tenantConfigWx.getWxAccountId())
				.set(tenantConfigWx.getWxAccountApiKey() != null, TenantConfigWx::getWxAccountApiKey, tenantConfigWx.getWxAccountApiKey())
				.set(tenantConfigWx.getWxTitlePictureFileName() != null, TenantConfigWx::getWxTitlePictureFileName, tenantConfigWx.getWxTitlePictureFileName())
				.set(tenantConfigWx.getWxServiceTel() != null, TenantConfigWx::getWxServiceTel, tenantConfigWx.getWxServiceTel())
				.set(tenantConfigWx.getWxReceivableNoticeOn() != null, TenantConfigWx::getWxReceivableNoticeOn, tenantConfigWx.getWxReceivableNoticeOn())
				.set(tenantConfigWx.getWxPaymentNoticeOn() != null, TenantConfigWx::getWxPaymentNoticeOn, tenantConfigWx.getWxPaymentNoticeOn())
				.set(tenantConfigWx.getWxBalanceMoneyChangeNoticeOn() != null, TenantConfigWx::getWxBalanceMoneyChangeNoticeOn, tenantConfigWx.getWxBalanceMoneyChangeNoticeOn())
				.set(tenantConfigWx.getWxArrearsNoticeOn() != null, TenantConfigWx::getWxArrearsNoticeOn, tenantConfigWx.getWxArrearsNoticeOn())
				.set(tenantConfigWx.getWxReceivableNoticeTemplate() != null, TenantConfigWx::getWxReceivableNoticeTemplate, tenantConfigWx.getWxReceivableNoticeTemplate())
				.set(tenantConfigWx.getWxPaymentNoticeTemplate() != null, TenantConfigWx::getWxPaymentNoticeTemplate, tenantConfigWx.getWxPaymentNoticeTemplate())
				.set(tenantConfigWx.getWxBalanceMoneyChangeNoticeTemplate() != null, TenantConfigWx::getWxBalanceMoneyChangeNoticeTemplate, tenantConfigWx.getWxBalanceMoneyChangeNoticeTemplate())
				.set(tenantConfigWx.getWxArrearsNoticeTemplate() != null, TenantConfigWx::getWxArrearsNoticeTemplate, tenantConfigWx.getWxArrearsNoticeTemplate())
				.set(tenantConfigWx.getWxArrearsDays() != null, TenantConfigWx::getWxArrearsDays, tenantConfigWx.getWxArrearsDays())
				.set(tenantConfigWx.getWxArrearsNoticeDay() != null, TenantConfigWx::getWxArrearsNoticeDay, tenantConfigWx.getWxArrearsNoticeDay())
				.set(tenantConfigWx.getWxArrearsNoticeStartTime() != null, TenantConfigWx::getWxArrearsNoticeStartTime, tenantConfigWx.getWxArrearsNoticeStartTime())
				.set(tenantConfigWx.getWxArrearsNoticeEndTime() != null, TenantConfigWx::getWxArrearsNoticeEndTime, tenantConfigWx.getWxArrearsNoticeEndTime())
				;

		boolean success = tenantConfigWxService.update(updateWrapperTenantConfigWx);
		if (success) {
			TenantConfigWx tenantConfigWxDatabase = tenantConfigWxService.getById(id);
			return entity2vo(tenantConfigWxDatabase);
		}
		log.info("partial update TenantConfigWx fail，{}",
				ToStringBuilder.reflectionToString(tenantConfigWx, ToStringStyle.JSON_STYLE));
		return null;
	}

	@ApiOperation(value = "根据ID删除微信参数设置")
	@RequestMapping(value = "/tenant-config-wxs/{id}", method = RequestMethod.DELETE)
	public CommonResult<Object> removeById(@PathVariable("id") String id) {
		boolean success = tenantConfigWxService.removeById(id);
		return success ? CommonResult.success(success) : CommonResult.failed();
	}

	private TenantConfigWxVo entity2vo(TenantConfigWx tenantConfigWx) {
		if (tenantConfigWx == null) {
			return null;
		}

		String jsonString = JSON.toJSONString(tenantConfigWx);
		TenantConfigWxVo tenantConfigWxVo = JSON.parseObject(jsonString, TenantConfigWxVo.class);
		if (StringUtils.isEmpty(tenantConfigWxVo.getTenantName())) {
			TenantInfo tenantInfo = tenantInfoService.getById(tenantConfigWx.getTenantId());
			if (tenantInfo != null) {
				tenantConfigWxVo.setTenantName(tenantInfo.getTenantName());
			}
		}
		return tenantConfigWxVo;
	}

}
