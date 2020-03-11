package com.zlsrj.wms.saas.mapper;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zlsrj.wms.common.test.TestCaseUtil;
import com.zlsrj.wms.api.entity.TenantPriceType;
import com.zlsrj.wms.api.entity.TenantInfo;
import com.zlsrj.wms.api.entity.TenantWaterType;

import cn.hutool.core.util.RandomUtil;
import lombok.extern.slf4j.Slf4j;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class TenantWaterTypeMapperTest {

	@Resource
	private TenantWaterTypeMapper tenantWaterTypeMapper;
	@Resource
	private TenantInfoMapper tenantInfoMapper;
	@Resource
	private TenantPriceTypeMapper tenantPriceTypeMapper;

	@Test
	public void selectByIdTest() {
		String id = "";
		TenantWaterType tenantWaterType = tenantWaterTypeMapper.selectById(id);
		log.info(tenantWaterType.toString());
	}
	
	@Test
	public void selectList() {
		QueryWrapper<TenantWaterType> queryWrapper = new QueryWrapper<TenantWaterType>();
		List<TenantWaterType> tenantWaterTypeList = tenantWaterTypeMapper.selectList(queryWrapper);
		tenantWaterTypeList.forEach(e -> {
			log.info(e.toString());
		});
	}

	@Test
	public void insert() {
		List<TenantInfo> tenantInfoList = tenantInfoMapper.selectList(new QueryWrapper<TenantInfo>());
		for(int i=0;i<RandomUtil.randomInt(10, 100);i++) {
			TenantInfo tenantInfo = tenantInfoList.get(RandomUtil.randomInt(tenantInfoList.size()));
			tenantInfo = TenantInfo.builder().id(RandomUtil.randomString(32)).build();
			
			TenantPriceType tenantPriceType = null;
			List<TenantPriceType> tenantPriceTypeList = tenantPriceTypeMapper.selectList(new QueryWrapper<TenantPriceType>().lambda().eq(TenantPriceType::getTenantId, tenantInfo.getId()));
			if(tenantPriceTypeList!=null && tenantPriceTypeList.size()>0) {
				tenantPriceType = tenantPriceTypeList.get(RandomUtil.randomInt(tenantPriceTypeList.size()));
			}
			
			TenantWaterType tenantWaterTypeParent = null;
			List<TenantWaterType> tenantWaterTypeList = tenantWaterTypeMapper.selectList(new QueryWrapper<TenantWaterType>().lambda().eq(TenantWaterType::getTenantId, tenantInfo.getId()));
			if(tenantWaterTypeList!=null && tenantWaterTypeList.size()>0) {
				tenantWaterTypeParent = tenantWaterTypeList.get(RandomUtil.randomInt(tenantWaterTypeList.size()));
			}
			
			TenantWaterType tenantWaterType = TenantWaterType.builder()//
					.id(TestCaseUtil.id())// 用水类别ID
					.tenantId(tenantInfo.getId())// 租户ID
					.waterTypeName("用水类别_"+RandomUtil.randomString(4))// 用水类别名称
					.waterTypeParentId(RandomUtil.randomBoolean()?(tenantWaterTypeParent!=null?tenantWaterTypeParent.getId():null):null)// 上级用水类别编号
					.defaultPriceTypeId(tenantPriceType!=null?tenantPriceType.getId():null)// 默认价格分类ID
					.build();
					
			int count = tenantWaterTypeMapper.insert(tenantWaterType);
			log.info("count={}",count);
			log.info("tenantWaterType={}",tenantWaterType);
		}
		
		
	}
	
}
