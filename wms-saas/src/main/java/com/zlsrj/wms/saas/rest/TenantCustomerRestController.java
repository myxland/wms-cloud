package com.zlsrj.wms.saas.rest;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zlsrj.wms.api.dto.TenantCustomerAddParam;
import com.zlsrj.wms.api.dto.TenantCustomerQueryParam;
import com.zlsrj.wms.api.dto.TenantCustomerUpdateParam;
import com.zlsrj.wms.api.entity.TenantInfo;
import com.zlsrj.wms.api.entity.TenantCustomer;
import com.zlsrj.wms.api.vo.TenantCustomerVo;
import com.zlsrj.wms.common.api.CommonResult;
import com.zlsrj.wms.common.util.TranslateUtil;
import com.zlsrj.wms.saas.service.ITenantInfoService;
import com.zlsrj.wms.saas.service.ITenantCustomerService;

import cn.hutool.core.date.DateUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

@Api(value = "用户信息", tags = { "用户信息操作接口" })
@RestController
@Slf4j
public class TenantCustomerRestController {

	@Autowired
	private ITenantCustomerService tenantCustomerService;
	@Autowired
	private ITenantInfoService tenantInfoService;

	@ApiOperation(value = "根据ID查询用户信息")
	@RequestMapping(value = "/tenant-customers/{id}", method = RequestMethod.GET)
	public TenantCustomerVo getById(@PathVariable("id") String id) {
		TenantCustomer tenantCustomer = tenantCustomerService.getById(id);

		return entity2vo(tenantCustomer);
	}

