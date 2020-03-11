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
import com.zlsrj.wms.api.dto.TenantReceivableQueryParam;
import com.zlsrj.wms.api.entity.TenantInfo;
import com.zlsrj.wms.api.entity.TenantReceivable;
import com.zlsrj.wms.api.vo.TenantReceivableVo;
import com.zlsrj.wms.common.api.CommonResult;
import com.zlsrj.wms.saas.service.IIdService;
import com.zlsrj.wms.saas.service.ITenantInfoService;
import com.zlsrj.wms.saas.service.ITenantReceivableService;

import cn.hutool.core.date.DateUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

@Api(value = "应收明细", tags = { "应收明细操作接口" })
@RestController
@Slf4j
public class TenantReceivableRestController {

	@Autowired
	private ITenantReceivableService tenantReceivableService;
	@Autowired
	private ITenantInfoService tenantInfoService;
	@Autowired
	private IIdService idService;

	@ApiOperation(value = "根据ID查询应收明细")
	@RequestMapping(value = "/tenant-receivables/{id}", method = RequestMethod.GET)
	public TenantReceivableVo getById(@PathVariable("id") String id) {
		TenantReceivable tenantReceivable = tenantReceivableService.getById(id);

		return entity2vo(tenantReceivable);
	}

	@ApiOperation(value = "根据参数查询应收明细列表")
	@RequestMapping(value = "/tenant-receivables", method = RequestMethod.GET)
	public Page<TenantReceivableVo> page(@RequestBody TenantReceivableQueryParam tenantReceivableQueryParam,
			@RequestParam(value = "page", defaultValue = "1") int page, //
			@RequestParam(value = "rows", defaultValue = "10") int rows, //
			@RequestParam(value = "sort") String sort, // 排序列字段名
			@RequestParam(value = "order") String order // 可以是 'asc' 或者 'desc'，默认值是 'asc'
	) {
		IPage<TenantReceivable> pageTenantReceivable = new Page<TenantReceivable>(page, rows);
		QueryWrapper<TenantReceivable> queryWrapperTenantReceivable = new QueryWrapper<TenantReceivable>();
		queryWrapperTenantReceivable.orderBy(StringUtils.isNotEmpty(sort), "asc".equals(order), sort);
		queryWrapperTenantReceivable.lambda()
				.eq(tenantReceivableQueryParam.getId() != null, TenantReceivable::getId, tenantReceivableQueryParam.getId())
				.eq(tenantReceivableQueryParam.getTenantId() != null, TenantReceivable::getTenantId, tenantReceivableQueryParam.getTenantId())
				.eq(tenantReceivableQueryParam.getReceivableStatus() != null, TenantReceivable::getReceivableStatus, tenantReceivableQueryParam.getReceivableStatus())
				.eq(tenantReceivableQueryParam.getReceivableType() != null, TenantReceivable::getReceivableType, tenantReceivableQueryParam.getReceivableType())
				.eq(tenantReceivableQueryParam.getDepartmentId() != null, TenantReceivable::getDepartmentId, tenantReceivableQueryParam.getDepartmentId())
				.eq(tenantReceivableQueryParam.getBookletId() != null, TenantReceivable::getBookletId, tenantReceivableQueryParam.getBookletId())
				.eq(tenantReceivableQueryParam.getBookletCode() != null, TenantReceivable::getBookletCode, tenantReceivableQueryParam.getBookletCode())
				.eq(tenantReceivableQueryParam.getCustomerId() != null, TenantReceivable::getCustomerId, tenantReceivableQueryParam.getCustomerId())
				.eq(tenantReceivableQueryParam.getCustomerCode() != null, TenantReceivable::getCustomerCode, tenantReceivableQueryParam.getCustomerCode())
				.eq(tenantReceivableQueryParam.getCustomerName() != null, TenantReceivable::getCustomerName, tenantReceivableQueryParam.getCustomerName())
				.eq(tenantReceivableQueryParam.getCustomerAddress() != null, TenantReceivable::getCustomerAddress, tenantReceivableQueryParam.getCustomerAddress())
				.eq(tenantReceivableQueryParam.getMeterId() != null, TenantReceivable::getMeterId, tenantReceivableQueryParam.getMeterId())
				.eq(tenantReceivableQueryParam.getMeterCode() != null, TenantReceivable::getMeterCode, tenantReceivableQueryParam.getMeterCode())
				.eq(tenantReceivableQueryParam.getMeterAddress() != null, TenantReceivable::getMeterAddress, tenantReceivableQueryParam.getMeterAddress())
				.eq(tenantReceivableQueryParam.getReadEmployeeId() != null, TenantReceivable::getReadEmployeeId, tenantReceivableQueryParam.getReadEmployeeId())
				.eq(tenantReceivableQueryParam.getReceivableTime() != null, TenantReceivable::getReceivableTime, tenantReceivableQueryParam.getReceivableTime())
				.ge(tenantReceivableQueryParam.getReceivableTimeStart() != null, TenantReceivable::getReceivableTime,tenantReceivableQueryParam.getReceivableTimeStart() == null ? null: DateUtil.beginOfDay(tenantReceivableQueryParam.getReceivableTimeStart()))
				.le(tenantReceivableQueryParam.getReceivableTimeEnd() != null, TenantReceivable::getReceivableTime,tenantReceivableQueryParam.getReceivableTimeEnd() == null ? null: DateUtil.endOfDay(tenantReceivableQueryParam.getReceivableTimeEnd()))
				.eq(tenantReceivableQueryParam.getSettleStartTime() != null, TenantReceivable::getSettleStartTime, tenantReceivableQueryParam.getSettleStartTime())
				.ge(tenantReceivableQueryParam.getSettleStartTimeStart() != null, TenantReceivable::getSettleStartTime,tenantReceivableQueryParam.getSettleStartTimeStart() == null ? null: DateUtil.beginOfDay(tenantReceivableQueryParam.getSettleStartTimeStart()))
				.le(tenantReceivableQueryParam.getSettleStartTimeEnd() != null, TenantReceivable::getSettleStartTime,tenantReceivableQueryParam.getSettleStartTimeEnd() == null ? null: DateUtil.endOfDay(tenantReceivableQueryParam.getSettleStartTimeEnd()))
				.eq(tenantReceivableQueryParam.getSettleStartPointer() != null, TenantReceivable::getSettleStartPointer, tenantReceivableQueryParam.getSettleStartPointer())
				.eq(tenantReceivableQueryParam.getSettleEndTime() != null, TenantReceivable::getSettleEndTime, tenantReceivableQueryParam.getSettleEndTime())
				.ge(tenantReceivableQueryParam.getSettleEndTimeStart() != null, TenantReceivable::getSettleEndTime,tenantReceivableQueryParam.getSettleEndTimeStart() == null ? null: DateUtil.beginOfDay(tenantReceivableQueryParam.getSettleEndTimeStart()))
				.le(tenantReceivableQueryParam.getSettleEndTimeEnd() != null, TenantReceivable::getSettleEndTime,tenantReceivableQueryParam.getSettleEndTimeEnd() == null ? null: DateUtil.endOfDay(tenantReceivableQueryParam.getSettleEndTimeEnd()))
				.eq(tenantReceivableQueryParam.getSettleEndPointer() != null, TenantReceivable::getSettleEndPointer, tenantReceivableQueryParam.getSettleEndPointer())
				.eq(tenantReceivableQueryParam.getSettleWaters() != null, TenantReceivable::getSettleWaters, tenantReceivableQueryParam.getSettleWaters())
				.eq(tenantReceivableQueryParam.getPriceTypeId() != null, TenantReceivable::getPriceTypeId, tenantReceivableQueryParam.getPriceTypeId())
				.eq(tenantReceivableQueryParam.getReceivableWaters() != null, TenantReceivable::getReceivableWaters, tenantReceivableQueryParam.getReceivableWaters())
				.eq(tenantReceivableQueryParam.getReceivableMoney() != null, TenantReceivable::getReceivableMoney, tenantReceivableQueryParam.getReceivableMoney())
				.eq(tenantReceivableQueryParam.getArrearsMoney() != null, TenantReceivable::getArrearsMoney, tenantReceivableQueryParam.getArrearsMoney())
				;

		IPage<TenantReceivable> tenantReceivablePage = tenantReceivableService.page(pageTenantReceivable, queryWrapperTenantReceivable);

		Page<TenantReceivableVo> tenantReceivableVoPage = new Page<TenantReceivableVo>(page, rows);
		tenantReceivableVoPage.setCurrent(tenantReceivablePage.getCurrent());
		tenantReceivableVoPage.setPages(tenantReceivablePage.getPages());
		tenantReceivableVoPage.setSize(tenantReceivablePage.getSize());
		tenantReceivableVoPage.setTotal(tenantReceivablePage.getTotal());
		tenantReceivableVoPage.setRecords(tenantReceivablePage.getRecords().stream()//
				.map(e -> entity2vo(e))//
				.collect(Collectors.toList()));

		return tenantReceivableVoPage;
	}
	
