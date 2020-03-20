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
import com.zlsrj.wms.api.dto.TenantConfigSmsQueryParam;
import com.zlsrj.wms.api.entity.TenantInfo;
import com.zlsrj.wms.api.entity.TenantConfigSms;
import com.zlsrj.wms.api.vo.TenantConfigSmsVo;
import com.zlsrj.wms.common.api.CommonResult;
import com.zlsrj.wms.saas.service.IIdService;
import com.zlsrj.wms.saas.service.ITenantInfoService;
import com.zlsrj.wms.saas.service.ITenantConfigSmsService;

import cn.hutool.core.date.DateUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

@Api(value = "短信参数设置", tags = { "短信参数设置操作接口" })
@RestController
@Slf4j
public class TenantConfigSmsRestController {

	@Autowired
	private ITenantConfigSmsService tenantConfigSmsService;
	@Autowired
	private ITenantInfoService tenantInfoService;
	@Autowired
	private IIdService idService;

	@ApiOperation(value = "根据ID查询短信参数设置")
	@RequestMapping(value = "/tenant-config-smss/{id}", method = RequestMethod.GET)
	public TenantConfigSmsVo getById(@PathVariable("id") String id) {
		TenantConfigSms tenantConfigSms = tenantConfigSmsService.getById(id);

		return entity2vo(tenantConfigSms);
	}

	@ApiOperation(value = "根据参数查询短信参数设置列表")
	@RequestMapping(value = "/tenant-config-smss", method = RequestMethod.GET)
	public Page<TenantConfigSmsVo> page(@RequestBody TenantConfigSmsQueryParam tenantConfigSmsQueryParam,
			@RequestParam(value = "page", defaultValue = "1") int page, //
			@RequestParam(value = "rows", defaultValue = "10") int rows, //
			@RequestParam(value = "sort") String sort, // 排序列字段名
			@RequestParam(value = "order") String order // 可以是 'asc' 或者 'desc'，默认值是 'asc'
	) {
		IPage<TenantConfigSms> pageTenantConfigSms = new Page<TenantConfigSms>(page, rows);
		QueryWrapper<TenantConfigSms> queryWrapperTenantConfigSms = new QueryWrapper<TenantConfigSms>();
		queryWrapperTenantConfigSms.orderBy(StringUtils.isNotEmpty(sort), "asc".equals(order), sort);
		queryWrapperTenantConfigSms.lambda()
				.eq(tenantConfigSmsQueryParam.getId() != null, TenantConfigSms::getId, tenantConfigSmsQueryParam.getId())
				.eq(tenantConfigSmsQueryParam.getTenantId() != null, TenantConfigSms::getTenantId, tenantConfigSmsQueryParam.getTenantId())
				.eq(tenantConfigSmsQueryParam.getSmsSignature() != null, TenantConfigSms::getSmsSignature, tenantConfigSmsQueryParam.getSmsSignature())
				.eq(tenantConfigSmsQueryParam.getSmsReceivableNoticeOn() != null, TenantConfigSms::getSmsReceivableNoticeOn, tenantConfigSmsQueryParam.getSmsReceivableNoticeOn())
				.eq(tenantConfigSmsQueryParam.getSmsPaymentNoticeOn() != null, TenantConfigSms::getSmsPaymentNoticeOn, tenantConfigSmsQueryParam.getSmsPaymentNoticeOn())
				.eq(tenantConfigSmsQueryParam.getSmsBalanceMoneyChangeNoticeOn() != null, TenantConfigSms::getSmsBalanceMoneyChangeNoticeOn, tenantConfigSmsQueryParam.getSmsBalanceMoneyChangeNoticeOn())
				.eq(tenantConfigSmsQueryParam.getSmsArrearsNoticeOn() != null, TenantConfigSms::getSmsArrearsNoticeOn, tenantConfigSmsQueryParam.getSmsArrearsNoticeOn())
				.eq(tenantConfigSmsQueryParam.getSmsReceivableNoticeTemplate() != null, TenantConfigSms::getSmsReceivableNoticeTemplate, tenantConfigSmsQueryParam.getSmsReceivableNoticeTemplate())
				.eq(tenantConfigSmsQueryParam.getSmsPaymentNoticeTemplate() != null, TenantConfigSms::getSmsPaymentNoticeTemplate, tenantConfigSmsQueryParam.getSmsPaymentNoticeTemplate())
				.eq(tenantConfigSmsQueryParam.getSmsBalanceMoneyChangeNoticeTemplate() != null, TenantConfigSms::getSmsBalanceMoneyChangeNoticeTemplate, tenantConfigSmsQueryParam.getSmsBalanceMoneyChangeNoticeTemplate())
				.eq(tenantConfigSmsQueryParam.getSmsArrearsNoticeTemplate() != null, TenantConfigSms::getSmsArrearsNoticeTemplate, tenantConfigSmsQueryParam.getSmsArrearsNoticeTemplate())
				.eq(tenantConfigSmsQueryParam.getSmsArrearsDays() != null, TenantConfigSms::getSmsArrearsDays, tenantConfigSmsQueryParam.getSmsArrearsDays())
				.eq(tenantConfigSmsQueryParam.getSmsArrearsNoticeDay() != null, TenantConfigSms::getSmsArrearsNoticeDay, tenantConfigSmsQueryParam.getSmsArrearsNoticeDay())
				.eq(tenantConfigSmsQueryParam.getSmsArrearsNoticeStartTime() != null, TenantConfigSms::getSmsArrearsNoticeStartTime, tenantConfigSmsQueryParam.getSmsArrearsNoticeStartTime())
				.ge(tenantConfigSmsQueryParam.getSmsArrearsNoticeStartTimeStart() != null, TenantConfigSms::getSmsArrearsNoticeStartTime,tenantConfigSmsQueryParam.getSmsArrearsNoticeStartTimeStart() == null ? null: DateUtil.beginOfDay(tenantConfigSmsQueryParam.getSmsArrearsNoticeStartTimeStart()))
				.le(tenantConfigSmsQueryParam.getSmsArrearsNoticeStartTimeEnd() != null, TenantConfigSms::getSmsArrearsNoticeStartTime,tenantConfigSmsQueryParam.getSmsArrearsNoticeStartTimeEnd() == null ? null: DateUtil.endOfDay(tenantConfigSmsQueryParam.getSmsArrearsNoticeStartTimeEnd()))
				.eq(tenantConfigSmsQueryParam.getSmsArrearsNoticeEndTime() != null, TenantConfigSms::getSmsArrearsNoticeEndTime, tenantConfigSmsQueryParam.getSmsArrearsNoticeEndTime())
				.ge(tenantConfigSmsQueryParam.getSmsArrearsNoticeEndTimeStart() != null, TenantConfigSms::getSmsArrearsNoticeEndTime,tenantConfigSmsQueryParam.getSmsArrearsNoticeEndTimeStart() == null ? null: DateUtil.beginOfDay(tenantConfigSmsQueryParam.getSmsArrearsNoticeEndTimeStart()))
				.le(tenantConfigSmsQueryParam.getSmsArrearsNoticeEndTimeEnd() != null, TenantConfigSms::getSmsArrearsNoticeEndTime,tenantConfigSmsQueryParam.getSmsArrearsNoticeEndTimeEnd() == null ? null: DateUtil.endOfDay(tenantConfigSmsQueryParam.getSmsArrearsNoticeEndTimeEnd()))
				;

		IPage<TenantConfigSms> tenantConfigSmsPage = tenantConfigSmsService.page(pageTenantConfigSms, queryWrapperTenantConfigSms);

		Page<TenantConfigSmsVo> tenantConfigSmsVoPage = new Page<TenantConfigSmsVo>(page, rows);
		tenantConfigSmsVoPage.setCurrent(tenantConfigSmsPage.getCurrent());
		tenantConfigSmsVoPage.setPages(tenantConfigSmsPage.getPages());
		tenantConfigSmsVoPage.setSize(tenantConfigSmsPage.getSize());
		tenantConfigSmsVoPage.setTotal(tenantConfigSmsPage.getTotal());
		tenantConfigSmsVoPage.setRecords(tenantConfigSmsPage.getRecords().stream()//
				.map(e -> entity2vo(e))//
				.collect(Collectors.toList()));

		return tenantConfigSmsVoPage;
	}

