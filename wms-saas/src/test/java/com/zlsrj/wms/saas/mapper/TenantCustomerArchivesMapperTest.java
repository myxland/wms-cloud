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
import com.zlsrj.wms.api.entity.TenantCustomerArchives;
import com.zlsrj.wms.api.entity.TenantInfo;

import cn.hutool.core.util.RandomUtil;
import lombok.extern.slf4j.Slf4j;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class TenantCustomerArchivesMapperTest {

	@Resource
	private TenantCustomerArchivesMapper tenantCustomerArchivesMapper;
	@Resource
	private TenantInfoMapper tenantInfoMapper;
	
	@Test
	public void selectByIdTest() {
		String id = "";
		TenantCustomerArchives tenantCustomerArchives = tenantCustomerArchivesMapper.selectById(id);
		log.info(tenantCustomerArchives.toString());
	}
	
	@Test
	public void selectList() {
		QueryWrapper<TenantCustomerArchives> queryWrapper = new QueryWrapper<TenantCustomerArchives>();
		List<TenantCustomerArchives> tenantCustomerArchivesList = tenantCustomerArchivesMapper.selectList(queryWrapper);
		tenantCustomerArchivesList.forEach(e -> {
			log.info(e.toString());
		});
	}

	@Test
	public void insert() {
		List<TenantInfo> tenantInfoList = tenantInfoMapper.selectList(new QueryWrapper<TenantInfo>());
		for(int i=0;i<RandomUtil.randomInt(10, 100);i++) {
			TenantInfo tenantInfo = tenantInfoList.get(RandomUtil.randomInt(tenantInfoList.size()));
			//tenantInfo = TenantInfo.builder().id(1L).build();
			
			TenantCustomerArchives tenantCustomerArchives = TenantCustomerArchives.builder()//
					.id(TestCaseUtil.id())// 档案ID
					.tenantId(tenantInfo.getId())// 租户ID
					.customerId(TestCaseUtil.id())// 用户ID
					.customerCode(RandomUtil.randomString(4))// 用户号
					.archivesName(TestCaseUtil.name())// 档案名称
					.archivesCreateTime(TestCaseUtil.dateBefore())// 创建时间
					.archivesCreateDate(TestCaseUtil.dateBefore())// 创建日期
					.archivesFilename(RandomUtil.randomString(4))// 存储文件名称JSON串
					.archivesInformation(RandomUtil.randomString(4))// 证件信息JSON串，例如身份证号、地址等
					.archivesCode(RandomUtil.randomString(4))// 证件号码，例如身份证号等
					.addTime(TestCaseUtil.dateBefore())// 数据新增时间
					.updateTime(TestCaseUtil.dateBefore())// 数据修改时间
					.build();
				
			int count = tenantCustomerArchivesMapper.insert(tenantCustomerArchives);
			log.info("count={}",count);
			log.info("tenantCustomerArchives={}",tenantCustomerArchives);
		}
		
	}
	
}
