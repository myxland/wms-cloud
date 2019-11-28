package com.zlsrj.wms.mbg.service.impl;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zlsrj.wms.api.entity.SystemDesign;
import com.zlsrj.wms.api.entity.SystemMenuDesign;
import com.zlsrj.wms.api.entity.TenantDept;
import com.zlsrj.wms.api.entity.TenantEmployee;
import com.zlsrj.wms.api.entity.TenantEmployeeRole;
import com.zlsrj.wms.api.entity.TenantInfo;
import com.zlsrj.wms.api.entity.TenantRole;
import com.zlsrj.wms.api.entity.TenantRoleMenu;
import com.zlsrj.wms.api.entity.TenantRoleSystem;
import com.zlsrj.wms.api.entity.TenantSystem;
import com.zlsrj.wms.api.enums.PricePolicyTypeEnum;
import com.zlsrj.wms.mbg.service.ISystemDesignService;
import com.zlsrj.wms.mbg.service.ISystemMenuDesignService;
import com.zlsrj.wms.mbg.service.ITenantDeptService;
import com.zlsrj.wms.mbg.service.ITenantEmployeeRoleService;
import com.zlsrj.wms.mbg.service.ITenantEmployeeService;
import com.zlsrj.wms.mbg.service.ITenantRbacService;
import com.zlsrj.wms.mbg.service.ITenantRoleMenuService;
import com.zlsrj.wms.mbg.service.ITenantRoleService;
import com.zlsrj.wms.mbg.service.ITenantRoleSystemService;
import com.zlsrj.wms.mbg.service.ITenantSystemService;

import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.RandomUtil;
import cn.hutool.crypto.digest.DigestUtil;

@Service
public class TenantRbacServiceImpl implements ITenantRbacService {

	@Resource
	private ITenantDeptService tenantDeptService;

	@Resource
	private ITenantEmployeeService tenantEmployeeService;

	@Resource
	private ITenantRoleService tenantRoleService;

	@Resource
	private ITenantSystemService tenantSystemService;

	@Resource
	private ITenantRoleSystemService tenantRoleSystemService;

	@Resource
	private ITenantRoleMenuService tenantRoleMenuService;

	@Resource
	private ITenantEmployeeRoleService tenantEmployeeRoleService;

	@Resource
	private ISystemDesignService systemDesignService;
	@Resource
	private ISystemMenuDesignService systemMenuDesignService;

