package com.zlsrj.wms.saas.mapper;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zlsrj.wms.common.test.TestCaseUtil;
import com.zlsrj.wms.api.entity.TenantCustomer;
import com.zlsrj.wms.api.entity.TenantInfo;
import com.zlsrj.wms.api.entity.TenantCustomerType;

import cn.hutool.core.util.RandomUtil;
import lombok.extern.slf4j.Slf4j;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class TenantCustomerMapperTest {

	@Resource
	private TenantCustomerMapper tenantCustomerMapper;
	@Resource
	private TenantInfoMapper tenantInfoMapper;
	@Resource
	private TenantCustomerTypeMapper tenantCustomerTypeMapper;

	@Test
	public void selectByIdTest() {
		Long id = 1L;
		TenantCustomer tenantCustomer = tenantCustomerMapper.selectById(id);
		log.info(tenantCustomer.toString());
	}
	
	@Test
	public void selectList() {
		QueryWrapper<TenantCustomer> queryWrapper = new QueryWrapper<TenantCustomer>();
		List<TenantCustomer> tenantCustomerList = tenantCustomerMapper.selectList(queryWrapper);
		tenantCustomerList.forEach(e -> {
			log.info(e.toString());
		});
	}

	@Test
	public void insert() {
		List<TenantInfo> tenantInfoList = tenantInfoMapper.selectList(new QueryWrapper<TenantInfo>());
		for(int i=0;i<RandomUtil.randomInt(10, 100);i++) {
			TenantInfo tenantInfo = tenantInfoList.get(RandomUtil.randomInt(tenantInfoList.size()));
			//tenantInfo = TenantInfo.builder().id(1L).build();
			
			TenantCustomerType tenantCustomerType = null;
			List<TenantCustomerType> tenantCustomerTypeList = tenantCustomerTypeMapper.selectList(new QueryWrapper<TenantCustomerType>().lambda().eq(TenantCustomerType::getTenantId, tenantInfo.getId()));
			if(tenantCustomerTypeList!=null && tenantCustomerTypeList.size()>0) {
				tenantCustomerType = tenantCustomerTypeList.get(RandomUtil.randomInt(tenantCustomerTypeList.size()));
			}
		
			TenantCustomer tenantCustomer = TenantCustomer.builder()//
					.id(TestCaseUtil.id())// 
					.tenantId(tenantInfo.getId())// 租户ID
					.customerCode(RandomUtil.randomString(4))// 用户代码
					.customerName(TestCaseUtil.name())// 用户名称
					.customerAddress(TestCaseUtil.address())// 用户地址
					.customerTypeId(tenantCustomerType!=null?tenantCustomerType.getId():null)// 用户类别ID
					.customerRegisterTime(TestCaseUtil.dateBefore())// 建档时间
					.customerStatus(RandomUtil.randomInt(1,3+1))// 用户状态（1：正常；2：暂停；3：消户）
					.customerPaymentMethod(RandomUtil.randomInt(1,4+1))// 用户缴费方式（1：坐收；2：走收；3：代扣；4：托收）
					.invoiceType(RandomUtil.randomInt(1,3+1))// 开票类别（1：普通纸制发票；2：普通电子发票；3：专用纸制发票）
					.invoiceName(TestCaseUtil.name())// 开票名称
					.invoiceTaxNo(RandomUtil.randomString(4))// 税号
					.invoiceAddress(TestCaseUtil.address())// 开票地址
					.invoiceTel(TestCaseUtil.tel())// 开票电话
					.invoiceBankCode(RandomUtil.randomString(4))// 开户行行号
					.invoiceBankName(TestCaseUtil.bankName())// 开户行名称
					.invoiceBankAccountNo(TestCaseUtil.bankCardNo(TestCaseUtil.bank()))// 开户行账号
					.customerBalanceMoney(new BigDecimal(0))// 用户预存余额
					.customerArrearsMoney(new BigDecimal(0))// 用户欠费金额
					.build();
					
			int count = tenantCustomerMapper.insert(tenantCustomer);
			log.info("count={}",count);
			log.info("tenantCustomer={}",tenantCustomer);
		}
		
	}
	
}
