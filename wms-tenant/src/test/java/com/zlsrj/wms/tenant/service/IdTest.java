package com.zlsrj.wms.tenant.service;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import lombok.extern.slf4j.Slf4j;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class IdTest {
	@Resource
	private IIdService idServce;
	
	@Test
	public void idBatchTest() {
		for(int i=0;i<100;i++) {
			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			log.info("id={}",idServce.selectId());
		}
		
	}
}
