package com.zlsrj.wms.saas.rest;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
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
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zlsrj.wms.api.dto.TenantConsumptionBillAddParam;
import com.zlsrj.wms.api.dto.TenantConsumptionBillQueryParam;
import com.zlsrj.wms.api.dto.TenantConsumptionBillUpdateParam;
import com.zlsrj.wms.api.entity.TenantInfo;
import com.zlsrj.wms.api.entity.TenantConsumptionBill;
import com.zlsrj.wms.api.vo.TenantConsumptionBillVo;
import com.zlsrj.wms.common.api.CommonResult;
import com.zlsrj.wms.common.util.TranslateUtil;
import com.zlsrj.wms.saas.service.ITenantInfoService;
import com.zlsrj.wms.saas.service.ITenantConsumptionBillService;

import cn.hutool.core.date.DateUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

@Api(value = "租户账单", tags = { "租户账单操作接口" })
@RestController
@Slf4j
public class TenantConsumptionBillRestController {

	@Autowired
	private ITenantConsumptionBillService tenantConsumptionBillService;
	@Autowired
	private ITenantInfoService tenantInfoService;

	@ApiOperation(value = "根据ID查询租户账单")
	@RequestMapping(value = "/tenant-consumption-bills/{id}", method = RequestMethod.GET)
	public TenantConsumptionBillVo getById(@PathVariable("id") String id) {
		TenantConsumptionBill tenantConsumptionBill = tenantConsumptionBillService.getById(id);

		return entity2vo(tenantConsumptionBill);
	}

	@ApiOperation(value = "根据参数查询租户账单列表")
	@RequestMapping(value = "/tenant-consumption-bills/list", method = RequestMethod.GET)
	public List<TenantConsumptionBillVo> list(@RequestBody TenantConsumptionBillQueryParam tenantConsumptionBillQueryParam) {
		QueryWrapper<TenantConsumptionBill> queryWrapperTenantConsumptionBill = new QueryWrapper<TenantConsumptionBill>();
		queryWrapperTenantConsumptionBill.lambda()
				.eq(tenantConsumptionBillQueryParam.getId() != null, TenantConsumptionBill::getId, tenantConsumptionBillQueryParam.getId())
				.eq(tenantConsumptionBillQueryParam.getTenantId() != null, TenantConsumptionBill::getTenantId, tenantConsumptionBillQueryParam.getTenantId())
				.eq(tenantConsumptionBillQueryParam.getConsumptionBillType() != null, TenantConsumptionBill::getConsumptionBillType, tenantConsumptionBillQueryParam.getConsumptionBillType())
				.eq(tenantConsumptionBillQueryParam.getConsumptionBillTime() != null, TenantConsumptionBill::getConsumptionBillTime, tenantConsumptionBillQueryParam.getConsumptionBillTime())
				.ge(tenantConsumptionBillQueryParam.getConsumptionBillTimeStart() != null, TenantConsumptionBill::getConsumptionBillTime,tenantConsumptionBillQueryParam.getConsumptionBillTimeStart() == null ? null: DateUtil.beginOfDay(tenantConsumptionBillQueryParam.getConsumptionBillTimeStart()))
				.le(tenantConsumptionBillQueryParam.getConsumptionBillTimeEnd() != null, TenantConsumptionBill::getConsumptionBillTime,tenantConsumptionBillQueryParam.getConsumptionBillTimeEnd() == null ? null: DateUtil.endOfDay(tenantConsumptionBillQueryParam.getConsumptionBillTimeEnd()))
				.eq(StringUtils.isNotBlank(tenantConsumptionBillQueryParam.getConsumptionBillName()), TenantConsumptionBill::getConsumptionBillName, tenantConsumptionBillQueryParam.getConsumptionBillName())
				.eq(tenantConsumptionBillQueryParam.getConsumptionBillMoney() != null, TenantConsumptionBill::getConsumptionBillMoney, tenantConsumptionBillQueryParam.getConsumptionBillMoney())
				.eq(tenantConsumptionBillQueryParam.getTenantBalance() != null, TenantConsumptionBill::getTenantBalance, tenantConsumptionBillQueryParam.getTenantBalance())
				.eq(tenantConsumptionBillQueryParam.getConsumptionBillRemark() != null, TenantConsumptionBill::getConsumptionBillRemark, tenantConsumptionBillQueryParam.getConsumptionBillRemark())
				;

		List<TenantConsumptionBill> tenantConsumptionBillList = tenantConsumptionBillService.list(queryWrapperTenantConsumptionBill);

		List<TenantConsumptionBillVo> tenantConsumptionBillVoList = TranslateUtil.translateList(tenantConsumptionBillList, TenantConsumptionBillVo.class);

		return tenantConsumptionBillVoList;
	}
	
