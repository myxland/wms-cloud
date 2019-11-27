package com.zlsrj.wms.tenant.rest;

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
import com.zlsrj.wms.api.dto.TenantInvoiceQueryParam;
import com.zlsrj.wms.api.entity.TenantInfo;
import com.zlsrj.wms.api.entity.TenantInvoice;
import com.zlsrj.wms.api.vo.TenantInvoiceVo;
import com.zlsrj.wms.common.api.CommonResult;
import com.zlsrj.wms.tenant.service.IIdService;
import com.zlsrj.wms.tenant.service.ITenantInfoService;
import com.zlsrj.wms.tenant.service.ITenantInvoiceService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

@Api(value = "租户发票配置", tags = { "租户发票配置操作接口" })
@RestController
@Slf4j
public class TenantInvoiceRestController {

	@Autowired
	private ITenantInvoiceService tenantInvoiceService;
	@Autowired
	private ITenantInfoService tenantInfoService;
	@Autowired
	private IIdService idService;

	@ApiOperation(value = "根据ID查询租户发票配置")
	@RequestMapping(value = "/tenant-invoices/{id}", method = RequestMethod.GET)
	public TenantInvoiceVo getById(@PathVariable("id") Long id) {
		TenantInvoice tenantInvoice = tenantInvoiceService.getById(id);

		return entity2vo(tenantInvoice);
	}

	@ApiOperation(value = "根据ID查询租户发票配置")
	@RequestMapping(value = "/tenant-invoices/tenant-id/{tenant-id}", method = RequestMethod.GET)
	public TenantInvoiceVo getByTenantId(@PathVariable("tenant-id") Long tenantId) {
		QueryWrapper<TenantInvoice> queryWrapperTenantInvoice = new QueryWrapper<TenantInvoice>();
		queryWrapperTenantInvoice.lambda().eq(TenantInvoice::getTenantId, tenantId);
		TenantInvoice tenantInvoice = tenantInvoiceService.getOne(queryWrapperTenantInvoice);

		return entity2vo(tenantInvoice);
	}

	@ApiOperation(value = "根据参数查询租户发票配置列表")
	@RequestMapping(value = "/tenant-invoices", method = RequestMethod.GET)
	public Page<TenantInvoiceVo> page(@RequestBody TenantInvoiceQueryParam tenantInvoiceQueryParam,
			@RequestParam(value = "page", defaultValue = "1") int page, //
			@RequestParam(value = "rows", defaultValue = "10") int rows, //
			@RequestParam(value = "sort") String sort, // 排序列字段名
			@RequestParam(value = "order") String order // 可以是 'asc' 或者 'desc'，默认值是 'asc'
	) {
		IPage<TenantInvoice> pageTenantInvoice = new Page<TenantInvoice>(page, rows);
		QueryWrapper<TenantInvoice> queryWrapperTenantInvoice = new QueryWrapper<TenantInvoice>();
		queryWrapperTenantInvoice.orderBy(StringUtils.isNotEmpty(sort), "desc".equals(order), sort);
		queryWrapperTenantInvoice.lambda()
				.eq(tenantInvoiceQueryParam.getId() != null, TenantInvoice::getId, tenantInvoiceQueryParam.getId())
				.eq(tenantInvoiceQueryParam.getTenantId() != null, TenantInvoice::getTenantId, tenantInvoiceQueryParam.getTenantId())
				.eq(tenantInvoiceQueryParam.getCreditNumber() != null, TenantInvoice::getCreditNumber, tenantInvoiceQueryParam.getCreditNumber())
				.eq(tenantInvoiceQueryParam.getInvoiceAddress() != null, TenantInvoice::getInvoiceAddress, tenantInvoiceQueryParam.getInvoiceAddress())
				.eq(tenantInvoiceQueryParam.getBankNo() != null, TenantInvoice::getBankNo, tenantInvoiceQueryParam.getBankNo())
				.eq(tenantInvoiceQueryParam.getBankName() != null, TenantInvoice::getBankName, tenantInvoiceQueryParam.getBankName())
				.eq(tenantInvoiceQueryParam.getAccountNo() != null, TenantInvoice::getAccountNo, tenantInvoiceQueryParam.getAccountNo())
				;

		IPage<TenantInvoice> tenantInvoicePage = tenantInvoiceService.page(pageTenantInvoice, queryWrapperTenantInvoice);

		Page<TenantInvoiceVo> tenantInvoiceVoPage = new Page<TenantInvoiceVo>(page, rows);
		tenantInvoiceVoPage.setCurrent(tenantInvoicePage.getCurrent());
		tenantInvoiceVoPage.setPages(tenantInvoicePage.getPages());
		tenantInvoiceVoPage.setSize(tenantInvoicePage.getSize());
		tenantInvoiceVoPage.setTotal(tenantInvoicePage.getTotal());
		tenantInvoiceVoPage.setRecords(tenantInvoicePage.getRecords().stream()//
				.map(e -> entity2vo(e))//
				.collect(Collectors.toList()));

		return tenantInvoiceVoPage;
	}

