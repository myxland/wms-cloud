package com.zlsrj.wms.saas.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zlsrj.wms.api.dto.SystemDictionariesAddParam;
import com.zlsrj.wms.api.dto.SystemDictionariesUpdateParam;
import com.zlsrj.wms.api.entity.SystemDictionaries;

public interface ISystemDictionariesService extends IService<SystemDictionaries> {
	String save(SystemDictionariesAddParam tenantCustomerTypeAddParam);

	boolean updateById(SystemDictionariesUpdateParam tenantCustomerTypeUpdateParam);

}