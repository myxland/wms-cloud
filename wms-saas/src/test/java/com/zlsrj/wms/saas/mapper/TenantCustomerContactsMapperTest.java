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
import com.zlsrj.wms.api.entity.TenantCustomerContacts;
import com.zlsrj.wms.api.entity.TenantInfo;

import cn.hutool.core.util.RandomUtil;
import lombok.extern.slf4j.Slf4j;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class TenantCustomerContactsMapperTest {

	@Resource
	private TenantCustomerContactsMapper tenantCustomerContactsMapper;
	@Resource
	private TenantInfoMapper tenantInfoMapper;
	
	@Test
	public void selectByIdTest() {
		String id = "";
		TenantCustomerContacts tenantCustomerContacts = tenantCustomerContactsMapper.selectById(id);
		log.info(tenantCustomerContacts.toString());
	}
	
	@Test
	public void selectList() {
		QueryWrapper<TenantCustomerContacts> queryWrapper = new QueryWrapper<TenantCustomerContacts>();
		List<TenantCustomerContacts> tenantCustomerContactsList = tenantCustomerContactsMapper.selectList(queryWrapper);
		tenantCustomerContactsList.forEach(e -> {
			log.info(e.toString());
		});
	}

	@Test
	public void insert() {
		List<TenantInfo> tenantInfoList = tenantInfoMapper.selectList(new QueryWrapper<TenantInfo>());
		for(int i=0;i<RandomUtil.randomInt(10, 100);i++) {
			TenantInfo tenantInfo = tenantInfoList.get(RandomUtil.randomInt(tenantInfoList.size()));
			//tenantInfo = TenantInfo.builder().id(1L).build();
			
			TenantCustomerContacts tenantCustomerContacts = TenantCustomerContacts.builder()//
					.id(TestCaseUtil.id())// 用户联系ID
					.tenantId(tenantInfo.getId())// 租户ID
					.customerId(TestCaseUtil.id())// 用户ID
					.customerCode(RandomUtil.randomString(4))// 用户号
					.contactsName(TestCaseUtil.name())// 联系人姓名
					.contactsAddress(TestCaseUtil.address())// 联系人地址
					.contactsMain(RandomUtil.randomInt(0,1000+1))// 主联系人（1：是；0：否）
					.contactsSex(RandomUtil.randomInt(1,0+1))// 性别（1：男；0：女）
					.contactsBirthday(TestCaseUtil.dateBefore())// 出生日期
					.contactsMobile(TestCaseUtil.mobile())// 手机号码
					.contactsTel(TestCaseUtil.tel())// 固定电话
					.contactsEmail(TestCaseUtil.email(null))// 邮箱地址
					.contactsWx(RandomUtil.randomString(4))// 微信号
					.contactsQq(TestCaseUtil.qq())// QQ号
					.contactsMemo(RandomUtil.randomString(4))// 备注
					.addTime(TestCaseUtil.dateBefore())// 数据新增时间
					.updateTime(TestCaseUtil.dateBefore())// 数据修改时间
					.build();
				
			int count = tenantCustomerContactsMapper.insert(tenantCustomerContacts);
			log.info("count={}",count);
			log.info("tenantCustomerContacts={}",tenantCustomerContacts);
		}
		
	}
	
}
