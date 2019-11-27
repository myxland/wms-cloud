package com.zlsrj.wms.tenant.service.impl;
import java.math.BigDecimal;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zlsrj.wms.api.entity.TenantInvoice;
import com.zlsrj.wms.api.entity.TenantInfo;
import com.zlsrj.wms.tenant.mapper.TenantInvoiceMapper;
import com.zlsrj.wms.tenant.service.IIdService;
import com.zlsrj.wms.tenant.service.ITenantInvoiceService;

@Service
public class TenantInvoiceServiceImpl extends ServiceImpl<TenantInvoiceMapper, TenantInvoice> implements ITenantInvoiceService {
	@Resource
	private IIdService idService;

	public boolean saveByTenantInfo(TenantInfo tenantInfo) {
		TenantInvoice tenantInvoice = TenantInvoice.builder()//
				.id(idService.selectId())// 租户编号
				.tenantId(tenantInfo.getId())// 租户编号
				.creditNumber(null)// 税号
				.invoiceAddress(null)// 开票地址
				.bankNo(null)// 开户行行号
				.bankName(null)// 开户行名称
				.accountNo(null)// 开户账号
				.build();

		return super.save(tenantInvoice);
	}
	
}
