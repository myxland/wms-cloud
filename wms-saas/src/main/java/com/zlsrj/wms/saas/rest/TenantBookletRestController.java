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
import com.zlsrj.wms.api.dto.TenantBookletQueryParam;
import com.zlsrj.wms.api.entity.TenantInfo;
import com.zlsrj.wms.api.entity.TenantBooklet;
import com.zlsrj.wms.api.vo.TenantBookletVo;
import com.zlsrj.wms.common.api.CommonResult;
import com.zlsrj.wms.saas.service.IIdService;
import com.zlsrj.wms.saas.service.ITenantInfoService;
import com.zlsrj.wms.saas.service.ITenantBookletService;

import cn.hutool.core.date.DateUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

@Api(value = "租户表册", tags = { "租户表册操作接口" })
@RestController
@Slf4j
public class TenantBookletRestController {

	@Autowired
	private ITenantBookletService tenantBookletService;
	@Autowired
	private ITenantInfoService tenantInfoService;
	@Autowired
	private IIdService idService;

	@ApiOperation(value = "根据ID查询租户表册")
	@RequestMapping(value = "/tenant-booklets/{id}", method = RequestMethod.GET)
	public TenantBookletVo getById(@PathVariable("id") Long id) {
		TenantBooklet tenantBooklet = tenantBookletService.getById(id);

		return entity2vo(tenantBooklet);
	}

	@ApiOperation(value = "根据参数查询租户表册列表")
	@RequestMapping(value = "/tenant-booklets", method = RequestMethod.GET)
	public Page<TenantBookletVo> page(@RequestBody TenantBookletQueryParam tenantBookletQueryParam,
			@RequestParam(value = "page", defaultValue = "1") int page, //
			@RequestParam(value = "rows", defaultValue = "10") int rows, //
			@RequestParam(value = "sort") String sort, // 排序列字段名
			@RequestParam(value = "order") String order // 可以是 'asc' 或者 'desc'，默认值是 'asc'
	) {
		IPage<TenantBooklet> pageTenantBooklet = new Page<TenantBooklet>(page, rows);
		QueryWrapper<TenantBooklet> queryWrapperTenantBooklet = new QueryWrapper<TenantBooklet>();
		queryWrapperTenantBooklet.orderBy(StringUtils.isNotEmpty(sort), "asc".equals(order), sort);
		queryWrapperTenantBooklet.lambda()
				.eq(tenantBookletQueryParam.getId() != null, TenantBooklet::getId, tenantBookletQueryParam.getId())
				.eq(tenantBookletQueryParam.getTenantId() != null, TenantBooklet::getTenantId, tenantBookletQueryParam.getTenantId())
				.eq(tenantBookletQueryParam.getBookletDepartmentId() != null, TenantBooklet::getBookletDepartmentId, tenantBookletQueryParam.getBookletDepartmentId())
				.eq(tenantBookletQueryParam.getBookletWaterAreaId() != null, TenantBooklet::getBookletWaterAreaId, tenantBookletQueryParam.getBookletWaterAreaId())
				.eq(tenantBookletQueryParam.getBookletCode() != null, TenantBooklet::getBookletCode, tenantBookletQueryParam.getBookletCode())
				.eq(tenantBookletQueryParam.getBookletName() != null, TenantBooklet::getBookletName, tenantBookletQueryParam.getBookletName())
				.eq(tenantBookletQueryParam.getBookletReadEmployeeId() != null, TenantBooklet::getBookletReadEmployeeId, tenantBookletQueryParam.getBookletReadEmployeeId())
				.eq(tenantBookletQueryParam.getBookletChargeEmployeeId() != null, TenantBooklet::getBookletChargeEmployeeId, tenantBookletQueryParam.getBookletChargeEmployeeId())
				.eq(tenantBookletQueryParam.getBookletSettleCycleInterval() != null, TenantBooklet::getBookletSettleCycleInterval, tenantBookletQueryParam.getBookletSettleCycleInterval())
				.eq(tenantBookletQueryParam.getBookletLastSettleMonth() != null, TenantBooklet::getBookletLastSettleMonth, tenantBookletQueryParam.getBookletLastSettleMonth())
				.ge(tenantBookletQueryParam.getBookletLastSettleMonthStart() != null, TenantBooklet::getBookletLastSettleMonth,tenantBookletQueryParam.getBookletLastSettleMonthStart() == null ? null: DateUtil.beginOfDay(tenantBookletQueryParam.getBookletLastSettleMonthStart()))
				.le(tenantBookletQueryParam.getBookletLastSettleMonthEnd() != null, TenantBooklet::getBookletLastSettleMonth,tenantBookletQueryParam.getBookletLastSettleMonthEnd() == null ? null: DateUtil.endOfDay(tenantBookletQueryParam.getBookletLastSettleMonthEnd()))
				.eq(tenantBookletQueryParam.getBookletNextSettleMonth() != null, TenantBooklet::getBookletNextSettleMonth, tenantBookletQueryParam.getBookletNextSettleMonth())
				.ge(tenantBookletQueryParam.getBookletNextSettleMonthStart() != null, TenantBooklet::getBookletNextSettleMonth,tenantBookletQueryParam.getBookletNextSettleMonthStart() == null ? null: DateUtil.beginOfDay(tenantBookletQueryParam.getBookletNextSettleMonthStart()))
				.le(tenantBookletQueryParam.getBookletNextSettleMonthEnd() != null, TenantBooklet::getBookletNextSettleMonth,tenantBookletQueryParam.getBookletNextSettleMonthEnd() == null ? null: DateUtil.endOfDay(tenantBookletQueryParam.getBookletNextSettleMonthEnd()))
				.eq(tenantBookletQueryParam.getBookletStatus() != null, TenantBooklet::getBookletStatus, tenantBookletQueryParam.getBookletStatus())
				.eq(tenantBookletQueryParam.getBookletOn() != null, TenantBooklet::getBookletOn, tenantBookletQueryParam.getBookletOn())
				;

		IPage<TenantBooklet> tenantBookletPage = tenantBookletService.page(pageTenantBooklet, queryWrapperTenantBooklet);

		Page<TenantBookletVo> tenantBookletVoPage = new Page<TenantBookletVo>(page, rows);
		tenantBookletVoPage.setCurrent(tenantBookletPage.getCurrent());
		tenantBookletVoPage.setPages(tenantBookletPage.getPages());
		tenantBookletVoPage.setSize(tenantBookletPage.getSize());
		tenantBookletVoPage.setTotal(tenantBookletPage.getTotal());
		tenantBookletVoPage.setRecords(tenantBookletPage.getRecords().stream()//
				.map(e -> entity2vo(e))//
				.collect(Collectors.toList()));

		return tenantBookletVoPage;
	}

