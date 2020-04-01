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
import com.zlsrj.wms.api.dto.TenantCustomerInvoiceAddParam;
import com.zlsrj.wms.api.dto.TenantCustomerInvoiceQueryParam;
import com.zlsrj.wms.api.dto.TenantCustomerInvoiceUpdateParam;
import com.zlsrj.wms.api.entity.TenantInfo;
import com.zlsrj.wms.api.entity.TenantCustomerInvoice;
import com.zlsrj.wms.api.vo.TenantCustomerInvoiceVo;
import com.zlsrj.wms.common.api.CommonResult;
import com.zlsrj.wms.common.util.TranslateUtil;
import com.zlsrj.wms.saas.service.ITenantInfoService;
import com.zlsrj.wms.saas.service.ITenantCustomerInvoiceService;

import cn.hutool.core.date.DateUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

@Api(value = "用户开票信息", tags = { "用户开票信息操作接口" })
@RestController
@Slf4j
public class TenantCustomerInvoiceRestController {

	@Autowired
	private ITenantCustomerInvoiceService tenantCustomerInvoiceService;
	@Autowired
	private ITenantInfoService tenantInfoService;

	@ApiOperation(value = "根据ID查询用户开票信息")
	@RequestMapping(value = "/tenant-customer-invoices/{id}", method = RequestMethod.GET)
	public TenantCustomerInvoiceVo getById(@PathVariable("id") String id) {
		TenantCustomerInvoice tenantCustomerInvoice = tenantCustomerInvoiceService.getById(id);

		return entity2vo(tenantCustomerInvoice);
	}

