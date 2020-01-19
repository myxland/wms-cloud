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
import com.zlsrj.wms.api.client.service.TenantPaymentDetailClientService;
import com.zlsrj.wms.api.client.service.TenantInfoClientService;
import com.zlsrj.wms.api.dto.TenantPaymentDetailQueryParam;
import com.zlsrj.wms.api.entity.TenantPaymentDetail;
import com.zlsrj.wms.api.vo.TenantPaymentDetailVo;
import com.zlsrj.wms.api.vo.TenantInfoVo;
import com.zlsrj.wms.common.api.CommonPage;
import com.zlsrj.wms.common.api.CommonResult;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

@Api(value = "实收明细账，记录本次所销账的欠费明细情况", tags = { "实收明细账，记录本次所销账的欠费明细情况操作接口" })
@Controller
@RequestMapping("/tenantPaymentDetail")
@Slf4j
public class TenantPaymentDetailController {

	@Autowired
	private TenantPaymentDetailClientService tenantPaymentDetailClientService;
	@Autowired
	private TenantInfoClientService tenantInfoClientService;

	@ApiOperation(value = "根据参数查询实收明细账，记录本次所销账的欠费明细情况列表")
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	@ResponseBody
	public CommonResult<CommonPage<TenantPaymentDetailVo>> list(TenantPaymentDetailQueryParam tenantPaymentDetailQueryParam, int pageNum,
			int pageSize) {
		Page<TenantPaymentDetailVo> tenantPaymentDetailVoPage = tenantPaymentDetailClientService.page(tenantPaymentDetailQueryParam, pageNum, pageSize, "id", "desc");
		tenantPaymentDetailVoPage.getRecords().stream().forEach(v->wrappperVo(v));

		CommonPage<TenantPaymentDetailVo> tenantPaymentDetailCommonPage = CommonPage.restPage(tenantPaymentDetailVoPage);

		return CommonResult.success(tenantPaymentDetailCommonPage);
	}

	@ApiOperation(value = "新增实收明细账，记录本次所销账的欠费明细情况")
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	@ResponseBody
	public CommonResult<TenantPaymentDetailVo> create(@RequestBody TenantPaymentDetail tenantPaymentDetail) {
		TenantPaymentDetailVo tenantPaymentDetailVo = tenantPaymentDetailClientService.save(tenantPaymentDetail);

		return CommonResult.success(tenantPaymentDetailVo);
	}

	@ApiOperation(value = "根据ID查询实收明细账，记录本次所销账的欠费明细情况")
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ResponseBody
	public CommonResult<TenantPaymentDetailVo> getById(@PathVariable("id") Long id) {
		TenantPaymentDetailVo tenantPaymentDetailVo = tenantPaymentDetailClientService.getById(id);
		wrappperVo(tenantPaymentDetailVo);

		return CommonResult.success(tenantPaymentDetailVo);
	}

	@ApiOperation(value = "根据参数更新实收明细账，记录本次所销账的欠费明细情况信息")
	@RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
	@ResponseBody
	public CommonResult<TenantPaymentDetailVo> getById(@RequestBody TenantPaymentDetail tenantPaymentDetail) {
		Long id = tenantPaymentDetail.getId();
		TenantPaymentDetailVo tenantPaymentDetailVo = tenantPaymentDetailClientService.updatePatchById(id, tenantPaymentDetail);
		wrappperVo(tenantPaymentDetailVo);

		return CommonResult.success(tenantPaymentDetailVo);
	}
	
	@ApiOperation(value = "根据ID删除实收明细账，记录本次所销账的欠费明细情况")
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
	@ResponseBody
	public CommonResult<Object> removeById(@PathVariable("id") Long id) {
		CommonResult<Object> commonResult = tenantPaymentDetailClientService.removeById(id);

		return commonResult;
	}

	private void wrappperVo(TenantPaymentDetailVo tenantPaymentDetailVo) {
		if (StringUtils.isEmpty(tenantPaymentDetailVo.getTenantName())) {
			TenantInfoVo tenantInfoVo = tenantInfoClientService.getById(tenantPaymentDetailVo.getTenantId());
			if (tenantInfoVo != null) {
				tenantPaymentDetailVo.setTenantName(tenantInfoVo.getTenantName());
			}
		}
	}

}