	@ApiOperation(value = "新增租户表册")
	@RequestMapping(value = "/tenant-booklets", method = RequestMethod.POST)
	public TenantBookletVo save(@RequestBody TenantBooklet tenantBooklet) {
		if (tenantBooklet.getId() == null || tenantBooklet.getId().compareTo(0L) <= 0) {
			tenantBooklet.setId(idService.selectId());
		}
		boolean success = tenantBookletService.save(tenantBooklet);
		if (success) {
			TenantBooklet tenantBookletDatabase = tenantBookletService.getById(tenantBooklet.getId());
			return entity2vo(tenantBookletDatabase);
		}
		log.info("save TenantBooklet fail，{}", ToStringBuilder.reflectionToString(tenantBooklet, ToStringStyle.JSON_STYLE));
		return null;
	}

	@ApiOperation(value = "更新租户表册全部信息")
	@RequestMapping(value = "/tenant-booklets/{id}", method = RequestMethod.PUT)
	public TenantBookletVo updateById(@PathVariable("id") Long id, @RequestBody TenantBooklet tenantBooklet) {
		tenantBooklet.setId(id);
		boolean success = tenantBookletService.updateById(tenantBooklet);
		if (success) {
			TenantBooklet tenantBookletDatabase = tenantBookletService.getById(id);
			return entity2vo(tenantBookletDatabase);
		}
		log.info("update TenantBooklet fail，{}", ToStringBuilder.reflectionToString(tenantBooklet, ToStringStyle.JSON_STYLE));
		return null;
	}