	@ApiOperation(value = "根据参数查询用户开票信息列表")
	@RequestMapping(value = "/tenant-customer-invoices/list", method = RequestMethod.GET)
	public List<TenantCustomerInvoiceVo> list(@RequestBody TenantCustomerInvoiceQueryParam tenantCustomerInvoiceQueryParam) {
		QueryWrapper<TenantCustomerInvoice> queryWrapperTenantCustomerInvoice = new QueryWrapper<TenantCustomerInvoice>();
		queryWrapperTenantCustomerInvoice.lambda()
				.eq(StringUtils.isNotEmpty(tenantCustomerInvoiceQueryParam.getId()), TenantCustomerInvoice::getId, tenantCustomerInvoiceQueryParam.getId())
				.eq(StringUtils.isNotEmpty(tenantCustomerInvoiceQueryParam.getTenantId()), TenantCustomerInvoice::getTenantId, tenantCustomerInvoiceQueryParam.getTenantId())
				.eq(StringUtils.isNotEmpty(tenantCustomerInvoiceQueryParam.getCustomerId()), TenantCustomerInvoice::getCustomerId, tenantCustomerInvoiceQueryParam.getCustomerId())
				.eq(StringUtils.isNotEmpty(tenantCustomerInvoiceQueryParam.getCustomerCode()), TenantCustomerInvoice::getCustomerCode, tenantCustomerInvoiceQueryParam.getCustomerCode())
				.eq(tenantCustomerInvoiceQueryParam.getInvoiceType() != null, TenantCustomerInvoice::getInvoiceType, tenantCustomerInvoiceQueryParam.getInvoiceType())
				.eq(StringUtils.isNotEmpty(tenantCustomerInvoiceQueryParam.getInvoiceName()), TenantCustomerInvoice::getInvoiceName, tenantCustomerInvoiceQueryParam.getInvoiceName())
				.eq(StringUtils.isNotEmpty(tenantCustomerInvoiceQueryParam.getInvoiceTaxNo()), TenantCustomerInvoice::getInvoiceTaxNo, tenantCustomerInvoiceQueryParam.getInvoiceTaxNo())
				.eq(StringUtils.isNotEmpty(tenantCustomerInvoiceQueryParam.getInvoiceAddress()), TenantCustomerInvoice::getInvoiceAddress, tenantCustomerInvoiceQueryParam.getInvoiceAddress())
				.eq(StringUtils.isNotEmpty(tenantCustomerInvoiceQueryParam.getInvoiceTel()), TenantCustomerInvoice::getInvoiceTel, tenantCustomerInvoiceQueryParam.getInvoiceTel())
				.eq(StringUtils.isNotEmpty(tenantCustomerInvoiceQueryParam.getInvoiceBank()), TenantCustomerInvoice::getInvoiceBank, tenantCustomerInvoiceQueryParam.getInvoiceBank())
				.eq(StringUtils.isNotEmpty(tenantCustomerInvoiceQueryParam.getInvoiceAccountNo()), TenantCustomerInvoice::getInvoiceAccountNo, tenantCustomerInvoiceQueryParam.getInvoiceAccountNo())
				.eq(StringUtils.isNotEmpty(tenantCustomerInvoiceQueryParam.getInvoiceMemo()), TenantCustomerInvoice::getInvoiceMemo, tenantCustomerInvoiceQueryParam.getInvoiceMemo())
				.eq(tenantCustomerInvoiceQueryParam.getAddTime() != null, TenantCustomerInvoice::getAddTime, tenantCustomerInvoiceQueryParam.getAddTime())
				.ge(tenantCustomerInvoiceQueryParam.getAddTimeStart() != null, TenantCustomerInvoice::getAddTime,tenantCustomerInvoiceQueryParam.getAddTimeStart() == null ? null: DateUtil.beginOfDay(tenantCustomerInvoiceQueryParam.getAddTimeStart()))
				.le(tenantCustomerInvoiceQueryParam.getAddTimeEnd() != null, TenantCustomerInvoice::getAddTime,tenantCustomerInvoiceQueryParam.getAddTimeEnd() == null ? null: DateUtil.endOfDay(tenantCustomerInvoiceQueryParam.getAddTimeEnd()))
				.eq(tenantCustomerInvoiceQueryParam.getUpdateTime() != null, TenantCustomerInvoice::getUpdateTime, tenantCustomerInvoiceQueryParam.getUpdateTime())
				.ge(tenantCustomerInvoiceQueryParam.getUpdateTimeStart() != null, TenantCustomerInvoice::getUpdateTime,tenantCustomerInvoiceQueryParam.getUpdateTimeStart() == null ? null: DateUtil.beginOfDay(tenantCustomerInvoiceQueryParam.getUpdateTimeStart()))
				.le(tenantCustomerInvoiceQueryParam.getUpdateTimeEnd() != null, TenantCustomerInvoice::getUpdateTime,tenantCustomerInvoiceQueryParam.getUpdateTimeEnd() == null ? null: DateUtil.endOfDay(tenantCustomerInvoiceQueryParam.getUpdateTimeEnd()))
				;

		String[] queryCols = tenantCustomerInvoiceQueryParam.getQueryCol();
		String[] queryTypes = tenantCustomerInvoiceQueryParam.getQueryType();
		String[] queryValues = tenantCustomerInvoiceQueryParam.getQueryValue();
		if (queryCols != null && queryCols.length > 0) {
			for (int i = 0; i < queryCols.length; i++) {
				queryWrapperTenantCustomerInvoice.lambda()//
						.apply("=".equals(queryTypes[i]), queryCols[i] + "={0}", queryValues[i])//
						.apply("like".equals(queryTypes[i]), queryCols[i] + " like CONCAT('%',{0},'%')", queryValues[i])//
				;
			}
		}
		
		List<TenantCustomerInvoice> tenantCustomerInvoiceList = tenantCustomerInvoiceService.list(queryWrapperTenantCustomerInvoice);

		List<TenantCustomerInvoiceVo> tenantCustomerInvoiceVoList = tenantCustomerInvoiceList.stream()//
				 .map(e -> entity2vo(e))//
				 .collect(Collectors.toList());

		return tenantCustomerInvoiceVoList;
	}
	
