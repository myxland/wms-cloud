package com.zlsrj.wms.saas.rest;

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
import com.zlsrj.wms.api.dto.TenantCustomerMeterChangeLogQueryParam;
import com.zlsrj.wms.api.entity.TenantInfo;
import com.zlsrj.wms.api.entity.TenantCustomerMeterChangeLog;
import com.zlsrj.wms.api.vo.TenantCustomerMeterChangeLogVo;
import com.zlsrj.wms.common.api.CommonResult;
import com.zlsrj.wms.saas.service.IIdService;
import com.zlsrj.wms.saas.service.ITenantInfoService;
import com.zlsrj.wms.saas.service.ITenantCustomerMeterChangeLogService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

@Api(value = "信息变更", tags = { "信息变更操作接口" })
@RestController
@Slf4j
public class TenantCustomerMeterChangeLogRestController {

	@Autowired
	private ITenantCustomerMeterChangeLogService tenantCustomerMeterChangeLogService;
	@Autowired
	private ITenantInfoService tenantInfoService;
	@Autowired
	private IIdService idService;

	@ApiOperation(value = "根据ID查询信息变更")
	@RequestMapping(value = "/tenant-customer-meter-change-logs/{id}", method = RequestMethod.GET)
	public TenantCustomerMeterChangeLogVo getById(@PathVariable("id") String id) {
		TenantCustomerMeterChangeLog tenantCustomerMeterChangeLog = tenantCustomerMeterChangeLogService.getById(id);

		return entity2vo(tenantCustomerMeterChangeLog);
	}

