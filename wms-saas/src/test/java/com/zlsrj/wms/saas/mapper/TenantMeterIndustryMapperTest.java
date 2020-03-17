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
import com.zlsrj.wms.api.entity.TenantMeterIndustry;
import com.zlsrj.wms.api.entity.TenantInfo;

import cn.hutool.core.util.RandomUtil;
import lombok.extern.slf4j.Slf4j;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class TenantMeterIndustryMapperTest {

	@Resource
	private TenantMeterIndustryMapper tenantMeterIndustryMapper;
	@Resource
	private TenantInfoMapper tenantInfoMapper;
	
	@Test
	public void selectByIdTest() {
		String id = "";
		TenantMeterIndustry tenantMeterIndustry = tenantMeterIndustryMapper.selectById(id);
		log.info(tenantMeterIndustry.toString());
	}
	
	@Test
	public void selectList() {
		QueryWrapper<TenantMeterIndustry> queryWrapper = new QueryWrapper<TenantMeterIndustry>();
		List<TenantMeterIndustry> tenantMeterIndustryList = tenantMeterIndustryMapper.selectList(queryWrapper);
		tenantMeterIndustryList.forEach(e -> {
			log.info(e.toString());
		});
	}

	@Test
	public void insert() {
		List<TenantInfo> tenantInfoList = tenantInfoMapper.selectList(new QueryWrapper<TenantInfo>());
		for(int i=0;i<RandomUtil.randomInt(10, 100);i++) {
			TenantInfo tenantInfo = tenantInfoList.get(RandomUtil.randomInt(tenantInfoList.size()));
			//tenantInfo = TenantInfo.builder().id(1L).build();
			
			TenantMeterIndustry tenantMeterIndustry = TenantMeterIndustry.builder()//
					.id(TestCaseUtil.id())// 行业分类ID
					.tenantId(tenantInfo.getId())// 租户ID
					.meterIndustryName(TestCaseUtil.name())// 名称
					.meterIndustryParentId(TestCaseUtil.id())// 父级ID
					.meterIndustryData(RandomUtil.randomString(4))// 结构化数据
					.addTime(TestCaseUtil.dateBefore())// 数据新增时间
					.updateTime(TestCaseUtil.dateBefore())// 数据修改时间
					.build();
				
			int count = tenantMeterIndustryMapper.insert(tenantMeterIndustry);
			log.info("count={}",count);
			log.info("tenantMeterIndustry={}",tenantMeterIndustry);
		}
		
	}
	
}
