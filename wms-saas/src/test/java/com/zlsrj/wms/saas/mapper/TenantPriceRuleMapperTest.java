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
import com.zlsrj.wms.api.entity.TenantPriceRule;
import com.zlsrj.wms.api.entity.TenantInfo;

import cn.hutool.core.util.RandomUtil;
import lombok.extern.slf4j.Slf4j;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class TenantPriceRuleMapperTest {

	@Resource
	private TenantPriceRuleMapper tenantPriceRuleMapper;
	@Resource
	private TenantInfoMapper tenantInfoMapper;
	
	@Test
	public void selectByIdTest() {
		String id = "";
		TenantPriceRule tenantPriceRule = tenantPriceRuleMapper.selectById(id);
		log.info(tenantPriceRule.toString());
	}
	
	@Test
	public void selectList() {
		QueryWrapper<TenantPriceRule> queryWrapper = new QueryWrapper<TenantPriceRule>();
		List<TenantPriceRule> tenantPriceRuleList = tenantPriceRuleMapper.selectList(queryWrapper);
		tenantPriceRuleList.forEach(e -> {
			log.info(e.toString());
		});
	}

	@Test
	public void insert() {
		List<TenantInfo> tenantInfoList = tenantInfoMapper.selectList(new QueryWrapper<TenantInfo>());
		for(int i=0;i<RandomUtil.randomInt(10, 100);i++) {
			TenantInfo tenantInfo = tenantInfoList.get(RandomUtil.randomInt(tenantInfoList.size()));
			//tenantInfo = TenantInfo.builder().id(1L).build();
			
			TenantPriceRule tenantPriceRule = TenantPriceRule.builder()//
					.id(TestCaseUtil.id())// 
					.tenantId(tenantInfo.getId())// 租户ID
					.ruleName(TestCaseUtil.name())// 计费方法名称
					.ruleExplain(RandomUtil.randomString(4))// 计费方法说明
					.addTime(TestCaseUtil.dateBefore())// 数据新增时间
					.updateTime(TestCaseUtil.dateBefore())// 数据修改时间
					.build();
				
			int count = tenantPriceRuleMapper.insert(tenantPriceRule);
			log.info("count={}",count);
			log.info("tenantPriceRule={}",tenantPriceRule);
		}
		
	}
	
}
