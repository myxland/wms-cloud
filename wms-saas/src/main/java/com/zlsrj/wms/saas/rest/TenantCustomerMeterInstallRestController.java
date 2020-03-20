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
import com.zlsrj.wms.api.dto.TenantCustomerMeterInstallQueryParam;
import com.zlsrj.wms.api.entity.TenantInfo;
import com.zlsrj.wms.api.entity.TenantCustomerMeterInstall;
import com.zlsrj.wms.api.vo.TenantCustomerMeterInstallVo;
import com.zlsrj.wms.common.api.CommonResult;
import com.zlsrj.wms.saas.service.IIdService;
import com.zlsrj.wms.saas.service.ITenantInfoService;
import com.zlsrj.wms.saas.service.ITenantCustomerMeterInstallService;

import cn.hutool.core.date.DateUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

@Api(value = "用户水表立户", tags = { "用户水表立户操作接口" })
@RestController
@Slf4j
public class TenantCustomerMeterInstallRestController {

	@Autowired
	private ITenantCustomerMeterInstallService tenantCustomerMeterInstallService;
	@Autowired
	private ITenantInfoService tenantInfoService;
	@Autowired
	private IIdService idService;

	@ApiOperation(value = "根据ID查询用户水表立户")
	@RequestMapping(value = "/tenant-customer-meter-installs/{id}", method = RequestMethod.GET)
	public TenantCustomerMeterInstallVo getById(@PathVariable("id") String id) {
		TenantCustomerMeterInstall tenantCustomerMeterInstall = tenantCustomerMeterInstallService.getById(id);

		return entity2vo(tenantCustomerMeterInstall);
	}

