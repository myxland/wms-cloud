package com.zlsrj.wms.saas.mapper;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zlsrj.wms.common.test.TestCaseUtil;
import com.zlsrj.wms.api.entity.TenantRole;

import cn.hutool.core.util.RandomUtil;
import lombok.extern.slf4j.Slf4j;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class TenantRoleMapperTest {

	@Resource
	private TenantRoleMapper tenantRoleMapper;

	@Test
	public void selectByIdTest() {
		String id = "";
		TenantRole tenantRole = tenantRoleMapper.selectById(id);
		log.info(tenantRole.toString());
	}
	
	@Test
	public void selectList() {
		QueryWrapper<TenantRole> queryWrapper = new QueryWrapper<TenantRole>();
		List<TenantRole> tenantRoleList = tenantRoleMapper.selectList(queryWrapper);
		tenantRoleList.forEach(e -> {
			log.info(e.toString());
		});
	}

	@Test
	public void insert() {
		TenantRole tenantRole = TenantRole.builder()//
				.id(TestCaseUtil.id())// 工作岗位ID
				.tenantId(RandomUtil.randomString(32))// 租户ID
				.roleName(TestCaseUtil.name())// 工作岗位名称
				.roleRemark(RandomUtil.randomString(4))// 工作岗位说明
				.createType(RandomUtil.randomInt(0,1+1))// 创建类型（1：平台默认创建；2：租户自建）
				.build();
				
		int count = tenantRoleMapper.insert(tenantRole);
		log.info("count={}",count);
		log.info("tenantRole={}",tenantRole);
	}
	
}