	@ApiOperation(value = "根据参数查询租户账单列表")
	@RequestMapping(value = "/tenant-consumption-bills", method = RequestMethod.GET)
	public Page<TenantConsumptionBillVo> page(@RequestBody TenantConsumptionBillQueryParam tenantConsumptionBillQueryParam,
			@RequestParam(value = "page", defaultValue = "1") int page, //
			@RequestParam(value = "rows", defaultValue = "10") int rows, //
			@RequestParam(value = "sort") String sort, // 排序列字段名
			@RequestParam(value = "order") String order // 可以是 'asc' 或者 'desc'，默认值是 'asc'
	) {
		IPage<TenantConsumptionBill> pageTenantConsumptionBill = new Page<TenantConsumptionBill>(page, rows);
		QueryWrapper<TenantConsumptionBill> queryWrapperTenantConsumptionBill = new QueryWrapper<TenantConsumptionBill>();
		queryWrapperTenantConsumptionBill.orderBy(StringUtils.isNotEmpty(sort), "asc".equals(order), sort);
		queryWrapperTenantConsumptionBill.lambda()
				.eq(tenantConsumptionBillQueryParam.getId() != null, TenantConsumptionBill::getId, tenantConsumptionBillQueryParam.getId())
				.eq(tenantConsumptionBillQueryParam.getTenantId() != null, TenantConsumptionBill::getTenantId, tenantConsumptionBillQueryParam.getTenantId())
				.eq(tenantConsumptionBillQueryParam.getConsumptionBillType() != null, TenantConsumptionBill::getConsumptionBillType, tenantConsumptionBillQueryParam.getConsumptionBillType())
				.eq(tenantConsumptionBillQueryParam.getConsumptionBillTime() != null, TenantConsumptionBill::getConsumptionBillTime, tenantConsumptionBillQueryParam.getConsumptionBillTime())
				.ge(tenantConsumptionBillQueryParam.getConsumptionBillTimeStart() != null, TenantConsumptionBill::getConsumptionBillTime,tenantConsumptionBillQueryParam.getConsumptionBillTimeStart() == null ? null: DateUtil.beginOfDay(tenantConsumptionBillQueryParam.getConsumptionBillTimeStart()))
				.le(tenantConsumptionBillQueryParam.getConsumptionBillTimeEnd() != null, TenantConsumptionBill::getConsumptionBillTime,tenantConsumptionBillQueryParam.getConsumptionBillTimeEnd() == null ? null: DateUtil.endOfDay(tenantConsumptionBillQueryParam.getConsumptionBillTimeEnd()))
				.eq(tenantConsumptionBillQueryParam.getConsumptionBillName() != null, TenantConsumptionBill::getConsumptionBillName, tenantConsumptionBillQueryParam.getConsumptionBillName())
				.eq(tenantConsumptionBillQueryParam.getConsumptionBillMoney() != null, TenantConsumptionBill::getConsumptionBillMoney, tenantConsumptionBillQueryParam.getConsumptionBillMoney())
				.eq(tenantConsumptionBillQueryParam.getTenantBalance() != null, TenantConsumptionBill::getTenantBalance, tenantConsumptionBillQueryParam.getTenantBalance())
				.eq(tenantConsumptionBillQueryParam.getConsumptionBillRemark() != null, TenantConsumptionBill::getConsumptionBillRemark, tenantConsumptionBillQueryParam.getConsumptionBillRemark())
				;

		IPage<TenantConsumptionBill> tenantConsumptionBillPage = tenantConsumptionBillService.page(pageTenantConsumptionBill, queryWrapperTenantConsumptionBill);

		Page<TenantConsumptionBillVo> tenantConsumptionBillVoPage = new Page<TenantConsumptionBillVo>(page, rows);
		tenantConsumptionBillVoPage.setCurrent(tenantConsumptionBillPage.getCurrent());
		tenantConsumptionBillVoPage.setPages(tenantConsumptionBillPage.getPages());
		tenantConsumptionBillVoPage.setSize(tenantConsumptionBillPage.getSize());
		tenantConsumptionBillVoPage.setTotal(tenantConsumptionBillPage.getTotal());
		tenantConsumptionBillVoPage.setRecords(tenantConsumptionBillPage.getRecords().stream()//
				.map(e -> entity2vo(e))//
				.collect(Collectors.toList()));

		return tenantConsumptionBillVoPage;
	}
	