	@ApiOperation(value = "根据参数查询用户水表立户列表")
	@RequestMapping(value = "/tenant-customer-meter-installs", method = RequestMethod.GET)
	public Page<TenantCustomerMeterInstallVo> page(@RequestBody TenantCustomerMeterInstallQueryParam tenantCustomerMeterInstallQueryParam,
			@RequestParam(value = "page", defaultValue = "1") int page, //
			@RequestParam(value = "rows", defaultValue = "10") int rows, //
			@RequestParam(value = "sort") String sort, // 排序列字段名
			@RequestParam(value = "order") String order // 可以是 'asc' 或者 'desc'，默认值是 'asc'
	) {
		IPage<TenantCustomerMeterInstall> pageTenantCustomerMeterInstall = new Page<TenantCustomerMeterInstall>(page, rows);
		QueryWrapper<TenantCustomerMeterInstall> queryWrapperTenantCustomerMeterInstall = new QueryWrapper<TenantCustomerMeterInstall>();
		queryWrapperTenantCustomerMeterInstall.orderBy(StringUtils.isNotEmpty(sort), "asc".equals(order), sort);
		queryWrapperTenantCustomerMeterInstall.lambda()
				.eq(tenantCustomerMeterInstallQueryParam.getId() != null, TenantCustomerMeterInstall::getId, tenantCustomerMeterInstallQueryParam.getId())
				.eq(tenantCustomerMeterInstallQueryParam.getTenantId() != null, TenantCustomerMeterInstall::getTenantId, tenantCustomerMeterInstallQueryParam.getTenantId())
				.eq(tenantCustomerMeterInstallQueryParam.getMeterId() != null, TenantCustomerMeterInstall::getMeterId, tenantCustomerMeterInstallQueryParam.getMeterId())
				.eq(tenantCustomerMeterInstallQueryParam.getMeterCode() != null, TenantCustomerMeterInstall::getMeterCode, tenantCustomerMeterInstallQueryParam.getMeterCode())
				.eq(tenantCustomerMeterInstallQueryParam.getCustName() != null, TenantCustomerMeterInstall::getCustName, tenantCustomerMeterInstallQueryParam.getCustName())
				.eq(tenantCustomerMeterInstallQueryParam.getMeterAddress() != null, TenantCustomerMeterInstall::getMeterAddress, tenantCustomerMeterInstallQueryParam.getMeterAddress())
				.eq(tenantCustomerMeterInstallQueryParam.getMeterMachineCode() != null, TenantCustomerMeterInstall::getMeterMachineCode, tenantCustomerMeterInstallQueryParam.getMeterMachineCode())
				.eq(tenantCustomerMeterInstallQueryParam.getManufactorId() != null, TenantCustomerMeterInstall::getManufactorId, tenantCustomerMeterInstallQueryParam.getManufactorId())
				.eq(tenantCustomerMeterInstallQueryParam.getMeterType() != null, TenantCustomerMeterInstall::getMeterType, tenantCustomerMeterInstallQueryParam.getMeterType())
				.eq(tenantCustomerMeterInstallQueryParam.getCaliberId() != null, TenantCustomerMeterInstall::getCaliberId, tenantCustomerMeterInstallQueryParam.getCaliberId())
				.eq(tenantCustomerMeterInstallQueryParam.getWaterTypeId() != null, TenantCustomerMeterInstall::getWaterTypeId, tenantCustomerMeterInstallQueryParam.getWaterTypeId())
				.eq(tenantCustomerMeterInstallQueryParam.getPriceTypeId() != null, TenantCustomerMeterInstall::getPriceTypeId, tenantCustomerMeterInstallQueryParam.getPriceTypeId())
				.eq(tenantCustomerMeterInstallQueryParam.getMeterIotCode() != null, TenantCustomerMeterInstall::getMeterIotCode, tenantCustomerMeterInstallQueryParam.getMeterIotCode())
				.eq(tenantCustomerMeterInstallQueryParam.getMeterInstallDate() != null, TenantCustomerMeterInstall::getMeterInstallDate, tenantCustomerMeterInstallQueryParam.getMeterInstallDate())
				.ge(tenantCustomerMeterInstallQueryParam.getMeterInstallDateStart() != null, TenantCustomerMeterInstall::getMeterInstallDate,tenantCustomerMeterInstallQueryParam.getMeterInstallDateStart() == null ? null: DateUtil.beginOfDay(tenantCustomerMeterInstallQueryParam.getMeterInstallDateStart()))
				.le(tenantCustomerMeterInstallQueryParam.getMeterInstallDateEnd() != null, TenantCustomerMeterInstall::getMeterInstallDate,tenantCustomerMeterInstallQueryParam.getMeterInstallDateEnd() == null ? null: DateUtil.endOfDay(tenantCustomerMeterInstallQueryParam.getMeterInstallDateEnd()))
				.eq(tenantCustomerMeterInstallQueryParam.getMeterLastSettleTime() != null, TenantCustomerMeterInstall::getMeterLastSettleTime, tenantCustomerMeterInstallQueryParam.getMeterLastSettleTime())
				.ge(tenantCustomerMeterInstallQueryParam.getMeterLastSettleTimeStart() != null, TenantCustomerMeterInstall::getMeterLastSettleTime,tenantCustomerMeterInstallQueryParam.getMeterLastSettleTimeStart() == null ? null: DateUtil.beginOfDay(tenantCustomerMeterInstallQueryParam.getMeterLastSettleTimeStart()))
				.le(tenantCustomerMeterInstallQueryParam.getMeterLastSettleTimeEnd() != null, TenantCustomerMeterInstall::getMeterLastSettleTime,tenantCustomerMeterInstallQueryParam.getMeterLastSettleTimeEnd() == null ? null: DateUtil.endOfDay(tenantCustomerMeterInstallQueryParam.getMeterLastSettleTimeEnd()))
				.eq(tenantCustomerMeterInstallQueryParam.getMeterLastSettlePointer() != null, TenantCustomerMeterInstall::getMeterLastSettlePointer, tenantCustomerMeterInstallQueryParam.getMeterLastSettlePointer())
				.eq(tenantCustomerMeterInstallQueryParam.getLinkmanMobile() != null, TenantCustomerMeterInstall::getLinkmanMobile, tenantCustomerMeterInstallQueryParam.getLinkmanMobile())
				.eq(tenantCustomerMeterInstallQueryParam.getCustmerIdNo() != null, TenantCustomerMeterInstall::getCustmerIdNo, tenantCustomerMeterInstallQueryParam.getCustmerIdNo())
				.eq(tenantCustomerMeterInstallQueryParam.getOldCode() != null, TenantCustomerMeterInstall::getOldCode, tenantCustomerMeterInstallQueryParam.getOldCode())
				.eq(tenantCustomerMeterInstallQueryParam.getInputType() != null, TenantCustomerMeterInstall::getInputType, tenantCustomerMeterInstallQueryParam.getInputType())
				.eq(tenantCustomerMeterInstallQueryParam.getInputTime() != null, TenantCustomerMeterInstall::getInputTime, tenantCustomerMeterInstallQueryParam.getInputTime())
				.ge(tenantCustomerMeterInstallQueryParam.getInputTimeStart() != null, TenantCustomerMeterInstall::getInputTime,tenantCustomerMeterInstallQueryParam.getInputTimeStart() == null ? null: DateUtil.beginOfDay(tenantCustomerMeterInstallQueryParam.getInputTimeStart()))
				.le(tenantCustomerMeterInstallQueryParam.getInputTimeEnd() != null, TenantCustomerMeterInstall::getInputTime,tenantCustomerMeterInstallQueryParam.getInputTimeEnd() == null ? null: DateUtil.endOfDay(tenantCustomerMeterInstallQueryParam.getInputTimeEnd()))
				.eq(tenantCustomerMeterInstallQueryParam.getCreateOn() != null, TenantCustomerMeterInstall::getCreateOn, tenantCustomerMeterInstallQueryParam.getCreateOn())
				;

		IPage<TenantCustomerMeterInstall> tenantCustomerMeterInstallPage = tenantCustomerMeterInstallService.page(pageTenantCustomerMeterInstall, queryWrapperTenantCustomerMeterInstall);

		Page<TenantCustomerMeterInstallVo> tenantCustomerMeterInstallVoPage = new Page<TenantCustomerMeterInstallVo>(page, rows);
		tenantCustomerMeterInstallVoPage.setCurrent(tenantCustomerMeterInstallPage.getCurrent());
		tenantCustomerMeterInstallVoPage.setPages(tenantCustomerMeterInstallPage.getPages());
		tenantCustomerMeterInstallVoPage.setSize(tenantCustomerMeterInstallPage.getSize());
		tenantCustomerMeterInstallVoPage.setTotal(tenantCustomerMeterInstallPage.getTotal());
		tenantCustomerMeterInstallVoPage.setRecords(tenantCustomerMeterInstallPage.getRecords().stream()//
				.map(e -> entity2vo(e))//
				.collect(Collectors.toList()));

		return tenantCustomerMeterInstallVoPage;
	}

