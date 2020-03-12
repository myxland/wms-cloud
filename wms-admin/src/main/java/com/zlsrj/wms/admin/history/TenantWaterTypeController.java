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
import com.zlsrj.wms.api.client.service.TenantWaterTypeClientService;
import com.zlsrj.wms.api.client.service.TenantInfoClientService;
import com.zlsrj.wms.api.dto.TenantWaterTypeQueryParam;
import com.zlsrj.wms.api.entity.TenantWaterType;
import com.zlsrj.wms.api.vo.TenantWaterTypeVo;
import com.zlsrj.wms.api.vo.TenantInfoVo;
import com.zlsrj.wms.common.api.CommonPage;
import com.zlsrj.wms.common.api.CommonResult;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

@Api(value = "用水分类", tags = { "用水分类操作接口" })
@Controller
@RequestMapping("/tenantWaterType")
@Slf4j
public class TenantWaterTypeController {

	@Autowired
	private TenantWaterTypeClientService tenantWaterTypeClientService;
	@Autowired
	private TenantInfoClientService tenantInfoClientService;

	@ApiOperation(value = "根据参数查询用水分类列表")
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	@ResponseBody
	public CommonResult<CommonPage<TenantWaterTypeVo>> list(TenantWaterTypeQueryParam tenantWaterTypeQueryParam, int pageNum,
			int pageSize) {
		Page<TenantWaterTypeVo> tenantWaterTypeVoPage = tenantWaterTypeClientService.page(tenantWaterTypeQueryParam, pageNum, pageSize, "id", "desc");
		tenantWaterTypeVoPage.getRecords().stream().forEach(v->wrappperVo(v));

		CommonPage<TenantWaterTypeVo> tenantWaterTypeCommonPage = CommonPage.restPage(tenantWaterTypeVoPage);

		return CommonResult.success(tenantWaterTypeCommonPage);
	}

	@ApiOperation(value = "新增用水分类")
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	@ResponseBody
	public CommonResult<TenantWaterTypeVo> create(@RequestBody TenantWaterType tenantWaterType) {
		TenantWaterTypeVo tenantWaterTypeVo = tenantWaterTypeClientService.save(tenantWaterType);

		return CommonResult.success(tenantWaterTypeVo);
	}

	@ApiOperation(value = "根据ID查询用水分类")
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ResponseBody
	public CommonResult<TenantWaterTypeVo> getById(@PathVariable("id") String id) {
		TenantWaterTypeVo tenantWaterTypeVo = tenantWaterTypeClientService.getById(id);
		wrappperVo(tenantWaterTypeVo);

		return CommonResult.success(tenantWaterTypeVo);
	}

	@ApiOperation(value = "根据参数更新用水分类信息")
	@RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
	@ResponseBody
	public CommonResult<TenantWaterTypeVo> getById(@RequestBody TenantWaterType tenantWaterType) {
		String id = tenantWaterType.getId();
		TenantWaterTypeVo tenantWaterTypeVo = tenantWaterTypeClientService.updatePatchById(id, tenantWaterType);
		wrappperVo(tenantWaterTypeVo);

		return CommonResult.success(tenantWaterTypeVo);
	}
	
	@ApiOperation(value = "根据ID删除用水分类")
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
	@ResponseBody
	public CommonResult<Object> removeById(@PathVariable("id") String id) {
		CommonResult<Object> commonResult = tenantWaterTypeClientService.removeById(id);

		return commonResult;
	}

	private void wrappperVo(TenantWaterTypeVo tenantWaterTypeVo) {
		if (StringUtils.isEmpty(tenantWaterTypeVo.getTenantName())) {
			TenantInfoVo tenantInfoVo = tenantInfoClientService.getById(tenantWaterTypeVo.getTenantId());
			if (tenantInfoVo != null) {
				tenantWaterTypeVo.setTenantName(tenantInfoVo.getTenantName());
			}
		}
		boolean hasChildren = tenantWaterTypeVo.isHasChildren();
		if(hasChildren == false) {
			String parentId = tenantWaterTypeVo.getId();
			TenantWaterTypeQueryParam tenantWaterTypeQueryParam = new TenantWaterTypeQueryParam();
			tenantWaterTypeQueryParam.setParentId(parentId);
			Page<TenantWaterTypeVo> tenantWaterTypeVoPage = tenantWaterTypeClientService.page(tenantWaterTypeQueryParam, 1, 500, "id", "desc");
			if(tenantWaterTypeVoPage!=null && tenantWaterTypeVoPage.getTotal()>0) {
				tenantWaterTypeVo.setHasChildren(true);
			}
		}
	}

}
