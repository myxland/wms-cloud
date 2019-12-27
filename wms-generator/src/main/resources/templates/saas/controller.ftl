package ${domainName}.${projectName}.controller;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import ${domainName}.common.api.CommonPage;
import ${domainName}.common.api.CommonResult;
import ${domainName}.${projectNameApi}.dto.${table.entityName}QueryParam;
import ${domainName}.${projectNameApi}.entity.${table.entityName};
import ${domainName}.${projectName}.service.I${table.entityName}Service;

@RestController
@RequestMapping("/${table.entityName?uncap_first}")
public class ${table.entityName}Controller {

	@Autowired
	private I${table.entityName}Service ${table.entityName?uncap_first}Service;

	@RequestMapping(value = "/select/{id}", method = RequestMethod.GET)
	public Object getById(@PathVariable("id") Long id) {
		${table.entityName} ${table.entityName?uncap_first} = ${table.entityName?uncap_first}Service.getById(id);

		return CommonResult.success(${table.entityName?uncap_first});
	}

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public Object list() {
		List<${table.entityName}> ${table.entityName?uncap_first}List = ${table.entityName?uncap_first}Service.list();

		return ${table.entityName?uncap_first}List;
	}

	@RequestMapping(value = "/page", method = RequestMethod.GET)
	public Object page(${table.entityName}QueryParam ${table.entityName?uncap_first}QueryParam,
			@RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
			@RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize) {

		IPage<${table.entityName}> page = new Page<${table.entityName}>(pageNum, pageSize);
		QueryWrapper<${table.entityName}> queryWrapper${table.entityName} = new QueryWrapper<${table.entityName}>();
		queryWrapper${table.entityName}.lambda()
		<#list table.columnList as column>
				.eq(${table.entityName?uncap_first}QueryParam.get${column.propertyName?cap_first}() != null, ${table.entityName}::get${column.propertyName?cap_first}, ${table.entityName?uncap_first}QueryParam.get${column.propertyName?cap_first}())
		</#list>
				;

		IPage<${table.entityName}> ${table.entityName?uncap_first}Page = ${table.entityName?uncap_first}Service.page(page, queryWrapper${table.entityName});

		return CommonPage.restPage(${table.entityName?uncap_first}Page);
	}

	@RequestMapping(value = "/insert", method = RequestMethod.POST)
	public Object insert(@RequestBody ${table.entityName} ${table.entityName?uncap_first}) {
		boolean success = ${table.entityName?uncap_first}Service.save(${table.entityName?uncap_first});

		return success ? CommonResult.success(success) : CommonResult.failed();
	}

	@RequestMapping(value = "/update", method = RequestMethod.PUT)
	public Object update(@RequestBody ${table.entityName} ${table.entityName?uncap_first}) {
		boolean success = ${table.entityName?uncap_first}Service.updateById(${table.entityName?uncap_first});

		return success ? CommonResult.success(success) : CommonResult.failed();
	}

	<#if table.includeStatus>
	@RequestMapping(value = "/update/{id}/status/{status}", method = RequestMethod.PUT)
	public Object updateStatus(@PathVariable("id") Long id, @PathVariable("status") Integer status) {
		UpdateWrapper<${table.entityName}> updateWrapper${table.entityName} = new UpdateWrapper<${table.entityName}>();
		<#list table.columnList as column>
		<#if column.columnName?ends_with("_status")>
		updateWrapper${table.entityName}.lambda().set(${table.entityName}::get${column.propertyName?cap_first}, status).eq(${table.entityName}::getId, id);
		</#if>
		</#list>
		boolean success = ${table.entityName?uncap_first}Service.update(updateWrapper${table.entityName});

		return success ? CommonResult.success(success) : CommonResult.failed();
	}

	</#if>
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
	public Object delete(@PathVariable("id") Long id) {
		boolean success = ${table.entityName?uncap_first}Service.removeById(id);
		
		return success ? CommonResult.success(success) : CommonResult.failed();
	}

	@RequestMapping(value = "/delete/ids/{ids}", method = RequestMethod.DELETE)
	public Object deleteByIds(@PathVariable("ids") String ids) {
		List<Long> idList = Arrays.asList(ids.split(",")).stream().map(id -> Long.parseLong(id))
				.collect(Collectors.toList());
		boolean success = ${table.entityName?uncap_first}Service.removeByIds(idList);
		
		return success ? CommonResult.success(success) : CommonResult.failed();
	}
	
}
