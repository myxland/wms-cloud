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
import com.zlsrj.wms.api.dto.TenantCustomerStatementAddParam;
import com.zlsrj.wms.api.dto.TenantCustomerStatementQueryParam;
import com.zlsrj.wms.api.dto.TenantCustomerStatementUpdateParam;
import com.zlsrj.wms.api.entity.TenantInfo;
import com.zlsrj.wms.api.entity.TenantCustomerStatement;
import com.zlsrj.wms.api.vo.TenantCustomerStatementVo;
import com.zlsrj.wms.common.api.CommonResult;
import com.zlsrj.wms.common.util.TranslateUtil;
import com.zlsrj.wms.saas.service.ITenantInfoService;
import com.zlsrj.wms.saas.service.ITenantCustomerStatementService;

import cn.hutool.core.date.DateUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

@Api(value = "用户结算信息", tags = { "用户结算信息操作接口" })
@RestController
@Slf4j
public class TenantCustomerStatementRestController {

	@Autowired
	private ITenantCustomerStatementService tenantCustomerStatementService;
	@Autowired
	private ITenantInfoService tenantInfoService;

	@ApiOperation(value = "根据ID查询用户结算信息")
	@RequestMapping(value = "/tenant-customer-statements/{id}", method = RequestMethod.GET)
	public TenantCustomerStatementVo getById(@PathVariable("id") String id) {
		TenantCustomerStatement tenantCustomerStatement = tenantCustomerStatementService.getById(id);

		return entity2vo(tenantCustomerStatement);
	}

