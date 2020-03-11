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
import com.zlsrj.wms.api.dto.TenantInfoQueryParam;
import com.zlsrj.wms.api.entity.TenantInfo;
import com.zlsrj.wms.api.vo.TenantInfoVo;
import com.zlsrj.wms.common.api.CommonResult;
import com.zlsrj.wms.saas.service.IIdService;
import com.zlsrj.wms.saas.service.ITenantInfoService;

import cn.hutool.core.date.DateUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

@Api(value = "租户表", tags = { "租户表操作接口" })
@RestController
@Slf4j
public class TenantInfoRestController {

	@Autowired
	private ITenantInfoService tenantInfoService;
	@Autowired
	private IIdService idService;

	@ApiOperation(value = "根据ID查询租户表")
	@RequestMapping(value = "/tenant-infos/{id}", method = RequestMethod.GET)
	public TenantInfoVo getById(@PathVariable("id") String id) {
		TenantInfo tenantInfo = tenantInfoService.getById(id);

		return entity2vo(tenantInfo);
	}

	@ApiOperation(value = "根据参数查询租户表列表")
	@RequestMapping(value = "/tenant-infos", method = RequestMethod.GET)
	public Page<TenantInfoVo> page(@RequestBody TenantInfoQueryParam tenantInfoQueryParam,
			@RequestParam(value = "page", defaultValue = "1") int page, //
			@RequestParam(value = "rows", defaultValue = "10") int rows, //
			@RequestParam(value = "sort") String sort, // 排序列字段名
			@RequestParam(value = "order") String order // 可以是 'asc' 或者 'desc'，默认值是 'asc'
	) {
		IPage<TenantInfo> pageTenantInfo = new Page<TenantInfo>(page, rows);
		QueryWrapper<TenantInfo> queryWrapperTenantInfo = new QueryWrapper<TenantInfo>();
		queryWrapperTenantInfo.orderBy(StringUtils.isNotEmpty(sort), "asc".equals(order), sort);
		queryWrapperTenantInfo.lambda()
				.eq(tenantInfoQueryParam.getId() != null, TenantInfo::getId, tenantInfoQueryParam.getId())
				.eq(tenantInfoQueryParam.getTenantName() != null, TenantInfo::getTenantName, tenantInfoQueryParam.getTenantName())
				.eq(tenantInfoQueryParam.getTenantBalance() != null, TenantInfo::getTenantBalance, tenantInfoQueryParam.getTenantBalance())
				.eq(tenantInfoQueryParam.getTeanantOverdraw() != null, TenantInfo::getTeanantOverdraw, tenantInfoQueryParam.getTeanantOverdraw())
				.eq(tenantInfoQueryParam.getTenantDisplayName() != null, TenantInfo::getTenantDisplayName, tenantInfoQueryParam.getTenantDisplayName())
				.eq(tenantInfoQueryParam.getTenantProvince() != null, TenantInfo::getTenantProvince, tenantInfoQueryParam.getTenantProvince())
				.eq(tenantInfoQueryParam.getTenantCity() != null, TenantInfo::getTenantCity, tenantInfoQueryParam.getTenantCity())
				.eq(tenantInfoQueryParam.getTenantCountyTown() != null, TenantInfo::getTenantCountyTown, tenantInfoQueryParam.getTenantCountyTown())
				.eq(tenantInfoQueryParam.getTenantLinkAddress() != null, TenantInfo::getTenantLinkAddress, tenantInfoQueryParam.getTenantLinkAddress())
				.eq(tenantInfoQueryParam.getTenantLinkman() != null, TenantInfo::getTenantLinkman, tenantInfoQueryParam.getTenantLinkman())
				.eq(tenantInfoQueryParam.getTenantLinkmanMobile() != null, TenantInfo::getTenantLinkmanMobile, tenantInfoQueryParam.getTenantLinkmanMobile())
				.eq(tenantInfoQueryParam.getTenantLinkmanTel() != null, TenantInfo::getTenantLinkmanTel, tenantInfoQueryParam.getTenantLinkmanTel())
				.eq(tenantInfoQueryParam.getTenantLinkmanEmail() != null, TenantInfo::getTenantLinkmanEmail, tenantInfoQueryParam.getTenantLinkmanEmail())
				.eq(tenantInfoQueryParam.getTenantLinkmanQq() != null, TenantInfo::getTenantLinkmanQq, tenantInfoQueryParam.getTenantLinkmanQq())
				.eq(tenantInfoQueryParam.getTenantType() != null, TenantInfo::getTenantType, tenantInfoQueryParam.getTenantType())
				.eq(tenantInfoQueryParam.getTenantRegisterTime() != null, TenantInfo::getTenantRegisterTime, tenantInfoQueryParam.getTenantRegisterTime())
				.ge(tenantInfoQueryParam.getTenantRegisterTimeStart() != null, TenantInfo::getTenantRegisterTime,tenantInfoQueryParam.getTenantRegisterTimeStart() == null ? null: DateUtil.beginOfDay(tenantInfoQueryParam.getTenantRegisterTimeStart()))
				.le(tenantInfoQueryParam.getTenantRegisterTimeEnd() != null, TenantInfo::getTenantRegisterTime,tenantInfoQueryParam.getTenantRegisterTimeEnd() == null ? null: DateUtil.endOfDay(tenantInfoQueryParam.getTenantRegisterTimeEnd()))
				.eq(tenantInfoQueryParam.getInvoiceType() != null, TenantInfo::getInvoiceType, tenantInfoQueryParam.getInvoiceType())
				.eq(tenantInfoQueryParam.getInvoiceName() != null, TenantInfo::getInvoiceName, tenantInfoQueryParam.getInvoiceName())
				.eq(tenantInfoQueryParam.getInvoiceTaxNo() != null, TenantInfo::getInvoiceTaxNo, tenantInfoQueryParam.getInvoiceTaxNo())
				.eq(tenantInfoQueryParam.getInvoiceAddress() != null, TenantInfo::getInvoiceAddress, tenantInfoQueryParam.getInvoiceAddress())
				.eq(tenantInfoQueryParam.getInvoiceTel() != null, TenantInfo::getInvoiceTel, tenantInfoQueryParam.getInvoiceTel())
				.eq(tenantInfoQueryParam.getInvoiceBankCode() != null, TenantInfo::getInvoiceBankCode, tenantInfoQueryParam.getInvoiceBankCode())
				.eq(tenantInfoQueryParam.getInvoiceBankName() != null, TenantInfo::getInvoiceBankName, tenantInfoQueryParam.getInvoiceBankName())
				.eq(tenantInfoQueryParam.getInvoiceBankAccountNo() != null, TenantInfo::getInvoiceBankAccountNo, tenantInfoQueryParam.getInvoiceBankAccountNo())
				.eq(tenantInfoQueryParam.getTenantAccesskey() != null, TenantInfo::getTenantAccesskey, tenantInfoQueryParam.getTenantAccesskey())
				;

		IPage<TenantInfo> tenantInfoPage = tenantInfoService.page(pageTenantInfo, queryWrapperTenantInfo);

		Page<TenantInfoVo> tenantInfoVoPage = new Page<TenantInfoVo>(page, rows);
		tenantInfoVoPage.setCurrent(tenantInfoPage.getCurrent());
		tenantInfoVoPage.setPages(tenantInfoPage.getPages());
		tenantInfoVoPage.setSize(tenantInfoPage.getSize());
		tenantInfoVoPage.setTotal(tenantInfoPage.getTotal());
		tenantInfoVoPage.setRecords(tenantInfoPage.getRecords().stream()//
				.map(e -> entity2vo(e))//
				.collect(Collectors.toList()));

		return tenantInfoVoPage;
	}

