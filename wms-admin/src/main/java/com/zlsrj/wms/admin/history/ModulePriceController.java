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
import com.zlsrj.wms.api.client.service.ModulePriceClientService;
import com.zlsrj.wms.api.client.service.ModuleInfoClientService;
import com.zlsrj.wms.api.dto.ModulePriceQueryParam;
import com.zlsrj.wms.api.entity.ModulePrice;
import com.zlsrj.wms.api.vo.ModulePriceVo;
import com.zlsrj.wms.api.vo.ModuleInfoVo;
import com.zlsrj.wms.common.api.CommonPage;
import com.zlsrj.wms.common.api.CommonResult;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

@Api(value = "模块价格", tags = { "模块价格操作接口" })
@Controller
@RequestMapping("/modulePrice")
@Slf4j
public class ModulePriceController {

	@Autowired
	private ModulePriceClientService modulePriceClientService;
	@Autowired
	private ModuleInfoClientService moduleInfoClientService;

	@ApiOperation(value = "根据参数查询模块价格列表")
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	@ResponseBody
	public CommonResult<CommonPage<ModulePriceVo>> list(ModulePriceQueryParam modulePriceQueryParam, int pageNum,
			int pageSize) {
		Page<ModulePriceVo> modulePriceVoPage = modulePriceClientService.page(modulePriceQueryParam, pageNum, pageSize, "id", "desc");
		modulePriceVoPage.getRecords().stream().forEach(v->wrappperVo(v));

		CommonPage<ModulePriceVo> modulePriceCommonPage = CommonPage.restPage(modulePriceVoPage);

		return CommonResult.success(modulePriceCommonPage);
	}

	@ApiOperation(value = "新增模块价格")
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	@ResponseBody
	public CommonResult<ModulePriceVo> create(@RequestBody ModulePrice modulePrice) {
		ModulePriceVo modulePriceVo = modulePriceClientService.save(modulePrice);

		return CommonResult.success(modulePriceVo);
	}

	@ApiOperation(value = "根据ID查询模块价格")
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ResponseBody
	public CommonResult<ModulePriceVo> getById(@PathVariable("id") String id) {
		ModulePriceVo modulePriceVo = modulePriceClientService.getById(id);
		wrappperVo(modulePriceVo);

		return CommonResult.success(modulePriceVo);
	}

	@ApiOperation(value = "根据参数更新模块价格信息")
	@RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
	@ResponseBody
	public CommonResult<ModulePriceVo> getById(@RequestBody ModulePrice modulePrice) {
		String id = modulePrice.getId();
		ModulePriceVo modulePriceVo = modulePriceClientService.updatePatchById(id, modulePrice);
		wrappperVo(modulePriceVo);

		return CommonResult.success(modulePriceVo);
	}
	
	@ApiOperation(value = "根据ID删除模块价格")
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
	@ResponseBody
	public CommonResult<Object> removeById(@PathVariable("id") String id) {
		CommonResult<Object> commonResult = modulePriceClientService.removeById(id);

		return commonResult;
	}

	private void wrappperVo(ModulePriceVo modulePriceVo) {
		if (StringUtils.isEmpty(modulePriceVo.getModuleName())) {
			ModuleInfoVo moduleInfoVo = moduleInfoClientService.getById(modulePriceVo.getModuleId());
			if (moduleInfoVo != null) {
				modulePriceVo.setModuleName(moduleInfoVo.getModuleName());
			}
		}
	}

}
