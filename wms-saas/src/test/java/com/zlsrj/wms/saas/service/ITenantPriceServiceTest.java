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

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zlsrj.wms.api.entity.TenantInfo;
import com.zlsrj.wms.api.entity.TenantPrice;
import com.zlsrj.wms.common.test.TestCaseUtil;
import com.zlsrj.wms.saas.mapper.TenantInfoMapper;

import cn.hutool.core.util.RandomUtil;
import lombok.extern.slf4j.Slf4j;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class ITenantPriceServiceTest {

	@Autowired
	private ITenantPriceService tenantPriceService;
	
	@Autowired
	private TenantInfoMapper tenantInfoMapper;

	@Test
	public void insertTest() {
		TenantPrice tenantPrice = TenantPrice.builder()//
				.id(TestCaseUtil.id())// 
				.tenantId(RandomUtil.randomString(4))// 租户ID
				.priceOrder(RandomUtil.randomInt(0,1000+1))// 排序
				.priceName(TestCaseUtil.name())// 水价名称
				.priceParentId(RandomUtil.randomString(4))// 父级ID
				.priceVersion(RandomUtil.randomString(4))// 水价版本
				.priceVersionMemo(RandomUtil.randomString(4))// 版本说明
				.marketingAreaId(RandomUtil.randomString(4))// 营销区域
				.priceMemo(RandomUtil.randomString(4))// 备注
				.addTime(new Date())// 数据新增时间
				.updateTime(new Date())// 数据修改时间
				.build();

		log.info(ToStringBuilder.reflectionToString(tenantPrice, ToStringStyle.MULTI_LINE_STYLE));

		boolean success = tenantPriceService.save(tenantPrice);

		log.info(Boolean.toString(success));
	}

	@Test
	public void updateTest() {

		String id = "";

		TenantPrice tenantPrice = TenantPrice.builder()//
				.tenantId(RandomUtil.randomString(4))// 租户ID
				.priceOrder(RandomUtil.randomInt(0,1000+1))// 排序
				.priceName(TestCaseUtil.name())// 水价名称
				.priceParentId(RandomUtil.randomString(4))// 父级ID
				.priceVersion(RandomUtil.randomString(4))// 水价版本
				.priceVersionMemo(RandomUtil.randomString(4))// 版本说明
				.marketingAreaId(RandomUtil.randomString(4))// 营销区域
				.priceMemo(RandomUtil.randomString(4))// 备注
				.addTime(new Date())// 数据新增时间
				.updateTime(new Date())// 数据修改时间
				.build();
		tenantPrice.setId(id);

		log.info(ToStringBuilder.reflectionToString(tenantPrice, ToStringStyle.MULTI_LINE_STYLE));

		boolean success = tenantPriceService.updateById(tenantPrice);

		log.info(Boolean.toString(success));
	}
	
	@Test
	public void saveBatchByTenantInfoTest() {
		QueryWrapper<TenantInfo> queryWrapperTenantInfo = new QueryWrapper<TenantInfo>();
		queryWrapperTenantInfo.lambda()//
				.eq(TenantInfo::getTenantType, 1)// 租户类型（1：使用单位；2：水表厂商；3：代收机构；4：内部运营；5：分销商）
				.in(TenantInfo::getId,"933d88d4d23244079cc0b49f99aa2c0b");//
		;
		
		List<TenantInfo> tenantInfoList = tenantInfoMapper.selectList(queryWrapperTenantInfo);
		TenantInfo tenantInfo = tenantInfoList.get(RandomUtil.randomInt(tenantInfoList.size()));
		
		tenantInfo.setPriceStepOn(1);
		boolean success = tenantPriceService.saveBatchByTenantInfo(tenantInfo);
		log.info(Boolean.toString(success));
	}
}
