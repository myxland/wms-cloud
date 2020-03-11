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
import com.zlsrj.wms.api.client.service.TenantConsumptionBillClientService;
import com.zlsrj.wms.api.client.service.TenantInfoClientService;
import com.zlsrj.wms.api.dto.TenantConsumptionBillQueryParam;
import com.zlsrj.wms.api.entity.TenantConsumptionBill;
import com.zlsrj.wms.api.vo.TenantConsumptionBillVo;
import com.zlsrj.wms.api.vo.TenantInfoVo;
import com.zlsrj.wms.common.api.CommonPage;
import com.zlsrj.wms.common.api.CommonResult;

import cn.hutool.core.date.DateUtil;
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
	public CommonResult<CommonPage<TenantConsumptionBillVo>> list(TenantConsumptionBillQueryParam tenantConsumptionBillQueryParam, int pageNum,
			int pageSize) {
		Page<TenantConsumptionBillVo> tenantConsumptionBillVoPage = tenantConsumptionBillClientService.page(tenantConsumptionBillQueryParam, pageNum, pageSize, "id", "desc");
		tenantConsumptionBillVoPage.getRecords().stream().forEach(v->wrappperVo(v));

		CommonPage<TenantConsumptionBillVo> tenantConsumptionBillCommonPage = CommonPage.restPage(tenantConsumptionBillVoPage);

		return CommonResult.success(tenantConsumptionBillCommonPage);
	}

	@ApiOperation(value = "新增租户账单")
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	@ResponseBody
	public CommonResult<TenantConsumptionBillVo> create(@RequestBody TenantConsumptionBill tenantConsumptionBill) {
		TenantConsumptionBillVo tenantConsumptionBillVo = tenantConsumptionBillClientService.save(tenantConsumptionBill);

		return CommonResult.success(tenantConsumptionBillVo);
	}
	
	@ApiOperation(value = "根据租户充值订单创建新增租户账单")
	@RequestMapping(value = "/createByTenantConsume", method = RequestMethod.POST)
	@ResponseBody
	public CommonResult<TenantConsumptionBillVo> createByTenantConsume(@RequestBody TenantConsumptionBill tenantConsumptionBill) {
		tenantConsumptionBill.setConsumptionBillType(1);
		tenantConsumptionBill.setConsumptionBillName("账户充值");
		tenantConsumptionBill.setConsumptionBillRemark("账户充值 "+DateUtil.now());
		
		TenantConsumptionBillVo tenantConsumptionBillVo = tenantConsumptionBillClientService.save(tenantConsumptionBill);

		return CommonResult.success(tenantConsumptionBillVo);
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
	public CommonResult<TenantConsumptionBillVo> getById(@RequestBody TenantConsumptionBill tenantConsumptionBill) {
		String id = tenantConsumptionBill.getId();
		TenantConsumptionBillVo tenantConsumptionBillVo = tenantConsumptionBillClientService.updatePatchById(id, tenantConsumptionBill);
		wrappperVo(tenantConsumptionBillVo);

		return CommonResult.success(tenantConsumptionBillVo);
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
			TenantInfoVo tenantInfoVo = tenantInfoClientService.getById(tenantConsumptionBillVo.getTenantId());
			if (tenantInfoVo != null) {
				tenantConsumptionBillVo.setTenantName(tenantInfoVo.getTenantName());
			}
		}
	}

}
