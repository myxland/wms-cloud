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
import com.zlsrj.wms.api.dto.TenantCustomerQueryParam;
import com.zlsrj.wms.api.entity.TenantInfo;
import com.zlsrj.wms.api.entity.TenantCustomer;
import com.zlsrj.wms.api.vo.TenantCustomerVo;
import com.zlsrj.wms.common.api.CommonResult;
import com.zlsrj.wms.saas.service.IIdService;
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
	@Autowired
	private IIdService idService;

	@ApiOperation(value = "根据ID查询用户信息")
	@RequestMapping(value = "/tenant-customers/{id}", method = RequestMethod.GET)
	public TenantCustomerVo getById(@PathVariable("id") Long id) {
		TenantCustomer tenantCustomer = tenantCustomerService.getById(id);

		return entity2vo(tenantCustomer);
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
		queryWrapperTenantCustomer.orderBy(StringUtils.isNotEmpty(sort), "asc".equals(order), sort);
		queryWrapperTenantCustomer.lambda()
				.eq(tenantCustomerQueryParam.getId() != null, TenantCustomer::getId, tenantCustomerQueryParam.getId())
				.eq(tenantCustomerQueryParam.getTenantId() != null, TenantCustomer::getTenantId, tenantCustomerQueryParam.getTenantId())
				.eq(tenantCustomerQueryParam.getCustomerCode() != null, TenantCustomer::getCustomerCode, tenantCustomerQueryParam.getCustomerCode())
				.eq(tenantCustomerQueryParam.getCustomerName() != null, TenantCustomer::getCustomerName, tenantCustomerQueryParam.getCustomerName())
				.eq(tenantCustomerQueryParam.getCustomerAddress() != null, TenantCustomer::getCustomerAddress, tenantCustomerQueryParam.getCustomerAddress())
				.eq(tenantCustomerQueryParam.getCustomerTypeId() != null, TenantCustomer::getCustomerTypeId, tenantCustomerQueryParam.getCustomerTypeId())
				.eq(tenantCustomerQueryParam.getCustomerRegisterTime() != null, TenantCustomer::getCustomerRegisterTime, tenantCustomerQueryParam.getCustomerRegisterTime())
				.ge(tenantCustomerQueryParam.getCustomerRegisterTimeStart() != null, TenantCustomer::getCustomerRegisterTime,tenantCustomerQueryParam.getCustomerRegisterTimeStart() == null ? null: DateUtil.beginOfDay(tenantCustomerQueryParam.getCustomerRegisterTimeStart()))
				.le(tenantCustomerQueryParam.getCustomerRegisterTimeEnd() != null, TenantCustomer::getCustomerRegisterTime,tenantCustomerQueryParam.getCustomerRegisterTimeEnd() == null ? null: DateUtil.endOfDay(tenantCustomerQueryParam.getCustomerRegisterTimeEnd()))
				.eq(tenantCustomerQueryParam.getCustomerStatus() != null, TenantCustomer::getCustomerStatus, tenantCustomerQueryParam.getCustomerStatus())
				.eq(tenantCustomerQueryParam.getCustomerPaymentMethod() != null, TenantCustomer::getCustomerPaymentMethod, tenantCustomerQueryParam.getCustomerPaymentMethod())
				.eq(tenantCustomerQueryParam.getInvoiceType() != null, TenantCustomer::getInvoiceType, tenantCustomerQueryParam.getInvoiceType())
				.eq(tenantCustomerQueryParam.getInvoiceName() != null, TenantCustomer::getInvoiceName, tenantCustomerQueryParam.getInvoiceName())
				.eq(tenantCustomerQueryParam.getInvoiceTaxNo() != null, TenantCustomer::getInvoiceTaxNo, tenantCustomerQueryParam.getInvoiceTaxNo())
				.eq(tenantCustomerQueryParam.getInvoiceAddress() != null, TenantCustomer::getInvoiceAddress, tenantCustomerQueryParam.getInvoiceAddress())
				.eq(tenantCustomerQueryParam.getInvoiceTel() != null, TenantCustomer::getInvoiceTel, tenantCustomerQueryParam.getInvoiceTel())
				.eq(tenantCustomerQueryParam.getInvoiceBankCode() != null, TenantCustomer::getInvoiceBankCode, tenantCustomerQueryParam.getInvoiceBankCode())
				.eq(tenantCustomerQueryParam.getInvoiceBankName() != null, TenantCustomer::getInvoiceBankName, tenantCustomerQueryParam.getInvoiceBankName())
				.eq(tenantCustomerQueryParam.getInvoiceBankAccountNo() != null, TenantCustomer::getInvoiceBankAccountNo, tenantCustomerQueryParam.getInvoiceBankAccountNo())
				.eq(tenantCustomerQueryParam.getCustomerBalanceMoney() != null, TenantCustomer::getCustomerBalanceMoney, tenantCustomerQueryParam.getCustomerBalanceMoney())
				.eq(tenantCustomerQueryParam.getCustomerArrearsMoney() != null, TenantCustomer::getCustomerArrearsMoney, tenantCustomerQueryParam.getCustomerArrearsMoney())
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

	@ApiOperation(value = "新增用户信息")
	@RequestMapping(value = "/tenant-customers", method = RequestMethod.POST)
	public TenantCustomerVo save(@RequestBody TenantCustomer tenantCustomer) {
		if (tenantCustomer.getId() == null || tenantCustomer.getId().compareTo(0L) <= 0) {
			tenantCustomer.setId(idService.selectId());
		}
		boolean success = tenantCustomerService.save(tenantCustomer);
		if (success) {
			TenantCustomer tenantCustomerDatabase = tenantCustomerService.getById(tenantCustomer.getId());
			return entity2vo(tenantCustomerDatabase);
		}
		log.info("save TenantCustomer fail，{}", ToStringBuilder.reflectionToString(tenantCustomer, ToStringStyle.JSON_STYLE));
		return null;
	}

	@ApiOperation(value = "更新用户信息全部信息")
	@RequestMapping(value = "/tenant-customers/{id}", method = RequestMethod.PUT)
	public TenantCustomerVo updateById(@PathVariable("id") Long id, @RequestBody TenantCustomer tenantCustomer) {
		tenantCustomer.setId(id);
		boolean success = tenantCustomerService.updateById(tenantCustomer);
		if (success) {
			TenantCustomer tenantCustomerDatabase = tenantCustomerService.getById(id);
			return entity2vo(tenantCustomerDatabase);
		}
		log.info("update TenantCustomer fail，{}", ToStringBuilder.reflectionToString(tenantCustomer, ToStringStyle.JSON_STYLE));
		return null;
	}

	@ApiOperation(value = "根据参数更新用户信息信息")
	@RequestMapping(value = "/tenant-customers/{id}", method = RequestMethod.PATCH)
	public TenantCustomerVo updatePatchById(@PathVariable("id") Long id, @RequestBody TenantCustomer tenantCustomer) {
        TenantCustomer tenantCustomerWhere = TenantCustomer.builder()//
				.id(id)//
				.build();
		UpdateWrapper<TenantCustomer> updateWrapperTenantCustomer = new UpdateWrapper<TenantCustomer>();
		updateWrapperTenantCustomer.setEntity(tenantCustomerWhere);
		updateWrapperTenantCustomer.lambda()//
				//.eq(TenantCustomer::getId, id)
				// .set(tenantCustomer.getId() != null, TenantCustomer::getId, tenantCustomer.getId())
				.set(tenantCustomer.getTenantId() != null, TenantCustomer::getTenantId, tenantCustomer.getTenantId())
				.set(tenantCustomer.getCustomerCode() != null, TenantCustomer::getCustomerCode, tenantCustomer.getCustomerCode())
				.set(tenantCustomer.getCustomerName() != null, TenantCustomer::getCustomerName, tenantCustomer.getCustomerName())
				.set(tenantCustomer.getCustomerAddress() != null, TenantCustomer::getCustomerAddress, tenantCustomer.getCustomerAddress())
				.set(tenantCustomer.getCustomerTypeId() != null, TenantCustomer::getCustomerTypeId, tenantCustomer.getCustomerTypeId())
				.set(tenantCustomer.getCustomerRegisterTime() != null, TenantCustomer::getCustomerRegisterTime, tenantCustomer.getCustomerRegisterTime())
				.set(tenantCustomer.getCustomerStatus() != null, TenantCustomer::getCustomerStatus, tenantCustomer.getCustomerStatus())
				.set(tenantCustomer.getCustomerPaymentMethod() != null, TenantCustomer::getCustomerPaymentMethod, tenantCustomer.getCustomerPaymentMethod())
				.set(tenantCustomer.getInvoiceType() != null, TenantCustomer::getInvoiceType, tenantCustomer.getInvoiceType())
				.set(tenantCustomer.getInvoiceName() != null, TenantCustomer::getInvoiceName, tenantCustomer.getInvoiceName())
				.set(tenantCustomer.getInvoiceTaxNo() != null, TenantCustomer::getInvoiceTaxNo, tenantCustomer.getInvoiceTaxNo())
				.set(tenantCustomer.getInvoiceAddress() != null, TenantCustomer::getInvoiceAddress, tenantCustomer.getInvoiceAddress())
				.set(tenantCustomer.getInvoiceTel() != null, TenantCustomer::getInvoiceTel, tenantCustomer.getInvoiceTel())
				.set(tenantCustomer.getInvoiceBankCode() != null, TenantCustomer::getInvoiceBankCode, tenantCustomer.getInvoiceBankCode())
				.set(tenantCustomer.getInvoiceBankName() != null, TenantCustomer::getInvoiceBankName, tenantCustomer.getInvoiceBankName())
				.set(tenantCustomer.getInvoiceBankAccountNo() != null, TenantCustomer::getInvoiceBankAccountNo, tenantCustomer.getInvoiceBankAccountNo())
				.set(tenantCustomer.getCustomerBalanceMoney() != null, TenantCustomer::getCustomerBalanceMoney, tenantCustomer.getCustomerBalanceMoney())
				.set(tenantCustomer.getCustomerArrearsMoney() != null, TenantCustomer::getCustomerArrearsMoney, tenantCustomer.getCustomerArrearsMoney())
				;

		boolean success = tenantCustomerService.update(updateWrapperTenantCustomer);
		if (success) {
			TenantCustomer tenantCustomerDatabase = tenantCustomerService.getById(id);
			return entity2vo(tenantCustomerDatabase);
		}
		log.info("partial update TenantCustomer fail，{}",
				ToStringBuilder.reflectionToString(tenantCustomer, ToStringStyle.JSON_STYLE));
		return null;
	}

	@ApiOperation(value = "根据ID删除用户信息")
	@RequestMapping(value = "/tenant-customers/{id}", method = RequestMethod.DELETE)
	public CommonResult<Object> removeById(@PathVariable("id") Long id) {
		boolean success = tenantCustomerService.removeById(id);
		return success ? CommonResult.success(success) : CommonResult.failed();
	}

	private TenantCustomerVo entity2vo(TenantCustomer tenantCustomer) {
		if (tenantCustomer == null) {
			return null;
		}

		String jsonString = JSON.toJSONString(tenantCustomer);
		TenantCustomerVo tenantCustomerVo = JSON.parseObject(jsonString, TenantCustomerVo.class);
		if (StringUtils.isEmpty(tenantCustomerVo.getTenantName())) {
			TenantInfo tenantInfo = tenantInfoService.getById(tenantCustomer.getTenantId());
			if (tenantInfo != null) {
				tenantCustomerVo.setTenantName(tenantInfo.getTenantName());
			}
		}
		return tenantCustomerVo;
	}

}