	@ApiOperation(value = "根据参数查询信息变更列表")
	@RequestMapping(value = "/tenant-customer-meter-change-logs", method = RequestMethod.GET)
	public Page<TenantCustomerMeterChangeLogVo> page(@RequestBody TenantCustomerMeterChangeLogQueryParam tenantCustomerMeterChangeLogQueryParam,
			@RequestParam(value = "page", defaultValue = "1") int page, //
			@RequestParam(value = "rows", defaultValue = "10") int rows, //
			@RequestParam(value = "sort") String sort, // 排序列字段名
			@RequestParam(value = "order") String order // 可以是 'asc' 或者 'desc'，默认值是 'asc'
	) {
		IPage<TenantCustomerMeterChangeLog> pageTenantCustomerMeterChangeLog = new Page<TenantCustomerMeterChangeLog>(page, rows);
		QueryWrapper<TenantCustomerMeterChangeLog> queryWrapperTenantCustomerMeterChangeLog = new QueryWrapper<TenantCustomerMeterChangeLog>();
		queryWrapperTenantCustomerMeterChangeLog.orderBy(StringUtils.isNotEmpty(sort), "asc".equals(order), sort);
		queryWrapperTenantCustomerMeterChangeLog.lambda()
				.eq(tenantCustomerMeterChangeLogQueryParam.getId() != null, TenantCustomerMeterChangeLog::getId, tenantCustomerMeterChangeLogQueryParam.getId())
				.eq(tenantCustomerMeterChangeLogQueryParam.getTenantId() != null, TenantCustomerMeterChangeLog::getTenantId, tenantCustomerMeterChangeLogQueryParam.getTenantId())
				.eq(tenantCustomerMeterChangeLogQueryParam.getCustomerId() != null, TenantCustomerMeterChangeLog::getCustomerId, tenantCustomerMeterChangeLogQueryParam.getCustomerId())
				.eq(tenantCustomerMeterChangeLogQueryParam.getCsutomerIdNew() != null, TenantCustomerMeterChangeLog::getCsutomerIdNew, tenantCustomerMeterChangeLogQueryParam.getCsutomerIdNew())
				.eq(tenantCustomerMeterChangeLogQueryParam.getCustomerName() != null, TenantCustomerMeterChangeLog::getCustomerName, tenantCustomerMeterChangeLogQueryParam.getCustomerName())
				.eq(tenantCustomerMeterChangeLogQueryParam.getCustomerNameNew() != null, TenantCustomerMeterChangeLog::getCustomerNameNew, tenantCustomerMeterChangeLogQueryParam.getCustomerNameNew())
				.eq(tenantCustomerMeterChangeLogQueryParam.getCustomerAddress() != null, TenantCustomerMeterChangeLog::getCustomerAddress, tenantCustomerMeterChangeLogQueryParam.getCustomerAddress())
				.eq(tenantCustomerMeterChangeLogQueryParam.getCustomerAddressNew() != null, TenantCustomerMeterChangeLog::getCustomerAddressNew, tenantCustomerMeterChangeLogQueryParam.getCustomerAddressNew())
				.eq(tenantCustomerMeterChangeLogQueryParam.getCustomerTypeId() != null, TenantCustomerMeterChangeLog::getCustomerTypeId, tenantCustomerMeterChangeLogQueryParam.getCustomerTypeId())
				.eq(tenantCustomerMeterChangeLogQueryParam.getCustomerTypeIdNew() != null, TenantCustomerMeterChangeLog::getCustomerTypeIdNew, tenantCustomerMeterChangeLogQueryParam.getCustomerTypeIdNew())
				.eq(tenantCustomerMeterChangeLogQueryParam.getCustomerStatus() != null, TenantCustomerMeterChangeLog::getCustomerStatus, tenantCustomerMeterChangeLogQueryParam.getCustomerStatus())
				.eq(tenantCustomerMeterChangeLogQueryParam.getCustomerStatusNew() != null, TenantCustomerMeterChangeLog::getCustomerStatusNew, tenantCustomerMeterChangeLogQueryParam.getCustomerStatusNew())
				.eq(tenantCustomerMeterChangeLogQueryParam.getCustomerPaymentMethod() != null, TenantCustomerMeterChangeLog::getCustomerPaymentMethod, tenantCustomerMeterChangeLogQueryParam.getCustomerPaymentMethod())
				.eq(tenantCustomerMeterChangeLogQueryParam.getCustomerPaymentMethodNew() != null, TenantCustomerMeterChangeLog::getCustomerPaymentMethodNew, tenantCustomerMeterChangeLogQueryParam.getCustomerPaymentMethodNew())
				.eq(tenantCustomerMeterChangeLogQueryParam.getInvoiceType() != null, TenantCustomerMeterChangeLog::getInvoiceType, tenantCustomerMeterChangeLogQueryParam.getInvoiceType())
				.eq(tenantCustomerMeterChangeLogQueryParam.getInvoiceTypeNew() != null, TenantCustomerMeterChangeLog::getInvoiceTypeNew, tenantCustomerMeterChangeLogQueryParam.getInvoiceTypeNew())
				.eq(tenantCustomerMeterChangeLogQueryParam.getInvoiceName() != null, TenantCustomerMeterChangeLog::getInvoiceName, tenantCustomerMeterChangeLogQueryParam.getInvoiceName())
				.eq(tenantCustomerMeterChangeLogQueryParam.getInvoiceNameNew() != null, TenantCustomerMeterChangeLog::getInvoiceNameNew, tenantCustomerMeterChangeLogQueryParam.getInvoiceNameNew())
				.eq(tenantCustomerMeterChangeLogQueryParam.getInvoiceTaxNo() != null, TenantCustomerMeterChangeLog::getInvoiceTaxNo, tenantCustomerMeterChangeLogQueryParam.getInvoiceTaxNo())
				.eq(tenantCustomerMeterChangeLogQueryParam.getInvoiceTaxNoNew() != null, TenantCustomerMeterChangeLog::getInvoiceTaxNoNew, tenantCustomerMeterChangeLogQueryParam.getInvoiceTaxNoNew())
				.eq(tenantCustomerMeterChangeLogQueryParam.getInvoiceAddress() != null, TenantCustomerMeterChangeLog::getInvoiceAddress, tenantCustomerMeterChangeLogQueryParam.getInvoiceAddress())
				.eq(tenantCustomerMeterChangeLogQueryParam.getInvoiceAddressNew() != null, TenantCustomerMeterChangeLog::getInvoiceAddressNew, tenantCustomerMeterChangeLogQueryParam.getInvoiceAddressNew())
				.eq(tenantCustomerMeterChangeLogQueryParam.getInvoiceTel() != null, TenantCustomerMeterChangeLog::getInvoiceTel, tenantCustomerMeterChangeLogQueryParam.getInvoiceTel())
				.eq(tenantCustomerMeterChangeLogQueryParam.getInvoiceTelNew() != null, TenantCustomerMeterChangeLog::getInvoiceTelNew, tenantCustomerMeterChangeLogQueryParam.getInvoiceTelNew())
				.eq(tenantCustomerMeterChangeLogQueryParam.getInvoiceBankCode() != null, TenantCustomerMeterChangeLog::getInvoiceBankCode, tenantCustomerMeterChangeLogQueryParam.getInvoiceBankCode())
				.eq(tenantCustomerMeterChangeLogQueryParam.getInvoiceBankCodeNew() != null, TenantCustomerMeterChangeLog::getInvoiceBankCodeNew, tenantCustomerMeterChangeLogQueryParam.getInvoiceBankCodeNew())
				.eq(tenantCustomerMeterChangeLogQueryParam.getInvoiceBankName() != null, TenantCustomerMeterChangeLog::getInvoiceBankName, tenantCustomerMeterChangeLogQueryParam.getInvoiceBankName())
				.eq(tenantCustomerMeterChangeLogQueryParam.getInvoiceBankNameNew() != null, TenantCustomerMeterChangeLog::getInvoiceBankNameNew, tenantCustomerMeterChangeLogQueryParam.getInvoiceBankNameNew())
				.eq(tenantCustomerMeterChangeLogQueryParam.getInvoiceBankAccountNo() != null, TenantCustomerMeterChangeLog::getInvoiceBankAccountNo, tenantCustomerMeterChangeLogQueryParam.getInvoiceBankAccountNo())
				.eq(tenantCustomerMeterChangeLogQueryParam.getInvoiceBankAccountNoNew() != null, TenantCustomerMeterChangeLog::getInvoiceBankAccountNoNew, tenantCustomerMeterChangeLogQueryParam.getInvoiceBankAccountNoNew())
				.eq(tenantCustomerMeterChangeLogQueryParam.getMeterId() != null, TenantCustomerMeterChangeLog::getMeterId, tenantCustomerMeterChangeLogQueryParam.getMeterId())
				.eq(tenantCustomerMeterChangeLogQueryParam.getPriceTypeId() != null, TenantCustomerMeterChangeLog::getPriceTypeId, tenantCustomerMeterChangeLogQueryParam.getPriceTypeId())
				.eq(tenantCustomerMeterChangeLogQueryParam.getPriceTypeIdNew() != null, TenantCustomerMeterChangeLog::getPriceTypeIdNew, tenantCustomerMeterChangeLogQueryParam.getPriceTypeIdNew())
				.eq(tenantCustomerMeterChangeLogQueryParam.getMeterLastSettlePointer() != null, TenantCustomerMeterChangeLog::getMeterLastSettlePointer, tenantCustomerMeterChangeLogQueryParam.getMeterLastSettlePointer())
				.eq(tenantCustomerMeterChangeLogQueryParam.getMeterLastSettlePointerNew() != null, TenantCustomerMeterChangeLog::getMeterLastSettlePointerNew, tenantCustomerMeterChangeLogQueryParam.getMeterLastSettlePointerNew())
				.eq(tenantCustomerMeterChangeLogQueryParam.getManufactorId() != null, TenantCustomerMeterChangeLog::getManufactorId, tenantCustomerMeterChangeLogQueryParam.getManufactorId())
				.eq(tenantCustomerMeterChangeLogQueryParam.getManufactorIdNew() != null, TenantCustomerMeterChangeLog::getManufactorIdNew, tenantCustomerMeterChangeLogQueryParam.getManufactorIdNew())
				.eq(tenantCustomerMeterChangeLogQueryParam.getMeterType() != null, TenantCustomerMeterChangeLog::getMeterType, tenantCustomerMeterChangeLogQueryParam.getMeterType())
				.eq(tenantCustomerMeterChangeLogQueryParam.getMeterTypeNew() != null, TenantCustomerMeterChangeLog::getMeterTypeNew, tenantCustomerMeterChangeLogQueryParam.getMeterTypeNew())
				.eq(tenantCustomerMeterChangeLogQueryParam.getCaliberId() != null, TenantCustomerMeterChangeLog::getCaliberId, tenantCustomerMeterChangeLogQueryParam.getCaliberId())
				.eq(tenantCustomerMeterChangeLogQueryParam.getCaliberIdNew() != null, TenantCustomerMeterChangeLog::getCaliberIdNew, tenantCustomerMeterChangeLogQueryParam.getCaliberIdNew())
				.eq(tenantCustomerMeterChangeLogQueryParam.getMeterMachineCode() != null, TenantCustomerMeterChangeLog::getMeterMachineCode, tenantCustomerMeterChangeLogQueryParam.getMeterMachineCode())
				.eq(tenantCustomerMeterChangeLogQueryParam.getMeterMachineCodeNew() != null, TenantCustomerMeterChangeLog::getMeterMachineCodeNew, tenantCustomerMeterChangeLogQueryParam.getMeterMachineCodeNew())
				.eq(tenantCustomerMeterChangeLogQueryParam.getMeterIotCode() != null, TenantCustomerMeterChangeLog::getMeterIotCode, tenantCustomerMeterChangeLogQueryParam.getMeterIotCode())
				.eq(tenantCustomerMeterChangeLogQueryParam.getMeterIotCodeNew() != null, TenantCustomerMeterChangeLog::getMeterIotCodeNew, tenantCustomerMeterChangeLogQueryParam.getMeterIotCodeNew())
				.eq(tenantCustomerMeterChangeLogQueryParam.getMeterPeoples() != null, TenantCustomerMeterChangeLog::getMeterPeoples, tenantCustomerMeterChangeLogQueryParam.getMeterPeoples())
				.eq(tenantCustomerMeterChangeLogQueryParam.getMeterPeoplesNew() != null, TenantCustomerMeterChangeLog::getMeterPeoplesNew, tenantCustomerMeterChangeLogQueryParam.getMeterPeoplesNew())
				;

		IPage<TenantCustomerMeterChangeLog> tenantCustomerMeterChangeLogPage = tenantCustomerMeterChangeLogService.page(pageTenantCustomerMeterChangeLog, queryWrapperTenantCustomerMeterChangeLog);

		Page<TenantCustomerMeterChangeLogVo> tenantCustomerMeterChangeLogVoPage = new Page<TenantCustomerMeterChangeLogVo>(page, rows);
		tenantCustomerMeterChangeLogVoPage.setCurrent(tenantCustomerMeterChangeLogPage.getCurrent());
		tenantCustomerMeterChangeLogVoPage.setPages(tenantCustomerMeterChangeLogPage.getPages());
		tenantCustomerMeterChangeLogVoPage.setSize(tenantCustomerMeterChangeLogPage.getSize());
		tenantCustomerMeterChangeLogVoPage.setTotal(tenantCustomerMeterChangeLogPage.getTotal());
		tenantCustomerMeterChangeLogVoPage.setRecords(tenantCustomerMeterChangeLogPage.getRecords().stream()//
				.map(e -> entity2vo(e))//
				.collect(Collectors.toList()));

		return tenantCustomerMeterChangeLogVoPage;
	}

