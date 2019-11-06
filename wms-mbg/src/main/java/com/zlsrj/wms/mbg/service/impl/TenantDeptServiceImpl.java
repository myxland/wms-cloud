package com.zlsrj.wms.mbg.service.impl;

import java.util.Arrays;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zlsrj.wms.api.entity.TenantDept;
import com.zlsrj.wms.mbg.mapper.TenantDeptMapper;
import com.zlsrj.wms.mbg.service.ITenantDeptService;

import cn.hutool.core.util.IdUtil;

@Service
public class TenantDeptServiceImpl extends ServiceImpl<TenantDeptMapper, TenantDept> implements ITenantDeptService {
	/**
	 * 初始化租户部门 一级部门，营业所 二级部门，收费柜台、查表班
	 * 
	 * @param tenantId
	 * @return
	 */
	@Transactional(rollbackFor = Exception.class)
	public boolean initByTenant(Long tenantId) {
		Long idRoot = IdUtil.createSnowflake(1L, 1L).nextId();
		String[] tenantDeptRoot = new String[] { "营业所" };
		String[] tenantDeptLeaf = new String[] { "收费柜台", "查表班" };
		boolean success = false;

		Arrays.asList(tenantDeptRoot).forEach(n -> {
			TenantDept tenantDept = TenantDept.builder()//
					.id(idRoot)// 系统ID
					.parentDeptId(null)// 上级部门
					.tenantId(tenantId)// 租户编号
					.deptName(n)// 部门名称
					.build();
			baseMapper.insert(tenantDept);
		});

		Arrays.asList(tenantDeptLeaf).forEach(n -> {
			TenantDept tenantDept = TenantDept.builder()//
					.id(IdUtil.createSnowflake(1L, 1L).nextId())// 系统ID
					.parentDeptId(idRoot)// 上级部门
					.tenantId(tenantId)// 租户编号
					.deptName(n)// 部门名称
					.build();
			baseMapper.insert(tenantDept);
		});
		success = true;

		return success;
	}
}