	@ApiOperation(value = "新增短信参数设置")
	@RequestMapping(value = "/tenant-config-smss", method = RequestMethod.POST)
	public TenantConfigSmsVo save(@RequestBody TenantConfigSms tenantConfigSms) {
		if (tenantConfigSms.getId() == null || tenantConfigSms.getId().trim().length() <= 0) {
			tenantConfigSms.setId(idService.selectId());
		}
		boolean success = tenantConfigSmsService.save(tenantConfigSms);
		if (success) {
			TenantConfigSms tenantConfigSmsDatabase = tenantConfigSmsService.getById(tenantConfigSms.getId());
			return entity2vo(tenantConfigSmsDatabase);
		}
		log.info("save TenantConfigSms fail，{}", ToStringBuilder.reflectionToString(tenantConfigSms, ToStringStyle.JSON_STYLE));
		return null;
	}

	@ApiOperation(value = "更新短信参数设置全部信息")
	@RequestMapping(value = "/tenant-config-smss/{id}", method = RequestMethod.PUT)
	public TenantConfigSmsVo updateById(@PathVariable("id") String id, @RequestBody TenantConfigSms tenantConfigSms) {
		tenantConfigSms.setId(id);
		boolean success = tenantConfigSmsService.updateById(tenantConfigSms);
		if (success) {
			TenantConfigSms tenantConfigSmsDatabase = tenantConfigSmsService.getById(id);
			return entity2vo(tenantConfigSmsDatabase);
		}
		log.info("update TenantConfigSms fail，{}", ToStringBuilder.reflectionToString(tenantConfigSms, ToStringStyle.JSON_STYLE));
		return null;
	}