	@ApiOperation(value = "根据参数查询用户结算信息列表")
	@RequestMapping(value = "/tenant-customer-statements/list", method = RequestMethod.GET)
	public List<TenantCustomerStatementVo> list(@RequestBody TenantCustomerStatementQueryParam tenantCustomerStatementQueryParam) {
		QueryWrapper<TenantCustomerStatement> queryWrapperTenantCustomerStatement = new QueryWrapper<TenantCustomerStatement>();
		queryWrapperTenantCustomerStatement.lambda()
				.eq(StringUtils.isNotEmpty(tenantCustomerStatementQueryParam.getId()), TenantCustomerStatement::getId, tenantCustomerStatementQueryParam.getId())
				.eq(StringUtils.isNotEmpty(tenantCustomerStatementQueryParam.getTenantId()), TenantCustomerStatement::getTenantId, tenantCustomerStatementQueryParam.getTenantId())
				.eq(StringUtils.isNotEmpty(tenantCustomerStatementQueryParam.getCustomerId()), TenantCustomerStatement::getCustomerId, tenantCustomerStatementQueryParam.getCustomerId())
				.eq(StringUtils.isNotEmpty(tenantCustomerStatementQueryParam.getCustomerCode()), TenantCustomerStatement::getCustomerCode, tenantCustomerStatementQueryParam.getCustomerCode())
				.eq(tenantCustomerStatementQueryParam.getStatementMethod() != null, TenantCustomerStatement::getStatementMethod, tenantCustomerStatementQueryParam.getStatementMethod())
				.eq(StringUtils.isNotEmpty(tenantCustomerStatementQueryParam.getStatementBankId()), TenantCustomerStatement::getStatementBankId, tenantCustomerStatementQueryParam.getStatementBankId())
				.eq(StringUtils.isNotEmpty(tenantCustomerStatementQueryParam.getEntrustAgreementNo()), TenantCustomerStatement::getEntrustAgreementNo, tenantCustomerStatementQueryParam.getEntrustAgreementNo())
				.eq(StringUtils.isNotEmpty(tenantCustomerStatementQueryParam.getEntrustCode()), TenantCustomerStatement::getEntrustCode, tenantCustomerStatementQueryParam.getEntrustCode())
				.eq(tenantCustomerStatementQueryParam.getStatementAccountBankId() != null, TenantCustomerStatement::getStatementAccountBankId, tenantCustomerStatementQueryParam.getStatementAccountBankId())
				.eq(StringUtils.isNotEmpty(tenantCustomerStatementQueryParam.getStatementAccountName()), TenantCustomerStatement::getStatementAccountName, tenantCustomerStatementQueryParam.getStatementAccountName())
				.eq(StringUtils.isNotEmpty(tenantCustomerStatementQueryParam.getStatementAccountNo()), TenantCustomerStatement::getStatementAccountNo, tenantCustomerStatementQueryParam.getStatementAccountNo())
				.eq(tenantCustomerStatementQueryParam.getStatementRegisterDate() != null, TenantCustomerStatement::getStatementRegisterDate, tenantCustomerStatementQueryParam.getStatementRegisterDate())
				.ge(tenantCustomerStatementQueryParam.getStatementRegisterDateStart() != null, TenantCustomerStatement::getStatementRegisterDate,tenantCustomerStatementQueryParam.getStatementRegisterDateStart() == null ? null: DateUtil.beginOfDay(tenantCustomerStatementQueryParam.getStatementRegisterDateStart()))
				.le(tenantCustomerStatementQueryParam.getStatementRegisterDateEnd() != null, TenantCustomerStatement::getStatementRegisterDate,tenantCustomerStatementQueryParam.getStatementRegisterDateEnd() == null ? null: DateUtil.endOfDay(tenantCustomerStatementQueryParam.getStatementRegisterDateEnd()))
				.eq(StringUtils.isNotEmpty(tenantCustomerStatementQueryParam.getStatementMemo()), TenantCustomerStatement::getStatementMemo, tenantCustomerStatementQueryParam.getStatementMemo())
				.eq(tenantCustomerStatementQueryParam.getAddTime() != null, TenantCustomerStatement::getAddTime, tenantCustomerStatementQueryParam.getAddTime())
				.ge(tenantCustomerStatementQueryParam.getAddTimeStart() != null, TenantCustomerStatement::getAddTime,tenantCustomerStatementQueryParam.getAddTimeStart() == null ? null: DateUtil.beginOfDay(tenantCustomerStatementQueryParam.getAddTimeStart()))
				.le(tenantCustomerStatementQueryParam.getAddTimeEnd() != null, TenantCustomerStatement::getAddTime,tenantCustomerStatementQueryParam.getAddTimeEnd() == null ? null: DateUtil.endOfDay(tenantCustomerStatementQueryParam.getAddTimeEnd()))
				.eq(tenantCustomerStatementQueryParam.getUpdateTime() != null, TenantCustomerStatement::getUpdateTime, tenantCustomerStatementQueryParam.getUpdateTime())
				.ge(tenantCustomerStatementQueryParam.getUpdateTimeStart() != null, TenantCustomerStatement::getUpdateTime,tenantCustomerStatementQueryParam.getUpdateTimeStart() == null ? null: DateUtil.beginOfDay(tenantCustomerStatementQueryParam.getUpdateTimeStart()))
				.le(tenantCustomerStatementQueryParam.getUpdateTimeEnd() != null, TenantCustomerStatement::getUpdateTime,tenantCustomerStatementQueryParam.getUpdateTimeEnd() == null ? null: DateUtil.endOfDay(tenantCustomerStatementQueryParam.getUpdateTimeEnd()))
				;

		String[] queryCols = tenantCustomerStatementQueryParam.getQueryCol();
		String[] queryTypes = tenantCustomerStatementQueryParam.getQueryType();
		String[] queryValues = tenantCustomerStatementQueryParam.getQueryValue();
		if (queryCols != null && queryCols.length > 0) {
			for (int i = 0; i < queryCols.length; i++) {
				queryWrapperTenantCustomerStatement.lambda()//
						.apply("=".equals(queryTypes[i]), queryCols[i] + "={0}", queryValues[i])//
						.apply("like".equals(queryTypes[i]), queryCols[i] + " like CONCAT('%',{0},'%')", queryValues[i])//
				;
			}
		}
		
		List<TenantCustomerStatement> tenantCustomerStatementList = tenantCustomerStatementService.list(queryWrapperTenantCustomerStatement);

		List<TenantCustomerStatementVo> tenantCustomerStatementVoList = tenantCustomerStatementList.stream()//
				 .map(e -> entity2vo(e))//
				 .collect(Collectors.toList());

		return tenantCustomerStatementVoList;
	}
	
