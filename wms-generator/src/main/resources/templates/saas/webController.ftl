package ${domainName}.${projectNameWeb}.controller;

import java.util.List;

<#if table.includeTenantId || table.includeSysId || table.includeModuleId>
import org.apache.commons.lang3.StringUtils;
</#if>import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import ${domainName}.${projectNameApi}.client.service.${table.entityName}ClientService;
<#if table.includeTenantId>
import ${domainName}.${projectNameApi}.client.service.TenantInfoClientService;
</#if>
<#if table.includeSysId>
import ${domainName}.${projectNameApi}.client.service.SystemDesignClientService;
</#if>
<#if table.includeModuleId>
import ${domainName}.${projectNameApi}.client.service.ModuleInfoClientService;
</#if>
import ${domainName}.${projectNameApi}.dto.${table.entityName}AddParam;
import ${domainName}.${projectNameApi}.dto.${table.entityName}QueryParam;
import ${domainName}.${projectNameApi}.dto.${table.entityName}UpdateParam;
import ${domainName}.${projectNameApi}.vo.${table.entityName}Vo;
<#if table.includeTenantId>
import ${domainName}.${projectNameApi}.vo.TenantInfoVo;
</#if>
<#if table.includeSysId>
import ${domainName}.${projectNameApi}.vo.SystemDesignVo;
</#if>
<#if table.includeModuleId>
import ${domainName}.${projectNameApi}.vo.ModuleInfoVo;
</#if>
import ${domainName}.common.api.CommonResult;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

@Api(value = "${table.tableComment}", tags = { "${table.tableComment}操作接口" })
@Controller
@RequestMapping("/${table.entityName?uncap_first}")
@Slf4j
public class ${table.entityName}Controller {

	@Autowired
	private ${table.entityName}ClientService ${table.entityName?uncap_first}ClientService;
	<#if table.includeTenantId>
	@Autowired
	private TenantInfoClientService tenantInfoClientService;
	</#if>
	<#if table.includeSysId>
	@Autowired
	private SystemDesignClientService systemDesignClientService;
	</#if>
	<#if table.includeModuleId>
	@Autowired
	private ModuleInfoClientService moduleInfoClientService;
	</#if>

	@ApiOperation(value = "根据参数查询${table.tableComment}列表")
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	@ResponseBody
	public CommonResult<List<${table.entityName}Vo>> list(${table.entityName}QueryParam ${table.entityName?uncap_first}QueryParam) {
		List<${table.entityName}Vo> ${table.entityName?uncap_first}VoList = ${table.entityName?uncap_first}ClientService.list(${table.entityName?uncap_first}QueryParam);
		${table.entityName?uncap_first}VoList.stream().forEach(v->wrappperVo(v));

		return CommonResult.success(${table.entityName?uncap_first}VoList);
	}

	<#if table.includeSingleUpdatable>
	<#list table.singleUpdatableColumnList as column>
	@ApiOperation(value = "更新${table.tableComment}${column.propertyComment}")
	@RequestMapping(value = "/update/${column.propertyName}", method = RequestMethod.POST)
	@ResponseBody
	public CommonResult<Boolean> update${column.propertyName?cap_first}(@RequestParam("ids") String ids,
			@RequestParam("${column.propertyName}") ${column.propertyType} ${column.propertyName}) {
		Arrays.asList(ids.split(",")).forEach(n -> {
			String id = n;
			${table.entityName} ${table.entityName?uncap_first} = new ${table.entityName}();
			${table.entityName?uncap_first}.set${column.propertyName?cap_first}(${column.propertyName});
			${table.entityName?uncap_first}ClientService.updatePatchById(id, ${table.entityName?uncap_first});
		});

		return CommonResult.success(true);
	}

	</#list>
	</#if>
	<#if table.includeBatchUpdatable>
	<#list table.batchUpdatableColumnList as column>
	@ApiOperation(value = "更新${table.tableComment}${column.propertyComment}")
	@RequestMapping(value = "/update/${column.propertyName}", method = RequestMethod.POST)
	@ResponseBody
	public CommonResult<Boolean> update${column.propertyName?cap_first}(@RequestParam("ids") String ids,
			@RequestParam("${column.propertyName}") ${column.propertyType} ${column.propertyName}) {
		Arrays.asList(ids.split(",")).forEach(n -> {
			String id = n;
			${table.entityName} ${table.entityName?uncap_first} = new ${table.entityName}();
			${table.entityName?uncap_first}.set${column.propertyName?cap_first}(${column.propertyName});
			${table.entityName?uncap_first}ClientService.updatePatchById(id, ${table.entityName?uncap_first});
		});

		return CommonResult.success(true);
	}

