package com.zlsrj.wms.tenant.mapper;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zlsrj.wms.common.test.TestCaseUtil;
import com.zlsrj.wms.api.entity.TenantInfo;

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
		Long id = 1L;
		TenantInfo tenantInfo = tenantInfoMapper.selectById(id);
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
		for (int i = 0; i < 100; i++) {
			String companyShortName = TestCaseUtil.companyShortName();

			TenantInfo tenantInfo = TenantInfo.builder()//
					.id(TestCaseUtil.id())// 租户编号
					.tenantName(TestCaseUtil.companyName(companyShortName))// 租户名称
					.displayName(companyShortName)// 显示名称
					.tenantProvince(TestCaseUtil.province())// 省
					.tenantCity(TestCaseUtil.city())// 市
					.tenantArea(TestCaseUtil.area())// 区县
					.tenantAddress(TestCaseUtil.address())// 联系地址
					.tenantContact(TestCaseUtil.name())// 联系人
					.tenantMobile(TestCaseUtil.mobile())// 手机号码
					.tenantTel(TestCaseUtil.tel())// 单位电话
					.tenantEmail(TestCaseUtil.email(null))// 邮箱
					.tenantQq(TestCaseUtil.qq())// QQ号码
					.tenantType(RandomUtil.randomInt(1,3+1))// 租户类型（1：使用单位；2：供应单位；3：内部运营）
					.tenantStatus(RandomUtil.randomInt(1,2+1))// 租户状态（1：正式；2：试用）
					.regTime(TestCaseUtil.dateBefore())// 注册时间
					.endDate(TestCaseUtil.dateAfter())// 到期日期
					.build();
					
			int count = tenantInfoMapper.insert(tenantInfo);
			log.info("count={}",count);
			log.info("tenantInfo={}",tenantInfo);
		}
		
	}
	
}
