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
import com.zlsrj.wms.api.entity.TenantMeterBrandDefault;

import cn.hutool.core.util.RandomUtil;
import lombok.extern.slf4j.Slf4j;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class TenantMeterBrandDefaultMapperTest {

	@Resource
	private TenantMeterBrandDefaultMapper tenantMeterBrandDefaultMapper;
	
	@Test
	public void selectByIdTest() {
		String id = "";
		TenantMeterBrandDefault tenantMeterBrandDefault = tenantMeterBrandDefaultMapper.selectById(id);
		log.info(tenantMeterBrandDefault.toString());
	}
	
	@Test
	public void selectList() {
		QueryWrapper<TenantMeterBrandDefault> queryWrapper = new QueryWrapper<TenantMeterBrandDefault>();
		List<TenantMeterBrandDefault> tenantMeterBrandDefaultList = tenantMeterBrandDefaultMapper.selectList(queryWrapper);
		tenantMeterBrandDefaultList.forEach(e -> {
			log.info(e.toString());
		});
	}

	@Test
	public void insert() {
		for(int i=0;i<RandomUtil.randomInt(10, 100);i++) {
			TenantMeterBrandDefault tenantMeterBrandDefault = TenantMeterBrandDefault.builder()//
					.meterBrandName(TestCaseUtil.name())// 名称
					.meterBrandData(RandomUtil.randomString(4))// 结构化数据
					.addTime(TestCaseUtil.dateBefore())// 数据新增时间
					.updateTime(TestCaseUtil.dateBefore())// 数据修改时间
					.build();
				
			int count = tenantMeterBrandDefaultMapper.insert(tenantMeterBrandDefault);
			log.info("count={}",count);
			log.info("tenantMeterBrandDefault={}",tenantMeterBrandDefault);
		}
		
	}
	
}
