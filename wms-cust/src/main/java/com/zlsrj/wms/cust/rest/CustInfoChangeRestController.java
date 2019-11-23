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
import com.zlsrj.wms.api.dto.CustInfoChangeQueryParam;
import com.zlsrj.wms.api.entity.CustInfoChange;
import com.zlsrj.wms.api.vo.CustInfoChangeVo;
import com.zlsrj.wms.common.api.CommonResult;
import com.zlsrj.wms.cust.service.IIdService;
import com.zlsrj.wms.cust.service.ICustInfoChangeService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

@Api(value = "用户变更", tags = { "用户变更操作接口" })
@RestController
@Slf4j
public class CustInfoChangeRestController {

	@Autowired
	private ICustInfoChangeService custInfoChangeService;
	@Autowired
	private IIdService idService;

	@ApiOperation(value = "根据ID查询用户变更")
	@RequestMapping(value = "/cust-info-changes/{id}", method = RequestMethod.GET)
	public CustInfoChangeVo getById(@PathVariable("id") Long id) {
		CustInfoChange custInfoChange = custInfoChangeService.getById(id);

		return entity2vo(custInfoChange);
	}

	@ApiOperation(value = "根据参数查询用户变更列表")
	@RequestMapping(value = "/cust-info-changes", method = RequestMethod.GET)
	public Page<CustInfoChangeVo> page(@RequestBody CustInfoChangeQueryParam custInfoChangeQueryParam,
			@RequestParam(value = "page", defaultValue = "1") int page, //
			@RequestParam(value = "rows", defaultValue = "10") int rows, //
			@RequestParam(value = "sort") String sort, // 排序列字段名
			@RequestParam(value = "order") String order // 可以是 'asc' 或者 'desc'，默认值是 'asc'
	) {
		IPage<CustInfoChange> pageCustInfoChange = new Page<CustInfoChange>(page, rows);
		QueryWrapper<CustInfoChange> queryWrapperCustInfoChange = new QueryWrapper<CustInfoChange>();
		queryWrapperCustInfoChange.orderBy(StringUtils.isNotEmpty(sort), "desc".equals(order), sort);
		queryWrapperCustInfoChange.lambda()
				.eq(custInfoChangeQueryParam.getId() != null, CustInfoChange::getId, custInfoChangeQueryParam.getId())
				.eq(custInfoChangeQueryParam.getTenantId() != null, CustInfoChange::getTenantId, custInfoChangeQueryParam.getTenantId())
				.eq(custInfoChangeQueryParam.getCustId() != null, CustInfoChange::getCustId, custInfoChangeQueryParam.getCustId())
				.eq(custInfoChangeQueryParam.getChangeDate() != null, CustInfoChange::getChangeDate, custInfoChangeQueryParam.getChangeDate())
				.eq(custInfoChangeQueryParam.getChanger() != null, CustInfoChange::getChanger, custInfoChangeQueryParam.getChanger())
				.eq(custInfoChangeQueryParam.getBaseChange() != null, CustInfoChange::getBaseChange, custInfoChangeQueryParam.getBaseChange())
				.eq(custInfoChangeQueryParam.getBillChange() != null, CustInfoChange::getBillChange, custInfoChangeQueryParam.getBillChange())
				.eq(custInfoChangeQueryParam.getStatusChange() != null, CustInfoChange::getStatusChange, custInfoChangeQueryParam.getStatusChange())
				.eq(custInfoChangeQueryParam.getCustName() != null, CustInfoChange::getCustName, custInfoChangeQueryParam.getCustName())
				.eq(custInfoChangeQueryParam.getCustNameHis() != null, CustInfoChange::getCustNameHis, custInfoChangeQueryParam.getCustNameHis())
				.eq(custInfoChangeQueryParam.getCustAddress() != null, CustInfoChange::getCustAddress, custInfoChangeQueryParam.getCustAddress())
				.eq(custInfoChangeQueryParam.getCustAddressHis() != null, CustInfoChange::getCustAddressHis, custInfoChangeQueryParam.getCustAddressHis())
				.eq(custInfoChangeQueryParam.getCustTypeId() != null, CustInfoChange::getCustTypeId, custInfoChangeQueryParam.getCustTypeId())
				.eq(custInfoChangeQueryParam.getCustTypeIdHis() != null, CustInfoChange::getCustTypeIdHis, custInfoChangeQueryParam.getCustTypeIdHis())
				.eq(custInfoChangeQueryParam.getCustRegistDate() != null, CustInfoChange::getCustRegistDate, custInfoChangeQueryParam.getCustRegistDate())
				.eq(custInfoChangeQueryParam.getCustRegistDateHis() != null, CustInfoChange::getCustRegistDateHis, custInfoChangeQueryParam.getCustRegistDateHis())
				.eq(custInfoChangeQueryParam.getCustStatus() != null, CustInfoChange::getCustStatus, custInfoChangeQueryParam.getCustStatus())
				.eq(custInfoChangeQueryParam.getCustStatusHis() != null, CustInfoChange::getCustStatusHis, custInfoChangeQueryParam.getCustStatusHis())
				.eq(custInfoChangeQueryParam.getPayType() != null, CustInfoChange::getPayType, custInfoChangeQueryParam.getPayType())
				.eq(custInfoChangeQueryParam.getPayTypeHis() != null, CustInfoChange::getPayTypeHis, custInfoChangeQueryParam.getPayTypeHis())
				.eq(custInfoChangeQueryParam.getBillType() != null, CustInfoChange::getBillType, custInfoChangeQueryParam.getBillType())
				.eq(custInfoChangeQueryParam.getBillTypeHis() != null, CustInfoChange::getBillTypeHis, custInfoChangeQueryParam.getBillTypeHis())
				.eq(custInfoChangeQueryParam.getBillName() != null, CustInfoChange::getBillName, custInfoChangeQueryParam.getBillName())
				.eq(custInfoChangeQueryParam.getBillNameHis() != null, CustInfoChange::getBillNameHis, custInfoChangeQueryParam.getBillNameHis())
				.eq(custInfoChangeQueryParam.getBillTaxnum() != null, CustInfoChange::getBillTaxnum, custInfoChangeQueryParam.getBillTaxnum())
				.eq(custInfoChangeQueryParam.getBillTaxnumHis() != null, CustInfoChange::getBillTaxnumHis, custInfoChangeQueryParam.getBillTaxnumHis())
				.eq(custInfoChangeQueryParam.getBillAddress() != null, CustInfoChange::getBillAddress, custInfoChangeQueryParam.getBillAddress())
				.eq(custInfoChangeQueryParam.getBillAddressHis() != null, CustInfoChange::getBillAddressHis, custInfoChangeQueryParam.getBillAddressHis())
				.eq(custInfoChangeQueryParam.getBillTel() != null, CustInfoChange::getBillTel, custInfoChangeQueryParam.getBillTel())
				.eq(custInfoChangeQueryParam.getBillTelHis() != null, CustInfoChange::getBillTelHis, custInfoChangeQueryParam.getBillTelHis())
				.eq(custInfoChangeQueryParam.getBillBank() != null, CustInfoChange::getBillBank, custInfoChangeQueryParam.getBillBank())
				.eq(custInfoChangeQueryParam.getBillBankHis() != null, CustInfoChange::getBillBankHis, custInfoChangeQueryParam.getBillBankHis())
				.eq(custInfoChangeQueryParam.getBillBankId() != null, CustInfoChange::getBillBankId, custInfoChangeQueryParam.getBillBankId())
				.eq(custInfoChangeQueryParam.getBillBankIdHis() != null, CustInfoChange::getBillBankIdHis, custInfoChangeQueryParam.getBillBankIdHis())
				.eq(custInfoChangeQueryParam.getBillBankName() != null, CustInfoChange::getBillBankName, custInfoChangeQueryParam.getBillBankName())
				.eq(custInfoChangeQueryParam.getBillBankNameHis() != null, CustInfoChange::getBillBankNameHis, custInfoChangeQueryParam.getBillBankNameHis())
				.eq(custInfoChangeQueryParam.getBillBankAccount() != null, CustInfoChange::getBillBankAccount, custInfoChangeQueryParam.getBillBankAccount())
				.eq(custInfoChangeQueryParam.getBillBankAccountHis() != null, CustInfoChange::getBillBankAccountHis, custInfoChangeQueryParam.getBillBankAccountHis())
				;

		IPage<CustInfoChange> custInfoChangePage = custInfoChangeService.page(pageCustInfoChange, queryWrapperCustInfoChange);

		Page<CustInfoChangeVo> custInfoChangeVoPage = new Page<CustInfoChangeVo>(page, rows);
		custInfoChangeVoPage.setCurrent(custInfoChangePage.getCurrent());
		custInfoChangeVoPage.setPages(custInfoChangePage.getPages());
		custInfoChangeVoPage.setSize(custInfoChangePage.getSize());
		custInfoChangeVoPage.setTotal(custInfoChangePage.getTotal());
		custInfoChangeVoPage.setRecords(custInfoChangePage.getRecords().stream()//
				.map(e -> entity2vo(e))//
				.collect(Collectors.toList()));

		return custInfoChangeVoPage;
	}

