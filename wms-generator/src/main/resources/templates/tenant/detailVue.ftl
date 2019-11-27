<template> 
  <el-card class="form-container" shadow="never">
    <el-form :model="${table.entityName?uncap_first}" :rules="rules" ref="${table.entityName?uncap_first}From" label-width="150px">
      <#list table.columnList as column>
      <#if column.columnKey=="PRI">
      <el-form-item label="${column.propertyComment}：" prop="${column.propertyName}">
        <el-input v-model="${table.entityName?uncap_first}.${column.propertyName}" :disabled="true"></el-input>
      </el-form-item>
      <#elseif column.columnName?ends_with("sys_id")>
      <el-form-item label="${column.propertyComment?replace("编号","")}：" prop="${column.propertyName}">
        <el-select v-model="${table.entityName?uncap_first}.${column.propertyName}" placeholder="请选择${column.propertyComment?replace("编号","")}"<#if column.defaultAddValue?default("")?trim?length gt 1> :disabled="true"</#if> clearable>
                <el-option
                  v-for="item in systemDesignOptions"
                  :key="item.value"
                  :label="item.label"
                  :value="item.value">
                </el-option>
        </el-select>
      </el-form-item>
      <#elseif column.columnName?ends_with("tenant_id")>
      <el-form-item label="${column.propertyComment?replace("编号","")}：" prop="${column.propertyName}">
        <el-select v-model="${table.entityName?uncap_first}.${column.propertyName}" placeholder="请选择${column.propertyComment?replace("编号","")}"<#if column.defaultAddValue?default("")?trim?length gt 1> :disabled="true"</#if> clearable>
                <el-option
                  v-for="item in tenantInfoOptions"
                  :key="item.value"
                  :label="item.label"
                  :value="item.value">
                </el-option>
        </el-select>
      </el-form-item>
      <#elseif column.selectable>
      <#if column.ynSelectable>
      <el-form-item label="${column.propertyComment}：" prop="${column.propertyName}">
        <el-switch
          v-model="${table.entityName?uncap_first}.${column.propertyName}"
          <#if column.defaultAddValue?default("")?trim?length gt 1>
          :disabled="true"
          </#if>
          :active-value="1"
          :inactive-value="0">
        </el-switch>
      </el-form-item>
      <#else>
      <el-form-item label="${column.propertyComment}：" prop="${column.propertyName}" clearable>
        <el-select
          v-model="${table.entityName?uncap_first}.${column.propertyName}"
          <#if column.defaultAddValue?default("")?trim?length gt 1>
          :disabled="true"
          </#if>
          placeholder="请选择${column.propertyComment}">
          <el-option
            v-for="item in ${column.propertyName}Options"
            :key="item.value"
            :label="item.label"
            :value="item.value">
          </el-option>
        </el-select>
      </el-form-item>      
      </#if>
      <#elseif column.propertyType=="Date">
      <#if column.dataType="date">
      <el-form-item label="${column.propertyComment}：" prop="${column.propertyName}">
        <el-date-picker
                class="input-width"
                v-model="${table.entityName?uncap_first}.${column.propertyName}"
                value-format="yyyy-MM-dd"
                type="date"
                <#if column.defaultAddValue?default("")?trim?length gt 1>
                :disabled="true"
                </#if>
                placeholder="请选择${column.propertyComment}">
        </el-date-picker>
      </el-form-item>
      <#else>
      <el-form-item label="${column.propertyComment}：" prop="${column.propertyName}">
        <el-date-picker
                class="input-width"
                v-model="${table.entityName?uncap_first}.${column.propertyName}"
                value-format="yyyy-MM-dd HH:mm:ss"
                type="datetime"
                <#if column.defaultAddValue?default("")?trim?length gt 1>
                :disabled="true"
                </#if>
                placeholder="请选择${column.propertyComment}">
        </el-date-picker>
      </el-form-item>
      </#if>
      <#elseif column.dataType == "int" && column.propertySelect == false>
      <el-form-item label="${column.propertyComment}：" prop="${column.propertyName}">
        <el-input-number v-model="${table.entityName?uncap_first}.${column.propertyName}" :min="0"<#if column.defaultAddValue?default("")?trim?length gt 1> :disabled="true"</#if> placeholder="${column.propertyComment}"></el-input-number>
      </el-form-item>
      <#elseif column.dataType == "decimal">
      <el-form-item label="${column.propertyComment}：" prop="${column.propertyName}">
        <el-input-number v-model="${table.entityName?uncap_first}.${column.propertyName}" :min="0"<#if column.columnName?ends_with("_ratio")> :max="1"</#if> :precision="${column.numericScale}"<#if column.defaultAddValue?default("")?trim?length gt 1> :disabled="true"</#if> placeholder="${column.propertyComment}"></el-input-number>
      </el-form-item>
      <#else>
      <el-form-item label="${column.propertyComment}：" prop="${column.propertyName}">
        <el-input v-model="${table.entityName?uncap_first}.${column.propertyName}"<#if column.defaultAddValue?default("")?trim?length gt 1> :disabled="true"</#if>></el-input>
      </el-form-item>
      </#if>
      </#list>
      <el-form-item>
        <el-button type="primary" @click="onSubmit('${table.entityName?uncap_first}From')">提交</el-button>
        <el-button v-if="!isEdit" @click="resetForm('${table.entityName?uncap_first}From')">重置</el-button>
      </el-form-item>
    </el-form>
  </el-card>
