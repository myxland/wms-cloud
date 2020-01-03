package com.zlsrj.wms.saas.rest;

import java.util.stream.Collectors;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zlsrj.wms.api.dto.ModuleInfoQueryParam;
import com.zlsrj.wms.api.entity.ModuleInfo;
import com.zlsrj.wms.api.entity.ModuleInfo;
import com.zlsrj.wms.api.vo.ModuleInfoVo;
import com.zlsrj.wms.common.api.CommonResult;
import com.zlsrj.wms.saas.service.IIdService;
import com.zlsrj.wms.saas.service.IModuleInfoService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

@Api(value = "模块信息", tags = { "模块信息操作接口" })
@RestController
@Slf4j
public class ModuleInfoRestController {

	@Autowired
	private IModuleInfoService moduleInfoService;
	@Autowired
	private IIdService idService;

	@ApiOperation(value = "根据ID查询模块信息")
	@RequestMapping(value = "/module-infos/{id}", method = RequestMethod.GET)
	public ModuleInfoVo getById(@PathVariable("id") Long id) {
		ModuleInfo moduleInfo = moduleInfoService.getById(id);

		return entity2vo(moduleInfo);
	}

	@ApiOperation(value = "根据参数查询模块信息列表")
	@RequestMapping(value = "/module-infos", method = RequestMethod.GET)
	public Page<ModuleInfoVo> page(@RequestBody ModuleInfoQueryParam moduleInfoQueryParam,
			@RequestParam(value = "page", defaultValue = "1") int page, //
			@RequestParam(value = "rows", defaultValue = "10") int rows, //
			@RequestParam(value = "sort") String sort, // 排序列字段名
			@RequestParam(value = "order") String order // 可以是 'asc' 或者 'desc'，默认值是 'asc'
	) {
		IPage<ModuleInfo> pageModuleInfo = new Page<ModuleInfo>(page, rows);
		QueryWrapper<ModuleInfo> queryWrapperModuleInfo = new QueryWrapper<ModuleInfo>();
		queryWrapperModuleInfo.orderBy(StringUtils.isNotEmpty(sort), "desc".equals(order), sort);
		queryWrapperModuleInfo.lambda()
				.eq(moduleInfoQueryParam.getId() != null, ModuleInfo::getId, moduleInfoQueryParam.getId())
				.eq(moduleInfoQueryParam.getModuleName() != null, ModuleInfo::getModuleName, moduleInfoQueryParam.getModuleName())
				.eq(moduleInfoQueryParam.getOpenTarget() != null, ModuleInfo::getOpenTarget, moduleInfoQueryParam.getOpenTarget())
				.eq(moduleInfoQueryParam.getRunEnv() != null, ModuleInfo::getRunEnv, moduleInfoQueryParam.getRunEnv())
				.eq(moduleInfoQueryParam.getRelyModuleId() != null, ModuleInfo::getRelyModuleId, moduleInfoQueryParam.getRelyModuleId())
				.eq(moduleInfoQueryParam.getBillingMode() != null, ModuleInfo::getBillingMode, moduleInfoQueryParam.getBillingMode())
				.eq(moduleInfoQueryParam.getBillingCycle() != null, ModuleInfo::getBillingCycle, moduleInfoQueryParam.getBillingCycle())
				.eq(moduleInfoQueryParam.getBasicEditionOn() != null, ModuleInfo::getBasicEditionOn, moduleInfoQueryParam.getBasicEditionOn())
				.eq(moduleInfoQueryParam.getAdvanceEditionOn() != null, ModuleInfo::getAdvanceEditionOn, moduleInfoQueryParam.getAdvanceEditionOn())
				.eq(moduleInfoQueryParam.getUltimateEditionOn() != null, ModuleInfo::getUltimateEditionOn, moduleInfoQueryParam.getUltimateEditionOn())
				.eq(moduleInfoQueryParam.getModuleOn() != null, ModuleInfo::getModuleOn, moduleInfoQueryParam.getModuleOn())
				.eq(moduleInfoQueryParam.getModuleAppid() != null, ModuleInfo::getModuleAppid, moduleInfoQueryParam.getModuleAppid())
				;

		IPage<ModuleInfo> moduleInfoPage = moduleInfoService.page(pageModuleInfo, queryWrapperModuleInfo);

		Page<ModuleInfoVo> moduleInfoVoPage = new Page<ModuleInfoVo>(page, rows);
		moduleInfoVoPage.setCurrent(moduleInfoPage.getCurrent());
		moduleInfoVoPage.setPages(moduleInfoPage.getPages());
		moduleInfoVoPage.setSize(moduleInfoPage.getSize());
		moduleInfoVoPage.setTotal(moduleInfoPage.getTotal());
		moduleInfoVoPage.setRecords(moduleInfoPage.getRecords().stream()//
				.map(e -> entity2vo(e))//
				.collect(Collectors.toList()));

		return moduleInfoVoPage;
	}

