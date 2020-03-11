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
import com.zlsrj.wms.api.dto.TenantMeterReadLogCurrentQueryParam;
import com.zlsrj.wms.api.entity.TenantInfo;
import com.zlsrj.wms.api.entity.TenantMeterReadLogCurrent;
import com.zlsrj.wms.api.vo.TenantMeterReadLogCurrentVo;
import com.zlsrj.wms.common.api.CommonResult;
import com.zlsrj.wms.saas.service.IIdService;
import com.zlsrj.wms.saas.service.ITenantInfoService;
import com.zlsrj.wms.saas.service.ITenantMeterReadLogCurrentService;

import cn.hutool.core.date.DateUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

@Api(value = "当期抄表计划", tags = { "当期抄表计划操作接口" })
@RestController
@Slf4j
public class TenantMeterReadLogCurrentRestController {

	@Autowired
	private ITenantMeterReadLogCurrentService tenantMeterReadLogCurrentService;
	@Autowired
	private ITenantInfoService tenantInfoService;
	@Autowired
	private IIdService idService;

	@ApiOperation(value = "根据ID查询当期抄表计划")
	@RequestMapping(value = "/tenant-meter-read-log-currents/{id}", method = RequestMethod.GET)
	public TenantMeterReadLogCurrentVo getById(@PathVariable("id") String id) {
		TenantMeterReadLogCurrent tenantMeterReadLogCurrent = tenantMeterReadLogCurrentService.getById(id);

		return entity2vo(tenantMeterReadLogCurrent);
	}

