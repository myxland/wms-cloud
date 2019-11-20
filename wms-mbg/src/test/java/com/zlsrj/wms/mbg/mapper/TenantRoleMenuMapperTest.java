package com.zlsrj.wms.mbg.mapper;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zlsrj.wms.common.test.TestCaseUtil;
import com.zlsrj.wms.api.entity.TenantRoleMenu;

import cn.hutool.core.util.RandomUtil;
import lombok.extern.slf4j.Slf4j;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class TenantRoleMenuMapperTest {

	@Resource
	private TenantRoleMenuMapper tenantRoleMenuMapper;

	@Test
	public void selectByIdTest() {
		Long id = 1L;
		TenantRoleMenu tenantRoleMenu = tenantRoleMenuMapper.selectById(id);
		log.info(tenantRoleMenu.toString());
	}
	
	@Test
	public void selectList() {
		QueryWrapper<TenantRoleMenu> queryWrapper = new QueryWrapper<TenantRoleMenu>();
		List<TenantRoleMenu> tenantRoleMenuList = tenantRoleMenuMapper.selectList(queryWrapper);
		tenantRoleMenuList.forEach(e -> {
			log.info(e.toString());
		});
	}

	@Test
	public void insert() {
		TenantRoleMenu tenantRoleMenu = TenantRoleMenu.builder()//
				.id(TestCaseUtil.id())// 系统ID
				.tenantId(RandomUtil.randomLong())// 租户编号
				.roleId(RandomUtil.randomLong())// 角色编号
				.sysId(RandomUtil.randomLong())// 模块编号
				.menuId(RandomUtil.randomLong())// 菜单编号
				.roleMenuOn(RandomUtil.randomInt(0,1+1))// 开放（1：开放；0：不开放）
				.build();
				
		int count = tenantRoleMenuMapper.insert(tenantRoleMenu);
		log.info("count={}",count);
		log.info("tenantRoleMenu={}",tenantRoleMenu);
	}
	
}
