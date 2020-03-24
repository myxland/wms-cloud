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
import com.zlsrj.wms.api.entity.TenantMeterTypeDefault;

import cn.hutool.core.util.RandomUtil;
import lombok.extern.slf4j.Slf4j;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class TenantMeterTypeDefaultMapperTest {

	@Resource
	private TenantMeterTypeDefaultMapper tenantMeterTypeDefaultMapper;
	
	@Test
	public void selectByIdTest() {
		String id = "";
		TenantMeterTypeDefault tenantMeterTypeDefault = tenantMeterTypeDefaultMapper.selectById(id);
		log.info(tenantMeterTypeDefault.toString());
	}
	
	@Test
	public void selectList() {
		QueryWrapper<TenantMeterTypeDefault> queryWrapper = new QueryWrapper<TenantMeterTypeDefault>();
		List<TenantMeterTypeDefault> tenantMeterTypeDefaultList = tenantMeterTypeDefaultMapper.selectList(queryWrapper);
		tenantMeterTypeDefaultList.forEach(e -> {
			log.info(e.toString());
		});
	}

	@Test
	public void insert() {
		for(int i=0;i<RandomUtil.randomInt(10, 100);i++) {
			TenantMeterTypeDefault tenantMeterTypeDefault = TenantMeterTypeDefault.builder()//
					.meterTypeName(TestCaseUtil.name())// 名称
					.meterTypeData(RandomUtil.randomString(4))// 结构化数据
					.addTime(TestCaseUtil.dateBefore())// 数据新增时间
					.updateTime(TestCaseUtil.dateBefore())// 数据修改时间
					.build();
				
			int count = tenantMeterTypeDefaultMapper.insert(tenantMeterTypeDefault);
			log.info("count={}",count);
			log.info("tenantMeterTypeDefault={}",tenantMeterTypeDefault);
		}
		
	}
	
}
