package ${domainName}.${projectName}.rest;

<#if table.includeDate>
import java.util.Date;
</#if>
import java.util.List;
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
import ${domainName}.${projectNameApi}.dto.${table.entityName}AddParam;
import ${domainName}.${projectNameApi}.dto.${table.entityName}QueryParam;
import ${domainName}.${projectNameApi}.dto.${table.entityName}UpdateParam;
<#if table.includeSysId>
import ${domainName}.${projectNameApi}.entity.SystemDesign;
</#if>
<#if table.includeModuleId>
import ${domainName}.${projectNameApi}.entity.ModuleInfo;
</#if>
<#if table.includeTenantId>
import ${domainName}.${projectNameApi}.entity.TenantInfo;
</#if>
import ${domainName}.${projectNameApi}.entity.${table.entityName};
import ${domainName}.${projectNameApi}.vo.${table.entityName}Vo;
import ${domainName}.common.api.CommonResult;
import ${domainName}.common.util.TranslateUtil;
<#if table.includeSysId>
import ${domainName}.${projectName}.service.ISystemDesignService;
</#if>
<#if table.includeTenantId>
import ${domainName}.${projectName}.service.ITenantInfoService;
</#if>
import ${domainName}.${projectName}.service.I${table.entityName}Service;

<#if table.includeDate>
import cn.hutool.core.date.DateUtil;
</#if>
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
	<#if table.includeTenantId>
	@Autowired
	private ITenantInfoService tenantInfoService;
	</#if>

	@ApiOperation(value = "根据ID查询${table.tableComment}")
	@RequestMapping(value = "/${table.restSegment}s/{id}", method = RequestMethod.GET)
	public ${table.entityName}Vo getById(@PathVariable("id") String id) {
		${table.entityName} ${table.entityName?uncap_first} = ${table.entityName?uncap_first}Service.getById(id);

		return entity2vo(${table.entityName?uncap_first});
	}

	<#if table.includeTenantOne2One>
	@ApiOperation(value = "根据ID查询${table.tableComment}")
	@RequestMapping(value = "/${table.restSegment}s/tenant-id/{tenant-id}", method = RequestMethod.GET)
	public ${table.entityName}Vo getByTenantId(@PathVariable("tenant-id") String tenantId) {
		QueryWrapper<${table.entityName}> queryWrapper${table.entityName} = new QueryWrapper<${table.entityName}>();
		queryWrapper${table.entityName}.lambda().eq(${table.entityName}::getTenantId, tenantId);
		${table.entityName} ${table.entityName?uncap_first} = ${table.entityName?uncap_first}Service.getOne(queryWrapper${table.entityName});

		return entity2vo(${table.entityName?uncap_first});
	}

	</#if>
	@ApiOperation(value = "根据参数查询${table.tableComment}列表")
	@RequestMapping(value = "/${table.restSegment}s/list", method = RequestMethod.GET)
	public List<${table.entityName}Vo> list(@RequestBody ${table.entityName}QueryParam ${table.entityName?uncap_first}QueryParam) {
		QueryWrapper<${table.entityName}> queryWrapper${table.entityName} = new QueryWrapper<${table.entityName}>();
		queryWrapper${table.entityName}.lambda()
		<#list table.columnList as column>
				.eq(${table.entityName?uncap_first}QueryParam.get${column.propertyName?cap_first}() != null, ${table.entityName}::get${column.propertyName?cap_first}, ${table.entityName?uncap_first}QueryParam.get${column.propertyName?cap_first}())
		<#if column.likeable>
				.like(${table.entityName?uncap_first}QueryParam.get${column.propertyName?cap_first}Like() != null, ${table.entityName}::get${column.propertyName?cap_first},${table.entityName?uncap_first}QueryParam.get${column.propertyName?cap_first}Like())
		</#if>
		<#if column.dataType=="date" || column.dataType=="datetime" || column.dataType=="timestamp" || column.dataType=="time">
				.ge(${table.entityName?uncap_first}QueryParam.get${column.propertyName?cap_first}Start() != null, ${table.entityName}::get${column.propertyName?cap_first},${table.entityName?uncap_first}QueryParam.get${column.propertyName?cap_first}Start() == null ? null: DateUtil.beginOfDay(${table.entityName?uncap_first}QueryParam.get${column.propertyName?cap_first}Start()))
				.le(${table.entityName?uncap_first}QueryParam.get${column.propertyName?cap_first}End() != null, ${table.entityName}::get${column.propertyName?cap_first},${table.entityName?uncap_first}QueryParam.get${column.propertyName?cap_first}End() == null ? null: DateUtil.endOfDay(${table.entityName?uncap_first}QueryParam.get${column.propertyName?cap_first}End()))
		</#if>
		</#list>
		<#if table.includeParentId>
		<#list table.columnList as column>
		<#if column.columnName?ends_with("_parent_id")>
				.eq(${table.entityName?uncap_first}QueryParam.getParentId()!=null,${table.entityName}::get${column.propertyName?cap_first}, ${table.entityName?uncap_first}QueryParam.getParentId())
		</#if>
		</#list>
		</#if>
				;

		List<${table.entityName}> ${table.entityName?uncap_first}List = ${table.entityName?uncap_first}Service.list(queryWrapper${table.entityName});

		List<${table.entityName}Vo> ${table.entityName?uncap_first}VoList = TranslateUtil.translateList(${table.entityName?uncap_first}List, ${table.entityName}Vo.class);

		return ${table.entityName?uncap_first}VoList;
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
		queryWrapper${table.entityName}.orderBy(StringUtils.isNotEmpty(sort), "asc".equals(order), sort);
		queryWrapper${table.entityName}.lambda()
		<#list table.columnList as column>
				.eq(${table.entityName?uncap_first}QueryParam.get${column.propertyName?cap_first}() != null, ${table.entityName}::get${column.propertyName?cap_first}, ${table.entityName?uncap_first}QueryParam.get${column.propertyName?cap_first}())
		<#if column.likeable>
				.like(${table.entityName?uncap_first}QueryParam.get${column.propertyName?cap_first}Like() != null, ${table.entityName}::get${column.propertyName?cap_first},${table.entityName?uncap_first}QueryParam.get${column.propertyName?cap_first}Like())
		</#if>
		<#if column.dataType=="date" || column.dataType=="datetime" || column.dataType=="timestamp" || column.dataType=="time">
				.ge(${table.entityName?uncap_first}QueryParam.get${column.propertyName?cap_first}Start() != null, ${table.entityName}::get${column.propertyName?cap_first},${table.entityName?uncap_first}QueryParam.get${column.propertyName?cap_first}Start() == null ? null: DateUtil.beginOfDay(${table.entityName?uncap_first}QueryParam.get${column.propertyName?cap_first}Start()))
				.le(${table.entityName?uncap_first}QueryParam.get${column.propertyName?cap_first}End() != null, ${table.entityName}::get${column.propertyName?cap_first},${table.entityName?uncap_first}QueryParam.get${column.propertyName?cap_first}End() == null ? null: DateUtil.endOfDay(${table.entityName?uncap_first}QueryParam.get${column.propertyName?cap_first}End()))
		</#if>
		</#list>
		<#if table.includeParentId>
		<#list table.columnList as column>
		<#if column.columnName?ends_with("_parent_id")>
				.eq(${table.entityName?uncap_first}QueryParam.getParentId()!=null,${table.entityName}::get${column.propertyName?cap_first}, ${table.entityName?uncap_first}QueryParam.getParentId())
				.isNull(${table.entityName?uncap_first}QueryParam.getParentId()==null, ${table.entityName}::get${column.propertyName?cap_first})
		</#if>
		</#list>
		</#if>
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
	
	<#if table.includeAggregation>
	@ApiOperation(value = "根据参数查询${table.tableComment}统计")
	@RequestMapping(value = "/${table.restSegment}s/aggregation", method = RequestMethod.GET)
	public ${table.entityName}Vo aggregation(@RequestBody ${table.entityName}QueryParam ${table.entityName?uncap_first}QueryParam) {
		QueryWrapper<${table.entityName}> queryWrapper${table.entityName} = new QueryWrapper<${table.entityName}>();
		queryWrapper${table.entityName}.lambda()
		<#list table.columnList as column>
				.eq(${table.entityName?uncap_first}QueryParam.get${column.propertyName?cap_first}() != null, ${table.entityName}::get${column.propertyName?cap_first}, ${table.entityName?uncap_first}QueryParam.get${column.propertyName?cap_first}())
		<#if column.likeable>
				.like(${table.entityName?uncap_first}QueryParam.get${column.propertyName?cap_first}Like() != null, ${table.entityName}::get${column.propertyName?cap_first},${table.entityName?uncap_first}QueryParam.get${column.propertyName?cap_first}Like())
		</#if>
		<#if column.dataType=="date" || column.dataType=="datetime" || column.dataType=="timestamp" || column.dataType=="time">
				.ge(${table.entityName?uncap_first}QueryParam.get${column.propertyName?cap_first}Start() != null, ${table.entityName}::get${column.propertyName?cap_first},${table.entityName?uncap_first}QueryParam.get${column.propertyName?cap_first}Start() == null ? null: DateUtil.beginOfDay(${table.entityName?uncap_first}QueryParam.get${column.propertyName?cap_first}Start()))
				.le(${table.entityName?uncap_first}QueryParam.get${column.propertyName?cap_first}End() != null, ${table.entityName}::get${column.propertyName?cap_first},${table.entityName?uncap_first}QueryParam.get${column.propertyName?cap_first}End() == null ? null: DateUtil.endOfDay(${table.entityName?uncap_first}QueryParam.get${column.propertyName?cap_first}End()))
		</#if>
		</#list>
		<#if table.includeParentId>
		<#list table.columnList as column>
		<#if column.columnName?ends_with("_parent_id")>
				.eq(${table.entityName?uncap_first}QueryParam.getParentId()!=null,${table.entityName}::get${column.propertyName?cap_first}, ${table.entityName?uncap_first}QueryParam.getParentId())
				.isNull(${table.entityName?uncap_first}QueryParam.getParentId()==null, ${table.entityName}::get${column.propertyName?cap_first})
		</#if>
		</#list>
		</#if>
				;

		${table.entityName} ${table.entityName?uncap_first} = ${table.entityName?uncap_first}Service.getAggregation(queryWrapper${table.entityName});
		
		return entity2vo(${table.entityName?uncap_first});
	}

	</#if>
	@ApiOperation(value = "新增${table.tableComment}")
	@RequestMapping(value = "/${table.restSegment}s", method = RequestMethod.POST)
	public String save(@RequestBody ${table.entityName}AddParam ${table.entityName?uncap_first}AddParam) {
		return ${table.entityName?uncap_first}Service.save(${table.entityName?uncap_first}AddParam);
	}

	@ApiOperation(value = "更新${table.tableComment}全部信息")
	@RequestMapping(value = "/${table.restSegment}s/{id}", method = RequestMethod.PUT)
	public boolean updateById(@PathVariable("id") String id, @RequestBody ${table.entityName}UpdateParam ${table.entityName?uncap_first}UpdateParam) {
		${table.entityName?uncap_first}UpdateParam.setId(id);
		return ${table.entityName?uncap_first}Service.updateById(${table.entityName?uncap_first}UpdateParam);
	}

	@ApiOperation(value = "根据参数更新${table.tableComment}信息")
	@RequestMapping(value = "/${table.restSegment}s/{id}", method = RequestMethod.PATCH)
	public ${table.entityName}Vo updatePatchById(@PathVariable("id") String id, @RequestBody ${table.entityName} ${table.entityName?uncap_first}) {
        ${table.entityName} ${table.entityName?uncap_first}Where = ${table.entityName}.builder()//
				.id(id)//
				.build();
		UpdateWrapper<${table.entityName}> updateWrapper${table.entityName} = new UpdateWrapper<${table.entityName}>();
		updateWrapper${table.entityName}.setEntity(${table.entityName?uncap_first}Where);
		updateWrapper${table.entityName}.lambda()//
				//.eq(${table.entityName}::getId, id)
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
	public CommonResult<Object> removeById(@PathVariable("id") String id) {
		boolean success = ${table.entityName?uncap_first}Service.removeById(id);
		return success ? CommonResult.success(success) : CommonResult.failed();
	}

	private ${table.entityName}Vo entity2vo(${table.entityName} ${table.entityName?uncap_first}) {
		if (${table.entityName?uncap_first} == null) {
			return null;
		}

		String jsonString = JSON.toJSONString(${table.entityName?uncap_first});
		${table.entityName}Vo ${table.entityName?uncap_first}Vo = JSON.parseObject(jsonString, ${table.entityName}Vo.class);
		<#if table.includeSysId>
		<#list table.columnList as column>
		<#if column.columnName?ends_with("sys_id")>
		if (StringUtils.isEmpty(${table.entityName?uncap_first}Vo.get${column.propertyName?cap_first?replace("SysId","ModuleName")}())) {
			SystemDesign systemDesign = systemDesignService.getDictionaryById(${table.entityName?uncap_first}.get${column.propertyName?cap_first}());
			if (systemDesign != null) {
				${table.entityName?uncap_first}Vo.set${column.propertyName?cap_first?replace("SysId","ModuleName")}(systemDesign.getModuleName());
			}
		}
		</#if>
		</#list>
		</#if>
		<#if table.includeTenantId>
		<#list table.columnList as column>
		<#if column.columnName?ends_with("tenant_id")>
		if (StringUtils.isEmpty(${table.entityName?uncap_first}Vo.get${column.propertyName?replace("Id","Name")?cap_first}())) {
			TenantInfo tenantInfo = tenantInfoService.getDictionaryById(${table.entityName?uncap_first}.get${column.propertyName?cap_first}());
			if (tenantInfo != null) {
				${table.entityName?uncap_first}Vo.set${column.propertyName?replace("Id","Name")?cap_first}(tenantInfo.getTenantName());
			}
		}
		</#if>
		</#list>
		</#if>
		return ${table.entityName?uncap_first}Vo;
	}

}
