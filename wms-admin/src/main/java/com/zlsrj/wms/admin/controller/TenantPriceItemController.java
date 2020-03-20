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

import com.zlsrj.wms.api.client.service.TenantInfoClientService;
import com.zlsrj.wms.api.client.service.TenantPriceDetailClientService;
import com.zlsrj.wms.api.client.service.TenantPriceItemClientService;
import com.zlsrj.wms.api.dto.TenantPriceDetailQueryParam;
import com.zlsrj.wms.api.dto.TenantPriceItemAddParam;
import com.zlsrj.wms.api.dto.TenantPriceItemQueryParam;
import com.zlsrj.wms.api.dto.TenantPriceItemUpdateParam;
import com.zlsrj.wms.api.vo.TenantInfoVo;
import com.zlsrj.wms.api.vo.TenantPriceDetailVo;
import com.zlsrj.wms.api.vo.TenantPriceItemVo;
import com.zlsrj.wms.common.api.CommonResult;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

@Api(value = "费用项目", tags = { "费用项目操作接口" })
@Controller
@RequestMapping("/tenantPriceItem")
@Slf4j
public class TenantPriceItemController {

	@Autowired
	private TenantPriceItemClientService tenantPriceItemClientService;
	@Autowired
	private TenantInfoClientService tenantInfoClientService;
	@Autowired
	private TenantPriceDetailClientService tenantPriceDetailClientService;

	@ApiOperation(value = "根据参数查询费用项目列表")
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	@ResponseBody
	public CommonResult<List<TenantPriceItemVo>> list(TenantPriceItemQueryParam tenantPriceItemQueryParam) {
		List<TenantPriceItemVo> tenantPriceItemVoList = tenantPriceItemClientService.list(tenantPriceItemQueryParam);
		tenantPriceItemVoList.stream().forEach(v->wrappperVo(v));

		return CommonResult.success(tenantPriceItemVoList);
	}

	@ApiOperation(value = "新增费用项目")
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	@ResponseBody
	public CommonResult<Object> create(@RequestBody TenantPriceItemAddParam tenantPriceItemAddParam) {
		String id = tenantPriceItemClientService.save(tenantPriceItemAddParam);

		return CommonResult.success(id);
	}

	@ApiOperation(value = "根据ID查询费用项目")
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ResponseBody
	public CommonResult<TenantPriceItemVo> getById(@PathVariable("id") String id) {
		TenantPriceItemVo tenantPriceItemVo = tenantPriceItemClientService.getById(id);
		wrappperVo(tenantPriceItemVo);

		return CommonResult.success(tenantPriceItemVo);
	}

	@ApiOperation(value = "根据参数更新费用项目信息")
	@RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
	@ResponseBody
	public CommonResult<Object> updateById(@PathVariable("id") String id,@RequestBody TenantPriceItemUpdateParam tenantPriceItemUpdateParam) {
		boolean success = tenantPriceItemClientService.updateById(id, tenantPriceItemUpdateParam);

		return CommonResult.success(success);
	}
	
	@ApiOperation(value = "根据ID删除费用项目")
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
	@ResponseBody
	public CommonResult<Object> removeById(@PathVariable("id") String id) {
		//水价明细
		TenantPriceDetailQueryParam tenantPriceDetailQueryParam = new TenantPriceDetailQueryParam();
		tenantPriceDetailQueryParam.setPriceItemId(id);
		List<TenantPriceDetailVo> tenantPriceDetailVoList = tenantPriceDetailClientService.list(tenantPriceDetailQueryParam);
		if(tenantPriceDetailVoList!=null && tenantPriceDetailVoList.size()>0) {
			return CommonResult.failed("当前费用项目正在使用，不能进行删除");
		}
		
		
		CommonResult<Object> commonResult = tenantPriceItemClientService.removeById(id);

		return commonResult;
	}

	private void wrappperVo(TenantPriceItemVo tenantPriceItemVo) {
		if (StringUtils.isEmpty(tenantPriceItemVo.getTenantName())) {
			TenantInfoVo tenantInfoVo = tenantInfoClientService.getDictionaryById(tenantPriceItemVo.getTenantId());
			if (tenantInfoVo != null) {
				tenantPriceItemVo.setTenantName(tenantInfoVo.getTenantName());
			}
		}
	}

}
