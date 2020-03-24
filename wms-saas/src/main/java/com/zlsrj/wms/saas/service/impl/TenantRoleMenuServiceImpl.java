package com.zlsrj.wms.saas.service.impl;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zlsrj.wms.api.entity.ModuleInfo;
import com.zlsrj.wms.api.entity.ModuleMenu;
import com.zlsrj.wms.api.entity.TenantInfo;
import com.zlsrj.wms.api.entity.TenantRole;
import com.zlsrj.wms.api.entity.TenantRoleMenu;
import com.zlsrj.wms.saas.mapper.ModuleInfoMapper;
import com.zlsrj.wms.saas.mapper.ModuleMenuMapper;
import com.zlsrj.wms.saas.mapper.TenantRoleMapper;
import com.zlsrj.wms.saas.mapper.TenantRoleMenuMapper;
import com.zlsrj.wms.saas.service.IIdService;
import com.zlsrj.wms.saas.service.ITenantRoleMenuService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class TenantRoleMenuServiceImpl extends ServiceImpl<TenantRoleMenuMapper, TenantRoleMenu> implements ITenantRoleMenuService {
	@Resource
	private IIdService idService;
	
	@Resource
	private ModuleInfoMapper moduleInfoMapper;
	@Resource
	private ModuleMenuMapper moduleMenuMapper;
	@Resource
	private TenantRoleMapper tenantRoleMapper;
	
	@Override
	public boolean saveBatchByTenantInfo(TenantInfo tenantInfo) {
		boolean success = false;
		QueryWrapper<TenantRoleMenu> queryWrapperTenantRoleMenu = new QueryWrapper<TenantRoleMenu>();
		queryWrapperTenantRoleMenu.lambda()//
				.eq(TenantRoleMenu::getTenantId, tenantInfo.getId())//
		;
		int count = super.count(queryWrapperTenantRoleMenu);
		if (count > 0) {
			log.error("根据租户信息初始化角色菜单失败，角色菜单已存在。");
			return false;
		}

		QueryWrapper<TenantRole> queryWrapperTenantRole = new QueryWrapper<TenantRole>();
		queryWrapperTenantRole.lambda()//
				.eq(TenantRole::getTenantId, tenantInfo.getId())//
				.eq(TenantRole::getRoleName, "系统管理员")//
				.eq(TenantRole::getCreateType, 1)//
		;
		TenantRole tenantRole = tenantRoleMapper.selectOne(queryWrapperTenantRole);

		QueryWrapper<ModuleInfo> queryWrapperModuleInfo = new QueryWrapper<ModuleInfo>();
		queryWrapperModuleInfo.lambda()//
				.eq(ModuleInfo::getOpenTarget, tenantInfo.getTenantType())//
				.eq(ModuleInfo::getBillingMode, 1)//
				.eq(ModuleInfo::getModuleOn, 1)//
		;
		List<ModuleInfo> moduleInfoList = moduleInfoMapper.selectList(queryWrapperModuleInfo);
		if (moduleInfoList != null && moduleInfoList.size() > 0) {
			QueryWrapper<ModuleMenu> queryWrapperModuleMenu = new QueryWrapper<ModuleMenu>();
			queryWrapperModuleMenu.lambda()//
					.in(ModuleMenu::getModuleId, moduleInfoList.stream()//
							.map(e -> e.getId()).collect(Collectors.toList()));
			List<ModuleMenu> moduleMenuList = moduleMenuMapper.selectList(queryWrapperModuleMenu);

			if (moduleMenuList != null && moduleMenuList.size() > 0) {
				List<TenantRoleMenu> tenantRoleMenuList = new ArrayList<TenantRoleMenu>();
				for (ModuleMenu moduleMenu : moduleMenuList) {
					TenantRoleMenu tenantRoleMenu = TenantRoleMenu.builder()//
							.id(idService.selectId())//
							.tenantId(tenantInfo.getId())//
							.roleId(tenantRole.getId())//
							.menuId(moduleMenu.getId())//
							.build();
					tenantRoleMenuList.add(tenantRoleMenu);
				}

				success = this.saveBatch(tenantRoleMenuList);
			}

		}

		return success;
	}
}
