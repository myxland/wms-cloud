package com.zlsrj.wms.saas.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zlsrj.wms.api.dto.ModuleInfoAddParam;
import com.zlsrj.wms.api.dto.ModuleInfoUpdateParam;
import com.zlsrj.wms.api.entity.ModuleInfo;

public interface IModuleInfoService extends IService<ModuleInfo> {
	String save(ModuleInfoAddParam tenantCustomerTypeAddParam);

	boolean updateById(ModuleInfoUpdateParam tenantCustomerTypeUpdateParam);

}