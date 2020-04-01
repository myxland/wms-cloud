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
import com.zlsrj.wms.api.dto.TenantMeterAddParam;
import com.zlsrj.wms.api.dto.TenantMeterQueryParam;
import com.zlsrj.wms.api.dto.TenantMeterUpdateParam;
import com.zlsrj.wms.api.entity.TenantInfo;
import com.zlsrj.wms.api.entity.TenantMeter;
import com.zlsrj.wms.api.vo.TenantMeterVo;
import com.zlsrj.wms.common.api.CommonResult;
import com.zlsrj.wms.common.util.TranslateUtil;
import com.zlsrj.wms.saas.service.ITenantInfoService;
import com.zlsrj.wms.saas.service.ITenantMeterService;

import cn.hutool.core.date.DateUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

@Api(value = "水表信息", tags = { "水表信息操作接口" })
@RestController
@Slf4j
public class TenantMeterRestController {

	@Autowired
	private ITenantMeterService tenantMeterService;
	@Autowired
	private ITenantInfoService tenantInfoService;

	@ApiOperation(value = "根据ID查询水表信息")
	@RequestMapping(value = "/tenant-meters/{id}", method = RequestMethod.GET)
	public TenantMeterVo getById(@PathVariable("id") String id) {
		TenantMeter tenantMeter = tenantMeterService.getById(id);

		return entity2vo(tenantMeter);
	}