	@ApiOperation(value = "根据参数更新租户表册信息")
	@RequestMapping(value = "/tenant-booklets/{id}", method = RequestMethod.PATCH)
	public TenantBookletVo updatePatchById(@PathVariable("id") Long id, @RequestBody TenantBooklet tenantBooklet) {
        TenantBooklet tenantBookletWhere = TenantBooklet.builder()//
				.id(id)//
				.build();
		UpdateWrapper<TenantBooklet> updateWrapperTenantBooklet = new UpdateWrapper<TenantBooklet>();
		updateWrapperTenantBooklet.setEntity(tenantBookletWhere);
		updateWrapperTenantBooklet.lambda()//
				//.eq(TenantBooklet::getId, id)
				// .set(tenantBooklet.getId() != null, TenantBooklet::getId, tenantBooklet.getId())
				.set(tenantBooklet.getTenantId() != null, TenantBooklet::getTenantId, tenantBooklet.getTenantId())
				.set(tenantBooklet.getBookletDepartmentId() != null, TenantBooklet::getBookletDepartmentId, tenantBooklet.getBookletDepartmentId())
				.set(tenantBooklet.getBookletWaterAreaId() != null, TenantBooklet::getBookletWaterAreaId, tenantBooklet.getBookletWaterAreaId())
				.set(tenantBooklet.getBookletCode() != null, TenantBooklet::getBookletCode, tenantBooklet.getBookletCode())
				.set(tenantBooklet.getBookletName() != null, TenantBooklet::getBookletName, tenantBooklet.getBookletName())
				.set(tenantBooklet.getBookletReadEmployeeId() != null, TenantBooklet::getBookletReadEmployeeId, tenantBooklet.getBookletReadEmployeeId())
				.set(tenantBooklet.getBookletChargeEmployeeId() != null, TenantBooklet::getBookletChargeEmployeeId, tenantBooklet.getBookletChargeEmployeeId())
				.set(tenantBooklet.getBookletSettleCycleInterval() != null, TenantBooklet::getBookletSettleCycleInterval, tenantBooklet.getBookletSettleCycleInterval())
				.set(tenantBooklet.getBookletLastSettleMonth() != null, TenantBooklet::getBookletLastSettleMonth, tenantBooklet.getBookletLastSettleMonth())
				.set(tenantBooklet.getBookletNextSettleMonth() != null, TenantBooklet::getBookletNextSettleMonth, tenantBooklet.getBookletNextSettleMonth())
				.set(tenantBooklet.getBookletStatus() != null, TenantBooklet::getBookletStatus, tenantBooklet.getBookletStatus())
				.set(tenantBooklet.getBookletOn() != null, TenantBooklet::getBookletOn, tenantBooklet.getBookletOn())
				;

		boolean success = tenantBookletService.update(updateWrapperTenantBooklet);
		if (success) {
			TenantBooklet tenantBookletDatabase = tenantBookletService.getById(id);
			return entity2vo(tenantBookletDatabase);
		}
		log.info("partial update TenantBooklet fail，{}",
				ToStringBuilder.reflectionToString(tenantBooklet, ToStringStyle.JSON_STYLE));
		return null;
	}

	@ApiOperation(value = "根据ID删除租户表册")
	@RequestMapping(value = "/tenant-booklets/{id}", method = RequestMethod.DELETE)
	public CommonResult<Object> removeById(@PathVariable("id") Long id) {
		boolean success = tenantBookletService.removeById(id);
		return success ? CommonResult.success(success) : CommonResult.failed();
	}

	private TenantBookletVo entity2vo(TenantBooklet tenantBooklet) {
		if (tenantBooklet == null) {
			return null;
		}

		String jsonString = JSON.toJSONString(tenantBooklet);
		TenantBookletVo tenantBookletVo = JSON.parseObject(jsonString, TenantBookletVo.class);
		if (StringUtils.isEmpty(tenantBookletVo.getTenantName())) {
			TenantInfo tenantInfo = tenantInfoService.getById(tenantBooklet.getTenantId());
			if (tenantInfo != null) {
				tenantBookletVo.setTenantName(tenantInfo.getTenantName());
			}
		}
		return tenantBookletVo;
	}

}
