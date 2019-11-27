package com.zlsrj.wms.tenant.mapper;

import java.math.BigDecimal;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zlsrj.wms.common.test.TestCaseUtil;
import com.zlsrj.wms.api.entity.TenantConfig;

import cn.hutool.core.util.RandomUtil;
import lombok.extern.slf4j.Slf4j;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class TenantConfigMapperTest {

	@Resource
	private TenantConfigMapper tenantConfigMapper;

	@Test
	public void selectByIdTest() {
		Long id = 1L;
		TenantConfig tenantConfig = tenantConfigMapper.selectById(id);
		log.info(tenantConfig.toString());
	}
	
	@Test
	public void selectList() {
		QueryWrapper<TenantConfig> queryWrapper = new QueryWrapper<TenantConfig>();
		List<TenantConfig> tenantConfigList = tenantConfigMapper.selectList(queryWrapper);
		tenantConfigList.forEach(e -> {
			log.info(e.toString());
		});
	}

	@Test
	public void insert() {
		TenantConfig tenantConfig = TenantConfig.builder()//
				.id(TestCaseUtil.id())// 租户编号
				.tenantId(RandomUtil.randomLong())// 租户编号
				.partChargeOn(RandomUtil.randomInt(0,1+1))// 是否启用部分缴费（1：启用；0：不启用）
				.overDuefineOn(RandomUtil.randomInt(0,1+1))// 是否启用违约金（1：启用；0：不启用）
				.overDuefineDay(RandomUtil.randomInt(0,1000+1))// 违约金宽限天数
				.overDuefineRatio(new BigDecimal(0))// 违约金每天收取比例
				.overDuefineTopRatio(new BigDecimal(0))// 违约金封顶比例（与欠费金额相比）
				.ycdkType(RandomUtil.randomInt(0,1+1))// 预存抵扣方式（1：抄表后即时抵扣；2：人工发起抵扣）
				.build();
				
		int count = tenantConfigMapper.insert(tenantConfig);
		log.info("count={}",count);
		log.info("tenantConfig={}",tenantConfig);
	}
	
}
