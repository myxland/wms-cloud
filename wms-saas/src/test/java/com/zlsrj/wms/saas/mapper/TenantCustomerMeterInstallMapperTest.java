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
import com.zlsrj.wms.api.entity.TenantCaliber;
import com.zlsrj.wms.api.entity.TenantCustomerMeterInstall;
import com.zlsrj.wms.api.entity.TenantInfo;
import com.zlsrj.wms.api.entity.TenantManufactor;
import com.zlsrj.wms.api.entity.TenantPriceType;
import com.zlsrj.wms.api.entity.TenantWaterType;
import com.zlsrj.wms.common.test.TestCaseUtil;

import cn.hutool.core.util.RandomUtil;
import lombok.extern.slf4j.Slf4j;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class TenantCustomerMeterInstallMapperTest {

	@Resource
	private TenantCustomerMeterInstallMapper tenantCustomerMeterInstallMapper;
	@Resource
	private TenantInfoMapper tenantInfoMapper;
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
		Long id = 1L;
		TenantCustomerMeterInstall tenantCustomerMeterInstall = tenantCustomerMeterInstallMapper.selectById(id);
		log.info(tenantCustomerMeterInstall.toString());
	}
	
	@Test
	public void selectList() {
		QueryWrapper<TenantCustomerMeterInstall> queryWrapper = new QueryWrapper<TenantCustomerMeterInstall>();
		List<TenantCustomerMeterInstall> tenantCustomerMeterInstallList = tenantCustomerMeterInstallMapper.selectList(queryWrapper);
		tenantCustomerMeterInstallList.forEach(e -> {
			log.info(e.toString());
		});
	}

	@Test
	public void insert() {
		List<TenantInfo> tenantInfoList = tenantInfoMapper.selectList(new QueryWrapper<TenantInfo>());
		
		
		
		for(int i=0;i<RandomUtil.randomInt(10, 100);i++) {
			TenantInfo tenantInfo = tenantInfoList.get(RandomUtil.randomInt(tenantInfoList.size()));
			tenantInfo = TenantInfo.builder().id(1L).build();
		
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
			
			TenantCustomerMeterInstall tenantCustomerMeterInstall = TenantCustomerMeterInstall.builder()//
					.id(TestCaseUtil.id())// 水表立户ID
					.tenantId(tenantInfo.getId())// 租户ID
					.meterId(TestCaseUtil.id())// 已经立户的水表ID
					.meterCode(RandomUtil.randomString(RandomUtil.BASE_NUMBER,6))// 已经立户的水表代码
					.custName(TestCaseUtil.name())// 用户名称
					.meterAddress(TestCaseUtil.address())// 水表地址
					.meterMachineCode(RandomUtil.randomString(RandomUtil.BASE_NUMBER,16))// 表身号码[钢印号等]
					.manufactorId(tenantManufactor!=null?tenantManufactor.getId():null)// 厂商ID
					.meterType(RandomUtil.randomInt(1,3+1))// 水表类型（1：机械表；2：远传表；3：IC卡表）
					.caliberId(tenantCaliber!=null?tenantCaliber.getId():null)// 水表口径ID
					.waterTypeId(tenantWaterType!=null?tenantWaterType.getId():null)// 用水分类ID
					.priceTypeId(tenantPriceType!=null?tenantPriceType.getId():null)// 价格分类ID
					.meterIotCode(RandomUtil.randomString(4))// 采集系统编号
					.meterInstallDate(TestCaseUtil.dateBefore())// 水表安装日期
					.meterLastSettleTime(TestCaseUtil.dateBefore())// 最后一次结算时间
					.meterLastSettlePointer(new BigDecimal(RandomUtil.randomInt(0, 1000)))// 最后一次结算指针
					.linkmanMobile(TestCaseUtil.mobile())// 联系人手机号码
					.custmerIdNo(TestCaseUtil.idcard())// 用户身份证编号
					.oldCode(RandomUtil.randomString(4))// 老系统编号
					.inputType(RandomUtil.randomInt(1,2+1))// 录入方式（1：手工录入；2：文件导入）
					.inputTime(new Date())// 录入日期
					.createOn(RandomUtil.randomInt(0,1+1))// 是否已经立户（1：是；0：否）
					.build();
					
			int count = tenantCustomerMeterInstallMapper.insert(tenantCustomerMeterInstall);
			log.info("count={}",count);
			log.info("tenantCustomerMeterInstall={}",tenantCustomerMeterInstall);
		}
		
	}
	
}
