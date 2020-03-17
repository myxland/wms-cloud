#mybatisplus
entity=${targetDir}/${artifactIdApi}/src/main/java/${domainNameDir}/${projectNameApi}/entity/${table.entityName}.java
dto=${targetDir}/${artifactIdApi}/src/main/java/${domainNameDir}/${projectNameApi}/dto/${table.entityName}QueryParam.java
dtoAdd=${targetDir}/${artifactIdApi}/src/main/java/${domainNameDir}/${projectNameApi}/dto/${table.entityName}AddParam.java
dtoUpdate=${targetDir}/${artifactIdApi}/src/main/java/${domainNameDir}/${projectNameApi}/dto/${table.entityName}UpdateParam.java
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
webControllerTest=${targetDir}/${artifactIdWeb}/src/test/java/${domainNameDir}/${projectNameWeb}/controller/${table.entityName}ControllerTest.java