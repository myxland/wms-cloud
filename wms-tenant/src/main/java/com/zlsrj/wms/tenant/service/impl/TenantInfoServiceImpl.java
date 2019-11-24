package com.zlsrj.wms.tenant.service.impl;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zlsrj.wms.api.entity.TenantInfo;
import com.zlsrj.wms.tenant.mapper.TenantInfoMapper;
import com.zlsrj.wms.tenant.service.ITenantInfoService;
import com.zlsrj.wms.tenant.service.RedisService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class TenantInfoServiceImpl extends ServiceImpl<TenantInfoMapper, TenantInfo> implements ITenantInfoService {

	@Autowired
	private RedisService<String, String> redisService;

	@Override
	public TenantInfo getById(Serializable id) {
		try {
			String jsonString = redisService.getValue(id.toString());
			if (StringUtils.isNotEmpty(jsonString)) {
				TenantInfo tenantInfo = JSON.parseObject(jsonString, TenantInfo.class);
				return tenantInfo;
			}
		}
		catch(Exception e) {
			//ex.printStackTrace();
			log.error("redis get value error", e);
		}
		
		TenantInfo tenantInfo = baseMapper.selectById(id);
		if (tenantInfo != null) {
			redisService.setValue(id.toString(), JSON.toJSONString(tenantInfo));
		}

		return tenantInfo;
	}

	@Override
	public boolean update(TenantInfo entity, Wrapper<TenantInfo> updateWrapper) {
		boolean success = super.update(entity, updateWrapper);
		if (success) {
			try {
				Long id = updateWrapper.getEntity().getId();
				TenantInfo tenantInfo = baseMapper.selectById(id);
				redisService.setValue(Long.toString(id), JSON.toJSONString(tenantInfo));
			}
			catch(Exception e) {
				//ex.printStackTrace();
				log.error("redis set value error", e);
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
			}
			catch(Exception e) {
				//ex.printStackTrace();
				log.error("redis remove error", e);
			}
		}
		return success;
	}
}