	/*
	 * 基于角色的权限访问控制（Role-Based Access Control） 创建部门 创建员工 创建角色 创建系统（模块） 创建菜单
	 * 创建角色与系统（模块）关系 创建角色与菜单关系 创建员工与角色关系
	 */
	public boolean initByTenant(TenantInfo tenantInfo) {
		boolean success = false;
		Long tenantDeptId = null;
		Long tenantEmployeeId = null;
		Long tenantRoleId = null;

		// 部门
		Long idRoot = IdUtil.createSnowflake(1L, 1L).nextId();
		String[] tenantDeptRoot = new String[] { "营业所" };
		String[] tenantDeptLeaf = new String[] { "收费柜台", "查表班" };

		for (String n : tenantDeptRoot) {
			TenantDept tenantDept = TenantDept.builder()//
					.id(idRoot)// 系统ID
					.parentDeptId(null)// 上级部门
					.tenantId(tenantInfo.getId())// 租户编号
					.deptName(n)// 部门名称
					.build();
			tenantDeptService.save(tenantDept);
			tenantDeptId = tenantDept.getId();
		}

		Arrays.asList(tenantDeptLeaf).forEach(n -> {
			TenantDept tenantDept = TenantDept.builder()//
					.id(IdUtil.createSnowflake(1L, 1L).nextId())// 系统ID
					.parentDeptId(idRoot)// 上级部门
					.tenantId(tenantInfo.getId())// 租户编号
					.deptName(n)// 部门名称
					.build();
			tenantDeptService.save(tenantDept);
		});

		// 员工
		TenantEmployee tenantEmployee = TenantEmployee.builder()//
				.id(IdUtil.createSnowflake(1L, 1L).nextId())// 系统ID
				.tenantId(tenantInfo.getId())// 租户编号
				.empName(tenantInfo.getTenantContact())// 员工名称
				.empPassword(DigestUtil.md5Hex(RandomUtil.randomString(8)))// 登录密码
				.deptId(tenantDeptId)// 员工部门
				.loginOn(1)// 可登录系统（1可登录，0不能登录）
				.empStatus(1)// 员工状态（在职/离职/禁用）
				.empMobile(tenantInfo.getTenantMobile())// 员工手机号
				.empEmail(tenantInfo.getTenantEmail())// 员工邮箱
				.empPersonalWx(null)// 员工个人微信号
				.empEnterpriceWx(null)// 员工企业微信号
				.build();

		tenantEmployeeService.save(tenantEmployee);

		tenantEmployeeId = tenantEmployee.getId();

		// 角色
		TenantRole tenantRole = TenantRole.builder()//
				.id(IdUtil.createSnowflake(1L, 1L).nextId())// 系统ID
				.tenantId(tenantInfo.getId())// 租户编号
				.roleName("系统管理员")// 角色名称
				.roleRemark("系统默认建立，拥有最大权限")// 角色说明
				.build();

		tenantRoleService.save(tenantRole);
		tenantRoleId = tenantRole.getId();

		// 员工与角色关系
		TenantEmployeeRole tenantEmployeeRole = TenantEmployeeRole.builder()//
				.id(IdUtil.createSnowflake(1L, 1L).nextId())// 系统ID
				.tenantId(tenantInfo.getId())// 租户编号
				.empId(tenantEmployeeId)// 员工编号
				.roleId(tenantRoleId)// 角色编号
				.empRoleOn(1)// 开放（1开放，0不开放）
				.build();

		tenantEmployeeRoleService.save(tenantEmployeeRole);

		// 创建系统（模块）
		QueryWrapper<SystemDesign> queryWrapperSystemDesign = new QueryWrapper<SystemDesign>();
		queryWrapperSystemDesign.lambda().eq(SystemDesign::getOpenTenantType, tenantInfo.getTenantType());

		List<SystemDesign> systemDesignList = systemDesignService.list(queryWrapperSystemDesign);

		if (systemDesignList != null && systemDesignList.size() > 0) {
			for (SystemDesign s : systemDesignList) {
				TenantSystem tenantSystem = TenantSystem.builder()//
						.id(IdUtil.createSnowflake(1L, 1L).nextId())// 系统ID
						.tenantId(tenantInfo.getId())// 租户编号
						.sysId(s.getId())// 模块编号
						.sysDispName(s.getModuleName())// 模块显示名称
						.sysOrder(1)// 模块排序
						.sysEdition(s.getBasicOn() > 0 ? 1 : null)// 开通版本（基础版/高级版/旗舰版）
						.sysStatus(s.getModuleReleaseOn())// 模块状态（1开通/0未开通）
						.sysPriceType(s.getPricePolicyType() == PricePolicyTypeEnum.FREE.getCode() ? 1 : 2)// 价格体系（标准价格/指定价格）
						.sysOpenDate(new Date())// 开通时间
						.sysEndDate(new Date())// 到期时间
						.sysStartChargeDate(new Date())// 开始计费日期
						.sysArrears(new BigDecimal(0))// 欠费金额
						.sysOverdraft(new BigDecimal(0))// 透支额度
						.build();

				tenantSystemService.save(tenantSystem);

				// 角色与系统（模块）关系
				TenantRoleSystem tenantRoleSystem = TenantRoleSystem.builder()//
						.id(IdUtil.createSnowflake(1L, 1L).nextId())// 系统ID
						.tenantId(tenantInfo.getId())// 租户编号
						.roleId(tenantRoleId)// 角色编号
						.sysId(tenantSystem.getId())// 模块编号
						.roleSysOn(1)// 开放（1开放，0不开放）
						.build();

				tenantRoleSystemService.save(tenantRoleSystem);

				// 角色与菜单关系
				QueryWrapper<SystemMenuDesign> queryWrapperSystemMenuDesign = new QueryWrapper<SystemMenuDesign>();
				queryWrapperSystemMenuDesign.lambda().eq(SystemMenuDesign::getSysId, s.getId());

				List<SystemMenuDesign> systemMenuDesignList = systemMenuDesignService
						.list(queryWrapperSystemMenuDesign);

				if (systemMenuDesignList != null && systemMenuDesignList.size() > 0) {
					for (SystemMenuDesign m : systemMenuDesignList) {
						TenantRoleMenu tenantRoleMenu = TenantRoleMenu.builder()//
								.id(IdUtil.createSnowflake(1L, 1L).nextId())// 系统ID
								.tenantId(tenantInfo.getId())// 租户编号
								.roleId(tenantRoleId)// 角色编号
								.sysId(s.getId())// 模块编号
								.menuId(m.getId())// 菜单编号
								.roleMenuOn(1)// 开放（1开放，0不开放）
								.build();

						tenantRoleMenuService.save(tenantRoleMenu);
					}
				}

			}

		}


		success = true;
		return success;
	}
}
