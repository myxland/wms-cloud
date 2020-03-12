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
import com.zlsrj.wms.api.client.service.TenantMeterClientService;
import com.zlsrj.wms.api.client.service.TenantInfoClientService;
import com.zlsrj.wms.api.dto.TenantMeterQueryParam;
import com.zlsrj.wms.api.entity.TenantMeter;
import com.zlsrj.wms.api.vo.TenantMeterVo;
import com.zlsrj.wms.api.vo.TenantInfoVo;
import com.zlsrj.wms.common.api.CommonPage;
import com.zlsrj.wms.common.api.CommonResult;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

@Api(value = "水表信息", tags = { "水表信息操作接口" })
@Controller
@RequestMapping("/tenantMeter")
@Slf4j
public class TenantMeterController {

	@Autowired
	private TenantMeterClientService tenantMeterClientService;
	@Autowired
	private TenantInfoClientService tenantInfoClientService;

	@ApiOperation(value = "根据参数查询水表信息列表")
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	@ResponseBody
	public CommonResult<CommonPage<TenantMeterVo>> list(TenantMeterQueryParam tenantMeterQueryParam, int pageNum,
			int pageSize) {
		Page<TenantMeterVo> tenantMeterVoPage = tenantMeterClientService.page(tenantMeterQueryParam, pageNum, pageSize, "id", "desc");
		tenantMeterVoPage.getRecords().stream().forEach(v->wrappperVo(v));

		CommonPage<TenantMeterVo> tenantMeterCommonPage = CommonPage.restPage(tenantMeterVoPage);

		return CommonResult.success(tenantMeterCommonPage);
	}

	@ApiOperation(value = "新增水表信息")
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	@ResponseBody
	public CommonResult<TenantMeterVo> create(@RequestBody TenantMeter tenantMeter) {
		TenantMeterVo tenantMeterVo = tenantMeterClientService.save(tenantMeter);

		return CommonResult.success(tenantMeterVo);
	}

	@ApiOperation(value = "根据ID查询水表信息")
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ResponseBody
	public CommonResult<TenantMeterVo> getById(@PathVariable("id") String id) {
		TenantMeterVo tenantMeterVo = tenantMeterClientService.getById(id);
		wrappperVo(tenantMeterVo);

		return CommonResult.success(tenantMeterVo);
	}

	@ApiOperation(value = "根据参数更新水表信息信息")
	@RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
	@ResponseBody
	public CommonResult<TenantMeterVo> getById(@RequestBody TenantMeter tenantMeter) {
		String id = tenantMeter.getId();
		TenantMeterVo tenantMeterVo = tenantMeterClientService.updatePatchById(id, tenantMeter);
		wrappperVo(tenantMeterVo);

		return CommonResult.success(tenantMeterVo);
	}
	
	@ApiOperation(value = "根据ID删除水表信息")
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
	@ResponseBody
	public CommonResult<Object> removeById(@PathVariable("id") String id) {
		CommonResult<Object> commonResult = tenantMeterClientService.removeById(id);

		return commonResult;
	}

	private void wrappperVo(TenantMeterVo tenantMeterVo) {
		if (StringUtils.isEmpty(tenantMeterVo.getTenantName())) {
			TenantInfoVo tenantInfoVo = tenantInfoClientService.getById(tenantMeterVo.getTenantId());
			if (tenantInfoVo != null) {
				tenantMeterVo.setTenantName(tenantInfoVo.getTenantName());
			}
		}
		boolean hasChildren = tenantMeterVo.isHasChildren();
		if(hasChildren == false) {
			String parentId = tenantMeterVo.getId();
			TenantMeterQueryParam tenantMeterQueryParam = new TenantMeterQueryParam();
			tenantMeterQueryParam.setParentId(parentId);
			Page<TenantMeterVo> tenantMeterVoPage = tenantMeterClientService.page(tenantMeterQueryParam, 1, 500, "id", "desc");
			if(tenantMeterVoPage!=null && tenantMeterVoPage.getTotal()>0) {
				tenantMeterVo.setHasChildren(true);
			}
		}
	}

}
