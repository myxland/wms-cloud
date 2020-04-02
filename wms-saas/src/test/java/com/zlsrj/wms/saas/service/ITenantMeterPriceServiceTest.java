package com.zlsrj.wms.saas.service;

import java.math.BigDecimal;
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
import com.zlsrj.wms.api.dto.TenantMeterPriceAddParam;
import com.zlsrj.wms.api.entity.TenantInfo;
import com.zlsrj.wms.api.entity.TenantMeter;
import com.zlsrj.wms.api.entity.TenantMeterPrice;
import com.zlsrj.wms.api.entity.TenantPrice;
import com.zlsrj.wms.saas.mapper.TenantInfoMapper;
import com.zlsrj.wms.saas.mapper.TenantMeterMapper;
import com.zlsrj.wms.saas.mapper.TenantPriceMapper;

import cn.hutool.core.util.RandomUtil;
import lombok.extern.slf4j.Slf4j;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class ITenantMeterPriceServiceTest {

	@Autowired
	private ITenantMeterPriceService tenantMeterPriceService;
	@Autowired
	private TenantInfoMapper tenantInfoMapper;
	@Autowired
	private TenantMeterMapper tenantMeterMapper;
	@Autowired
	private TenantPriceMapper tenantPriceMapper;
	
	@Test
	public void insertTest() {
		for(int i=0;i<10;i++) {
			QueryWrapper<TenantInfo> queryWrapperTenantInfo = new QueryWrapper<TenantInfo>();
			queryWrapperTenantInfo.lambda()//
					.eq(TenantInfo::getTenantType, 1)// 租户类型（1：使用单位；2：水表厂商；3：代收机构；4：内部运营；5：分销商）
					.in(TenantInfo::getId,"933d88d4d23244079cc0b49f99aa2c0b",
							"e1ddb601b6cc48b79f989d710712f6d0");
			;
			List<TenantInfo> tenantInfoList = tenantInfoMapper.selectList(queryWrapperTenantInfo);
			TenantInfo tenantInfo = tenantInfoList.get(RandomUtil.randomInt(tenantInfoList.size()));
			String tenantId = tenantInfo.getId();
			
			QueryWrapper<TenantMeter> queryWrapperTenantMeter = new QueryWrapper<TenantMeter>();
			queryWrapperTenantMeter.lambda()//
					.eq(TenantMeter::getTenantId, tenantId)// 
			;

			List<TenantMeter> tenantMeterList = tenantMeterMapper.selectList(queryWrapperTenantMeter);
			TenantMeter tenantMeter = tenantMeterList.get(RandomUtil.randomInt(tenantMeterList.size()));
			
			QueryWrapper<TenantPrice> queryWrapperTenantPrice = new QueryWrapper<TenantPrice>();
			queryWrapperTenantPrice.lambda()//
					.eq(TenantPrice::getTenantId, tenantId)// 
			;

			List<TenantPrice> tenantPriceList = tenantPriceMapper.selectList(queryWrapperTenantPrice);
			TenantPrice tenantPrice = tenantPriceList.get(RandomUtil.randomInt(tenantPriceList.size()));
			
			
			TenantMeterPriceAddParam tenantMeterPriceAddParam = new TenantMeterPriceAddParam();
			tenantMeterPriceAddParam.setTenantId(tenantId);// 租户ID
			tenantMeterPriceAddParam.setMeterId(tenantMeter.getId());// 水表ID
			tenantMeterPriceAddParam.setMeterCode(tenantMeter.getCustomerCode());// 水表编号
			tenantMeterPriceAddParam.setPriceOrder(RandomUtil.randomInt(0,1000+1));// 排序
			tenantMeterPriceAddParam.setPriceId(tenantPrice.getId());// 水价列表ID
			tenantMeterPriceAddParam.setPriceType(RandomUtil.randomInt(0,1+1));// 计费类别（1：定量；0：定比）
			tenantMeterPriceAddParam.setPriceScale(RandomUtil.randomBigDecimal(BigDecimal.ZERO, BigDecimal.ONE));// 系数

			log.info(ToStringBuilder.reflectionToString(tenantMeterPriceAddParam, ToStringStyle.MULTI_LINE_STYLE));

			String id = tenantMeterPriceService.save(tenantMeterPriceAddParam);

			log.info("id={}",id);
		}
	}

	@Test
	public void updateTest() {

		String id = "";

		TenantMeterPrice tenantMeterPrice = TenantMeterPrice.builder()//
				.tenantId(RandomUtil.randomString(4))// 租户ID
				.meterId(RandomUtil.randomString(4))// 水表ID
				.meterCode(RandomUtil.randomString(4))// 水表编号
				.priceOrder(RandomUtil.randomInt(0,1000+1))// 排序
				.priceId(RandomUtil.randomString(4))// 水价列表ID
				.priceType(RandomUtil.randomInt(0,1+1))// 计费类别（1：定量；0：定比）
				.priceScale(new BigDecimal(0))// 系数
				.addTime(new Date())// 数据新增时间
				.updateTime(new Date())// 数据修改时间
				.build();
		tenantMeterPrice.setId(id);

		log.info(ToStringBuilder.reflectionToString(tenantMeterPrice, ToStringStyle.MULTI_LINE_STYLE));

		boolean success = tenantMeterPriceService.updateById(tenantMeterPrice);

		log.info(Boolean.toString(success));
	}
}