	@ApiOperation(value = "根据参数查询应收明细统计")
	@RequestMapping(value = "/tenant-receivables/aggregation", method = RequestMethod.GET)
	public TenantReceivableVo aggregation(@RequestBody TenantReceivableQueryParam tenantReceivableQueryParam) {
		QueryWrapper<TenantReceivable> queryWrapperTenantReceivable = new QueryWrapper<TenantReceivable>();
		queryWrapperTenantReceivable.lambda()
				.eq(tenantReceivableQueryParam.getId() != null, TenantReceivable::getId, tenantReceivableQueryParam.getId())
				.eq(tenantReceivableQueryParam.getTenantId() != null, TenantReceivable::getTenantId, tenantReceivableQueryParam.getTenantId())
				.eq(tenantReceivableQueryParam.getReceivableStatus() != null, TenantReceivable::getReceivableStatus, tenantReceivableQueryParam.getReceivableStatus())
				.eq(tenantReceivableQueryParam.getReceivableType() != null, TenantReceivable::getReceivableType, tenantReceivableQueryParam.getReceivableType())
				.eq(tenantReceivableQueryParam.getDepartmentId() != null, TenantReceivable::getDepartmentId, tenantReceivableQueryParam.getDepartmentId())
				.eq(tenantReceivableQueryParam.getBookletId() != null, TenantReceivable::getBookletId, tenantReceivableQueryParam.getBookletId())
				.eq(tenantReceivableQueryParam.getBookletCode() != null, TenantReceivable::getBookletCode, tenantReceivableQueryParam.getBookletCode())
				.eq(tenantReceivableQueryParam.getCustomerId() != null, TenantReceivable::getCustomerId, tenantReceivableQueryParam.getCustomerId())
				.eq(tenantReceivableQueryParam.getCustomerCode() != null, TenantReceivable::getCustomerCode, tenantReceivableQueryParam.getCustomerCode())
				.eq(tenantReceivableQueryParam.getCustomerName() != null, TenantReceivable::getCustomerName, tenantReceivableQueryParam.getCustomerName())
				.eq(tenantReceivableQueryParam.getCustomerAddress() != null, TenantReceivable::getCustomerAddress, tenantReceivableQueryParam.getCustomerAddress())
				.eq(tenantReceivableQueryParam.getMeterId() != null, TenantReceivable::getMeterId, tenantReceivableQueryParam.getMeterId())
				.eq(tenantReceivableQueryParam.getMeterCode() != null, TenantReceivable::getMeterCode, tenantReceivableQueryParam.getMeterCode())
				.eq(tenantReceivableQueryParam.getMeterAddress() != null, TenantReceivable::getMeterAddress, tenantReceivableQueryParam.getMeterAddress())
				.eq(tenantReceivableQueryParam.getReadEmployeeId() != null, TenantReceivable::getReadEmployeeId, tenantReceivableQueryParam.getReadEmployeeId())
				.eq(tenantReceivableQueryParam.getReceivableTime() != null, TenantReceivable::getReceivableTime, tenantReceivableQueryParam.getReceivableTime())
				.ge(tenantReceivableQueryParam.getReceivableTimeStart() != null, TenantReceivable::getReceivableTime,tenantReceivableQueryParam.getReceivableTimeStart() == null ? null: DateUtil.beginOfDay(tenantReceivableQueryParam.getReceivableTimeStart()))
				.le(tenantReceivableQueryParam.getReceivableTimeEnd() != null, TenantReceivable::getReceivableTime,tenantReceivableQueryParam.getReceivableTimeEnd() == null ? null: DateUtil.endOfDay(tenantReceivableQueryParam.getReceivableTimeEnd()))
				.eq(tenantReceivableQueryParam.getSettleStartTime() != null, TenantReceivable::getSettleStartTime, tenantReceivableQueryParam.getSettleStartTime())
				.ge(tenantReceivableQueryParam.getSettleStartTimeStart() != null, TenantReceivable::getSettleStartTime,tenantReceivableQueryParam.getSettleStartTimeStart() == null ? null: DateUtil.beginOfDay(tenantReceivableQueryParam.getSettleStartTimeStart()))
				.le(tenantReceivableQueryParam.getSettleStartTimeEnd() != null, TenantReceivable::getSettleStartTime,tenantReceivableQueryParam.getSettleStartTimeEnd() == null ? null: DateUtil.endOfDay(tenantReceivableQueryParam.getSettleStartTimeEnd()))
				.eq(tenantReceivableQueryParam.getSettleStartPointer() != null, TenantReceivable::getSettleStartPointer, tenantReceivableQueryParam.getSettleStartPointer())
				.eq(tenantReceivableQueryParam.getSettleEndTime() != null, TenantReceivable::getSettleEndTime, tenantReceivableQueryParam.getSettleEndTime())
				.ge(tenantReceivableQueryParam.getSettleEndTimeStart() != null, TenantReceivable::getSettleEndTime,tenantReceivableQueryParam.getSettleEndTimeStart() == null ? null: DateUtil.beginOfDay(tenantReceivableQueryParam.getSettleEndTimeStart()))
				.le(tenantReceivableQueryParam.getSettleEndTimeEnd() != null, TenantReceivable::getSettleEndTime,tenantReceivableQueryParam.getSettleEndTimeEnd() == null ? null: DateUtil.endOfDay(tenantReceivableQueryParam.getSettleEndTimeEnd()))
				.eq(tenantReceivableQueryParam.getSettleEndPointer() != null, TenantReceivable::getSettleEndPointer, tenantReceivableQueryParam.getSettleEndPointer())
				.eq(tenantReceivableQueryParam.getSettleWaters() != null, TenantReceivable::getSettleWaters, tenantReceivableQueryParam.getSettleWaters())
				.eq(tenantReceivableQueryParam.getPriceTypeId() != null, TenantReceivable::getPriceTypeId, tenantReceivableQueryParam.getPriceTypeId())
				.eq(tenantReceivableQueryParam.getReceivableWaters() != null, TenantReceivable::getReceivableWaters, tenantReceivableQueryParam.getReceivableWaters())
				.eq(tenantReceivableQueryParam.getReceivableMoney() != null, TenantReceivable::getReceivableMoney, tenantReceivableQueryParam.getReceivableMoney())
				.eq(tenantReceivableQueryParam.getArrearsMoney() != null, TenantReceivable::getArrearsMoney, tenantReceivableQueryParam.getArrearsMoney())
				;

		TenantReceivable tenantReceivable = tenantReceivableService.getAggregation(queryWrapperTenantReceivable);
		
		return entity2vo(tenantReceivable);
	}

