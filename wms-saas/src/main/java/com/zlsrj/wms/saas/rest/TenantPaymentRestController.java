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
import com.zlsrj.wms.api.dto.TenantPaymentQueryParam;
import com.zlsrj.wms.api.entity.TenantInfo;
import com.zlsrj.wms.api.entity.TenantPayment;
import com.zlsrj.wms.api.vo.TenantPaymentVo;
import com.zlsrj.wms.common.api.CommonResult;
import com.zlsrj.wms.saas.service.IIdService;
import com.zlsrj.wms.saas.service.ITenantInfoService;
import com.zlsrj.wms.saas.service.ITenantPaymentService;

import cn.hutool.core.date.DateUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

@Api(value = "实收总账，记录每次缴费的总信息", tags = { "实收总账，记录每次缴费的总信息操作接口" })
@RestController
@Slf4j
public class TenantPaymentRestController {

	@Autowired
	private ITenantPaymentService tenantPaymentService;
	@Autowired
	private ITenantInfoService tenantInfoService;
	@Autowired
	private IIdService idService;

	@ApiOperation(value = "根据ID查询实收总账，记录每次缴费的总信息")
	@RequestMapping(value = "/tenant-payments/{id}", method = RequestMethod.GET)
	public TenantPaymentVo getById(@PathVariable("id") String id) {
		TenantPayment tenantPayment = tenantPaymentService.getById(id);

		return entity2vo(tenantPayment);
	}

	@ApiOperation(value = "根据参数查询实收总账，记录每次缴费的总信息列表")
	@RequestMapping(value = "/tenant-payments", method = RequestMethod.GET)
	public Page<TenantPaymentVo> page(@RequestBody TenantPaymentQueryParam tenantPaymentQueryParam,
			@RequestParam(value = "page", defaultValue = "1") int page, //
			@RequestParam(value = "rows", defaultValue = "10") int rows, //
			@RequestParam(value = "sort") String sort, // 排序列字段名
			@RequestParam(value = "order") String order // 可以是 'asc' 或者 'desc'，默认值是 'asc'
	) {
		IPage<TenantPayment> pageTenantPayment = new Page<TenantPayment>(page, rows);
		QueryWrapper<TenantPayment> queryWrapperTenantPayment = new QueryWrapper<TenantPayment>();
		queryWrapperTenantPayment.orderBy(StringUtils.isNotEmpty(sort), "asc".equals(order), sort);
		queryWrapperTenantPayment.lambda()
				.eq(tenantPaymentQueryParam.getId() != null, TenantPayment::getId, tenantPaymentQueryParam.getId())
				.eq(tenantPaymentQueryParam.getTenantId() != null, TenantPayment::getTenantId, tenantPaymentQueryParam.getTenantId())
				.eq(tenantPaymentQueryParam.getOutTransno() != null, TenantPayment::getOutTransno, tenantPaymentQueryParam.getOutTransno())
				.eq(tenantPaymentQueryParam.getInTransno() != null, TenantPayment::getInTransno, tenantPaymentQueryParam.getInTransno())
				.eq(tenantPaymentQueryParam.getPayTime() != null, TenantPayment::getPayTime, tenantPaymentQueryParam.getPayTime())
				.ge(tenantPaymentQueryParam.getPayTimeStart() != null, TenantPayment::getPayTime,tenantPaymentQueryParam.getPayTimeStart() == null ? null: DateUtil.beginOfDay(tenantPaymentQueryParam.getPayTimeStart()))
				.le(tenantPaymentQueryParam.getPayTimeEnd() != null, TenantPayment::getPayTime,tenantPaymentQueryParam.getPayTimeEnd() == null ? null: DateUtil.endOfDay(tenantPaymentQueryParam.getPayTimeEnd()))
				.eq(tenantPaymentQueryParam.getPaymentStatus() != null, TenantPayment::getPaymentStatus, tenantPaymentQueryParam.getPaymentStatus())
				.eq(tenantPaymentQueryParam.getCustomerId() != null, TenantPayment::getCustomerId, tenantPaymentQueryParam.getCustomerId())
				.eq(tenantPaymentQueryParam.getChargeDepartmentId() != null, TenantPayment::getChargeDepartmentId, tenantPaymentQueryParam.getChargeDepartmentId())
				.eq(tenantPaymentQueryParam.getChargeEmployeeId() != null, TenantPayment::getChargeEmployeeId, tenantPaymentQueryParam.getChargeEmployeeId())
				.eq(tenantPaymentQueryParam.getPayChannels() != null, TenantPayment::getPayChannels, tenantPaymentQueryParam.getPayChannels())
				.eq(tenantPaymentQueryParam.getPayMethod() != null, TenantPayment::getPayMethod, tenantPaymentQueryParam.getPayMethod())
				.eq(tenantPaymentQueryParam.getCustomerBalanceMoneyBefore() != null, TenantPayment::getCustomerBalanceMoneyBefore, tenantPaymentQueryParam.getCustomerBalanceMoneyBefore())
				.eq(tenantPaymentQueryParam.getCustomerPayMoney() != null, TenantPayment::getCustomerPayMoney, tenantPaymentQueryParam.getCustomerPayMoney())
				.eq(tenantPaymentQueryParam.getCustomerBalanceMoneyHappen() != null, TenantPayment::getCustomerBalanceMoneyHappen, tenantPaymentQueryParam.getCustomerBalanceMoneyHappen())
				.eq(tenantPaymentQueryParam.getPayTheArrearsMoney() != null, TenantPayment::getPayTheArrearsMoney, tenantPaymentQueryParam.getPayTheArrearsMoney())
				.eq(tenantPaymentQueryParam.getPayTheLateFeeMoney() != null, TenantPayment::getPayTheLateFeeMoney, tenantPaymentQueryParam.getPayTheLateFeeMoney())
				.eq(tenantPaymentQueryParam.getCustomerBalanceMoneyAfter() != null, TenantPayment::getCustomerBalanceMoneyAfter, tenantPaymentQueryParam.getCustomerBalanceMoneyAfter())
				;

		IPage<TenantPayment> tenantPaymentPage = tenantPaymentService.page(pageTenantPayment, queryWrapperTenantPayment);

		Page<TenantPaymentVo> tenantPaymentVoPage = new Page<TenantPaymentVo>(page, rows);
		tenantPaymentVoPage.setCurrent(tenantPaymentPage.getCurrent());
		tenantPaymentVoPage.setPages(tenantPaymentPage.getPages());
		tenantPaymentVoPage.setSize(tenantPaymentPage.getSize());
		tenantPaymentVoPage.setTotal(tenantPaymentPage.getTotal());
		tenantPaymentVoPage.setRecords(tenantPaymentPage.getRecords().stream()//
				.map(e -> entity2vo(e))//
				.collect(Collectors.toList()));

		return tenantPaymentVoPage;
	}
	