	@ApiOperation(value = "根据参数查询用户结算信息数量")
	@RequestMapping(value = "/tenant-customer-statements/count", method = RequestMethod.GET)
	public int count(@RequestBody TenantCustomerStatementQueryParam tenantCustomerStatementQueryParam) {
		QueryWrapper<TenantCustomerStatement> queryWrapperTenantCustomerStatement = new QueryWrapper<TenantCustomerStatement>();
		queryWrapperTenantCustomerStatement.lambda()
				.eq(StringUtils.isNotEmpty(tenantCustomerStatementQueryParam.getId()), TenantCustomerStatement::getId, tenantCustomerStatementQueryParam.getId())
				.eq(StringUtils.isNotEmpty(tenantCustomerStatementQueryParam.getTenantId()), TenantCustomerStatement::getTenantId, tenantCustomerStatementQueryParam.getTenantId())
				.eq(StringUtils.isNotEmpty(tenantCustomerStatementQueryParam.getCustomerId()), TenantCustomerStatement::getCustomerId, tenantCustomerStatementQueryParam.getCustomerId())
				.eq(StringUtils.isNotEmpty(tenantCustomerStatementQueryParam.getCustomerCode()), TenantCustomerStatement::getCustomerCode, tenantCustomerStatementQueryParam.getCustomerCode())
				.eq(tenantCustomerStatementQueryParam.getStatementMethod() != null, TenantCustomerStatement::getStatementMethod, tenantCustomerStatementQueryParam.getStatementMethod())
				.eq(StringUtils.isNotEmpty(tenantCustomerStatementQueryParam.getStatementBankId()), TenantCustomerStatement::getStatementBankId, tenantCustomerStatementQueryParam.getStatementBankId())
				.eq(StringUtils.isNotEmpty(tenantCustomerStatementQueryParam.getEntrustAgreementNo()), TenantCustomerStatement::getEntrustAgreementNo, tenantCustomerStatementQueryParam.getEntrustAgreementNo())
				.eq(StringUtils.isNotEmpty(tenantCustomerStatementQueryParam.getEntrustCode()), TenantCustomerStatement::getEntrustCode, tenantCustomerStatementQueryParam.getEntrustCode())
				.eq(tenantCustomerStatementQueryParam.getStatementAccountBankId() != null, TenantCustomerStatement::getStatementAccountBankId, tenantCustomerStatementQueryParam.getStatementAccountBankId())
				.eq(StringUtils.isNotEmpty(tenantCustomerStatementQueryParam.getStatementAccountName()), TenantCustomerStatement::getStatementAccountName, tenantCustomerStatementQueryParam.getStatementAccountName())
				.eq(StringUtils.isNotEmpty(tenantCustomerStatementQueryParam.getStatementAccountNo()), TenantCustomerStatement::getStatementAccountNo, tenantCustomerStatementQueryParam.getStatementAccountNo())
				.eq(tenantCustomerStatementQueryParam.getStatementRegisterDate() != null, TenantCustomerStatement::getStatementRegisterDate, tenantCustomerStatementQueryParam.getStatementRegisterDate())
				.ge(tenantCustomerStatementQueryParam.getStatementRegisterDateStart() != null, TenantCustomerStatement::getStatementRegisterDate,tenantCustomerStatementQueryParam.getStatementRegisterDateStart() == null ? null: DateUtil.beginOfDay(tenantCustomerStatementQueryParam.getStatementRegisterDateStart()))
				.le(tenantCustomerStatementQueryParam.getStatementRegisterDateEnd() != null, TenantCustomerStatement::getStatementRegisterDate,tenantCustomerStatementQueryParam.getStatementRegisterDateEnd() == null ? null: DateUtil.endOfDay(tenantCustomerStatementQueryParam.getStatementRegisterDateEnd()))
				.eq(StringUtils.isNotEmpty(tenantCustomerStatementQueryParam.getStatementMemo()), TenantCustomerStatement::getStatementMemo, tenantCustomerStatementQueryParam.getStatementMemo())
				.eq(tenantCustomerStatementQueryParam.getAddTime() != null, TenantCustomerStatement::getAddTime, tenantCustomerStatementQueryParam.getAddTime())
				.ge(tenantCustomerStatementQueryParam.getAddTimeStart() != null, TenantCustomerStatement::getAddTime,tenantCustomerStatementQueryParam.getAddTimeStart() == null ? null: DateUtil.beginOfDay(tenantCustomerStatementQueryParam.getAddTimeStart()))
				.le(tenantCustomerStatementQueryParam.getAddTimeEnd() != null, TenantCustomerStatement::getAddTime,tenantCustomerStatementQueryParam.getAddTimeEnd() == null ? null: DateUtil.endOfDay(tenantCustomerStatementQueryParam.getAddTimeEnd()))
				.eq(tenantCustomerStatementQueryParam.getUpdateTime() != null, TenantCustomerStatement::getUpdateTime, tenantCustomerStatementQueryParam.getUpdateTime())
				.ge(tenantCustomerStatementQueryParam.getUpdateTimeStart() != null, TenantCustomerStatement::getUpdateTime,tenantCustomerStatementQueryParam.getUpdateTimeStart() == null ? null: DateUtil.beginOfDay(tenantCustomerStatementQueryParam.getUpdateTimeStart()))
				.le(tenantCustomerStatementQueryParam.getUpdateTimeEnd() != null, TenantCustomerStatement::getUpdateTime,tenantCustomerStatementQueryParam.getUpdateTimeEnd() == null ? null: DateUtil.endOfDay(tenantCustomerStatementQueryParam.getUpdateTimeEnd()))
				;

		String[] queryCols = tenantCustomerStatementQueryParam.getQueryCol();
		String[] queryTypes = tenantCustomerStatementQueryParam.getQueryType();
		String[] queryValues = tenantCustomerStatementQueryParam.getQueryValue();
		if (queryCols != null && queryCols.length > 0) {
			for (int i = 0; i < queryCols.length; i++) {
				queryWrapperTenantCustomerStatement.lambda()//
						.apply("=".equals(queryTypes[i]), queryCols[i] + "={0}", queryValues[i])//
						.apply("like".equals(queryTypes[i]), queryCols[i] + " like CONCAT('%',{0},'%')", queryValues[i])//
				;
			}
		}
		
		int count = tenantCustomerStatementService.count(queryWrapperTenantCustomerStatement);

		return count;
	}
	