	@ApiOperation(value = "根据参数查询用户信息列表")
	@RequestMapping(value = "/tenant-customers/list", method = RequestMethod.GET)
	public List<TenantCustomerVo> list(@RequestBody TenantCustomerQueryParam tenantCustomerQueryParam) {
		QueryWrapper<TenantCustomer> queryWrapperTenantCustomer = new QueryWrapper<TenantCustomer>();
		queryWrapperTenantCustomer.lambda()
				.eq(StringUtils.isNotEmpty(tenantCustomerQueryParam.getId()), TenantCustomer::getId, tenantCustomerQueryParam.getId())
				.eq(StringUtils.isNotEmpty(tenantCustomerQueryParam.getTenantId()), TenantCustomer::getTenantId, tenantCustomerQueryParam.getTenantId())
				.eq(StringUtils.isNotEmpty(tenantCustomerQueryParam.getCustomerCode()), TenantCustomer::getCustomerCode, tenantCustomerQueryParam.getCustomerCode())
				.eq(StringUtils.isNotEmpty(tenantCustomerQueryParam.getCustomerName()), TenantCustomer::getCustomerName, tenantCustomerQueryParam.getCustomerName())
				.eq(StringUtils.isNotEmpty(tenantCustomerQueryParam.getCustomerAddress()), TenantCustomer::getCustomerAddress, tenantCustomerQueryParam.getCustomerAddress())
				.eq(tenantCustomerQueryParam.getCustomerStatus() != null, TenantCustomer::getCustomerStatus, tenantCustomerQueryParam.getCustomerStatus())
				.eq(StringUtils.isNotEmpty(tenantCustomerQueryParam.getCustomerType()), TenantCustomer::getCustomerType, tenantCustomerQueryParam.getCustomerType())
				.eq(tenantCustomerQueryParam.getCustomerRegisterTime() != null, TenantCustomer::getCustomerRegisterTime, tenantCustomerQueryParam.getCustomerRegisterTime())
				.ge(tenantCustomerQueryParam.getCustomerRegisterTimeStart() != null, TenantCustomer::getCustomerRegisterTime,tenantCustomerQueryParam.getCustomerRegisterTimeStart() == null ? null: DateUtil.beginOfDay(tenantCustomerQueryParam.getCustomerRegisterTimeStart()))
				.le(tenantCustomerQueryParam.getCustomerRegisterTimeEnd() != null, TenantCustomer::getCustomerRegisterTime,tenantCustomerQueryParam.getCustomerRegisterTimeEnd() == null ? null: DateUtil.endOfDay(tenantCustomerQueryParam.getCustomerRegisterTimeEnd()))
				.eq(tenantCustomerQueryParam.getCustomerRegisterDate() != null, TenantCustomer::getCustomerRegisterDate, tenantCustomerQueryParam.getCustomerRegisterDate())
				.ge(tenantCustomerQueryParam.getCustomerRegisterDateStart() != null, TenantCustomer::getCustomerRegisterDate,tenantCustomerQueryParam.getCustomerRegisterDateStart() == null ? null: DateUtil.beginOfDay(tenantCustomerQueryParam.getCustomerRegisterDateStart()))
				.le(tenantCustomerQueryParam.getCustomerRegisterDateEnd() != null, TenantCustomer::getCustomerRegisterDate,tenantCustomerQueryParam.getCustomerRegisterDateEnd() == null ? null: DateUtil.endOfDay(tenantCustomerQueryParam.getCustomerRegisterDateEnd()))
				.eq(tenantCustomerQueryParam.getCustomerCreditRating() != null, TenantCustomer::getCustomerCreditRating, tenantCustomerQueryParam.getCustomerCreditRating())
				.eq(tenantCustomerQueryParam.getCustomerRatingDate() != null, TenantCustomer::getCustomerRatingDate, tenantCustomerQueryParam.getCustomerRatingDate())
				.ge(tenantCustomerQueryParam.getCustomerRatingDateStart() != null, TenantCustomer::getCustomerRatingDate,tenantCustomerQueryParam.getCustomerRatingDateStart() == null ? null: DateUtil.beginOfDay(tenantCustomerQueryParam.getCustomerRatingDateStart()))
				.le(tenantCustomerQueryParam.getCustomerRatingDateEnd() != null, TenantCustomer::getCustomerRatingDate,tenantCustomerQueryParam.getCustomerRatingDateEnd() == null ? null: DateUtil.endOfDay(tenantCustomerQueryParam.getCustomerRatingDateEnd()))
				.eq(tenantCustomerQueryParam.getCustomerBalanceAmt() != null, TenantCustomer::getCustomerBalanceAmt, tenantCustomerQueryParam.getCustomerBalanceAmt())
				.eq(tenantCustomerQueryParam.getCustomerFreezeBalance() != null, TenantCustomer::getCustomerFreezeBalance, tenantCustomerQueryParam.getCustomerFreezeBalance())
				.eq(tenantCustomerQueryParam.getCustomerOweAmt() != null, TenantCustomer::getCustomerOweAmt, tenantCustomerQueryParam.getCustomerOweAmt())
				.eq(tenantCustomerQueryParam.getCustomerPenaltyAmt() != null, TenantCustomer::getCustomerPenaltyAmt, tenantCustomerQueryParam.getCustomerPenaltyAmt())
				.eq(StringUtils.isNotEmpty(tenantCustomerQueryParam.getCustomerMemo()), TenantCustomer::getCustomerMemo, tenantCustomerQueryParam.getCustomerMemo())
				.eq(tenantCustomerQueryParam.getAddTime() != null, TenantCustomer::getAddTime, tenantCustomerQueryParam.getAddTime())
				.ge(tenantCustomerQueryParam.getAddTimeStart() != null, TenantCustomer::getAddTime,tenantCustomerQueryParam.getAddTimeStart() == null ? null: DateUtil.beginOfDay(tenantCustomerQueryParam.getAddTimeStart()))
				.le(tenantCustomerQueryParam.getAddTimeEnd() != null, TenantCustomer::getAddTime,tenantCustomerQueryParam.getAddTimeEnd() == null ? null: DateUtil.endOfDay(tenantCustomerQueryParam.getAddTimeEnd()))
				.eq(tenantCustomerQueryParam.getUpdateTime() != null, TenantCustomer::getUpdateTime, tenantCustomerQueryParam.getUpdateTime())
				.ge(tenantCustomerQueryParam.getUpdateTimeStart() != null, TenantCustomer::getUpdateTime,tenantCustomerQueryParam.getUpdateTimeStart() == null ? null: DateUtil.beginOfDay(tenantCustomerQueryParam.getUpdateTimeStart()))
				.le(tenantCustomerQueryParam.getUpdateTimeEnd() != null, TenantCustomer::getUpdateTime,tenantCustomerQueryParam.getUpdateTimeEnd() == null ? null: DateUtil.endOfDay(tenantCustomerQueryParam.getUpdateTimeEnd()))
				;

		List<TenantCustomer> tenantCustomerList = tenantCustomerService.list(queryWrapperTenantCustomer);

		List<TenantCustomerVo> tenantCustomerVoList = tenantCustomerList.stream()//
				 .map(e -> entity2vo(e))//
				 .collect(Collectors.toList());

		return tenantCustomerVoList;
	}
	