	@ApiOperation(value = "根据参数查询当期抄表计划列表")
	@RequestMapping(value = "/tenant-meter-read-log-currents", method = RequestMethod.GET)
	public Page<TenantMeterReadLogCurrentVo> page(@RequestBody TenantMeterReadLogCurrentQueryParam tenantMeterReadLogCurrentQueryParam,
			@RequestParam(value = "page", defaultValue = "1") int page, //
			@RequestParam(value = "rows", defaultValue = "10") int rows, //
			@RequestParam(value = "sort") String sort, // 排序列字段名
			@RequestParam(value = "order") String order // 可以是 'asc' 或者 'desc'，默认值是 'asc'
	) {
		IPage<TenantMeterReadLogCurrent> pageTenantMeterReadLogCurrent = new Page<TenantMeterReadLogCurrent>(page, rows);
		QueryWrapper<TenantMeterReadLogCurrent> queryWrapperTenantMeterReadLogCurrent = new QueryWrapper<TenantMeterReadLogCurrent>();
		queryWrapperTenantMeterReadLogCurrent.orderBy(StringUtils.isNotEmpty(sort), "asc".equals(order), sort);
		queryWrapperTenantMeterReadLogCurrent.lambda()
				.eq(tenantMeterReadLogCurrentQueryParam.getId() != null, TenantMeterReadLogCurrent::getId, tenantMeterReadLogCurrentQueryParam.getId())
				.eq(tenantMeterReadLogCurrentQueryParam.getTenantId() != null, TenantMeterReadLogCurrent::getTenantId, tenantMeterReadLogCurrentQueryParam.getTenantId())
				.eq(tenantMeterReadLogCurrentQueryParam.getReadMonth() != null, TenantMeterReadLogCurrent::getReadMonth, tenantMeterReadLogCurrentQueryParam.getReadMonth())
				.ge(tenantMeterReadLogCurrentQueryParam.getReadMonthStart() != null, TenantMeterReadLogCurrent::getReadMonth,tenantMeterReadLogCurrentQueryParam.getReadMonthStart() == null ? null: DateUtil.beginOfDay(tenantMeterReadLogCurrentQueryParam.getReadMonthStart()))
				.le(tenantMeterReadLogCurrentQueryParam.getReadMonthEnd() != null, TenantMeterReadLogCurrent::getReadMonth,tenantMeterReadLogCurrentQueryParam.getReadMonthEnd() == null ? null: DateUtil.endOfDay(tenantMeterReadLogCurrentQueryParam.getReadMonthEnd()))
				.eq(tenantMeterReadLogCurrentQueryParam.getCustomerId() != null, TenantMeterReadLogCurrent::getCustomerId, tenantMeterReadLogCurrentQueryParam.getCustomerId())
				.eq(tenantMeterReadLogCurrentQueryParam.getMeterId() != null, TenantMeterReadLogCurrent::getMeterId, tenantMeterReadLogCurrentQueryParam.getMeterId())
				.eq(tenantMeterReadLogCurrentQueryParam.getMeterYearTotalWatersBefore() != null, TenantMeterReadLogCurrent::getMeterYearTotalWatersBefore, tenantMeterReadLogCurrentQueryParam.getMeterYearTotalWatersBefore())
				.eq(tenantMeterReadLogCurrentQueryParam.getSettleStartTime() != null, TenantMeterReadLogCurrent::getSettleStartTime, tenantMeterReadLogCurrentQueryParam.getSettleStartTime())
				.ge(tenantMeterReadLogCurrentQueryParam.getSettleStartTimeStart() != null, TenantMeterReadLogCurrent::getSettleStartTime,tenantMeterReadLogCurrentQueryParam.getSettleStartTimeStart() == null ? null: DateUtil.beginOfDay(tenantMeterReadLogCurrentQueryParam.getSettleStartTimeStart()))
				.le(tenantMeterReadLogCurrentQueryParam.getSettleStartTimeEnd() != null, TenantMeterReadLogCurrent::getSettleStartTime,tenantMeterReadLogCurrentQueryParam.getSettleStartTimeEnd() == null ? null: DateUtil.endOfDay(tenantMeterReadLogCurrentQueryParam.getSettleStartTimeEnd()))
				.eq(tenantMeterReadLogCurrentQueryParam.getSettleStartPointer() != null, TenantMeterReadLogCurrent::getSettleStartPointer, tenantMeterReadLogCurrentQueryParam.getSettleStartPointer())
				.eq(tenantMeterReadLogCurrentQueryParam.getCurrentReadTime() != null, TenantMeterReadLogCurrent::getCurrentReadTime, tenantMeterReadLogCurrentQueryParam.getCurrentReadTime())
				.ge(tenantMeterReadLogCurrentQueryParam.getCurrentReadTimeStart() != null, TenantMeterReadLogCurrent::getCurrentReadTime,tenantMeterReadLogCurrentQueryParam.getCurrentReadTimeStart() == null ? null: DateUtil.beginOfDay(tenantMeterReadLogCurrentQueryParam.getCurrentReadTimeStart()))
				.le(tenantMeterReadLogCurrentQueryParam.getCurrentReadTimeEnd() != null, TenantMeterReadLogCurrent::getCurrentReadTime,tenantMeterReadLogCurrentQueryParam.getCurrentReadTimeEnd() == null ? null: DateUtil.endOfDay(tenantMeterReadLogCurrentQueryParam.getCurrentReadTimeEnd()))
				.eq(tenantMeterReadLogCurrentQueryParam.getCurrentReadPointer() != null, TenantMeterReadLogCurrent::getCurrentReadPointer, tenantMeterReadLogCurrentQueryParam.getCurrentReadPointer())
				.eq(tenantMeterReadLogCurrentQueryParam.getReadEmployeeId() != null, TenantMeterReadLogCurrent::getReadEmployeeId, tenantMeterReadLogCurrentQueryParam.getReadEmployeeId())
				.eq(tenantMeterReadLogCurrentQueryParam.getMeterStatusId() != null, TenantMeterReadLogCurrent::getMeterStatusId, tenantMeterReadLogCurrentQueryParam.getMeterStatusId())
				.eq(tenantMeterReadLogCurrentQueryParam.getSettleWaters() != null, TenantMeterReadLogCurrent::getSettleWaters, tenantMeterReadLogCurrentQueryParam.getSettleWaters())
				.eq(tenantMeterReadLogCurrentQueryParam.getReceivableWaters() != null, TenantMeterReadLogCurrent::getReceivableWaters, tenantMeterReadLogCurrentQueryParam.getReceivableWaters())
				.eq(tenantMeterReadLogCurrentQueryParam.getReadSource() != null, TenantMeterReadLogCurrent::getReadSource, tenantMeterReadLogCurrentQueryParam.getReadSource())
				.eq(tenantMeterReadLogCurrentQueryParam.getReadStatus() != null, TenantMeterReadLogCurrent::getReadStatus, tenantMeterReadLogCurrentQueryParam.getReadStatus())
				.eq(tenantMeterReadLogCurrentQueryParam.getCheckResult() != null, TenantMeterReadLogCurrent::getCheckResult, tenantMeterReadLogCurrentQueryParam.getCheckResult())
				.eq(tenantMeterReadLogCurrentQueryParam.getProcessReault() != null, TenantMeterReadLogCurrent::getProcessReault, tenantMeterReadLogCurrentQueryParam.getProcessReault())
				.eq(tenantMeterReadLogCurrentQueryParam.getProcessEmployeeId() != null, TenantMeterReadLogCurrent::getProcessEmployeeId, tenantMeterReadLogCurrentQueryParam.getProcessEmployeeId())
				.eq(tenantMeterReadLogCurrentQueryParam.getProcessTime() != null, TenantMeterReadLogCurrent::getProcessTime, tenantMeterReadLogCurrentQueryParam.getProcessTime())
				.ge(tenantMeterReadLogCurrentQueryParam.getProcessTimeStart() != null, TenantMeterReadLogCurrent::getProcessTime,tenantMeterReadLogCurrentQueryParam.getProcessTimeStart() == null ? null: DateUtil.beginOfDay(tenantMeterReadLogCurrentQueryParam.getProcessTimeStart()))
				.le(tenantMeterReadLogCurrentQueryParam.getProcessTimeEnd() != null, TenantMeterReadLogCurrent::getProcessTime,tenantMeterReadLogCurrentQueryParam.getProcessTimeEnd() == null ? null: DateUtil.endOfDay(tenantMeterReadLogCurrentQueryParam.getProcessTimeEnd()))
				.eq(tenantMeterReadLogCurrentQueryParam.getProcessType() != null, TenantMeterReadLogCurrent::getProcessType, tenantMeterReadLogCurrentQueryParam.getProcessType())
				;

		IPage<TenantMeterReadLogCurrent> tenantMeterReadLogCurrentPage = tenantMeterReadLogCurrentService.page(pageTenantMeterReadLogCurrent, queryWrapperTenantMeterReadLogCurrent);

		Page<TenantMeterReadLogCurrentVo> tenantMeterReadLogCurrentVoPage = new Page<TenantMeterReadLogCurrentVo>(page, rows);
		tenantMeterReadLogCurrentVoPage.setCurrent(tenantMeterReadLogCurrentPage.getCurrent());
		tenantMeterReadLogCurrentVoPage.setPages(tenantMeterReadLogCurrentPage.getPages());
		tenantMeterReadLogCurrentVoPage.setSize(tenantMeterReadLogCurrentPage.getSize());
		tenantMeterReadLogCurrentVoPage.setTotal(tenantMeterReadLogCurrentPage.getTotal());
		tenantMeterReadLogCurrentVoPage.setRecords(tenantMeterReadLogCurrentPage.getRecords().stream()//
				.map(e -> entity2vo(e))//
				.collect(Collectors.toList()));

		return tenantMeterReadLogCurrentVoPage;
	}

