package com.zlsrj.wms.saas.service.impl;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zlsrj.wms.api.dto.TenantCustomerTypeAddParam;
import com.zlsrj.wms.api.dto.TenantCustomerTypeUpdateParam;
import com.zlsrj.wms.api.entity.TenantCustomerType;
import com.zlsrj.wms.api.entity.TenantCustomerTypeDefault;
import com.zlsrj.wms.api.entity.TenantInfo;
import com.zlsrj.wms.common.util.TranslateUtil;
import com.zlsrj.wms.saas.mapper.TenantCustomerTypeDefaultMapper;
import com.zlsrj.wms.saas.mapper.TenantCustomerTypeMapper;
import com.zlsrj.wms.saas.service.IIdService;
import com.zlsrj.wms.saas.service.ITenantCustomerTypeService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class TenantCustomerTypeServiceImpl extends ServiceImpl<TenantCustomerTypeMapper, TenantCustomerType> implements ITenantCustomerTypeService {
	@Resource
	private IIdService idService;
	@Resource
	private TenantCustomerTypeDefaultMapper tenantCustomerTypeDefaultMapper;
	
	@Override
	public boolean saveBatchByTenantInfo(TenantInfo tenantInfo) {
		boolean success = false;
		
		QueryWrapper<TenantCustomerType> queryWrapperTenantCustomerType = new QueryWrapper<TenantCustomerType>();
		queryWrapperTenantCustomerType.lambda()//
				.eq(TenantCustomerType::getTenantId, tenantInfo.getId())//
		;
		int count = super.count(queryWrapperTenantCustomerType);
		if (count > 0) {
			log.error("根据租户信息初始化用户分类失败，用户分类已存在。");
			return false;
		}
		
		List<TenantCustomerTypeDefault> tenantCustomerTypeDefaultList = tenantCustomerTypeDefaultMapper.selectList(null);
		
		if(tenantCustomerTypeDefaultList!=null && tenantCustomerTypeDefaultList.size()>0) {
			
			List<TenantCustomerType> tenantCustomerTypeList = new ArrayList<TenantCustomerType>();
			
			for(TenantCustomerTypeDefault tenantCustomerTypeDefault: tenantCustomerTypeDefaultList) {
				TenantCustomerType tenantCustomerType = TenantCustomerType.builder()//
						.id(idService.selectId())// 
						.tenantId(tenantInfo.getId())// 租户ID
						.customerTypeName(tenantCustomerTypeDefault.getCustomerTypeName())// 用户分类名称
						.customerTypeData(tenantCustomerTypeDefault.getCustomerTypeData())// 结构化数据
						.build();
				
				tenantCustomerTypeList.add(tenantCustomerType);
			}
			
			success = this.saveBatch(tenantCustomerTypeList);
		}
		
		return success;
	}
	
	@Override
	public boolean removeBatchByTenantInfo(TenantInfo tenantInfo) {
		boolean success = false;
		QueryWrapper<TenantCustomerType> queryWrapperTenantCustomerType = new QueryWrapper<TenantCustomerType>();
		queryWrapperTenantCustomerType.lambda()//
				.eq(TenantCustomerType::getTenantId, tenantInfo.getId())//
		;
		success = this.remove(queryWrapperTenantCustomerType);
		
		return success;
	}

	@Override
	public String save(TenantCustomerTypeAddParam tenantCustomerTypeAddParam) {
		TenantCustomerType tenantCustomerType = TranslateUtil.translate(tenantCustomerTypeAddParam,
				TenantCustomerType.class);
		if (tenantCustomerType != null && StringUtils.isBlank(tenantCustomerType.getId())) {
			tenantCustomerType.setId(idService.selectId());
		}
		this.save(tenantCustomerType);

		return tenantCustomerType.getId();
	}

	@Override
	public boolean updateById(TenantCustomerTypeUpdateParam tenantCustomerTypeUpdateParam) {
		TenantCustomerType tenantCustomerType = TranslateUtil.translate(tenantCustomerTypeUpdateParam,
				TenantCustomerType.class);

		return this.updateById(tenantCustomerType);
	}
	
}
