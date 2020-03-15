package com.zlsrj.wms.saas.service.impl;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zlsrj.wms.api.entity.ModuleInfo;
import com.zlsrj.wms.api.entity.ModuleMenu;
import com.zlsrj.wms.saas.mapper.ModuleMenuMapper;
import com.zlsrj.wms.saas.service.IIdService;
import com.zlsrj.wms.saas.service.IModuleMenuService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ModuleMenuServiceImpl extends ServiceImpl<ModuleMenuMapper, ModuleMenu> implements IModuleMenuService {
	@Resource
	private IIdService idService;

	@Override
	public boolean saveBatchByModuleInfo(ModuleInfo moduleInfo) {
		return false;
	}
	
	@Override
	public List<ModuleMenu> selectModuleMenuByEmployee(String tenantId,String employeeId){
		Map<String,Object> parameters = new HashMap<String,Object>();
		parameters.put("tenantId", tenantId);
		parameters.put("employeeId", employeeId);
		return this.baseMapper.selectModuleMenuByEmployee(parameters);
	}
	
	@Override
	public List<ModuleMenu> selectModuleMenuByTenant(String tenantId){
		Map<String,Object> parameters = new HashMap<String,Object>();
		parameters.put("tenantId", tenantId);
		return this.baseMapper.selectModuleMenuByTenant(parameters);
	}

}
