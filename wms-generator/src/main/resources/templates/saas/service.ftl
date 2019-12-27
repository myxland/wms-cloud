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
	<#if table.includeTenantOne2One>
	/**
	 * 根据新建租户信息创建默认${table.tableComment}
	 * @param tenantInfo
	 * @return
	 */
	boolean saveByTenantInfo(TenantInfo tenantInfo);
	</#if>
	<#if table.includeTenantOne2Many>
	/**
	 * 根据新建租户信息创建默认用户类型
	 * @param tenantInfo
	 * @return
	 */
	boolean saveBatchByTenantInfo(TenantInfo tenantInfo);
	</#if>
	<#if table.includeModuleOne2One>
	/**
	 * 根据新建模块信息创建默认${table.tableComment}
	 * @param moduleInfo
	 * @return
	 */
	boolean saveByModuleInfo(ModuleInfo moduleInfo);
	</#if>
	<#if table.includeModuleOne2Many>
	/**
	 * 根据新建模块信息创建默认用户类型
	 * @param moduleInfo
	 * @return
	 */
	boolean saveBatchByModuleInfo(ModuleInfo moduleInfo);
	</#if>
}
