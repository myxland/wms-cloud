package com.zlsrj.wms.saas.mapper;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zlsrj.wms.common.test.TestCaseUtil;
import com.zlsrj.wms.api.entity.TenantModule;

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
		String id = "";
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
				.id(TestCaseUtil.id())// 租户模块ID
				.tenantId(RandomUtil.randomString(32))// 租户ID
				.moduleId(RandomUtil.randomString(32))// 模块ID
				.moduleEdition(RandomUtil.randomInt(0,1000+1))// 开通版本（1：基础版；2：高级版；3：旗舰版）
				.moduleOpenTime(new Date())// 开通时间
				.build();
				
		int count = tenantModuleMapper.insert(tenantModule);
		log.info("count={}",count);
		log.info("tenantModule={}",tenantModule);
	}
	
}