	@ApiOperation(value = "根据参数查询实收总账，记录每次缴费的总信息统计")
	@RequestMapping(value = "/tenant-payments/aggregation", method = RequestMethod.GET)
	public TenantPaymentVo aggregation(@RequestBody TenantPaymentQueryParam tenantPaymentQueryParam) {
		QueryWrapper<TenantPayment> queryWrapperTenantPayment = new QueryWrapper<TenantPayment>();
		queryWrapperTenantPayment.lambda()
				.eq(tenantPaymentQueryParam.getId() != null, TenantPayment::getId, tenantPaymentQueryParam.getId())
				.eq(tenantPaymentQueryParam.getTenantId() != null, TenantPayment::getTenantId, tenantPaymentQueryParam.getTenantId())
				.eq(tenantPaymentQueryParam.getOutTransno() != null, TenantPayment::getOutTransno, tenantPaymentQueryParam.getOutTransno())
				.eq(tenantPaymentQueryParam.getInTransno() != null, TenantPayment::getInTransno, tenantPaymentQueryParam.getInTransno())
				.eq(tenantPaymentQueryParam.getPayTime() != null, TenantPayment::getPayTime, tenantPaymentQueryParam.getPayTime())
				.ge(tenantPaymentQueryParam.getPayTimeStart() != null, TenantPayment::getPayTime,tenantPaymentQueryParam.getPayTimeStart() == null ? null: DateUtil.beginOfDay(tenantPaymentQueryParam.getPayTimeStart()))
				.le(tenantPaymentQueryParam.getPayTimeEnd() != null, TenantPayment::getPayTime,tenantPaymentQueryParam.getPayTimeEnd() == null ? null: DateUtil.endOfDay(tenantPaymentQueryParam.getPayTimeEnd()))
				.eq(tenantPaymentQueryParam.getPaymentStatus() != null, TenantPayment::getPaymentStatus, tenantPaymentQueryParam.getPaymentStatus())
				.eq(tenantPaymentQueryParam.getCustomerId() != null, TenantPayment::getCustomerId, tenantPaymentQueryParam.getCustomerId())
				.eq(tenantPaymentQueryParam.getChargeDepartmentId() != null, TenantPayment::getChargeDepartmentId, tenantPaymentQueryParam.getChargeDepartmentId())
				.eq(tenantPaymentQueryParam.getChargeEmployeeId() != null, TenantPayment::getChargeEmployeeId, tenantPaymentQueryParam.getChargeEmployeeId())
				.eq(tenantPaymentQueryParam.getPayChannels() != null, TenantPayment::getPayChannels, tenantPaymentQueryParam.getPayChannels())
				.eq(tenantPaymentQueryParam.getPayMethod() != null, TenantPayment::getPayMethod, tenantPaymentQueryParam.getPayMethod())
				.eq(tenantPaymentQueryParam.getCustomerBalanceMoneyBefore() != null, TenantPayment::getCustomerBalanceMoneyBefore, tenantPaymentQueryParam.getCustomerBalanceMoneyBefore())
				.eq(tenantPaymentQueryParam.getCustomerPayMoney() != null, TenantPayment::getCustomerPayMoney, tenantPaymentQueryParam.getCustomerPayMoney())
				.eq(tenantPaymentQueryParam.getCustomerBalanceMoneyHappen() != null, TenantPayment::getCustomerBalanceMoneyHappen, tenantPaymentQueryParam.getCustomerBalanceMoneyHappen())
				.eq(tenantPaymentQueryParam.getPayTheArrearsMoney() != null, TenantPayment::getPayTheArrearsMoney, tenantPaymentQueryParam.getPayTheArrearsMoney())
				.eq(tenantPaymentQueryParam.getPayTheLateFeeMoney() != null, TenantPayment::getPayTheLateFeeMoney, tenantPaymentQueryParam.getPayTheLateFeeMoney())
				.eq(tenantPaymentQueryParam.getCustomerBalanceMoneyAfter() != null, TenantPayment::getCustomerBalanceMoneyAfter, tenantPaymentQueryParam.getCustomerBalanceMoneyAfter())
				;

		TenantPayment tenantPayment = tenantPaymentService.getAggregation(queryWrapperTenantPayment);
		
		return entity2vo(tenantPayment);
	}

