package com.zlsrj.wms.tenant.mapper;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zlsrj.wms.common.test.TestCaseUtil;
import com.zlsrj.wms.api.entity.TenantInvoice;

import cn.hutool.core.util.RandomUtil;
import lombok.extern.slf4j.Slf4j;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class TenantInvoiceMapperTest {

	@Resource
	private TenantInvoiceMapper tenantInvoiceMapper;

	@Test
	public void selectByIdTest() {
		Long id = 1L;
		TenantInvoice tenantInvoice = tenantInvoiceMapper.selectById(id);
		log.info(tenantInvoice.toString());
	}
	
	@Test
	public void selectList() {
		QueryWrapper<TenantInvoice> queryWrapper = new QueryWrapper<TenantInvoice>();
		List<TenantInvoice> tenantInvoiceList = tenantInvoiceMapper.selectList(queryWrapper);
		tenantInvoiceList.forEach(e -> {
			log.info(e.toString());
		});
	}

	@Test
	public void insert() {
		TenantInvoice tenantInvoice = TenantInvoice.builder()//
				.id(TestCaseUtil.id())// 租户编号
				.tenantId(RandomUtil.randomLong())// 租户编号
				.creditNumber(RandomUtil.randomString(4))// 税号
				.invoiceAddress(TestCaseUtil.address())// 开票地址
				.bankNo(TestCaseUtil.bankNo())// 开户行行号
				.bankName(TestCaseUtil.bankName())// 开户行名称
				.accountNo(TestCaseUtil.bankCardNo(TestCaseUtil.bank()))// 开户账号
				.build();
				
		int count = tenantInvoiceMapper.insert(tenantInvoice);
		log.info("count={}",count);
		log.info("tenantInvoice={}",tenantInvoice);
	}
	
}
