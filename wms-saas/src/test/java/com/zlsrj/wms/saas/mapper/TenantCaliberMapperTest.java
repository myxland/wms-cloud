package com.zlsrj.wms.saas.mapper;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zlsrj.wms.common.test.TestCaseUtil;
import com.zlsrj.wms.api.entity.TenantCaliber;
import com.zlsrj.wms.api.entity.TenantInfo;

import cn.hutool.core.util.RandomUtil;
import lombok.extern.slf4j.Slf4j;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class TenantCaliberMapperTest {

	@Resource
	private TenantCaliberMapper tenantCaliberMapper;
	@Resource
	private TenantInfoMapper tenantInfoMapper;

	@Test
	public void selectByIdTest() {
		Long id = 1L;
		TenantCaliber tenantCaliber = tenantCaliberMapper.selectById(id);
		log.info(tenantCaliber.toString());
	}
	
	@Test
	public void selectList() {
		QueryWrapper<TenantCaliber> queryWrapper = new QueryWrapper<TenantCaliber>();
		List<TenantCaliber> tenantCaliberList = tenantCaliberMapper.selectList(queryWrapper);
		tenantCaliberList.forEach(e -> {
			log.info(e.toString());
		});
	}

	@Test
	public void insert() {
		List<TenantInfo> tenantInfoList = tenantInfoMapper.selectList(new QueryWrapper<TenantInfo>());
		for(int i=0;i<RandomUtil.randomInt(10, 100);i++) {
			TenantInfo tenantInfo = tenantInfoList.get(RandomUtil.randomInt(tenantInfoList.size()));
			tenantInfo = TenantInfo.builder().id(1L).build();
			
			TenantCaliber tenantCaliber = TenantCaliber.builder()//
					.id(TestCaseUtil.id())// 口径ID
					.tenantId(tenantInfo.getId())// 租户ID
					.caliberName("DN"+RandomUtil.randomInt(1, 20)*10)// 口径名称
					.build();
					
			int count = tenantCaliberMapper.insert(tenantCaliber);
			log.info("count={}",count);
			log.info("tenantCaliber={}",tenantCaliber);
		}
		
	}
	
}
