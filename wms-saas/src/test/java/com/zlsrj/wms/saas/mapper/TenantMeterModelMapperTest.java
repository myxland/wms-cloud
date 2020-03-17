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
import com.zlsrj.wms.api.entity.TenantMeterModel;
import com.zlsrj.wms.api.entity.TenantInfo;

import cn.hutool.core.util.RandomUtil;
import lombok.extern.slf4j.Slf4j;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class TenantMeterModelMapperTest {

	@Resource
	private TenantMeterModelMapper tenantMeterModelMapper;
	@Resource
	private TenantInfoMapper tenantInfoMapper;
	
	@Test
	public void selectByIdTest() {
		String id = "";
		TenantMeterModel tenantMeterModel = tenantMeterModelMapper.selectById(id);
		log.info(tenantMeterModel.toString());
	}
	
	@Test
	public void selectList() {
		QueryWrapper<TenantMeterModel> queryWrapper = new QueryWrapper<TenantMeterModel>();
		List<TenantMeterModel> tenantMeterModelList = tenantMeterModelMapper.selectList(queryWrapper);
		tenantMeterModelList.forEach(e -> {
			log.info(e.toString());
		});
	}

	@Test
	public void insert() {
		List<TenantInfo> tenantInfoList = tenantInfoMapper.selectList(new QueryWrapper<TenantInfo>());
		for(int i=0;i<RandomUtil.randomInt(10, 100);i++) {
			TenantInfo tenantInfo = tenantInfoList.get(RandomUtil.randomInt(tenantInfoList.size()));
			//tenantInfo = TenantInfo.builder().id(1L).build();
			
			TenantMeterModel tenantMeterModel = TenantMeterModel.builder()//
					.id(TestCaseUtil.id())// 水表型号ID
					.tenantId(tenantInfo.getId())// 租户ID
					.meterModelName(TestCaseUtil.name())// 名称
					.meterModelData(RandomUtil.randomString(4))// 结构化数据
					.addTime(TestCaseUtil.dateBefore())// 数据新增时间
					.updateTime(TestCaseUtil.dateBefore())// 数据修改时间
					.build();
				
			int count = tenantMeterModelMapper.insert(tenantMeterModel);
			log.info("count={}",count);
			log.info("tenantMeterModel={}",tenantMeterModel);
		}
		
	}
	
}
