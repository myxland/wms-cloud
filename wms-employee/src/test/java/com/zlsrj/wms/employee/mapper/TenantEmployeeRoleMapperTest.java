package com.zlsrj.wms.employee.mapper;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zlsrj.wms.common.test.TestCaseUtil;
import com.zlsrj.wms.api.entity.TenantEmployeeRole;

import cn.hutool.core.util.RandomUtil;
import lombok.extern.slf4j.Slf4j;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class TenantEmployeeRoleMapperTest {

	@Resource
	private TenantEmployeeRoleMapper tenantEmployeeRoleMapper;

	@Test
	public void selectByIdTest() {
		Long id = 1L;
		TenantEmployeeRole tenantEmployeeRole = tenantEmployeeRoleMapper.selectById(id);
		log.info(tenantEmployeeRole.toString());
	}
	
	@Test
	public void selectList() {
		QueryWrapper<TenantEmployeeRole> queryWrapper = new QueryWrapper<TenantEmployeeRole>();
		List<TenantEmployeeRole> tenantEmployeeRoleList = tenantEmployeeRoleMapper.selectList(queryWrapper);
		tenantEmployeeRoleList.forEach(e -> {
			log.info(e.toString());
		});
	}

	@Test
	public void insert() {
		TenantEmployeeRole tenantEmployeeRole = TenantEmployeeRole.builder()//
				.id(TestCaseUtil.id())// 系统ID
				.tenantId(RandomUtil.randomLong())// 租户编号
				.empId(RandomUtil.randomLong())// 员工编号
				.roleId(RandomUtil.randomLong())// 角色编号
				.empRoleOn(RandomUtil.randomInt(0,1+1))// 开放（1：开放；0：不开放）
				.build();
				
		int count = tenantEmployeeRoleMapper.insert(tenantEmployeeRole);
		log.info("count={}",count);
		log.info("tenantEmployeeRole={}",tenantEmployeeRole);
	}
	
}