	@ApiOperation(value = "根据参数查询租户账单统计")
	@RequestMapping(value = "/tenant-consumption-bills/aggregation", method = RequestMethod.GET)
	public TenantConsumptionBillVo aggregation(@RequestBody TenantConsumptionBillQueryParam tenantConsumptionBillQueryParam) {
		QueryWrapper<TenantConsumptionBill> queryWrapperTenantConsumptionBill = new QueryWrapper<TenantConsumptionBill>();
		queryWrapperTenantConsumptionBill.lambda()
				.eq(tenantConsumptionBillQueryParam.getId() != null, TenantConsumptionBill::getId, tenantConsumptionBillQueryParam.getId())
				.eq(tenantConsumptionBillQueryParam.getTenantId() != null, TenantConsumptionBill::getTenantId, tenantConsumptionBillQueryParam.getTenantId())
				.eq(tenantConsumptionBillQueryParam.getConsumptionBillType() != null, TenantConsumptionBill::getConsumptionBillType, tenantConsumptionBillQueryParam.getConsumptionBillType())
				.eq(tenantConsumptionBillQueryParam.getConsumptionBillTime() != null, TenantConsumptionBill::getConsumptionBillTime, tenantConsumptionBillQueryParam.getConsumptionBillTime())
				.ge(tenantConsumptionBillQueryParam.getConsumptionBillTimeStart() != null, TenantConsumptionBill::getConsumptionBillTime,tenantConsumptionBillQueryParam.getConsumptionBillTimeStart() == null ? null: DateUtil.beginOfDay(tenantConsumptionBillQueryParam.getConsumptionBillTimeStart()))
				.le(tenantConsumptionBillQueryParam.getConsumptionBillTimeEnd() != null, TenantConsumptionBill::getConsumptionBillTime,tenantConsumptionBillQueryParam.getConsumptionBillTimeEnd() == null ? null: DateUtil.endOfDay(tenantConsumptionBillQueryParam.getConsumptionBillTimeEnd()))
				.eq(tenantConsumptionBillQueryParam.getConsumptionBillName() != null, TenantConsumptionBill::getConsumptionBillName, tenantConsumptionBillQueryParam.getConsumptionBillName())
				.eq(tenantConsumptionBillQueryParam.getConsumptionBillMoney() != null, TenantConsumptionBill::getConsumptionBillMoney, tenantConsumptionBillQueryParam.getConsumptionBillMoney())
				.eq(tenantConsumptionBillQueryParam.getTenantBalance() != null, TenantConsumptionBill::getTenantBalance, tenantConsumptionBillQueryParam.getTenantBalance())
				.eq(tenantConsumptionBillQueryParam.getConsumptionBillRemark() != null, TenantConsumptionBill::getConsumptionBillRemark, tenantConsumptionBillQueryParam.getConsumptionBillRemark())
				;

		TenantConsumptionBill tenantConsumptionBill = tenantConsumptionBillService.getAggregation(queryWrapperTenantConsumptionBill);
		
		return entity2vo(tenantConsumptionBill);
	}

	@ApiOperation(value = "新增租户账单")
	@RequestMapping(value = "/tenant-consumption-bills", method = RequestMethod.POST)
	public String save(@RequestBody TenantConsumptionBillAddParam tenantConsumptionBillAddParam) {
		return tenantConsumptionBillService.save(tenantConsumptionBillAddParam);
	}

