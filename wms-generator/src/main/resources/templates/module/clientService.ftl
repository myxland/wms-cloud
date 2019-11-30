package ${domainName}.${projectNameApi}.client.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import ${domainName}.${projectNameApi}.dto.${table.entityName}QueryParam;
import ${domainName}.${projectNameApi}.entity.${table.entityName};
import ${domainName}.${projectNameApi}.vo.${table.entityName}Vo;
import ${domainName}.common.api.CommonResult;

@FeignClient(value = "${artifactId?upper_case}", contextId = "${table.entityName}")
public interface ${table.entityName}ClientService {
	@RequestMapping(value = "/${table.restSegment}s/{id}", method = RequestMethod.GET)
	public ${table.entityName}Vo getById(@PathVariable("id") Long id);

	<#if table.includeTenantOne2One>
	@RequestMapping(value = "/${table.restSegment}s/tenant-id/{tenant-id}", method = RequestMethod.GET)
	public ${table.entityName}Vo getByTenantId(@PathVariable("tenant-id") Long tenantId);

	</#if>
	@RequestMapping(value = "/${table.restSegment}s", method = RequestMethod.GET)
	public Page<${table.entityName}Vo> page(@RequestBody ${table.entityName}QueryParam ${table.entityName?uncap_first}QueryParam,
			@RequestParam(value = "page", defaultValue = "1") int page, //
			@RequestParam(value = "rows", defaultValue = "10") int rows, //
			@RequestParam(value = "sort") String sort, // 排序列字段名
			@RequestParam(value = "order") String order // 可以是 'asc' 或者 'desc'，默认值是 'asc'
	);

	@RequestMapping(value = "/${table.restSegment}s", method = RequestMethod.POST)
	public ${table.entityName}Vo save(@RequestBody ${table.entityName} ${table.entityName?uncap_first});

	@RequestMapping(value = "/${table.restSegment}s/{id}", method = RequestMethod.PUT)
	public ${table.entityName}Vo updateById(@PathVariable("id") Long id, @RequestBody ${table.entityName} ${table.entityName?uncap_first});

	@RequestMapping(value = "/${table.restSegment}s/{id}", method = RequestMethod.PATCH)
	public ${table.entityName}Vo updatePatchById(@PathVariable("id") Long id, @RequestBody ${table.entityName} ${table.entityName?uncap_first});

	@RequestMapping(value = "/${table.restSegment}s/{id}", method = RequestMethod.DELETE)
	public CommonResult<Object> removeById(@PathVariable("id") Long id);
}

