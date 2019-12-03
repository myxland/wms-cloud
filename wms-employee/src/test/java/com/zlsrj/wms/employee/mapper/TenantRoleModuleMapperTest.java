package com.zlsrj.wms.employee.mapper;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zlsrj.wms.common.test.TestCaseUtil;
import com.zlsrj.wms.api.entity.TenantRoleModule;

import cn.hutool.core.util.RandomUtil;
import lombok.extern.slf4j.Slf4j;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class TenantRoleModuleMapperTest {

	@Resource
	private TenantRoleModuleMapper tenantRoleModuleMapper;

	@Test
	public void selectByIdTest() {
		Long id = 1L;
		TenantRoleModule tenantRoleModule = tenantRoleModuleMapper.selectById(id);
		log.info(tenantRoleModule.toString());
	}
	
	@Test
	public void selectList() {
		QueryWrapper<TenantRoleModule> queryWrapper = new QueryWrapper<TenantRoleModule>();
		List<TenantRoleModule> tenantRoleModuleList = tenantRoleModuleMapper.selectList(queryWrapper);
		tenantRoleModuleList.forEach(e -> {
			log.info(e.toString());
		});
	}

	@Test
	public void insert() {
		TenantRoleModule tenantRoleModule = TenantRoleModule.builder()//
				.id(TestCaseUtil.id())// 系统ID
				.tenantId(RandomUtil.randomLong())// 租户编号
				.roleId(RandomUtil.randomLong())// 角色编号
				.moduleId(RandomUtil.randomLong())// 模块编号
				.roleModuleOn(RandomUtil.randomInt(0,1+1))// 开放（1：开放；0：不开放）
				.build();
				
		int count = tenantRoleModuleMapper.insert(tenantRoleModule);
		log.info("count={}",count);
		log.info("tenantRoleModule={}",tenantRoleModule);
	}
	
}