	@ApiOperation(value = "新增用户变更")
	@RequestMapping(value = "/cust-info-changes", method = RequestMethod.POST)
	public CustInfoChangeVo save(@RequestBody CustInfoChange custInfoChange) {
		if (custInfoChange.getId() == null || custInfoChange.getId().compareTo(0L) <= 0) {
			custInfoChange.setId(idService.selectId());
		}
		boolean success = custInfoChangeService.save(custInfoChange);
		if (success) {
			CustInfoChange custInfoChangeDatabase = custInfoChangeService.getById(custInfoChange.getId());
			return entity2vo(custInfoChangeDatabase);
		}
		log.info("save CustInfoChange fail，{}", ToStringBuilder.reflectionToString(custInfoChange, ToStringStyle.JSON_STYLE));
		return null;
	}

	@ApiOperation(value = "更新用户变更全部信息")
	@RequestMapping(value = "/cust-info-changes/{id}", method = RequestMethod.PUT)
	public CustInfoChangeVo updateById(@PathVariable("id") Long id, @RequestBody CustInfoChange custInfoChange) {
		custInfoChange.setId(id);
		boolean success = custInfoChangeService.updateById(custInfoChange);
		if (success) {
			CustInfoChange custInfoChangeDatabase = custInfoChangeService.getById(id);
			return entity2vo(custInfoChangeDatabase);
		}
		log.info("update CustInfoChange fail，{}", ToStringBuilder.reflectionToString(custInfoChange, ToStringStyle.JSON_STYLE));
		return null;
	}