	@ApiOperation(value = "新增实收总账，记录每次缴费的总信息")
	@RequestMapping(value = "/tenant-payments", method = RequestMethod.POST)
	public TenantPaymentVo save(@RequestBody TenantPayment tenantPayment) {
		if (tenantPayment.getId() == null || tenantPayment.getId().trim().length() <= 0) {
			tenantPayment.setId(idService.selectId());
		}
		boolean success = tenantPaymentService.save(tenantPayment);
		if (success) {
			TenantPayment tenantPaymentDatabase = tenantPaymentService.getById(tenantPayment.getId());
			return entity2vo(tenantPaymentDatabase);
		}
		log.info("save TenantPayment fail，{}", ToStringBuilder.reflectionToString(tenantPayment, ToStringStyle.JSON_STYLE));
		return null;
	}

	@ApiOperation(value = "更新实收总账，记录每次缴费的总信息全部信息")
	@RequestMapping(value = "/tenant-payments/{id}", method = RequestMethod.PUT)
	public TenantPaymentVo updateById(@PathVariable("id") String id, @RequestBody TenantPayment tenantPayment) {
		tenantPayment.setId(id);
		boolean success = tenantPaymentService.updateById(tenantPayment);
		if (success) {
			TenantPayment tenantPaymentDatabase = tenantPaymentService.getById(id);
			return entity2vo(tenantPaymentDatabase);
		}
		log.info("update TenantPayment fail，{}", ToStringBuilder.reflectionToString(tenantPayment, ToStringStyle.JSON_STYLE));
		return null;
	}

