package ${domainName}.${projectName}.service;

import com.baomidou.mybatisplus.extension.service.IService;
import ${domainName}.${projectNameApi}.entity.${table.entityName};

public interface I${table.entityName}Service extends IService<${table.entityName}> {
	<#if table.includeTenantOne2One>
	/**
	 * 根据新建租户信息创建默认${table.tableComment}
	 * @param tenantInfo
	 * @return
	 */
	boolean saveByTenantInfo(TenantInfo tenantInfo);
	</#if>
}
