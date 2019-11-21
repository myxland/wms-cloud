package com.zlsrj.wms.cust.rest;

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
import com.zlsrj.wms.api.dto.CustInfoQueryParam;
import com.zlsrj.wms.api.entity.CustInfo;
import com.zlsrj.wms.api.vo.CustInfoVo;
import com.zlsrj.wms.common.api.CommonResult;
import com.zlsrj.wms.cust.service.IIdService;
import com.zlsrj.wms.cust.service.ICustInfoService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

@Api(value = "用户信息", tags = { "用户信息操作接口" })
@RestController
@Slf4j
public class CustInfoRestController {

	@Autowired
	private ICustInfoService custInfoService;
	@Autowired
	private IIdService idService;

	@ApiOperation(value = "根据ID查询用户信息")
	@RequestMapping(value = "/cust-infos/{id}", method = RequestMethod.GET)
	public CustInfoVo getById(@PathVariable("id") Long id) {
		CustInfo custInfo = custInfoService.getById(id);

		return entity2vo(custInfo);
	}

	@ApiOperation(value = "根据参数查询用户信息列表")
	@RequestMapping(value = "/cust-infos", method = RequestMethod.GET)
	public Page<CustInfoVo> page(@RequestBody CustInfoQueryParam custInfoQueryParam,
			@RequestParam(value = "page", defaultValue = "1") int page, //
			@RequestParam(value = "rows", defaultValue = "10") int rows, //
			@RequestParam(value = "sort") String sort, // 排序列字段名
			@RequestParam(value = "order") String order // 可以是 'asc' 或者 'desc'，默认值是 'asc'
	) {
		IPage<CustInfo> pageCustInfo = new Page<CustInfo>(page, rows);
		QueryWrapper<CustInfo> queryWrapperCustInfo = new QueryWrapper<CustInfo>();
		queryWrapperCustInfo.orderBy(StringUtils.isNotEmpty(sort), "desc".equals(order), sort);
		queryWrapperCustInfo.lambda()
				.eq(custInfoQueryParam.getId() != null, CustInfo::getId, custInfoQueryParam.getId())
				.eq(custInfoQueryParam.getTenantId() != null, CustInfo::getTenantId, custInfoQueryParam.getTenantId())
				.eq(custInfoQueryParam.getCustNo() != null, CustInfo::getCustNo, custInfoQueryParam.getCustNo())
				.eq(custInfoQueryParam.getCustName() != null, CustInfo::getCustName, custInfoQueryParam.getCustName())
				.eq(custInfoQueryParam.getCustAddress() != null, CustInfo::getCustAddress, custInfoQueryParam.getCustAddress())
				.eq(custInfoQueryParam.getCustTypeId() != null, CustInfo::getCustTypeId, custInfoQueryParam.getCustTypeId())
				.eq(custInfoQueryParam.getCustRegistDate() != null, CustInfo::getCustRegistDate, custInfoQueryParam.getCustRegistDate())
				.eq(custInfoQueryParam.getCustStatus() != null, CustInfo::getCustStatus, custInfoQueryParam.getCustStatus())
				.eq(custInfoQueryParam.getPayType() != null, CustInfo::getPayType, custInfoQueryParam.getPayType())
				.eq(custInfoQueryParam.getBillType() != null, CustInfo::getBillType, custInfoQueryParam.getBillType())
				.eq(custInfoQueryParam.getBillName() != null, CustInfo::getBillName, custInfoQueryParam.getBillName())
				.eq(custInfoQueryParam.getBillTaxnum() != null, CustInfo::getBillTaxnum, custInfoQueryParam.getBillTaxnum())
				.eq(custInfoQueryParam.getBillAddress() != null, CustInfo::getBillAddress, custInfoQueryParam.getBillAddress())
				.eq(custInfoQueryParam.getBillTel() != null, CustInfo::getBillTel, custInfoQueryParam.getBillTel())
				.eq(custInfoQueryParam.getBillBank() != null, CustInfo::getBillBank, custInfoQueryParam.getBillBank())
				.eq(custInfoQueryParam.getBillBankName() != null, CustInfo::getBillBankName, custInfoQueryParam.getBillBankName())
				.eq(custInfoQueryParam.getBillBankAccount() != null, CustInfo::getBillBankAccount, custInfoQueryParam.getBillBankAccount())
				.eq(custInfoQueryParam.getBillBankId() != null, CustInfo::getBillBankId, custInfoQueryParam.getBillBankId())
				.eq(custInfoQueryParam.getSaveMoney() != null, CustInfo::getSaveMoney, custInfoQueryParam.getSaveMoney())
				.eq(custInfoQueryParam.getDueMoney() != null, CustInfo::getDueMoney, custInfoQueryParam.getDueMoney())
				;

		IPage<CustInfo> custInfoPage = custInfoService.page(pageCustInfo, queryWrapperCustInfo);

		Page<CustInfoVo> custInfoVoPage = new Page<CustInfoVo>(page, rows);
		custInfoVoPage.setCurrent(custInfoPage.getCurrent());
		custInfoVoPage.setPages(custInfoPage.getPages());
		custInfoVoPage.setSize(custInfoPage.getSize());
		custInfoVoPage.setTotal(custInfoPage.getTotal());
		custInfoVoPage.setRecords(custInfoPage.getRecords().stream()//
				.map(e -> entity2vo(e))//
				.collect(Collectors.toList()));

		return custInfoVoPage;
	}

