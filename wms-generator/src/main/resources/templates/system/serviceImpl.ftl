package ${domainName}.${projectName}.service.impl;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import ${domainName}.${projectNameApi}.entity.${table.entityName};
import ${domainName}.${projectName}.mapper.${table.entityName}Mapper;
import ${domainName}.${projectName}.service.I${table.entityName}Service;

@Service
public class ${table.entityName}ServiceImpl extends ServiceImpl<${table.entityName}Mapper, ${table.entityName}> implements I${table.entityName}Service {

}
