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
import com.zlsrj.wms.api.dto.TenantPaymentDetailQueryParam;
import com.zlsrj.wms.api.entity.TenantInfo;
import com.zlsrj.wms.api.entity.TenantPaymentDetail;
import com.zlsrj.wms.api.vo.TenantPaymentDetailVo;
import com.zlsrj.wms.common.api.CommonResult;
import com.zlsrj.wms.saas.service.IIdService;
import com.zlsrj.wms.saas.service.ITenantInfoService;
import com.zlsrj.wms.saas.service.ITenantPaymentDetailService;

import cn.hutool.core.date.DateUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

@Api(value = "实收明细账，记录本次所销账的欠费明细情况", tags = { "实收明细账，记录本次所销账的欠费明细情况操作接口" })
@RestController
@Slf4j
public class TenantPaymentDetailRestController {

	@Autowired
	private ITenantPaymentDetailService tenantPaymentDetailService;
	@Autowired
	private ITenantInfoService tenantInfoService;
	@Autowired
	private IIdService idService;

	@ApiOperation(value = "根据ID查询实收明细账，记录本次所销账的欠费明细情况")
	@RequestMapping(value = "/tenant-payment-details/{id}", method = RequestMethod.GET)
	public TenantPaymentDetailVo getById(@PathVariable("id") String id) {
		TenantPaymentDetail tenantPaymentDetail = tenantPaymentDetailService.getById(id);

		return entity2vo(tenantPaymentDetail);
	}

	@ApiOperation(value = "根据参数查询实收明细账，记录本次所销账的欠费明细情况列表")
	@RequestMapping(value = "/tenant-payment-details", method = RequestMethod.GET)
	public Page<TenantPaymentDetailVo> page(@RequestBody TenantPaymentDetailQueryParam tenantPaymentDetailQueryParam,
			@RequestParam(value = "page", defaultValue = "1") int page, //
			@RequestParam(value = "rows", defaultValue = "10") int rows, //
			@RequestParam(value = "sort") String sort, // 排序列字段名
			@RequestParam(value = "order") String order // 可以是 'asc' 或者 'desc'，默认值是 'asc'
	) {
		IPage<TenantPaymentDetail> pageTenantPaymentDetail = new Page<TenantPaymentDetail>(page, rows);
		QueryWrapper<TenantPaymentDetail> queryWrapperTenantPaymentDetail = new QueryWrapper<TenantPaymentDetail>();
		queryWrapperTenantPaymentDetail.orderBy(StringUtils.isNotEmpty(sort), "asc".equals(order), sort);
		queryWrapperTenantPaymentDetail.lambda()
				.eq(tenantPaymentDetailQueryParam.getId() != null, TenantPaymentDetail::getId, tenantPaymentDetailQueryParam.getId())
				.eq(tenantPaymentDetailQueryParam.getTenantId() != null, TenantPaymentDetail::getTenantId, tenantPaymentDetailQueryParam.getTenantId())
				.eq(tenantPaymentDetailQueryParam.getPaymentId() != null, TenantPaymentDetail::getPaymentId, tenantPaymentDetailQueryParam.getPaymentId())
				.eq(tenantPaymentDetailQueryParam.getReceivableTime() != null, TenantPaymentDetail::getReceivableTime, tenantPaymentDetailQueryParam.getReceivableTime())
				.ge(tenantPaymentDetailQueryParam.getReceivableTimeStart() != null, TenantPaymentDetail::getReceivableTime,tenantPaymentDetailQueryParam.getReceivableTimeStart() == null ? null: DateUtil.beginOfDay(tenantPaymentDetailQueryParam.getReceivableTimeStart()))
				.le(tenantPaymentDetailQueryParam.getReceivableTimeEnd() != null, TenantPaymentDetail::getReceivableTime,tenantPaymentDetailQueryParam.getReceivableTimeEnd() == null ? null: DateUtil.endOfDay(tenantPaymentDetailQueryParam.getReceivableTimeEnd()))
				.eq(tenantPaymentDetailQueryParam.getReceivableId() != null, TenantPaymentDetail::getReceivableId, tenantPaymentDetailQueryParam.getReceivableId())
				.eq(tenantPaymentDetailQueryParam.getReceivableDetailId() != null, TenantPaymentDetail::getReceivableDetailId, tenantPaymentDetailQueryParam.getReceivableDetailId())
				.eq(tenantPaymentDetailQueryParam.getStepNo() != null, TenantPaymentDetail::getStepNo, tenantPaymentDetailQueryParam.getStepNo())
				.eq(tenantPaymentDetailQueryParam.getPayWaters() != null, TenantPaymentDetail::getPayWaters, tenantPaymentDetailQueryParam.getPayWaters())
				.eq(tenantPaymentDetailQueryParam.getPriceTypeId() != null, TenantPaymentDetail::getPriceTypeId, tenantPaymentDetailQueryParam.getPriceTypeId())
				.eq(tenantPaymentDetailQueryParam.getPriceItemId() != null, TenantPaymentDetail::getPriceItemId, tenantPaymentDetailQueryParam.getPriceItemId())
				.eq(tenantPaymentDetailQueryParam.getPayPrice() != null, TenantPaymentDetail::getPayPrice, tenantPaymentDetailQueryParam.getPayPrice())
				.eq(tenantPaymentDetailQueryParam.getPayMoney() != null, TenantPaymentDetail::getPayMoney, tenantPaymentDetailQueryParam.getPayMoney())
				;

		IPage<TenantPaymentDetail> tenantPaymentDetailPage = tenantPaymentDetailService.page(pageTenantPaymentDetail, queryWrapperTenantPaymentDetail);

		Page<TenantPaymentDetailVo> tenantPaymentDetailVoPage = new Page<TenantPaymentDetailVo>(page, rows);
		tenantPaymentDetailVoPage.setCurrent(tenantPaymentDetailPage.getCurrent());
		tenantPaymentDetailVoPage.setPages(tenantPaymentDetailPage.getPages());
		tenantPaymentDetailVoPage.setSize(tenantPaymentDetailPage.getSize());
		tenantPaymentDetailVoPage.setTotal(tenantPaymentDetailPage.getTotal());
		tenantPaymentDetailVoPage.setRecords(tenantPaymentDetailPage.getRecords().stream()//
				.map(e -> entity2vo(e))//
				.collect(Collectors.toList()));

		return tenantPaymentDetailVoPage;
	}