	@ApiOperation(value = "根据参数更新用户变更信息")
	@RequestMapping(value = "/cust-info-changes/{id}", method = RequestMethod.PATCH)
	public CustInfoChangeVo updatePatchById(@PathVariable("id") Long id, @RequestBody CustInfoChange custInfoChange) {
		UpdateWrapper<CustInfoChange> updateWrapperCustInfoChange = new UpdateWrapper<CustInfoChange>();
		updateWrapperCustInfoChange.lambda()//
				.eq(CustInfoChange::getId, id)
				// .set(custInfoChange.getId() != null, CustInfoChange::getId, custInfoChange.getId())
				.set(custInfoChange.getTenantId() != null, CustInfoChange::getTenantId, custInfoChange.getTenantId())
				.set(custInfoChange.getCustId() != null, CustInfoChange::getCustId, custInfoChange.getCustId())
				.set(custInfoChange.getChangeDate() != null, CustInfoChange::getChangeDate, custInfoChange.getChangeDate())
				.set(custInfoChange.getChanger() != null, CustInfoChange::getChanger, custInfoChange.getChanger())
				.set(custInfoChange.getBaseChange() != null, CustInfoChange::getBaseChange, custInfoChange.getBaseChange())
				.set(custInfoChange.getBillChange() != null, CustInfoChange::getBillChange, custInfoChange.getBillChange())
				.set(custInfoChange.getStatusChange() != null, CustInfoChange::getStatusChange, custInfoChange.getStatusChange())
				.set(custInfoChange.getCustName() != null, CustInfoChange::getCustName, custInfoChange.getCustName())
				.set(custInfoChange.getCustNameHis() != null, CustInfoChange::getCustNameHis, custInfoChange.getCustNameHis())
				.set(custInfoChange.getCustAddress() != null, CustInfoChange::getCustAddress, custInfoChange.getCustAddress())
				.set(custInfoChange.getCustAddressHis() != null, CustInfoChange::getCustAddressHis, custInfoChange.getCustAddressHis())
				.set(custInfoChange.getCustTypeId() != null, CustInfoChange::getCustTypeId, custInfoChange.getCustTypeId())
				.set(custInfoChange.getCustTypeIdHis() != null, CustInfoChange::getCustTypeIdHis, custInfoChange.getCustTypeIdHis())
				.set(custInfoChange.getCustRegistDate() != null, CustInfoChange::getCustRegistDate, custInfoChange.getCustRegistDate())
				.set(custInfoChange.getCustRegistDateHis() != null, CustInfoChange::getCustRegistDateHis, custInfoChange.getCustRegistDateHis())
				.set(custInfoChange.getCustStatus() != null, CustInfoChange::getCustStatus, custInfoChange.getCustStatus())
				.set(custInfoChange.getCustStatusHis() != null, CustInfoChange::getCustStatusHis, custInfoChange.getCustStatusHis())
				.set(custInfoChange.getPayType() != null, CustInfoChange::getPayType, custInfoChange.getPayType())
				.set(custInfoChange.getPayTypeHis() != null, CustInfoChange::getPayTypeHis, custInfoChange.getPayTypeHis())
				.set(custInfoChange.getBillType() != null, CustInfoChange::getBillType, custInfoChange.getBillType())
				.set(custInfoChange.getBillTypeHis() != null, CustInfoChange::getBillTypeHis, custInfoChange.getBillTypeHis())
				.set(custInfoChange.getBillName() != null, CustInfoChange::getBillName, custInfoChange.getBillName())
				.set(custInfoChange.getBillNameHis() != null, CustInfoChange::getBillNameHis, custInfoChange.getBillNameHis())
				.set(custInfoChange.getBillTaxnum() != null, CustInfoChange::getBillTaxnum, custInfoChange.getBillTaxnum())
				.set(custInfoChange.getBillTaxnumHis() != null, CustInfoChange::getBillTaxnumHis, custInfoChange.getBillTaxnumHis())
				.set(custInfoChange.getBillAddress() != null, CustInfoChange::getBillAddress, custInfoChange.getBillAddress())
				.set(custInfoChange.getBillAddressHis() != null, CustInfoChange::getBillAddressHis, custInfoChange.getBillAddressHis())
				.set(custInfoChange.getBillTel() != null, CustInfoChange::getBillTel, custInfoChange.getBillTel())
				.set(custInfoChange.getBillTelHis() != null, CustInfoChange::getBillTelHis, custInfoChange.getBillTelHis())
				.set(custInfoChange.getBillBank() != null, CustInfoChange::getBillBank, custInfoChange.getBillBank())
				.set(custInfoChange.getBillBankHis() != null, CustInfoChange::getBillBankHis, custInfoChange.getBillBankHis())
				.set(custInfoChange.getBillBankId() != null, CustInfoChange::getBillBankId, custInfoChange.getBillBankId())
				.set(custInfoChange.getBillBankIdHis() != null, CustInfoChange::getBillBankIdHis, custInfoChange.getBillBankIdHis())
				.set(custInfoChange.getBillBankName() != null, CustInfoChange::getBillBankName, custInfoChange.getBillBankName())
				.set(custInfoChange.getBillBankNameHis() != null, CustInfoChange::getBillBankNameHis, custInfoChange.getBillBankNameHis())
				.set(custInfoChange.getBillBankAccount() != null, CustInfoChange::getBillBankAccount, custInfoChange.getBillBankAccount())
				.set(custInfoChange.getBillBankAccountHis() != null, CustInfoChange::getBillBankAccountHis, custInfoChange.getBillBankAccountHis())
				;

		boolean success = custInfoChangeService.update(updateWrapperCustInfoChange);
		if (success) {
			CustInfoChange custInfoChangeDatabase = custInfoChangeService.getById(id);
			return entity2vo(custInfoChangeDatabase);
		}
		log.info("partial update CustInfoChange fail，{}",
				ToStringBuilder.reflectionToString(custInfoChange, ToStringStyle.JSON_STYLE));
		return null;
	}

	@ApiOperation(value = "根据ID删除用户变更")
	@RequestMapping(value = "/cust-info-changes/{id}", method = RequestMethod.DELETE)
	public CommonResult<Object> removeById(@PathVariable("id") Long id) {
		boolean success = custInfoChangeService.removeById(id);
		return success ? CommonResult.success(success) : CommonResult.failed();
	}

	private CustInfoChangeVo entity2vo(CustInfoChange custInfoChange) {
		String jsonString = JSON.toJSONString(custInfoChange);
		CustInfoChangeVo custInfoChangeVo = JSON.parseObject(jsonString, CustInfoChangeVo.class);
		return custInfoChangeVo;
	}

}
