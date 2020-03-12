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
import com.zlsrj.wms.api.client.service.TenantPriceTypeClientService;
import com.zlsrj.wms.api.client.service.TenantInfoClientService;
import com.zlsrj.wms.api.dto.TenantPriceTypeQueryParam;
import com.zlsrj.wms.api.entity.TenantPriceType;
import com.zlsrj.wms.api.vo.TenantPriceTypeVo;
import com.zlsrj.wms.api.vo.TenantInfoVo;
import com.zlsrj.wms.common.api.CommonPage;
import com.zlsrj.wms.common.api.CommonResult;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

@Api(value = "水价分类", tags = { "水价分类操作接口" })
@Controller
@RequestMapping("/tenantPriceType")
@Slf4j
public class TenantPriceTypeController {

	@Autowired
	private TenantPriceTypeClientService tenantPriceTypeClientService;
	@Autowired
	private TenantInfoClientService tenantInfoClientService;

	@ApiOperation(value = "根据参数查询水价分类列表")
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	@ResponseBody
	public CommonResult<CommonPage<TenantPriceTypeVo>> list(TenantPriceTypeQueryParam tenantPriceTypeQueryParam, int pageNum,
			int pageSize) {
		Page<TenantPriceTypeVo> tenantPriceTypeVoPage = tenantPriceTypeClientService.page(tenantPriceTypeQueryParam, pageNum, pageSize, "id", "desc");
		tenantPriceTypeVoPage.getRecords().stream().forEach(v->wrappperVo(v));

		CommonPage<TenantPriceTypeVo> tenantPriceTypeCommonPage = CommonPage.restPage(tenantPriceTypeVoPage);

		return CommonResult.success(tenantPriceTypeCommonPage);
	}

	@ApiOperation(value = "新增水价分类")
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	@ResponseBody
	public CommonResult<TenantPriceTypeVo> create(@RequestBody TenantPriceType tenantPriceType) {
		TenantPriceTypeVo tenantPriceTypeVo = tenantPriceTypeClientService.save(tenantPriceType);

		return CommonResult.success(tenantPriceTypeVo);
	}

	@ApiOperation(value = "根据ID查询水价分类")
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ResponseBody
	public CommonResult<TenantPriceTypeVo> getById(@PathVariable("id") String id) {
		TenantPriceTypeVo tenantPriceTypeVo = tenantPriceTypeClientService.getById(id);
		wrappperVo(tenantPriceTypeVo);

		return CommonResult.success(tenantPriceTypeVo);
	}

	@ApiOperation(value = "根据参数更新水价分类信息")
	@RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
	@ResponseBody
	public CommonResult<TenantPriceTypeVo> getById(@RequestBody TenantPriceType tenantPriceType) {
		String id = tenantPriceType.getId();
		TenantPriceTypeVo tenantPriceTypeVo = tenantPriceTypeClientService.updatePatchById(id, tenantPriceType);
		wrappperVo(tenantPriceTypeVo);

		return CommonResult.success(tenantPriceTypeVo);
	}
	
	@ApiOperation(value = "根据ID删除水价分类")
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
	@ResponseBody
	public CommonResult<Object> removeById(@PathVariable("id") String id) {
		CommonResult<Object> commonResult = tenantPriceTypeClientService.removeById(id);

		return commonResult;
	}

	private void wrappperVo(TenantPriceTypeVo tenantPriceTypeVo) {
		if (StringUtils.isEmpty(tenantPriceTypeVo.getTenantName())) {
			TenantInfoVo tenantInfoVo = tenantInfoClientService.getById(tenantPriceTypeVo.getTenantId());
			if (tenantInfoVo != null) {
				tenantPriceTypeVo.setTenantName(tenantInfoVo.getTenantName());
			}
		}
	}

}
