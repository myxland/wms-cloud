package ${domainName}.${projectNameApi}.client.service;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import ${domainName}.${projectNameApi}.dto.${table.entityName}AddParam;
import ${domainName}.${projectNameApi}.dto.${table.entityName}QueryParam;
import ${domainName}.${projectNameApi}.dto.${table.entityName}UpdateParam;
import ${domainName}.${projectNameApi}.vo.${table.entityName}Vo;
import ${domainName}.common.api.CommonResult;

@FeignClient(value = "${artifactId?upper_case}", contextId = "${table.entityName}")
public interface ${table.entityName}ClientService {
	@RequestMapping(value = "/${table.restSegment}s/{id}", method = RequestMethod.GET)
	public ${table.entityName}Vo getById(@PathVariable("id") String id);

	<#if table.includeTenantOne2One>
	@RequestMapping(value = "/${table.restSegment}s/tenant-id/{tenant-id}", method = RequestMethod.GET)
	public ${table.entityName}Vo getByTenantId(@PathVariable("tenant-id") String tenantId);

	</#if>
	<#if table.includeModuleOne2One>
	@RequestMapping(value = "/${table.restSegment}s/module-id/{module-id}", method = RequestMethod.GET)
	public ${table.entityName}Vo getByModuleId(@PathVariable("module-id") String moduleId);

	</#if>
	@RequestMapping(value = "/${table.restSegment}s/list", method = RequestMethod.GET)
	public List<${table.entityName}Vo> list(@RequestBody ${table.entityName}QueryParam ${table.entityName?uncap_first}QueryParam);

	@RequestMapping(value = "/${table.restSegment}s/count", method = RequestMethod.GET)
	public int count(@RequestBody ${table.entityName}QueryParam ${table.entityName?uncap_first}QueryParam);

	@RequestMapping(value = "/${table.restSegment}s", method = RequestMethod.GET)
	public Page<${table.entityName}Vo> page(@RequestBody ${table.entityName}QueryParam ${table.entityName?uncap_first}QueryParam,
			@RequestParam(value = "page", defaultValue = "1") int page, //
			@RequestParam(value = "rows", defaultValue = "10") int rows, //
			@RequestParam(value = "sort") String sort, // 排序列字段名
			@RequestParam(value = "order") String order // 可以是 'asc' 或者 'desc'，默认值是 'asc'
	);

	<#if table.includeAggregation>
	@RequestMapping(value = "/${table.restSegment}s/aggregation", method = RequestMethod.GET)
	public ${table.entityName}Vo aggregation(@RequestBody ${table.entityName}QueryParam ${table.entityName?uncap_first}QueryParam);

	</#if>
	@RequestMapping(value = "/${table.restSegment}s", method = RequestMethod.POST)
	public String save(@RequestBody ${table.entityName}AddParam ${table.entityName?uncap_first}AddParam);

	@RequestMapping(value = "/${table.restSegment}s/{id}", method = RequestMethod.PUT)
	public boolean updateById(@PathVariable("id") String id, @RequestBody ${table.entityName}UpdateParam ${table.entityName?uncap_first}UpdateParam);

	@RequestMapping(value = "/${table.restSegment}s/{id}", method = RequestMethod.DELETE)
	public CommonResult<Object> removeById(@PathVariable("id") String id);
}