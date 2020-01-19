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
import com.zlsrj.wms.api.client.service.TenantPriceStepClientService;
import com.zlsrj.wms.api.client.service.TenantInfoClientService;
import com.zlsrj.wms.api.dto.TenantPriceStepQueryParam;
import com.zlsrj.wms.api.entity.TenantPriceStep;
import com.zlsrj.wms.api.vo.TenantPriceStepVo;
import com.zlsrj.wms.api.vo.TenantInfoVo;
import com.zlsrj.wms.common.api.CommonPage;
import com.zlsrj.wms.common.api.CommonResult;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

@Api(value = "水价阶梯", tags = { "水价阶梯操作接口" })
@Controller
@RequestMapping("/tenantPriceStep")
@Slf4j
public class TenantPriceStepController {

	@Autowired
	private TenantPriceStepClientService tenantPriceStepClientService;
	@Autowired
	private TenantInfoClientService tenantInfoClientService;

	@ApiOperation(value = "根据参数查询水价阶梯列表")
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	@ResponseBody
	public CommonResult<CommonPage<TenantPriceStepVo>> list(TenantPriceStepQueryParam tenantPriceStepQueryParam, int pageNum,
			int pageSize) {
		Page<TenantPriceStepVo> tenantPriceStepVoPage = tenantPriceStepClientService.page(tenantPriceStepQueryParam, pageNum, pageSize, "id", "desc");
		tenantPriceStepVoPage.getRecords().stream().forEach(v->wrappperVo(v));

		CommonPage<TenantPriceStepVo> tenantPriceStepCommonPage = CommonPage.restPage(tenantPriceStepVoPage);

		return CommonResult.success(tenantPriceStepCommonPage);
	}

	@ApiOperation(value = "新增水价阶梯")
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	@ResponseBody
	public CommonResult<TenantPriceStepVo> create(@RequestBody TenantPriceStep tenantPriceStep) {
		TenantPriceStepVo tenantPriceStepVo = tenantPriceStepClientService.save(tenantPriceStep);

		return CommonResult.success(tenantPriceStepVo);
	}

	@ApiOperation(value = "根据ID查询水价阶梯")
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ResponseBody
	public CommonResult<TenantPriceStepVo> getById(@PathVariable("id") Long id) {
		TenantPriceStepVo tenantPriceStepVo = tenantPriceStepClientService.getById(id);
		wrappperVo(tenantPriceStepVo);

		return CommonResult.success(tenantPriceStepVo);
	}

	@ApiOperation(value = "根据参数更新水价阶梯信息")
	@RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
	@ResponseBody
	public CommonResult<TenantPriceStepVo> getById(@RequestBody TenantPriceStep tenantPriceStep) {
		Long id = tenantPriceStep.getId();
		TenantPriceStepVo tenantPriceStepVo = tenantPriceStepClientService.updatePatchById(id, tenantPriceStep);
		wrappperVo(tenantPriceStepVo);

		return CommonResult.success(tenantPriceStepVo);
	}
	
	@ApiOperation(value = "根据ID删除水价阶梯")
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
	@ResponseBody
	public CommonResult<Object> removeById(@PathVariable("id") Long id) {
		CommonResult<Object> commonResult = tenantPriceStepClientService.removeById(id);

		return commonResult;
	}

	private void wrappperVo(TenantPriceStepVo tenantPriceStepVo) {
		if (StringUtils.isEmpty(tenantPriceStepVo.getTenantName())) {
			TenantInfoVo tenantInfoVo = tenantInfoClientService.getById(tenantPriceStepVo.getTenantId());
			if (tenantInfoVo != null) {
				tenantPriceStepVo.setTenantName(tenantInfoVo.getTenantName());
			}
		}
	}

}
