package com.zlsrj.wms.saas.service.impl;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zlsrj.wms.api.dto.TenantCustomerArchivesAddParam;
import com.zlsrj.wms.api.dto.TenantCustomerArchivesUpdateParam;
import com.zlsrj.wms.api.entity.TenantCustomerArchives;
import com.zlsrj.wms.common.util.TranslateUtil;
import com.zlsrj.wms.saas.mapper.TenantCustomerArchivesMapper;
import com.zlsrj.wms.saas.service.ITenantCustomerArchivesService;
import com.zlsrj.wms.saas.service.IIdService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class TenantCustomerArchivesServiceImpl extends ServiceImpl<TenantCustomerArchivesMapper, TenantCustomerArchives> implements ITenantCustomerArchivesService {
	@Resource
	private IIdService idService;

	@Override
	public String save(TenantCustomerArchivesAddParam tenantCustomerArchivesAddParam) {
		TenantCustomerArchives tenantCustomerArchives = TranslateUtil.translate(tenantCustomerArchivesAddParam,
				TenantCustomerArchives.class);
		if (tenantCustomerArchives != null && StringUtils.isBlank(tenantCustomerArchives.getId())) {
			tenantCustomerArchives.setId(idService.selectId());
		}
		this.save(tenantCustomerArchives);

		return tenantCustomerArchives.getId();
	}

	@Override
	public boolean updateById(TenantCustomerArchivesUpdateParam tenantCustomerArchivesUpdateParam) {
		TenantCustomerArchives tenantCustomerArchives = TranslateUtil.translate(tenantCustomerArchivesUpdateParam,
				TenantCustomerArchives.class);

		return this.updateById(tenantCustomerArchives);
	}
	
}
