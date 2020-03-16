package com.zlsrj.wms.saas.service.impl;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zlsrj.wms.api.dto.TenantRoleAddParam;
import com.zlsrj.wms.api.dto.TenantRoleUpdateParam;
import com.zlsrj.wms.api.entity.TenantEmployeeRole;
import com.zlsrj.wms.api.entity.TenantInfo;
import com.zlsrj.wms.api.entity.TenantRole;
import com.zlsrj.wms.api.entity.TenantRoleMenu;
import com.zlsrj.wms.api.enums.RoleEnum;
import com.zlsrj.wms.common.util.TranslateUtil;
import com.zlsrj.wms.saas.mapper.TenantEmployeeRoleMapper;
import com.zlsrj.wms.saas.mapper.TenantRoleMapper;
import com.zlsrj.wms.saas.mapper.TenantRoleMenuMapper;
import com.zlsrj.wms.saas.service.IIdService;
import com.zlsrj.wms.saas.service.ITenantRoleService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class TenantRoleServiceImpl extends ServiceImpl<TenantRoleMapper, TenantRole> implements ITenantRoleService {
	@Resource
	private IIdService idService;
	
	@Resource
	private TenantRoleMenuMapper tenantRoleMenuMapper;
	
	@Resource
	private TenantEmployeeRoleMapper tenantEmployeeRoleMapper;

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
	
	@Override
	@Transactional
	public String save(TenantRoleAddParam tenantRoleAddParam) {
		TenantRole tenantRole = TranslateUtil.translate(tenantRoleAddParam, TenantRole.class);
		if (tenantRole.getId() == null || tenantRole.getId().trim().length() == 0) {
			tenantRole.setId(idService.selectId());
		}
		this.save(tenantRole);
		
		// moduleMenu
		QueryWrapper<TenantRoleMenu> wrapperTenantRoleMenu = new QueryWrapper<TenantRoleMenu>();
		wrapperTenantRoleMenu.lambda()//
				.eq(TenantRoleMenu::getTenantId, tenantRole.getTenantId())
				.eq(TenantRoleMenu::getRoleId, tenantRole.getId());
		tenantRoleMenuMapper.delete(wrapperTenantRoleMenu);

		List<Map<String,String>> moduleMenuList = tenantRoleAddParam.getModuleMenuList();
		if (moduleMenuList != null && moduleMenuList.size() > 0) {
			for (Map<String,String> map : moduleMenuList) {
				TenantRoleMenu tenantRoleMenu = TenantRoleMenu.builder()//
						.id(idService.selectId())//
						.tenantId(tenantRole.getTenantId())//
						.roleId(tenantRole.getId())//
						.menuId(map.get("id"))
						.build();
				tenantRoleMenuMapper.insert(tenantRoleMenu);
			}
		}else {
			log.info("新增角色未配菜单信息");
		}
		
		// tenantEmployee
		QueryWrapper<TenantEmployeeRole> wrapperTenantEmployeeRole = new QueryWrapper<TenantEmployeeRole>();
		wrapperTenantEmployeeRole.lambda()//
				.eq(TenantEmployeeRole::getTenantId, tenantRole.getTenantId())
				.eq(TenantEmployeeRole::getRoleId, tenantRole.getId());
		tenantEmployeeRoleMapper.delete(wrapperTenantEmployeeRole);
		
		List<Map<String,String>> tenantEmployeeList = tenantRoleAddParam.getTenantEmployeeList();
		if (tenantEmployeeList != null && tenantEmployeeList.size() > 0) {
			for (Map<String,String> map : tenantEmployeeList) {
				TenantEmployeeRole tenantEmployeeRole = TenantEmployeeRole.builder()//
						.id(idService.selectId())//
						.tenantId(tenantRole.getTenantId())//
						.employeeId(map.get("id"))
						.roleId(tenantRole.getId())//
						.build();
				tenantEmployeeRoleMapper.insert(tenantEmployeeRole);
			}
		}else {
			log.info("新增角色未配员工信息");
		}
		
		return tenantRole.getId();
	}
	
	@Override
	@Transactional
	public boolean updateById(TenantRoleUpdateParam tenantRoleUpdateParam) {
		boolean success = false;
		String id = tenantRoleUpdateParam.getId();
		TenantRole tenantRole = TranslateUtil.translate(tenantRoleUpdateParam, TenantRole.class);
		
		// moduleMenu
		QueryWrapper<TenantRoleMenu> wrapperTenantRoleMenu = new QueryWrapper<TenantRoleMenu>();
		wrapperTenantRoleMenu.lambda()//
				.eq(TenantRoleMenu::getTenantId, tenantRole.getTenantId())
				.eq(TenantRoleMenu::getRoleId, tenantRole.getId());
		tenantRoleMenuMapper.delete(wrapperTenantRoleMenu);

		List<Map<String,String>> moduleMenuList = tenantRoleUpdateParam.getModuleMenuList();
		if (moduleMenuList != null && moduleMenuList.size() > 0) {
			for (Map<String,String> map : moduleMenuList) {
				TenantRoleMenu tenantRoleMenu = TenantRoleMenu.builder()//
						.id(idService.selectId())//
						.tenantId(tenantRole.getTenantId())//
						.roleId(tenantRole.getId())//
						.menuId(map.get("id"))
						.build();
				tenantRoleMenuMapper.insert(tenantRoleMenu);
			}
		}else {
			log.info("更新角色未配菜单信息");
		}
		
		// tenantEmployee
		QueryWrapper<TenantEmployeeRole> wrapperTenantEmployeeRole = new QueryWrapper<TenantEmployeeRole>();
		wrapperTenantEmployeeRole.lambda()//
				.eq(TenantEmployeeRole::getTenantId, tenantRole.getTenantId())
				.eq(TenantEmployeeRole::getRoleId, tenantRole.getId());
		tenantEmployeeRoleMapper.delete(wrapperTenantEmployeeRole);
		
		List<Map<String,String>> tenantEmployeeList = tenantRoleUpdateParam.getTenantEmployeeList();
		if (tenantEmployeeList != null && tenantEmployeeList.size() > 0) {
			for (Map<String,String> map : tenantEmployeeList) {
				TenantEmployeeRole tenantEmployeeRole = TenantEmployeeRole.builder()//
						.id(idService.selectId())//
						.tenantId(tenantRole.getTenantId())//
						.employeeId(map.get("id"))
						.roleId(tenantRole.getId())//
						.build();
				tenantEmployeeRoleMapper.insert(tenantEmployeeRole);
			}
		}else {
			log.info("更新角色未配员工信息");
		}
		
		success = this.updateById(tenantRole);
		
		return success;
	}

}