	@ApiOperation(value = "根据参数查询水表信息列表")
	@RequestMapping(value = "/tenant-meters/list", method = RequestMethod.GET)
	public List<TenantMeterVo> list(@RequestBody TenantMeterQueryParam tenantMeterQueryParam) {
		QueryWrapper<TenantMeter> queryWrapperTenantMeter = new QueryWrapper<TenantMeter>();
		queryWrapperTenantMeter.lambda()
				.eq(StringUtils.isNotEmpty(tenantMeterQueryParam.getId()), TenantMeter::getId, tenantMeterQueryParam.getId())
				.eq(StringUtils.isNotEmpty(tenantMeterQueryParam.getTenantId()), TenantMeter::getTenantId, tenantMeterQueryParam.getTenantId())
				.eq(StringUtils.isNotEmpty(tenantMeterQueryParam.getCustomerId()), TenantMeter::getCustomerId, tenantMeterQueryParam.getCustomerId())
				.eq(StringUtils.isNotEmpty(tenantMeterQueryParam.getCustomerCode()), TenantMeter::getCustomerCode, tenantMeterQueryParam.getCustomerCode())
				.eq(StringUtils.isNotEmpty(tenantMeterQueryParam.getMeterCode()), TenantMeter::getMeterCode, tenantMeterQueryParam.getMeterCode())
				.eq(StringUtils.isNotEmpty(tenantMeterQueryParam.getMeterAddress()), TenantMeter::getMeterAddress, tenantMeterQueryParam.getMeterAddress())
				.eq(StringUtils.isNotEmpty(tenantMeterQueryParam.getMeterBookId()), TenantMeter::getMeterBookId, tenantMeterQueryParam.getMeterBookId())
				.eq(tenantMeterQueryParam.getMeterPeoples() != null, TenantMeter::getMeterPeoples, tenantMeterQueryParam.getMeterPeoples())
				.eq(tenantMeterQueryParam.getMeterStatus() != null, TenantMeter::getMeterStatus, tenantMeterQueryParam.getMeterStatus())
				.eq(StringUtils.isNotEmpty(tenantMeterQueryParam.getMeterBrandId()), TenantMeter::getMeterBrandId, tenantMeterQueryParam.getMeterBrandId())
				.eq(StringUtils.isNotEmpty(tenantMeterQueryParam.getMeterCaliberId()), TenantMeter::getMeterCaliberId, tenantMeterQueryParam.getMeterCaliberId())
				.eq(StringUtils.isNotEmpty(tenantMeterQueryParam.getMeterTypeId()), TenantMeter::getMeterTypeId, tenantMeterQueryParam.getMeterTypeId())
				.eq(StringUtils.isNotEmpty(tenantMeterQueryParam.getMeterModelId()), TenantMeter::getMeterModelId, tenantMeterQueryParam.getMeterModelId())
				.eq(StringUtils.isNotEmpty(tenantMeterQueryParam.getMeterMarketingAreaId()), TenantMeter::getMeterMarketingAreaId, tenantMeterQueryParam.getMeterMarketingAreaId())
				.eq(StringUtils.isNotEmpty(tenantMeterQueryParam.getMeterSupplyAreaId()), TenantMeter::getMeterSupplyAreaId, tenantMeterQueryParam.getMeterSupplyAreaId())
				.eq(StringUtils.isNotEmpty(tenantMeterQueryParam.getMeterIndustryId()), TenantMeter::getMeterIndustryId, tenantMeterQueryParam.getMeterIndustryId())
				.eq(tenantMeterQueryParam.getMeterUseType() != null, TenantMeter::getMeterUseType, tenantMeterQueryParam.getMeterUseType())
				.eq(tenantMeterQueryParam.getMeterSaveWater() != null, TenantMeter::getMeterSaveWater, tenantMeterQueryParam.getMeterSaveWater())
				.eq(tenantMeterQueryParam.getMeterNewFlag() != null, TenantMeter::getMeterNewFlag, tenantMeterQueryParam.getMeterNewFlag())
				.eq(StringUtils.isNotEmpty(tenantMeterQueryParam.getMeterGpsX()), TenantMeter::getMeterGpsX, tenantMeterQueryParam.getMeterGpsX())
				.eq(StringUtils.isNotEmpty(tenantMeterQueryParam.getMeterGpsY()), TenantMeter::getMeterGpsY, tenantMeterQueryParam.getMeterGpsY())
				.eq(StringUtils.isNotEmpty(tenantMeterQueryParam.getMeterMachineCode()), TenantMeter::getMeterMachineCode, tenantMeterQueryParam.getMeterMachineCode())
				.eq(StringUtils.isNotEmpty(tenantMeterQueryParam.getMeterRemoteCode()), TenantMeter::getMeterRemoteCode, tenantMeterQueryParam.getMeterRemoteCode())
				.eq(tenantMeterQueryParam.getMeterInstallDate() != null, TenantMeter::getMeterInstallDate, tenantMeterQueryParam.getMeterInstallDate())
				.ge(tenantMeterQueryParam.getMeterInstallDateStart() != null, TenantMeter::getMeterInstallDate,tenantMeterQueryParam.getMeterInstallDateStart() == null ? null: DateUtil.beginOfDay(tenantMeterQueryParam.getMeterInstallDateStart()))
				.le(tenantMeterQueryParam.getMeterInstallDateEnd() != null, TenantMeter::getMeterInstallDate,tenantMeterQueryParam.getMeterInstallDateEnd() == null ? null: DateUtil.endOfDay(tenantMeterQueryParam.getMeterInstallDateEnd()))
				.eq(tenantMeterQueryParam.getMeterRegisterTime() != null, TenantMeter::getMeterRegisterTime, tenantMeterQueryParam.getMeterRegisterTime())
				.ge(tenantMeterQueryParam.getMeterRegisterTimeStart() != null, TenantMeter::getMeterRegisterTime,tenantMeterQueryParam.getMeterRegisterTimeStart() == null ? null: DateUtil.beginOfDay(tenantMeterQueryParam.getMeterRegisterTimeStart()))
				.le(tenantMeterQueryParam.getMeterRegisterTimeEnd() != null, TenantMeter::getMeterRegisterTime,tenantMeterQueryParam.getMeterRegisterTimeEnd() == null ? null: DateUtil.endOfDay(tenantMeterQueryParam.getMeterRegisterTimeEnd()))
				.eq(StringUtils.isNotEmpty(tenantMeterQueryParam.getMeterInstallPer()), TenantMeter::getMeterInstallPer, tenantMeterQueryParam.getMeterInstallPer())
				.eq(tenantMeterQueryParam.getMeterReadOrder() != null, TenantMeter::getMeterReadOrder, tenantMeterQueryParam.getMeterReadOrder())
				.eq(tenantMeterQueryParam.getMeterReadCode() != null, TenantMeter::getMeterReadCode, tenantMeterQueryParam.getMeterReadCode())
				.eq(tenantMeterQueryParam.getMeterReadDate() != null, TenantMeter::getMeterReadDate, tenantMeterQueryParam.getMeterReadDate())
				.ge(tenantMeterQueryParam.getMeterReadDateStart() != null, TenantMeter::getMeterReadDate,tenantMeterQueryParam.getMeterReadDateStart() == null ? null: DateUtil.beginOfDay(tenantMeterQueryParam.getMeterReadDateStart()))
				.le(tenantMeterQueryParam.getMeterReadDateEnd() != null, TenantMeter::getMeterReadDate,tenantMeterQueryParam.getMeterReadDateEnd() == null ? null: DateUtil.endOfDay(tenantMeterQueryParam.getMeterReadDateEnd()))
				.eq(tenantMeterQueryParam.getMeterSettleCode() != null, TenantMeter::getMeterSettleCode, tenantMeterQueryParam.getMeterSettleCode())
				.eq(tenantMeterQueryParam.getMeterSettleDate() != null, TenantMeter::getMeterSettleDate, tenantMeterQueryParam.getMeterSettleDate())
				.ge(tenantMeterQueryParam.getMeterSettleDateStart() != null, TenantMeter::getMeterSettleDate,tenantMeterQueryParam.getMeterSettleDateStart() == null ? null: DateUtil.beginOfDay(tenantMeterQueryParam.getMeterSettleDateStart()))
				.le(tenantMeterQueryParam.getMeterSettleDateEnd() != null, TenantMeter::getMeterSettleDate,tenantMeterQueryParam.getMeterSettleDateEnd() == null ? null: DateUtil.endOfDay(tenantMeterQueryParam.getMeterSettleDateEnd()))
				.eq(tenantMeterQueryParam.getMeterOweAmt() != null, TenantMeter::getMeterOweAmt, tenantMeterQueryParam.getMeterOweAmt())
				.eq(tenantMeterQueryParam.getMeterPenaltyAmt() != null, TenantMeter::getMeterPenaltyAmt, tenantMeterQueryParam.getMeterPenaltyAmt())
				.eq(tenantMeterQueryParam.getMeterYearTotalWaters() != null, TenantMeter::getMeterYearTotalWaters, tenantMeterQueryParam.getMeterYearTotalWaters())
				.eq(tenantMeterQueryParam.getMeterTotalWaters() != null, TenantMeter::getMeterTotalWaters, tenantMeterQueryParam.getMeterTotalWaters())
				.eq(tenantMeterQueryParam.getMeterPriceStepDate() != null, TenantMeter::getMeterPriceStepDate, tenantMeterQueryParam.getMeterPriceStepDate())
				.ge(tenantMeterQueryParam.getMeterPriceStepDateStart() != null, TenantMeter::getMeterPriceStepDate,tenantMeterQueryParam.getMeterPriceStepDateStart() == null ? null: DateUtil.beginOfDay(tenantMeterQueryParam.getMeterPriceStepDateStart()))
				.le(tenantMeterQueryParam.getMeterPriceStepDateEnd() != null, TenantMeter::getMeterPriceStepDate,tenantMeterQueryParam.getMeterPriceStepDateEnd() == null ? null: DateUtil.endOfDay(tenantMeterQueryParam.getMeterPriceStepDateEnd()))
				.eq(tenantMeterQueryParam.getMeterPriceStepWaters() != null, TenantMeter::getMeterPriceStepWaters, tenantMeterQueryParam.getMeterPriceStepWaters())
				.eq(StringUtils.isNotEmpty(tenantMeterQueryParam.getMeterMemo()), TenantMeter::getMeterMemo, tenantMeterQueryParam.getMeterMemo())
				.eq(tenantMeterQueryParam.getAddTime() != null, TenantMeter::getAddTime, tenantMeterQueryParam.getAddTime())
				.ge(tenantMeterQueryParam.getAddTimeStart() != null, TenantMeter::getAddTime,tenantMeterQueryParam.getAddTimeStart() == null ? null: DateUtil.beginOfDay(tenantMeterQueryParam.getAddTimeStart()))
				.le(tenantMeterQueryParam.getAddTimeEnd() != null, TenantMeter::getAddTime,tenantMeterQueryParam.getAddTimeEnd() == null ? null: DateUtil.endOfDay(tenantMeterQueryParam.getAddTimeEnd()))
				.eq(tenantMeterQueryParam.getUpdateTime() != null, TenantMeter::getUpdateTime, tenantMeterQueryParam.getUpdateTime())
				.ge(tenantMeterQueryParam.getUpdateTimeStart() != null, TenantMeter::getUpdateTime,tenantMeterQueryParam.getUpdateTimeStart() == null ? null: DateUtil.beginOfDay(tenantMeterQueryParam.getUpdateTimeStart()))
				.le(tenantMeterQueryParam.getUpdateTimeEnd() != null, TenantMeter::getUpdateTime,tenantMeterQueryParam.getUpdateTimeEnd() == null ? null: DateUtil.endOfDay(tenantMeterQueryParam.getUpdateTimeEnd()))
				;

		List<TenantMeter> tenantMeterList = tenantMeterService.list(queryWrapperTenantMeter);

		List<TenantMeterVo> tenantMeterVoList = tenantMeterList.stream()//
				 .map(e -> entity2vo(e))//
				 .collect(Collectors.toList());

		return tenantMeterVoList;
	}
	
