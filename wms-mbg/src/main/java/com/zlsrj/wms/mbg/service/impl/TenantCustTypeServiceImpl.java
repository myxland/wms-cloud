package com.zlsrj.wms.mbg.service.impl;

import java.util.Arrays;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zlsrj.wms.mbg.entity.TenantCustType;
import com.zlsrj.wms.mbg.enums.CustTypeEnum;
import com.zlsrj.wms.mbg.mapper.TenantCustTypeMapper;
import com.zlsrj.wms.mbg.service.ITenantCustTypeService;

import cn.hutool.core.util.IdUtil;

@Service
public class TenantCustTypeServiceImpl extends ServiceImpl<TenantCustTypeMapper, TenantCustType>
		implements ITenantCustTypeService {

	/**
	 * 初始化客户类型
	 * 
	 * @param tenantId
	 * @return
	 */
	@Transactional(rollbackFor = Exception.class)
	public boolean initByTenant(Long tenantId) {
		Arrays.asList(CustTypeEnum.values()).forEach(c -> {
			TenantCustType tenantCustType = TenantCustType.builder()//
					.id(IdUtil.createSnowflake(1L, 1L).nextId())// 用户类别编号
					.tenantId(tenantId)// 租户编号
					.custTypeName(c.getText())// 用户类别名称
					.build();
			baseMapper.insert(tenantCustType);
		});

		return true;
	}
}
