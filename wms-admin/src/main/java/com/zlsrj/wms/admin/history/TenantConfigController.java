package com.zlsrj.wms.admin.history;

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
import com.zlsrj.wms.api.client.service.TenantConfigClientService;
import com.zlsrj.wms.api.dto.TenantConfigQueryParam;
import com.zlsrj.wms.api.entity.TenantConfig;
import com.zlsrj.wms.api.vo.TenantConfigVo;
import com.zlsrj.wms.common.api.CommonPage;
import com.zlsrj.wms.common.api.CommonResult;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

@Api(value = "租户基础配置", tags = { "租户基础配置操作接口" })
@Controller
@RequestMapping("/tenantConfig")
@Slf4j
public class TenantConfigController {

	@Autowired
	private TenantConfigClientService tenantConfigClientService;

	@ApiOperation(value = "根据参数查询租户基础配置列表")
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	@ResponseBody
	public CommonResult<CommonPage<TenantConfigVo>> list(TenantConfigQueryParam tenantConfigQueryParam, int pageNum,
			int pageSize) {
		Page<TenantConfigVo> tenantConfigVoPage = tenantConfigClientService.page(tenantConfigQueryParam, pageNum, pageSize, "id", "desc");

		CommonPage<TenantConfigVo> tenantConfigCommonPage = CommonPage.restPage(tenantConfigVoPage);

		return CommonResult.success(tenantConfigCommonPage);
	}

	@ApiOperation(value = "新增租户基础配置")
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	@ResponseBody
	public CommonResult<TenantConfigVo> create(@RequestBody TenantConfig tenantConfig) {
		TenantConfigVo tenantConfigVo = tenantConfigClientService.save(tenantConfig);

		return CommonResult.success(tenantConfigVo);
	}

	@ApiOperation(value = "根据ID查询租户基础配置")
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ResponseBody
	public CommonResult<TenantConfigVo> getById(@PathVariable("id") String id) {
		TenantConfigVo tenantConfigVo = tenantConfigClientService.getById(id);

		return CommonResult.success(tenantConfigVo);
	}

	@ApiOperation(value = "根据租户ID查询租户基础配置")
	@RequestMapping(value = "/tenantId/{tenantId}", method = RequestMethod.GET)
	@ResponseBody
	public CommonResult<TenantConfigVo> getByTenantId(@PathVariable("tenantId") String tenantId) {
		TenantConfigVo tenantConfigVo = tenantConfigClientService.getByTenantId(tenantId);

		return CommonResult.success(tenantConfigVo);
	}

	@ApiOperation(value = "根据参数更新租户基础配置信息")
	@RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
	@ResponseBody
	public CommonResult<TenantConfigVo> getById(@RequestBody TenantConfig tenantConfig) {
		String id = tenantConfig.getId();
		TenantConfigVo tenantConfigVo = tenantConfigClientService.updatePatchById(id, tenantConfig);

		return CommonResult.success(tenantConfigVo);
	}
	
	@ApiOperation(value = "根据ID删除租户基础配置")
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
	@ResponseBody
	public CommonResult<Object> removeById(@PathVariable("id") String id) {
		CommonResult<Object> commonResult = tenantConfigClientService.removeById(id);

		return commonResult;
	}

}
