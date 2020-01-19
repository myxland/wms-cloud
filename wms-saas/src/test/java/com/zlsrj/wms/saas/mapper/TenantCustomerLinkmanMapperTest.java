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
import com.zlsrj.wms.api.entity.TenantCustomerLinkman;
import com.zlsrj.wms.api.entity.TenantInfo;
import com.zlsrj.wms.api.entity.TenantCustomer;

import cn.hutool.core.util.RandomUtil;
import lombok.extern.slf4j.Slf4j;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class TenantCustomerLinkmanMapperTest {

	@Resource
	private TenantCustomerLinkmanMapper tenantCustomerLinkmanMapper;
	@Resource
	private TenantInfoMapper tenantInfoMapper;
	@Resource
	private TenantCustomerMapper tenantCustomerMapper;
	
	@Test
	public void selectByIdTest() {
		Long id = 1L;
		TenantCustomerLinkman tenantCustomerLinkman = tenantCustomerLinkmanMapper.selectById(id);
		log.info(tenantCustomerLinkman.toString());
	}
	
	@Test
	public void selectList() {
		QueryWrapper<TenantCustomerLinkman> queryWrapper = new QueryWrapper<TenantCustomerLinkman>();
		List<TenantCustomerLinkman> tenantCustomerLinkmanList = tenantCustomerLinkmanMapper.selectList(queryWrapper);
		tenantCustomerLinkmanList.forEach(e -> {
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
			
			TenantCustomerLinkman tenantCustomerLinkman = TenantCustomerLinkman.builder()//
					.id(TestCaseUtil.id())// 联系人ID
					.tenantId(tenantInfo.getId())// 租户ID
					.customerId(tenantCustomer!=null?tenantCustomer.getId():null)// 客户ID
					.linkmanName(TestCaseUtil.name())// 联系人姓名
					.linkmanAddress(TestCaseUtil.address())// 联系人地址
					.linkmanMainOn(RandomUtil.randomInt(0,0+1))// 主联系人（1：是；0：否）
					.linkmanSex(RandomUtil.randomInt(1,2+1))// 性别（1：男；2：女）
					.linkmanBirthday(TestCaseUtil.dateBefore())// 出生日期
					.linkmanMobile(TestCaseUtil.mobile())// 手机号码
					.linkmanTel(TestCaseUtil.tel())// 固定电话号码
					.linkmanEmail(TestCaseUtil.email(null))// 邮箱地址
					.linkmanPersonalWx(RandomUtil.randomString(4))// 微信号
					.linkmanQq(TestCaseUtil.qq())// QQ号
					.linkmanRemark(RandomUtil.randomString(4))// 备注
					.build();
				
			int count = tenantCustomerLinkmanMapper.insert(tenantCustomerLinkman);
			log.info("count={}",count);
			log.info("tenantCustomerLinkman={}",tenantCustomerLinkman);
		}
		
	}
	
}
