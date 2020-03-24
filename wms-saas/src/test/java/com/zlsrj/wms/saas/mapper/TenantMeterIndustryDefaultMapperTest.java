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
import com.zlsrj.wms.api.entity.TenantMeterIndustryDefault;

import cn.hutool.core.util.RandomUtil;
import lombok.extern.slf4j.Slf4j;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class TenantMeterIndustryDefaultMapperTest {

	@Resource
	private TenantMeterIndustryDefaultMapper tenantMeterIndustryDefaultMapper;
	
	@Test
	public void selectByIdTest() {
		String id = "";
		TenantMeterIndustryDefault tenantMeterIndustryDefault = tenantMeterIndustryDefaultMapper.selectById(id);
		log.info(tenantMeterIndustryDefault.toString());
	}
	
	@Test
	public void selectList() {
		QueryWrapper<TenantMeterIndustryDefault> queryWrapper = new QueryWrapper<TenantMeterIndustryDefault>();
		List<TenantMeterIndustryDefault> tenantMeterIndustryDefaultList = tenantMeterIndustryDefaultMapper.selectList(queryWrapper);
		tenantMeterIndustryDefaultList.forEach(e -> {
			log.info(e.toString());
		});
	}

	@Test
	public void insert() {
		for(int i=0;i<RandomUtil.randomInt(10, 100);i++) {
			TenantMeterIndustryDefault tenantMeterIndustryDefault = TenantMeterIndustryDefault.builder()//
					.id(TestCaseUtil.id())// 
					.meterIndustryName(TestCaseUtil.name())// 名称
					.meterIndustryParentId(TestCaseUtil.id())// 父级ID
					.meterIndustryData(RandomUtil.randomString(4))// 结构化数据
					.addTime(TestCaseUtil.dateBefore())// 数据新增时间
					.updateTime(TestCaseUtil.dateBefore())// 数据修改时间
					.build();
				
			int count = tenantMeterIndustryDefaultMapper.insert(tenantMeterIndustryDefault);
			log.info("count={}",count);
			log.info("tenantMeterIndustryDefault={}",tenantMeterIndustryDefault);
		}
		
	}
	
}
