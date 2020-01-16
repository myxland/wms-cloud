package com.zlsrj.wms.saas.mapper;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zlsrj.wms.common.test.TestCaseUtil;
import com.zlsrj.wms.api.entity.TenantEmployee;

import cn.hutool.core.util.RandomUtil;
import lombok.extern.slf4j.Slf4j;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class TenantEmployeeMapperTest {

	@Resource
	private TenantEmployeeMapper tenantEmployeeMapper;

	@Test
	public void selectByIdTest() {
		Long id = 1L;
		TenantEmployee tenantEmployee = tenantEmployeeMapper.selectById(id);
		log.info(tenantEmployee.toString());
	}
	
	@Test
	public void selectList() {
		QueryWrapper<TenantEmployee> queryWrapper = new QueryWrapper<TenantEmployee>();
		List<TenantEmployee> tenantEmployeeList = tenantEmployeeMapper.selectList(queryWrapper);
		tenantEmployeeList.forEach(e -> {
			log.info(e.toString());
		});
	}

	@Test
	public void insert() {
		TenantEmployee tenantEmployee = TenantEmployee.builder()//
				.id(TestCaseUtil.id())// 员工ID
				.tenantId(RandomUtil.randomLong())// 租户ID
				.employeeName(TestCaseUtil.name())// 员工名称
				.employeePassword(RandomUtil.randomString(4))// 登录密码
				.employeeDepartmentId(RandomUtil.randomLong())// 员工所属部门ID
				.employeeLoginOn(RandomUtil.randomInt(0,1+1))// 可登录系统（1：可登录；0：不能登录）
				.employeeStatus(RandomUtil.randomInt(0,1+1))// 员工状态（1：在职；2：离职；3：禁用）
				.employeeMobile(TestCaseUtil.mobile())// 员工手机号
				.employeeEmail(TestCaseUtil.email(null))// 员工邮箱
				.employeePersonalWx(RandomUtil.randomString(4))// 员工个人微信号
				.employeeEnterpriceWx(RandomUtil.randomString(4))// 员工企业微信号
				.employeeDingding(RandomUtil.randomString(4))// 钉钉号
				.employeeCreateType(RandomUtil.randomInt(0,1+1))// 操作员创建类型（1：平台默认创建；2：租户自建）
				.build();
				
		int count = tenantEmployeeMapper.insert(tenantEmployee);
		log.info("count={}",count);
		log.info("tenantEmployee={}",tenantEmployee);
	}
	
}
