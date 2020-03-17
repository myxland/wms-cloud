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
import com.zlsrj.wms.api.entity.TenantMeterType;
import com.zlsrj.wms.api.entity.TenantInfo;

import cn.hutool.core.util.RandomUtil;
import lombok.extern.slf4j.Slf4j;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class TenantMeterTypeMapperTest {

	@Resource
	private TenantMeterTypeMapper tenantMeterTypeMapper;
	@Resource
	private TenantInfoMapper tenantInfoMapper;
	
	@Test
	public void selectByIdTest() {
		String id = "";
		TenantMeterType tenantMeterType = tenantMeterTypeMapper.selectById(id);
		log.info(tenantMeterType.toString());
	}
	
	@Test
	public void selectList() {
		QueryWrapper<TenantMeterType> queryWrapper = new QueryWrapper<TenantMeterType>();
		List<TenantMeterType> tenantMeterTypeList = tenantMeterTypeMapper.selectList(queryWrapper);
		tenantMeterTypeList.forEach(e -> {
			log.info(e.toString());
		});
	}

	@Test
	public void insert() {
		List<TenantInfo> tenantInfoList = tenantInfoMapper.selectList(new QueryWrapper<TenantInfo>());
		for(int i=0;i<RandomUtil.randomInt(10, 100);i++) {
			TenantInfo tenantInfo = tenantInfoList.get(RandomUtil.randomInt(tenantInfoList.size()));
			//tenantInfo = TenantInfo.builder().id(1L).build();
			
			TenantMeterType tenantMeterType = TenantMeterType.builder()//
					.id(TestCaseUtil.id())// 水表类型ID
					.tenantId(tenantInfo.getId())// 租户ID
					.meterTypeName(TestCaseUtil.name())// 名称
					.meterTypeData(RandomUtil.randomString(4))// 结构化数据
					.addTime(TestCaseUtil.dateBefore())// 数据新增时间
					.updateTime(TestCaseUtil.dateBefore())// 数据修改时间
					.build();
				
			int count = tenantMeterTypeMapper.insert(tenantMeterType);
			log.info("count={}",count);
			log.info("tenantMeterType={}",tenantMeterType);
		}
		
	}
	
}
