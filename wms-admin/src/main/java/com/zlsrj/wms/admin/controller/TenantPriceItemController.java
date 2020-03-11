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
import com.zlsrj.wms.api.client.service.TenantPriceItemClientService;
import com.zlsrj.wms.api.client.service.TenantInfoClientService;
import com.zlsrj.wms.api.dto.TenantPriceItemQueryParam;
import com.zlsrj.wms.api.entity.TenantPriceItem;
import com.zlsrj.wms.api.vo.TenantPriceItemVo;
import com.zlsrj.wms.api.vo.TenantInfoVo;
import com.zlsrj.wms.common.api.CommonPage;
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

	@ApiOperation(value = "根据参数查询费用项目列表")
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	@ResponseBody
	public CommonResult<CommonPage<TenantPriceItemVo>> list(TenantPriceItemQueryParam tenantPriceItemQueryParam, int pageNum,
			int pageSize) {
		Page<TenantPriceItemVo> tenantPriceItemVoPage = tenantPriceItemClientService.page(tenantPriceItemQueryParam, pageNum, pageSize, "id", "desc");
		tenantPriceItemVoPage.getRecords().stream().forEach(v->wrappperVo(v));

		CommonPage<TenantPriceItemVo> tenantPriceItemCommonPage = CommonPage.restPage(tenantPriceItemVoPage);

		return CommonResult.success(tenantPriceItemCommonPage);
	}

	@ApiOperation(value = "新增费用项目")
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	@ResponseBody
	public CommonResult<TenantPriceItemVo> create(@RequestBody TenantPriceItem tenantPriceItem) {
		TenantPriceItemVo tenantPriceItemVo = tenantPriceItemClientService.save(tenantPriceItem);

		return CommonResult.success(tenantPriceItemVo);
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
	public CommonResult<TenantPriceItemVo> getById(@RequestBody TenantPriceItem tenantPriceItem) {
		String id = tenantPriceItem.getId();
		TenantPriceItemVo tenantPriceItemVo = tenantPriceItemClientService.updatePatchById(id, tenantPriceItem);
		wrappperVo(tenantPriceItemVo);

		return CommonResult.success(tenantPriceItemVo);
	}
	
	@ApiOperation(value = "根据ID删除费用项目")
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
	@ResponseBody
	public CommonResult<Object> removeById(@PathVariable("id") String id) {
		CommonResult<Object> commonResult = tenantPriceItemClientService.removeById(id);

		return commonResult;
	}

	private void wrappperVo(TenantPriceItemVo tenantPriceItemVo) {
		if (StringUtils.isEmpty(tenantPriceItemVo.getTenantName())) {
			TenantInfoVo tenantInfoVo = tenantInfoClientService.getById(tenantPriceItemVo.getTenantId());
			if (tenantInfoVo != null) {
				tenantPriceItemVo.setTenantName(tenantInfoVo.getTenantName());
			}
		}
	}

}
