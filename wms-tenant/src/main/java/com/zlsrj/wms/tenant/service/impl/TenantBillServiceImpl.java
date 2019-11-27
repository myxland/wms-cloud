package com.zlsrj.wms.tenant.service.impl;
import java.math.BigDecimal;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zlsrj.wms.api.entity.TenantBill;
import com.zlsrj.wms.api.entity.TenantInfo;
import com.zlsrj.wms.tenant.mapper.TenantBillMapper;
import com.zlsrj.wms.tenant.service.IIdService;
import com.zlsrj.wms.tenant.service.ITenantBillService;

@Service
public class TenantBillServiceImpl extends ServiceImpl<TenantBillMapper, TenantBill> implements ITenantBillService {
	@Resource
	private IIdService idService;

	public boolean saveByTenantInfo(TenantInfo tenantInfo) {
		TenantBill tenantBill = TenantBill.builder()//
				.id(idService.selectId())// 编号ID
				.tenantId(tenantInfo.getId())// 租户编号
				.billPrintType(null)// 用户发票开具方式（1：按实收开票；2：按应收开票）
				.billRemark(null)// 发票备注定义
				.billService(null)// 电子发票服务商（百望/航天信息）
				.billJrdm(null)// 接入代码
				.billQmcs(null)// 签名值参数
				.billSkpbh(null)// 税控盘编号
				.billSkpkl(null)// 税控盘口令
				.billKeypwd(null)// 税务数字证书密码
				.build();

		return super.save(tenantBill);
	}
	
}