	@ApiOperation(value = "根据参数查询用户信息数量")
	@RequestMapping(value = "/tenant-customers/count", method = RequestMethod.GET)
	public int count(@RequestBody TenantCustomerQueryParam tenantCustomerQueryParam) {
		QueryWrapper<TenantCustomer> queryWrapperTenantCustomer = new QueryWrapper<TenantCustomer>();
		queryWrapperTenantCustomer.lambda()
				.eq(StringUtils.isNotEmpty(tenantCustomerQueryParam.getId()), TenantCustomer::getId, tenantCustomerQueryParam.getId())
				.eq(StringUtils.isNotEmpty(tenantCustomerQueryParam.getTenantId()), TenantCustomer::getTenantId, tenantCustomerQueryParam.getTenantId())
				.eq(StringUtils.isNotEmpty(tenantCustomerQueryParam.getCustomerCode()), TenantCustomer::getCustomerCode, tenantCustomerQueryParam.getCustomerCode())
				.eq(StringUtils.isNotEmpty(tenantCustomerQueryParam.getCustomerName()), TenantCustomer::getCustomerName, tenantCustomerQueryParam.getCustomerName())
				.eq(StringUtils.isNotEmpty(tenantCustomerQueryParam.getCustomerAddress()), TenantCustomer::getCustomerAddress, tenantCustomerQueryParam.getCustomerAddress())
				.eq(tenantCustomerQueryParam.getCustomerStatus() != null, TenantCustomer::getCustomerStatus, tenantCustomerQueryParam.getCustomerStatus())
				.eq(StringUtils.isNotEmpty(tenantCustomerQueryParam.getCustomerType()), TenantCustomer::getCustomerType, tenantCustomerQueryParam.getCustomerType())
				.eq(tenantCustomerQueryParam.getCustomerRegisterTime() != null, TenantCustomer::getCustomerRegisterTime, tenantCustomerQueryParam.getCustomerRegisterTime())
				.ge(tenantCustomerQueryParam.getCustomerRegisterTimeStart() != null, TenantCustomer::getCustomerRegisterTime,tenantCustomerQueryParam.getCustomerRegisterTimeStart() == null ? null: DateUtil.beginOfDay(tenantCustomerQueryParam.getCustomerRegisterTimeStart()))
				.le(tenantCustomerQueryParam.getCustomerRegisterTimeEnd() != null, TenantCustomer::getCustomerRegisterTime,tenantCustomerQueryParam.getCustomerRegisterTimeEnd() == null ? null: DateUtil.endOfDay(tenantCustomerQueryParam.getCustomerRegisterTimeEnd()))
				.eq(tenantCustomerQueryParam.getCustomerRegisterDate() != null, TenantCustomer::getCustomerRegisterDate, tenantCustomerQueryParam.getCustomerRegisterDate())
				.ge(tenantCustomerQueryParam.getCustomerRegisterDateStart() != null, TenantCustomer::getCustomerRegisterDate,tenantCustomerQueryParam.getCustomerRegisterDateStart() == null ? null: DateUtil.beginOfDay(tenantCustomerQueryParam.getCustomerRegisterDateStart()))
				.le(tenantCustomerQueryParam.getCustomerRegisterDateEnd() != null, TenantCustomer::getCustomerRegisterDate,tenantCustomerQueryParam.getCustomerRegisterDateEnd() == null ? null: DateUtil.endOfDay(tenantCustomerQueryParam.getCustomerRegisterDateEnd()))
				.eq(tenantCustomerQueryParam.getCustomerCreditRating() != null, TenantCustomer::getCustomerCreditRating, tenantCustomerQueryParam.getCustomerCreditRating())
				.eq(tenantCustomerQueryParam.getCustomerRatingDate() != null, TenantCustomer::getCustomerRatingDate, tenantCustomerQueryParam.getCustomerRatingDate())
				.ge(tenantCustomerQueryParam.getCustomerRatingDateStart() != null, TenantCustomer::getCustomerRatingDate,tenantCustomerQueryParam.getCustomerRatingDateStart() == null ? null: DateUtil.beginOfDay(tenantCustomerQueryParam.getCustomerRatingDateStart()))
				.le(tenantCustomerQueryParam.getCustomerRatingDateEnd() != null, TenantCustomer::getCustomerRatingDate,tenantCustomerQueryParam.getCustomerRatingDateEnd() == null ? null: DateUtil.endOfDay(tenantCustomerQueryParam.getCustomerRatingDateEnd()))
				.eq(tenantCustomerQueryParam.getCustomerBalanceAmt() != null, TenantCustomer::getCustomerBalanceAmt, tenantCustomerQueryParam.getCustomerBalanceAmt())
				.eq(tenantCustomerQueryParam.getCustomerFreezeBalance() != null, TenantCustomer::getCustomerFreezeBalance, tenantCustomerQueryParam.getCustomerFreezeBalance())
				.eq(tenantCustomerQueryParam.getCustomerOweAmt() != null, TenantCustomer::getCustomerOweAmt, tenantCustomerQueryParam.getCustomerOweAmt())
				.eq(tenantCustomerQueryParam.getCustomerPenaltyAmt() != null, TenantCustomer::getCustomerPenaltyAmt, tenantCustomerQueryParam.getCustomerPenaltyAmt())
				.eq(StringUtils.isNotEmpty(tenantCustomerQueryParam.getCustomerMemo()), TenantCustomer::getCustomerMemo, tenantCustomerQueryParam.getCustomerMemo())
				.eq(tenantCustomerQueryParam.getAddTime() != null, TenantCustomer::getAddTime, tenantCustomerQueryParam.getAddTime())
				.ge(tenantCustomerQueryParam.getAddTimeStart() != null, TenantCustomer::getAddTime,tenantCustomerQueryParam.getAddTimeStart() == null ? null: DateUtil.beginOfDay(tenantCustomerQueryParam.getAddTimeStart()))
				.le(tenantCustomerQueryParam.getAddTimeEnd() != null, TenantCustomer::getAddTime,tenantCustomerQueryParam.getAddTimeEnd() == null ? null: DateUtil.endOfDay(tenantCustomerQueryParam.getAddTimeEnd()))
				.eq(tenantCustomerQueryParam.getUpdateTime() != null, TenantCustomer::getUpdateTime, tenantCustomerQueryParam.getUpdateTime())
				.ge(tenantCustomerQueryParam.getUpdateTimeStart() != null, TenantCustomer::getUpdateTime,tenantCustomerQueryParam.getUpdateTimeStart() == null ? null: DateUtil.beginOfDay(tenantCustomerQueryParam.getUpdateTimeStart()))
				.le(tenantCustomerQueryParam.getUpdateTimeEnd() != null, TenantCustomer::getUpdateTime,tenantCustomerQueryParam.getUpdateTimeEnd() == null ? null: DateUtil.endOfDay(tenantCustomerQueryParam.getUpdateTimeEnd()))
				;

		int count = tenantCustomerService.count(queryWrapperTenantCustomer);

		return count;
	}
	
