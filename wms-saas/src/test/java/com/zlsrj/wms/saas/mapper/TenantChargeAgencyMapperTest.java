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
import com.zlsrj.wms.api.entity.TenantChargeAgency;
import com.zlsrj.wms.api.entity.TenantInfo;

import cn.hutool.core.util.RandomUtil;
import lombok.extern.slf4j.Slf4j;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class TenantChargeAgencyMapperTest {

	@Resource
	private TenantChargeAgencyMapper tenantChargeAgencyMapper;
	@Resource
	private TenantInfoMapper tenantInfoMapper;
	
	@Test
	public void selectByIdTest() {
		String id = "";
		TenantChargeAgency tenantChargeAgency = tenantChargeAgencyMapper.selectById(id);
		log.info(tenantChargeAgency.toString());
	}
	
	@Test
	public void selectList() {
		QueryWrapper<TenantChargeAgency> queryWrapper = new QueryWrapper<TenantChargeAgency>();
		List<TenantChargeAgency> tenantChargeAgencyList = tenantChargeAgencyMapper.selectList(queryWrapper);
		tenantChargeAgencyList.forEach(e -> {
			log.info(e.toString());
		});
	}

	@Test
	public void insert() {
		List<TenantInfo> tenantInfoList = tenantInfoMapper.selectList(new QueryWrapper<TenantInfo>());
		for(int i=0;i<RandomUtil.randomInt(10, 100);i++) {
			TenantInfo tenantInfo = tenantInfoList.get(RandomUtil.randomInt(tenantInfoList.size()));
			//tenantInfo = TenantInfo.builder().id(1L).build();
			
			TenantChargeAgency tenantChargeAgency = TenantChargeAgency.builder()//
					.id(TestCaseUtil.id())// 代收机构ID
					.tenantId(tenantInfo.getId())// 租户ID
					.chargeAgencyName(TestCaseUtil.name())// 代收机构名称
					.chargeAgencyData(RandomUtil.randomString(4))// 结构化数据
					.createType(RandomUtil.randomInt(1,2+1))// 创建类型（1：平台默认创建；2：租户自建）
					.apiOn(RandomUtil.randomInt(1,0+1))// 是否开放API（1：开放；0：不开放）
					.addTime(TestCaseUtil.dateBefore())// 数据新增时间
					.updateTime(TestCaseUtil.dateBefore())// 数据修改时间
					.build();
				
			int count = tenantChargeAgencyMapper.insert(tenantChargeAgency);
			log.info("count={}",count);
			log.info("tenantChargeAgency={}",tenantChargeAgency);
		}
		
	}
	
}
