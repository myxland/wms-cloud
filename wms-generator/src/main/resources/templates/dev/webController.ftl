package ${domainName}.${projectNameWeb}.controller;

import java.util.Arrays;

<#if table.includeTenantId>
import org.apache.commons.lang3.StringUtils;
</#if>
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import ${domainName}.${projectNameApi}.client.service.${table.entityName}ClientService;
<#if table.includeTenantId>
import ${domainName}.${projectNameApi}.client.service.TenantInfoClientService;
</#if>
import ${domainName}.${projectNameApi}.dto.${table.entityName}QueryParam;
import ${domainName}.${projectNameApi}.entity.${table.entityName};
import ${domainName}.${projectNameApi}.vo.${table.entityName}Vo;
<#if table.includeTenantId>
import ${domainName}.${projectNameApi}.vo.TenantInfoVo;
</#if>
import ${domainName}.common.api.CommonPage;
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

	@ApiOperation(value = "根据参数查询${table.tableComment}列表")
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	@ResponseBody
	public CommonResult<CommonPage<${table.entityName}Vo>> list(${table.entityName}QueryParam ${table.entityName?uncap_first}QueryParam, int pageNum,
			int pageSize) {
		Page<${table.entityName}Vo> ${table.entityName?uncap_first}VoPage = ${table.entityName?uncap_first}ClientService.page(${table.entityName?uncap_first}QueryParam, pageNum, pageSize, "id", "desc");
		${table.entityName?uncap_first}VoPage.getRecords().stream().forEach(v->wrappperVo(v));

		CommonPage<${table.entityName}Vo> ${table.entityName?uncap_first}CommonPage = CommonPage.restPage(${table.entityName?uncap_first}VoPage);

		return CommonResult.success(${table.entityName?uncap_first}CommonPage);
	}

	<#if table.includeSingleUpdatable>
	<#list table.singleUpdatableColumnList as column>
	@ApiOperation(value = "更新${table.tableComment}${column.propertyComment}")
	@RequestMapping(value = "/update/${column.propertyName}", method = RequestMethod.POST)
	@ResponseBody
	public CommonResult<Boolean> update${column.propertyName?cap_first}(@RequestParam("ids") String ids,
			@RequestParam("${column.propertyName}") ${column.propertyType} ${column.propertyName}) {
		Arrays.asList(ids.split(",")).forEach(n -> {
			Long id = Long.parseLong(n);
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
			Long id = Long.parseLong(n);
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
	public CommonResult<${table.entityName}Vo> create(@RequestBody ${table.entityName} ${table.entityName?uncap_first}) {
		${table.entityName}Vo ${table.entityName?uncap_first}Vo = ${table.entityName?uncap_first}ClientService.save(${table.entityName?uncap_first});

		return CommonResult.success(${table.entityName?uncap_first}Vo);
	}

	@ApiOperation(value = "根据ID查询${table.tableComment}")
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ResponseBody
	public CommonResult<${table.entityName}Vo> getById(@PathVariable("id") Long id) {
		${table.entityName}Vo ${table.entityName?uncap_first}Vo = ${table.entityName?uncap_first}ClientService.getById(id);
		wrappperVo(${table.entityName?uncap_first}Vo);
		
		return CommonResult.success(${table.entityName?uncap_first}Vo);
	}

	@ApiOperation(value = "根据参数更新${table.tableComment}信息")
	@RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
	@ResponseBody
	public CommonResult<${table.entityName}Vo> getById(@RequestBody ${table.entityName} ${table.entityName?uncap_first}) {
		Long id = ${table.entityName?uncap_first}.getId();
		${table.entityName}Vo ${table.entityName?uncap_first}Vo = ${table.entityName?uncap_first}ClientService.updatePatchById(id, ${table.entityName?uncap_first});

		return CommonResult.success(${table.entityName?uncap_first}Vo);
	}
	
	@ApiOperation(value = "根据ID删除${table.tableComment}")
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
	@ResponseBody
	public CommonResult<Object> removeById(@PathVariable("id") Long id) {
		CommonResult<Object> commonResult = ${table.entityName?uncap_first}ClientService.removeById(id);

		return commonResult;
	}
	
	private void wrappperVo(${table.entityName}Vo ${table.entityName?uncap_first}Vo) {
		<#if table.includeTenantId>
		if (StringUtils.isEmpty(${table.entityName?uncap_first}Vo.getTenantName())) {
			TenantInfoVo tenantInfoVo = tenantInfoClientService.getById(${table.entityName?uncap_first}Vo.getTenantId());
			if (tenantInfoVo != null) {
				${table.entityName?uncap_first}Vo.setTenantName(tenantInfoVo.getTenantName());
			}
		}
		</#if>
	}

}