	@ApiOperation(value = "根据参数查询用户信息列表")
	@RequestMapping(value = "/tenant-customers", method = RequestMethod.GET)
	public Page<TenantCustomerVo> page(@RequestBody TenantCustomerQueryParam tenantCustomerQueryParam,
			@RequestParam(value = "page", defaultValue = "1") int page, //
			@RequestParam(value = "rows", defaultValue = "10") int rows, //
			@RequestParam(value = "sort") String sort, // 排序列字段名
			@RequestParam(value = "order") String order // 可以是 'asc' 或者 'desc'，默认值是 'asc'
	) {
		IPage<TenantCustomer> pageTenantCustomer = new Page<TenantCustomer>(page, rows);
		QueryWrapper<TenantCustomer> queryWrapperTenantCustomer = new QueryWrapper<TenantCustomer>();
		queryWrapperTenantCustomer.orderBy(StringUtils.isNotBlank(sort), "asc".equals(order), sort);
		queryWrapperTenantCustomer.lambda()
				.eq(StringUtils.isNotEmpty(tenantCustomerQueryParam.getId()), TenantCustomer::getId, tenantCustomerQueryParam.getId())
				.eq(StringUtils.isNotEmpty(tenantCustomerQueryParam.getTenantId()), TenantCustomer::getTenantId, tenantCustomerQueryParam.getTenantId())
				.eq(StringUtils.isNotEmpty(tenantCustomerQueryParam.getCustomerCode()), TenantCustomer::getCustomerCode, tenantCustomerQueryParam.getCustomerCode())
				.eq(StringUtils.isNotEmpty(tenantCustomerQueryParam.getCustomerName()), TenantCustomer::getCustomerName, tenantCustomerQueryParam.getCustomerName())
				.eq(StringUtils.isNotEmpty(tenantCustomerQueryParam.getCustomerAddress()), TenantCustomer::getCustomerAddress, tenantCustomerQueryParam.getCustomerAddress())
				.eq(tenantCustomerQueryParam.getCustomerStatus() != null, TenantCustomer::getCustomerStatus, tenantCustomerQueryParam.getCustomerStatus())
				.eq(StringUtils.isNotEmpty(tenantCustomerQueryParam.getCustomerType()), TenantCustomer::getCustomerType, tenantCustomerQueryParam.getCustomerType())
				.eq(tenantCustomerQueryParam.getCustomerRegisterTime() != null, TenantCustomer::getCustomerRegisterTime, tenantCustomerQueryParam.getCustomerRegisterTime())
				.ge(tenantCustomerQueryParam.getCustomerRegisterTimeStart() != null, TenantCustomer::getCustomerRegisterTime,tenantCustomerQueryParam.getCustomerRegisterTimeStart() == null ? null: DateUtil.beginOfDay(tenantCustomerQueryParam.getCustomerRegisterTimeStart()))
				.le(tenantCustomerQueryParam.getCustomerRegisterTimeEnd() != null, TenantCustomer::getCustomerRegisterTime,tenantCustomerQueryParam.getCustomerRegisterTimeEnd() == null ? null: DateUtil.endOfDay(tenantCustomerQueryParam.getCustomerRegisterTimeEnd()))
				.eq(tenantCustomerQueryParam.getCustomerRegisterDate() != null, TenantCustomer::getCustomerRegisterDate, tenantCustomerQueryParam.getCustomerRegisterDate())
				.ge(tenantCustomerQueryParam.getCustomerRegisterDateStart() != null, TenantCustomer::getCustomerRegisterDate,tenantCustomerQueryParam.getCustomerRegisterDateStart() == null ? null: DateUtil.beginOfDay(tenantCustomerQueryParam.getCustomerRegisterDateStart()))
				.le(tenantCustomerQueryParam.getCustomerRegisterDateEnd() != null, TenantCustomer::getCustomerRegisterDate,tenantCustomerQueryParam.getCustomerRegisterDateEnd() == null ? null: DateUtil.endOfDay(tenantCustomerQueryParam.getCustomerRegisterDateEnd()))
				.eq(tenantCustomerQueryParam.getCustomerCreditRating() != null, TenantCustomer::getCustomerCreditRating, tenantCustomerQueryParam.getCustomerCreditRating())
				.eq(tenantCustomerQueryParam.getCustomerRatingDate() != null, TenantCustomer::getCustomerRatingDate, tenantCustomerQueryParam.getCustomerRatingDate())
				.ge(tenantCustomerQueryParam.getCustomerRatingDateStart() != null, TenantCustomer::getCustomerRatingDate,tenantCustomerQueryParam.getCustomerRatingDateStart() == null ? null: DateUtil.beginOfDay(tenantCustomerQueryParam.getCustomerRatingDateStart()))
				.le(tenantCustomerQueryParam.getCustomerRatingDateEnd() != null, TenantCustomer::getCustomerRatingDate,tenantCustomerQueryParam.getCustomerRatingDateEnd() == null ? null: DateUtil.endOfDay(tenantCustomerQueryParam.getCustomerRatingDateEnd()))
				.eq(tenantCustomerQueryParam.getCustomerBalanceAmt() != null, TenantCustomer::getCustomerBalanceAmt, tenantCustomerQueryParam.getCustomerBalanceAmt())
				.eq(tenantCustomerQueryParam.getCustomerFreezeBalance() != null, TenantCustomer::getCustomerFreezeBalance, tenantCustomerQueryParam.getCustomerFreezeBalance())
				.eq(tenantCustomerQueryParam.getCustomerOweAmt() != null, TenantCustomer::getCustomerOweAmt, tenantCustomerQueryParam.getCustomerOweAmt())
				.eq(tenantCustomerQueryParam.getCustomerPenaltyAmt() != null, TenantCustomer::getCustomerPenaltyAmt, tenantCustomerQueryParam.getCustomerPenaltyAmt())
				.eq(StringUtils.isNotEmpty(tenantCustomerQueryParam.getCustomerMemo()), TenantCustomer::getCustomerMemo, tenantCustomerQueryParam.getCustomerMemo())
				.eq(tenantCustomerQueryParam.getAddTime() != null, TenantCustomer::getAddTime, tenantCustomerQueryParam.getAddTime())
				.ge(tenantCustomerQueryParam.getAddTimeStart() != null, TenantCustomer::getAddTime,tenantCustomerQueryParam.getAddTimeStart() == null ? null: DateUtil.beginOfDay(tenantCustomerQueryParam.getAddTimeStart()))
				.le(tenantCustomerQueryParam.getAddTimeEnd() != null, TenantCustomer::getAddTime,tenantCustomerQueryParam.getAddTimeEnd() == null ? null: DateUtil.endOfDay(tenantCustomerQueryParam.getAddTimeEnd()))
				.eq(tenantCustomerQueryParam.getUpdateTime() != null, TenantCustomer::getUpdateTime, tenantCustomerQueryParam.getUpdateTime())
				.ge(tenantCustomerQueryParam.getUpdateTimeStart() != null, TenantCustomer::getUpdateTime,tenantCustomerQueryParam.getUpdateTimeStart() == null ? null: DateUtil.beginOfDay(tenantCustomerQueryParam.getUpdateTimeStart()))
				.le(tenantCustomerQueryParam.getUpdateTimeEnd() != null, TenantCustomer::getUpdateTime,tenantCustomerQueryParam.getUpdateTimeEnd() == null ? null: DateUtil.endOfDay(tenantCustomerQueryParam.getUpdateTimeEnd()))
				;

		IPage<TenantCustomer> tenantCustomerPage = tenantCustomerService.page(pageTenantCustomer, queryWrapperTenantCustomer);

		Page<TenantCustomerVo> tenantCustomerVoPage = new Page<TenantCustomerVo>(page, rows);
		tenantCustomerVoPage.setCurrent(tenantCustomerPage.getCurrent());
		tenantCustomerVoPage.setPages(tenantCustomerPage.getPages());
		tenantCustomerVoPage.setSize(tenantCustomerPage.getSize());
		tenantCustomerVoPage.setTotal(tenantCustomerPage.getTotal());
		tenantCustomerVoPage.setRecords(tenantCustomerPage.getRecords().stream()//
				.map(e -> entity2vo(e))//
				.collect(Collectors.toList()));

		return tenantCustomerVoPage;
	}
	
