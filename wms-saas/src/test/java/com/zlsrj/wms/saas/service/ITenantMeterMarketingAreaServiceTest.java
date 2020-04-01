package com.zlsrj.wms.saas.service;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.zlsrj.wms.common.test.TestCaseUtil;
import com.zlsrj.wms.saas.mapper.TenantInfoMapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zlsrj.wms.api.entity.TenantInfo;
import com.zlsrj.wms.api.entity.TenantMeterMarketingArea;

import cn.hutool.core.util.RandomUtil;
import lombok.extern.slf4j.Slf4j;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class ITenantMeterMarketingAreaServiceTest {

	@Autowired
	private ITenantMeterMarketingAreaService tenantMeterMarketingAreaService;
	@Autowired
	private TenantInfoMapper tenantInfoMapper;

	@Test
	public void insertTest() {
		TenantMeterMarketingArea tenantMeterMarketingArea = TenantMeterMarketingArea.builder()//
				.id(TestCaseUtil.id())// 营销机构ID
				.tenantId(RandomUtil.randomString(4))// 租户ID
				.marketingAreaType(RandomUtil.randomInt(0,1+1))// 区域类型（0：内部机构；1：代收机构）
				.marketingAreaName(TestCaseUtil.name())// 名称
				.marketingAreaParentId(RandomUtil.randomString(4))// 父级ID
				.marketingAreaData(RandomUtil.randomString(4))// 结构化数据
				.addTime(new Date())// 数据新增时间
				.updateTime(new Date())// 数据修改时间
				.build();

		log.info(ToStringBuilder.reflectionToString(tenantMeterMarketingArea, ToStringStyle.MULTI_LINE_STYLE));

		boolean success = tenantMeterMarketingAreaService.save(tenantMeterMarketingArea);

		log.info(Boolean.toString(success));
	}

	@Test
	public void updateTest() {

		String id = "";

		TenantMeterMarketingArea tenantMeterMarketingArea = TenantMeterMarketingArea.builder()//
				.tenantId(RandomUtil.randomString(4))// 租户ID
				.marketingAreaType(RandomUtil.randomInt(0,1+1))// 区域类型（0：内部机构；1：代收机构）
				.marketingAreaName(TestCaseUtil.name())// 名称
				.marketingAreaParentId(RandomUtil.randomString(4))// 父级ID
				.marketingAreaData(RandomUtil.randomString(4))// 结构化数据
				.addTime(new Date())// 数据新增时间
				.updateTime(new Date())// 数据修改时间
				.build();
		tenantMeterMarketingArea.setId(id);

		log.info(ToStringBuilder.reflectionToString(tenantMeterMarketingArea, ToStringStyle.MULTI_LINE_STYLE));

		boolean success = tenantMeterMarketingAreaService.updateById(tenantMeterMarketingArea);

		log.info(Boolean.toString(success));
	}
	
	@Test
	public void saveBatchByTenantInfoTest() {
		QueryWrapper<TenantInfo> queryWrapperTenantInfo = new QueryWrapper<TenantInfo>();
		queryWrapperTenantInfo.lambda()//
				.eq(TenantInfo::getTenantType, 1)// 租户类型（1：使用单位；2：水表厂商；3：代收机构；4：内部运营；5：分销商）
				.in(TenantInfo::getId,"e1ddb601b6cc48b79f989d710712f6d0");
		;
		
		List<TenantInfo> tenantInfoList = tenantInfoMapper.selectList(queryWrapperTenantInfo);
		TenantInfo tenantInfo = tenantInfoList.get(RandomUtil.randomInt(tenantInfoList.size()));
		
		boolean success = tenantMeterMarketingAreaService.saveBatchByTenantInfo(tenantInfo);
		log.info(Boolean.toString(success));
	}
}