	@ApiOperation(value = "新增当期抄表计划")
	@RequestMapping(value = "/tenant-meter-read-log-currents", method = RequestMethod.POST)
	public TenantMeterReadLogCurrentVo save(@RequestBody TenantMeterReadLogCurrent tenantMeterReadLogCurrent) {
		if (tenantMeterReadLogCurrent.getId() == null || tenantMeterReadLogCurrent.getId().trim().length() <= 0) {
			tenantMeterReadLogCurrent.setId(idService.selectId());
		}
		boolean success = tenantMeterReadLogCurrentService.save(tenantMeterReadLogCurrent);
		if (success) {
			TenantMeterReadLogCurrent tenantMeterReadLogCurrentDatabase = tenantMeterReadLogCurrentService.getById(tenantMeterReadLogCurrent.getId());
			return entity2vo(tenantMeterReadLogCurrentDatabase);
		}
		log.info("save TenantMeterReadLogCurrent fail，{}", ToStringBuilder.reflectionToString(tenantMeterReadLogCurrent, ToStringStyle.JSON_STYLE));
		return null;
	}

	@ApiOperation(value = "更新当期抄表计划全部信息")
	@RequestMapping(value = "/tenant-meter-read-log-currents/{id}", method = RequestMethod.PUT)
	public TenantMeterReadLogCurrentVo updateById(@PathVariable("id") String id, @RequestBody TenantMeterReadLogCurrent tenantMeterReadLogCurrent) {
		tenantMeterReadLogCurrent.setId(id);
		boolean success = tenantMeterReadLogCurrentService.updateById(tenantMeterReadLogCurrent);
		if (success) {
			TenantMeterReadLogCurrent tenantMeterReadLogCurrentDatabase = tenantMeterReadLogCurrentService.getById(id);
			return entity2vo(tenantMeterReadLogCurrentDatabase);
		}
		log.info("update TenantMeterReadLogCurrent fail，{}", ToStringBuilder.reflectionToString(tenantMeterReadLogCurrent, ToStringStyle.JSON_STYLE));
		return null;
	}