	@ApiOperation(value = "根据参数更新实收总账，记录每次缴费的总信息信息")
	@RequestMapping(value = "/tenant-payments/{id}", method = RequestMethod.PATCH)
	public TenantPaymentVo updatePatchById(@PathVariable("id") String id, @RequestBody TenantPayment tenantPayment) {
        TenantPayment tenantPaymentWhere = TenantPayment.builder()//
				.id(id)//
				.build();
		UpdateWrapper<TenantPayment> updateWrapperTenantPayment = new UpdateWrapper<TenantPayment>();
		updateWrapperTenantPayment.setEntity(tenantPaymentWhere);
		updateWrapperTenantPayment.lambda()//
				//.eq(TenantPayment::getId, id)
				// .set(tenantPayment.getId() != null, TenantPayment::getId, tenantPayment.getId())
				.set(tenantPayment.getTenantId() != null, TenantPayment::getTenantId, tenantPayment.getTenantId())
				.set(tenantPayment.getOutTransno() != null, TenantPayment::getOutTransno, tenantPayment.getOutTransno())
				.set(tenantPayment.getInTransno() != null, TenantPayment::getInTransno, tenantPayment.getInTransno())
				.set(tenantPayment.getPayTime() != null, TenantPayment::getPayTime, tenantPayment.getPayTime())
				.set(tenantPayment.getPaymentStatus() != null, TenantPayment::getPaymentStatus, tenantPayment.getPaymentStatus())
				.set(tenantPayment.getCustomerId() != null, TenantPayment::getCustomerId, tenantPayment.getCustomerId())
				.set(tenantPayment.getChargeDepartmentId() != null, TenantPayment::getChargeDepartmentId, tenantPayment.getChargeDepartmentId())
				.set(tenantPayment.getChargeEmployeeId() != null, TenantPayment::getChargeEmployeeId, tenantPayment.getChargeEmployeeId())
				.set(tenantPayment.getPayChannels() != null, TenantPayment::getPayChannels, tenantPayment.getPayChannels())
				.set(tenantPayment.getPayMethod() != null, TenantPayment::getPayMethod, tenantPayment.getPayMethod())
				.set(tenantPayment.getCustomerBalanceMoneyBefore() != null, TenantPayment::getCustomerBalanceMoneyBefore, tenantPayment.getCustomerBalanceMoneyBefore())
				.set(tenantPayment.getCustomerPayMoney() != null, TenantPayment::getCustomerPayMoney, tenantPayment.getCustomerPayMoney())
				.set(tenantPayment.getCustomerBalanceMoneyHappen() != null, TenantPayment::getCustomerBalanceMoneyHappen, tenantPayment.getCustomerBalanceMoneyHappen())
				.set(tenantPayment.getPayTheArrearsMoney() != null, TenantPayment::getPayTheArrearsMoney, tenantPayment.getPayTheArrearsMoney())
				.set(tenantPayment.getPayTheLateFeeMoney() != null, TenantPayment::getPayTheLateFeeMoney, tenantPayment.getPayTheLateFeeMoney())
				.set(tenantPayment.getCustomerBalanceMoneyAfter() != null, TenantPayment::getCustomerBalanceMoneyAfter, tenantPayment.getCustomerBalanceMoneyAfter())
				;

		boolean success = tenantPaymentService.update(updateWrapperTenantPayment);
		if (success) {
			TenantPayment tenantPaymentDatabase = tenantPaymentService.getById(id);
			return entity2vo(tenantPaymentDatabase);
		}
		log.info("partial update TenantPayment fail，{}",
				ToStringBuilder.reflectionToString(tenantPayment, ToStringStyle.JSON_STYLE));
		return null;
	}

	@ApiOperation(value = "根据ID删除实收总账，记录每次缴费的总信息")
	@RequestMapping(value = "/tenant-payments/{id}", method = RequestMethod.DELETE)
	public CommonResult<Object> removeById(@PathVariable("id") String id) {
		boolean success = tenantPaymentService.removeById(id);
		return success ? CommonResult.success(success) : CommonResult.failed();
	}

	private TenantPaymentVo entity2vo(TenantPayment tenantPayment) {
		if (tenantPayment == null) {
			return null;
		}

		String jsonString = JSON.toJSONString(tenantPayment);
		TenantPaymentVo tenantPaymentVo = JSON.parseObject(jsonString, TenantPaymentVo.class);
		if (StringUtils.isEmpty(tenantPaymentVo.getTenantName())) {
			TenantInfo tenantInfo = tenantInfoService.getDictionaryById(tenantPayment.getTenantId());
			if (tenantInfo != null) {
				tenantPaymentVo.setTenantName(tenantInfo.getTenantName());
			}
		}
		return tenantPaymentVo;
	}

}