</template>
<script>
  import {create${table.entityName?cap_first}, get${table.entityName?cap_first},<#if table.includeTenantOne2One> get${table.entityName?cap_first}ByTenantId,</#if> update${table.entityName?cap_first}} from '@/api/${table.entityName?uncap_first}'
  <#if table.includeSysId>
  import {fetchList as fetchSystemDesignList} from '@/api/systemDesign';
  </#if>
  <#if table.includeTenantId>
  import {fetchList as fetchTenantInfoList} from '@/api/tenantInfo';
  </#if>
  <#if table.includeDate>
  import {formatDate} from '@/utils/date';
  </#if>
  import SingleUpload from '@/components/Upload/singleUpload'
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
    name: '${table.entityName?cap_first}Detail',
    components:{SingleUpload},
    props: {
      isEdit: {
        type: Boolean,
        default: false
      }
    },
    data() {
      return {
        ${table.entityName?uncap_first}:Object.assign({}, default${table.entityName?cap_first}),
        <#if table.includeSysId>
        systemDesignOptions:[],
        </#if>
        <#if table.includeTenantId>
        tenantInfoOptions:[],
        </#if>
        <#list table.columnList as column>
        <#if column.selectable>
      	<#if column.ynSelectable == false>
        ${column.propertyName}Options: Object.assign({},default${column.propertyName?cap_first}Options),
        </#if>
        </#if>
        </#list>
        rules: {
        <#if table.includeNotNullabe>
        <#list table.notNullabeColumnList as column>
        <#if column.selectable>
          ${column.propertyName}: [
            {required: true, message: '请选择${column.propertyComment}', trigger: 'blur'}
          ]<#if column_has_next>,</#if>
        <#else>
          ${column.propertyName}: [
            {required: true, message: '请输入${column.propertyComment}', trigger: 'blur'}
          ]<#if column_has_next>,</#if>
        </#if>
        </#list>
        </#if>
        }
      }
    },
    created() {
      if (this.isEdit) {
      	if (this.$route.query.id) {
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
        }
        if (this.$route.query.tenantId) {
          get${table.entityName?cap_first}ByTenantId(this.$route.query.tenantId).then(response => {
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
        }
        
      }else{
        this.${table.entityName?uncap_first} = Object.assign({},default${table.entityName?cap_first});
      }
      <#if table.includeSysId>
      this.getSystemDesignList();
      </#if>
      <#if table.includeTenantId>
      this.getTenantInfoList();
      </#if>
    },
    methods: {
    <#if table.includeSysId>
      getSystemDesignList() {
        fetchSystemDesignList({pageNum:1,pageSize:500}).then(response => {
          this.systemDesignOptions = [];
          let systemDesignList = response.data.list;
          for(let i=0;i<systemDesignList.length;i++){
            this.systemDesignOptions.push({label:systemDesignList[i].moduleName,value:systemDesignList[i].id});
          }
        });
      },
      </#if>
      <#if table.includeTenantId>
      getTenantInfoList() {
        fetchTenantInfoList({pageNum:1,pageSize:500}).then(response => {
          this.tenantInfoOptions = [];
          let tenantInfoList = response.data.list;
          for(let i=0;i<tenantInfoList.length;i++){
            this.tenantInfoOptions.push({label:tenantInfoList[i].tenantName,value:tenantInfoList[i].id});
          }
        });
      },
      </#if>
      onSubmit(formName) {
        this.$refs[formName].validate((valid) => {
          if (valid) {
            this.$confirm('是否提交数据', '提示', {
              confirmButtonText: '确定',
              cancelButtonText: '取消',
              type: 'warning'
            }).then(() => {
              if (this.isEdit) {
                update${table.entityName?cap_first}(this.$route.query.id, this.${table.entityName?uncap_first}).then(response => {
                  this.$refs[formName].resetFields();
                  this.$message({
                    message: '修改成功',
                    type: 'success',
                    duration:1000
                  });
                  this.$router.back();
                });
              } else {
                create${table.entityName?cap_first}(this.${table.entityName?uncap_first}).then(response => {
                  this.$refs[formName].resetFields();
                  this.${table.entityName?uncap_first} = Object.assign({},default${table.entityName?cap_first});
                  this.$message({
                    message: '提交成功',
                    type: 'success',
                    duration:1000
                  });
                });
              }
            });

          } else {
            this.$message({
              message: '验证失败',
              type: 'error',
              duration:1000
            });
            return false;
          }
        });
      },
      resetForm(formName) {
        this.$refs[formName].resetFields();
        this.${table.entityName?uncap_first} = Object.assign({},default${table.entityName?cap_first});
      }
    }
  }
</script>
<style>
</style>