	@ApiOperation(value = "根据参数查询用户结算信息列表")
	@RequestMapping(value = "/tenant-customer-statements", method = RequestMethod.GET)
	public Page<TenantCustomerStatementVo> page(@RequestBody TenantCustomerStatementQueryParam tenantCustomerStatementQueryParam,
			@RequestParam(value = "page", defaultValue = "1") int page, //
			@RequestParam(value = "rows", defaultValue = "10") int rows, //
			@RequestParam(value = "sort", required = false) String sort, // 排序列字段名
			@RequestParam(value = "order", required = false) String order // 可以是 'asc' 或者 'desc'，默认值是 'asc'
	) {
		IPage<TenantCustomerStatement> pageTenantCustomerStatement = new Page<TenantCustomerStatement>(page, rows);
		QueryWrapper<TenantCustomerStatement> queryWrapperTenantCustomerStatement = new QueryWrapper<TenantCustomerStatement>();
		queryWrapperTenantCustomerStatement.orderBy(StringUtils.isNotBlank(sort), "asc".equals(order), sort);
		queryWrapperTenantCustomerStatement.lambda()
				.eq(StringUtils.isNotEmpty(tenantCustomerStatementQueryParam.getId()), TenantCustomerStatement::getId, tenantCustomerStatementQueryParam.getId())
				.eq(StringUtils.isNotEmpty(tenantCustomerStatementQueryParam.getTenantId()), TenantCustomerStatement::getTenantId, tenantCustomerStatementQueryParam.getTenantId())
				.eq(StringUtils.isNotEmpty(tenantCustomerStatementQueryParam.getCustomerId()), TenantCustomerStatement::getCustomerId, tenantCustomerStatementQueryParam.getCustomerId())
				.eq(StringUtils.isNotEmpty(tenantCustomerStatementQueryParam.getCustomerCode()), TenantCustomerStatement::getCustomerCode, tenantCustomerStatementQueryParam.getCustomerCode())
				.eq(tenantCustomerStatementQueryParam.getStatementMethod() != null, TenantCustomerStatement::getStatementMethod, tenantCustomerStatementQueryParam.getStatementMethod())
				.eq(StringUtils.isNotEmpty(tenantCustomerStatementQueryParam.getStatementBankId()), TenantCustomerStatement::getStatementBankId, tenantCustomerStatementQueryParam.getStatementBankId())
				.eq(StringUtils.isNotEmpty(tenantCustomerStatementQueryParam.getEntrustAgreementNo()), TenantCustomerStatement::getEntrustAgreementNo, tenantCustomerStatementQueryParam.getEntrustAgreementNo())
				.eq(StringUtils.isNotEmpty(tenantCustomerStatementQueryParam.getEntrustCode()), TenantCustomerStatement::getEntrustCode, tenantCustomerStatementQueryParam.getEntrustCode())
				.eq(tenantCustomerStatementQueryParam.getStatementAccountBankId() != null, TenantCustomerStatement::getStatementAccountBankId, tenantCustomerStatementQueryParam.getStatementAccountBankId())
				.eq(StringUtils.isNotEmpty(tenantCustomerStatementQueryParam.getStatementAccountName()), TenantCustomerStatement::getStatementAccountName, tenantCustomerStatementQueryParam.getStatementAccountName())
				.eq(StringUtils.isNotEmpty(tenantCustomerStatementQueryParam.getStatementAccountNo()), TenantCustomerStatement::getStatementAccountNo, tenantCustomerStatementQueryParam.getStatementAccountNo())
				.eq(tenantCustomerStatementQueryParam.getStatementRegisterDate() != null, TenantCustomerStatement::getStatementRegisterDate, tenantCustomerStatementQueryParam.getStatementRegisterDate())
				.ge(tenantCustomerStatementQueryParam.getStatementRegisterDateStart() != null, TenantCustomerStatement::getStatementRegisterDate,tenantCustomerStatementQueryParam.getStatementRegisterDateStart() == null ? null: DateUtil.beginOfDay(tenantCustomerStatementQueryParam.getStatementRegisterDateStart()))
				.le(tenantCustomerStatementQueryParam.getStatementRegisterDateEnd() != null, TenantCustomerStatement::getStatementRegisterDate,tenantCustomerStatementQueryParam.getStatementRegisterDateEnd() == null ? null: DateUtil.endOfDay(tenantCustomerStatementQueryParam.getStatementRegisterDateEnd()))
				.eq(StringUtils.isNotEmpty(tenantCustomerStatementQueryParam.getStatementMemo()), TenantCustomerStatement::getStatementMemo, tenantCustomerStatementQueryParam.getStatementMemo())
				.eq(tenantCustomerStatementQueryParam.getAddTime() != null, TenantCustomerStatement::getAddTime, tenantCustomerStatementQueryParam.getAddTime())
				.ge(tenantCustomerStatementQueryParam.getAddTimeStart() != null, TenantCustomerStatement::getAddTime,tenantCustomerStatementQueryParam.getAddTimeStart() == null ? null: DateUtil.beginOfDay(tenantCustomerStatementQueryParam.getAddTimeStart()))
				.le(tenantCustomerStatementQueryParam.getAddTimeEnd() != null, TenantCustomerStatement::getAddTime,tenantCustomerStatementQueryParam.getAddTimeEnd() == null ? null: DateUtil.endOfDay(tenantCustomerStatementQueryParam.getAddTimeEnd()))
				.eq(tenantCustomerStatementQueryParam.getUpdateTime() != null, TenantCustomerStatement::getUpdateTime, tenantCustomerStatementQueryParam.getUpdateTime())
				.ge(tenantCustomerStatementQueryParam.getUpdateTimeStart() != null, TenantCustomerStatement::getUpdateTime,tenantCustomerStatementQueryParam.getUpdateTimeStart() == null ? null: DateUtil.beginOfDay(tenantCustomerStatementQueryParam.getUpdateTimeStart()))
				.le(tenantCustomerStatementQueryParam.getUpdateTimeEnd() != null, TenantCustomerStatement::getUpdateTime,tenantCustomerStatementQueryParam.getUpdateTimeEnd() == null ? null: DateUtil.endOfDay(tenantCustomerStatementQueryParam.getUpdateTimeEnd()))
				;

		String[] queryCols = tenantCustomerStatementQueryParam.getQueryCol();
		String[] queryTypes = tenantCustomerStatementQueryParam.getQueryType();
		String[] queryValues = tenantCustomerStatementQueryParam.getQueryValue();
		if (queryCols != null && queryCols.length > 0) {
			for (int i = 0; i < queryCols.length; i++) {
				queryWrapperTenantCustomerStatement.lambda()//
						.apply("=".equals(queryTypes[i]), queryCols[i] + "={0}", queryValues[i])//
						.apply("like".equals(queryTypes[i]), queryCols[i] + " like CONCAT('%',{0},'%')", queryValues[i])//
				;
			}
		}
		
		IPage<TenantCustomerStatement> tenantCustomerStatementPage = tenantCustomerStatementService.page(pageTenantCustomerStatement, queryWrapperTenantCustomerStatement);

		Page<TenantCustomerStatementVo> tenantCustomerStatementVoPage = new Page<TenantCustomerStatementVo>(page, rows);
		tenantCustomerStatementVoPage.setCurrent(tenantCustomerStatementPage.getCurrent());
		tenantCustomerStatementVoPage.setPages(tenantCustomerStatementPage.getPages());
		tenantCustomerStatementVoPage.setSize(tenantCustomerStatementPage.getSize());
		tenantCustomerStatementVoPage.setTotal(tenantCustomerStatementPage.getTotal());
		tenantCustomerStatementVoPage.setRecords(tenantCustomerStatementPage.getRecords().stream()//
				.map(e -> entity2vo(e))//
				.collect(Collectors.toList()));

		return tenantCustomerStatementVoPage;
	}
	
