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
import com.zlsrj.wms.api.entity.TenantCustomerInvoice;
import com.zlsrj.wms.api.entity.TenantInfo;

import cn.hutool.core.util.RandomUtil;
import lombok.extern.slf4j.Slf4j;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class TenantCustomerInvoiceMapperTest {

	@Resource
	private TenantCustomerInvoiceMapper tenantCustomerInvoiceMapper;
	@Resource
	private TenantInfoMapper tenantInfoMapper;
	
	@Test
	public void selectByIdTest() {
		String id = "";
		TenantCustomerInvoice tenantCustomerInvoice = tenantCustomerInvoiceMapper.selectById(id);
		log.info(tenantCustomerInvoice.toString());
	}
	
	@Test
	public void selectList() {
		QueryWrapper<TenantCustomerInvoice> queryWrapper = new QueryWrapper<TenantCustomerInvoice>();
		List<TenantCustomerInvoice> tenantCustomerInvoiceList = tenantCustomerInvoiceMapper.selectList(queryWrapper);
		tenantCustomerInvoiceList.forEach(e -> {
			log.info(e.toString());
		});
	}

	@Test
	public void insert() {
		List<TenantInfo> tenantInfoList = tenantInfoMapper.selectList(new QueryWrapper<TenantInfo>());
		for(int i=0;i<RandomUtil.randomInt(10, 100);i++) {
			TenantInfo tenantInfo = tenantInfoList.get(RandomUtil.randomInt(tenantInfoList.size()));
			//tenantInfo = TenantInfo.builder().id(1L).build();
			
			TenantCustomerInvoice tenantCustomerInvoice = TenantCustomerInvoice.builder()//
					.id(TestCaseUtil.id())// 用户开票ID
					.tenantId(tenantInfo.getId())// 租户ID
					.customerId(TestCaseUtil.id())// 用户ID
					.customerCode(RandomUtil.randomString(4))// 用户号
					.invoiceType(RandomUtil.randomInt(1,3+1))// 开票类型（1：增值税普通纸质发票；2：增值税普通电子发票；3：增值税专用纸质发票）
					.invoiceName(TestCaseUtil.name())// 开票名称
					.invoiceTaxNo(RandomUtil.randomString(4))// 开票税号
					.invoiceAddress(TestCaseUtil.address())// 开票地址
					.invoiceTel(TestCaseUtil.tel())// 开票电话
					.invoiceBank(RandomUtil.randomString(4))// 开户银行
					.invoiceAccountNo(TestCaseUtil.bankCardNo(TestCaseUtil.bank()))// 开户账号
					.invoiceMemo(RandomUtil.randomString(4))// 备注
					.addTime(TestCaseUtil.dateBefore())// 数据新增时间
					.updateTime(TestCaseUtil.dateBefore())// 数据修改时间
					.build();
				
			int count = tenantCustomerInvoiceMapper.insert(tenantCustomerInvoice);
			log.info("count={}",count);
			log.info("tenantCustomerInvoice={}",tenantCustomerInvoice);
		}
		
	}
	
}
