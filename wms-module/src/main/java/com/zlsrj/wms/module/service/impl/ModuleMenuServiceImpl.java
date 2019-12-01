package com.zlsrj.wms.module.service.impl;
import java.io.Serializable;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zlsrj.wms.api.entity.ModuleInfo;
import com.zlsrj.wms.api.entity.ModuleMenu;
import com.zlsrj.wms.module.mapper.ModuleMenuMapper;
import com.zlsrj.wms.module.service.IIdService;
import com.zlsrj.wms.module.service.IModuleMenuService;
import com.zlsrj.wms.module.service.RedisService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ModuleMenuServiceImpl extends ServiceImpl<ModuleMenuMapper, ModuleMenu> implements IModuleMenuService {
	@Resource
	private RedisService<String, String> redisService;
	@Resource
	private IIdService idService;

	@Override
	public ModuleMenu getById(Serializable id) {
		try {
			String jsonString = redisService.getValue(id.toString());
			if (StringUtils.isNotEmpty(jsonString)) {
				ModuleMenu moduleMenu = JSON.parseObject(jsonString, ModuleMenu.class);
				return moduleMenu;
			}
		} catch (Exception e) {
			// ex.printStackTrace();
			log.error("redis get value error", e);
		}

		ModuleMenu moduleMenu = baseMapper.selectById(id);
		if (moduleMenu != null) {
			redisService.setValue(id.toString(), JSON.toJSONString(moduleMenu));
		}

		return moduleMenu;
	}

	@Override
	public boolean update(ModuleMenu entity, Wrapper<ModuleMenu> updateWrapper) {
		boolean success = super.update(entity, updateWrapper);
		if (success) {
			try {
				Long id = updateWrapper.getEntity().getId();
				redisService.remove(Long.toString(id));
			} catch(Exception e) {
				//ex.printStackTrace();
				log.error("redis remove error", e);
			}
		}
		return success;
	}

	@Override
	public boolean removeById(Serializable id) {
		boolean success = super.removeById(id);
		if (success) {
			try {
				redisService.remove(id.toString());
			} catch(Exception e) {
				//ex.printStackTrace();
				log.error("redis remove error", e);
			}
		}
		return success;
	}
	
	@Override
	public boolean saveBatchByModuleInfo(ModuleInfo moduleInfo) {
		return false;
	}

}
