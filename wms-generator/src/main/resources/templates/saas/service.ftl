package ${domainName}.${projectName}.service;

import com.baomidou.mybatisplus.extension.service.IService;
import ${domainName}.${projectNameApi}.entity.${table.entityName};
<#if table.includeTenantOne2One || table.includeTenantOne2Many>
import ${domainName}.${projectNameApi}.entity.TenantInfo;
</#if>
<#if table.includeModuleOne2One || table.includeModuleOne2Many>
import ${domainName}.${projectNameApi}.entity.ModuleInfo;
</#if>

public interface I${table.entityName}Service extends IService<${table.entityName}> {

}