<template> 
  <${table.entityName?uncap_first}-view></${table.entityName?uncap_first}-view>
</template>
<script>
  import ${table.entityName?cap_first}View from './components/${table.entityName?cap_first}View'
  export default {
    name: 'view${table.entityName?cap_first}',
    components: { ${table.entityName?cap_first}View }
  }
</script>
<style>
</style>