	@ApiOperation(value = "根据参数查询用户信息统计")
	@RequestMapping(value = "/tenant-customers/aggregation", method = RequestMethod.GET)
	public TenantCustomerVo aggregation(@RequestBody TenantCustomerQueryParam tenantCustomerQueryParam) {
		QueryWrapper<TenantCustomer> queryWrapperTenantCustomer = new QueryWrapper<TenantCustomer>();
		queryWrapperTenantCustomer.lambda()
				.eq(StringUtils.isNotEmpty(tenantCustomerQueryParam.getId()), TenantCustomer::getId, tenantCustomerQueryParam.getId())
				.eq(StringUtils.isNotEmpty(tenantCustomerQueryParam.getTenantId()), TenantCustomer::getTenantId, tenantCustomerQueryParam.getTenantId())
				.eq(StringUtils.isNotEmpty(tenantCustomerQueryParam.getCustomerCode()), TenantCustomer::getCustomerCode, tenantCustomerQueryParam.getCustomerCode())
				.eq(StringUtils.isNotEmpty(tenantCustomerQueryParam.getCustomerName()), TenantCustomer::getCustomerName, tenantCustomerQueryParam.getCustomerName())
				.eq(StringUtils.isNotEmpty(tenantCustomerQueryParam.getCustomerAddress()), TenantCustomer::getCustomerAddress, tenantCustomerQueryParam.getCustomerAddress())
				.eq(tenantCustomerQueryParam.getCustomerStatus() != null, TenantCustomer::getCustomerStatus, tenantCustomerQueryParam.getCustomerStatus())
				.eq(StringUtils.isNotEmpty(tenantCustomerQueryParam.getCustomerType()), TenantCustomer::getCustomerType, tenantCustomerQueryParam.getCustomerType())
				.eq(tenantCustomerQueryParam.getCustomerRegisterTime() != null, TenantCustomer::getCustomerRegisterTime, tenantCustomerQueryParam.getCustomerRegisterTime())
				.ge(tenantCustomerQueryParam.getCustomerRegisterTimeStart() != null, TenantCustomer::getCustomerRegisterTime,tenantCustomerQueryParam.getCustomerRegisterTimeStart() == null ? null: DateUtil.beginOfDay(tenantCustomerQueryParam.getCustomerRegisterTimeStart()))
				.le(tenantCustomerQueryParam.getCustomerRegisterTimeEnd() != null, TenantCustomer::getCustomerRegisterTime,tenantCustomerQueryParam.getCustomerRegisterTimeEnd() == null ? null: DateUtil.endOfDay(tenantCustomerQueryParam.getCustomerRegisterTimeEnd()))
				.eq(tenantCustomerQueryParam.getCustomerRegisterDate() != null, TenantCustomer::getCustomerRegisterDate, tenantCustomerQueryParam.getCustomerRegisterDate())
				.ge(tenantCustomerQueryParam.getCustomerRegisterDateStart() != null, TenantCustomer::getCustomerRegisterDate,tenantCustomerQueryParam.getCustomerRegisterDateStart() == null ? null: DateUtil.beginOfDay(tenantCustomerQueryParam.getCustomerRegisterDateStart()))
				.le(tenantCustomerQueryParam.getCustomerRegisterDateEnd() != null, TenantCustomer::getCustomerRegisterDate,tenantCustomerQueryParam.getCustomerRegisterDateEnd() == null ? null: DateUtil.endOfDay(tenantCustomerQueryParam.getCustomerRegisterDateEnd()))
				.eq(tenantCustomerQueryParam.getCustomerCreditRating() != null, TenantCustomer::getCustomerCreditRating, tenantCustomerQueryParam.getCustomerCreditRating())
				.eq(tenantCustomerQueryParam.getCustomerRatingDate() != null, TenantCustomer::getCustomerRatingDate, tenantCustomerQueryParam.getCustomerRatingDate())
				.ge(tenantCustomerQueryParam.getCustomerRatingDateStart() != null, TenantCustomer::getCustomerRatingDate,tenantCustomerQueryParam.getCustomerRatingDateStart() == null ? null: DateUtil.beginOfDay(tenantCustomerQueryParam.getCustomerRatingDateStart()))
				.le(tenantCustomerQueryParam.getCustomerRatingDateEnd() != null, TenantCustomer::getCustomerRatingDate,tenantCustomerQueryParam.getCustomerRatingDateEnd() == null ? null: DateUtil.endOfDay(tenantCustomerQueryParam.getCustomerRatingDateEnd()))
				.eq(tenantCustomerQueryParam.getCustomerBalanceAmt() != null, TenantCustomer::getCustomerBalanceAmt, tenantCustomerQueryParam.getCustomerBalanceAmt())
				.eq(tenantCustomerQueryParam.getCustomerFreezeBalance() != null, TenantCustomer::getCustomerFreezeBalance, tenantCustomerQueryParam.getCustomerFreezeBalance())
				.eq(tenantCustomerQueryParam.getCustomerOweAmt() != null, TenantCustomer::getCustomerOweAmt, tenantCustomerQueryParam.getCustomerOweAmt())
				.eq(tenantCustomerQueryParam.getCustomerPenaltyAmt() != null, TenantCustomer::getCustomerPenaltyAmt, tenantCustomerQueryParam.getCustomerPenaltyAmt())
				.eq(StringUtils.isNotEmpty(tenantCustomerQueryParam.getCustomerMemo()), TenantCustomer::getCustomerMemo, tenantCustomerQueryParam.getCustomerMemo())
				.eq(tenantCustomerQueryParam.getAddTime() != null, TenantCustomer::getAddTime, tenantCustomerQueryParam.getAddTime())
				.ge(tenantCustomerQueryParam.getAddTimeStart() != null, TenantCustomer::getAddTime,tenantCustomerQueryParam.getAddTimeStart() == null ? null: DateUtil.beginOfDay(tenantCustomerQueryParam.getAddTimeStart()))
				.le(tenantCustomerQueryParam.getAddTimeEnd() != null, TenantCustomer::getAddTime,tenantCustomerQueryParam.getAddTimeEnd() == null ? null: DateUtil.endOfDay(tenantCustomerQueryParam.getAddTimeEnd()))
				.eq(tenantCustomerQueryParam.getUpdateTime() != null, TenantCustomer::getUpdateTime, tenantCustomerQueryParam.getUpdateTime())
				.ge(tenantCustomerQueryParam.getUpdateTimeStart() != null, TenantCustomer::getUpdateTime,tenantCustomerQueryParam.getUpdateTimeStart() == null ? null: DateUtil.beginOfDay(tenantCustomerQueryParam.getUpdateTimeStart()))
				.le(tenantCustomerQueryParam.getUpdateTimeEnd() != null, TenantCustomer::getUpdateTime,tenantCustomerQueryParam.getUpdateTimeEnd() == null ? null: DateUtil.endOfDay(tenantCustomerQueryParam.getUpdateTimeEnd()))
				;

		TenantCustomer tenantCustomer = tenantCustomerService.getAggregation(queryWrapperTenantCustomer);
		
		return entity2vo(tenantCustomer);
	}