	@ApiOperation(value = "根据参数更新当期抄表计划信息")
	@RequestMapping(value = "/tenant-meter-read-log-currents/{id}", method = RequestMethod.PATCH)
	public TenantMeterReadLogCurrentVo updatePatchById(@PathVariable("id") String id, @RequestBody TenantMeterReadLogCurrent tenantMeterReadLogCurrent) {
        TenantMeterReadLogCurrent tenantMeterReadLogCurrentWhere = TenantMeterReadLogCurrent.builder()//
				.id(id)//
				.build();
		UpdateWrapper<TenantMeterReadLogCurrent> updateWrapperTenantMeterReadLogCurrent = new UpdateWrapper<TenantMeterReadLogCurrent>();
		updateWrapperTenantMeterReadLogCurrent.setEntity(tenantMeterReadLogCurrentWhere);
		updateWrapperTenantMeterReadLogCurrent.lambda()//
				//.eq(TenantMeterReadLogCurrent::getId, id)
				// .set(tenantMeterReadLogCurrent.getId() != null, TenantMeterReadLogCurrent::getId, tenantMeterReadLogCurrent.getId())
				.set(tenantMeterReadLogCurrent.getTenantId() != null, TenantMeterReadLogCurrent::getTenantId, tenantMeterReadLogCurrent.getTenantId())
				.set(tenantMeterReadLogCurrent.getReadMonth() != null, TenantMeterReadLogCurrent::getReadMonth, tenantMeterReadLogCurrent.getReadMonth())
				.set(tenantMeterReadLogCurrent.getCustomerId() != null, TenantMeterReadLogCurrent::getCustomerId, tenantMeterReadLogCurrent.getCustomerId())
				.set(tenantMeterReadLogCurrent.getMeterId() != null, TenantMeterReadLogCurrent::getMeterId, tenantMeterReadLogCurrent.getMeterId())
				.set(tenantMeterReadLogCurrent.getMeterYearTotalWatersBefore() != null, TenantMeterReadLogCurrent::getMeterYearTotalWatersBefore, tenantMeterReadLogCurrent.getMeterYearTotalWatersBefore())
				.set(tenantMeterReadLogCurrent.getSettleStartTime() != null, TenantMeterReadLogCurrent::getSettleStartTime, tenantMeterReadLogCurrent.getSettleStartTime())
				.set(tenantMeterReadLogCurrent.getSettleStartPointer() != null, TenantMeterReadLogCurrent::getSettleStartPointer, tenantMeterReadLogCurrent.getSettleStartPointer())
				.set(tenantMeterReadLogCurrent.getCurrentReadTime() != null, TenantMeterReadLogCurrent::getCurrentReadTime, tenantMeterReadLogCurrent.getCurrentReadTime())
				.set(tenantMeterReadLogCurrent.getCurrentReadPointer() != null, TenantMeterReadLogCurrent::getCurrentReadPointer, tenantMeterReadLogCurrent.getCurrentReadPointer())
				.set(tenantMeterReadLogCurrent.getReadEmployeeId() != null, TenantMeterReadLogCurrent::getReadEmployeeId, tenantMeterReadLogCurrent.getReadEmployeeId())
				.set(tenantMeterReadLogCurrent.getMeterStatusId() != null, TenantMeterReadLogCurrent::getMeterStatusId, tenantMeterReadLogCurrent.getMeterStatusId())
				.set(tenantMeterReadLogCurrent.getSettleWaters() != null, TenantMeterReadLogCurrent::getSettleWaters, tenantMeterReadLogCurrent.getSettleWaters())
				.set(tenantMeterReadLogCurrent.getReceivableWaters() != null, TenantMeterReadLogCurrent::getReceivableWaters, tenantMeterReadLogCurrent.getReceivableWaters())
				.set(tenantMeterReadLogCurrent.getReadSource() != null, TenantMeterReadLogCurrent::getReadSource, tenantMeterReadLogCurrent.getReadSource())
				.set(tenantMeterReadLogCurrent.getReadStatus() != null, TenantMeterReadLogCurrent::getReadStatus, tenantMeterReadLogCurrent.getReadStatus())
				.set(tenantMeterReadLogCurrent.getCheckResult() != null, TenantMeterReadLogCurrent::getCheckResult, tenantMeterReadLogCurrent.getCheckResult())
				.set(tenantMeterReadLogCurrent.getProcessReault() != null, TenantMeterReadLogCurrent::getProcessReault, tenantMeterReadLogCurrent.getProcessReault())
				.set(tenantMeterReadLogCurrent.getProcessEmployeeId() != null, TenantMeterReadLogCurrent::getProcessEmployeeId, tenantMeterReadLogCurrent.getProcessEmployeeId())
				.set(tenantMeterReadLogCurrent.getProcessTime() != null, TenantMeterReadLogCurrent::getProcessTime, tenantMeterReadLogCurrent.getProcessTime())
				.set(tenantMeterReadLogCurrent.getProcessType() != null, TenantMeterReadLogCurrent::getProcessType, tenantMeterReadLogCurrent.getProcessType())
				;

		boolean success = tenantMeterReadLogCurrentService.update(updateWrapperTenantMeterReadLogCurrent);
		if (success) {
			TenantMeterReadLogCurrent tenantMeterReadLogCurrentDatabase = tenantMeterReadLogCurrentService.getById(id);
			return entity2vo(tenantMeterReadLogCurrentDatabase);
		}
		log.info("partial update TenantMeterReadLogCurrent fail，{}",
				ToStringBuilder.reflectionToString(tenantMeterReadLogCurrent, ToStringStyle.JSON_STYLE));
		return null;
	}

