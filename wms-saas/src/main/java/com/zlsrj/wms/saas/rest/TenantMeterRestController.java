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
import com.zlsrj.wms.api.dto.TenantMeterQueryParam;
import com.zlsrj.wms.api.entity.TenantInfo;
import com.zlsrj.wms.api.entity.TenantMeter;
import com.zlsrj.wms.api.vo.TenantMeterVo;
import com.zlsrj.wms.common.api.CommonResult;
import com.zlsrj.wms.saas.service.IIdService;
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
	@Autowired
	private IIdService idService;

	@ApiOperation(value = "根据ID查询水表信息")
	@RequestMapping(value = "/tenant-meters/{id}", method = RequestMethod.GET)
	public TenantMeterVo getById(@PathVariable("id") Long id) {
		TenantMeter tenantMeter = tenantMeterService.getById(id);

		return entity2vo(tenantMeter);
	}

	@ApiOperation(value = "根据参数查询水表信息列表")
	@RequestMapping(value = "/tenant-meters", method = RequestMethod.GET)
	public Page<TenantMeterVo> page(@RequestBody TenantMeterQueryParam tenantMeterQueryParam,
			@RequestParam(value = "page", defaultValue = "1") int page, //
			@RequestParam(value = "rows", defaultValue = "10") int rows, //
			@RequestParam(value = "sort") String sort, // 排序列字段名
			@RequestParam(value = "order") String order // 可以是 'asc' 或者 'desc'，默认值是 'asc'
	) {
		IPage<TenantMeter> pageTenantMeter = new Page<TenantMeter>(page, rows);
		QueryWrapper<TenantMeter> queryWrapperTenantMeter = new QueryWrapper<TenantMeter>();
		queryWrapperTenantMeter.orderBy(StringUtils.isNotEmpty(sort), "asc".equals(order), sort);
		queryWrapperTenantMeter.lambda()
				.eq(tenantMeterQueryParam.getId() != null, TenantMeter::getId, tenantMeterQueryParam.getId())
				.eq(tenantMeterQueryParam.getTenantId() != null, TenantMeter::getTenantId, tenantMeterQueryParam.getTenantId())
				.eq(tenantMeterQueryParam.getCustomerId() != null, TenantMeter::getCustomerId, tenantMeterQueryParam.getCustomerId())
				.eq(tenantMeterQueryParam.getMeterBookletId() != null, TenantMeter::getMeterBookletId, tenantMeterQueryParam.getMeterBookletId())
				.eq(tenantMeterQueryParam.getMeterParentId() != null, TenantMeter::getMeterParentId, tenantMeterQueryParam.getMeterParentId())
				.eq(tenantMeterQueryParam.getMeterCode() != null, TenantMeter::getMeterCode, tenantMeterQueryParam.getMeterCode())
				.eq(tenantMeterQueryParam.getMeterAddress() != null, TenantMeter::getMeterAddress, tenantMeterQueryParam.getMeterAddress())
				.eq(tenantMeterQueryParam.getMeterPeoples() != null, TenantMeter::getMeterPeoples, tenantMeterQueryParam.getMeterPeoples())
				.eq(tenantMeterQueryParam.getMeterMachineCode() != null, TenantMeter::getMeterMachineCode, tenantMeterQueryParam.getMeterMachineCode())
				.eq(tenantMeterQueryParam.getMeterUseType() != null, TenantMeter::getMeterUseType, tenantMeterQueryParam.getMeterUseType())
				.eq(tenantMeterQueryParam.getMeterManufactorId() != null, TenantMeter::getMeterManufactorId, tenantMeterQueryParam.getMeterManufactorId())
				.eq(tenantMeterQueryParam.getMeterType() != null, TenantMeter::getMeterType, tenantMeterQueryParam.getMeterType())
				.eq(tenantMeterQueryParam.getCaliberId() != null, TenantMeter::getCaliberId, tenantMeterQueryParam.getCaliberId())
				.eq(tenantMeterQueryParam.getMeterWaterTypeId() != null, TenantMeter::getMeterWaterTypeId, tenantMeterQueryParam.getMeterWaterTypeId())
				.eq(tenantMeterQueryParam.getPriceTypeId() != null, TenantMeter::getPriceTypeId, tenantMeterQueryParam.getPriceTypeId())
				.eq(tenantMeterQueryParam.getMeterIotCode() != null, TenantMeter::getMeterIotCode, tenantMeterQueryParam.getMeterIotCode())
				.eq(tenantMeterQueryParam.getMeterInstallDate() != null, TenantMeter::getMeterInstallDate, tenantMeterQueryParam.getMeterInstallDate())
				.ge(tenantMeterQueryParam.getMeterInstallDateStart() != null, TenantMeter::getMeterInstallDate,tenantMeterQueryParam.getMeterInstallDateStart() == null ? null: DateUtil.beginOfDay(tenantMeterQueryParam.getMeterInstallDateStart()))
				.le(tenantMeterQueryParam.getMeterInstallDateEnd() != null, TenantMeter::getMeterInstallDate,tenantMeterQueryParam.getMeterInstallDateEnd() == null ? null: DateUtil.endOfDay(tenantMeterQueryParam.getMeterInstallDateEnd()))
				.eq(tenantMeterQueryParam.getMeterRegisterTime() != null, TenantMeter::getMeterRegisterTime, tenantMeterQueryParam.getMeterRegisterTime())
				.ge(tenantMeterQueryParam.getMeterRegisterTimeStart() != null, TenantMeter::getMeterRegisterTime,tenantMeterQueryParam.getMeterRegisterTimeStart() == null ? null: DateUtil.beginOfDay(tenantMeterQueryParam.getMeterRegisterTimeStart()))
				.le(tenantMeterQueryParam.getMeterRegisterTimeEnd() != null, TenantMeter::getMeterRegisterTime,tenantMeterQueryParam.getMeterRegisterTimeEnd() == null ? null: DateUtil.endOfDay(tenantMeterQueryParam.getMeterRegisterTimeEnd()))
				.eq(tenantMeterQueryParam.getMeterStatus() != null, TenantMeter::getMeterStatus, tenantMeterQueryParam.getMeterStatus())
				.eq(tenantMeterQueryParam.getMeterYearTotalWaters() != null, TenantMeter::getMeterYearTotalWaters, tenantMeterQueryParam.getMeterYearTotalWaters())
				.eq(tenantMeterQueryParam.getMeterLastSettleTime() != null, TenantMeter::getMeterLastSettleTime, tenantMeterQueryParam.getMeterLastSettleTime())
				.ge(tenantMeterQueryParam.getMeterLastSettleTimeStart() != null, TenantMeter::getMeterLastSettleTime,tenantMeterQueryParam.getMeterLastSettleTimeStart() == null ? null: DateUtil.beginOfDay(tenantMeterQueryParam.getMeterLastSettleTimeStart()))
				.le(tenantMeterQueryParam.getMeterLastSettleTimeEnd() != null, TenantMeter::getMeterLastSettleTime,tenantMeterQueryParam.getMeterLastSettleTimeEnd() == null ? null: DateUtil.endOfDay(tenantMeterQueryParam.getMeterLastSettleTimeEnd()))
				.eq(tenantMeterQueryParam.getMeterLastSettlePointer() != null, TenantMeter::getMeterLastSettlePointer, tenantMeterQueryParam.getMeterLastSettlePointer())
				.eq(tenantMeterQueryParam.getMeterArrearsMoney() != null, TenantMeter::getMeterArrearsMoney, tenantMeterQueryParam.getMeterArrearsMoney())
				.eq(tenantMeterQueryParam.getParentId()!=null,TenantMeter::getMeterParentId, tenantMeterQueryParam.getParentId())
				.isNull(tenantMeterQueryParam.getParentId()==null, TenantMeter::getMeterParentId)
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

	@ApiOperation(value = "新增水表信息")
	@RequestMapping(value = "/tenant-meters", method = RequestMethod.POST)
	public TenantMeterVo save(@RequestBody TenantMeter tenantMeter) {
		if (tenantMeter.getId() == null || tenantMeter.getId().compareTo(0L) <= 0) {
			tenantMeter.setId(idService.selectId());
		}
		boolean success = tenantMeterService.save(tenantMeter);
		if (success) {
			TenantMeter tenantMeterDatabase = tenantMeterService.getById(tenantMeter.getId());
			return entity2vo(tenantMeterDatabase);
		}
		log.info("save TenantMeter fail，{}", ToStringBuilder.reflectionToString(tenantMeter, ToStringStyle.JSON_STYLE));
		return null;
	}

	@ApiOperation(value = "更新水表信息全部信息")
	@RequestMapping(value = "/tenant-meters/{id}", method = RequestMethod.PUT)
	public TenantMeterVo updateById(@PathVariable("id") Long id, @RequestBody TenantMeter tenantMeter) {
		tenantMeter.setId(id);
		boolean success = tenantMeterService.updateById(tenantMeter);
		if (success) {
			TenantMeter tenantMeterDatabase = tenantMeterService.getById(id);
			return entity2vo(tenantMeterDatabase);
		}
		log.info("update TenantMeter fail，{}", ToStringBuilder.reflectionToString(tenantMeter, ToStringStyle.JSON_STYLE));
		return null;
	}

	@ApiOperation(value = "根据参数更新水表信息信息")
	@RequestMapping(value = "/tenant-meters/{id}", method = RequestMethod.PATCH)
	public TenantMeterVo updatePatchById(@PathVariable("id") Long id, @RequestBody TenantMeter tenantMeter) {
        TenantMeter tenantMeterWhere = TenantMeter.builder()//
				.id(id)//
				.build();
		UpdateWrapper<TenantMeter> updateWrapperTenantMeter = new UpdateWrapper<TenantMeter>();
		updateWrapperTenantMeter.setEntity(tenantMeterWhere);
		updateWrapperTenantMeter.lambda()//
				//.eq(TenantMeter::getId, id)
				// .set(tenantMeter.getId() != null, TenantMeter::getId, tenantMeter.getId())
				.set(tenantMeter.getTenantId() != null, TenantMeter::getTenantId, tenantMeter.getTenantId())
				.set(tenantMeter.getCustomerId() != null, TenantMeter::getCustomerId, tenantMeter.getCustomerId())
				.set(tenantMeter.getMeterBookletId() != null, TenantMeter::getMeterBookletId, tenantMeter.getMeterBookletId())
				.set(tenantMeter.getMeterParentId() != null, TenantMeter::getMeterParentId, tenantMeter.getMeterParentId())
				.set(tenantMeter.getMeterCode() != null, TenantMeter::getMeterCode, tenantMeter.getMeterCode())
				.set(tenantMeter.getMeterAddress() != null, TenantMeter::getMeterAddress, tenantMeter.getMeterAddress())
				.set(tenantMeter.getMeterPeoples() != null, TenantMeter::getMeterPeoples, tenantMeter.getMeterPeoples())
				.set(tenantMeter.getMeterMachineCode() != null, TenantMeter::getMeterMachineCode, tenantMeter.getMeterMachineCode())
				.set(tenantMeter.getMeterUseType() != null, TenantMeter::getMeterUseType, tenantMeter.getMeterUseType())
				.set(tenantMeter.getMeterManufactorId() != null, TenantMeter::getMeterManufactorId, tenantMeter.getMeterManufactorId())
				.set(tenantMeter.getMeterType() != null, TenantMeter::getMeterType, tenantMeter.getMeterType())
				.set(tenantMeter.getCaliberId() != null, TenantMeter::getCaliberId, tenantMeter.getCaliberId())
				.set(tenantMeter.getMeterWaterTypeId() != null, TenantMeter::getMeterWaterTypeId, tenantMeter.getMeterWaterTypeId())
				.set(tenantMeter.getPriceTypeId() != null, TenantMeter::getPriceTypeId, tenantMeter.getPriceTypeId())
				.set(tenantMeter.getMeterIotCode() != null, TenantMeter::getMeterIotCode, tenantMeter.getMeterIotCode())
				.set(tenantMeter.getMeterInstallDate() != null, TenantMeter::getMeterInstallDate, tenantMeter.getMeterInstallDate())
				.set(tenantMeter.getMeterRegisterTime() != null, TenantMeter::getMeterRegisterTime, tenantMeter.getMeterRegisterTime())
				.set(tenantMeter.getMeterStatus() != null, TenantMeter::getMeterStatus, tenantMeter.getMeterStatus())
				.set(tenantMeter.getMeterYearTotalWaters() != null, TenantMeter::getMeterYearTotalWaters, tenantMeter.getMeterYearTotalWaters())
				.set(tenantMeter.getMeterLastSettleTime() != null, TenantMeter::getMeterLastSettleTime, tenantMeter.getMeterLastSettleTime())
				.set(tenantMeter.getMeterLastSettlePointer() != null, TenantMeter::getMeterLastSettlePointer, tenantMeter.getMeterLastSettlePointer())
				.set(tenantMeter.getMeterArrearsMoney() != null, TenantMeter::getMeterArrearsMoney, tenantMeter.getMeterArrearsMoney())
				;

		boolean success = tenantMeterService.update(updateWrapperTenantMeter);
		if (success) {
			TenantMeter tenantMeterDatabase = tenantMeterService.getById(id);
			return entity2vo(tenantMeterDatabase);
		}
		log.info("partial update TenantMeter fail，{}",
				ToStringBuilder.reflectionToString(tenantMeter, ToStringStyle.JSON_STYLE));
		return null;
	}

	@ApiOperation(value = "根据ID删除水表信息")
	@RequestMapping(value = "/tenant-meters/{id}", method = RequestMethod.DELETE)
	public CommonResult<Object> removeById(@PathVariable("id") Long id) {
		boolean success = tenantMeterService.removeById(id);
		return success ? CommonResult.success(success) : CommonResult.failed();
	}

	private TenantMeterVo entity2vo(TenantMeter tenantMeter) {
		if (tenantMeter == null) {
			return null;
		}

		String jsonString = JSON.toJSONString(tenantMeter);
		TenantMeterVo tenantMeterVo = JSON.parseObject(jsonString, TenantMeterVo.class);
		if (StringUtils.isEmpty(tenantMeterVo.getTenantName())) {
			TenantInfo tenantInfo = tenantInfoService.getById(tenantMeter.getTenantId());
			if (tenantInfo != null) {
				tenantMeterVo.setTenantName(tenantInfo.getTenantName());
			}
		}
		return tenantMeterVo;
	}

}