	@ApiOperation(value = "根据参数查询用户开票信息数量")
	@RequestMapping(value = "/tenant-customer-invoices/count", method = RequestMethod.GET)
	public int count(@RequestBody TenantCustomerInvoiceQueryParam tenantCustomerInvoiceQueryParam) {
		QueryWrapper<TenantCustomerInvoice> queryWrapperTenantCustomerInvoice = new QueryWrapper<TenantCustomerInvoice>();
		queryWrapperTenantCustomerInvoice.lambda()
				.eq(StringUtils.isNotEmpty(tenantCustomerInvoiceQueryParam.getId()), TenantCustomerInvoice::getId, tenantCustomerInvoiceQueryParam.getId())
				.eq(StringUtils.isNotEmpty(tenantCustomerInvoiceQueryParam.getTenantId()), TenantCustomerInvoice::getTenantId, tenantCustomerInvoiceQueryParam.getTenantId())
				.eq(StringUtils.isNotEmpty(tenantCustomerInvoiceQueryParam.getCustomerId()), TenantCustomerInvoice::getCustomerId, tenantCustomerInvoiceQueryParam.getCustomerId())
				.eq(StringUtils.isNotEmpty(tenantCustomerInvoiceQueryParam.getCustomerCode()), TenantCustomerInvoice::getCustomerCode, tenantCustomerInvoiceQueryParam.getCustomerCode())
				.eq(tenantCustomerInvoiceQueryParam.getInvoiceType() != null, TenantCustomerInvoice::getInvoiceType, tenantCustomerInvoiceQueryParam.getInvoiceType())
				.eq(StringUtils.isNotEmpty(tenantCustomerInvoiceQueryParam.getInvoiceName()), TenantCustomerInvoice::getInvoiceName, tenantCustomerInvoiceQueryParam.getInvoiceName())
				.eq(StringUtils.isNotEmpty(tenantCustomerInvoiceQueryParam.getInvoiceTaxNo()), TenantCustomerInvoice::getInvoiceTaxNo, tenantCustomerInvoiceQueryParam.getInvoiceTaxNo())
				.eq(StringUtils.isNotEmpty(tenantCustomerInvoiceQueryParam.getInvoiceAddress()), TenantCustomerInvoice::getInvoiceAddress, tenantCustomerInvoiceQueryParam.getInvoiceAddress())
				.eq(StringUtils.isNotEmpty(tenantCustomerInvoiceQueryParam.getInvoiceTel()), TenantCustomerInvoice::getInvoiceTel, tenantCustomerInvoiceQueryParam.getInvoiceTel())
				.eq(StringUtils.isNotEmpty(tenantCustomerInvoiceQueryParam.getInvoiceBank()), TenantCustomerInvoice::getInvoiceBank, tenantCustomerInvoiceQueryParam.getInvoiceBank())
				.eq(StringUtils.isNotEmpty(tenantCustomerInvoiceQueryParam.getInvoiceAccountNo()), TenantCustomerInvoice::getInvoiceAccountNo, tenantCustomerInvoiceQueryParam.getInvoiceAccountNo())
				.eq(StringUtils.isNotEmpty(tenantCustomerInvoiceQueryParam.getInvoiceMemo()), TenantCustomerInvoice::getInvoiceMemo, tenantCustomerInvoiceQueryParam.getInvoiceMemo())
				.eq(tenantCustomerInvoiceQueryParam.getAddTime() != null, TenantCustomerInvoice::getAddTime, tenantCustomerInvoiceQueryParam.getAddTime())
				.ge(tenantCustomerInvoiceQueryParam.getAddTimeStart() != null, TenantCustomerInvoice::getAddTime,tenantCustomerInvoiceQueryParam.getAddTimeStart() == null ? null: DateUtil.beginOfDay(tenantCustomerInvoiceQueryParam.getAddTimeStart()))
				.le(tenantCustomerInvoiceQueryParam.getAddTimeEnd() != null, TenantCustomerInvoice::getAddTime,tenantCustomerInvoiceQueryParam.getAddTimeEnd() == null ? null: DateUtil.endOfDay(tenantCustomerInvoiceQueryParam.getAddTimeEnd()))
				.eq(tenantCustomerInvoiceQueryParam.getUpdateTime() != null, TenantCustomerInvoice::getUpdateTime, tenantCustomerInvoiceQueryParam.getUpdateTime())
				.ge(tenantCustomerInvoiceQueryParam.getUpdateTimeStart() != null, TenantCustomerInvoice::getUpdateTime,tenantCustomerInvoiceQueryParam.getUpdateTimeStart() == null ? null: DateUtil.beginOfDay(tenantCustomerInvoiceQueryParam.getUpdateTimeStart()))
				.le(tenantCustomerInvoiceQueryParam.getUpdateTimeEnd() != null, TenantCustomerInvoice::getUpdateTime,tenantCustomerInvoiceQueryParam.getUpdateTimeEnd() == null ? null: DateUtil.endOfDay(tenantCustomerInvoiceQueryParam.getUpdateTimeEnd()))
				;

		String[] queryCols = tenantCustomerInvoiceQueryParam.getQueryCol();
		String[] queryTypes = tenantCustomerInvoiceQueryParam.getQueryType();
		String[] queryValues = tenantCustomerInvoiceQueryParam.getQueryValue();
		if (queryCols != null && queryCols.length > 0) {
			for (int i = 0; i < queryCols.length; i++) {
				queryWrapperTenantCustomerInvoice.lambda()//
						.apply("=".equals(queryTypes[i]), queryCols[i] + "={0}", queryValues[i])//
						.apply("like".equals(queryTypes[i]), queryCols[i] + " like CONCAT('%',{0},'%')", queryValues[i])//
				;
			}
		}
		
		int count = tenantCustomerInvoiceService.count(queryWrapperTenantCustomerInvoice);

		return count;
	}
	
