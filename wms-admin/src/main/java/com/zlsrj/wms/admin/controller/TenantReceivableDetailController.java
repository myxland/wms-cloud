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
import com.zlsrj.wms.api.client.service.TenantReceivableDetailClientService;
import com.zlsrj.wms.api.client.service.TenantInfoClientService;
import com.zlsrj.wms.api.dto.TenantReceivableDetailQueryParam;
import com.zlsrj.wms.api.entity.TenantReceivableDetail;
import com.zlsrj.wms.api.vo.TenantReceivableDetailVo;
import com.zlsrj.wms.api.vo.TenantInfoVo;
import com.zlsrj.wms.common.api.CommonPage;
import com.zlsrj.wms.common.api.CommonResult;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

@Api(value = "应收明细账", tags = { "应收明细账操作接口" })
@Controller
@RequestMapping("/tenantReceivableDetail")
@Slf4j
public class TenantReceivableDetailController {

	@Autowired
	private TenantReceivableDetailClientService tenantReceivableDetailClientService;
	@Autowired
	private TenantInfoClientService tenantInfoClientService;

	@ApiOperation(value = "根据参数查询应收明细账列表")
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	@ResponseBody
	public CommonResult<CommonPage<TenantReceivableDetailVo>> list(TenantReceivableDetailQueryParam tenantReceivableDetailQueryParam, int pageNum,
			int pageSize) {
		Page<TenantReceivableDetailVo> tenantReceivableDetailVoPage = tenantReceivableDetailClientService.page(tenantReceivableDetailQueryParam, pageNum, pageSize, "id", "desc");
		tenantReceivableDetailVoPage.getRecords().stream().forEach(v->wrappperVo(v));

		CommonPage<TenantReceivableDetailVo> tenantReceivableDetailCommonPage = CommonPage.restPage(tenantReceivableDetailVoPage);

		return CommonResult.success(tenantReceivableDetailCommonPage);
	}

	@ApiOperation(value = "新增应收明细账")
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	@ResponseBody
	public CommonResult<TenantReceivableDetailVo> create(@RequestBody TenantReceivableDetail tenantReceivableDetail) {
		TenantReceivableDetailVo tenantReceivableDetailVo = tenantReceivableDetailClientService.save(tenantReceivableDetail);

		return CommonResult.success(tenantReceivableDetailVo);
	}

	@ApiOperation(value = "根据ID查询应收明细账")
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ResponseBody
	public CommonResult<TenantReceivableDetailVo> getById(@PathVariable("id") String id) {
		TenantReceivableDetailVo tenantReceivableDetailVo = tenantReceivableDetailClientService.getById(id);
		wrappperVo(tenantReceivableDetailVo);

		return CommonResult.success(tenantReceivableDetailVo);
	}

	@ApiOperation(value = "根据参数更新应收明细账信息")
	@RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
	@ResponseBody
	public CommonResult<TenantReceivableDetailVo> getById(@RequestBody TenantReceivableDetail tenantReceivableDetail) {
		String id = tenantReceivableDetail.getId();
		TenantReceivableDetailVo tenantReceivableDetailVo = tenantReceivableDetailClientService.updatePatchById(id, tenantReceivableDetail);
		wrappperVo(tenantReceivableDetailVo);

		return CommonResult.success(tenantReceivableDetailVo);
	}
	
	@ApiOperation(value = "根据ID删除应收明细账")
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
	@ResponseBody
	public CommonResult<Object> removeById(@PathVariable("id") String id) {
		CommonResult<Object> commonResult = tenantReceivableDetailClientService.removeById(id);

		return commonResult;
	}

	private void wrappperVo(TenantReceivableDetailVo tenantReceivableDetailVo) {
		if (StringUtils.isEmpty(tenantReceivableDetailVo.getTenantName())) {
			TenantInfoVo tenantInfoVo = tenantInfoClientService.getById(tenantReceivableDetailVo.getTenantId());
			if (tenantInfoVo != null) {
				tenantReceivableDetailVo.setTenantName(tenantInfoVo.getTenantName());
			}
		}
	}

}