	@ApiOperation(value = "新增实收明细账，记录本次所销账的欠费明细情况")
	@RequestMapping(value = "/tenant-payment-details", method = RequestMethod.POST)
	public TenantPaymentDetailVo save(@RequestBody TenantPaymentDetail tenantPaymentDetail) {
		if (tenantPaymentDetail.getId() == null || tenantPaymentDetail.getId().trim().length() <= 0) {
			tenantPaymentDetail.setId(idService.selectId());
		}
		boolean success = tenantPaymentDetailService.save(tenantPaymentDetail);
		if (success) {
			TenantPaymentDetail tenantPaymentDetailDatabase = tenantPaymentDetailService.getById(tenantPaymentDetail.getId());
			return entity2vo(tenantPaymentDetailDatabase);
		}
		log.info("save TenantPaymentDetail fail，{}", ToStringBuilder.reflectionToString(tenantPaymentDetail, ToStringStyle.JSON_STYLE));
		return null;
	}

	@ApiOperation(value = "更新实收明细账，记录本次所销账的欠费明细情况全部信息")
	@RequestMapping(value = "/tenant-payment-details/{id}", method = RequestMethod.PUT)
	public TenantPaymentDetailVo updateById(@PathVariable("id") String id, @RequestBody TenantPaymentDetail tenantPaymentDetail) {
		tenantPaymentDetail.setId(id);
		boolean success = tenantPaymentDetailService.updateById(tenantPaymentDetail);
		if (success) {
			TenantPaymentDetail tenantPaymentDetailDatabase = tenantPaymentDetailService.getById(id);
			return entity2vo(tenantPaymentDetailDatabase);
		}
		log.info("update TenantPaymentDetail fail，{}", ToStringBuilder.reflectionToString(tenantPaymentDetail, ToStringStyle.JSON_STYLE));
		return null;
	}