	@ApiOperation(value = "新增模块信息")
	@RequestMapping(value = "/module-infos", method = RequestMethod.POST)
	public ModuleInfoVo save(@RequestBody ModuleInfo moduleInfo) {
		if (moduleInfo.getId() == null || moduleInfo.getId().compareTo(0L) <= 0) {
			moduleInfo.setId(idService.selectId());
		}
		boolean success = moduleInfoService.save(moduleInfo);
		if (success) {
			ModuleInfo moduleInfoDatabase = moduleInfoService.getById(moduleInfo.getId());
			return entity2vo(moduleInfoDatabase);
		}
		log.info("save ModuleInfo fail，{}", ToStringBuilder.reflectionToString(moduleInfo, ToStringStyle.JSON_STYLE));
		return null;
	}

	@ApiOperation(value = "更新模块信息全部信息")
	@RequestMapping(value = "/module-infos/{id}", method = RequestMethod.PUT)
	public ModuleInfoVo updateById(@PathVariable("id") Long id, @RequestBody ModuleInfo moduleInfo) {
		moduleInfo.setId(id);
		boolean success = moduleInfoService.updateById(moduleInfo);
		if (success) {
			ModuleInfo moduleInfoDatabase = moduleInfoService.getById(id);
			return entity2vo(moduleInfoDatabase);
		}
		log.info("update ModuleInfo fail，{}", ToStringBuilder.reflectionToString(moduleInfo, ToStringStyle.JSON_STYLE));
		return null;
	}

	@ApiOperation(value = "根据参数更新模块信息信息")
	@RequestMapping(value = "/module-infos/{id}", method = RequestMethod.PATCH)
	public ModuleInfoVo updatePatchById(@PathVariable("id") Long id, @RequestBody ModuleInfo moduleInfo) {
        ModuleInfo moduleInfoWhere = ModuleInfo.builder()//
				.id(id)//
				.basicEditionOn(moduleInfo.getBasicEditionOn())//
				.advanceEditionOn(moduleInfo.getAdvanceEditionOn())//
				.basicEditionOn(moduleInfo.getUltimateEditionOn())//
				.ultimateModulePriceList(moduleInfo.getBasicModulePriceList())//
				.advanceModulePriceList(moduleInfo.getAdvanceModulePriceList())//
				.ultimateModulePriceList(moduleInfo.getUltimateModulePriceList())//
				.build();
		UpdateWrapper<ModuleInfo> updateWrapperModuleInfo = new UpdateWrapper<ModuleInfo>();
		updateWrapperModuleInfo.setEntity(moduleInfoWhere);
		updateWrapperModuleInfo.lambda()//
				//.eq(ModuleInfo::getId, id)
				// .set(moduleInfo.getId() != null, ModuleInfo::getId, moduleInfo.getId())
				.set(moduleInfo.getModuleName() != null, ModuleInfo::getModuleName, moduleInfo.getModuleName())
				.set(moduleInfo.getOpenTarget() != null, ModuleInfo::getOpenTarget, moduleInfo.getOpenTarget())
				.set(moduleInfo.getRunEnv() != null, ModuleInfo::getRunEnv, moduleInfo.getRunEnv())
				.set(moduleInfo.getRelyModuleId() != null, ModuleInfo::getRelyModuleId, moduleInfo.getRelyModuleId())
				.set(moduleInfo.getBillingMode() != null, ModuleInfo::getBillingMode, moduleInfo.getBillingMode())
				.set(moduleInfo.getBillingCycle() != null, ModuleInfo::getBillingCycle, moduleInfo.getBillingCycle())
				.set(moduleInfo.getBasicEditionOn() != null, ModuleInfo::getBasicEditionOn, moduleInfo.getBasicEditionOn())
				.set(moduleInfo.getAdvanceEditionOn() != null, ModuleInfo::getAdvanceEditionOn, moduleInfo.getAdvanceEditionOn())
				.set(moduleInfo.getUltimateEditionOn() != null, ModuleInfo::getUltimateEditionOn, moduleInfo.getUltimateEditionOn())
				.set(moduleInfo.getModuleOn() != null, ModuleInfo::getModuleOn, moduleInfo.getModuleOn())
				.set(moduleInfo.getModuleAppid() != null, ModuleInfo::getModuleAppid, moduleInfo.getModuleAppid())
				;

		boolean success = moduleInfoService.update(updateWrapperModuleInfo);
		if (success) {
			ModuleInfo moduleInfoDatabase = moduleInfoService.getById(id);
			return entity2vo(moduleInfoDatabase);
		}
		log.info("partial update ModuleInfo fail，{}",
				ToStringBuilder.reflectionToString(moduleInfo, ToStringStyle.JSON_STYLE));
		return null;
	}

	@ApiOperation(value = "根据ID删除模块信息")
	@RequestMapping(value = "/module-infos/{id}", method = RequestMethod.DELETE)
	public CommonResult<Object> removeById(@PathVariable("id") Long id) {
		boolean success = moduleInfoService.removeById(id);
		return success ? CommonResult.success(success) : CommonResult.failed();
	}

	private ModuleInfoVo entity2vo(ModuleInfo moduleInfo) {
		if (moduleInfo == null) {
			return null;
		}

		String jsonString = JSON.toJSONString(moduleInfo);
		ModuleInfoVo moduleInfoVo = JSON.parseObject(jsonString, ModuleInfoVo.class);
		return moduleInfoVo;
	}

}
