package com.zlsrj.wms.saas.mapper;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zlsrj.wms.api.entity.TenantInfo;
import com.zlsrj.wms.api.entity.TenantManufactor;
import com.zlsrj.wms.common.test.TestCaseUtil;

import cn.hutool.core.util.RandomUtil;
import lombok.extern.slf4j.Slf4j;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class TenantManufactorMapperTest {

	@Resource
	private TenantManufactorMapper tenantManufactorMapper;
	@Resource
	private TenantInfoMapper tenantInfoMapper;

	@Test
	public void selectByIdTest() {
		Long id = 1L;
		TenantManufactor tenantManufactor = tenantManufactorMapper.selectById(id);
		log.info(tenantManufactor.toString());
	}
	
	@Test
	public void selectList() {
		QueryWrapper<TenantManufactor> queryWrapper = new QueryWrapper<TenantManufactor>();
		List<TenantManufactor> tenantManufactorList = tenantManufactorMapper.selectList(queryWrapper);
		tenantManufactorList.forEach(e -> {
			log.info(e.toString());
		});
	}

	@Test
	public void insert() {
		List<TenantInfo> tenantInfoList = tenantInfoMapper.selectList(new QueryWrapper<TenantInfo>());
		for(int i=0;i<RandomUtil.randomInt(10, 100);i++) {
			
			TenantInfo tenantInfo = tenantInfoList.get(RandomUtil.randomInt(tenantInfoList.size()));
			
			TenantManufactor tenantManufactor = TenantManufactor.builder()//
					.id(TestCaseUtil.id())// 制造商ID
					.tenantId(tenantInfo.getId())// 租户ID
					.manufactorName(TestCaseUtil.companyName())// 制造商名称
					.manufactorApikey(RandomUtil.randomString(4))// 远传表接入APIKEY
					.build();
					
			int count = tenantManufactorMapper.insert(tenantManufactor);
			log.info("count={}",count);
			log.info("tenantManufactor={}",tenantManufactor);
		}
		
	}
	
}
