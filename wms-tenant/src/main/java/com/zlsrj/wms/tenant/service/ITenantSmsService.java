package com.zlsrj.wms.tenant.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zlsrj.wms.api.entity.TenantSms;
import com.zlsrj.wms.api.entity.TenantInfo;

public interface ITenantSmsService extends IService<TenantSms> {
	/**
	 * 根据新建租户信息创建默认租户短信配置
	 * @param tenantInfo
	 * @return
	 */
	boolean saveByTenantInfo(TenantInfo tenantInfo);
}
