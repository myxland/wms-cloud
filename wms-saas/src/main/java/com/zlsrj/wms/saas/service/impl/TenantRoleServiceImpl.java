package com.zlsrj.wms.saas.service.impl;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zlsrj.wms.api.entity.TenantRole;
import com.zlsrj.wms.api.entity.TenantInfo;
import com.zlsrj.wms.api.enums.RoleEnum;
import com.zlsrj.wms.saas.mapper.TenantRoleMapper;
import com.zlsrj.wms.saas.service.IIdService;
import com.zlsrj.wms.saas.service.ITenantRoleService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class TenantRoleServiceImpl extends ServiceImpl<TenantRoleMapper, TenantRole> implements ITenantRoleService {
	@Resource
	private IIdService idService;

	@Override
	public boolean saveBatchByTenantInfo(TenantInfo tenantInfo) {
		QueryWrapper<TenantRole> queryWrapperTenantRole = new QueryWrapper<TenantRole>();
		queryWrapperTenantRole.lambda()//
				.eq(TenantRole::getTenantId, tenantInfo.getId())//
		;
		int count = super.count(queryWrapperTenantRole);
		if (count > 0) {
			log.error("根据租户信息初始化角色信息失败，角色信息已存在。");
			return false;
		}

		List<TenantRole> tenantRoleList = new ArrayList<TenantRole>();
		for (RoleEnum roleEnum : RoleEnum.values()) {
			TenantRole tenantRole = TenantRole.builder()//
					.id(idService.selectId())// 工作岗位ID
					.tenantId(tenantInfo.getId())// 租户ID
					.roleName(roleEnum.getText())// 工作岗位名称
					.roleRemark(null)// 工作岗位说明
					.createType(null)// 创建类型（1：平台默认创建；2：租户自建）
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
