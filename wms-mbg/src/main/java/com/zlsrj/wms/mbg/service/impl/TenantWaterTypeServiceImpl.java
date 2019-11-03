package com.zlsrj.wms.mbg.service.impl;

import java.util.Arrays;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zlsrj.wms.mbg.entity.TenantWaterType;
import com.zlsrj.wms.mbg.enums.WaterTypeEnum;
import com.zlsrj.wms.mbg.mapper.TenantWaterTypeMapper;
import com.zlsrj.wms.mbg.service.ITenantWaterTypeService;

import cn.hutool.core.util.IdUtil;

@Service
public class TenantWaterTypeServiceImpl extends ServiceImpl<TenantWaterTypeMapper, TenantWaterType>
		implements ITenantWaterTypeService {

	/**
	 * 初始化用水类型
	 * 
	 * @param tenantId
	 * @return
	 */
	@Transactional(rollbackFor = Exception.class)
	public boolean initByTenant(Long tenantId) {
		Arrays.asList(WaterTypeEnum.values()).forEach(c -> {
			TenantWaterType tenantWaterType = TenantWaterType.builder()//
					.id(IdUtil.createSnowflake(1L, 1L).nextId())// 用水类别编号
					.tenantId(tenantId)// 租户编号
					.waterTypeName(c.getText())// 用水类别名称
					.build();
			baseMapper.insert(tenantWaterType);
		});

		return true;
	}
}