	@ApiOperation(value = "根据参数查询水表信息数量")
	@RequestMapping(value = "/tenant-meters/count", method = RequestMethod.GET)
	public int count(@RequestBody TenantMeterQueryParam tenantMeterQueryParam) {
		QueryWrapper<TenantMeter> queryWrapperTenantMeter = new QueryWrapper<TenantMeter>();
		queryWrapperTenantMeter.lambda()
				.eq(StringUtils.isNotEmpty(tenantMeterQueryParam.getId()), TenantMeter::getId, tenantMeterQueryParam.getId())
				.eq(StringUtils.isNotEmpty(tenantMeterQueryParam.getTenantId()), TenantMeter::getTenantId, tenantMeterQueryParam.getTenantId())
				.eq(StringUtils.isNotEmpty(tenantMeterQueryParam.getCustomerId()), TenantMeter::getCustomerId, tenantMeterQueryParam.getCustomerId())
				.eq(StringUtils.isNotEmpty(tenantMeterQueryParam.getCustomerCode()), TenantMeter::getCustomerCode, tenantMeterQueryParam.getCustomerCode())
				.eq(StringUtils.isNotEmpty(tenantMeterQueryParam.getMeterCode()), TenantMeter::getMeterCode, tenantMeterQueryParam.getMeterCode())
				.eq(StringUtils.isNotEmpty(tenantMeterQueryParam.getMeterAddress()), TenantMeter::getMeterAddress, tenantMeterQueryParam.getMeterAddress())
				.eq(StringUtils.isNotEmpty(tenantMeterQueryParam.getMeterBookId()), TenantMeter::getMeterBookId, tenantMeterQueryParam.getMeterBookId())
				.eq(tenantMeterQueryParam.getMeterPeoples() != null, TenantMeter::getMeterPeoples, tenantMeterQueryParam.getMeterPeoples())
				.eq(tenantMeterQueryParam.getMeterStatus() != null, TenantMeter::getMeterStatus, tenantMeterQueryParam.getMeterStatus())
				.eq(StringUtils.isNotEmpty(tenantMeterQueryParam.getMeterBrandId()), TenantMeter::getMeterBrandId, tenantMeterQueryParam.getMeterBrandId())
				.eq(StringUtils.isNotEmpty(tenantMeterQueryParam.getMeterCaliberId()), TenantMeter::getMeterCaliberId, tenantMeterQueryParam.getMeterCaliberId())
				.eq(StringUtils.isNotEmpty(tenantMeterQueryParam.getMeterTypeId()), TenantMeter::getMeterTypeId, tenantMeterQueryParam.getMeterTypeId())
				.eq(StringUtils.isNotEmpty(tenantMeterQueryParam.getMeterModelId()), TenantMeter::getMeterModelId, tenantMeterQueryParam.getMeterModelId())
				.eq(StringUtils.isNotEmpty(tenantMeterQueryParam.getMeterMarketingAreaId()), TenantMeter::getMeterMarketingAreaId, tenantMeterQueryParam.getMeterMarketingAreaId())
				.eq(StringUtils.isNotEmpty(tenantMeterQueryParam.getMeterSupplyAreaId()), TenantMeter::getMeterSupplyAreaId, tenantMeterQueryParam.getMeterSupplyAreaId())
				.eq(StringUtils.isNotEmpty(tenantMeterQueryParam.getMeterIndustryId()), TenantMeter::getMeterIndustryId, tenantMeterQueryParam.getMeterIndustryId())
				.eq(tenantMeterQueryParam.getMeterUseType() != null, TenantMeter::getMeterUseType, tenantMeterQueryParam.getMeterUseType())
				.eq(tenantMeterQueryParam.getMeterSaveWater() != null, TenantMeter::getMeterSaveWater, tenantMeterQueryParam.getMeterSaveWater())
				.eq(tenantMeterQueryParam.getMeterNewFlag() != null, TenantMeter::getMeterNewFlag, tenantMeterQueryParam.getMeterNewFlag())
				.eq(StringUtils.isNotEmpty(tenantMeterQueryParam.getMeterGpsX()), TenantMeter::getMeterGpsX, tenantMeterQueryParam.getMeterGpsX())
				.eq(StringUtils.isNotEmpty(tenantMeterQueryParam.getMeterGpsY()), TenantMeter::getMeterGpsY, tenantMeterQueryParam.getMeterGpsY())
				.eq(StringUtils.isNotEmpty(tenantMeterQueryParam.getMeterMachineCode()), TenantMeter::getMeterMachineCode, tenantMeterQueryParam.getMeterMachineCode())
				.eq(StringUtils.isNotEmpty(tenantMeterQueryParam.getMeterRemoteCode()), TenantMeter::getMeterRemoteCode, tenantMeterQueryParam.getMeterRemoteCode())
				.eq(tenantMeterQueryParam.getMeterInstallDate() != null, TenantMeter::getMeterInstallDate, tenantMeterQueryParam.getMeterInstallDate())
				.ge(tenantMeterQueryParam.getMeterInstallDateStart() != null, TenantMeter::getMeterInstallDate,tenantMeterQueryParam.getMeterInstallDateStart() == null ? null: DateUtil.beginOfDay(tenantMeterQueryParam.getMeterInstallDateStart()))
				.le(tenantMeterQueryParam.getMeterInstallDateEnd() != null, TenantMeter::getMeterInstallDate,tenantMeterQueryParam.getMeterInstallDateEnd() == null ? null: DateUtil.endOfDay(tenantMeterQueryParam.getMeterInstallDateEnd()))
				.eq(tenantMeterQueryParam.getMeterRegisterTime() != null, TenantMeter::getMeterRegisterTime, tenantMeterQueryParam.getMeterRegisterTime())
				.ge(tenantMeterQueryParam.getMeterRegisterTimeStart() != null, TenantMeter::getMeterRegisterTime,tenantMeterQueryParam.getMeterRegisterTimeStart() == null ? null: DateUtil.beginOfDay(tenantMeterQueryParam.getMeterRegisterTimeStart()))
				.le(tenantMeterQueryParam.getMeterRegisterTimeEnd() != null, TenantMeter::getMeterRegisterTime,tenantMeterQueryParam.getMeterRegisterTimeEnd() == null ? null: DateUtil.endOfDay(tenantMeterQueryParam.getMeterRegisterTimeEnd()))
				.eq(StringUtils.isNotEmpty(tenantMeterQueryParam.getMeterInstallPer()), TenantMeter::getMeterInstallPer, tenantMeterQueryParam.getMeterInstallPer())
				.eq(tenantMeterQueryParam.getMeterReadOrder() != null, TenantMeter::getMeterReadOrder, tenantMeterQueryParam.getMeterReadOrder())
				.eq(tenantMeterQueryParam.getMeterReadCode() != null, TenantMeter::getMeterReadCode, tenantMeterQueryParam.getMeterReadCode())
				.eq(tenantMeterQueryParam.getMeterReadDate() != null, TenantMeter::getMeterReadDate, tenantMeterQueryParam.getMeterReadDate())
				.ge(tenantMeterQueryParam.getMeterReadDateStart() != null, TenantMeter::getMeterReadDate,tenantMeterQueryParam.getMeterReadDateStart() == null ? null: DateUtil.beginOfDay(tenantMeterQueryParam.getMeterReadDateStart()))
				.le(tenantMeterQueryParam.getMeterReadDateEnd() != null, TenantMeter::getMeterReadDate,tenantMeterQueryParam.getMeterReadDateEnd() == null ? null: DateUtil.endOfDay(tenantMeterQueryParam.getMeterReadDateEnd()))
				.eq(tenantMeterQueryParam.getMeterSettleCode() != null, TenantMeter::getMeterSettleCode, tenantMeterQueryParam.getMeterSettleCode())
				.eq(tenantMeterQueryParam.getMeterSettleDate() != null, TenantMeter::getMeterSettleDate, tenantMeterQueryParam.getMeterSettleDate())
				.ge(tenantMeterQueryParam.getMeterSettleDateStart() != null, TenantMeter::getMeterSettleDate,tenantMeterQueryParam.getMeterSettleDateStart() == null ? null: DateUtil.beginOfDay(tenantMeterQueryParam.getMeterSettleDateStart()))
				.le(tenantMeterQueryParam.getMeterSettleDateEnd() != null, TenantMeter::getMeterSettleDate,tenantMeterQueryParam.getMeterSettleDateEnd() == null ? null: DateUtil.endOfDay(tenantMeterQueryParam.getMeterSettleDateEnd()))
				.eq(tenantMeterQueryParam.getMeterOweAmt() != null, TenantMeter::getMeterOweAmt, tenantMeterQueryParam.getMeterOweAmt())
				.eq(tenantMeterQueryParam.getMeterPenaltyAmt() != null, TenantMeter::getMeterPenaltyAmt, tenantMeterQueryParam.getMeterPenaltyAmt())
				.eq(tenantMeterQueryParam.getMeterYearTotalWaters() != null, TenantMeter::getMeterYearTotalWaters, tenantMeterQueryParam.getMeterYearTotalWaters())
				.eq(tenantMeterQueryParam.getMeterTotalWaters() != null, TenantMeter::getMeterTotalWaters, tenantMeterQueryParam.getMeterTotalWaters())
				.eq(tenantMeterQueryParam.getMeterPriceStepDate() != null, TenantMeter::getMeterPriceStepDate, tenantMeterQueryParam.getMeterPriceStepDate())
				.ge(tenantMeterQueryParam.getMeterPriceStepDateStart() != null, TenantMeter::getMeterPriceStepDate,tenantMeterQueryParam.getMeterPriceStepDateStart() == null ? null: DateUtil.beginOfDay(tenantMeterQueryParam.getMeterPriceStepDateStart()))
				.le(tenantMeterQueryParam.getMeterPriceStepDateEnd() != null, TenantMeter::getMeterPriceStepDate,tenantMeterQueryParam.getMeterPriceStepDateEnd() == null ? null: DateUtil.endOfDay(tenantMeterQueryParam.getMeterPriceStepDateEnd()))
				.eq(tenantMeterQueryParam.getMeterPriceStepWaters() != null, TenantMeter::getMeterPriceStepWaters, tenantMeterQueryParam.getMeterPriceStepWaters())
				.eq(StringUtils.isNotEmpty(tenantMeterQueryParam.getMeterMemo()), TenantMeter::getMeterMemo, tenantMeterQueryParam.getMeterMemo())
				.eq(tenantMeterQueryParam.getAddTime() != null, TenantMeter::getAddTime, tenantMeterQueryParam.getAddTime())
				.ge(tenantMeterQueryParam.getAddTimeStart() != null, TenantMeter::getAddTime,tenantMeterQueryParam.getAddTimeStart() == null ? null: DateUtil.beginOfDay(tenantMeterQueryParam.getAddTimeStart()))
				.le(tenantMeterQueryParam.getAddTimeEnd() != null, TenantMeter::getAddTime,tenantMeterQueryParam.getAddTimeEnd() == null ? null: DateUtil.endOfDay(tenantMeterQueryParam.getAddTimeEnd()))
				.eq(tenantMeterQueryParam.getUpdateTime() != null, TenantMeter::getUpdateTime, tenantMeterQueryParam.getUpdateTime())
				.ge(tenantMeterQueryParam.getUpdateTimeStart() != null, TenantMeter::getUpdateTime,tenantMeterQueryParam.getUpdateTimeStart() == null ? null: DateUtil.beginOfDay(tenantMeterQueryParam.getUpdateTimeStart()))
				.le(tenantMeterQueryParam.getUpdateTimeEnd() != null, TenantMeter::getUpdateTime,tenantMeterQueryParam.getUpdateTimeEnd() == null ? null: DateUtil.endOfDay(tenantMeterQueryParam.getUpdateTimeEnd()))
				;

		int count = tenantMeterService.count(queryWrapperTenantMeter);

		return count;
	}
	
