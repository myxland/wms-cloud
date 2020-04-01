package com.zlsrj.wms.saas.service.impl;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zlsrj.wms.api.dto.TenantCustomerStatementAddParam;
import com.zlsrj.wms.api.dto.TenantCustomerStatementUpdateParam;
import com.zlsrj.wms.api.entity.TenantCustomerStatement;
import com.zlsrj.wms.common.util.TranslateUtil;
import com.zlsrj.wms.saas.mapper.TenantCustomerStatementMapper;
import com.zlsrj.wms.saas.service.ITenantCustomerStatementService;
import com.zlsrj.wms.saas.service.IIdService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class TenantCustomerStatementServiceImpl extends ServiceImpl<TenantCustomerStatementMapper, TenantCustomerStatement> implements ITenantCustomerStatementService {
	@Resource
	private IIdService idService;

	@Override
	public String save(TenantCustomerStatementAddParam tenantCustomerStatementAddParam) {
		TenantCustomerStatement tenantCustomerStatement = TranslateUtil.translate(tenantCustomerStatementAddParam,
				TenantCustomerStatement.class);
		if (tenantCustomerStatement != null && StringUtils.isBlank(tenantCustomerStatement.getId())) {
			tenantCustomerStatement.setId(idService.selectId());
		}
		this.save(tenantCustomerStatement);

		return tenantCustomerStatement.getId();
	}

	@Override
	public boolean updateById(TenantCustomerStatementUpdateParam tenantCustomerStatementUpdateParam) {
		TenantCustomerStatement tenantCustomerStatement = TranslateUtil.translate(tenantCustomerStatementUpdateParam,
				TenantCustomerStatement.class);

		return this.updateById(tenantCustomerStatement);
	}
	
}