	@ApiOperation(value = "根据参数更新实收明细账，记录本次所销账的欠费明细情况信息")
	@RequestMapping(value = "/tenant-payment-details/{id}", method = RequestMethod.PATCH)
	public TenantPaymentDetailVo updatePatchById(@PathVariable("id") String id, @RequestBody TenantPaymentDetail tenantPaymentDetail) {
        TenantPaymentDetail tenantPaymentDetailWhere = TenantPaymentDetail.builder()//
				.id(id)//
				.build();
		UpdateWrapper<TenantPaymentDetail> updateWrapperTenantPaymentDetail = new UpdateWrapper<TenantPaymentDetail>();
		updateWrapperTenantPaymentDetail.setEntity(tenantPaymentDetailWhere);
		updateWrapperTenantPaymentDetail.lambda()//
				//.eq(TenantPaymentDetail::getId, id)
				// .set(tenantPaymentDetail.getId() != null, TenantPaymentDetail::getId, tenantPaymentDetail.getId())
				.set(tenantPaymentDetail.getTenantId() != null, TenantPaymentDetail::getTenantId, tenantPaymentDetail.getTenantId())
				.set(tenantPaymentDetail.getPaymentId() != null, TenantPaymentDetail::getPaymentId, tenantPaymentDetail.getPaymentId())
				.set(tenantPaymentDetail.getReceivableTime() != null, TenantPaymentDetail::getReceivableTime, tenantPaymentDetail.getReceivableTime())
				.set(tenantPaymentDetail.getReceivableId() != null, TenantPaymentDetail::getReceivableId, tenantPaymentDetail.getReceivableId())
				.set(tenantPaymentDetail.getReceivableDetailId() != null, TenantPaymentDetail::getReceivableDetailId, tenantPaymentDetail.getReceivableDetailId())
				.set(tenantPaymentDetail.getStepNo() != null, TenantPaymentDetail::getStepNo, tenantPaymentDetail.getStepNo())
				.set(tenantPaymentDetail.getPayWaters() != null, TenantPaymentDetail::getPayWaters, tenantPaymentDetail.getPayWaters())
				.set(tenantPaymentDetail.getPriceTypeId() != null, TenantPaymentDetail::getPriceTypeId, tenantPaymentDetail.getPriceTypeId())
				.set(tenantPaymentDetail.getPriceItemId() != null, TenantPaymentDetail::getPriceItemId, tenantPaymentDetail.getPriceItemId())
				.set(tenantPaymentDetail.getPayPrice() != null, TenantPaymentDetail::getPayPrice, tenantPaymentDetail.getPayPrice())
				.set(tenantPaymentDetail.getPayMoney() != null, TenantPaymentDetail::getPayMoney, tenantPaymentDetail.getPayMoney())
				;

		boolean success = tenantPaymentDetailService.update(updateWrapperTenantPaymentDetail);
		if (success) {
			TenantPaymentDetail tenantPaymentDetailDatabase = tenantPaymentDetailService.getById(id);
			return entity2vo(tenantPaymentDetailDatabase);
		}
		log.info("partial update TenantPaymentDetail fail，{}",
				ToStringBuilder.reflectionToString(tenantPaymentDetail, ToStringStyle.JSON_STYLE));
		return null;
	}

	@ApiOperation(value = "根据ID删除实收明细账，记录本次所销账的欠费明细情况")
	@RequestMapping(value = "/tenant-payment-details/{id}", method = RequestMethod.DELETE)
	public CommonResult<Object> removeById(@PathVariable("id") String id) {
		boolean success = tenantPaymentDetailService.removeById(id);
		return success ? CommonResult.success(success) : CommonResult.failed();
	}

	private TenantPaymentDetailVo entity2vo(TenantPaymentDetail tenantPaymentDetail) {
		if (tenantPaymentDetail == null) {
			return null;
		}

		String jsonString = JSON.toJSONString(tenantPaymentDetail);
		TenantPaymentDetailVo tenantPaymentDetailVo = JSON.parseObject(jsonString, TenantPaymentDetailVo.class);
		if (StringUtils.isEmpty(tenantPaymentDetailVo.getTenantName())) {
			TenantInfo tenantInfo = tenantInfoService.getDictionaryById(tenantPaymentDetail.getTenantId());
			if (tenantInfo != null) {
				tenantPaymentDetailVo.setTenantName(tenantInfo.getTenantName());
			}
		}
		return tenantPaymentDetailVo;
	}

}
