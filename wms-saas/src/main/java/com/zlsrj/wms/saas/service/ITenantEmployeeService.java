package com.zlsrj.wms.saas.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zlsrj.wms.api.dto.TenantEmployeeAddParam;
import com.zlsrj.wms.api.dto.TenantEmployeeBatchUpdateParam;
import com.zlsrj.wms.api.dto.TenantEmployeeUpdateParam;
import com.zlsrj.wms.api.entity.TenantEmployee;

public interface ITenantEmployeeService extends IService<TenantEmployee> {
	String save(TenantEmployeeAddParam tenantEmployeeAddParam);
	boolean updateById(String id,TenantEmployeeUpdateParam tenantEmployeeUpdateParam);
	boolean update(String id,TenantEmployeeUpdateParam tenantEmployeeUpdateParam);
	/**
	 * 根据ids批量更新租户员工信息
	 * @param ids
	 * @param tenantEmployeeBatchUpdateParam
	 * @return
	 */
	boolean updateByIds(String[] ids,TenantEmployeeBatchUpdateParam tenantEmployeeBatchUpdateParam);
}