	@ApiOperation(value = "新增用户信息")
	@RequestMapping(value = "/tenant-customers", method = RequestMethod.POST)
	public String save(@RequestBody TenantCustomerAddParam tenantCustomerAddParam) {
		return tenantCustomerService.save(tenantCustomerAddParam);
	}

	@ApiOperation(value = "更新用户信息全部信息")
	@RequestMapping(value = "/tenant-customers/{id}", method = RequestMethod.PUT)
	public boolean updateById(@PathVariable("id") String id, @RequestBody TenantCustomerUpdateParam tenantCustomerUpdateParam) {
		tenantCustomerUpdateParam.setId(id);
		return tenantCustomerService.updateById(tenantCustomerUpdateParam);
	}

	@ApiOperation(value = "根据ID删除用户信息")
	@RequestMapping(value = "/tenant-customers/{id}", method = RequestMethod.DELETE)
	public CommonResult<Object> removeById(@PathVariable("id") String id) {
		boolean success = tenantCustomerService.removeById(id);
		return success ? CommonResult.success(success) : CommonResult.failed();
	}

	private TenantCustomerVo entity2vo(TenantCustomer tenantCustomer) {
		if (tenantCustomer == null) {
			return null;
		}

		TenantCustomerVo tenantCustomerVo = TranslateUtil.translate(tenantCustomer, TenantCustomerVo.class);
		if (StringUtils.isEmpty(tenantCustomerVo.getTenantName())) {
			TenantInfo tenantInfo = tenantInfoService.getDictionaryById(tenantCustomer.getTenantId());
			if (tenantInfo != null) {
				tenantCustomerVo.setTenantName(tenantInfo.getTenantName());
			}
		}
		return tenantCustomerVo;
	}

}