	@ApiOperation(value = "新增用户水表立户")
	@RequestMapping(value = "/tenant-customer-meter-installs", method = RequestMethod.POST)
	public TenantCustomerMeterInstallVo save(@RequestBody TenantCustomerMeterInstall tenantCustomerMeterInstall) {
		if (tenantCustomerMeterInstall.getId() == null || tenantCustomerMeterInstall.getId().trim().length() <= 0) {
			tenantCustomerMeterInstall.setId(idService.selectId());
		}
		boolean success = tenantCustomerMeterInstallService.save(tenantCustomerMeterInstall);
		if (success) {
			TenantCustomerMeterInstall tenantCustomerMeterInstallDatabase = tenantCustomerMeterInstallService.getById(tenantCustomerMeterInstall.getId());
			return entity2vo(tenantCustomerMeterInstallDatabase);
		}
		log.info("save TenantCustomerMeterInstall fail，{}", ToStringBuilder.reflectionToString(tenantCustomerMeterInstall, ToStringStyle.JSON_STYLE));
		return null;
	}

	@ApiOperation(value = "更新用户水表立户全部信息")
	@RequestMapping(value = "/tenant-customer-meter-installs/{id}", method = RequestMethod.PUT)
	public TenantCustomerMeterInstallVo updateById(@PathVariable("id") String id, @RequestBody TenantCustomerMeterInstall tenantCustomerMeterInstall) {
		tenantCustomerMeterInstall.setId(id);
		boolean success = tenantCustomerMeterInstallService.updateById(tenantCustomerMeterInstall);
		if (success) {
			TenantCustomerMeterInstall tenantCustomerMeterInstallDatabase = tenantCustomerMeterInstallService.getById(id);
			return entity2vo(tenantCustomerMeterInstallDatabase);
		}
		log.info("update TenantCustomerMeterInstall fail，{}", ToStringBuilder.reflectionToString(tenantCustomerMeterInstall, ToStringStyle.JSON_STYLE));
		return null;
	}

