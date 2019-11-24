<template> 
  <${table.entityName?uncap_first}-detail :is-edit='true'></${table.entityName?uncap_first}-detail>
</template>
<script>
  import ${table.entityName?cap_first}Detail from './components/${table.entityName?cap_first}Detail'
  export default {
    name: 'update${table.entityName?cap_first}',
    components: { ${table.entityName?cap_first}Detail }
  }
</script>
<style>
</style>