	@ApiOperation(value = "根据参数更新短信参数设置信息")
	@RequestMapping(value = "/tenant-config-smss/{id}", method = RequestMethod.PATCH)
	public TenantConfigSmsVo updatePatchById(@PathVariable("id") String id, @RequestBody TenantConfigSms tenantConfigSms) {
        TenantConfigSms tenantConfigSmsWhere = TenantConfigSms.builder()//
				.id(id)//
				.build();
		UpdateWrapper<TenantConfigSms> updateWrapperTenantConfigSms = new UpdateWrapper<TenantConfigSms>();
		updateWrapperTenantConfigSms.setEntity(tenantConfigSmsWhere);
		updateWrapperTenantConfigSms.lambda()//
				//.eq(TenantConfigSms::getId, id)
				// .set(tenantConfigSms.getId() != null, TenantConfigSms::getId, tenantConfigSms.getId())
				.set(tenantConfigSms.getTenantId() != null, TenantConfigSms::getTenantId, tenantConfigSms.getTenantId())
				.set(tenantConfigSms.getSmsSignature() != null, TenantConfigSms::getSmsSignature, tenantConfigSms.getSmsSignature())
				.set(tenantConfigSms.getSmsReceivableNoticeOn() != null, TenantConfigSms::getSmsReceivableNoticeOn, tenantConfigSms.getSmsReceivableNoticeOn())
				.set(tenantConfigSms.getSmsPaymentNoticeOn() != null, TenantConfigSms::getSmsPaymentNoticeOn, tenantConfigSms.getSmsPaymentNoticeOn())
				.set(tenantConfigSms.getSmsBalanceMoneyChangeNoticeOn() != null, TenantConfigSms::getSmsBalanceMoneyChangeNoticeOn, tenantConfigSms.getSmsBalanceMoneyChangeNoticeOn())
				.set(tenantConfigSms.getSmsArrearsNoticeOn() != null, TenantConfigSms::getSmsArrearsNoticeOn, tenantConfigSms.getSmsArrearsNoticeOn())
				.set(tenantConfigSms.getSmsReceivableNoticeTemplate() != null, TenantConfigSms::getSmsReceivableNoticeTemplate, tenantConfigSms.getSmsReceivableNoticeTemplate())
				.set(tenantConfigSms.getSmsPaymentNoticeTemplate() != null, TenantConfigSms::getSmsPaymentNoticeTemplate, tenantConfigSms.getSmsPaymentNoticeTemplate())
				.set(tenantConfigSms.getSmsBalanceMoneyChangeNoticeTemplate() != null, TenantConfigSms::getSmsBalanceMoneyChangeNoticeTemplate, tenantConfigSms.getSmsBalanceMoneyChangeNoticeTemplate())
				.set(tenantConfigSms.getSmsArrearsNoticeTemplate() != null, TenantConfigSms::getSmsArrearsNoticeTemplate, tenantConfigSms.getSmsArrearsNoticeTemplate())
				.set(tenantConfigSms.getSmsArrearsDays() != null, TenantConfigSms::getSmsArrearsDays, tenantConfigSms.getSmsArrearsDays())
				.set(tenantConfigSms.getSmsArrearsNoticeDay() != null, TenantConfigSms::getSmsArrearsNoticeDay, tenantConfigSms.getSmsArrearsNoticeDay())
				.set(tenantConfigSms.getSmsArrearsNoticeStartTime() != null, TenantConfigSms::getSmsArrearsNoticeStartTime, tenantConfigSms.getSmsArrearsNoticeStartTime())
				.set(tenantConfigSms.getSmsArrearsNoticeEndTime() != null, TenantConfigSms::getSmsArrearsNoticeEndTime, tenantConfigSms.getSmsArrearsNoticeEndTime())
				;

		boolean success = tenantConfigSmsService.update(updateWrapperTenantConfigSms);
		if (success) {
			TenantConfigSms tenantConfigSmsDatabase = tenantConfigSmsService.getById(id);
			return entity2vo(tenantConfigSmsDatabase);
		}
		log.info("partial update TenantConfigSms fail，{}",
				ToStringBuilder.reflectionToString(tenantConfigSms, ToStringStyle.JSON_STYLE));
		return null;
	}

	@ApiOperation(value = "根据ID删除短信参数设置")
	@RequestMapping(value = "/tenant-config-smss/{id}", method = RequestMethod.DELETE)
	public CommonResult<Object> removeById(@PathVariable("id") String id) {
		boolean success = tenantConfigSmsService.removeById(id);
		return success ? CommonResult.success(success) : CommonResult.failed();
	}

	private TenantConfigSmsVo entity2vo(TenantConfigSms tenantConfigSms) {
		if (tenantConfigSms == null) {
			return null;
		}

		String jsonString = JSON.toJSONString(tenantConfigSms);
		TenantConfigSmsVo tenantConfigSmsVo = JSON.parseObject(jsonString, TenantConfigSmsVo.class);
		if (StringUtils.isEmpty(tenantConfigSmsVo.getTenantName())) {
			TenantInfo tenantInfo = tenantInfoService.getDictionaryById(tenantConfigSms.getTenantId());
			if (tenantInfo != null) {
				tenantConfigSmsVo.setTenantName(tenantInfo.getTenantName());
			}
		}
		return tenantConfigSmsVo;
	}

}