	@ApiOperation(value = "根据参数更新用户水表立户信息")
	@RequestMapping(value = "/tenant-customer-meter-installs/{id}", method = RequestMethod.PATCH)
	public TenantCustomerMeterInstallVo updatePatchById(@PathVariable("id") String id, @RequestBody TenantCustomerMeterInstall tenantCustomerMeterInstall) {
        TenantCustomerMeterInstall tenantCustomerMeterInstallWhere = TenantCustomerMeterInstall.builder()//
				.id(id)//
				.build();
		UpdateWrapper<TenantCustomerMeterInstall> updateWrapperTenantCustomerMeterInstall = new UpdateWrapper<TenantCustomerMeterInstall>();
		updateWrapperTenantCustomerMeterInstall.setEntity(tenantCustomerMeterInstallWhere);
		updateWrapperTenantCustomerMeterInstall.lambda()//
				//.eq(TenantCustomerMeterInstall::getId, id)
				// .set(tenantCustomerMeterInstall.getId() != null, TenantCustomerMeterInstall::getId, tenantCustomerMeterInstall.getId())
				.set(tenantCustomerMeterInstall.getTenantId() != null, TenantCustomerMeterInstall::getTenantId, tenantCustomerMeterInstall.getTenantId())
				.set(tenantCustomerMeterInstall.getMeterId() != null, TenantCustomerMeterInstall::getMeterId, tenantCustomerMeterInstall.getMeterId())
				.set(tenantCustomerMeterInstall.getMeterCode() != null, TenantCustomerMeterInstall::getMeterCode, tenantCustomerMeterInstall.getMeterCode())
				.set(tenantCustomerMeterInstall.getCustName() != null, TenantCustomerMeterInstall::getCustName, tenantCustomerMeterInstall.getCustName())
				.set(tenantCustomerMeterInstall.getMeterAddress() != null, TenantCustomerMeterInstall::getMeterAddress, tenantCustomerMeterInstall.getMeterAddress())
				.set(tenantCustomerMeterInstall.getMeterMachineCode() != null, TenantCustomerMeterInstall::getMeterMachineCode, tenantCustomerMeterInstall.getMeterMachineCode())
				.set(tenantCustomerMeterInstall.getManufactorId() != null, TenantCustomerMeterInstall::getManufactorId, tenantCustomerMeterInstall.getManufactorId())
				.set(tenantCustomerMeterInstall.getMeterType() != null, TenantCustomerMeterInstall::getMeterType, tenantCustomerMeterInstall.getMeterType())
				.set(tenantCustomerMeterInstall.getCaliberId() != null, TenantCustomerMeterInstall::getCaliberId, tenantCustomerMeterInstall.getCaliberId())
				.set(tenantCustomerMeterInstall.getWaterTypeId() != null, TenantCustomerMeterInstall::getWaterTypeId, tenantCustomerMeterInstall.getWaterTypeId())
				.set(tenantCustomerMeterInstall.getPriceTypeId() != null, TenantCustomerMeterInstall::getPriceTypeId, tenantCustomerMeterInstall.getPriceTypeId())
				.set(tenantCustomerMeterInstall.getMeterIotCode() != null, TenantCustomerMeterInstall::getMeterIotCode, tenantCustomerMeterInstall.getMeterIotCode())
				.set(tenantCustomerMeterInstall.getMeterInstallDate() != null, TenantCustomerMeterInstall::getMeterInstallDate, tenantCustomerMeterInstall.getMeterInstallDate())
				.set(tenantCustomerMeterInstall.getMeterLastSettleTime() != null, TenantCustomerMeterInstall::getMeterLastSettleTime, tenantCustomerMeterInstall.getMeterLastSettleTime())
				.set(tenantCustomerMeterInstall.getMeterLastSettlePointer() != null, TenantCustomerMeterInstall::getMeterLastSettlePointer, tenantCustomerMeterInstall.getMeterLastSettlePointer())
				.set(tenantCustomerMeterInstall.getLinkmanMobile() != null, TenantCustomerMeterInstall::getLinkmanMobile, tenantCustomerMeterInstall.getLinkmanMobile())
				.set(tenantCustomerMeterInstall.getCustmerIdNo() != null, TenantCustomerMeterInstall::getCustmerIdNo, tenantCustomerMeterInstall.getCustmerIdNo())
				.set(tenantCustomerMeterInstall.getOldCode() != null, TenantCustomerMeterInstall::getOldCode, tenantCustomerMeterInstall.getOldCode())
				.set(tenantCustomerMeterInstall.getInputType() != null, TenantCustomerMeterInstall::getInputType, tenantCustomerMeterInstall.getInputType())
				.set(tenantCustomerMeterInstall.getInputTime() != null, TenantCustomerMeterInstall::getInputTime, tenantCustomerMeterInstall.getInputTime())
				.set(tenantCustomerMeterInstall.getCreateOn() != null, TenantCustomerMeterInstall::getCreateOn, tenantCustomerMeterInstall.getCreateOn())
				;

		boolean success = tenantCustomerMeterInstallService.update(updateWrapperTenantCustomerMeterInstall);
		if (success) {
			TenantCustomerMeterInstall tenantCustomerMeterInstallDatabase = tenantCustomerMeterInstallService.getById(id);
			return entity2vo(tenantCustomerMeterInstallDatabase);
		}
		log.info("partial update TenantCustomerMeterInstall fail，{}",
				ToStringBuilder.reflectionToString(tenantCustomerMeterInstall, ToStringStyle.JSON_STYLE));
		return null;
	}

	@ApiOperation(value = "根据ID删除用户水表立户")
	@RequestMapping(value = "/tenant-customer-meter-installs/{id}", method = RequestMethod.DELETE)
	public CommonResult<Object> removeById(@PathVariable("id") String id) {
		boolean success = tenantCustomerMeterInstallService.removeById(id);
		return success ? CommonResult.success(success) : CommonResult.failed();
	}

	private TenantCustomerMeterInstallVo entity2vo(TenantCustomerMeterInstall tenantCustomerMeterInstall) {
		if (tenantCustomerMeterInstall == null) {
			return null;
		}

		String jsonString = JSON.toJSONString(tenantCustomerMeterInstall);
		TenantCustomerMeterInstallVo tenantCustomerMeterInstallVo = JSON.parseObject(jsonString, TenantCustomerMeterInstallVo.class);
		if (StringUtils.isEmpty(tenantCustomerMeterInstallVo.getTenantName())) {
			TenantInfo tenantInfo = tenantInfoService.getDictionaryById(tenantCustomerMeterInstall.getTenantId());
			if (tenantInfo != null) {
				tenantCustomerMeterInstallVo.setTenantName(tenantInfo.getTenantName());
			}
		}
		return tenantCustomerMeterInstallVo;
	}

}
