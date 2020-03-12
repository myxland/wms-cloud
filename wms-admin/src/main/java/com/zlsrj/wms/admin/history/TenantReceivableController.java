package com.zlsrj.wms.admin.history;

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
import com.zlsrj.wms.api.client.service.TenantReceivableClientService;
import com.zlsrj.wms.api.client.service.TenantInfoClientService;
import com.zlsrj.wms.api.dto.TenantReceivableQueryParam;
import com.zlsrj.wms.api.entity.TenantReceivable;
import com.zlsrj.wms.api.vo.TenantReceivableVo;
import com.zlsrj.wms.api.vo.TenantInfoVo;
import com.zlsrj.wms.common.api.CommonPage;
import com.zlsrj.wms.common.api.CommonResult;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

@Api(value = "应收明细", tags = { "应收明细操作接口" })
@Controller
@RequestMapping("/tenantReceivable")
@Slf4j
public class TenantReceivableController {

	@Autowired
	private TenantReceivableClientService tenantReceivableClientService;
	@Autowired
	private TenantInfoClientService tenantInfoClientService;

	@ApiOperation(value = "根据参数查询应收明细列表")
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	@ResponseBody
	public CommonResult<CommonPage<TenantReceivableVo>> list(TenantReceivableQueryParam tenantReceivableQueryParam, int pageNum,
			int pageSize) {
		Page<TenantReceivableVo> tenantReceivableVoPage = tenantReceivableClientService.page(tenantReceivableQueryParam, pageNum, pageSize, "id", "desc");
		tenantReceivableVoPage.getRecords().stream().forEach(v->wrappperVo(v));
		
		TenantReceivableVo tenantReceivableVoAggregation = tenantReceivableClientService.aggregation(tenantReceivableQueryParam);

		CommonPage<TenantReceivableVo> tenantReceivableCommonPage = CommonPage.restPage(tenantReceivableVoPage,tenantReceivableVoAggregation);

		return CommonResult.success(tenantReceivableCommonPage);
	}

	@ApiOperation(value = "新增应收明细")
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	@ResponseBody
	public CommonResult<TenantReceivableVo> create(@RequestBody TenantReceivable tenantReceivable) {
		TenantReceivableVo tenantReceivableVo = tenantReceivableClientService.save(tenantReceivable);

		return CommonResult.success(tenantReceivableVo);
	}

	@ApiOperation(value = "根据ID查询应收明细")
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ResponseBody
	public CommonResult<TenantReceivableVo> getById(@PathVariable("id") String id) {
		TenantReceivableVo tenantReceivableVo = tenantReceivableClientService.getById(id);
		wrappperVo(tenantReceivableVo);

		return CommonResult.success(tenantReceivableVo);
	}

	@ApiOperation(value = "根据参数更新应收明细信息")
	@RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
	@ResponseBody
	public CommonResult<TenantReceivableVo> getById(@RequestBody TenantReceivable tenantReceivable) {
		String id = tenantReceivable.getId();
		TenantReceivableVo tenantReceivableVo = tenantReceivableClientService.updatePatchById(id, tenantReceivable);
		wrappperVo(tenantReceivableVo);

		return CommonResult.success(tenantReceivableVo);
	}
	
	@ApiOperation(value = "根据ID删除应收明细")
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
	@ResponseBody
	public CommonResult<Object> removeById(@PathVariable("id") String id) {
		CommonResult<Object> commonResult = tenantReceivableClientService.removeById(id);

		return commonResult;
	}

	private void wrappperVo(TenantReceivableVo tenantReceivableVo) {
		if (StringUtils.isEmpty(tenantReceivableVo.getTenantName())) {
			TenantInfoVo tenantInfoVo = tenantInfoClientService.getById(tenantReceivableVo.getTenantId());
			if (tenantInfoVo != null) {
				tenantReceivableVo.setTenantName(tenantInfoVo.getTenantName());
			}
		}
	}

}
