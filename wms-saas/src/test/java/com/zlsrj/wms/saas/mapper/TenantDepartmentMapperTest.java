package com.zlsrj.wms.saas.mapper;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zlsrj.wms.common.test.TestCaseUtil;
import com.zlsrj.wms.api.entity.TenantDepartment;

import cn.hutool.core.util.RandomUtil;
import lombok.extern.slf4j.Slf4j;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class TenantDepartmentMapperTest {

	@Resource
	private TenantDepartmentMapper tenantDepartmentMapper;

	@Test
	public void selectByIdTest() {
		String id = "";
		TenantDepartment tenantDepartment = tenantDepartmentMapper.selectById(id);
		log.info(tenantDepartment.toString());
	}
	
	@Test
	public void selectList() {
		QueryWrapper<TenantDepartment> queryWrapper = new QueryWrapper<TenantDepartment>();
		List<TenantDepartment> tenantDepartmentList = tenantDepartmentMapper.selectList(queryWrapper);
		tenantDepartmentList.forEach(e -> {
			log.info(e.toString());
		});
	}

	@Test
	public void insert() {
		TenantDepartment tenantDepartment = TenantDepartment.builder()//
				.id(TestCaseUtil.id())// 部门ID
				.tenantId(RandomUtil.randomString(32))// 租户ID
				.departmentName(TestCaseUtil.name())// 部门名称
				.departmentParentId(RandomUtil.randomString(32))// 上级部门ID
				.build();
				
		int count = tenantDepartmentMapper.insert(tenantDepartment);
		log.info("count={}",count);
		log.info("tenantDepartment={}",tenantDepartment);
	}
	
}