	</#list>
	</#if>
	@ApiOperation(value = "新增${table.tableComment}")
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	@ResponseBody
	public CommonResult<Object> create(@RequestBody ${table.entityName}AddParam ${table.entityName?uncap_first}AddParam) {
		String id = ${table.entityName?uncap_first}ClientService.save(${table.entityName?uncap_first}AddParam);

		return CommonResult.success(id);
	}

	@ApiOperation(value = "根据ID查询${table.tableComment}")
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ResponseBody
	public CommonResult<${table.entityName}Vo> getById(@PathVariable("id") String id) {
		${table.entityName}Vo ${table.entityName?uncap_first}Vo = ${table.entityName?uncap_first}ClientService.getById(id);
		<#if table.includeTenantId || table.includeSysId || table.includeModuleId>
		wrappperVo(${table.entityName?uncap_first}Vo);
		</#if>

		return CommonResult.success(${table.entityName?uncap_first}Vo);
	}

	<#if table.includeTenantOne2One>
	@ApiOperation(value = "根据租户ID查询${table.tableComment}")
	@RequestMapping(value = "/tenantId/{tenantId}", method = RequestMethod.GET)
	@ResponseBody
	public CommonResult<${table.entityName}Vo> getByTenantId(@PathVariable("tenantId") Long tenantId) {
		${table.entityName}Vo ${table.entityName?uncap_first}Vo = ${table.entityName?uncap_first}ClientService.getByTenantId(tenantId);
		<#if table.includeTenantId || table.includeSysId || table.includeModuleId>
		wrappperVo(${table.entityName?uncap_first}Vo);
		</#if>

		return CommonResult.success(${table.entityName?uncap_first}Vo);
	}

	</#if>
	@ApiOperation(value = "根据参数更新${table.tableComment}信息")
	@RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
	@ResponseBody
	public CommonResult<Object> updateById(@PathVariable("id") String id,@RequestBody ${table.entityName}UpdateParam ${table.entityName?uncap_first}UpdateParam) {
		boolean success = ${table.entityName?uncap_first}ClientService.updateById(id, ${table.entityName?uncap_first}UpdateParam);

		return CommonResult.success(success);
	}
	
	@ApiOperation(value = "根据ID删除${table.tableComment}")
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
	@ResponseBody
	public CommonResult<Object> removeById(@PathVariable("id") String id) {
		CommonResult<Object> commonResult = ${table.entityName?uncap_first}ClientService.removeById(id);

		return commonResult;
	}

	<#if table.includeTenantId || table.includeSysId || table.includeModuleId>
	private void wrappperVo(${table.entityName}Vo ${table.entityName?uncap_first}Vo) {
		<#if table.includeTenantId>
		if (StringUtils.isEmpty(${table.entityName?uncap_first}Vo.getTenantName())) {
			TenantInfoVo tenantInfoVo = tenantInfoClientService.getDictionaryById(${table.entityName?uncap_first}Vo.getTenantId());
			if (tenantInfoVo != null) {
				${table.entityName?uncap_first}Vo.setTenantName(tenantInfoVo.getTenantName());
			}
		}
		</#if>
		<#if table.includeSysId>
		if (StringUtils.isEmpty(${table.entityName?uncap_first}Vo.getSysName())) {
			SystemDesignVo systemDesignVo = systemDesignClientService.getDictionaryById(${table.entityName?uncap_first}Vo.getSysId());
			if (systemDesignVo != null) {
				${table.entityName?uncap_first}Vo.setSysName(systemDesignVo.getModuleName());
			}
		}
		</#if>
		<#if table.includeModuleId>
		if (StringUtils.isEmpty(${table.entityName?uncap_first}Vo.getModuleName())) {
			ModuleInfoVo moduleInfoVo = moduleInfoClientService.getDictionaryById(${table.entityName?uncap_first}Vo.getModuleId());
			if (moduleInfoVo != null) {
				${table.entityName?uncap_first}Vo.setModuleName(moduleInfoVo.getModuleName());
			}
		}
		</#if>
		<#if table.includeParentId>
		boolean hasChildren = ${table.entityName?uncap_first}Vo.isHasChildren();
		if(hasChildren == false) {
			String parentId = ${table.entityName?uncap_first}Vo.getId();
			${table.entityName}QueryParam ${table.entityName?uncap_first}QueryParam = new ${table.entityName}QueryParam();
			${table.entityName?uncap_first}QueryParam.setParentId(parentId);
			List<${table.entityName}Vo> ${table.entityName?uncap_first}VoList = ${table.entityName?uncap_first}ClientService.list(${table.entityName?uncap_first}QueryParam);
			if(${table.entityName?uncap_first}VoList!=null && ${table.entityName?uncap_first}VoList.size()>0) {
				${table.entityName?uncap_first}Vo.setHasChildren(true);
			}
		}
		</#if>
	}

	</#if>
}
