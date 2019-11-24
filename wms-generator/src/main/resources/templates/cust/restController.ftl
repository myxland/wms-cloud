package ${domainName}.${projectName}.rest;

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
import ${domainName}.${projectNameApi}.dto.${table.entityName}QueryParam;
<#if table.includeSysId>
import ${domainName}.${projectNameApi}.entity.SystemDesign;
</#if>
import ${domainName}.${projectNameApi}.entity.${table.entityName};
import ${domainName}.${projectNameApi}.vo.${table.entityName}Vo;
import ${domainName}.common.api.CommonResult;
import ${domainName}.${projectName}.service.IIdService;
<#if table.includeSysId>
import ${domainName}.${projectName}.service.ISystemDesignService;
</#if>
import ${domainName}.${projectName}.service.I${table.entityName}Service;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

@Api(value = "${table.tableComment}", tags = { "${table.tableComment}操作接口" })
@RestController
@Slf4j
public class ${table.entityName}RestController {

	@Autowired
	private I${table.entityName}Service ${table.entityName?uncap_first}Service;
	<#if table.includeSysId>
	@Autowired
	private ISystemDesignService systemDesignService;
	</#if>
	@Autowired
	private IIdService idService;

	@ApiOperation(value = "根据ID查询${table.tableComment}")
	@RequestMapping(value = "/${table.restSegment}s/{id}", method = RequestMethod.GET)
	public ${table.entityName}Vo getById(@PathVariable("id") Long id) {
		${table.entityName} ${table.entityName?uncap_first} = ${table.entityName?uncap_first}Service.getById(id);

		return entity2vo(${table.entityName?uncap_first});
	}

	@ApiOperation(value = "根据参数查询${table.tableComment}列表")
	@RequestMapping(value = "/${table.restSegment}s", method = RequestMethod.GET)
	public Page<${table.entityName}Vo> page(@RequestBody ${table.entityName}QueryParam ${table.entityName?uncap_first}QueryParam,
			@RequestParam(value = "page", defaultValue = "1") int page, //
			@RequestParam(value = "rows", defaultValue = "10") int rows, //
			@RequestParam(value = "sort") String sort, // 排序列字段名
			@RequestParam(value = "order") String order // 可以是 'asc' 或者 'desc'，默认值是 'asc'
	) {
		IPage<${table.entityName}> page${table.entityName} = new Page<${table.entityName}>(page, rows);
		QueryWrapper<${table.entityName}> queryWrapper${table.entityName} = new QueryWrapper<${table.entityName}>();
		queryWrapper${table.entityName}.orderBy(StringUtils.isNotEmpty(sort), "desc".equals(order), sort);
		queryWrapper${table.entityName}.lambda()
		<#list table.columnList as column>
				.eq(${table.entityName?uncap_first}QueryParam.get${column.propertyName?cap_first}() != null, ${table.entityName}::get${column.propertyName?cap_first}, ${table.entityName?uncap_first}QueryParam.get${column.propertyName?cap_first}())
		</#list>
				;

		IPage<${table.entityName}> ${table.entityName?uncap_first}Page = ${table.entityName?uncap_first}Service.page(page${table.entityName}, queryWrapper${table.entityName});

		Page<${table.entityName}Vo> ${table.entityName?uncap_first}VoPage = new Page<${table.entityName}Vo>(page, rows);
		${table.entityName?uncap_first}VoPage.setCurrent(${table.entityName?uncap_first}Page.getCurrent());
		${table.entityName?uncap_first}VoPage.setPages(${table.entityName?uncap_first}Page.getPages());
		${table.entityName?uncap_first}VoPage.setSize(${table.entityName?uncap_first}Page.getSize());
		${table.entityName?uncap_first}VoPage.setTotal(${table.entityName?uncap_first}Page.getTotal());
		${table.entityName?uncap_first}VoPage.setRecords(${table.entityName?uncap_first}Page.getRecords().stream()//
				.map(e -> entity2vo(e))//
				.collect(Collectors.toList()));

		return ${table.entityName?uncap_first}VoPage;
	}

