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

import com.zlsrj.wms.api.client.service.TenantMeterReadSituationClientService;
import com.zlsrj.wms.api.client.service.TenantInfoClientService;
import com.zlsrj.wms.api.dto.TenantMeterReadSituationAddParam;
import com.zlsrj.wms.api.dto.TenantMeterReadSituationQueryParam;
import com.zlsrj.wms.api.dto.TenantMeterReadSituationUpdateParam;
import com.zlsrj.wms.api.vo.TenantMeterReadSituationVo;
import com.zlsrj.wms.api.vo.TenantInfoVo;
import com.zlsrj.wms.common.api.CommonResult;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

@Api(value = "抄表表况", tags = { "抄表表况操作接口" })
@Controller
@RequestMapping("/tenantMeterReadSituation")
@Slf4j
public class TenantMeterReadSituationController {

	@Autowired
	private TenantMeterReadSituationClientService tenantMeterReadSituationClientService;
	@Autowired
	private TenantInfoClientService tenantInfoClientService;

	@ApiOperation(value = "根据参数查询抄表表况列表")
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	@ResponseBody
	public CommonResult<List<TenantMeterReadSituationVo>> list(TenantMeterReadSituationQueryParam tenantMeterReadSituationQueryParam) {
		List<TenantMeterReadSituationVo> tenantMeterReadSituationVoList = tenantMeterReadSituationClientService.list(tenantMeterReadSituationQueryParam);
		tenantMeterReadSituationVoList.stream().forEach(v->wrappperVo(v));

		return CommonResult.success(tenantMeterReadSituationVoList);
	}

	@ApiOperation(value = "新增抄表表况")
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	@ResponseBody
	public CommonResult<Object> create(@RequestBody TenantMeterReadSituationAddParam tenantMeterReadSituationAddParam) {
		String id = tenantMeterReadSituationClientService.save(tenantMeterReadSituationAddParam);

		return CommonResult.success(id);
	}

	@ApiOperation(value = "根据ID查询抄表表况")
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ResponseBody
	public CommonResult<TenantMeterReadSituationVo> getById(@PathVariable("id") String id) {
		TenantMeterReadSituationVo tenantMeterReadSituationVo = tenantMeterReadSituationClientService.getById(id);
		wrappperVo(tenantMeterReadSituationVo);

		return CommonResult.success(tenantMeterReadSituationVo);
	}

	@ApiOperation(value = "根据参数更新抄表表况信息")
	@RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
	@ResponseBody
	public CommonResult<Object> updateById(@PathVariable("id") String id,@RequestBody TenantMeterReadSituationUpdateParam tenantMeterReadSituationUpdateParam) {
		boolean success = tenantMeterReadSituationClientService.updateById(id, tenantMeterReadSituationUpdateParam);

		return CommonResult.success(success);
	}
	
	@ApiOperation(value = "根据ID删除抄表表况")
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
	@ResponseBody
	public CommonResult<Object> removeById(@PathVariable("id") String id) {
		CommonResult<Object> commonResult = tenantMeterReadSituationClientService.removeById(id);

		return commonResult;
	}

	private void wrappperVo(TenantMeterReadSituationVo tenantMeterReadSituationVo) {
		if (StringUtils.isEmpty(tenantMeterReadSituationVo.getTenantName())) {
			TenantInfoVo tenantInfoVo = tenantInfoClientService.getDictionaryById(tenantMeterReadSituationVo.getTenantId());
			if (tenantInfoVo != null) {
				tenantMeterReadSituationVo.setTenantName(tenantInfoVo.getTenantName());
			}
		}
	}

}
