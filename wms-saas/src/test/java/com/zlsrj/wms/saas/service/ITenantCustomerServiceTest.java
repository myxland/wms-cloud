package com.zlsrj.wms.saas.service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zlsrj.wms.api.dto.TenantCustomerAddParam;
import com.zlsrj.wms.api.entity.TenantCustomer;
import com.zlsrj.wms.api.entity.TenantCustomerType;
import com.zlsrj.wms.api.entity.TenantInfo;
import com.zlsrj.wms.common.test.TestCaseUtil;
import com.zlsrj.wms.saas.mapper.TenantCustomerTypeMapper;
import com.zlsrj.wms.saas.mapper.TenantInfoMapper;

import cn.hutool.core.util.RandomUtil;
import lombok.extern.slf4j.Slf4j;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class ITenantCustomerServiceTest {

	@Autowired
	private ITenantCustomerService tenantCustomerService;
	
	@Autowired
	private TenantInfoMapper tenantInfoMapper;
	
	@Autowired
	private TenantCustomerTypeMapper tenantCustomerTypeMapper;

	@Test
	public void insertTest() {
		
		QueryWrapper<TenantInfo> queryWrapperTenantInfo = new QueryWrapper<TenantInfo>();
		queryWrapperTenantInfo.lambda()//
				.eq(TenantInfo::getTenantType, 1)// 租户类型（1：使用单位；2：水表厂商；3：代收机构；4：内部运营；5：分销商）
		;

		List<TenantInfo> tenantInfoList = tenantInfoMapper.selectList(queryWrapperTenantInfo);
		TenantInfo tenantInfo = tenantInfoList.get(RandomUtil.randomInt(tenantInfoList.size()));
		
		String tenantId = tenantInfo.getId();
		
		QueryWrapper<TenantCustomerType> queryWrapperTenantCustomerType = new QueryWrapper<TenantCustomerType>();
		queryWrapperTenantCustomerType.lambda()//
				.eq(TenantCustomerType::getTenantId, tenantId)
		;

		List<TenantCustomerType> tenantCustomerTypeList = tenantCustomerTypeMapper.selectList(queryWrapperTenantCustomerType);
		TenantCustomerType tenantCustomerType = tenantCustomerTypeList.get(RandomUtil.randomInt(tenantCustomerTypeList.size()));
		
		String customerTypeId = tenantCustomerType.getId();
		
		TenantCustomerAddParam tenantCustomerAddParam = new TenantCustomerAddParam();
		tenantCustomerAddParam.setTenantId(tenantId);// 租户ID
		//tenantCustomerAddParam.setCustomerCode(RandomUtil.randomString(4));// 用户号 系统生成
		tenantCustomerAddParam.setCustomerName(TestCaseUtil.name());// 用户名称
		tenantCustomerAddParam.setCustomerAddress(TestCaseUtil.address());// 用户地址
		tenantCustomerAddParam.setCustomerStatus(RandomUtil.randomInt(1,3+1));// 用户状态（1：正常；2：暂停；3：销户）
		tenantCustomerAddParam.setCustomerType(customerTypeId);// 用户类别
		tenantCustomerAddParam.setCustomerRegisterTime(TestCaseUtil.dateBefore());// 建档时间
		tenantCustomerAddParam.setCustomerRegisterDate(TestCaseUtil.dateBefore());// 立户日期
		tenantCustomerAddParam.setCustomerCreditRating(RandomUtil.randomInt(0,1000+1));// 信用等级
		tenantCustomerAddParam.setCustomerRatingDate(TestCaseUtil.dateBefore());// 最近评估日期
		tenantCustomerAddParam.setCustomerBalanceAmt(new BigDecimal(RandomUtil.randomDouble(100)));// 预存余额
		tenantCustomerAddParam.setCustomerFreezeBalance(new BigDecimal(RandomUtil.randomDouble(100)));// 预存冻结金额
		tenantCustomerAddParam.setCustomerOweAmt(new BigDecimal(RandomUtil.randomDouble(100)));// 欠费金额
		tenantCustomerAddParam.setCustomerPenaltyAmt(new BigDecimal(RandomUtil.randomDouble(100)));// 违约金
		tenantCustomerAddParam.setCustomerMemo(RandomUtil.randomString(10));// 用户备注

		log.info(ToStringBuilder.reflectionToString(tenantCustomerAddParam, ToStringStyle.MULTI_LINE_STYLE));

		String id = tenantCustomerService.save(tenantCustomerAddParam);

		log.info("id={}",id);
	}

	@Test
	public void updateTest() {

		String id = "";

		TenantCustomer tenantCustomer = TenantCustomer.builder()//
				.tenantId(RandomUtil.randomString(4))// 租户ID
				.customerCode(RandomUtil.randomString(4))// 用户号
				.customerName(TestCaseUtil.name())// 用户名称
				.customerAddress(TestCaseUtil.address())// 用户地址
				.customerStatus(RandomUtil.randomInt(0,1+1))// 用户状态（1：正常；2：暂停；3：销户）
				.customerType(RandomUtil.randomString(4))// 用户类别
				.customerRegisterTime(new Date())// 建档时间
				.customerRegisterDate(new Date())// 立户日期
				.customerCreditRating(RandomUtil.randomInt(0,1000+1))// 信用等级
				.customerRatingDate(new Date())// 最近评估日期
				.customerBalanceAmt(new BigDecimal(0))// 预存余额
				.customerFreezeBalance(new BigDecimal(0))// 预存冻结金额
				.customerOweAmt(new BigDecimal(0))// 欠费金额
				.customerPenaltyAmt(new BigDecimal(0))// 违约金
				.customerMemo(RandomUtil.randomString(4))// 用户备注
				.addTime(new Date())// 数据新增时间
				.updateTime(new Date())// 数据修改时间
				.build();
		tenantCustomer.setId(id);

		log.info(ToStringBuilder.reflectionToString(tenantCustomer, ToStringStyle.MULTI_LINE_STYLE));

		boolean success = tenantCustomerService.updateById(tenantCustomer);

		log.info(Boolean.toString(success));
	}
}
