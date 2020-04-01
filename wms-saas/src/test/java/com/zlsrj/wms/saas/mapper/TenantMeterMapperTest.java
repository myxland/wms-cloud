package com.zlsrj.wms.saas.mapper;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zlsrj.wms.common.test.TestCaseUtil;
import com.zlsrj.wms.api.entity.TenantMeter;
import com.zlsrj.wms.api.entity.TenantInfo;

import cn.hutool.core.util.RandomUtil;
import lombok.extern.slf4j.Slf4j;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class TenantMeterMapperTest {

	@Resource
	private TenantMeterMapper tenantMeterMapper;
	@Resource
	private TenantInfoMapper tenantInfoMapper;
	
	@Test
	public void selectByIdTest() {
		String id = "";
		TenantMeter tenantMeter = tenantMeterMapper.selectById(id);
		log.info(tenantMeter.toString());
	}
	
	@Test
	public void selectList() {
		QueryWrapper<TenantMeter> queryWrapper = new QueryWrapper<TenantMeter>();
		List<TenantMeter> tenantMeterList = tenantMeterMapper.selectList(queryWrapper);
		tenantMeterList.forEach(e -> {
			log.info(e.toString());
		});
	}

	@Test
	public void insert() {
		List<TenantInfo> tenantInfoList = tenantInfoMapper.selectList(new QueryWrapper<TenantInfo>());
		for(int i=0;i<RandomUtil.randomInt(10, 100);i++) {
			TenantInfo tenantInfo = tenantInfoList.get(RandomUtil.randomInt(tenantInfoList.size()));
			//tenantInfo = TenantInfo.builder().id(1L).build();
			
			TenantMeter tenantMeter = TenantMeter.builder()//
					.id(TestCaseUtil.id())// 水表ID
					.tenantId(tenantInfo.getId())// 租户ID
					.customerId(TestCaseUtil.id())// 用户IＤ
					.customerCode(RandomUtil.randomString(4))// 用户编号
					.meterCode(RandomUtil.randomString(4))// 水表编号
					.meterAddress(TestCaseUtil.address())// 水表地址
					.meterBookId(TestCaseUtil.id())// 表册ID
					.meterPeoples(RandomUtil.randomInt(0,1000+1))// 用水人数
					.meterStatus(RandomUtil.randomInt(1,3+1))// 水表状态（1：正常；2：暂停；3：拆表）
					.meterBrandId(TestCaseUtil.id())// 水表厂家
					.meterCaliberId(TestCaseUtil.id())// 水表口径
					.meterTypeId(TestCaseUtil.id())// 水表类型
					.meterModelId(TestCaseUtil.id())// 水表型号
					.meterMarketingAreaId(TestCaseUtil.id())// 营销区域
					.meterSupplyAreaId(TestCaseUtil.id())// 供水区域
					.meterIndustryId(TestCaseUtil.id())// 行业分类
					.meterUseType(RandomUtil.randomInt(1,3+1))// 水表用途（1：计量计费；2：计量不计费；3：考核表计量）
					.meterSaveWater(RandomUtil.randomInt(0,1000+1))// 节水标志（1：节水；0：无）
					.meterNewFlag(RandomUtil.randomInt(0,1000+1))// 新表标志（1：新表；0：旧表）
					.meterGpsX(RandomUtil.randomString(4))// gps x坐标
					.meterGpsY(RandomUtil.randomString(4))// gps y坐标
					.meterMachineCode(RandomUtil.randomString(4))// 表身码
					.meterRemoteCode(RandomUtil.randomString(4))// 远传表号
					.meterInstallDate(TestCaseUtil.dateBefore())// 水表安装日期
					.meterRegisterTime(TestCaseUtil.dateBefore())// 建档时间
					.meterInstallPer(RandomUtil.randomString(4))// 装表人员
					.meterReadOrder(RandomUtil.randomInt(0,1000+1))// 抄表顺序
					.meterReadCode(RandomUtil.randomInt(0,1000+1))// 最近抄码
					.meterReadDate(TestCaseUtil.dateBefore())// 最近抄表日期
					.meterSettleCode(RandomUtil.randomInt(0,1000+1))// 最近计费抄码
					.meterSettleDate(TestCaseUtil.dateBefore())// 最近计费日期
					.meterOweAmt(new BigDecimal(0))// 欠费金额
					.meterPenaltyAmt(new BigDecimal(0))// 违约金
					.meterYearTotalWaters(TestCaseUtil.water())// 年用水总量
					.meterTotalWaters(TestCaseUtil.water())// 历史用水总量
					.meterPriceStepDate(TestCaseUtil.dateBefore())// 阶梯起算日
					.meterPriceStepWaters(TestCaseUtil.water())// 阶梯使用量
					.meterMemo(RandomUtil.randomString(4))// 备注
					.addTime(TestCaseUtil.dateBefore())// 数据新增时间
					.updateTime(TestCaseUtil.dateBefore())// 数据修改时间
					.build();
				
			int count = tenantMeterMapper.insert(tenantMeter);
			log.info("count={}",count);
			log.info("tenantMeter={}",tenantMeter);
		}
		
	}
	
	@Test
	public void selectAggregation() {
		QueryWrapper<TenantMeter> wrapper = new QueryWrapper<TenantMeter>();
		wrapper.lambda()//
				.eq(TenantMeter::getTenantId, 1L)//
		;
		TenantMeter tenantMeterAggregation = tenantMeterMapper.selectAggregation(wrapper);
		
		log.info("tenantMeterAggregation={}", tenantMeterAggregation);
	}
	
}