	@ApiOperation(value = "新增应收明细")
	@RequestMapping(value = "/tenant-receivables", method = RequestMethod.POST)
	public TenantReceivableVo save(@RequestBody TenantReceivable tenantReceivable) {
		if (tenantReceivable.getId() == null || tenantReceivable.getId().trim().length() <= 0) {
			tenantReceivable.setId(idService.selectId());
		}
		boolean success = tenantReceivableService.save(tenantReceivable);
		if (success) {
			TenantReceivable tenantReceivableDatabase = tenantReceivableService.getById(tenantReceivable.getId());
			return entity2vo(tenantReceivableDatabase);
		}
		log.info("save TenantReceivable fail，{}", ToStringBuilder.reflectionToString(tenantReceivable, ToStringStyle.JSON_STYLE));
		return null;
	}

	@ApiOperation(value = "更新应收明细全部信息")
	@RequestMapping(value = "/tenant-receivables/{id}", method = RequestMethod.PUT)
	public TenantReceivableVo updateById(@PathVariable("id") String id, @RequestBody TenantReceivable tenantReceivable) {
		tenantReceivable.setId(id);
		boolean success = tenantReceivableService.updateById(tenantReceivable);
		if (success) {
			TenantReceivable tenantReceivableDatabase = tenantReceivableService.getById(id);
			return entity2vo(tenantReceivableDatabase);
		}
		log.info("update TenantReceivable fail，{}", ToStringBuilder.reflectionToString(tenantReceivable, ToStringStyle.JSON_STYLE));
		return null;
	}

