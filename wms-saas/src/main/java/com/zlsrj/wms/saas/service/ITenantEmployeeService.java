package com.zlsrj.wms.saas.service;

import java.io.Serializable;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zlsrj.wms.api.dto.TenantEmployeeAddParam;
import com.zlsrj.wms.api.dto.TenantEmployeeBatchUpdateParam;
import com.zlsrj.wms.api.dto.TenantEmployeeUpdateParam;
import com.zlsrj.wms.api.entity.TenantEmployee;
import com.zlsrj.wms.api.entity.TenantInfo;

public interface ITenantEmployeeService extends IService<TenantEmployee> {
	boolean saveBatchByTenantInfo(TenantInfo tenantInfo);
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
	
	/**
	 * 更新员工密码
	 * @param id
	 * @param password
	 * @return
	 */
	boolean updatePassword(String id,String password);
	
	TenantEmployee getDictionaryById(Serializable id);
}