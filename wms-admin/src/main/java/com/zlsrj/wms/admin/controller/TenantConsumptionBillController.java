package com.zlsrj.wms.admin.controller;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zlsrj.wms.api.client.service.TenantConsumptionBillClientService;
import com.zlsrj.wms.api.client.service.TenantInfoClientService;
import com.zlsrj.wms.api.dto.TenantConsumptionBillAddParam;
import com.zlsrj.wms.api.dto.TenantConsumptionBillQueryParam;
import com.zlsrj.wms.api.dto.TenantConsumptionBillUpdateParam;
import com.zlsrj.wms.api.vo.TenantConsumptionBillVo;
import com.zlsrj.wms.api.vo.TenantInfoVo;
import com.zlsrj.wms.common.api.CommonResult;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

@Api(value = "租户账单", tags = { "租户账单操作接口" })
@Controller
@RequestMapping("/tenantConsumptionBill")
@Slf4j
public class TenantConsumptionBillController {

	@Autowired
	private TenantConsumptionBillClientService tenantConsumptionBillClientService;
	@Autowired
	private TenantInfoClientService tenantInfoClientService;

	@ApiOperation(value = "根据参数查询租户账单列表")
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	@ResponseBody
	public CommonResult<List<TenantConsumptionBillVo>> list(TenantConsumptionBillQueryParam tenantConsumptionBillQueryParam) {
		List<TenantConsumptionBillVo> tenantConsumptionBillVoList = tenantConsumptionBillClientService.list(tenantConsumptionBillQueryParam);
		tenantConsumptionBillVoList.stream().forEach(v->wrappperVo(v));

		return CommonResult.success(tenantConsumptionBillVoList);
	}

	@ApiOperation(value = "新增租户账单")
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	@ResponseBody
	public CommonResult<Object> create(@RequestBody TenantConsumptionBillAddParam tenantConsumptionBillAddParam) {
		String id = tenantConsumptionBillClientService.save(tenantConsumptionBillAddParam);

		return CommonResult.success(id);
	}

	@ApiOperation(value = "根据ID查询租户账单")
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ResponseBody
	public CommonResult<TenantConsumptionBillVo> getById(@PathVariable("id") String id) {
		TenantConsumptionBillVo tenantConsumptionBillVo = tenantConsumptionBillClientService.getById(id);
		wrappperVo(tenantConsumptionBillVo);

		return CommonResult.success(tenantConsumptionBillVo);
	}

	@ApiOperation(value = "根据参数更新租户账单信息")
	@RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
	@ResponseBody
	public CommonResult<Object> updateById(@PathVariable("id") String id,@RequestBody TenantConsumptionBillUpdateParam tenantConsumptionBillUpdateParam) {
		boolean success = tenantConsumptionBillClientService.updateById(id, tenantConsumptionBillUpdateParam);

		return CommonResult.success(success);
	}
	
	@ApiOperation(value = "根据ID删除租户账单")
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
	@ResponseBody
	public CommonResult<Object> removeById(@PathVariable("id") String id) {
		CommonResult<Object> commonResult = tenantConsumptionBillClientService.removeById(id);

		return commonResult;
	}

	private void wrappperVo(TenantConsumptionBillVo tenantConsumptionBillVo) {
		if (StringUtils.isEmpty(tenantConsumptionBillVo.getTenantName())) {
			TenantInfoVo tenantInfoVo = tenantInfoClientService.getDictionaryById(tenantConsumptionBillVo.getTenantId());
			if (tenantInfoVo != null) {
				tenantConsumptionBillVo.setTenantName(tenantInfoVo.getTenantName());
			}
		}
	}

}
