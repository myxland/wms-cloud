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
import com.zlsrj.wms.api.entity.TenantBooklet;
import com.zlsrj.wms.api.entity.TenantCaliber;
import com.zlsrj.wms.api.entity.TenantCustomer;
import com.zlsrj.wms.api.entity.TenantInfo;
import com.zlsrj.wms.api.entity.TenantManufactor;
import com.zlsrj.wms.api.entity.TenantMeter;
import com.zlsrj.wms.api.entity.TenantPriceType;
import com.zlsrj.wms.api.entity.TenantWaterType;
import com.zlsrj.wms.common.test.TestCaseUtil;

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
	@Resource
	private TenantCustomerMapper tenantCustomerMapper;
	@Resource
	private TenantBookletMapper tenantBookletMapper;
	@Resource
	private TenantManufactorMapper tenantManufactorMapper;
	@Resource
	private TenantCaliberMapper tenantCaliberMapper;
	@Resource
	private TenantWaterTypeMapper tenantWaterTypeMapper;
	@Resource
	private TenantPriceTypeMapper tenantPriceTypeMapper;

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
			tenantInfo = TenantInfo.builder().id(RandomUtil.randomString(32)).build();
			
			TenantCustomer tenantCustomer = null;
			List<TenantCustomer> tenantCustomerList = tenantCustomerMapper.selectList(new QueryWrapper<TenantCustomer>().lambda().eq(TenantCustomer::getTenantId, tenantInfo.getId()));
			if(tenantCustomerList!=null && tenantCustomerList.size()>0) {
				tenantCustomer = tenantCustomerList.get(RandomUtil.randomInt(tenantCustomerList.size()));
			}
			
			TenantBooklet tenantBooklet = null;
			List<TenantBooklet> tenantBookletList = tenantBookletMapper.selectList(new QueryWrapper<TenantBooklet>().lambda().eq(TenantBooklet::getTenantId, tenantInfo.getId()));
			if(tenantBookletList!=null && tenantBookletList.size()>0) {
				tenantBooklet = tenantBookletList.get(RandomUtil.randomInt(tenantBookletList.size()));
			}
			
			TenantManufactor tenantManufactor = null;
			List<TenantManufactor> tenantManufactorList = tenantManufactorMapper.selectList(new QueryWrapper<TenantManufactor>().lambda().eq(TenantManufactor::getTenantId, tenantInfo.getId()));
			if(tenantManufactorList!=null && tenantManufactorList.size()>0) {
				tenantManufactor = tenantManufactorList.get(RandomUtil.randomInt(tenantManufactorList.size()));
			}
			
			TenantCaliber tenantCaliber = null;
			List<TenantCaliber> tenantCaliberList = tenantCaliberMapper.selectList(new QueryWrapper<TenantCaliber>().lambda().eq(TenantCaliber::getTenantId, tenantInfo.getId()));
			if(tenantCaliberList!=null && tenantCaliberList.size()>0) {
				tenantCaliber = tenantCaliberList.get(RandomUtil.randomInt(tenantCaliberList.size()));
			}
			
			TenantWaterType tenantWaterType = null;
			List<TenantWaterType> tenantWaterTypeList = tenantWaterTypeMapper.selectList(new QueryWrapper<TenantWaterType>().lambda().eq(TenantWaterType::getTenantId, tenantInfo.getId()));
			if(tenantWaterTypeList!=null && tenantWaterTypeList.size()>0) {
				tenantWaterType = tenantWaterTypeList.get(RandomUtil.randomInt(tenantWaterTypeList.size()));
			}
			
			TenantPriceType tenantPriceType = null;
			List<TenantPriceType> tenantPriceTypeList = tenantPriceTypeMapper.selectList(new QueryWrapper<TenantPriceType>().lambda().eq(TenantPriceType::getTenantId, tenantInfo.getId()));
			if(tenantPriceTypeList!=null && tenantPriceTypeList.size()>0) {
				tenantPriceType = tenantPriceTypeList.get(RandomUtil.randomInt(tenantPriceTypeList.size()));
			}
			
			TenantMeter tenantMeter = TenantMeter.builder()//
					.id(TestCaseUtil.id())// 水表ID
					.tenantId(tenantInfo.getId())// 租户ID
					.customerId(tenantCustomer!=null?tenantCustomer.getId():null)// 用户ID
					.meterBookletId(tenantBooklet!=null?tenantBooklet.getId():null)// 水表表册ID
					.meterParentId(null)// 上级水表ID
					.meterCode(RandomUtil.randomString(RandomUtil.BASE_NUMBER,10))// 水表编号
					.meterAddress(TestCaseUtil.address())// 水表地址
					.meterPeoples(RandomUtil.randomInt(0,1000+1))// 家庭人数
					.meterMachineCode(RandomUtil.randomString(RandomUtil.BASE_NUMBER,16))// 表身号码[钢印号等]
					.meterUseType(RandomUtil.randomInt(1,2+1))// 用途（1：水费结算；2：水量考核）
					.meterManufactorId(tenantManufactor!=null?tenantManufactor.getId():null)// 厂商ID
					.meterType(RandomUtil.randomInt(1,3+1))// 水表类型（1：机械表；2：远传表；3：IC卡表）
					.caliberId(tenantCaliber!=null?tenantCaliber.getId():null)// 水表口径ID
					.meterWaterTypeId(tenantWaterType!=null?tenantWaterType.getId():null)// 用水分类ID
					.priceTypeId(tenantPriceType!=null?tenantPriceType.getId():null)// 价格分类ID
					.meterIotCode(RandomUtil.randomString(4))// 采集系统代码
					.meterInstallDate(TestCaseUtil.dateBefore())// 水表安装日期
					.meterRegisterTime(TestCaseUtil.dateBefore())// 水表建档日期
					.meterStatus(RandomUtil.randomInt(1,3+1))// 水表状态（1：正常；2：暂停；3：拆表）
					.meterYearTotalWaters(new BigDecimal(RandomUtil.randomInt(0,1000)))// 年累计用水量
					.meterLastSettleTime(TestCaseUtil.dateBefore())// 最后一次结算日期
					.meterLastSettlePointer(new BigDecimal(RandomUtil.randomInt(0,1000)))// 最后一次结算指针
					.meterArrearsMoney(new BigDecimal(RandomUtil.randomInt(0,1000)))// 水表欠费总金额
					.build();
					
			int count = tenantMeterMapper.insert(tenantMeter);
			log.info("count={}",count);
			log.info("tenantMeter={}",tenantMeter);
		}
		
		
	}
	
}