	@ApiOperation(value = "新增信息变更")
	@RequestMapping(value = "/tenant-customer-meter-change-logs", method = RequestMethod.POST)
	public TenantCustomerMeterChangeLogVo save(@RequestBody TenantCustomerMeterChangeLog tenantCustomerMeterChangeLog) {
		if (tenantCustomerMeterChangeLog.getId() == null || tenantCustomerMeterChangeLog.getId().trim().length() <= 0) {
			tenantCustomerMeterChangeLog.setId(idService.selectId());
		}
		boolean success = tenantCustomerMeterChangeLogService.save(tenantCustomerMeterChangeLog);
		if (success) {
			TenantCustomerMeterChangeLog tenantCustomerMeterChangeLogDatabase = tenantCustomerMeterChangeLogService.getById(tenantCustomerMeterChangeLog.getId());
			return entity2vo(tenantCustomerMeterChangeLogDatabase);
		}
		log.info("save TenantCustomerMeterChangeLog fail，{}", ToStringBuilder.reflectionToString(tenantCustomerMeterChangeLog, ToStringStyle.JSON_STYLE));
		return null;
	}

	@ApiOperation(value = "更新信息变更全部信息")
	@RequestMapping(value = "/tenant-customer-meter-change-logs/{id}", method = RequestMethod.PUT)
	public TenantCustomerMeterChangeLogVo updateById(@PathVariable("id") String id, @RequestBody TenantCustomerMeterChangeLog tenantCustomerMeterChangeLog) {
		tenantCustomerMeterChangeLog.setId(id);
		boolean success = tenantCustomerMeterChangeLogService.updateById(tenantCustomerMeterChangeLog);
		if (success) {
			TenantCustomerMeterChangeLog tenantCustomerMeterChangeLogDatabase = tenantCustomerMeterChangeLogService.getById(id);
			return entity2vo(tenantCustomerMeterChangeLogDatabase);
		}
		log.info("update TenantCustomerMeterChangeLog fail，{}", ToStringBuilder.reflectionToString(tenantCustomerMeterChangeLog, ToStringStyle.JSON_STYLE));
		return null;
	}

