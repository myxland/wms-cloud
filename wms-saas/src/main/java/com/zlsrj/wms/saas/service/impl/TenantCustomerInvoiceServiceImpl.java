package com.zlsrj.wms.saas.service.impl;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zlsrj.wms.api.dto.TenantCustomerInvoiceAddParam;
import com.zlsrj.wms.api.dto.TenantCustomerInvoiceUpdateParam;
import com.zlsrj.wms.api.entity.TenantCustomerInvoice;
import com.zlsrj.wms.common.util.TranslateUtil;
import com.zlsrj.wms.saas.mapper.TenantCustomerInvoiceMapper;
import com.zlsrj.wms.saas.service.ITenantCustomerInvoiceService;
import com.zlsrj.wms.saas.service.IIdService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class TenantCustomerInvoiceServiceImpl extends ServiceImpl<TenantCustomerInvoiceMapper, TenantCustomerInvoice> implements ITenantCustomerInvoiceService {
	@Resource
	private IIdService idService;

	@Override
	public String save(TenantCustomerInvoiceAddParam tenantCustomerInvoiceAddParam) {
		TenantCustomerInvoice tenantCustomerInvoice = TranslateUtil.translate(tenantCustomerInvoiceAddParam,
				TenantCustomerInvoice.class);
		if (tenantCustomerInvoice != null && StringUtils.isBlank(tenantCustomerInvoice.getId())) {
			tenantCustomerInvoice.setId(idService.selectId());
		}
		this.save(tenantCustomerInvoice);

		return tenantCustomerInvoice.getId();
	}

	@Override
	public boolean updateById(TenantCustomerInvoiceUpdateParam tenantCustomerInvoiceUpdateParam) {
		TenantCustomerInvoice tenantCustomerInvoice = TranslateUtil.translate(tenantCustomerInvoiceUpdateParam,
				TenantCustomerInvoice.class);

		return this.updateById(tenantCustomerInvoice);
	}
	
}
