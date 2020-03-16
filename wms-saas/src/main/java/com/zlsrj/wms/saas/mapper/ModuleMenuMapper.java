package com.zlsrj.wms.saas.mapper;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zlsrj.wms.api.entity.ModuleMenu;

public interface ModuleMenuMapper extends BaseMapper<ModuleMenu> {
	public List<ModuleMenu> selectModuleMenuByEmployee(Map<String,Object> parameters);
	public List<ModuleMenu> selectModuleMenuByTenant(Map<String,Object> parameters);
	public List<ModuleMenu> selectModuleMenuByRole(Map<String,Object> parameters);
}