	@ApiOperation(value = "根据参数更新信息变更信息")
	@RequestMapping(value = "/tenant-customer-meter-change-logs/{id}", method = RequestMethod.PATCH)
	public TenantCustomerMeterChangeLogVo updatePatchById(@PathVariable("id") String id, @RequestBody TenantCustomerMeterChangeLog tenantCustomerMeterChangeLog) {
        TenantCustomerMeterChangeLog tenantCustomerMeterChangeLogWhere = TenantCustomerMeterChangeLog.builder()//
				.id(id)//
				.build();
		UpdateWrapper<TenantCustomerMeterChangeLog> updateWrapperTenantCustomerMeterChangeLog = new UpdateWrapper<TenantCustomerMeterChangeLog>();
		updateWrapperTenantCustomerMeterChangeLog.setEntity(tenantCustomerMeterChangeLogWhere);
		updateWrapperTenantCustomerMeterChangeLog.lambda()//
				//.eq(TenantCustomerMeterChangeLog::getId, id)
				// .set(tenantCustomerMeterChangeLog.getId() != null, TenantCustomerMeterChangeLog::getId, tenantCustomerMeterChangeLog.getId())
				.set(tenantCustomerMeterChangeLog.getTenantId() != null, TenantCustomerMeterChangeLog::getTenantId, tenantCustomerMeterChangeLog.getTenantId())
				.set(tenantCustomerMeterChangeLog.getCustomerId() != null, TenantCustomerMeterChangeLog::getCustomerId, tenantCustomerMeterChangeLog.getCustomerId())
				.set(tenantCustomerMeterChangeLog.getCsutomerIdNew() != null, TenantCustomerMeterChangeLog::getCsutomerIdNew, tenantCustomerMeterChangeLog.getCsutomerIdNew())
				.set(tenantCustomerMeterChangeLog.getCustomerName() != null, TenantCustomerMeterChangeLog::getCustomerName, tenantCustomerMeterChangeLog.getCustomerName())
				.set(tenantCustomerMeterChangeLog.getCustomerNameNew() != null, TenantCustomerMeterChangeLog::getCustomerNameNew, tenantCustomerMeterChangeLog.getCustomerNameNew())
				.set(tenantCustomerMeterChangeLog.getCustomerAddress() != null, TenantCustomerMeterChangeLog::getCustomerAddress, tenantCustomerMeterChangeLog.getCustomerAddress())
				.set(tenantCustomerMeterChangeLog.getCustomerAddressNew() != null, TenantCustomerMeterChangeLog::getCustomerAddressNew, tenantCustomerMeterChangeLog.getCustomerAddressNew())
				.set(tenantCustomerMeterChangeLog.getCustomerTypeId() != null, TenantCustomerMeterChangeLog::getCustomerTypeId, tenantCustomerMeterChangeLog.getCustomerTypeId())
				.set(tenantCustomerMeterChangeLog.getCustomerTypeIdNew() != null, TenantCustomerMeterChangeLog::getCustomerTypeIdNew, tenantCustomerMeterChangeLog.getCustomerTypeIdNew())
				.set(tenantCustomerMeterChangeLog.getCustomerStatus() != null, TenantCustomerMeterChangeLog::getCustomerStatus, tenantCustomerMeterChangeLog.getCustomerStatus())
				.set(tenantCustomerMeterChangeLog.getCustomerStatusNew() != null, TenantCustomerMeterChangeLog::getCustomerStatusNew, tenantCustomerMeterChangeLog.getCustomerStatusNew())
				.set(tenantCustomerMeterChangeLog.getCustomerPaymentMethod() != null, TenantCustomerMeterChangeLog::getCustomerPaymentMethod, tenantCustomerMeterChangeLog.getCustomerPaymentMethod())
				.set(tenantCustomerMeterChangeLog.getCustomerPaymentMethodNew() != null, TenantCustomerMeterChangeLog::getCustomerPaymentMethodNew, tenantCustomerMeterChangeLog.getCustomerPaymentMethodNew())
				.set(tenantCustomerMeterChangeLog.getInvoiceType() != null, TenantCustomerMeterChangeLog::getInvoiceType, tenantCustomerMeterChangeLog.getInvoiceType())
				.set(tenantCustomerMeterChangeLog.getInvoiceTypeNew() != null, TenantCustomerMeterChangeLog::getInvoiceTypeNew, tenantCustomerMeterChangeLog.getInvoiceTypeNew())
				.set(tenantCustomerMeterChangeLog.getInvoiceName() != null, TenantCustomerMeterChangeLog::getInvoiceName, tenantCustomerMeterChangeLog.getInvoiceName())
				.set(tenantCustomerMeterChangeLog.getInvoiceNameNew() != null, TenantCustomerMeterChangeLog::getInvoiceNameNew, tenantCustomerMeterChangeLog.getInvoiceNameNew())
				.set(tenantCustomerMeterChangeLog.getInvoiceTaxNo() != null, TenantCustomerMeterChangeLog::getInvoiceTaxNo, tenantCustomerMeterChangeLog.getInvoiceTaxNo())
				.set(tenantCustomerMeterChangeLog.getInvoiceTaxNoNew() != null, TenantCustomerMeterChangeLog::getInvoiceTaxNoNew, tenantCustomerMeterChangeLog.getInvoiceTaxNoNew())
				.set(tenantCustomerMeterChangeLog.getInvoiceAddress() != null, TenantCustomerMeterChangeLog::getInvoiceAddress, tenantCustomerMeterChangeLog.getInvoiceAddress())
				.set(tenantCustomerMeterChangeLog.getInvoiceAddressNew() != null, TenantCustomerMeterChangeLog::getInvoiceAddressNew, tenantCustomerMeterChangeLog.getInvoiceAddressNew())
				.set(tenantCustomerMeterChangeLog.getInvoiceTel() != null, TenantCustomerMeterChangeLog::getInvoiceTel, tenantCustomerMeterChangeLog.getInvoiceTel())
				.set(tenantCustomerMeterChangeLog.getInvoiceTelNew() != null, TenantCustomerMeterChangeLog::getInvoiceTelNew, tenantCustomerMeterChangeLog.getInvoiceTelNew())
				.set(tenantCustomerMeterChangeLog.getInvoiceBankCode() != null, TenantCustomerMeterChangeLog::getInvoiceBankCode, tenantCustomerMeterChangeLog.getInvoiceBankCode())
				.set(tenantCustomerMeterChangeLog.getInvoiceBankCodeNew() != null, TenantCustomerMeterChangeLog::getInvoiceBankCodeNew, tenantCustomerMeterChangeLog.getInvoiceBankCodeNew())
				.set(tenantCustomerMeterChangeLog.getInvoiceBankName() != null, TenantCustomerMeterChangeLog::getInvoiceBankName, tenantCustomerMeterChangeLog.getInvoiceBankName())
				.set(tenantCustomerMeterChangeLog.getInvoiceBankNameNew() != null, TenantCustomerMeterChangeLog::getInvoiceBankNameNew, tenantCustomerMeterChangeLog.getInvoiceBankNameNew())
				.set(tenantCustomerMeterChangeLog.getInvoiceBankAccountNo() != null, TenantCustomerMeterChangeLog::getInvoiceBankAccountNo, tenantCustomerMeterChangeLog.getInvoiceBankAccountNo())
				.set(tenantCustomerMeterChangeLog.getInvoiceBankAccountNoNew() != null, TenantCustomerMeterChangeLog::getInvoiceBankAccountNoNew, tenantCustomerMeterChangeLog.getInvoiceBankAccountNoNew())
				.set(tenantCustomerMeterChangeLog.getMeterId() != null, TenantCustomerMeterChangeLog::getMeterId, tenantCustomerMeterChangeLog.getMeterId())
				.set(tenantCustomerMeterChangeLog.getPriceTypeId() != null, TenantCustomerMeterChangeLog::getPriceTypeId, tenantCustomerMeterChangeLog.getPriceTypeId())
				.set(tenantCustomerMeterChangeLog.getPriceTypeIdNew() != null, TenantCustomerMeterChangeLog::getPriceTypeIdNew, tenantCustomerMeterChangeLog.getPriceTypeIdNew())
				.set(tenantCustomerMeterChangeLog.getMeterLastSettlePointer() != null, TenantCustomerMeterChangeLog::getMeterLastSettlePointer, tenantCustomerMeterChangeLog.getMeterLastSettlePointer())
				.set(tenantCustomerMeterChangeLog.getMeterLastSettlePointerNew() != null, TenantCustomerMeterChangeLog::getMeterLastSettlePointerNew, tenantCustomerMeterChangeLog.getMeterLastSettlePointerNew())
				.set(tenantCustomerMeterChangeLog.getManufactorId() != null, TenantCustomerMeterChangeLog::getManufactorId, tenantCustomerMeterChangeLog.getManufactorId())
				.set(tenantCustomerMeterChangeLog.getManufactorIdNew() != null, TenantCustomerMeterChangeLog::getManufactorIdNew, tenantCustomerMeterChangeLog.getManufactorIdNew())
				.set(tenantCustomerMeterChangeLog.getMeterType() != null, TenantCustomerMeterChangeLog::getMeterType, tenantCustomerMeterChangeLog.getMeterType())
				.set(tenantCustomerMeterChangeLog.getMeterTypeNew() != null, TenantCustomerMeterChangeLog::getMeterTypeNew, tenantCustomerMeterChangeLog.getMeterTypeNew())
				.set(tenantCustomerMeterChangeLog.getCaliberId() != null, TenantCustomerMeterChangeLog::getCaliberId, tenantCustomerMeterChangeLog.getCaliberId())
				.set(tenantCustomerMeterChangeLog.getCaliberIdNew() != null, TenantCustomerMeterChangeLog::getCaliberIdNew, tenantCustomerMeterChangeLog.getCaliberIdNew())
				.set(tenantCustomerMeterChangeLog.getMeterMachineCode() != null, TenantCustomerMeterChangeLog::getMeterMachineCode, tenantCustomerMeterChangeLog.getMeterMachineCode())
				.set(tenantCustomerMeterChangeLog.getMeterMachineCodeNew() != null, TenantCustomerMeterChangeLog::getMeterMachineCodeNew, tenantCustomerMeterChangeLog.getMeterMachineCodeNew())
				.set(tenantCustomerMeterChangeLog.getMeterIotCode() != null, TenantCustomerMeterChangeLog::getMeterIotCode, tenantCustomerMeterChangeLog.getMeterIotCode())
				.set(tenantCustomerMeterChangeLog.getMeterIotCodeNew() != null, TenantCustomerMeterChangeLog::getMeterIotCodeNew, tenantCustomerMeterChangeLog.getMeterIotCodeNew())
				.set(tenantCustomerMeterChangeLog.getMeterPeoples() != null, TenantCustomerMeterChangeLog::getMeterPeoples, tenantCustomerMeterChangeLog.getMeterPeoples())
				.set(tenantCustomerMeterChangeLog.getMeterPeoplesNew() != null, TenantCustomerMeterChangeLog::getMeterPeoplesNew, tenantCustomerMeterChangeLog.getMeterPeoplesNew())
				;

		boolean success = tenantCustomerMeterChangeLogService.update(updateWrapperTenantCustomerMeterChangeLog);
		if (success) {
			TenantCustomerMeterChangeLog tenantCustomerMeterChangeLogDatabase = tenantCustomerMeterChangeLogService.getById(id);
			return entity2vo(tenantCustomerMeterChangeLogDatabase);
		}
		log.info("partial update TenantCustomerMeterChangeLog fail，{}",
				ToStringBuilder.reflectionToString(tenantCustomerMeterChangeLog, ToStringStyle.JSON_STYLE));
		return null;
	}

