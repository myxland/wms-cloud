<template> 
  <div class="app-container">
    <el-card class="filter-container" shadow="never">
        <div>
          <i class="el-icon-search"></i>
          <span>筛选搜索</span>
          <el-button
            style="float: right"
            @click="search${table.entityName?cap_first}List()"
            type="primary"
            size="small">
            查询结果
          </el-button>
          <el-button
            style="float:right;margin-right: 15px"
            @click="handleResetSearch()"
            size="small">
            重置
          </el-button>
        </div>
        <div style="margin-top: 15px">
          <el-form :inline="true" :model="listQuery" size="small" label-width="140px">
            <#list table.columnList as column>
            <#if column.queryable>
            <#if column.selectable>
            <el-form-item label="${column.propertyComment}：">
              <el-select v-model="listQuery.${column.propertyName}" placeholder="全部" clearable>
                <el-option
                  v-for="item in ${column.propertyName}Options"
                  :key="item.value"
                  :label="item.label"
                  :value="item.value">
                </el-option>
              </el-select>
            </el-form-item>
            <#elseif "datetime"==column.dataType || "date"==column.dataType || "time"==column.dataType>
            <el-form-item label="${column.propertyComment}：">
              <el-date-picker
                class="input-width"
                v-model="listQuery.${column.propertyName}Start"
                value-format="yyyy-MM-dd"
                type="date"
                placeholder="请选择时间">
              </el-date-picker>
              <el-date-picker
                class="input-width"
                v-model="listQuery.${column.propertyName}End"
                value-format="yyyy-MM-dd"
                type="date"
                placeholder="请选择时间">
              </el-date-picker>
            </el-form-item>
            <#elseif column.columnName?ends_with("tenant_id")>
            <el-form-item label="${column.propertyComment?replace("编号","")}：">
              <el-select v-model="listQuery.${column.propertyName}" placeholder="请选择${column.propertyComment?replace("编号","")}" clearable>
                <el-option
                  v-for="item in tenantInfoOptions"
                  :key="item.value"
                  :label="item.label"
                  :value="item.value">
                </el-option>
              </el-select>
            </el-form-item>
            <#elseif column.columnName?ends_with("sys_id")>
            <el-form-item label="${column.propertyComment?replace("编号","")}：">
              <el-select v-model="listQuery.${column.propertyName}" placeholder="请选择${column.propertyComment?replace("编号","")}" clearable>
                <el-option
                  v-for="item in systemDesignOptions"
                  :key="item.value"
                  :label="item.label"
                  :value="item.value">
                </el-option>
              </el-select>
            </el-form-item>
            <#elseif column.likeable>
            <el-form-item label="${column.propertyComment}：">
              <el-input style="width: 203px" v-model="listQuery.${column.propertyName}Like" placeholder="${column.propertyComment}"></el-input>
            </el-form-item>
            <#else>
            <el-form-item label="${column.propertyComment}：">
              <el-input style="width: 203px" v-model="listQuery.${column.propertyName}" placeholder="${column.propertyComment}"></el-input>
            </el-form-item>
            </#if>
            </#if>
            </#list>
          </el-form>
        </div>
    </el-card>
    <el-card class="operate-container" shadow="never">
      <i class="el-icon-tickets"></i>
      <span>数据列表</span>
      <el-button
        class="btn-add"
        @click="add${table.entityName?cap_first}()"
        size="mini">
        添加
      </el-button>
    </el-card>
    <div class="table-container">
      <el-table ref="${table.entityName?uncap_first}Table"
                :data="list"
                style="width: 100%"
                @selection-change="handleSelectionChange"
                v-loading="listLoading"
                border>
        <el-table-column type="selection" width="60" align="center"></el-table-column>
        <#list table.columnList as column>
        <#if column.viewable>
        <#if column.selectable>
        <#if column.singleUpdatable>
        <el-table-column label="${column.propertyComment}" width="<#if column.gridWidthAuto==false>${column.gridWidth}</#if>" align="${column.gridAlign}" header-align="center">
          <template slot-scope="scope">
            <el-switch
              @change="handle${column.propertyName?cap_first}Change(scope.$index, scope.row)"
              :active-value="${column.propertyOptionList[0].value}"
              :inactive-value="${column.propertyOptionList[1].value}"
              v-model="scope.row.${column.propertyName}">
            </el-switch>
          </template>
        </el-table-column>
        <#else>
        <el-table-column label="${column.propertyComment}" width="<#if column.gridWidthAuto==false>${column.gridWidth}</#if>" align="${column.gridAlign}" header-align="center">
          <template slot-scope="scope">{{scope.row.${column.propertyName} | format${column.propertyName?cap_first}}}</template>
        </el-table-column>
        </#if>        
        <#elseif "datetime"==column.dataType>
        <el-table-column label="${column.propertyComment}" width="<#if column.gridWidthAuto==false>${column.gridWidth}</#if>" align="${column.gridAlign}" header-align="center">
          <template slot-scope="scope">{{scope.row.${column.propertyName} | formatTime}}</template>
        </el-table-column>
        <#elseif "date"==column.dataType>
        <el-table-column label="${column.propertyComment}" width="<#if column.gridWidthAuto==false>${column.gridWidth}</#if>" align="${column.gridAlign}" header-align="center">
          <template slot-scope="scope">{{scope.row.${column.propertyName} | formatDate}}</template>
        </el-table-column>
        <#elseif "decimal"==column.dataType && 2==column.numericScale>
        <el-table-column label="${column.propertyComment}" width="<#if column.gridWidthAuto==false>${column.gridWidth}</#if>" align="${column.gridAlign}" header-align="center">
          <template slot-scope="scope">{{scope.row.${column.propertyName} | formatMoney}}</template>
        </el-table-column>
        <#elseif column.columnName?ends_with("tenant_id")>
        <el-table-column label="${column.propertyComment?replace("编号","")}" width="<#if column.gridWidthAuto==false>${column.gridWidth}</#if>" align="${column.gridAlign}" header-align="center">
          <template slot-scope="scope">{{scope.row.${column.propertyName?replace("Id","Name")}}}</template>
        </el-table-column>
        <#elseif column.columnName?ends_with("sys_id")>
        <el-table-column label="${column.propertyComment?replace("编号","")}" width="<#if column.gridWidthAuto==false>${column.gridWidth}</#if>" align="${column.gridAlign}" header-align="center">
          <template slot-scope="scope">{{scope.row.${column.propertyName?replace("sysId","moduleName")?replace("SysId","ModuleName")}}}</template>
        </el-table-column>
        <#else>
        <el-table-column label="${column.propertyComment}" width="<#if column.gridWidthAuto==false>${column.gridWidth}</#if>" align="${column.gridAlign}" header-align="center">
          <template slot-scope="scope">{{scope.row.${column.propertyName}}}</template>
        </el-table-column>
        </#if>
        </#if>
        </#list>
        <el-table-column label="操作" width="220" align="center">
          <template slot-scope="scope">
            <el-button
              size="mini"
              @click="handleView(scope.${"$"}index, scope.row)">查看
            </el-button>
            <el-button
              size="mini"
              @click="handleUpdate(scope.${"$"}index, scope.row)">编辑
            </el-button>
            <el-button
              size="mini"
              type="danger"
              @click="handleDelete(scope.${"$"}index, scope.row)">删除
            </el-button>
          </template>
        </el-table-column>
      </el-table>
    </div>
    <div class="batch-operate-container">
      <el-select
        size="small"
        v-model="operateType" placeholder="批量操作">
        <el-option
          v-for="item in operates"
          :key="item.value"
          :label="item.label"
          :value="item.value">
        </el-option>
      </el-select>
      <el-button
        style="margin-left: 20px"
        class="search-button"
        @click="handleBatchOperate()"
        type="primary"
        size="small">
        确定
      </el-button>
    </div>
    <div class="pagination-container">
      <el-pagination
        background
        @size-change="handleSizeChange"
        @current-change="handleCurrentChange"
        layout="total, sizes,prev, pager, next,jumper"
        :page-size="listQuery.pageSize"
        :page-sizes="[5,10,15]"
        :current-page.sync="listQuery.pageNum"
        :total="total">
      </el-pagination>
    </div>
  </div>