	@ApiOperation(value = "根据参数查询用户开票信息列表")
	@RequestMapping(value = "/tenant-customer-invoices", method = RequestMethod.GET)
	public Page<TenantCustomerInvoiceVo> page(@RequestBody TenantCustomerInvoiceQueryParam tenantCustomerInvoiceQueryParam,
			@RequestParam(value = "page", defaultValue = "1") int page, //
			@RequestParam(value = "rows", defaultValue = "10") int rows, //
			@RequestParam(value = "sort", required = false) String sort, // 排序列字段名
			@RequestParam(value = "order", required = false) String order // 可以是 'asc' 或者 'desc'，默认值是 'asc'
	) {
		IPage<TenantCustomerInvoice> pageTenantCustomerInvoice = new Page<TenantCustomerInvoice>(page, rows);
		QueryWrapper<TenantCustomerInvoice> queryWrapperTenantCustomerInvoice = new QueryWrapper<TenantCustomerInvoice>();
		queryWrapperTenantCustomerInvoice.orderBy(StringUtils.isNotBlank(sort), "asc".equals(order), sort);
		queryWrapperTenantCustomerInvoice.lambda()
				.eq(StringUtils.isNotEmpty(tenantCustomerInvoiceQueryParam.getId()), TenantCustomerInvoice::getId, tenantCustomerInvoiceQueryParam.getId())
				.eq(StringUtils.isNotEmpty(tenantCustomerInvoiceQueryParam.getTenantId()), TenantCustomerInvoice::getTenantId, tenantCustomerInvoiceQueryParam.getTenantId())
				.eq(StringUtils.isNotEmpty(tenantCustomerInvoiceQueryParam.getCustomerId()), TenantCustomerInvoice::getCustomerId, tenantCustomerInvoiceQueryParam.getCustomerId())
				.eq(StringUtils.isNotEmpty(tenantCustomerInvoiceQueryParam.getCustomerCode()), TenantCustomerInvoice::getCustomerCode, tenantCustomerInvoiceQueryParam.getCustomerCode())
				.eq(tenantCustomerInvoiceQueryParam.getInvoiceType() != null, TenantCustomerInvoice::getInvoiceType, tenantCustomerInvoiceQueryParam.getInvoiceType())
				.eq(StringUtils.isNotEmpty(tenantCustomerInvoiceQueryParam.getInvoiceName()), TenantCustomerInvoice::getInvoiceName, tenantCustomerInvoiceQueryParam.getInvoiceName())
				.eq(StringUtils.isNotEmpty(tenantCustomerInvoiceQueryParam.getInvoiceTaxNo()), TenantCustomerInvoice::getInvoiceTaxNo, tenantCustomerInvoiceQueryParam.getInvoiceTaxNo())
				.eq(StringUtils.isNotEmpty(tenantCustomerInvoiceQueryParam.getInvoiceAddress()), TenantCustomerInvoice::getInvoiceAddress, tenantCustomerInvoiceQueryParam.getInvoiceAddress())
				.eq(StringUtils.isNotEmpty(tenantCustomerInvoiceQueryParam.getInvoiceTel()), TenantCustomerInvoice::getInvoiceTel, tenantCustomerInvoiceQueryParam.getInvoiceTel())
				.eq(StringUtils.isNotEmpty(tenantCustomerInvoiceQueryParam.getInvoiceBank()), TenantCustomerInvoice::getInvoiceBank, tenantCustomerInvoiceQueryParam.getInvoiceBank())
				.eq(StringUtils.isNotEmpty(tenantCustomerInvoiceQueryParam.getInvoiceAccountNo()), TenantCustomerInvoice::getInvoiceAccountNo, tenantCustomerInvoiceQueryParam.getInvoiceAccountNo())
				.eq(StringUtils.isNotEmpty(tenantCustomerInvoiceQueryParam.getInvoiceMemo()), TenantCustomerInvoice::getInvoiceMemo, tenantCustomerInvoiceQueryParam.getInvoiceMemo())
				.eq(tenantCustomerInvoiceQueryParam.getAddTime() != null, TenantCustomerInvoice::getAddTime, tenantCustomerInvoiceQueryParam.getAddTime())
				.ge(tenantCustomerInvoiceQueryParam.getAddTimeStart() != null, TenantCustomerInvoice::getAddTime,tenantCustomerInvoiceQueryParam.getAddTimeStart() == null ? null: DateUtil.beginOfDay(tenantCustomerInvoiceQueryParam.getAddTimeStart()))
				.le(tenantCustomerInvoiceQueryParam.getAddTimeEnd() != null, TenantCustomerInvoice::getAddTime,tenantCustomerInvoiceQueryParam.getAddTimeEnd() == null ? null: DateUtil.endOfDay(tenantCustomerInvoiceQueryParam.getAddTimeEnd()))
				.eq(tenantCustomerInvoiceQueryParam.getUpdateTime() != null, TenantCustomerInvoice::getUpdateTime, tenantCustomerInvoiceQueryParam.getUpdateTime())
				.ge(tenantCustomerInvoiceQueryParam.getUpdateTimeStart() != null, TenantCustomerInvoice::getUpdateTime,tenantCustomerInvoiceQueryParam.getUpdateTimeStart() == null ? null: DateUtil.beginOfDay(tenantCustomerInvoiceQueryParam.getUpdateTimeStart()))
				.le(tenantCustomerInvoiceQueryParam.getUpdateTimeEnd() != null, TenantCustomerInvoice::getUpdateTime,tenantCustomerInvoiceQueryParam.getUpdateTimeEnd() == null ? null: DateUtil.endOfDay(tenantCustomerInvoiceQueryParam.getUpdateTimeEnd()))
				;

		String[] queryCols = tenantCustomerInvoiceQueryParam.getQueryCol();
		String[] queryTypes = tenantCustomerInvoiceQueryParam.getQueryType();
		String[] queryValues = tenantCustomerInvoiceQueryParam.getQueryValue();
		if (queryCols != null && queryCols.length > 0) {
			for (int i = 0; i < queryCols.length; i++) {
				queryWrapperTenantCustomerInvoice.lambda()//
						.apply("=".equals(queryTypes[i]), queryCols[i] + "={0}", queryValues[i])//
						.apply("like".equals(queryTypes[i]), queryCols[i] + " like CONCAT('%',{0},'%')", queryValues[i])//
				;
			}
		}
		
		IPage<TenantCustomerInvoice> tenantCustomerInvoicePage = tenantCustomerInvoiceService.page(pageTenantCustomerInvoice, queryWrapperTenantCustomerInvoice);

		Page<TenantCustomerInvoiceVo> tenantCustomerInvoiceVoPage = new Page<TenantCustomerInvoiceVo>(page, rows);
		tenantCustomerInvoiceVoPage.setCurrent(tenantCustomerInvoicePage.getCurrent());
		tenantCustomerInvoiceVoPage.setPages(tenantCustomerInvoicePage.getPages());
		tenantCustomerInvoiceVoPage.setSize(tenantCustomerInvoicePage.getSize());
		tenantCustomerInvoiceVoPage.setTotal(tenantCustomerInvoicePage.getTotal());
		tenantCustomerInvoiceVoPage.setRecords(tenantCustomerInvoicePage.getRecords().stream()//
				.map(e -> entity2vo(e))//
				.collect(Collectors.toList()));

		return tenantCustomerInvoiceVoPage;
	}
	
