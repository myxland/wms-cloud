package com.zlsrj.wms.saas.service.impl;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zlsrj.wms.api.dto.TenantCustomerContactsAddParam;
import com.zlsrj.wms.api.dto.TenantCustomerContactsUpdateParam;
import com.zlsrj.wms.api.entity.TenantCustomerContacts;
import com.zlsrj.wms.common.util.TranslateUtil;
import com.zlsrj.wms.saas.mapper.TenantCustomerContactsMapper;
import com.zlsrj.wms.saas.service.ITenantCustomerContactsService;
import com.zlsrj.wms.saas.service.IIdService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class TenantCustomerContactsServiceImpl extends ServiceImpl<TenantCustomerContactsMapper, TenantCustomerContacts> implements ITenantCustomerContactsService {
	@Resource
	private IIdService idService;

	@Override
	public String save(TenantCustomerContactsAddParam tenantCustomerContactsAddParam) {
		TenantCustomerContacts tenantCustomerContacts = TranslateUtil.translate(tenantCustomerContactsAddParam,
				TenantCustomerContacts.class);
		if (tenantCustomerContacts != null && StringUtils.isBlank(tenantCustomerContacts.getId())) {
			tenantCustomerContacts.setId(idService.selectId());
		}
		this.save(tenantCustomerContacts);

		return tenantCustomerContacts.getId();
	}

	@Override
	public boolean updateById(TenantCustomerContactsUpdateParam tenantCustomerContactsUpdateParam) {
		TenantCustomerContacts tenantCustomerContacts = TranslateUtil.translate(tenantCustomerContactsUpdateParam,
				TenantCustomerContacts.class);

		return this.updateById(tenantCustomerContacts);
	}
	
}
