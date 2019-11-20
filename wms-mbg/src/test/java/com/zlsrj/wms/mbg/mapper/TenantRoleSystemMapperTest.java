package com.zlsrj.wms.mbg.mapper;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zlsrj.wms.common.test.TestCaseUtil;
import com.zlsrj.wms.api.entity.TenantRoleSystem;

import cn.hutool.core.util.RandomUtil;
import lombok.extern.slf4j.Slf4j;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class TenantRoleSystemMapperTest {

	@Resource
	private TenantRoleSystemMapper tenantRoleSystemMapper;

	@Test
	public void selectByIdTest() {
		Long id = 1L;
		TenantRoleSystem tenantRoleSystem = tenantRoleSystemMapper.selectById(id);
		log.info(tenantRoleSystem.toString());
	}
	
	@Test
	public void selectList() {
		QueryWrapper<TenantRoleSystem> queryWrapper = new QueryWrapper<TenantRoleSystem>();
		List<TenantRoleSystem> tenantRoleSystemList = tenantRoleSystemMapper.selectList(queryWrapper);
		tenantRoleSystemList.forEach(e -> {
			log.info(e.toString());
		});
	}

	@Test
	public void insert() {
		TenantRoleSystem tenantRoleSystem = TenantRoleSystem.builder()//
				.id(TestCaseUtil.id())// 系统ID
				.tenantId(RandomUtil.randomLong())// 租户编号
				.roleId(RandomUtil.randomLong())// 角色编号
				.sysId(RandomUtil.randomLong())// 模块编号
				.roleSysOn(RandomUtil.randomInt(0,1+1))// 开放（1：开放；0：不开放）
				.build();
				
		int count = tenantRoleSystemMapper.insert(tenantRoleSystem);
		log.info("count={}",count);
		log.info("tenantRoleSystem={}",tenantRoleSystem);
	}
	
}
