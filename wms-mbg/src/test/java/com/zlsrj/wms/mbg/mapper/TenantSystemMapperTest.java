package com.zlsrj.wms.mbg.mapper;

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
import com.zlsrj.wms.api.entity.TenantSystem;

import cn.hutool.core.util.RandomUtil;
import lombok.extern.slf4j.Slf4j;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class TenantSystemMapperTest {

	@Resource
	private TenantSystemMapper tenantSystemMapper;

	@Test
	public void selectByIdTest() {
		Long id = 1L;
		TenantSystem tenantSystem = tenantSystemMapper.selectById(id);
		log.info(tenantSystem.toString());
	}
	
	@Test
	public void selectList() {
		QueryWrapper<TenantSystem> queryWrapper = new QueryWrapper<TenantSystem>();
		List<TenantSystem> tenantSystemList = tenantSystemMapper.selectList(queryWrapper);
		tenantSystemList.forEach(e -> {
			log.info(e.toString());
		});
	}

	@Test
	public void insert() {
		TenantSystem tenantSystem = TenantSystem.builder()//
				.id(TestCaseUtil.id())// 系统ID
				.tenantId(RandomUtil.randomLong())// 租户编号
				.sysId(RandomUtil.randomLong())// 模块编号
				.sysDispName(TestCaseUtil.name())// 模块显示名称
				.sysOrder(RandomUtil.randomInt(0,1000+1))// 模块排序
				.sysEdition(RandomUtil.randomInt(0,1000+1))// 开通版本（1：基础版；2：高级版；3：旗舰版）
				.sysStatus(RandomUtil.randomInt(0,1+1))// 模块状态（1：开通；0：未开通）
				.sysPriceType(RandomUtil.randomInt(0,1+1))// 价格体系（1：标准价格；2：指定价格）
				.sysOpenDate(new Date())// 开通时间
				.sysEndDate(new Date())// 到期时间
				.sysStartChargeDate(new Date())// 开始计费日期
				.sysArrears(new BigDecimal(0))// 欠费金额
				.sysOverdraft(new BigDecimal(0))// 透支额度
				.build();
				
		int count = tenantSystemMapper.insert(tenantSystem);
		log.info("count={}",count);
		log.info("tenantSystem={}",tenantSystem);
	}
	
}
