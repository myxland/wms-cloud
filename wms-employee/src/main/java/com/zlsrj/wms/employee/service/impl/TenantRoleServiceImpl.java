package com.zlsrj.wms.employee.service.impl;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zlsrj.wms.api.entity.TenantRole;
import com.zlsrj.wms.api.entity.TenantInfo;
import com.zlsrj.wms.api.enums.RoleEnum;
import com.zlsrj.wms.employee.mapper.TenantRoleMapper;
import com.zlsrj.wms.employee.service.IIdService;
import com.zlsrj.wms.employee.service.ITenantRoleService;
import com.zlsrj.wms.employee.service.RedisService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class TenantRoleServiceImpl extends ServiceImpl<TenantRoleMapper, TenantRole> implements ITenantRoleService {
	@Resource
	private RedisService<String, String> redisService;
	@Resource
	private IIdService idService;

	@Override
	public TenantRole getById(Serializable id) {
		try {
			String jsonString = redisService.getValue(id.toString());
			if (StringUtils.isNotEmpty(jsonString)) {
				TenantRole tenantRole = JSON.parseObject(jsonString, TenantRole.class);
				return tenantRole;
			}
		} catch (Exception e) {
			// ex.printStackTrace();
			log.error("redis get value error", e);
		}

		TenantRole tenantRole = baseMapper.selectById(id);
		if (tenantRole != null) {
			redisService.setValue(id.toString(), JSON.toJSONString(tenantRole));
		}

		return tenantRole;
	}

	@Override
	public boolean update(TenantRole entity, Wrapper<TenantRole> updateWrapper) {
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
	public boolean saveBatchByTenantInfo(TenantInfo tenantInfo) {
		QueryWrapper<TenantRole> queryWrapperTenantRole = new QueryWrapper<TenantRole>();
		queryWrapperTenantRole.lambda()//
				.eq(TenantRole::getTenantId, tenantInfo.getId())//
		;
		int count = super.count(queryWrapperTenantRole);
		if (count > 0) {
			log.error("根据租户信息初始化租户角色失败，租户类型已存在。");
			return false;
		}

		List<TenantRole> tenantRoleList = new ArrayList<TenantRole>();
		for (RoleEnum roleEnum : RoleEnum.values()) {
			TenantRole tenantRole = TenantRole.builder()//
					.id(idService.selectId())// 系统ID
					.tenantId(tenantInfo.getId())// 租户编号
					.roleName(roleEnum.getText())// 角色名称
					.roleRemark(null)// 角色说明
					.build();
			log.info(tenantRole.toString());
			tenantRoleList.add(tenantRole);
			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		log.info(tenantRoleList.toString());
		return super.saveBatch(tenantRoleList);
	}

}