	@ApiOperation(value = "根据参数查询水表信息列表")
	@RequestMapping(value = "/tenant-meters", method = RequestMethod.GET)
	public Page<TenantMeterVo> page(@RequestBody TenantMeterQueryParam tenantMeterQueryParam,
			@RequestParam(value = "page", defaultValue = "1") int page, //
			@RequestParam(value = "rows", defaultValue = "10") int rows, //
			@RequestParam(value = "sort", required = false) String sort, // 排序列字段名
			@RequestParam(value = "order", required = false) String order // 可以是 'asc' 或者 'desc'，默认值是 'asc'
	) {
		IPage<TenantMeter> pageTenantMeter = new Page<TenantMeter>(page, rows);
		QueryWrapper<TenantMeter> queryWrapperTenantMeter = new QueryWrapper<TenantMeter>();
		queryWrapperTenantMeter.orderBy(StringUtils.isNotBlank(sort), "asc".equals(order), sort);
		queryWrapperTenantMeter.lambda()
				.eq(StringUtils.isNotEmpty(tenantMeterQueryParam.getId()), TenantMeter::getId, tenantMeterQueryParam.getId())
				.eq(StringUtils.isNotEmpty(tenantMeterQueryParam.getTenantId()), TenantMeter::getTenantId, tenantMeterQueryParam.getTenantId())
				.eq(StringUtils.isNotEmpty(tenantMeterQueryParam.getCustomerId()), TenantMeter::getCustomerId, tenantMeterQueryParam.getCustomerId())
				.eq(StringUtils.isNotEmpty(tenantMeterQueryParam.getCustomerCode()), TenantMeter::getCustomerCode, tenantMeterQueryParam.getCustomerCode())
				.eq(StringUtils.isNotEmpty(tenantMeterQueryParam.getMeterCode()), TenantMeter::getMeterCode, tenantMeterQueryParam.getMeterCode())
				.eq(StringUtils.isNotEmpty(tenantMeterQueryParam.getMeterAddress()), TenantMeter::getMeterAddress, tenantMeterQueryParam.getMeterAddress())
				.eq(StringUtils.isNotEmpty(tenantMeterQueryParam.getMeterBookId()), TenantMeter::getMeterBookId, tenantMeterQueryParam.getMeterBookId())
				.eq(tenantMeterQueryParam.getMeterPeoples() != null, TenantMeter::getMeterPeoples, tenantMeterQueryParam.getMeterPeoples())
				.eq(tenantMeterQueryParam.getMeterStatus() != null, TenantMeter::getMeterStatus, tenantMeterQueryParam.getMeterStatus())
				.eq(StringUtils.isNotEmpty(tenantMeterQueryParam.getMeterBrandId()), TenantMeter::getMeterBrandId, tenantMeterQueryParam.getMeterBrandId())
				.eq(StringUtils.isNotEmpty(tenantMeterQueryParam.getMeterCaliberId()), TenantMeter::getMeterCaliberId, tenantMeterQueryParam.getMeterCaliberId())
				.eq(StringUtils.isNotEmpty(tenantMeterQueryParam.getMeterTypeId()), TenantMeter::getMeterTypeId, tenantMeterQueryParam.getMeterTypeId())
				.eq(StringUtils.isNotEmpty(tenantMeterQueryParam.getMeterModelId()), TenantMeter::getMeterModelId, tenantMeterQueryParam.getMeterModelId())
				.eq(StringUtils.isNotEmpty(tenantMeterQueryParam.getMeterMarketingAreaId()), TenantMeter::getMeterMarketingAreaId, tenantMeterQueryParam.getMeterMarketingAreaId())
				.eq(StringUtils.isNotEmpty(tenantMeterQueryParam.getMeterSupplyAreaId()), TenantMeter::getMeterSupplyAreaId, tenantMeterQueryParam.getMeterSupplyAreaId())
				.eq(StringUtils.isNotEmpty(tenantMeterQueryParam.getMeterIndustryId()), TenantMeter::getMeterIndustryId, tenantMeterQueryParam.getMeterIndustryId())
				.eq(tenantMeterQueryParam.getMeterUseType() != null, TenantMeter::getMeterUseType, tenantMeterQueryParam.getMeterUseType())
				.eq(tenantMeterQueryParam.getMeterSaveWater() != null, TenantMeter::getMeterSaveWater, tenantMeterQueryParam.getMeterSaveWater())
				.eq(tenantMeterQueryParam.getMeterNewFlag() != null, TenantMeter::getMeterNewFlag, tenantMeterQueryParam.getMeterNewFlag())
				.eq(StringUtils.isNotEmpty(tenantMeterQueryParam.getMeterGpsX()), TenantMeter::getMeterGpsX, tenantMeterQueryParam.getMeterGpsX())
				.eq(StringUtils.isNotEmpty(tenantMeterQueryParam.getMeterGpsY()), TenantMeter::getMeterGpsY, tenantMeterQueryParam.getMeterGpsY())
				.eq(StringUtils.isNotEmpty(tenantMeterQueryParam.getMeterMachineCode()), TenantMeter::getMeterMachineCode, tenantMeterQueryParam.getMeterMachineCode())
				.eq(StringUtils.isNotEmpty(tenantMeterQueryParam.getMeterRemoteCode()), TenantMeter::getMeterRemoteCode, tenantMeterQueryParam.getMeterRemoteCode())
				.eq(tenantMeterQueryParam.getMeterInstallDate() != null, TenantMeter::getMeterInstallDate, tenantMeterQueryParam.getMeterInstallDate())
				.ge(tenantMeterQueryParam.getMeterInstallDateStart() != null, TenantMeter::getMeterInstallDate,tenantMeterQueryParam.getMeterInstallDateStart() == null ? null: DateUtil.beginOfDay(tenantMeterQueryParam.getMeterInstallDateStart()))
				.le(tenantMeterQueryParam.getMeterInstallDateEnd() != null, TenantMeter::getMeterInstallDate,tenantMeterQueryParam.getMeterInstallDateEnd() == null ? null: DateUtil.endOfDay(tenantMeterQueryParam.getMeterInstallDateEnd()))
				.eq(tenantMeterQueryParam.getMeterRegisterTime() != null, TenantMeter::getMeterRegisterTime, tenantMeterQueryParam.getMeterRegisterTime())
				.ge(tenantMeterQueryParam.getMeterRegisterTimeStart() != null, TenantMeter::getMeterRegisterTime,tenantMeterQueryParam.getMeterRegisterTimeStart() == null ? null: DateUtil.beginOfDay(tenantMeterQueryParam.getMeterRegisterTimeStart()))
				.le(tenantMeterQueryParam.getMeterRegisterTimeEnd() != null, TenantMeter::getMeterRegisterTime,tenantMeterQueryParam.getMeterRegisterTimeEnd() == null ? null: DateUtil.endOfDay(tenantMeterQueryParam.getMeterRegisterTimeEnd()))
				.eq(StringUtils.isNotEmpty(tenantMeterQueryParam.getMeterInstallPer()), TenantMeter::getMeterInstallPer, tenantMeterQueryParam.getMeterInstallPer())
				.eq(tenantMeterQueryParam.getMeterReadOrder() != null, TenantMeter::getMeterReadOrder, tenantMeterQueryParam.getMeterReadOrder())
				.eq(tenantMeterQueryParam.getMeterReadCode() != null, TenantMeter::getMeterReadCode, tenantMeterQueryParam.getMeterReadCode())
				.eq(tenantMeterQueryParam.getMeterReadDate() != null, TenantMeter::getMeterReadDate, tenantMeterQueryParam.getMeterReadDate())
				.ge(tenantMeterQueryParam.getMeterReadDateStart() != null, TenantMeter::getMeterReadDate,tenantMeterQueryParam.getMeterReadDateStart() == null ? null: DateUtil.beginOfDay(tenantMeterQueryParam.getMeterReadDateStart()))
				.le(tenantMeterQueryParam.getMeterReadDateEnd() != null, TenantMeter::getMeterReadDate,tenantMeterQueryParam.getMeterReadDateEnd() == null ? null: DateUtil.endOfDay(tenantMeterQueryParam.getMeterReadDateEnd()))
				.eq(tenantMeterQueryParam.getMeterSettleCode() != null, TenantMeter::getMeterSettleCode, tenantMeterQueryParam.getMeterSettleCode())
				.eq(tenantMeterQueryParam.getMeterSettleDate() != null, TenantMeter::getMeterSettleDate, tenantMeterQueryParam.getMeterSettleDate())
				.ge(tenantMeterQueryParam.getMeterSettleDateStart() != null, TenantMeter::getMeterSettleDate,tenantMeterQueryParam.getMeterSettleDateStart() == null ? null: DateUtil.beginOfDay(tenantMeterQueryParam.getMeterSettleDateStart()))
				.le(tenantMeterQueryParam.getMeterSettleDateEnd() != null, TenantMeter::getMeterSettleDate,tenantMeterQueryParam.getMeterSettleDateEnd() == null ? null: DateUtil.endOfDay(tenantMeterQueryParam.getMeterSettleDateEnd()))
				.eq(tenantMeterQueryParam.getMeterOweAmt() != null, TenantMeter::getMeterOweAmt, tenantMeterQueryParam.getMeterOweAmt())
				.eq(tenantMeterQueryParam.getMeterPenaltyAmt() != null, TenantMeter::getMeterPenaltyAmt, tenantMeterQueryParam.getMeterPenaltyAmt())
				.eq(tenantMeterQueryParam.getMeterYearTotalWaters() != null, TenantMeter::getMeterYearTotalWaters, tenantMeterQueryParam.getMeterYearTotalWaters())
				.eq(tenantMeterQueryParam.getMeterTotalWaters() != null, TenantMeter::getMeterTotalWaters, tenantMeterQueryParam.getMeterTotalWaters())
				.eq(tenantMeterQueryParam.getMeterPriceStepDate() != null, TenantMeter::getMeterPriceStepDate, tenantMeterQueryParam.getMeterPriceStepDate())
				.ge(tenantMeterQueryParam.getMeterPriceStepDateStart() != null, TenantMeter::getMeterPriceStepDate,tenantMeterQueryParam.getMeterPriceStepDateStart() == null ? null: DateUtil.beginOfDay(tenantMeterQueryParam.getMeterPriceStepDateStart()))
				.le(tenantMeterQueryParam.getMeterPriceStepDateEnd() != null, TenantMeter::getMeterPriceStepDate,tenantMeterQueryParam.getMeterPriceStepDateEnd() == null ? null: DateUtil.endOfDay(tenantMeterQueryParam.getMeterPriceStepDateEnd()))
				.eq(tenantMeterQueryParam.getMeterPriceStepWaters() != null, TenantMeter::getMeterPriceStepWaters, tenantMeterQueryParam.getMeterPriceStepWaters())
				.eq(StringUtils.isNotEmpty(tenantMeterQueryParam.getMeterMemo()), TenantMeter::getMeterMemo, tenantMeterQueryParam.getMeterMemo())
				.eq(tenantMeterQueryParam.getAddTime() != null, TenantMeter::getAddTime, tenantMeterQueryParam.getAddTime())
				.ge(tenantMeterQueryParam.getAddTimeStart() != null, TenantMeter::getAddTime,tenantMeterQueryParam.getAddTimeStart() == null ? null: DateUtil.beginOfDay(tenantMeterQueryParam.getAddTimeStart()))
				.le(tenantMeterQueryParam.getAddTimeEnd() != null, TenantMeter::getAddTime,tenantMeterQueryParam.getAddTimeEnd() == null ? null: DateUtil.endOfDay(tenantMeterQueryParam.getAddTimeEnd()))
				.eq(tenantMeterQueryParam.getUpdateTime() != null, TenantMeter::getUpdateTime, tenantMeterQueryParam.getUpdateTime())
				.ge(tenantMeterQueryParam.getUpdateTimeStart() != null, TenantMeter::getUpdateTime,tenantMeterQueryParam.getUpdateTimeStart() == null ? null: DateUtil.beginOfDay(tenantMeterQueryParam.getUpdateTimeStart()))
				.le(tenantMeterQueryParam.getUpdateTimeEnd() != null, TenantMeter::getUpdateTime,tenantMeterQueryParam.getUpdateTimeEnd() == null ? null: DateUtil.endOfDay(tenantMeterQueryParam.getUpdateTimeEnd()))
				;

		IPage<TenantMeter> tenantMeterPage = tenantMeterService.page(pageTenantMeter, queryWrapperTenantMeter);

		Page<TenantMeterVo> tenantMeterVoPage = new Page<TenantMeterVo>(page, rows);
		tenantMeterVoPage.setCurrent(tenantMeterPage.getCurrent());
		tenantMeterVoPage.setPages(tenantMeterPage.getPages());
		tenantMeterVoPage.setSize(tenantMeterPage.getSize());
		tenantMeterVoPage.setTotal(tenantMeterPage.getTotal());
		tenantMeterVoPage.setRecords(tenantMeterPage.getRecords().stream()//
				.map(e -> entity2vo(e))//
				.collect(Collectors.toList()));

		return tenantMeterVoPage;
	}
	