	@ApiOperation(value = "新增${table.tableComment}")
	@RequestMapping(value = "/${table.restSegment}s", method = RequestMethod.POST)
	public ${table.entityName}Vo save(@RequestBody ${table.entityName} ${table.entityName?uncap_first}) {
		if (${table.entityName?uncap_first}.getId() == null || ${table.entityName?uncap_first}.getId().compareTo(0L) <= 0) {
			${table.entityName?uncap_first}.setId(idService.selectId());
		}
		boolean success = ${table.entityName?uncap_first}Service.save(${table.entityName?uncap_first});
		if (success) {
			${table.entityName} ${table.entityName?uncap_first}Database = ${table.entityName?uncap_first}Service.getById(${table.entityName?uncap_first}.getId());
			return entity2vo(${table.entityName?uncap_first}Database);
		}
		log.info("save ${table.entityName} fail，{}", ToStringBuilder.reflectionToString(${table.entityName?uncap_first}, ToStringStyle.JSON_STYLE));
		return null;
	}

	@ApiOperation(value = "更新${table.tableComment}全部信息")
	@RequestMapping(value = "/${table.restSegment}s/{id}", method = RequestMethod.PUT)
	public ${table.entityName}Vo updateById(@PathVariable("id") Long id, @RequestBody ${table.entityName} ${table.entityName?uncap_first}) {
		${table.entityName?uncap_first}.setId(id);
		boolean success = ${table.entityName?uncap_first}Service.updateById(${table.entityName?uncap_first});
		if (success) {
			${table.entityName} ${table.entityName?uncap_first}Database = ${table.entityName?uncap_first}Service.getById(id);
			return entity2vo(${table.entityName?uncap_first}Database);
		}
		log.info("update ${table.entityName} fail，{}", ToStringBuilder.reflectionToString(${table.entityName?uncap_first}, ToStringStyle.JSON_STYLE));
		return null;
	}

	@ApiOperation(value = "根据参数更新${table.tableComment}信息")
	@RequestMapping(value = "/${table.restSegment}s/{id}", method = RequestMethod.PATCH)
	public ${table.entityName}Vo updatePatchById(@PathVariable("id") Long id, @RequestBody ${table.entityName} ${table.entityName?uncap_first}) {
		UpdateWrapper<${table.entityName}> updateWrapper${table.entityName} = new UpdateWrapper<${table.entityName}>();
		updateWrapper${table.entityName}.lambda()//
				.eq(${table.entityName}::getId, id)
				<#list table.columnList as column>
				<#if "PRI"==column.columnKey>
				// .set(${table.entityName?uncap_first}.get${column.propertyName?cap_first}() != null, ${table.entityName}::get${column.propertyName?cap_first}, ${table.entityName?uncap_first}.get${column.propertyName?cap_first}())
				<#else>
				.set(${table.entityName?uncap_first}.get${column.propertyName?cap_first}() != null, ${table.entityName}::get${column.propertyName?cap_first}, ${table.entityName?uncap_first}.get${column.propertyName?cap_first}())
				</#if>
				</#list>
				;

		boolean success = ${table.entityName?uncap_first}Service.update(updateWrapper${table.entityName});
		if (success) {
			${table.entityName} ${table.entityName?uncap_first}Database = ${table.entityName?uncap_first}Service.getById(id);
			return entity2vo(${table.entityName?uncap_first}Database);
		}
		log.info("partial update ${table.entityName} fail，{}",
				ToStringBuilder.reflectionToString(${table.entityName?uncap_first}, ToStringStyle.JSON_STYLE));
		return null;
	}

	@ApiOperation(value = "根据ID删除${table.tableComment}")
	@RequestMapping(value = "/${table.restSegment}s/{id}", method = RequestMethod.DELETE)
	public CommonResult<Object> removeById(@PathVariable("id") Long id) {
		boolean success = ${table.entityName?uncap_first}Service.removeById(id);
		return success ? CommonResult.success(success) : CommonResult.failed();
	}

	private ${table.entityName}Vo entity2vo(${table.entityName} ${table.entityName?uncap_first}) {
		String jsonString = JSON.toJSONString(${table.entityName?uncap_first});
		${table.entityName}Vo ${table.entityName?uncap_first}Vo = JSON.parseObject(jsonString, ${table.entityName}Vo.class);
		<#if table.includeSysId>
		<#list table.columnList as column>
		<#if column.columnName?ends_with("sys_id")>
		if (StringUtils.isEmpty(${table.entityName?uncap_first}Vo.get${column.propertyName?cap_first?replace("SysId","ModuleName")}())) {
			SystemDesign systemDesign = systemDesignService.getById(${table.entityName?uncap_first}.get${column.propertyName?cap_first}());
			if (systemDesign != null) {
				${table.entityName?uncap_first}Vo.set${column.propertyName?cap_first?replace("SysId","ModuleName")}(systemDesign.getModuleName());
			}
		}
		</#if>
		</#list>
		</#if>
		return ${table.entityName?uncap_first}Vo;
	}

}