	@ApiOperation(value = "根据ID删除当期抄表计划")
	@RequestMapping(value = "/tenant-meter-read-log-currents/{id}", method = RequestMethod.DELETE)
	public CommonResult<Object> removeById(@PathVariable("id") String id) {
		boolean success = tenantMeterReadLogCurrentService.removeById(id);
		return success ? CommonResult.success(success) : CommonResult.failed();
	}

	private TenantMeterReadLogCurrentVo entity2vo(TenantMeterReadLogCurrent tenantMeterReadLogCurrent) {
		if (tenantMeterReadLogCurrent == null) {
			return null;
		}

		String jsonString = JSON.toJSONString(tenantMeterReadLogCurrent);
		TenantMeterReadLogCurrentVo tenantMeterReadLogCurrentVo = JSON.parseObject(jsonString, TenantMeterReadLogCurrentVo.class);
		if (StringUtils.isEmpty(tenantMeterReadLogCurrentVo.getTenantName())) {
			TenantInfo tenantInfo = tenantInfoService.getById(tenantMeterReadLogCurrent.getTenantId());
			if (tenantInfo != null) {
				tenantMeterReadLogCurrentVo.setTenantName(tenantInfo.getTenantName());
			}
		}
		return tenantMeterReadLogCurrentVo;
	}

}
