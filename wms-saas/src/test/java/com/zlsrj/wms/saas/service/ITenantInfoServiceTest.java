package com.zlsrj.wms.saas.service;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.ClassUtils;
import com.baomidou.mybatisplus.core.toolkit.ReflectionKit;
import com.zlsrj.wms.api.dto.TenantInfoRechargeParam;
import com.zlsrj.wms.api.entity.TenantInfo;
import com.zlsrj.wms.common.annotation.DictionaryDescription;
import com.zlsrj.wms.common.annotation.DictionaryOrder;
import com.zlsrj.wms.common.annotation.DictionaryText;
import com.zlsrj.wms.common.annotation.DictionaryValue;
import com.zlsrj.wms.common.test.TestCaseUtil;

import cn.hutool.core.util.RandomUtil;
import lombok.extern.slf4j.Slf4j;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class ITenantInfoServiceTest {

	@Autowired
	private ITenantInfoService tenantInfoService;

	@Test
	public void insertTest() {
		for(int i=0;i<2;i++) {
			String companyShortName = TestCaseUtil.companyShortName();

			TenantInfo tenantInfo = TenantInfo.builder()//
					.id(TestCaseUtil.id())// 租户ID
					.tenantName("租户名称"+"-"+"新增用例"+"-"+i+"-"+RandomUtil.randomNumbers(4))// 租户名称
					.tenantBalance(new BigDecimal(0))// 账户余额
					.tenantOverdraw(new BigDecimal(0))// 透支额度
					.tenantDisplayName(companyShortName)// 租户显示名称
					.tenantProvince(TestCaseUtil.province())// 省
					.tenantCity(TestCaseUtil.city())// 市
					.tenantCountyTown(RandomUtil.randomString(4))// 区/县
					.tenantLinkAddress(TestCaseUtil.address())// 联系地址
					.tenantLinkman(TestCaseUtil.name())// 联系人
					.tenantLinkmanMobile(TestCaseUtil.mobile())// 手机号码
					.tenantLinkmanTel(TestCaseUtil.tel())// 单位电话
					.tenantLinkmanEmail(TestCaseUtil.email(null))// 邮箱
					.tenantLinkmanQq(TestCaseUtil.qq())// QQ号码
					.tenantType(RandomUtil.randomInt(0,1+1))// 租户类型（1：使用单位；2：水表厂商；3：代收机构；4：内部运营；5：分销商）
					.tenantRegisterTime(new Date())// 注册时间
					.invoiceType(RandomUtil.randomInt(0,1+1))// 开票类别（1：普通纸制发票；2：普通电子发票；3：专用纸制发票）
					.invoiceName(TestCaseUtil.name())// 开票名称
					.invoiceTaxNo(RandomUtil.randomString(4))// 税号
					.invoiceAddress(TestCaseUtil.address())// 开票地址
					.invoiceTel(TestCaseUtil.tel())// 开票电话
					.invoiceBankCode(RandomUtil.randomString(4))// 开户行行号
					.invoiceBankName(TestCaseUtil.bankName())// 开户行名称
					.invoiceBankAccountNo(TestCaseUtil.bankCardNo(TestCaseUtil.bank()))// 开户账号
					.tenantAccesskey(RandomUtil.randomString(4))// 租户KEY
					.priceStepOn(RandomUtil.randomInt(0,1+1))
					.build();

			log.info(ToStringBuilder.reflectionToString(tenantInfo, ToStringStyle.MULTI_LINE_STYLE));

			boolean success = tenantInfoService.save(tenantInfo);

			log.info(Boolean.toString(success));
		}
	}

	@Test
	public void updateTest() {

		String id = "";

		String companyShortName = TestCaseUtil.companyShortName();

		TenantInfo tenantInfo = TenantInfo.builder()//
				.tenantName(TestCaseUtil.companyName(companyShortName))// 租户名称
				.tenantBalance(new BigDecimal(0))// 账户余额
				.tenantOverdraw(new BigDecimal(0))// 透支额度
				.tenantDisplayName(companyShortName)// 租户显示名称
				.tenantProvince(TestCaseUtil.province())// 省
				.tenantCity(TestCaseUtil.city())// 市
				.tenantCountyTown(RandomUtil.randomString(4))// 区/县
				.tenantLinkAddress(TestCaseUtil.address())// 联系地址
				.tenantLinkman(TestCaseUtil.name())// 联系人
				.tenantLinkmanMobile(TestCaseUtil.mobile())// 手机号码
				.tenantLinkmanTel(TestCaseUtil.tel())// 单位电话
				.tenantLinkmanEmail(TestCaseUtil.email(null))// 邮箱
				.tenantLinkmanQq(TestCaseUtil.qq())// QQ号码
				.tenantType(RandomUtil.randomInt(0,1+1))// 租户类型（1：使用单位；2：水表厂商；3：代收机构；4：内部运营；5：分销商）
				.tenantRegisterTime(new Date())// 注册时间
				.invoiceType(RandomUtil.randomInt(0,1+1))// 开票类别（1：普通纸制发票；2：普通电子发票；3：专用纸制发票）
				.invoiceName(TestCaseUtil.name())// 开票名称
				.invoiceTaxNo(RandomUtil.randomString(4))// 税号
				.invoiceAddress(TestCaseUtil.address())// 开票地址
				.invoiceTel(TestCaseUtil.tel())// 开票电话
				.invoiceBankCode(RandomUtil.randomString(4))// 开户行行号
				.invoiceBankName(TestCaseUtil.bankName())// 开户行名称
				.invoiceBankAccountNo(TestCaseUtil.bankCardNo(TestCaseUtil.bank()))// 开户账号
				.tenantAccesskey(RandomUtil.randomString(4))// 租户KEY
				.build();
		tenantInfo.setId(id);

		log.info(ToStringBuilder.reflectionToString(tenantInfo, ToStringStyle.MULTI_LINE_STYLE));

		boolean success = tenantInfoService.updateById(tenantInfo);

		log.info(Boolean.toString(success));
	}
	
	@Test
	public void dictionaryTest() {
		//List<Field> fieldList = ReflectionKit.getFieldList(ClassUtils.getUserClass(TenantInfo.class));
		
		Map<String, Field> fieldMap = ReflectionKit.getFieldMap(ClassUtils.getUserClass(TenantInfo.class));
		
		//boolean isExistDictionaryValue = fieldList.stream().anyMatch(field -> field.isAnnotationPresent(DictionaryValue.class));
		//log.info("isExistDictionaryValue={}",isExistDictionaryValue);
		
//		Optional<Field> dictionaryValueField = fieldList.stream()//
//				.filter(field -> field.isAnnotationPresent(DictionaryValue.class)).findFirst();
//		
//		dictionaryValueField.get().getName()
		
		QueryWrapper<TenantInfo> queryWrapper = new QueryWrapper<TenantInfo>();
		queryWrapper//
				.lambda()//
				.select(//
						TenantInfo.class, e //
						-> //
						fieldMap.get(e.getProperty()).isAnnotationPresent(DictionaryValue.class)//
								|| fieldMap.get(e.getProperty()).isAnnotationPresent(DictionaryText.class)//
								|| fieldMap.get(e.getProperty()).isAnnotationPresent(DictionaryOrder.class)//
								|| fieldMap.get(e.getProperty()).isAnnotationPresent(DictionaryDescription.class)//
				);
		
		List<TenantInfo> tenantInfoList = tenantInfoService.list(queryWrapper);
		
		tenantInfoList.forEach(e -> {
			//log.info(e.toString());
			log.info(ToStringBuilder.reflectionToString(e, ToStringStyle.MULTI_LINE_STYLE));
		});
		
	}
	
	@Test
	public void getDictionaryById() {
		String id = "cbdc7fc0a26b4027b8d7f3016de4b0e1";
		log.info("id={}",id);
		TenantInfo tenantInfo = this.tenantInfoService.getDictionaryById(id);
		log.info(ToStringBuilder.reflectionToString(tenantInfo, ToStringStyle.MULTI_LINE_STYLE));
	}
	
	@Test
	public void updateRedisTest() {
		String id = "cbdc7fc0a26b4027b8d7f3016de4b0e1";
		log.info("id={}", id);
		TenantInfo entityWhere = TenantInfo.builder()//
				.id(id)//
				.build();
		UpdateWrapper<TenantInfo> updateWrapper = new UpdateWrapper<TenantInfo>();
		updateWrapper.setEntity(entityWhere);
		updateWrapper.lambda()//
				.set(TenantInfo::getTenantName, "租户名称" + "-" + "更新测试" + "-" + RandomUtil.randomNumbers(4));
		this.tenantInfoService.update(updateWrapper);
	}

	@Test
	public void deleteRedisTest() {
		String id = "48e60355794d4572b73ca6274aabe1ed";
		log.info("id={}", id);
		this.tenantInfoService.removeById(id);
	}
	
	@Test
	public void rechargeTest() {
		
		String id = "5776fb77e26e45209d56e39f9ceb7308";
		
		TenantInfoRechargeParam tenantInfoRechargeParam = new TenantInfoRechargeParam();
		tenantInfoRechargeParam.setId(id);
		tenantInfoRechargeParam.setRechargeMoney(new BigDecimal(RandomUtil.randomInt(1, 10)));
		
		this.tenantInfoService.recharge(tenantInfoRechargeParam);
	}
}
