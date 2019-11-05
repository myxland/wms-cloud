package com.zlsrj.wms.mbg.service.impl;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zlsrj.wms.mbg.entity.TenantInfo;
import com.zlsrj.wms.mbg.entity.TenantRole;
import com.zlsrj.wms.mbg.mapper.TenantRoleMapper;
import com.zlsrj.wms.mbg.service.ITenantRoleService;

import cn.hutool.core.util.IdUtil;

@Service
public class TenantRoleServiceImpl extends ServiceImpl<TenantRoleMapper, TenantRole> implements ITenantRoleService {
	/**
	 * 初始化租户角色
	 * 
	 * @param tenantId
	 * @return
	 */
	public boolean initByTenant(TenantInfo tenantInfo) {
		TenantRole tenantRole = TenantRole.builder()//
				.id(IdUtil.createSnowflake(1L, 1L).nextId())// 系统ID
				.tenantId(tenantInfo.getId())// 租户编号
				.roleName("系统管理员")// 角色名称
				.roleRemark("系统默认建立，拥有最大权限")// 角色说明
				.build();

		boolean success = retBool(baseMapper.insert(tenantRole));

		return success;
	}
}