	@ApiOperation(value = "新增用户开票信息")
	@RequestMapping(value = "/tenant-customer-invoices", method = RequestMethod.POST)
	public String save(@RequestBody TenantCustomerInvoiceAddParam tenantCustomerInvoiceAddParam) {
		return tenantCustomerInvoiceService.save(tenantCustomerInvoiceAddParam);
	}

	@ApiOperation(value = "更新用户开票信息全部信息")
	@RequestMapping(value = "/tenant-customer-invoices/{id}", method = RequestMethod.PUT)
	public boolean updateById(@PathVariable("id") String id, @RequestBody TenantCustomerInvoiceUpdateParam tenantCustomerInvoiceUpdateParam) {
		tenantCustomerInvoiceUpdateParam.setId(id);
		return tenantCustomerInvoiceService.updateById(tenantCustomerInvoiceUpdateParam);
	}

	@ApiOperation(value = "根据ID删除用户开票信息")
	@RequestMapping(value = "/tenant-customer-invoices/{id}", method = RequestMethod.DELETE)
	public CommonResult<Object> removeById(@PathVariable("id") String id) {
		boolean success = tenantCustomerInvoiceService.removeById(id);
		return success ? CommonResult.success(success) : CommonResult.failed();
	}

	private TenantCustomerInvoiceVo entity2vo(TenantCustomerInvoice tenantCustomerInvoice) {
		if (tenantCustomerInvoice == null) {
			return null;
		}

		TenantCustomerInvoiceVo tenantCustomerInvoiceVo = TranslateUtil.translate(tenantCustomerInvoice, TenantCustomerInvoiceVo.class);
		if (StringUtils.isEmpty(tenantCustomerInvoiceVo.getTenantName())) {
			TenantInfo tenantInfo = tenantInfoService.getDictionaryById(tenantCustomerInvoice.getTenantId());
			if (tenantInfo != null) {
				tenantCustomerInvoiceVo.setTenantName(tenantInfo.getTenantName());
			}
		}
		return tenantCustomerInvoiceVo;
	}

}
