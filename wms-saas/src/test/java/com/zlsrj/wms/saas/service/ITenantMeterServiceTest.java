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
import com.zlsrj.wms.api.dto.TenantMeterAddParam;
import com.zlsrj.wms.api.entity.TenantBook;
import com.zlsrj.wms.api.entity.TenantCustomer;
import com.zlsrj.wms.api.entity.TenantInfo;
import com.zlsrj.wms.api.entity.TenantMeter;
import com.zlsrj.wms.api.entity.TenantMeterBrand;
import com.zlsrj.wms.api.entity.TenantMeterCaliber;
import com.zlsrj.wms.api.entity.TenantMeterIndustry;
import com.zlsrj.wms.api.entity.TenantMeterMarketingArea;
import com.zlsrj.wms.api.entity.TenantMeterModel;
import com.zlsrj.wms.api.entity.TenantMeterSupplyArea;
import com.zlsrj.wms.api.entity.TenantMeterType;
import com.zlsrj.wms.common.test.TestCaseUtil;
import com.zlsrj.wms.saas.mapper.TenantBookMapper;
import com.zlsrj.wms.saas.mapper.TenantCustomerMapper;
import com.zlsrj.wms.saas.mapper.TenantInfoMapper;
import com.zlsrj.wms.saas.mapper.TenantMeterBrandMapper;
import com.zlsrj.wms.saas.mapper.TenantMeterCaliberMapper;
import com.zlsrj.wms.saas.mapper.TenantMeterIndustryMapper;
import com.zlsrj.wms.saas.mapper.TenantMeterMarketingAreaMapper;
import com.zlsrj.wms.saas.mapper.TenantMeterModelMapper;
import com.zlsrj.wms.saas.mapper.TenantMeterSupplyAreaMapper;
import com.zlsrj.wms.saas.mapper.TenantMeterTypeMapper;