	@ApiOperation(value = "新增租户发票配置")
	@RequestMapping(value = "/tenant-invoices", method = RequestMethod.POST)
	public TenantInvoiceVo save(@RequestBody TenantInvoice tenantInvoice) {
		if (tenantInvoice.getId() == null || tenantInvoice.getId().compareTo(0L) <= 0) {
			tenantInvoice.setId(idService.selectId());
		}
		boolean success = tenantInvoiceService.save(tenantInvoice);
		if (success) {
			TenantInvoice tenantInvoiceDatabase = tenantInvoiceService.getById(tenantInvoice.getId());
			return entity2vo(tenantInvoiceDatabase);
		}
		log.info("save TenantInvoice fail，{}", ToStringBuilder.reflectionToString(tenantInvoice, ToStringStyle.JSON_STYLE));
		return null;
	}

	@ApiOperation(value = "更新租户发票配置全部信息")
	@RequestMapping(value = "/tenant-invoices/{id}", method = RequestMethod.PUT)
	public TenantInvoiceVo updateById(@PathVariable("id") Long id, @RequestBody TenantInvoice tenantInvoice) {
		tenantInvoice.setId(id);
		boolean success = tenantInvoiceService.updateById(tenantInvoice);
		if (success) {
			TenantInvoice tenantInvoiceDatabase = tenantInvoiceService.getById(id);
			return entity2vo(tenantInvoiceDatabase);
		}
		log.info("update TenantInvoice fail，{}", ToStringBuilder.reflectionToString(tenantInvoice, ToStringStyle.JSON_STYLE));
		return null;
	}

	@ApiOperation(value = "根据参数更新租户发票配置信息")
	@RequestMapping(value = "/tenant-invoices/{id}", method = RequestMethod.PATCH)
	public TenantInvoiceVo updatePatchById(@PathVariable("id") Long id, @RequestBody TenantInvoice tenantInvoice) {
        TenantInvoice tenantInvoiceWhere = TenantInvoice.builder()//
				.id(id)//
				.build();
		UpdateWrapper<TenantInvoice> updateWrapperTenantInvoice = new UpdateWrapper<TenantInvoice>();
		updateWrapperTenantInvoice.setEntity(tenantInvoiceWhere);
		updateWrapperTenantInvoice.lambda()//
				//.eq(TenantInvoice::getId, id)
				// .set(tenantInvoice.getId() != null, TenantInvoice::getId, tenantInvoice.getId())
				.set(tenantInvoice.getTenantId() != null, TenantInvoice::getTenantId, tenantInvoice.getTenantId())
				.set(tenantInvoice.getCreditNumber() != null, TenantInvoice::getCreditNumber, tenantInvoice.getCreditNumber())
				.set(tenantInvoice.getInvoiceAddress() != null, TenantInvoice::getInvoiceAddress, tenantInvoice.getInvoiceAddress())
				.set(tenantInvoice.getBankNo() != null, TenantInvoice::getBankNo, tenantInvoice.getBankNo())
				.set(tenantInvoice.getBankName() != null, TenantInvoice::getBankName, tenantInvoice.getBankName())
				.set(tenantInvoice.getAccountNo() != null, TenantInvoice::getAccountNo, tenantInvoice.getAccountNo())
				;

		boolean success = tenantInvoiceService.update(updateWrapperTenantInvoice);
		if (success) {
			TenantInvoice tenantInvoiceDatabase = tenantInvoiceService.getById(id);
			return entity2vo(tenantInvoiceDatabase);
		}
		log.info("partial update TenantInvoice fail，{}",
				ToStringBuilder.reflectionToString(tenantInvoice, ToStringStyle.JSON_STYLE));
		return null;
	}

	@ApiOperation(value = "根据ID删除租户发票配置")
	@RequestMapping(value = "/tenant-invoices/{id}", method = RequestMethod.DELETE)
	public CommonResult<Object> removeById(@PathVariable("id") Long id) {
		boolean success = tenantInvoiceService.removeById(id);
		return success ? CommonResult.success(success) : CommonResult.failed();
	}

	private TenantInvoiceVo entity2vo(TenantInvoice tenantInvoice) {
		if (tenantInvoice == null) {
			return null;
		}

		String jsonString = JSON.toJSONString(tenantInvoice);
		TenantInvoiceVo tenantInvoiceVo = JSON.parseObject(jsonString, TenantInvoiceVo.class);
		if (StringUtils.isEmpty(tenantInvoiceVo.getTenantName())) {
			TenantInfo tenantInfo = tenantInfoService.getById(tenantInvoice.getTenantId());
			if (tenantInfo != null) {
				tenantInvoiceVo.setTenantName(tenantInfo.getTenantName());
			}
		}
		return tenantInvoiceVo;
	}

}
