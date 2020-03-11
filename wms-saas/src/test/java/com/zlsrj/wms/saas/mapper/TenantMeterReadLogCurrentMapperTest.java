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
import com.zlsrj.wms.api.entity.TenantMeterReadLogCurrent;
import com.zlsrj.wms.api.entity.TenantInfo;
import com.zlsrj.wms.api.entity.TenantCustomer;
import com.zlsrj.wms.api.entity.TenantMeter;
import com.zlsrj.wms.api.entity.TenantMeterStatus;

import cn.hutool.core.util.RandomUtil;
import lombok.extern.slf4j.Slf4j;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class TenantMeterReadLogCurrentMapperTest {

	@Resource
	private TenantMeterReadLogCurrentMapper tenantMeterReadLogCurrentMapper;
	@Resource
	private TenantInfoMapper tenantInfoMapper;
	@Resource
	private TenantCustomerMapper tenantCustomerMapper;
	@Resource
	private TenantMeterMapper tenantMeterMapper;
	@Resource
	private TenantMeterStatusMapper tenantMeterStatusMapper;
	
	@Test
	public void selectByIdTest() {
		String id = "";
		TenantMeterReadLogCurrent tenantMeterReadLogCurrent = tenantMeterReadLogCurrentMapper.selectById(id);
		log.info(tenantMeterReadLogCurrent.toString());
	}
	
	@Test
	public void selectList() {
		QueryWrapper<TenantMeterReadLogCurrent> queryWrapper = new QueryWrapper<TenantMeterReadLogCurrent>();
		List<TenantMeterReadLogCurrent> tenantMeterReadLogCurrentList = tenantMeterReadLogCurrentMapper.selectList(queryWrapper);
		tenantMeterReadLogCurrentList.forEach(e -> {
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
			
			TenantMeter tenantMeter = null;
			List<TenantMeter> tenantMeterList = tenantMeterMapper.selectList(new QueryWrapper<TenantMeter>().lambda().eq(TenantMeter::getTenantId, tenantInfo.getId()));
			if(tenantMeterList!=null && tenantMeterList.size()>0) {
				tenantMeter = tenantMeterList.get(RandomUtil.randomInt(tenantMeterList.size()));
			}
			
			TenantMeterStatus tenantMeterStatus = null;
			List<TenantMeterStatus> tenantMeterStatusList = tenantMeterStatusMapper.selectList(new QueryWrapper<TenantMeterStatus>().lambda().eq(TenantMeterStatus::getTenantId, tenantInfo.getId()));
			if(tenantMeterStatusList!=null && tenantMeterStatusList.size()>0) {
				tenantMeterStatus = tenantMeterStatusList.get(RandomUtil.randomInt(tenantMeterStatusList.size()));
			}
			
			TenantMeterReadLogCurrent tenantMeterReadLogCurrent = TenantMeterReadLogCurrent.builder()//
					.id(TestCaseUtil.id())// 抄表计划
					.tenantId(tenantInfo.getId())// 租户ID
					.readMonth(TestCaseUtil.monthBefore())// 结算月份
					.customerId(tenantCustomer!=null?tenantCustomer.getId():null)// 用户ID
					.meterId(tenantMeter!=null?tenantMeter.getId():null)// 水表ID
					.meterYearTotalWatersBefore(new BigDecimal(0))// 结算前水表当年累计水量
					.settleStartTime(TestCaseUtil.dateBefore())// 结算开始时间
					.settleStartPointer(TestCaseUtil.water())// 结算开始指针
					.currentReadTime(TestCaseUtil.dateBefore())// 本次抄表时间
					.currentReadPointer(TestCaseUtil.water())// 本次抄表指针
					.readEmployeeId(RandomUtil.randomString(32))// 抄表员ID
					.meterStatusId(tenantMeterStatus!=null?tenantMeterStatus.getId():null)// 表次抄表状况
					.settleWaters(TestCaseUtil.water())// 应结算水量
					.receivableWaters(TestCaseUtil.water())// 应收水量
					.readSource(RandomUtil.randomInt(1,4+1))// 抄表来源（1：移动抄表；2：人工入账；3：远传表导入；4：远传表接口）
					.readStatus(RandomUtil.randomInt(0,1+1))// 抄表状态（0：未抄；1：已抄）
					.checkResult(RandomUtil.randomInt(0,1+1))// 检查结果（0：正常；1：异常）
					.processReault(RandomUtil.randomInt(0,1000+1))// 处理结果（1：已处理；2：未处理）
					.processEmployeeId(RandomUtil.randomString(32))// 处理人
					.processTime(TestCaseUtil.dateBefore())// 处理时间
					.processType(RandomUtil.randomInt(1,2+1))// 处理方式（1：重新抄表；2：通过）
					.build();
				
			int count = tenantMeterReadLogCurrentMapper.insert(tenantMeterReadLogCurrent);
			log.info("count={}",count);
			log.info("tenantMeterReadLogCurrent={}",tenantMeterReadLogCurrent);
		}
		
	}
	
}
