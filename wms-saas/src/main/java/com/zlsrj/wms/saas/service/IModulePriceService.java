package com.zlsrj.wms.saas.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zlsrj.wms.api.entity.ModulePrice;
import com.zlsrj.wms.api.entity.ModuleInfo;

public interface IModulePriceService extends IService<ModulePrice> {
	/**
	 * 根据新建模块信息创建默认用户类型
	 * @param moduleInfo
	 * @return
	 */
	boolean saveBatchByModuleInfo(ModuleInfo moduleInfo);
}
