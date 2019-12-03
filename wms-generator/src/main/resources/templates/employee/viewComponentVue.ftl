<template> 
  <el-card class="form-container" shadow="never">
    <el-form :model="${table.entityName?uncap_first}" :rules="rules" ref="${table.entityName?uncap_first}From" label-width="150px">
      <#list table.columnList as column>
      <#if column.columnKey=="PRI">
      <el-form-item label="${column.propertyComment}：" prop="${column.propertyName}">
        {{${table.entityName?uncap_first}.${column.propertyName}}}
      </el-form-item>
      <#elseif column.columnName?ends_with("sys_id")>
      <el-form-item label="${column.propertyComment?replace("编号","")}：" prop="${column.propertyName}">
        {{${table.entityName?uncap_first}.${column.propertyName?replace("Id","Name")}}}
      </el-form-item>
      <#elseif column.columnName?ends_with("module_id")>
      <el-form-item label="${column.propertyComment?replace("编号","")}：" prop="${column.propertyName}">
        {{${table.entityName?uncap_first}.${column.propertyName?replace("Id","Name")}}}
      </el-form-item>
      <#elseif column.columnName?ends_with("tenant_id")>
      <el-form-item label="${column.propertyComment?replace("编号","")}：" prop="${column.propertyName}">
        {{${table.entityName?uncap_first}.${column.propertyName?replace("Id","Name")}}}
      </el-form-item>
      <#elseif column.selectable>
      <#if column.ynSelectable>
      <el-form-item label="${column.propertyComment}：" prop="${column.propertyName}">
        <el-switch
          v-model="${table.entityName?uncap_first}.${column.propertyName}"
          :active-value="1"
          :inactive-value="0">
        </el-switch>
      </el-form-item>
      <#else>
      <el-form-item label="${column.propertyComment}：" prop="${column.propertyName}">
        {{${table.entityName?uncap_first}.${column.propertyName} | format${column.propertyName?cap_first}}}
      </el-form-item>
      </#if>
      <#elseif column.propertyType=="Date">
      <#if column.dataType="date">
      <el-form-item label="${column.propertyComment}：" prop="${column.propertyName}">
        {{${table.entityName?uncap_first}.${column.propertyName}}}
      </el-form-item>
      <#else>
      <el-form-item label="${column.propertyComment}：" prop="${column.propertyName}">
        {{${table.entityName?uncap_first}.${column.propertyName}}}
      </el-form-item>
      </#if>
      <#elseif column.dataType == "int" && column.propertySelect == false>
      <el-form-item label="${column.propertyComment}：" prop="${column.propertyName}">
      	{{${table.entityName?uncap_first}.${column.propertyName}}}
      </el-form-item>
      <#elseif column.dataType == "decimal">
      <el-form-item label="${column.propertyComment}：" prop="${column.propertyName}">
      	{{${table.entityName?uncap_first}.${column.propertyName}}}
      </el-form-item>
      <#else>
      <el-form-item label="${column.propertyComment}：" prop="${column.propertyName}">
      	{{${table.entityName?uncap_first}.${column.propertyName}}}
      </el-form-item>
      </#if>
      </#list>      
    </el-form>
  </el-card>
</template>
<script>
  import {get${table.entityName}} from '@/api/${table.entityName?uncap_first}'
  <#if table.includeSysId>
  import {fetchList as fetchSystemDesignList} from '@/api/systemDesign';
  </#if>
  <#if table.includeModuleId>
  import {fetchList as fetchModuleInfoList} from '@/api/moduleInfo';
  </#if>
  <#if table.includeTenantId>
  import {fetchList as fetchTenantInfoList} from '@/api/tenantInfo';
  </#if>
  <#if table.includeDate>
  import {formatDate} from '@/utils/date';
  </#if>

  const default${table.entityName?cap_first}={
    <#list table.columnList as column>
    <#if "PRI" != column.columnKey>
    <#if "int" == column.dataType>
    <#if column.selectable>
    <#if column.ynSelectable>
    ${column.propertyName}: 1<#if column_has_next>,</#if>
    <#else>
    ${column.propertyName}: ''<#if column_has_next>,</#if>
    </#if>   
    <#else>
    ${column.propertyName}: 0<#if column_has_next>,</#if>
    </#if>
    <#elseif "decimal" == column.dataType>
    ${column.propertyName}: null<#if column_has_next>,</#if>
    <#else>
    ${column.propertyName}: ''<#if column_has_next>,</#if>
    </#if>
    </#if>
    </#list>
  };
  <#list table.columnList as column>
  <#if column.selectable>
  <#if column.ynSelectable == false>
  const default${column.propertyName?cap_first}Options=[
    <#list column.propertyOptionList as option>
    {
      value: ${option.value},
      label: '${option.text}'
    }<#if option_has_next>,</#if>
    </#list>  
  ];
  </#if>
  </#if>
  </#list>
  export default {
    name: '${table.entityName}View',
    data() {
      return {
        ${table.entityName?uncap_first}:Object.assign({}, default${table.entityName}),
        <#list table.columnList as column>
        <#if column.selectable>
      	<#if column.ynSelectable == false>
        ${column.propertyName}Options: Object.assign({},default${column.propertyName?cap_first}Options),
        </#if>
        </#if>
        </#list>
        rules: {
        }
      }
    },
    created() {
      get${table.entityName?cap_first}(this.$route.query.id).then(response => {
	      <#if table.includeDate>
	      let data = response.data;
	      <#list table.columnList as column>
	      <#if column.propertyType=="Date">
	      <#if column.dataType="date">
	      data.${column.propertyName} = formatDate(new Date(data.${column.propertyName}), 'yyyy-MM-dd');
	      <#else>
	      data.${column.propertyName} = formatDate(new Date(data.${column.propertyName}), 'yyyy-MM-dd hh:mm:ss');
	      </#if>
	      </#if>
	      </#list>
	      this.${table.entityName?uncap_first} = data;
	      //this.${table.entityName?uncap_first} = response.data;
	      <#else>
	      this.${table.entityName?uncap_first} = response.data;
	      </#if>
      });
    },
    filters: {
      <#list table.columnList as column>
      <#if column.selectable>
      <#if column.ynSelectable == false>
      format${column.propertyName?cap_first}(${column.propertyName}){
      	for(let i=0;i<default${column.propertyName?cap_first}Options.length;i++){
      		if(${column.propertyName}===default${column.propertyName?cap_first}Options[i].value){
      			return default${column.propertyName?cap_first}Options[i].label;
      		}
      	}
      },
      </#if>
      </#if>
      </#list>
    },
    methods: {
    }
  }
</script>
<style>
</style>