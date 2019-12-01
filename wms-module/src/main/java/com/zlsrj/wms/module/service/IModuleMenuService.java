package com.zlsrj.wms.module.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zlsrj.wms.api.entity.ModuleMenu;
import com.zlsrj.wms.api.entity.ModuleInfo;

public interface IModuleMenuService extends IService<ModuleMenu> {
	/**
	 * 根据新建模块信息创建默认用户类型
	 * @param moduleInfo
	 * @return
	 */
	boolean saveBatchByModuleInfo(ModuleInfo moduleInfo);
}
