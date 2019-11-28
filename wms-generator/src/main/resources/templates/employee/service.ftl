package ${domainName}.${projectName}.service;

import com.baomidou.mybatisplus.extension.service.IService;
import ${domainName}.${projectNameApi}.entity.${table.entityName};
<#if table.includeTenantOne2One || table.includeTenantOne2Many>
import ${domainName}.${projectNameApi}.entity.TenantInfo;
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
}
