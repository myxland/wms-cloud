package com.zlsrj.wms.saas.service;

import java.io.Serializable;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zlsrj.wms.api.dto.TenantDepartmentAddParam;
import com.zlsrj.wms.api.dto.TenantDepartmentUpdateParam;
import com.zlsrj.wms.api.entity.TenantDepartment;
import com.zlsrj.wms.api.entity.TenantInfo;

public interface ITenantDepartmentService extends IService<TenantDepartment> {
	/**
	 * 根据新建租户信息创建默认用户类型
	 * @param tenantInfo
	 * @return
	 */
	boolean saveBatchByTenantInfo(TenantInfo tenantInfo);
	
	boolean updateById(TenantDepartmentUpdateParam tenantDepartmentUpdateParam);
	
	String save(TenantDepartmentAddParam tenantDepartmentAddParam);
	
	TenantDepartment getDictionaryById(Serializable id);
}
