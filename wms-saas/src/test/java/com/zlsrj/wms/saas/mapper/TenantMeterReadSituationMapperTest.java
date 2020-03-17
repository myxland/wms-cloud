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
import com.zlsrj.wms.api.entity.TenantMeterReadSituation;
import com.zlsrj.wms.api.entity.TenantInfo;

import cn.hutool.core.util.RandomUtil;
import lombok.extern.slf4j.Slf4j;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class TenantMeterReadSituationMapperTest {

	@Resource
	private TenantMeterReadSituationMapper tenantMeterReadSituationMapper;
	@Resource
	private TenantInfoMapper tenantInfoMapper;
	
	@Test
	public void selectByIdTest() {
		String id = "";
		TenantMeterReadSituation tenantMeterReadSituation = tenantMeterReadSituationMapper.selectById(id);
		log.info(tenantMeterReadSituation.toString());
	}
	
	@Test
	public void selectList() {
		QueryWrapper<TenantMeterReadSituation> queryWrapper = new QueryWrapper<TenantMeterReadSituation>();
		List<TenantMeterReadSituation> tenantMeterReadSituationList = tenantMeterReadSituationMapper.selectList(queryWrapper);
		tenantMeterReadSituationList.forEach(e -> {
			log.info(e.toString());
		});
	}

	@Test
	public void insert() {
		List<TenantInfo> tenantInfoList = tenantInfoMapper.selectList(new QueryWrapper<TenantInfo>());
		for(int i=0;i<RandomUtil.randomInt(10, 100);i++) {
			TenantInfo tenantInfo = tenantInfoList.get(RandomUtil.randomInt(tenantInfoList.size()));
			//tenantInfo = TenantInfo.builder().id(1L).build();
			
			TenantMeterReadSituation tenantMeterReadSituation = TenantMeterReadSituation.builder()//
					.id(TestCaseUtil.id())// 抄表表况ID
					.tenantId(tenantInfo.getId())// 租户ID
					.readSituationName(TestCaseUtil.name())// 名称
					.readSituationData(RandomUtil.randomString(4))// 结构化数据
					.addTime(TestCaseUtil.dateBefore())// 数据新增时间
					.updateTime(TestCaseUtil.dateBefore())// 数据修改时间
					.build();
				
			int count = tenantMeterReadSituationMapper.insert(tenantMeterReadSituation);
			log.info("count={}",count);
			log.info("tenantMeterReadSituation={}",tenantMeterReadSituation);
		}
		
	}
	
}