	@ApiOperation(value = "根据参数查询水表信息统计")
	@RequestMapping(value = "/tenant-meters/aggregation", method = RequestMethod.GET)
	public TenantMeterVo aggregation(@RequestBody TenantMeterQueryParam tenantMeterQueryParam) {
		QueryWrapper<TenantMeter> queryWrapperTenantMeter = new QueryWrapper<TenantMeter>();
		queryWrapperTenantMeter.lambda()
				.eq(StringUtils.isNotEmpty(tenantMeterQueryParam.getId()), TenantMeter::getId, tenantMeterQueryParam.getId())
				.eq(StringUtils.isNotEmpty(tenantMeterQueryParam.getTenantId()), TenantMeter::getTenantId, tenantMeterQueryParam.getTenantId())
				.eq(StringUtils.isNotEmpty(tenantMeterQueryParam.getCustomerId()), TenantMeter::getCustomerId, tenantMeterQueryParam.getCustomerId())
				.eq(StringUtils.isNotEmpty(tenantMeterQueryParam.getCustomerCode()), TenantMeter::getCustomerCode, tenantMeterQueryParam.getCustomerCode())
				.eq(StringUtils.isNotEmpty(tenantMeterQueryParam.getMeterCode()), TenantMeter::getMeterCode, tenantMeterQueryParam.getMeterCode())
				.eq(StringUtils.isNotEmpty(tenantMeterQueryParam.getMeterAddress()), TenantMeter::getMeterAddress, tenantMeterQueryParam.getMeterAddress())
				.eq(StringUtils.isNotEmpty(tenantMeterQueryParam.getMeterBookId()), TenantMeter::getMeterBookId, tenantMeterQueryParam.getMeterBookId())
				.eq(tenantMeterQueryParam.getMeterPeoples() != null, TenantMeter::getMeterPeoples, tenantMeterQueryParam.getMeterPeoples())
				.eq(tenantMeterQueryParam.getMeterStatus() != null, TenantMeter::getMeterStatus, tenantMeterQueryParam.getMeterStatus())
				.eq(StringUtils.isNotEmpty(tenantMeterQueryParam.getMeterBrandId()), TenantMeter::getMeterBrandId, tenantMeterQueryParam.getMeterBrandId())
				.eq(StringUtils.isNotEmpty(tenantMeterQueryParam.getMeterCaliberId()), TenantMeter::getMeterCaliberId, tenantMeterQueryParam.getMeterCaliberId())
				.eq(StringUtils.isNotEmpty(tenantMeterQueryParam.getMeterTypeId()), TenantMeter::getMeterTypeId, tenantMeterQueryParam.getMeterTypeId())
				.eq(StringUtils.isNotEmpty(tenantMeterQueryParam.getMeterModelId()), TenantMeter::getMeterModelId, tenantMeterQueryParam.getMeterModelId())
				.eq(StringUtils.isNotEmpty(tenantMeterQueryParam.getMeterMarketingAreaId()), TenantMeter::getMeterMarketingAreaId, tenantMeterQueryParam.getMeterMarketingAreaId())
				.eq(StringUtils.isNotEmpty(tenantMeterQueryParam.getMeterSupplyAreaId()), TenantMeter::getMeterSupplyAreaId, tenantMeterQueryParam.getMeterSupplyAreaId())
				.eq(StringUtils.isNotEmpty(tenantMeterQueryParam.getMeterIndustryId()), TenantMeter::getMeterIndustryId, tenantMeterQueryParam.getMeterIndustryId())
				.eq(tenantMeterQueryParam.getMeterUseType() != null, TenantMeter::getMeterUseType, tenantMeterQueryParam.getMeterUseType())
				.eq(tenantMeterQueryParam.getMeterSaveWater() != null, TenantMeter::getMeterSaveWater, tenantMeterQueryParam.getMeterSaveWater())
				.eq(tenantMeterQueryParam.getMeterNewFlag() != null, TenantMeter::getMeterNewFlag, tenantMeterQueryParam.getMeterNewFlag())
				.eq(StringUtils.isNotEmpty(tenantMeterQueryParam.getMeterGpsX()), TenantMeter::getMeterGpsX, tenantMeterQueryParam.getMeterGpsX())
				.eq(StringUtils.isNotEmpty(tenantMeterQueryParam.getMeterGpsY()), TenantMeter::getMeterGpsY, tenantMeterQueryParam.getMeterGpsY())
				.eq(StringUtils.isNotEmpty(tenantMeterQueryParam.getMeterMachineCode()), TenantMeter::getMeterMachineCode, tenantMeterQueryParam.getMeterMachineCode())
				.eq(StringUtils.isNotEmpty(tenantMeterQueryParam.getMeterRemoteCode()), TenantMeter::getMeterRemoteCode, tenantMeterQueryParam.getMeterRemoteCode())
				.eq(tenantMeterQueryParam.getMeterInstallDate() != null, TenantMeter::getMeterInstallDate, tenantMeterQueryParam.getMeterInstallDate())
				.ge(tenantMeterQueryParam.getMeterInstallDateStart() != null, TenantMeter::getMeterInstallDate,tenantMeterQueryParam.getMeterInstallDateStart() == null ? null: DateUtil.beginOfDay(tenantMeterQueryParam.getMeterInstallDateStart()))
				.le(tenantMeterQueryParam.getMeterInstallDateEnd() != null, TenantMeter::getMeterInstallDate,tenantMeterQueryParam.getMeterInstallDateEnd() == null ? null: DateUtil.endOfDay(tenantMeterQueryParam.getMeterInstallDateEnd()))
				.eq(tenantMeterQueryParam.getMeterRegisterTime() != null, TenantMeter::getMeterRegisterTime, tenantMeterQueryParam.getMeterRegisterTime())
				.ge(tenantMeterQueryParam.getMeterRegisterTimeStart() != null, TenantMeter::getMeterRegisterTime,tenantMeterQueryParam.getMeterRegisterTimeStart() == null ? null: DateUtil.beginOfDay(tenantMeterQueryParam.getMeterRegisterTimeStart()))
				.le(tenantMeterQueryParam.getMeterRegisterTimeEnd() != null, TenantMeter::getMeterRegisterTime,tenantMeterQueryParam.getMeterRegisterTimeEnd() == null ? null: DateUtil.endOfDay(tenantMeterQueryParam.getMeterRegisterTimeEnd()))
				.eq(StringUtils.isNotEmpty(tenantMeterQueryParam.getMeterInstallPer()), TenantMeter::getMeterInstallPer, tenantMeterQueryParam.getMeterInstallPer())
				.eq(tenantMeterQueryParam.getMeterReadOrder() != null, TenantMeter::getMeterReadOrder, tenantMeterQueryParam.getMeterReadOrder())
				.eq(tenantMeterQueryParam.getMeterReadCode() != null, TenantMeter::getMeterReadCode, tenantMeterQueryParam.getMeterReadCode())
				.eq(tenantMeterQueryParam.getMeterReadDate() != null, TenantMeter::getMeterReadDate, tenantMeterQueryParam.getMeterReadDate())
				.ge(tenantMeterQueryParam.getMeterReadDateStart() != null, TenantMeter::getMeterReadDate,tenantMeterQueryParam.getMeterReadDateStart() == null ? null: DateUtil.beginOfDay(tenantMeterQueryParam.getMeterReadDateStart()))
				.le(tenantMeterQueryParam.getMeterReadDateEnd() != null, TenantMeter::getMeterReadDate,tenantMeterQueryParam.getMeterReadDateEnd() == null ? null: DateUtil.endOfDay(tenantMeterQueryParam.getMeterReadDateEnd()))
				.eq(tenantMeterQueryParam.getMeterSettleCode() != null, TenantMeter::getMeterSettleCode, tenantMeterQueryParam.getMeterSettleCode())
				.eq(tenantMeterQueryParam.getMeterSettleDate() != null, TenantMeter::getMeterSettleDate, tenantMeterQueryParam.getMeterSettleDate())
				.ge(tenantMeterQueryParam.getMeterSettleDateStart() != null, TenantMeter::getMeterSettleDate,tenantMeterQueryParam.getMeterSettleDateStart() == null ? null: DateUtil.beginOfDay(tenantMeterQueryParam.getMeterSettleDateStart()))
				.le(tenantMeterQueryParam.getMeterSettleDateEnd() != null, TenantMeter::getMeterSettleDate,tenantMeterQueryParam.getMeterSettleDateEnd() == null ? null: DateUtil.endOfDay(tenantMeterQueryParam.getMeterSettleDateEnd()))
				.eq(tenantMeterQueryParam.getMeterOweAmt() != null, TenantMeter::getMeterOweAmt, tenantMeterQueryParam.getMeterOweAmt())
				.eq(tenantMeterQueryParam.getMeterPenaltyAmt() != null, TenantMeter::getMeterPenaltyAmt, tenantMeterQueryParam.getMeterPenaltyAmt())
				.eq(tenantMeterQueryParam.getMeterYearTotalWaters() != null, TenantMeter::getMeterYearTotalWaters, tenantMeterQueryParam.getMeterYearTotalWaters())
				.eq(tenantMeterQueryParam.getMeterTotalWaters() != null, TenantMeter::getMeterTotalWaters, tenantMeterQueryParam.getMeterTotalWaters())
				.eq(tenantMeterQueryParam.getMeterPriceStepDate() != null, TenantMeter::getMeterPriceStepDate, tenantMeterQueryParam.getMeterPriceStepDate())
				.ge(tenantMeterQueryParam.getMeterPriceStepDateStart() != null, TenantMeter::getMeterPriceStepDate,tenantMeterQueryParam.getMeterPriceStepDateStart() == null ? null: DateUtil.beginOfDay(tenantMeterQueryParam.getMeterPriceStepDateStart()))
				.le(tenantMeterQueryParam.getMeterPriceStepDateEnd() != null, TenantMeter::getMeterPriceStepDate,tenantMeterQueryParam.getMeterPriceStepDateEnd() == null ? null: DateUtil.endOfDay(tenantMeterQueryParam.getMeterPriceStepDateEnd()))
				.eq(tenantMeterQueryParam.getMeterPriceStepWaters() != null, TenantMeter::getMeterPriceStepWaters, tenantMeterQueryParam.getMeterPriceStepWaters())
				.eq(StringUtils.isNotEmpty(tenantMeterQueryParam.getMeterMemo()), TenantMeter::getMeterMemo, tenantMeterQueryParam.getMeterMemo())
				.eq(tenantMeterQueryParam.getAddTime() != null, TenantMeter::getAddTime, tenantMeterQueryParam.getAddTime())
				.ge(tenantMeterQueryParam.getAddTimeStart() != null, TenantMeter::getAddTime,tenantMeterQueryParam.getAddTimeStart() == null ? null: DateUtil.beginOfDay(tenantMeterQueryParam.getAddTimeStart()))
				.le(tenantMeterQueryParam.getAddTimeEnd() != null, TenantMeter::getAddTime,tenantMeterQueryParam.getAddTimeEnd() == null ? null: DateUtil.endOfDay(tenantMeterQueryParam.getAddTimeEnd()))
				.eq(tenantMeterQueryParam.getUpdateTime() != null, TenantMeter::getUpdateTime, tenantMeterQueryParam.getUpdateTime())
				.ge(tenantMeterQueryParam.getUpdateTimeStart() != null, TenantMeter::getUpdateTime,tenantMeterQueryParam.getUpdateTimeStart() == null ? null: DateUtil.beginOfDay(tenantMeterQueryParam.getUpdateTimeStart()))
				.le(tenantMeterQueryParam.getUpdateTimeEnd() != null, TenantMeter::getUpdateTime,tenantMeterQueryParam.getUpdateTimeEnd() == null ? null: DateUtil.endOfDay(tenantMeterQueryParam.getUpdateTimeEnd()))
				;

		TenantMeter tenantMeter = tenantMeterService.getAggregation(queryWrapperTenantMeter);
		
		return entity2vo(tenantMeter);
	}

