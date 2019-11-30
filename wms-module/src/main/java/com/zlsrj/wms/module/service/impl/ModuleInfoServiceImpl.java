package com.zlsrj.wms.module.service.impl;
import java.io.Serializable;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zlsrj.wms.api.entity.ModuleInfo;
import com.zlsrj.wms.module.mapper.ModuleInfoMapper;
import com.zlsrj.wms.module.service.IModuleInfoService;
import com.zlsrj.wms.module.service.RedisService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ModuleInfoServiceImpl extends ServiceImpl<ModuleInfoMapper, ModuleInfo> implements IModuleInfoService {
	@Resource
	private RedisService<String, String> redisService;

	@Override
	public ModuleInfo getById(Serializable id) {
		try {
			String jsonString = redisService.getValue(id.toString());
			if (StringUtils.isNotEmpty(jsonString)) {
				ModuleInfo moduleInfo = JSON.parseObject(jsonString, ModuleInfo.class);
				return moduleInfo;
			}
		} catch (Exception e) {
			// ex.printStackTrace();
			log.error("redis get value error", e);
		}

		ModuleInfo moduleInfo = baseMapper.selectById(id);
		if (moduleInfo != null) {
			redisService.setValue(id.toString(), JSON.toJSONString(moduleInfo));
		}

		return moduleInfo;
	}

	@Override
	public boolean update(ModuleInfo entity, Wrapper<ModuleInfo> updateWrapper) {
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
	
}
