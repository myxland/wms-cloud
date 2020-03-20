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

import com.zlsrj.wms.api.client.service.TenantPriceStepClientService;
import com.zlsrj.wms.api.client.service.TenantInfoClientService;
import com.zlsrj.wms.api.dto.TenantPriceStepAddParam;
import com.zlsrj.wms.api.dto.TenantPriceStepQueryParam;
import com.zlsrj.wms.api.dto.TenantPriceStepUpdateParam;
import com.zlsrj.wms.api.vo.TenantPriceStepVo;
import com.zlsrj.wms.api.vo.TenantInfoVo;
import com.zlsrj.wms.common.api.CommonResult;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

@Api(value = "阶梯明细", tags = { "阶梯明细操作接口" })
@Controller
@RequestMapping("/tenantPriceStep")
@Slf4j
public class TenantPriceStepController {

	@Autowired
	private TenantPriceStepClientService tenantPriceStepClientService;
	@Autowired
	private TenantInfoClientService tenantInfoClientService;

	@ApiOperation(value = "根据参数查询阶梯明细列表")
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	@ResponseBody
	public CommonResult<List<TenantPriceStepVo>> list(TenantPriceStepQueryParam tenantPriceStepQueryParam) {
		List<TenantPriceStepVo> tenantPriceStepVoList = tenantPriceStepClientService.list(tenantPriceStepQueryParam);
		tenantPriceStepVoList.stream().forEach(v->wrappperVo(v));

		return CommonResult.success(tenantPriceStepVoList);
	}

	@ApiOperation(value = "新增阶梯明细")
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	@ResponseBody
	public CommonResult<Object> create(@RequestBody TenantPriceStepAddParam tenantPriceStepAddParam) {
		String id = tenantPriceStepClientService.save(tenantPriceStepAddParam);

		return CommonResult.success(id);
	}

	@ApiOperation(value = "根据ID查询阶梯明细")
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ResponseBody
	public CommonResult<TenantPriceStepVo> getById(@PathVariable("id") String id) {
		TenantPriceStepVo tenantPriceStepVo = tenantPriceStepClientService.getById(id);
		wrappperVo(tenantPriceStepVo);

		return CommonResult.success(tenantPriceStepVo);
	}

	@ApiOperation(value = "根据参数更新阶梯明细信息")
	@RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
	@ResponseBody
	public CommonResult<Object> updateById(@PathVariable("id") String id,@RequestBody TenantPriceStepUpdateParam tenantPriceStepUpdateParam) {
		boolean success = tenantPriceStepClientService.updateById(id, tenantPriceStepUpdateParam);

		return CommonResult.success(success);
	}
	
	@ApiOperation(value = "根据ID删除阶梯明细")
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
	@ResponseBody
	public CommonResult<Object> removeById(@PathVariable("id") String id) {
		CommonResult<Object> commonResult = tenantPriceStepClientService.removeById(id);

		return commonResult;
	}

	private void wrappperVo(TenantPriceStepVo tenantPriceStepVo) {
		if (StringUtils.isEmpty(tenantPriceStepVo.getTenantName())) {
			TenantInfoVo tenantInfoVo = tenantInfoClientService.getDictionaryById(tenantPriceStepVo.getTenantId());
			if (tenantInfoVo != null) {
				tenantPriceStepVo.setTenantName(tenantInfoVo.getTenantName());
			}
		}
	}

}
