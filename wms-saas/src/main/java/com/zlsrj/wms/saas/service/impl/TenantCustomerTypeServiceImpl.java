package com.zlsrj.wms.saas.service.impl;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zlsrj.wms.api.dto.TenantCustomerTypeAddParam;
import com.zlsrj.wms.api.dto.TenantCustomerTypeUpdateParam;
import com.zlsrj.wms.api.entity.TenantCustomerType;
import com.zlsrj.wms.common.util.TranslateUtil;
import com.zlsrj.wms.saas.mapper.TenantCustomerTypeMapper;
import com.zlsrj.wms.saas.service.ITenantCustomerTypeService;
import com.zlsrj.wms.saas.service.IIdService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class TenantCustomerTypeServiceImpl extends ServiceImpl<TenantCustomerTypeMapper, TenantCustomerType> implements ITenantCustomerTypeService {
	@Resource
	private IIdService idService;

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