	@ApiOperation(value = "根据ID删除信息变更")
	@RequestMapping(value = "/tenant-customer-meter-change-logs/{id}", method = RequestMethod.DELETE)
	public CommonResult<Object> removeById(@PathVariable("id") String id) {
		boolean success = tenantCustomerMeterChangeLogService.removeById(id);
		return success ? CommonResult.success(success) : CommonResult.failed();
	}

	private TenantCustomerMeterChangeLogVo entity2vo(TenantCustomerMeterChangeLog tenantCustomerMeterChangeLog) {
		if (tenantCustomerMeterChangeLog == null) {
			return null;
		}

		String jsonString = JSON.toJSONString(tenantCustomerMeterChangeLog);
		TenantCustomerMeterChangeLogVo tenantCustomerMeterChangeLogVo = JSON.parseObject(jsonString, TenantCustomerMeterChangeLogVo.class);
		if (StringUtils.isEmpty(tenantCustomerMeterChangeLogVo.getTenantName())) {
			TenantInfo tenantInfo = tenantInfoService.getById(tenantCustomerMeterChangeLog.getTenantId());
			if (tenantInfo != null) {
				tenantCustomerMeterChangeLogVo.setTenantName(tenantInfo.getTenantName());
			}
		}
		return tenantCustomerMeterChangeLogVo;
	}

}