	@ApiOperation(value = "更新租户账单全部信息")
	@RequestMapping(value = "/tenant-consumption-bills/{id}", method = RequestMethod.PUT)
	public boolean updateById(@PathVariable("id") String id, @RequestBody TenantConsumptionBillUpdateParam tenantConsumptionBillUpdateParam) {
		tenantConsumptionBillUpdateParam.setId(id);
		return tenantConsumptionBillService.updateById(tenantConsumptionBillUpdateParam);
	}

	@ApiOperation(value = "根据参数更新租户账单信息")
	@RequestMapping(value = "/tenant-consumption-bills/{id}", method = RequestMethod.PATCH)
	public TenantConsumptionBillVo updatePatchById(@PathVariable("id") String id, @RequestBody TenantConsumptionBill tenantConsumptionBill) {
        TenantConsumptionBill tenantConsumptionBillWhere = TenantConsumptionBill.builder()//
				.id(id)//
				.build();
		UpdateWrapper<TenantConsumptionBill> updateWrapperTenantConsumptionBill = new UpdateWrapper<TenantConsumptionBill>();
		updateWrapperTenantConsumptionBill.setEntity(tenantConsumptionBillWhere);
		updateWrapperTenantConsumptionBill.lambda()//
				//.eq(TenantConsumptionBill::getId, id)
				// .set(tenantConsumptionBill.getId() != null, TenantConsumptionBill::getId, tenantConsumptionBill.getId())
				.set(tenantConsumptionBill.getTenantId() != null, TenantConsumptionBill::getTenantId, tenantConsumptionBill.getTenantId())
				.set(tenantConsumptionBill.getConsumptionBillType() != null, TenantConsumptionBill::getConsumptionBillType, tenantConsumptionBill.getConsumptionBillType())
				.set(tenantConsumptionBill.getConsumptionBillTime() != null, TenantConsumptionBill::getConsumptionBillTime, tenantConsumptionBill.getConsumptionBillTime())
				.set(tenantConsumptionBill.getConsumptionBillName() != null, TenantConsumptionBill::getConsumptionBillName, tenantConsumptionBill.getConsumptionBillName())
				.set(tenantConsumptionBill.getConsumptionBillMoney() != null, TenantConsumptionBill::getConsumptionBillMoney, tenantConsumptionBill.getConsumptionBillMoney())
				.set(tenantConsumptionBill.getTenantBalance() != null, TenantConsumptionBill::getTenantBalance, tenantConsumptionBill.getTenantBalance())
				.set(tenantConsumptionBill.getConsumptionBillRemark() != null, TenantConsumptionBill::getConsumptionBillRemark, tenantConsumptionBill.getConsumptionBillRemark())
				;

		boolean success = tenantConsumptionBillService.update(updateWrapperTenantConsumptionBill);
		if (success) {
			TenantConsumptionBill tenantConsumptionBillDatabase = tenantConsumptionBillService.getById(id);
			return entity2vo(tenantConsumptionBillDatabase);
		}
		log.info("partial update TenantConsumptionBill fail，{}",
				ToStringBuilder.reflectionToString(tenantConsumptionBill, ToStringStyle.JSON_STYLE));
		return null;
	}

	@ApiOperation(value = "根据ID删除租户账单")
	@RequestMapping(value = "/tenant-consumption-bills/{id}", method = RequestMethod.DELETE)
	public CommonResult<Object> removeById(@PathVariable("id") String id) {
		boolean success = tenantConsumptionBillService.removeById(id);
		return success ? CommonResult.success(success) : CommonResult.failed();
	}

	private TenantConsumptionBillVo entity2vo(TenantConsumptionBill tenantConsumptionBill) {
		if (tenantConsumptionBill == null) {
			return null;
		}

		String jsonString = JSON.toJSONString(tenantConsumptionBill);
		TenantConsumptionBillVo tenantConsumptionBillVo = JSON.parseObject(jsonString, TenantConsumptionBillVo.class);
		if (StringUtils.isEmpty(tenantConsumptionBillVo.getTenantName())) {
			TenantInfo tenantInfo = tenantInfoService.getDictionaryById(tenantConsumptionBill.getTenantId());
			if (tenantInfo != null) {
				tenantConsumptionBillVo.setTenantName(tenantInfo.getTenantName());
			}
		}
		return tenantConsumptionBillVo;
	}

}
