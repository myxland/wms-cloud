import request from '@/utils/request'
export function fetchList(params) {
  return request({
    url:'/${table.entityName?uncap_first}/list',
    method:'get',
    params:params
  })
}

export function create${table.entityName?cap_first}(data) {
  return request({
    url:'/${table.entityName?uncap_first}/create',
    method:'post',
    data:data
  })
}

export function delete${table.entityName?cap_first}(id) {
  return request({
    url:'/${table.entityName?uncap_first}/delete/'+id,
    method:'get',
  })
}

export function get${table.entityName?cap_first}(id) {
  return request({
    url:'/${table.entityName?uncap_first}/'+id,
    method:'get',
  })
}

export function update${table.entityName?cap_first}(id,data) {
  return request({
    url:'/${table.entityName?uncap_first}/update/'+id,
    method:'post',
    data:data
  })
}
<#if table.includeSingleUpdatable>
<#list table.singleUpdatableColumnList as column>

export function update${column.propertyName?cap_first}(data) {
  return request({
    url:'/${table.entityName?uncap_first}/update/${column.propertyName}',
    method:'post',
    data:data
  })
}
</#list>
</#if>
<#if table.includeBatchUpdatable>
<#list table.batchUpdatableColumnList as column>

export function update${column.propertyName?cap_first}(data) {
  return request({
    url:'/${table.entityName?uncap_first}/update/${column.propertyName}',
    method:'post',
    data:data
  })
}
</#list>
</#if>