</template>
<script>
  import {fetchList, delete${table.entityName?cap_first}<#if table.includeSingleUpdatable><#list table.singleUpdatableColumnList as column>, update${column.propertyName?cap_first}</#list></#if><#if table.includeBatchUpdatable><#list table.batchUpdatableColumnList as column>, update${column.propertyName?cap_first}</#list></#if>} from '@/api/${table.entityName?uncap_first}'
   <#if table.includeSysId>
  import {fetchList as fetchSystemDesignList} from '@/api/systemDesign';
  </#if>
  <#if table.includeTenantId>
  import {fetchList as fetchTenantInfoList} from '@/api/tenantInfo';
  </#if>
  <#if table.includeDate>
  import {formatDate} from '@/utils/date';
  </#if>
  <#if table.includeBigDecimal>
  import accounting from 'accounting';
  </#if>
  const defaultListQuery = {
    pageNum: 1,
    pageSize: 10,
    <#list table.columnList as column>
    <#if "datetime"==column.dataType || "date"==column.dataType || "time"==column.dataType>
    ${column.propertyName}: null,
    ${column.propertyName}Start: null,
    ${column.propertyName}End: null<#if column_has_next>,</#if>
    <#elseif column.likeable>
    ${column.propertyName}Like: null<#if column_has_next>,</#if>
    <#else>
    ${column.propertyName}: null<#if column_has_next>,</#if>
    </#if>
    </#list>
  };
  <#list table.columnList as column>
  <#if column.selectable>
  const default${column.propertyName?cap_first}Options=[
    <#list column.propertyOptionList as option>
    {
      value: ${option.value},
      label: '${option.text}'
    }<#if option_has_next>,</#if>
    </#list>  
  ];
  </#if>
  </#list>
  
  export default {
    name: '${table.entityName?uncap_first}List',
    data() {
      return {
        operates: [
          <#if table.includeBatchUpdatable>
          <#list table.batchUpdatableColumnList as column>
          <#list column.propertyOptionList as propertyOption>
          {
            label: "${propertyOption.text}${column.propertyComment}",
            value: "<#if propertyOption.value == "1">active<#elseif propertyOption.value == "0">inactive</#if>${column.propertyName?cap_first}"
          }<#if propertyOption_has_next>,<#else><#if column_has_next>,</#if></#if>
          </#list>
          </#list>
          </#if>
        ],
        operateType: null,
        listQuery:Object.assign({},defaultListQuery),
        list: null,
        total: null,
        listLoading: true,
        <#list table.columnList as column>
        <#if column.selectable>
        ${column.propertyName}Options: Object.assign({},default${column.propertyName?cap_first}Options),
        </#if>
        </#list>
	<#if table.includeSysId>
        systemDesignOptions:[],
        </#if>
        <#if table.includeTenantId>
        tenantInfoOptions:[],
        </#if>
        multipleSelection: []
      }
    },
    created() {
      this.getList();
      <#if table.includeSysId>
      this.getSystemDesignList();
      </#if>
      <#if table.includeTenantId>
      this.getTenantInfoList();
      </#if>
    },
    filters:{
      <#if table.includeDate>
      formatTime(time) {
        if(time==null||time===''){
          return 'N/A';
        }
        let date = new Date(time);
        return formatDate(date, 'yyyy-MM-dd hh:mm:ss')
      },
      formatDate(time) {
        if(time==null||time===''){
          return 'N/A';
        }
        let date = new Date(time);
        return formatDate(date, 'yyyy-MM-dd')
      },
      </#if>
      <#if table.includeBigDecimal>
      formatMoney(money){
        // 指定货币符号、保留小数位、千位间隔符
        return accounting.formatMoney(money,'',2,'');
      },
      </#if>
      <#list table.columnList as column>
      <#if column.selectable>
      format${column.propertyName?cap_first}(${column.propertyName}){
        for(let i=0;i<default${column.propertyName?cap_first}Options.length;i++){
          if(${column.propertyName}===default${column.propertyName?cap_first}Options[i].value){
            return default${column.propertyName?cap_first}Options[i].label;
          }
        }
      },
      </#if>
      </#list>      
    },
    methods: {
      getList() {
        this.listLoading = true;
        fetchList(this.listQuery).then(response => {
          this.listLoading = false;
          this.list = response.data.list;
          this.total = response.data.total;
          this.totalPage = response.data.totalPage;
          this.pageSize = response.data.pageSize;
        });
      },
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
      handleResetSearch() {
        this.listQuery = Object.assign({}, defaultListQuery);
      },
      handleSelectionChange(val) {
        this.multipleSelection = val;
      },
      handleView(index, row) {
        this.${"$"}router.push({path: '/${projectName}/view${table.entityName?cap_first}', query: {id: row.id}})
      },
      handleUpdate(index, row) {
        this.${"$"}router.push({path: '/${projectName}/update${table.entityName?cap_first}', query: {id: row.id}})
      },
      handleDelete(index, row) {
        this.${"$"}confirm('是否要删除该${table.tableComment}', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          delete${table.entityName?cap_first}(row.id).then(response => {
            this.${"$"}message({
              message: '删除成功',
              type: 'success',
              duration: 1000
            });
            this.getList();
          });
        });
      },
      handleSizeChange(val) {
        this.listQuery.pageNum = 1;
        this.listQuery.pageSize = val;
        this.getList();
      },
      <#if table.includeSingleUpdatable>
      <#list table.singleUpdatableColumnList as column>
      handle${column.propertyName?cap_first}Change(index, row) {
        let data = new URLSearchParams();
        ;
        data.append("ids", row.id);
        data.append("${column.propertyName}", row.${column.propertyName});
        update${column.propertyName?cap_first}(data).then(response => {
          this.$message({
            message: '修改成功',
            type: 'success',
            duration: 1000
          });
        }).catch(error => {
          if (row.${column.propertyName} === 0) {
            row.${column.propertyName} = 1;
          } else {
            row.${column.propertyName} = 0;
          }
        });
      },
      </#list>
      </#if>
      handleCurrentChange(val) {
        this.listQuery.pageNum = val;
        this.getList();
      },
      search${table.entityName?cap_first}List() {
        this.listQuery.pageNum = 1;
        this.getList();
      },
      handleBatchOperate() {
        console.log(this.multipleSelection);
        if (this.multipleSelection < 1) {
          this.${"$"}message({
            message: '请选择一条记录',
            type: 'warning',
            duration: 1000
          });
          return;
        }
        let ids = [];
        for (let i = 0; i < this.multipleSelection.length; i++) {
          ids.push(this.multipleSelection[i].id);
        }
        let data = new URLSearchParams();
        data.append("ids", ids);
        <#if table.includeBatchUpdatable>
        <#list table.batchUpdatableColumnList as column>
        <#if column_index gt 0>else </#if>if(this.operateType=="active${column.propertyName?cap_first}" || this.operateType=="inactive${column.propertyName?cap_first}"){
          let ${column.propertyName} = 0;
          if(this.operateType=="active${column.propertyName?cap_first}"){
            ${column.propertyName} = 1;
          }
          data.append("${column.propertyName}", ${column.propertyName});
          update${column.propertyName?cap_first}(data).then(response => {
            this.getList();
            this.$message({
              message: '修改成功',
              type: 'success',
              duration: 1000
            });
          });
        }
        </#list>
        else {
          this.$message({
            message: '请选择批量操作类型',
            type: 'warning',
            duration: 1000
          });
          return;
        }
        </#if>
      },
      add${table.entityName?cap_first}() {
        this.${"$"}router.push({path: '/${projectName}/add${table.entityName?cap_first}'})
      }
    }
  }
</script>
<style rel="stylesheet/scss" lang="scss" scoped>


</style>


