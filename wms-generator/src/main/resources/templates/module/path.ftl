#mybatisplus
#entity=${targetDir}/${artifactId}/src/main/java/${domainNameDir}/${projectName}/entity/${table.entityName}.java
#dto=${targetDir}/${artifactId}/src/main/java/${domainNameDir}/${projectName}/dto/${table.entityName}QueryParam.java
#controller=${targetDir}/${artifactId}/src/main/java/${domainNameDir}/${projectName}/controller/${table.entityName}Controller.java
#controllerTest=${targetDir}/${artifactId}/src/test/java/${domainNameDir}/${projectName}/controller/${table.entityName}ControllerTest.java
entity=${targetDir}/${artifactIdApi}/src/main/java/${domainNameDir}/${projectNameApi}/entity/${table.entityName}.java
dto=${targetDir}/${artifactIdApi}/src/main/java/${domainNameDir}/${projectNameApi}/dto/${table.entityName}QueryParam.java
vo=${targetDir}/${artifactIdApi}/src/main/java/${domainNameDir}/${projectNameApi}/vo/${table.entityName}Vo.java
clientService=${targetDir}/${artifactIdApi}/src/main/java/${domainNameDir}/${projectNameApi}/client/service/${table.entityName}ClientService.java
mapper=${targetDir}/${artifactId}/src/main/java/${domainNameDir}/${projectName}/mapper/${table.entityName}Mapper.java
service=${targetDir}/${artifactId}/src/main/java/${domainNameDir}/${projectName}/service/I${table.entityName}Service.java
serviceImpl=${targetDir}/${artifactId}/src/main/java/${domainNameDir}/${projectName}/service/impl/${table.entityName}ServiceImpl.java
restController=${targetDir}/${artifactId}/src/main/java/${domainNameDir}/${projectName}/rest/${table.entityName}RestController.java
mapperXml=${targetDir}/${artifactId}/src/main/resources/mapper/${projectName}/${table.entityName}Mapper.xml
mapperTest=${targetDir}/${artifactId}/src/test/java/${domainNameDir}/${projectName}/mapper/${table.entityName}MapperTest.java
serviceTest=${targetDir}/${artifactId}/src/test/java/${domainNameDir}/${projectName}/service/I${table.entityName}ServiceTest.java
restControllerTest=${targetDir}/${artifactId}/src/test/java/${domainNameDir}/${projectName}/rest/${table.entityName}RestControllerTest.java
webController=${targetDir}/${artifactIdWeb}/src/main/java/${domainNameDir}/${projectNameWeb}/controller/${table.entityName}Controller.java

#vue
router=${targetDirVue}/${projectNameVue}/src/router/index.js
api=${targetDirVue}/${projectNameVue}/src/api/${table.entityName?uncap_first}.js
indexVue=${targetDirVue}/${projectNameVue}/src/views/${projectName}/${table.entityName?uncap_first}/index.vue
viewVue=${targetDirVue}/${projectNameVue}/src/views/${projectName}/${table.entityName?uncap_first}/view.vue
addVue=${targetDirVue}/${projectNameVue}/src/views/${projectName}/${table.entityName?uncap_first}/add.vue
updateVue=${targetDirVue}/${projectNameVue}/src/views/${projectName}/${table.entityName?uncap_first}/update.vue
detailVue=${targetDirVue}/${projectNameVue}/src/views/${projectName}/${table.entityName?uncap_first}/components/${table.entityName?cap_first}Detail.vue
viewComponentVue=${targetDirVue}/${projectNameVue}/src/views/${projectName}/${table.entityName?uncap_first}/components/${table.entityName?cap_first}View.vue