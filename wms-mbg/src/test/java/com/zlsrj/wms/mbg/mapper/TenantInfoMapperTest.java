package com.zlsrj.wms.mbg.mapper;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.zlsrj.wms.mbg.entity.TenantInfo;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.javafaker.Faker;

import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.RandomUtil;
import lombok.extern.slf4j.Slf4j;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class TenantInfoMapperTest {

	@Resource
	private TenantInfoMapper tenantInfoMapper;

	@Test
	public void selectByIdTest() {
		TenantInfo tenantInfo = tenantInfoMapper.selectById(10L);
		log.info(tenantInfo.toString());
	}
	
	@Test
	public void selectList() {
		QueryWrapper<TenantInfo> queryWrapper = new QueryWrapper<TenantInfo>();
		List<TenantInfo> tenantInfoList = tenantInfoMapper.selectList(queryWrapper);
		tenantInfoList.forEach(e -> {
			log.info(e.toString());
		});
	}

	@Test
	public void insert() {
		TenantInfo tenantInfo = TenantInfo.builder()//
				.id(IdUtil.createSnowflake(1L, 1L).nextId())//租户编号
				.tenantName(RandomUtil.randomString(4))//租户名称 tenant_name varchar(1000) tenantName STRING 租户名称 String TenantName
				.displayName(RandomUtil.randomString(4))//显示名称 display_name varchar(1000) displayName STRING 显示名称 String DisplayName
				.tenantProvice(RandomUtil.randomString(4))//省 tenant_provice varchar(100) tenantProvice STRING 省 String TenantProvice
				.tenantCity(RandomUtil.randomString(4))//市 tenant_city varchar(100) tenantCity STRING 市 String TenantCity
				.tenantArea(RandomUtil.randomString(4))//区县 tenant_area varchar(100) tenantArea STRING 区县 String TenantArea
				.tenantAddress(RandomUtil.randomString(4))//联系地址 tenant_address varchar(1000) tenantAddress STRING 联系地址 String TenantAddress
				.tenantLinkman(RandomUtil.randomString(4))//联系人 tenant_linkman varchar(100) tenantLinkman STRING 联系人 String TenantLinkman
				.tenantMobile(RandomUtil.randomString(4))//手机号码 tenant_mobile varchar(100) tenantMobile STRING 手机号码 String TenantMobile
				.tenantTel(RandomUtil.randomString(4))//单位电话 tenant_tel varchar(100) tenantTel STRING 单位电话 String TenantTel
				.tenantEmail(RandomUtil.randomString(4))//邮箱 tenant_email varchar(100) tenantEmail STRING 邮箱 String TenantEmail
				.tenantQq(RandomUtil.randomString(4))//QQ号码 tenant_qq varchar(100) tenantQq STRING QQ号码 String TenantQq
				.tenantType(RandomUtil.randomInt(10))//租户类型（使用单位/供应单位/内部运营)
				.tenantStatus(RandomUtil.randomInt(10))//租户状态（正式/试用）
				.regTime(new Date())//注册时间
				.endDate(new Date())//到期日期
				.creditNumber(RandomUtil.randomString(4))//税号 credit_number varchar(100) creditNumber STRING 税号 String CreditNumber
				.invoiceAddress(RandomUtil.randomString(4))//开票地址 invoice_address varchar(1000) invoiceAddress STRING 开票地址 String InvoiceAddress
				.bankNo(RandomUtil.randomString(4))//开户行行号 bank_no varchar(100) bankNo STRING 开户行行号 String BankNo
				.bankName(RandomUtil.randomString(4))//开户行名称 bank_name varchar(100) bankName STRING 开户行名称 String BankName
				.accountNo(RandomUtil.randomString(4))//开户账号 account_no varchar(20) accountNo STRING 开户账号 String AccountNo
				.partChargeOn(RandomUtil.randomString(4))//是否启用部分缴费（启用/不启用） part_charge_on varchar(20) partChargeOn STRING 是否启用部分缴费（启用/不启用） String PartChargeOn
				.overDuefineOn(RandomUtil.randomString(4))//是否启用违约金（启用/不启用） over_duefine_on varchar(20) overDuefineOn STRING 是否启用违约金（启用/不启用） String OverDuefineOn
				.overDuefineDay(RandomUtil.randomInt(10))//违约金宽限天数（从计费之日开始）
				.overDuefineRatio(new BigDecimal(0))//违约金每天收取比例 over_duefine_ratio decimal(16,4) overDuefineRatio BIG_DECIMAL 违约金每天收取比例 BigDecimal OverDuefineRatio
				.overDuefineTopRatio(new BigDecimal(0))//违约金封顶比例（与欠费金额相比） over_duefine_top_ratio decimal(16,4) overDuefineTopRatio BIG_DECIMAL 违约金封顶比例（与欠费金额相比） BigDecimal OverDuefineTopRatio
				.ycdkType(RandomUtil.randomInt(10))//预存抵扣方式（抄表后即时抵扣/人工发起抵扣）
				.build();
		
		int count = tenantInfoMapper.insert(tenantInfo);
		log.info("count={}",count);
		log.info("tenantInfo={}",tenantInfo);
	}
	
}
