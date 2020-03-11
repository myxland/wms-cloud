package com.zlsrj.wms.admin.controller;

import java.util.Arrays;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zlsrj.wms.api.client.service.DevReadCurrHisClientService;
import com.zlsrj.wms.api.client.service.TenantInfoClientService;
import com.zlsrj.wms.api.dto.DevReadCurrHisQueryParam;
import com.zlsrj.wms.api.entity.DevReadCurrHis;
import com.zlsrj.wms.api.vo.DevReadCurrHisVo;
import com.zlsrj.wms.api.vo.TenantInfoVo;
import com.zlsrj.wms.common.api.CommonPage;
import com.zlsrj.wms.common.api.CommonResult;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

@Api(value = "历史抄表信息", tags = { "历史抄表信息操作接口" })
@Controller
@RequestMapping("/devReadCurrHis")
@Slf4j
public class DevReadCurrHisController {

	@Autowired
	private DevReadCurrHisClientService devReadCurrHisClientService;
	@Autowired
	private TenantInfoClientService tenantInfoClientService;

	@ApiOperation(value = "根据参数查询历史抄表信息列表")
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	@ResponseBody
	public CommonResult<CommonPage<DevReadCurrHisVo>> list(DevReadCurrHisQueryParam devReadCurrHisQueryParam, int pageNum,
			int pageSize) {
		Page<DevReadCurrHisVo> devReadCurrHisVoPage = devReadCurrHisClientService.page(devReadCurrHisQueryParam, pageNum, pageSize, "id", "desc");
		devReadCurrHisVoPage.getRecords().stream().forEach(v->wrappperVo(v));

		CommonPage<DevReadCurrHisVo> devReadCurrHisCommonPage = CommonPage.restPage(devReadCurrHisVoPage);

		return CommonResult.success(devReadCurrHisCommonPage);
	}

	@ApiOperation(value = "新增历史抄表信息")
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	@ResponseBody
	public CommonResult<DevReadCurrHisVo> create(@RequestBody DevReadCurrHis devReadCurrHis) {
		DevReadCurrHisVo devReadCurrHisVo = devReadCurrHisClientService.save(devReadCurrHis);

		return CommonResult.success(devReadCurrHisVo);
	}

	@ApiOperation(value = "根据ID查询历史抄表信息")
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ResponseBody
	public CommonResult<DevReadCurrHisVo> getById(@PathVariable("id") String id) {
		DevReadCurrHisVo devReadCurrHisVo = devReadCurrHisClientService.getById(id);
		wrappperVo(devReadCurrHisVo);
		
		return CommonResult.success(devReadCurrHisVo);
	}

	@ApiOperation(value = "根据参数更新历史抄表信息信息")
	@RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
	@ResponseBody
	public CommonResult<DevReadCurrHisVo> getById(@RequestBody DevReadCurrHis devReadCurrHis) {
		String id = devReadCurrHis.getId();
		DevReadCurrHisVo devReadCurrHisVo = devReadCurrHisClientService.updatePatchById(id, devReadCurrHis);

		return CommonResult.success(devReadCurrHisVo);
	}
	
	@ApiOperation(value = "根据ID删除历史抄表信息")
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
	@ResponseBody
	public CommonResult<Object> removeById(@PathVariable("id") String id) {
		CommonResult<Object> commonResult = devReadCurrHisClientService.removeById(id);

		return commonResult;
	}
	
	private void wrappperVo(DevReadCurrHisVo devReadCurrHisVo) {
		if (StringUtils.isEmpty(devReadCurrHisVo.getTenantName())) {
			TenantInfoVo tenantInfoVo = tenantInfoClientService.getById(devReadCurrHisVo.getTenantId());
			if (tenantInfoVo != null) {
				devReadCurrHisVo.setTenantName(tenantInfoVo.getTenantName());
			}
		}
	}

}
