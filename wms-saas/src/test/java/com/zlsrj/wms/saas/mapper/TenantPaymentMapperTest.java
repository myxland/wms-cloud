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
import com.zlsrj.wms.api.entity.TenantPayment;
import com.zlsrj.wms.api.entity.TenantInfo;
import com.zlsrj.wms.api.entity.TenantCustomer;
import com.zlsrj.wms.api.entity.TenantDepartment;

import cn.hutool.core.util.RandomUtil;
import lombok.extern.slf4j.Slf4j;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class TenantPaymentMapperTest {

	@Resource
	private TenantPaymentMapper tenantPaymentMapper;
	@Resource
	private TenantInfoMapper tenantInfoMapper;
	@Resource
	private TenantCustomerMapper tenantCustomerMapper;
	@Resource
	private TenantDepartmentMapper tenantDepartmentMapper;
	
	@Test
	public void selectByIdTest() {
		Long id = 1L;
		TenantPayment tenantPayment = tenantPaymentMapper.selectById(id);
		log.info(tenantPayment.toString());
	}
	
	@Test
	public void selectList() {
		QueryWrapper<TenantPayment> queryWrapper = new QueryWrapper<TenantPayment>();
		List<TenantPayment> tenantPaymentList = tenantPaymentMapper.selectList(queryWrapper);
		tenantPaymentList.forEach(e -> {
			log.info(e.toString());
		});
	}

	@Test
	public void insert() {
		List<TenantInfo> tenantInfoList = tenantInfoMapper.selectList(new QueryWrapper<TenantInfo>());
		for(int i=0;i<RandomUtil.randomInt(10, 100);i++) {
			TenantInfo tenantInfo = tenantInfoList.get(RandomUtil.randomInt(tenantInfoList.size()));
			tenantInfo = TenantInfo.builder().id(1L).build();
			
			TenantCustomer tenantCustomer = null;
			List<TenantCustomer> tenantCustomerList = tenantCustomerMapper.selectList(new QueryWrapper<TenantCustomer>().lambda().eq(TenantCustomer::getTenantId, tenantInfo.getId()));
			if(tenantCustomerList!=null && tenantCustomerList.size()>0) {
				tenantCustomer = tenantCustomerList.get(RandomUtil.randomInt(tenantCustomerList.size()));
			}
			
			TenantDepartment tenantDepartment = null;
			List<TenantDepartment> tenantDepartmentList = tenantDepartmentMapper.selectList(new QueryWrapper<TenantDepartment>().lambda().eq(TenantDepartment::getTenantId, tenantInfo.getId()));
			if(tenantDepartmentList!=null && tenantDepartmentList.size()>0) {
				tenantDepartment = tenantDepartmentList.get(RandomUtil.randomInt(tenantDepartmentList.size()));
			}
			
			TenantPayment tenantPayment = TenantPayment.builder()//
					.id(TestCaseUtil.id())// 实收账ID
					.tenantId(tenantInfo.getId())// 租户ID
					.outTransno(RandomUtil.randomString(4))// 内部生成的订单号
					.inTransno(RandomUtil.randomString(4))// 外部如微信支付宝传入的订单号
					.payTime(new Date())// 付款时间
					.paymentStatus(RandomUtil.randomInt(1,3+1))// 实收账状态（1：正常；2：被退款；3：退款记录）
					.customerId(tenantCustomer!=null?tenantCustomer.getId():null)// 用户ID
					.chargeDepartmentId(tenantDepartment!=null?tenantDepartment.getId():null)// 收款部门ID
					.chargeEmployeeId(RandomUtil.randomLong())// 收费员ID
					.payChannels(RandomUtil.randomInt(1,5+1))// 付款途径（1：柜台；2：银行；3：线上；4：走收；5：系统处理）
					.payMethod(RandomUtil.randomInt(0,13+1))// 付款方式（0：预存抵扣；1：现金；2：支票；3：刷卡；4：电汇；5：代扣；6：托收；7：微信生活缴费；8：支付宝生活缴费；9：微信公众号；10：微信扫码[用户被扫]；11：支付宝扫码[用户被扫]；12：微信扫码[用户主扫]；13：支付宝扫码[用户主扫]）
					.customerBalanceMoneyBefore(TestCaseUtil.money())// 用户上期预存余额
					.customerPayMoney(TestCaseUtil.money())// 用户付款金额
					.customerBalanceMoneyHappen(TestCaseUtil.money())// 用户预存发生值
					.payTheArrearsMoney(TestCaseUtil.money())// 所缴欠费金额
					.payTheLateFeeMoney(TestCaseUtil.money())// 所缴违约金金额
					.customerBalanceMoneyAfter(TestCaseUtil.money())// 用户本期预存余额
					.build();
				
			int count = tenantPaymentMapper.insert(tenantPayment);
			log.info("count={}",count);
			log.info("tenantPayment={}",tenantPayment);
		}
		
	}
	
}
