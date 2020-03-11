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
import com.zlsrj.wms.api.client.service.TenantMeterStatusClientService;
import com.zlsrj.wms.api.client.service.TenantInfoClientService;
import com.zlsrj.wms.api.dto.TenantMeterStatusQueryParam;
import com.zlsrj.wms.api.entity.TenantMeterStatus;
import com.zlsrj.wms.api.vo.TenantMeterStatusVo;
import com.zlsrj.wms.api.vo.TenantInfoVo;
import com.zlsrj.wms.common.api.CommonPage;
import com.zlsrj.wms.common.api.CommonResult;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

@Api(value = "水表状况", tags = { "水表状况操作接口" })
@Controller
@RequestMapping("/tenantMeterStatus")
@Slf4j
public class TenantMeterStatusController {

	@Autowired
	private TenantMeterStatusClientService tenantMeterStatusClientService;
	@Autowired
	private TenantInfoClientService tenantInfoClientService;

	@ApiOperation(value = "根据参数查询水表状况列表")
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	@ResponseBody
	public CommonResult<CommonPage<TenantMeterStatusVo>> list(TenantMeterStatusQueryParam tenantMeterStatusQueryParam, int pageNum,
			int pageSize) {
		Page<TenantMeterStatusVo> tenantMeterStatusVoPage = tenantMeterStatusClientService.page(tenantMeterStatusQueryParam, pageNum, pageSize, "id", "desc");
		tenantMeterStatusVoPage.getRecords().stream().forEach(v->wrappperVo(v));

		CommonPage<TenantMeterStatusVo> tenantMeterStatusCommonPage = CommonPage.restPage(tenantMeterStatusVoPage);

		return CommonResult.success(tenantMeterStatusCommonPage);
	}

	@ApiOperation(value = "新增水表状况")
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	@ResponseBody
	public CommonResult<TenantMeterStatusVo> create(@RequestBody TenantMeterStatus tenantMeterStatus) {
		TenantMeterStatusVo tenantMeterStatusVo = tenantMeterStatusClientService.save(tenantMeterStatus);

		return CommonResult.success(tenantMeterStatusVo);
	}

	@ApiOperation(value = "根据ID查询水表状况")
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ResponseBody
	public CommonResult<TenantMeterStatusVo> getById(@PathVariable("id") String id) {
		TenantMeterStatusVo tenantMeterStatusVo = tenantMeterStatusClientService.getById(id);
		wrappperVo(tenantMeterStatusVo);

		return CommonResult.success(tenantMeterStatusVo);
	}

	@ApiOperation(value = "根据参数更新水表状况信息")
	@RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
	@ResponseBody
	public CommonResult<TenantMeterStatusVo> getById(@RequestBody TenantMeterStatus tenantMeterStatus) {
		String id = tenantMeterStatus.getId();
		TenantMeterStatusVo tenantMeterStatusVo = tenantMeterStatusClientService.updatePatchById(id, tenantMeterStatus);
		wrappperVo(tenantMeterStatusVo);

		return CommonResult.success(tenantMeterStatusVo);
	}
	
	@ApiOperation(value = "根据ID删除水表状况")
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
	@ResponseBody
	public CommonResult<Object> removeById(@PathVariable("id") String id) {
		CommonResult<Object> commonResult = tenantMeterStatusClientService.removeById(id);

		return commonResult;
	}

	private void wrappperVo(TenantMeterStatusVo tenantMeterStatusVo) {
		if (StringUtils.isEmpty(tenantMeterStatusVo.getTenantName())) {
			TenantInfoVo tenantInfoVo = tenantInfoClientService.getById(tenantMeterStatusVo.getTenantId());
			if (tenantInfoVo != null) {
				tenantMeterStatusVo.setTenantName(tenantInfoVo.getTenantName());
			}
		}
	}

}
