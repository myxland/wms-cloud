package com.zlsrj.wms.saas.mapper;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zlsrj.wms.api.entity.TenantBooklet;
import com.zlsrj.wms.api.entity.TenantDepartment;
import com.zlsrj.wms.api.entity.TenantInfo;
import com.zlsrj.wms.api.entity.TenantWaterArea;
import com.zlsrj.wms.common.test.TestCaseUtil;

import cn.hutool.core.util.RandomUtil;
import lombok.extern.slf4j.Slf4j;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class TenantBookletMapperTest {

	@Resource
	private TenantBookletMapper tenantBookletMapper;
	@Resource
	private TenantInfoMapper tenantInfoMapper;
	@Resource
	private TenantDepartmentMapper tenantDepartmentMapper;
	@Resource
	private TenantWaterAreaMapper tenantWaterAreaMapper;

	@Test
	public void selectByIdTest() {
		String id = "";
		TenantBooklet tenantBooklet = tenantBookletMapper.selectById(id);
		log.info(tenantBooklet.toString());
	}
	
	@Test
	public void selectList() {
		QueryWrapper<TenantBooklet> queryWrapper = new QueryWrapper<TenantBooklet>();
		List<TenantBooklet> tenantBookletList = tenantBookletMapper.selectList(queryWrapper);
		tenantBookletList.forEach(e -> {
			log.info(e.toString());
		});
	}

	@Test
	public void insert() {
		List<TenantInfo> tenantInfoList = tenantInfoMapper.selectList(new QueryWrapper<TenantInfo>());
		for(int i=0;i<RandomUtil.randomInt(10, 100);i++) {
			TenantInfo tenantInfo = tenantInfoList.get(RandomUtil.randomInt(tenantInfoList.size()));
			tenantInfo = TenantInfo.builder().id(RandomUtil.randomString(32)).build();
			
			TenantDepartment tenantDepartment = null;
			List<TenantDepartment> tenantDepartmentList = tenantDepartmentMapper.selectList(new QueryWrapper<TenantDepartment>().lambda().eq(TenantDepartment::getTenantId, tenantInfo.getId()));
			if(tenantDepartmentList!=null && tenantDepartmentList.size()>0) {
				tenantDepartment = tenantDepartmentList.get(RandomUtil.randomInt(tenantDepartmentList.size()));
			}
			
			TenantWaterArea tenantWaterArea = null;
			List<TenantWaterArea> tenantWaterAreaList = tenantWaterAreaMapper.selectList(new QueryWrapper<TenantWaterArea>().lambda().eq(TenantWaterArea::getTenantId, tenantInfo.getId()));
			if(tenantWaterAreaList!=null && tenantWaterAreaList.size()>0) {
				tenantWaterArea = tenantWaterAreaList.get(RandomUtil.randomInt(tenantWaterAreaList.size()));
			}
			
			TenantBooklet tenantBooklet = TenantBooklet.builder()//
					.id(TestCaseUtil.id())// 表册ID
					.tenantId(tenantInfo.getId())// 租户ID
					.bookletDepartmentId(tenantDepartment!=null?tenantDepartment.getId():null)// 所属部门ID
					.bookletWaterAreaId(tenantWaterArea!=null?tenantWaterArea.getId():null)// 所属供水区域ID
					.bookletCode(RandomUtil.randomString(4))// 表册代码
					.bookletName("表册_"+TestCaseUtil.name())// 表册名称
					.bookletReadEmployeeId(RandomUtil.randomString(32))// 抄表员ID
					.bookletChargeEmployeeId(RandomUtil.randomString(32))// 收费员ID
					.bookletSettleCycleInterval(RandomUtil.randomInt(0,1000+1))// 结算间隔周期[月]
					.bookletLastSettleMonth(TestCaseUtil.monthBefore())// 最后一次结算月份
					.bookletNextSettleMonth(TestCaseUtil.monthAfter())// 下次计划结算月份
					.bookletStatus(RandomUtil.randomInt(1,2+1))// 表册状态（1：抄表进行中；2：抄表截止）
					.bookletOn(RandomUtil.randomInt(0,1+1))// 表册可用状态（1：可用；0：禁用）
					.build();
					
			int count = tenantBookletMapper.insert(tenantBooklet);
			log.info("count={}",count);
			log.info("tenantBooklet={}",tenantBooklet);
		}
		
	}
	
}
