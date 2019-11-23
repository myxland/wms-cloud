package com.zlsrj.wms.cust.mapper;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zlsrj.wms.common.test.TestCaseUtil;
import com.zlsrj.wms.api.entity.CustContact;

import cn.hutool.core.util.RandomUtil;
import lombok.extern.slf4j.Slf4j;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class CustContactMapperTest {

	@Resource
	private CustContactMapper custContactMapper;

	@Test
	public void selectByIdTest() {
		Long id = 1L;
		CustContact custContact = custContactMapper.selectById(id);
		log.info(custContact.toString());
	}
	
	@Test
	public void selectList() {
		QueryWrapper<CustContact> queryWrapper = new QueryWrapper<CustContact>();
		List<CustContact> custContactList = custContactMapper.selectList(queryWrapper);
		custContactList.forEach(e -> {
			log.info(e.toString());
		});
	}

	@Test
	public void insert() {
		for(int i=0;i<100;i++) {
			CustContact custContact = CustContact.builder()//
					.id(TestCaseUtil.id())// 系统编号
					.tenantId(RandomUtil.randomLong())// 租户编号
					.custId(RandomUtil.randomString(4))// 用户编号
					.contactName(TestCaseUtil.name())// 联系人姓名
					.mainOn(RandomUtil.randomInt(0,1+1))// 主联系人（1：是；0：否）
					.gender(RandomUtil.randomInt(1,2+1))// 性别（1：男；2：女）
					.birthday(TestCaseUtil.dateBefore())// 
					.mobile(TestCaseUtil.mobile())// 手机号码
					.tel(TestCaseUtil.tel())// 固定电话号码
					.email(TestCaseUtil.email(null))// 邮箱地址
					.personalWx(TestCaseUtil.wx())// 微信号
					.qq(TestCaseUtil.qq())// QQ号
					.remark(RandomUtil.randomString(4))// 备注
					.build();
					
			int count = custContactMapper.insert(custContact);
			log.info("count={}",count);
			log.info("custContact={}",custContact);
		}
		
	}
	
}