	@ApiOperation(value = "根据参数更新应收明细信息")
	@RequestMapping(value = "/tenant-receivables/{id}", method = RequestMethod.PATCH)
	public TenantReceivableVo updatePatchById(@PathVariable("id") String id, @RequestBody TenantReceivable tenantReceivable) {
        TenantReceivable tenantReceivableWhere = TenantReceivable.builder()//
				.id(id)//
				.build();
		UpdateWrapper<TenantReceivable> updateWrapperTenantReceivable = new UpdateWrapper<TenantReceivable>();
		updateWrapperTenantReceivable.setEntity(tenantReceivableWhere);
		updateWrapperTenantReceivable.lambda()//
				//.eq(TenantReceivable::getId, id)
				// .set(tenantReceivable.getId() != null, TenantReceivable::getId, tenantReceivable.getId())
				.set(tenantReceivable.getTenantId() != null, TenantReceivable::getTenantId, tenantReceivable.getTenantId())
				.set(tenantReceivable.getReceivableStatus() != null, TenantReceivable::getReceivableStatus, tenantReceivable.getReceivableStatus())
				.set(tenantReceivable.getReceivableType() != null, TenantReceivable::getReceivableType, tenantReceivable.getReceivableType())
				.set(tenantReceivable.getDepartmentId() != null, TenantReceivable::getDepartmentId, tenantReceivable.getDepartmentId())
				.set(tenantReceivable.getBookletId() != null, TenantReceivable::getBookletId, tenantReceivable.getBookletId())
				.set(tenantReceivable.getBookletCode() != null, TenantReceivable::getBookletCode, tenantReceivable.getBookletCode())
				.set(tenantReceivable.getCustomerId() != null, TenantReceivable::getCustomerId, tenantReceivable.getCustomerId())
				.set(tenantReceivable.getCustomerCode() != null, TenantReceivable::getCustomerCode, tenantReceivable.getCustomerCode())
				.set(tenantReceivable.getCustomerName() != null, TenantReceivable::getCustomerName, tenantReceivable.getCustomerName())
				.set(tenantReceivable.getCustomerAddress() != null, TenantReceivable::getCustomerAddress, tenantReceivable.getCustomerAddress())
				.set(tenantReceivable.getMeterId() != null, TenantReceivable::getMeterId, tenantReceivable.getMeterId())
				.set(tenantReceivable.getMeterCode() != null, TenantReceivable::getMeterCode, tenantReceivable.getMeterCode())
				.set(tenantReceivable.getMeterAddress() != null, TenantReceivable::getMeterAddress, tenantReceivable.getMeterAddress())
				.set(tenantReceivable.getReadEmployeeId() != null, TenantReceivable::getReadEmployeeId, tenantReceivable.getReadEmployeeId())
				.set(tenantReceivable.getReceivableTime() != null, TenantReceivable::getReceivableTime, tenantReceivable.getReceivableTime())
				.set(tenantReceivable.getSettleStartTime() != null, TenantReceivable::getSettleStartTime, tenantReceivable.getSettleStartTime())
				.set(tenantReceivable.getSettleStartPointer() != null, TenantReceivable::getSettleStartPointer, tenantReceivable.getSettleStartPointer())
				.set(tenantReceivable.getSettleEndTime() != null, TenantReceivable::getSettleEndTime, tenantReceivable.getSettleEndTime())
				.set(tenantReceivable.getSettleEndPointer() != null, TenantReceivable::getSettleEndPointer, tenantReceivable.getSettleEndPointer())
				.set(tenantReceivable.getSettleWaters() != null, TenantReceivable::getSettleWaters, tenantReceivable.getSettleWaters())
				.set(tenantReceivable.getPriceTypeId() != null, TenantReceivable::getPriceTypeId, tenantReceivable.getPriceTypeId())
				.set(tenantReceivable.getReceivableWaters() != null, TenantReceivable::getReceivableWaters, tenantReceivable.getReceivableWaters())
				.set(tenantReceivable.getReceivableMoney() != null, TenantReceivable::getReceivableMoney, tenantReceivable.getReceivableMoney())
				.set(tenantReceivable.getArrearsMoney() != null, TenantReceivable::getArrearsMoney, tenantReceivable.getArrearsMoney())
				;

		boolean success = tenantReceivableService.update(updateWrapperTenantReceivable);
		if (success) {
			TenantReceivable tenantReceivableDatabase = tenantReceivableService.getById(id);
			return entity2vo(tenantReceivableDatabase);
		}
		log.info("partial update TenantReceivable fail，{}",
				ToStringBuilder.reflectionToString(tenantReceivable, ToStringStyle.JSON_STYLE));
		return null;
	}

	@ApiOperation(value = "根据ID删除应收明细")
	@RequestMapping(value = "/tenant-receivables/{id}", method = RequestMethod.DELETE)
	public CommonResult<Object> removeById(@PathVariable("id") String id) {
		boolean success = tenantReceivableService.removeById(id);
		return success ? CommonResult.success(success) : CommonResult.failed();
	}

	private TenantReceivableVo entity2vo(TenantReceivable tenantReceivable) {
		if (tenantReceivable == null) {
			return null;
		}

		String jsonString = JSON.toJSONString(tenantReceivable);
		TenantReceivableVo tenantReceivableVo = JSON.parseObject(jsonString, TenantReceivableVo.class);
		if (StringUtils.isEmpty(tenantReceivableVo.getTenantName())) {
			TenantInfo tenantInfo = tenantInfoService.getById(tenantReceivable.getTenantId());
			if (tenantInfo != null) {
				tenantReceivableVo.setTenantName(tenantInfo.getTenantName());
			}
		}
		return tenantReceivableVo;
	}

}