	@ApiOperation(value = "新增用户信息")
	@RequestMapping(value = "/cust-infos", method = RequestMethod.POST)
	public CustInfoVo save(@RequestBody CustInfo custInfo) {
		if (custInfo.getId() == null || custInfo.getId().compareTo(0L) <= 0) {
			custInfo.setId(idService.selectId());
		}
		boolean success = custInfoService.save(custInfo);
		if (success) {
			CustInfo custInfoDatabase = custInfoService.getById(custInfo.getId());
			return entity2vo(custInfoDatabase);
		}
		log.info("save CustInfo fail，{}", ToStringBuilder.reflectionToString(custInfo, ToStringStyle.JSON_STYLE));
		return null;
	}

	@ApiOperation(value = "更新用户信息全部信息")
	@RequestMapping(value = "/cust-infos/{id}", method = RequestMethod.PUT)
	public CustInfoVo updateById(@PathVariable("id") Long id, @RequestBody CustInfo custInfo) {
		custInfo.setId(id);
		boolean success = custInfoService.updateById(custInfo);
		if (success) {
			CustInfo custInfoDatabase = custInfoService.getById(id);
			return entity2vo(custInfoDatabase);
		}
		log.info("update CustInfo fail，{}", ToStringBuilder.reflectionToString(custInfo, ToStringStyle.JSON_STYLE));
		return null;
	}

	@ApiOperation(value = "根据参数更新用户信息信息")
	@RequestMapping(value = "/cust-infos/{id}", method = RequestMethod.PATCH)
	public CustInfoVo updatePatchById(@PathVariable("id") Long id, @RequestBody CustInfo custInfo) {
		UpdateWrapper<CustInfo> updateWrapperCustInfo = new UpdateWrapper<CustInfo>();
		updateWrapperCustInfo.lambda()//
				.eq(CustInfo::getId, id)
				// .set(custInfo.getId() != null, CustInfo::getId, custInfo.getId())
				.set(custInfo.getTenantId() != null, CustInfo::getTenantId, custInfo.getTenantId())
				.set(custInfo.getCustNo() != null, CustInfo::getCustNo, custInfo.getCustNo())
				.set(custInfo.getCustName() != null, CustInfo::getCustName, custInfo.getCustName())
				.set(custInfo.getCustAddress() != null, CustInfo::getCustAddress, custInfo.getCustAddress())
				.set(custInfo.getCustTypeId() != null, CustInfo::getCustTypeId, custInfo.getCustTypeId())
				.set(custInfo.getCustRegistDate() != null, CustInfo::getCustRegistDate, custInfo.getCustRegistDate())
				.set(custInfo.getCustStatus() != null, CustInfo::getCustStatus, custInfo.getCustStatus())
				.set(custInfo.getPayType() != null, CustInfo::getPayType, custInfo.getPayType())
				.set(custInfo.getBillType() != null, CustInfo::getBillType, custInfo.getBillType())
				.set(custInfo.getBillName() != null, CustInfo::getBillName, custInfo.getBillName())
				.set(custInfo.getBillTaxnum() != null, CustInfo::getBillTaxnum, custInfo.getBillTaxnum())
				.set(custInfo.getBillAddress() != null, CustInfo::getBillAddress, custInfo.getBillAddress())
				.set(custInfo.getBillTel() != null, CustInfo::getBillTel, custInfo.getBillTel())
				.set(custInfo.getBillBank() != null, CustInfo::getBillBank, custInfo.getBillBank())
				.set(custInfo.getBillBankName() != null, CustInfo::getBillBankName, custInfo.getBillBankName())
				.set(custInfo.getBillBankAccount() != null, CustInfo::getBillBankAccount, custInfo.getBillBankAccount())
				.set(custInfo.getBillBankId() != null, CustInfo::getBillBankId, custInfo.getBillBankId())
				.set(custInfo.getSaveMoney() != null, CustInfo::getSaveMoney, custInfo.getSaveMoney())
				.set(custInfo.getDueMoney() != null, CustInfo::getDueMoney, custInfo.getDueMoney())
				;

		boolean success = custInfoService.update(updateWrapperCustInfo);
		if (success) {
			CustInfo custInfoDatabase = custInfoService.getById(id);
			return entity2vo(custInfoDatabase);
		}
		log.info("partial update CustInfo fail，{}",
				ToStringBuilder.reflectionToString(custInfo, ToStringStyle.JSON_STYLE));
		return null;
	}

	@ApiOperation(value = "根据ID删除用户信息")
	@RequestMapping(value = "/cust-infos/{id}", method = RequestMethod.DELETE)
	public CommonResult<Object> removeById(@PathVariable("id") Long id) {
		boolean success = custInfoService.removeById(id);
		return success ? CommonResult.success(success) : CommonResult.failed();
	}

	private CustInfoVo entity2vo(CustInfo custInfo) {
		String jsonString = JSON.toJSONString(custInfo);
		CustInfoVo custInfoVo = JSON.parseObject(jsonString, CustInfoVo.class);
		return custInfoVo;
	}

}