import cn.hutool.core.util.RandomUtil;
import lombok.extern.slf4j.Slf4j;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class ITenantMeterServiceTest {

	@Autowired
	private ITenantMeterService tenantMeterService;
	
	@Autowired
	private TenantInfoMapper tenantInfoMapper;
	@Autowired
	private TenantCustomerMapper tenantCustomerMapper;
	@Autowired
	private TenantBookMapper tenantBookMapper;
	@Autowired
	private TenantMeterBrandMapper tenantMeterBrandMapper;
	@Autowired
	private TenantMeterCaliberMapper tenantMeterCaliberMapper;
	@Autowired
	private TenantMeterTypeMapper tenantMeterTypeMapper;
	@Autowired
	private TenantMeterModelMapper tenantMeterModelMapper;
	@Autowired
	private TenantMeterMarketingAreaMapper tenantMeterMarketingAreaMapper;
	@Autowired
	private TenantMeterSupplyAreaMapper tenantMeterSupplyAreaMapper;
	@Autowired
	private TenantMeterIndustryMapper tenantMeterIndustryMapper;

	@Test
	public void insertTest() {
		
		QueryWrapper<TenantInfo> queryWrapperTenantInfo = new QueryWrapper<TenantInfo>();
		queryWrapperTenantInfo.lambda()//
				.eq(TenantInfo::getTenantType, 1)// 租户类型（1：使用单位；2：水表厂商；3：代收机构；4：内部运营；5：分销商）
				.in(TenantInfo::getId,"933d88d4d23244079cc0b49f99aa2c0b",
						"e1ddb601b6cc48b79f989d710712f6d0");
		;

		List<TenantInfo> tenantInfoList = tenantInfoMapper.selectList(queryWrapperTenantInfo);
		TenantInfo tenantInfo = tenantInfoList.get(RandomUtil.randomInt(tenantInfoList.size()));
		
		String tenantId = tenantInfo.getId();
		
		QueryWrapper<TenantCustomer> queryWrapperTenantCustomer = new QueryWrapper<TenantCustomer>();
		queryWrapperTenantCustomer.lambda()//
				.eq(TenantCustomer::getTenantId, tenantId)// 
		;

		List<TenantCustomer> tenantCustomerList = tenantCustomerMapper.selectList(queryWrapperTenantCustomer);
		TenantCustomer tenantCustomer = tenantCustomerList.get(RandomUtil.randomInt(tenantCustomerList.size()));
		
		QueryWrapper<TenantBook> queryWrapperTenantBook = new QueryWrapper<TenantBook>();
		queryWrapperTenantBook.lambda()//
				.eq(TenantBook::getTenantId, tenantId)// 
		;

		List<TenantBook> tenantBookList = tenantBookMapper.selectList(queryWrapperTenantBook);
		TenantBook tenantBook = tenantBookList.get(RandomUtil.randomInt(tenantBookList.size()));
		
		QueryWrapper<TenantMeterBrand> queryWrapperTenantMeterBrand = new QueryWrapper<TenantMeterBrand>();
		queryWrapperTenantMeterBrand.lambda()//
				.eq(TenantMeterBrand::getTenantId, tenantId)// 
		;

		List<TenantMeterBrand> tenantMeterBrandList = tenantMeterBrandMapper.selectList(queryWrapperTenantMeterBrand);
		TenantMeterBrand tenantMeterBrand = tenantMeterBrandList.get(RandomUtil.randomInt(tenantMeterBrandList.size()));
		
		QueryWrapper<TenantMeterCaliber> queryWrapperTenantMeterCaliber = new QueryWrapper<TenantMeterCaliber>();
		queryWrapperTenantMeterCaliber.lambda()//
				.eq(TenantMeterCaliber::getTenantId, tenantId)// 
		;

		List<TenantMeterCaliber> tenantMeterCaliberList = tenantMeterCaliberMapper.selectList(queryWrapperTenantMeterCaliber);
		TenantMeterCaliber tenantMeterCaliber = tenantMeterCaliberList.get(RandomUtil.randomInt(tenantMeterCaliberList.size()));
		
		QueryWrapper<TenantMeterType> queryWrapperTenantMeterType = new QueryWrapper<TenantMeterType>();
		queryWrapperTenantMeterType.lambda()//
				.eq(TenantMeterType::getTenantId, tenantId)// 
		;

		List<TenantMeterType> tenantMeterTypeList = tenantMeterTypeMapper.selectList(queryWrapperTenantMeterType);
		TenantMeterType tenantMeterType = tenantMeterTypeList.get(RandomUtil.randomInt(tenantMeterTypeList.size()));
		
		QueryWrapper<TenantMeterModel> queryWrapperTenantMeterModel = new QueryWrapper<TenantMeterModel>();
		queryWrapperTenantMeterModel.lambda()//
				.eq(TenantMeterModel::getTenantId, tenantId)// 
		;

		List<TenantMeterModel> tenantMeterModelList = tenantMeterModelMapper.selectList(queryWrapperTenantMeterModel);
		TenantMeterModel tenantMeterModel = tenantMeterModelList.get(RandomUtil.randomInt(tenantMeterModelList.size()));
		
		QueryWrapper<TenantMeterMarketingArea> queryWrapperTenantMeterMarketingArea = new QueryWrapper<TenantMeterMarketingArea>();
		queryWrapperTenantMeterMarketingArea.lambda()//
				.eq(TenantMeterMarketingArea::getTenantId, tenantId)// 
		;

		List<TenantMeterMarketingArea> tenantMeterMarketingAreaList = tenantMeterMarketingAreaMapper.selectList(queryWrapperTenantMeterMarketingArea);
		TenantMeterMarketingArea tenantMeterMarketingArea = tenantMeterMarketingAreaList.get(RandomUtil.randomInt(tenantMeterMarketingAreaList.size()));
		
		QueryWrapper<TenantMeterSupplyArea> queryWrapperTenantMeterSupplyArea = new QueryWrapper<TenantMeterSupplyArea>();
		queryWrapperTenantMeterSupplyArea.lambda()//
				.eq(TenantMeterSupplyArea::getTenantId, tenantId)// 
		;

		List<TenantMeterSupplyArea> tenantMeterSupplyAreaList = tenantMeterSupplyAreaMapper.selectList(queryWrapperTenantMeterSupplyArea);
		TenantMeterSupplyArea tenantMeterSupplyArea = tenantMeterSupplyAreaList.get(RandomUtil.randomInt(tenantMeterSupplyAreaList.size()));
		
		
		QueryWrapper<TenantMeterIndustry> queryWrapperTenantMeterIndustry = new QueryWrapper<TenantMeterIndustry>();
		queryWrapperTenantMeterIndustry.lambda()//
				.eq(TenantMeterIndustry::getTenantId, tenantId)// 
		;

		List<TenantMeterIndustry> tenantMeterIndustryList = tenantMeterIndustryMapper.selectList(queryWrapperTenantMeterIndustry);
		TenantMeterIndustry tenantMeterIndustry = tenantMeterIndustryList.get(RandomUtil.randomInt(tenantMeterIndustryList.size()));
		
		TenantMeterAddParam tenantMeterAddParam = new TenantMeterAddParam();
		tenantMeterAddParam.setTenantId(tenantId);// 租户ID
		tenantMeterAddParam.setCustomerId(tenantCustomer.getId());// 用户IＤ
		tenantMeterAddParam.setCustomerCode(tenantCustomer.getCustomerCode());// 用户编号
		//tenantMeterAddParam.setMeterCode(RandomUtil.randomString(4));// 水表编号 后台生成
		tenantMeterAddParam.setMeterAddress(TestCaseUtil.address());// 水表地址
		tenantMeterAddParam.setMeterBookId(tenantBook.getId());// 表册ID
		tenantMeterAddParam.setMeterPeoples(RandomUtil.randomInt(1,10+1));// 用水人数
		tenantMeterAddParam.setMeterStatus(RandomUtil.randomInt(1,3+1));// 水表状态（1：正常；2：暂停；3：拆表）
		tenantMeterAddParam.setMeterBrandId(tenantMeterBrand.getId());// 水表厂家
		tenantMeterAddParam.setMeterCaliberId(tenantMeterCaliber.getId());// 水表口径
		tenantMeterAddParam.setMeterTypeId(tenantMeterType.getId());// 水表类型
		tenantMeterAddParam.setMeterModelId(tenantMeterModel.getId());// 水表型号
		tenantMeterAddParam.setMeterMarketingAreaId(tenantMeterMarketingArea.getId());// 营销区域
		tenantMeterAddParam.setMeterSupplyAreaId(tenantMeterSupplyArea.getId());// 供水区域
		tenantMeterAddParam.setMeterIndustryId(tenantMeterIndustry.getId());// 行业分类
		tenantMeterAddParam.setMeterUseType(RandomUtil.randomInt(1,3+1));// 水表用途（1：计量计费；2：计量不计费；3：考核表计量）
		tenantMeterAddParam.setMeterSaveWater(RandomUtil.randomInt(0,1+1));// 节水标志（1：节水；0：无）
		tenantMeterAddParam.setMeterNewFlag(RandomUtil.randomInt(0,1+1));// 新表标志（1：新表；0：旧表）
		tenantMeterAddParam.setMeterGpsX(RandomUtil.randomNumbers(4));// gps x坐标
		tenantMeterAddParam.setMeterGpsY(RandomUtil.randomNumbers(4));// gps y坐标
		tenantMeterAddParam.setMeterMachineCode(RandomUtil.randomString(4));// 表身码
		tenantMeterAddParam.setMeterRemoteCode(RandomUtil.randomString(4));// 远传表号
		tenantMeterAddParam.setMeterInstallDate(TestCaseUtil.dateBefore());// 水表安装日期
		tenantMeterAddParam.setMeterRegisterTime(TestCaseUtil.dateBefore());// 建档时间
		tenantMeterAddParam.setMeterInstallPer(RandomUtil.randomString(4));// 装表人员
		tenantMeterAddParam.setMeterReadOrder(RandomUtil.randomInt(0,1000+1));// 抄表顺序
		tenantMeterAddParam.setMeterReadCode(RandomUtil.randomInt(0,1000+1));// 最近抄码
		tenantMeterAddParam.setMeterReadDate(TestCaseUtil.dateBefore());// 最近抄表日期
		tenantMeterAddParam.setMeterSettleCode(RandomUtil.randomInt(0,1000+1));// 最近计费抄码
		tenantMeterAddParam.setMeterSettleDate(TestCaseUtil.dateBefore());// 最近计费日期
		tenantMeterAddParam.setMeterOweAmt(new BigDecimal(0));// 欠费金额
		tenantMeterAddParam.setMeterPenaltyAmt(new BigDecimal(0));// 违约金
		tenantMeterAddParam.setMeterYearTotalWaters(new BigDecimal(0));// 年用水总量
		tenantMeterAddParam.setMeterTotalWaters(new BigDecimal(0));// 历史用水总量
		tenantMeterAddParam.setMeterPriceStepDate(TestCaseUtil.dateBefore());// 阶梯起算日
		tenantMeterAddParam.setMeterPriceStepWaters(new BigDecimal(0));// 阶梯使用量
		tenantMeterAddParam.setMeterMemo(RandomUtil.randomString(4));// 备注

		log.info(ToStringBuilder.reflectionToString(tenantMeterAddParam, ToStringStyle.MULTI_LINE_STYLE));

		String id = tenantMeterService.save(tenantMeterAddParam);

		log.info("id={}",id);
	}

	@Test
	public void updateTest() {

		String id = "";

		TenantMeter tenantMeter = TenantMeter.builder()//
				.tenantId(RandomUtil.randomString(4))// 租户ID
				.customerId(RandomUtil.randomString(4))// 用户IＤ
				.customerCode(RandomUtil.randomString(4))// 用户编号
				.meterCode(RandomUtil.randomString(4))// 水表编号
				.meterAddress(TestCaseUtil.address())// 水表地址
				.meterBookId(RandomUtil.randomString(4))// 表册ID
				.meterPeoples(RandomUtil.randomInt(0,1000+1))// 用水人数
				.meterStatus(RandomUtil.randomInt(0,1+1))// 水表状态（1：正常；2：暂停；3：拆表）
				.meterBrandId(RandomUtil.randomString(4))// 水表厂家
				.meterCaliberId(RandomUtil.randomString(4))// 水表口径
				.meterTypeId(RandomUtil.randomString(4))// 水表类型
				.meterModelId(RandomUtil.randomString(4))// 水表型号
				.meterMarketingAreaId(RandomUtil.randomString(4))// 营销区域
				.meterSupplyAreaId(RandomUtil.randomString(4))// 供水区域
				.meterIndustryId(RandomUtil.randomString(4))// 行业分类
				.meterUseType(RandomUtil.randomInt(0,1+1))// 水表用途（1：计量计费；2：计量不计费；3：考核表计量）
				.meterSaveWater(RandomUtil.randomInt(0,1000+1))// 节水标志（1：节水；0：无）
				.meterNewFlag(RandomUtil.randomInt(0,1000+1))// 新表标志（1：新表；0：旧表）
				.meterGpsX(RandomUtil.randomString(4))// gps x坐标
				.meterGpsY(RandomUtil.randomString(4))// gps y坐标
				.meterMachineCode(RandomUtil.randomString(4))// 表身码
				.meterRemoteCode(RandomUtil.randomString(4))// 远传表号
				.meterInstallDate(new Date())// 水表安装日期
				.meterRegisterTime(new Date())// 建档时间
				.meterInstallPer(RandomUtil.randomString(4))// 装表人员
				.meterReadOrder(RandomUtil.randomInt(0,1000+1))// 抄表顺序
				.meterReadCode(RandomUtil.randomInt(0,1000+1))// 最近抄码
				.meterReadDate(new Date())// 最近抄表日期
				.meterSettleCode(RandomUtil.randomInt(0,1000+1))// 最近计费抄码
				.meterSettleDate(new Date())// 最近计费日期
				.meterOweAmt(new BigDecimal(0))// 欠费金额
				.meterPenaltyAmt(new BigDecimal(0))// 违约金
				.meterYearTotalWaters(new BigDecimal(0))// 年用水总量
				.meterTotalWaters(new BigDecimal(0))// 历史用水总量
				.meterPriceStepDate(new Date())// 阶梯起算日
				.meterPriceStepWaters(new BigDecimal(0))// 阶梯使用量
				.meterMemo(RandomUtil.randomString(4))// 备注
				.addTime(new Date())// 数据新增时间
				.updateTime(new Date())// 数据修改时间
				.build();
		tenantMeter.setId(id);

		log.info(ToStringBuilder.reflectionToString(tenantMeter, ToStringStyle.MULTI_LINE_STYLE));

		boolean success = tenantMeterService.updateById(tenantMeter);

		log.info(Boolean.toString(success));
	}
}
