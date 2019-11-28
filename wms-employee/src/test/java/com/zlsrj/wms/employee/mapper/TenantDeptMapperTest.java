package com.zlsrj.wms.employee.mapper;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zlsrj.wms.common.test.TestCaseUtil;
import com.zlsrj.wms.api.entity.TenantDept;

import cn.hutool.core.util.RandomUtil;
import lombok.extern.slf4j.Slf4j;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class TenantDeptMapperTest {

	@Resource
	private TenantDeptMapper tenantDeptMapper;

	@Test
	public void selectByIdTest() {
		Long id = 1L;
		TenantDept tenantDept = tenantDeptMapper.selectById(id);
		log.info(tenantDept.toString());
	}
	
	@Test
	public void selectList() {
		QueryWrapper<TenantDept> queryWrapper = new QueryWrapper<TenantDept>();
		List<TenantDept> tenantDeptList = tenantDeptMapper.selectList(queryWrapper);
		tenantDeptList.forEach(e -> {
			log.info(e.toString());
		});
	}

	@Test
	public void insert() {
		TenantDept tenantDept = TenantDept.builder()//
				.id(TestCaseUtil.id())// 系统ID
				.parentDeptId(RandomUtil.randomLong())// 上级部门
				.tenantId(RandomUtil.randomLong())// 租户编号
				.deptName(TestCaseUtil.name())// 部门名称
				.build();
				
		int count = tenantDeptMapper.insert(tenantDept);
		log.info("count={}",count);
		log.info("tenantDept={}",tenantDept);
	}
	
}
