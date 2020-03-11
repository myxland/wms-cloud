package com.zlsrj.wms.saas.mapper;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zlsrj.wms.common.test.TestCaseUtil;
import com.zlsrj.wms.api.entity.TenantMeterStatus;

import cn.hutool.core.util.RandomUtil;
import lombok.extern.slf4j.Slf4j;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class TenantMeterStatusMapperTest {

	@Resource
	private TenantMeterStatusMapper tenantMeterStatusMapper;

	@Test
	public void selectByIdTest() {
		String id = "";
		TenantMeterStatus tenantMeterStatus = tenantMeterStatusMapper.selectById(id);
		log.info(tenantMeterStatus.toString());
	}
	
	@Test
	public void selectList() {
		QueryWrapper<TenantMeterStatus> queryWrapper = new QueryWrapper<TenantMeterStatus>();
		List<TenantMeterStatus> tenantMeterStatusList = tenantMeterStatusMapper.selectList(queryWrapper);
		tenantMeterStatusList.forEach(e -> {
			log.info(e.toString());
		});
	}

	@Test
	public void insert() {
		TenantMeterStatus tenantMeterStatus = TenantMeterStatus.builder()//
				.id(TestCaseUtil.id())// 表况ID
				.tenantId(RandomUtil.randomString(32))// 租户ID
				.meterStatusName(TestCaseUtil.name())// 表况名称
				.usenumCalcType(RandomUtil.randomInt(0,1+1))// 水量计算方式（1：自动计算；2：手工输入）
				.workBillType(RandomUtil.randomInt(0,1+1))// 生成工单类型（0：不生成；1：故障换表；3：周期换表）
				.createType(RandomUtil.randomInt(0,1+1))// 创建方式（1：平台创建；2：客户自建）
				.build();
				
		int count = tenantMeterStatusMapper.insert(tenantMeterStatus);
		log.info("count={}",count);
		log.info("tenantMeterStatus={}",tenantMeterStatus);
	}
	
}
