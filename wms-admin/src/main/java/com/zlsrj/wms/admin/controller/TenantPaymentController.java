package com.zlsrj.wms.admin.controller;

import org.apache.commons.lang3.StringUtils;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zlsrj.wms.api.client.service.TenantPaymentClientService;
import com.zlsrj.wms.api.client.service.TenantInfoClientService;
import com.zlsrj.wms.api.dto.TenantPaymentQueryParam;
import com.zlsrj.wms.api.entity.TenantPayment;
import com.zlsrj.wms.api.vo.TenantPaymentVo;
import com.zlsrj.wms.api.vo.TenantInfoVo;
import com.zlsrj.wms.common.api.CommonPage;
import com.zlsrj.wms.common.api.CommonResult;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

@Api(value = "实收总账，记录每次缴费的总信息", tags = { "实收总账，记录每次缴费的总信息操作接口" })
@Controller
@RequestMapping("/tenantPayment")
@Slf4j
public class TenantPaymentController {

	@Autowired
	private TenantPaymentClientService tenantPaymentClientService;
	@Autowired
	private TenantInfoClientService tenantInfoClientService;

	@ApiOperation(value = "根据参数查询实收总账，记录每次缴费的总信息列表")
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	@ResponseBody
	public CommonResult<CommonPage<TenantPaymentVo>> list(TenantPaymentQueryParam tenantPaymentQueryParam, int pageNum,
			int pageSize) {
		Page<TenantPaymentVo> tenantPaymentVoPage = tenantPaymentClientService.page(tenantPaymentQueryParam, pageNum, pageSize, "id", "desc");
		tenantPaymentVoPage.getRecords().stream().forEach(v->wrappperVo(v));

		TenantPaymentVo tenantPaymentVoAggregation = tenantPaymentClientService.aggregation(tenantPaymentQueryParam);

		CommonPage<TenantPaymentVo> tenantPaymentCommonPage = CommonPage.restPage(tenantPaymentVoPage,tenantPaymentVoAggregation);
		return CommonResult.success(tenantPaymentCommonPage);
	}

	@ApiOperation(value = "新增实收总账，记录每次缴费的总信息")
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	@ResponseBody
	public CommonResult<TenantPaymentVo> create(@RequestBody TenantPayment tenantPayment) {
		TenantPaymentVo tenantPaymentVo = tenantPaymentClientService.save(tenantPayment);

		return CommonResult.success(tenantPaymentVo);
	}

	@ApiOperation(value = "根据ID查询实收总账，记录每次缴费的总信息")
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ResponseBody
	public CommonResult<TenantPaymentVo> getById(@PathVariable("id") Long id) {
		TenantPaymentVo tenantPaymentVo = tenantPaymentClientService.getById(id);
		wrappperVo(tenantPaymentVo);

		return CommonResult.success(tenantPaymentVo);
	}

	@ApiOperation(value = "根据参数更新实收总账，记录每次缴费的总信息信息")
	@RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
	@ResponseBody
	public CommonResult<TenantPaymentVo> getById(@RequestBody TenantPayment tenantPayment) {
		Long id = tenantPayment.getId();
		TenantPaymentVo tenantPaymentVo = tenantPaymentClientService.updatePatchById(id, tenantPayment);
		wrappperVo(tenantPaymentVo);

		return CommonResult.success(tenantPaymentVo);
	}
	
	@ApiOperation(value = "根据ID删除实收总账，记录每次缴费的总信息")
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
	@ResponseBody
	public CommonResult<Object> removeById(@PathVariable("id") Long id) {
		CommonResult<Object> commonResult = tenantPaymentClientService.removeById(id);

		return commonResult;
	}

	private void wrappperVo(TenantPaymentVo tenantPaymentVo) {
		if (StringUtils.isEmpty(tenantPaymentVo.getTenantName())) {
			TenantInfoVo tenantInfoVo = tenantInfoClientService.getById(tenantPaymentVo.getTenantId());
			if (tenantInfoVo != null) {
				tenantPaymentVo.setTenantName(tenantInfoVo.getTenantName());
			}
		}
	}

}