	@ApiOperation(value = "新增租户表")
	@RequestMapping(value = "/tenant-infos", method = RequestMethod.POST)
	public TenantInfoVo save(@RequestBody TenantInfo tenantInfo) {
		if (tenantInfo.getId() == null || tenantInfo.getId().trim().length() <= 0) {
			tenantInfo.setId(idService.selectId());
		}
		boolean success = tenantInfoService.save(tenantInfo);
		if (success) {
			TenantInfo tenantInfoDatabase = tenantInfoService.getById(tenantInfo.getId());
			return entity2vo(tenantInfoDatabase);
		}
		log.info("save TenantInfo fail，{}", ToStringBuilder.reflectionToString(tenantInfo, ToStringStyle.JSON_STYLE));
		return null;
	}

	@ApiOperation(value = "更新租户表全部信息")
	@RequestMapping(value = "/tenant-infos/{id}", method = RequestMethod.PUT)
	public TenantInfoVo updateById(@PathVariable("id") String id, @RequestBody TenantInfo tenantInfo) {
		tenantInfo.setId(id);
		boolean success = tenantInfoService.updateById(tenantInfo);
		if (success) {
			TenantInfo tenantInfoDatabase = tenantInfoService.getById(id);
			return entity2vo(tenantInfoDatabase);
		}
		log.info("update TenantInfo fail，{}", ToStringBuilder.reflectionToString(tenantInfo, ToStringStyle.JSON_STYLE));
		return null;
	}

