package com.zlsrj.wms.tenant.mapper;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zlsrj.wms.api.entity.TenantModule;
import com.zlsrj.wms.common.test.TestCaseUtil;

import cn.hutool.core.util.RandomUtil;
import lombok.extern.slf4j.Slf4j;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class TenantModuleMapperTest {

	@Resource
	private TenantModuleMapper tenantModuleMapper;

	@Test
	public void selectByIdTest() {
		Long id = 1L;
		TenantModule tenantModule = tenantModuleMapper.selectById(id);
		log.info(tenantModule.toString());
	}
	
	@Test
	public void selectList() {
		QueryWrapper<TenantModule> queryWrapper = new QueryWrapper<TenantModule>();
		List<TenantModule> tenantModuleList = tenantModuleMapper.selectList(queryWrapper);
		tenantModuleList.forEach(e -> {
			log.info(e.toString());
		});
	}

	@Test
	public void insert() {

		TenantModule tenantModule = TenantModule.builder()//
				.id(TestCaseUtil.id())// 系统ID
				.tenantId(RandomUtil.randomLong())// 租户编号
				.moduleId(RandomUtil.randomLong())// 模块编号
				.build();
				
		int count = tenantModuleMapper.insert(tenantModule);
		log.info("count={}",count);
		log.info("tenantModule={}",tenantModule);
	}
	
}
