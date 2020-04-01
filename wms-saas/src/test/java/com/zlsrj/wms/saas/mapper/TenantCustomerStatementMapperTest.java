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
import com.zlsrj.wms.api.entity.TenantCustomerStatement;
import com.zlsrj.wms.api.entity.TenantInfo;

import cn.hutool.core.util.RandomUtil;
import lombok.extern.slf4j.Slf4j;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class TenantCustomerStatementMapperTest {

	@Resource
	private TenantCustomerStatementMapper tenantCustomerStatementMapper;
	@Resource
	private TenantInfoMapper tenantInfoMapper;
	
	@Test
	public void selectByIdTest() {
		String id = "";
		TenantCustomerStatement tenantCustomerStatement = tenantCustomerStatementMapper.selectById(id);
		log.info(tenantCustomerStatement.toString());
	}
	
	@Test
	public void selectList() {
		QueryWrapper<TenantCustomerStatement> queryWrapper = new QueryWrapper<TenantCustomerStatement>();
		List<TenantCustomerStatement> tenantCustomerStatementList = tenantCustomerStatementMapper.selectList(queryWrapper);
		tenantCustomerStatementList.forEach(e -> {
			log.info(e.toString());
		});
	}

	@Test
	public void insert() {
		List<TenantInfo> tenantInfoList = tenantInfoMapper.selectList(new QueryWrapper<TenantInfo>());
		for(int i=0;i<RandomUtil.randomInt(10, 100);i++) {
			TenantInfo tenantInfo = tenantInfoList.get(RandomUtil.randomInt(tenantInfoList.size()));
			//tenantInfo = TenantInfo.builder().id(1L).build();
			
			TenantCustomerStatement tenantCustomerStatement = TenantCustomerStatement.builder()//
					.id(TestCaseUtil.id())// 用户结算ID
					.tenantId(tenantInfo.getId())// 租户ID
					.customerId(TestCaseUtil.id())// 用户ID
					.customerCode(RandomUtil.randomString(4))// 用户号
					.statementMethod(RandomUtil.randomInt(1,4+1))// 结算方式（1：坐收；2：托收；3：代扣；4：走收）
					.statementBankId(TestCaseUtil.id())// 付款银行
					.entrustAgreementNo(RandomUtil.randomString(4))// 委托授权号
					.entrustCode(RandomUtil.randomString(4))// 托收号
					.statementAccountBankId(new Long(RandomUtil.randomInt(1000000,10000000)))// 开户银行
					.statementAccountName(TestCaseUtil.name())// 开户名称
					.statementAccountNo(TestCaseUtil.bankCardNo(TestCaseUtil.bank()))// 开户账号
					.statementRegisterDate(TestCaseUtil.dateBefore())// 签约日期
					.statementMemo(RandomUtil.randomString(4))// 备注
					.addTime(TestCaseUtil.dateBefore())// 数据新增时间
					.updateTime(TestCaseUtil.dateBefore())// 数据修改时间
					.build();
				
			int count = tenantCustomerStatementMapper.insert(tenantCustomerStatement);
			log.info("count={}",count);
			log.info("tenantCustomerStatement={}",tenantCustomerStatement);
		}
		
	}
	
}
