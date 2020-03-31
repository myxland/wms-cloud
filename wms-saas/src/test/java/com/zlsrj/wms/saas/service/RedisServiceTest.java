package com.zlsrj.wms.saas.service;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.alibaba.fastjson.JSON;
import com.zlsrj.wms.api.entity.TenantInfo;

import lombok.extern.slf4j.Slf4j;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class RedisServiceTest {

	@Autowired
	private RedisService<String,Object> redisService;

	@Test
	public void getValueTest() {
		Object n = redisService.getValue("n");
		log.info(n.toString());
	}
	
	@Test
	public void setValueTest() {
		TenantInfo tenantInfo = new TenantInfo();
		tenantInfo.setId("AE6492EB900A4CEAB9C6E2DB3E03C344");
		tenantInfo.setTenantName("武汉市水务集团有限责任公司");
		redisService.setValue(tenantInfo.getId(), JSON.toJSONString(tenantInfo));
		
		Object obj = redisService.getValue(tenantInfo.getId());
		log.info(obj.toString());
	}
	
	@Test
	public void incrementTest() {
		long x = redisService.increment("n");
		log.info("x={}",x);
	}
	
	@Test
	public void hasKeyTest() {
		boolean has = redisService.hasKey("n");
		log.info("x={}",has);
		has = redisService.hasKey("m");
		log.info("x={}",has);
	}
}
