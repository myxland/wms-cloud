package com.zlsrj.wms.tenant.mapper;

import java.math.BigDecimal;
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
		String companyShortName = TestCaseUtil.companyShortName();

		TenantModule tenantModule = TenantModule.builder()//
				.id(TestCaseUtil.id())// 系统ID
				.tenantId(RandomUtil.randomLong())// 租户编号
				.moduleId(RandomUtil.randomLong())// 模块编号
				.moduleDisplayName(companyShortName)// 模块显示名称
				.moduleOrder(RandomUtil.randomInt(0,1000+1))// 模块排序
				.moduleEdition(RandomUtil.randomInt(0,1000+1))// 开通版本（1：基础版；2：高级版；3：旗舰版）
				.moduleStatus(RandomUtil.randomInt(0,1+1))// 模块状态（1：开通；0：未开通）
				.modulePriceType(RandomUtil.randomInt(0,1+1))// 价格体系（1：标准价格；2：指定价格）
				.moduleOpenDate(new Date())// 开通时间
				.moduleEndDate(new Date())// 到期时间
				.moduleStartChargeDate(new Date())// 开始计费日期
				.moduleArrears(new BigDecimal(0))// 欠费金额
				.moduleOverdraft(new BigDecimal(0))// 透支额度
				.build();
				
		int count = tenantModuleMapper.insert(tenantModule);
		log.info("count={}",count);
		log.info("tenantModule={}",tenantModule);
	}
	
}
