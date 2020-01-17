package com.zlsrj.wms.saas.mapper;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zlsrj.wms.common.test.TestCaseUtil;
import com.zlsrj.wms.api.entity.TenantInfo;
import com.zlsrj.wms.api.entity.TenantWaterArea;

import cn.hutool.core.util.RandomUtil;
import lombok.extern.slf4j.Slf4j;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class TenantWaterAreaMapperTest {

	@Resource
	private TenantWaterAreaMapper tenantWaterAreaMapper;
	@Resource
	private TenantInfoMapper tenantInfoMapper;

	@Test
	public void selectByIdTest() {
		Long id = 1L;
		TenantWaterArea tenantWaterArea = tenantWaterAreaMapper.selectById(id);
		log.info(tenantWaterArea.toString());
	}
	
	@Test
	public void selectList() {
		QueryWrapper<TenantWaterArea> queryWrapper = new QueryWrapper<TenantWaterArea>();
		List<TenantWaterArea> tenantWaterAreaList = tenantWaterAreaMapper.selectList(queryWrapper);
		tenantWaterAreaList.forEach(e -> {
			log.info(e.toString());
		});
	}

	@Test
	public void insert() {
		List<TenantInfo> tenantInfoList = tenantInfoMapper.selectList(new QueryWrapper<TenantInfo>());
		for(int i=0;i<RandomUtil.randomInt(10, 100);i++) {
			TenantInfo tenantInfo = tenantInfoList.get(RandomUtil.randomInt(tenantInfoList.size()));
			tenantInfo = TenantInfo.builder().id(1L).build();
			
			TenantWaterArea tenantWaterArea = TenantWaterArea.builder()//
					.id(TestCaseUtil.id())// 供水区域ID
					.tenantId(tenantInfo.getId())// 租户ID
					.waterAreaName("供水区域_"+TestCaseUtil.name())// 供水区域名称
					.waterAreaParentId(null)// 上级供水区域ID
					.build();
					
			int count = tenantWaterAreaMapper.insert(tenantWaterArea);
			log.info("count={}",count);
			log.info("tenantWaterArea={}",tenantWaterArea);
		}
		
	}
	
}