	@ApiOperation(value = "根据参数更新租户表信息")
	@RequestMapping(value = "/tenant-infos/{id}", method = RequestMethod.PATCH)
	public TenantInfoVo updatePatchById(@PathVariable("id") String id, @RequestBody TenantInfo tenantInfo) {
        TenantInfo tenantInfoWhere = TenantInfo.builder()//
				.id(id)//
				.build();
		UpdateWrapper<TenantInfo> updateWrapperTenantInfo = new UpdateWrapper<TenantInfo>();
		updateWrapperTenantInfo.setEntity(tenantInfoWhere);
		updateWrapperTenantInfo.lambda()//
				//.eq(TenantInfo::getId, id)
				// .set(tenantInfo.getId() != null, TenantInfo::getId, tenantInfo.getId())
				.set(tenantInfo.getTenantName() != null, TenantInfo::getTenantName, tenantInfo.getTenantName())
				.set(tenantInfo.getTenantBalance() != null, TenantInfo::getTenantBalance, tenantInfo.getTenantBalance())
				.set(tenantInfo.getTeanantOverdraw() != null, TenantInfo::getTeanantOverdraw, tenantInfo.getTeanantOverdraw())
				.set(tenantInfo.getTenantDisplayName() != null, TenantInfo::getTenantDisplayName, tenantInfo.getTenantDisplayName())
				.set(tenantInfo.getTenantProvince() != null, TenantInfo::getTenantProvince, tenantInfo.getTenantProvince())
				.set(tenantInfo.getTenantCity() != null, TenantInfo::getTenantCity, tenantInfo.getTenantCity())
				.set(tenantInfo.getTenantCountyTown() != null, TenantInfo::getTenantCountyTown, tenantInfo.getTenantCountyTown())
				.set(tenantInfo.getTenantLinkAddress() != null, TenantInfo::getTenantLinkAddress, tenantInfo.getTenantLinkAddress())
				.set(tenantInfo.getTenantLinkman() != null, TenantInfo::getTenantLinkman, tenantInfo.getTenantLinkman())
				.set(tenantInfo.getTenantLinkmanMobile() != null, TenantInfo::getTenantLinkmanMobile, tenantInfo.getTenantLinkmanMobile())
				.set(tenantInfo.getTenantLinkmanTel() != null, TenantInfo::getTenantLinkmanTel, tenantInfo.getTenantLinkmanTel())
				.set(tenantInfo.getTenantLinkmanEmail() != null, TenantInfo::getTenantLinkmanEmail, tenantInfo.getTenantLinkmanEmail())
				.set(tenantInfo.getTenantLinkmanQq() != null, TenantInfo::getTenantLinkmanQq, tenantInfo.getTenantLinkmanQq())
				.set(tenantInfo.getTenantType() != null, TenantInfo::getTenantType, tenantInfo.getTenantType())
				.set(tenantInfo.getTenantRegisterTime() != null, TenantInfo::getTenantRegisterTime, tenantInfo.getTenantRegisterTime())
				.set(tenantInfo.getInvoiceType() != null, TenantInfo::getInvoiceType, tenantInfo.getInvoiceType())
				.set(tenantInfo.getInvoiceName() != null, TenantInfo::getInvoiceName, tenantInfo.getInvoiceName())
				.set(tenantInfo.getInvoiceTaxNo() != null, TenantInfo::getInvoiceTaxNo, tenantInfo.getInvoiceTaxNo())
				.set(tenantInfo.getInvoiceAddress() != null, TenantInfo::getInvoiceAddress, tenantInfo.getInvoiceAddress())
				.set(tenantInfo.getInvoiceTel() != null, TenantInfo::getInvoiceTel, tenantInfo.getInvoiceTel())
				.set(tenantInfo.getInvoiceBankCode() != null, TenantInfo::getInvoiceBankCode, tenantInfo.getInvoiceBankCode())
				.set(tenantInfo.getInvoiceBankName() != null, TenantInfo::getInvoiceBankName, tenantInfo.getInvoiceBankName())
				.set(tenantInfo.getInvoiceBankAccountNo() != null, TenantInfo::getInvoiceBankAccountNo, tenantInfo.getInvoiceBankAccountNo())
				.set(tenantInfo.getTenantAccesskey() != null, TenantInfo::getTenantAccesskey, tenantInfo.getTenantAccesskey())
				;

		boolean success = tenantInfoService.update(updateWrapperTenantInfo);
		if (success) {
			TenantInfo tenantInfoDatabase = tenantInfoService.getById(id);
			return entity2vo(tenantInfoDatabase);
		}
		log.info("partial update TenantInfo fail，{}",
				ToStringBuilder.reflectionToString(tenantInfo, ToStringStyle.JSON_STYLE));
		return null;
	}

	@ApiOperation(value = "根据ID删除租户表")
	@RequestMapping(value = "/tenant-infos/{id}", method = RequestMethod.DELETE)
	public CommonResult<Object> removeById(@PathVariable("id") String id) {
		boolean success = tenantInfoService.removeById(id);
		return success ? CommonResult.success(success) : CommonResult.failed();
	}

	private TenantInfoVo entity2vo(TenantInfo tenantInfo) {
		if (tenantInfo == null) {
			return null;
		}

		String jsonString = JSON.toJSONString(tenantInfo);
		TenantInfoVo tenantInfoVo = JSON.parseObject(jsonString, TenantInfoVo.class);
		return tenantInfoVo;
	}

}
