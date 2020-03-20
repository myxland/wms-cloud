package com.zlsrj.wms.saas.service;

import java.io.Serializable;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zlsrj.wms.api.entity.TenantInfo;

public interface ITenantInfoService extends IService<TenantInfo> {
	TenantInfo getDictionaryById(Serializable id);
}
