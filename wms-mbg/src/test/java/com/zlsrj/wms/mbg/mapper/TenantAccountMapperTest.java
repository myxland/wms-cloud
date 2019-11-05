package com.zlsrj.wms.mbg.mapper;

import java.math.BigDecimal;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zlsrj.wms.common.test.TestCaseUtil;
import com.zlsrj.wms.mbg.entity.TenantAccount;

import cn.hutool.core.util.RandomUtil;
import lombok.extern.slf4j.Slf4j;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class TenantAccountMapperTest {

	@Resource
	private TenantAccountMapper tenantAccountMapper;

	@Test
	public void selectByIdTest() {
		Long id = 1L;
		TenantAccount tenantAccount = tenantAccountMapper.selectById(id);
		log.info(tenantAccount.toString());
	}
	
	@Test
	public void selectList() {
		QueryWrapper<TenantAccount> queryWrapper = new QueryWrapper<TenantAccount>();
		List<TenantAccount> tenantAccountList = tenantAccountMapper.selectList(queryWrapper);
		tenantAccountList.forEach(e -> {
			log.info(e.toString());
		});
	}

	@Test
	public void insert() {
		TenantAccount tenantAccount = TenantAccount.builder()//
				.id(TestCaseUtil.id())// 编号ID
				.tenantId(RandomUtil.randomLong())// 租房编号
				.accountBalance(new BigDecimal(0))// 账户余额
				.build();
				
		int count = tenantAccountMapper.insert(tenantAccount);
		log.info("count={}",count);
		log.info("tenantAccount={}",tenantAccount);
	}
	
}