	@ApiOperation(value = "新增水表信息")
	@RequestMapping(value = "/tenant-meters", method = RequestMethod.POST)
	public String save(@RequestBody TenantMeterAddParam tenantMeterAddParam) {
		return tenantMeterService.save(tenantMeterAddParam);
	}

	@ApiOperation(value = "更新水表信息全部信息")
	@RequestMapping(value = "/tenant-meters/{id}", method = RequestMethod.PUT)
	public boolean updateById(@PathVariable("id") String id, @RequestBody TenantMeterUpdateParam tenantMeterUpdateParam) {
		tenantMeterUpdateParam.setId(id);
		return tenantMeterService.updateById(tenantMeterUpdateParam);
	}

	@ApiOperation(value = "根据ID删除水表信息")
	@RequestMapping(value = "/tenant-meters/{id}", method = RequestMethod.DELETE)
	public CommonResult<Object> removeById(@PathVariable("id") String id) {
		boolean success = tenantMeterService.removeById(id);
		return success ? CommonResult.success(success) : CommonResult.failed();
	}

	private TenantMeterVo entity2vo(TenantMeter tenantMeter) {
		if (tenantMeter == null) {
			return null;
		}

		TenantMeterVo tenantMeterVo = TranslateUtil.translate(tenantMeter, TenantMeterVo.class);
		if (StringUtils.isEmpty(tenantMeterVo.getTenantName())) {
			TenantInfo tenantInfo = tenantInfoService.getDictionaryById(tenantMeter.getTenantId());
			if (tenantInfo != null) {
				tenantMeterVo.setTenantName(tenantInfo.getTenantName());
			}
		}
		return tenantMeterVo;
	}

}