	@ApiOperation(value = "新增用户结算信息")
	@RequestMapping(value = "/tenant-customer-statements", method = RequestMethod.POST)
	public String save(@RequestBody TenantCustomerStatementAddParam tenantCustomerStatementAddParam) {
		return tenantCustomerStatementService.save(tenantCustomerStatementAddParam);
	}

	@ApiOperation(value = "更新用户结算信息全部信息")
	@RequestMapping(value = "/tenant-customer-statements/{id}", method = RequestMethod.PUT)
	public boolean updateById(@PathVariable("id") String id, @RequestBody TenantCustomerStatementUpdateParam tenantCustomerStatementUpdateParam) {
		tenantCustomerStatementUpdateParam.setId(id);
		return tenantCustomerStatementService.updateById(tenantCustomerStatementUpdateParam);
	}

	@ApiOperation(value = "根据ID删除用户结算信息")
	@RequestMapping(value = "/tenant-customer-statements/{id}", method = RequestMethod.DELETE)
	public CommonResult<Object> removeById(@PathVariable("id") String id) {
		boolean success = tenantCustomerStatementService.removeById(id);
		return success ? CommonResult.success(success) : CommonResult.failed();
	}

	private TenantCustomerStatementVo entity2vo(TenantCustomerStatement tenantCustomerStatement) {
		if (tenantCustomerStatement == null) {
			return null;
		}

		TenantCustomerStatementVo tenantCustomerStatementVo = TranslateUtil.translate(tenantCustomerStatement, TenantCustomerStatementVo.class);
		if (StringUtils.isEmpty(tenantCustomerStatementVo.getTenantName())) {
			TenantInfo tenantInfo = tenantInfoService.getDictionaryById(tenantCustomerStatement.getTenantId());
			if (tenantInfo != null) {
				tenantCustomerStatementVo.setTenantName(tenantInfo.getTenantName());
			}
		}
		return tenantCustomerStatementVo;
	}

}
