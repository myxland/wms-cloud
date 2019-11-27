package com.zlsrj.wms.tenant.service.impl;
import java.math.BigDecimal;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zlsrj.wms.api.entity.TenantConfig;
import com.zlsrj.wms.api.entity.TenantInfo;
import com.zlsrj.wms.tenant.mapper.TenantConfigMapper;
import com.zlsrj.wms.tenant.service.IIdService;
import com.zlsrj.wms.tenant.service.ITenantConfigService;

@Service
public class TenantConfigServiceImpl extends ServiceImpl<TenantConfigMapper, TenantConfig> implements ITenantConfigService {
	@Resource
	private IIdService idService;

	public boolean saveByTenantInfo(TenantInfo tenantInfo) {
		TenantConfig tenantConfig = TenantConfig.builder()//
				.id(idService.selectId())// 租户编号
				.tenantId(tenantInfo.getId())// 租户编号
				.partChargeOn(null)// 是否启用部分缴费（1：启用；0：不启用）
				.overDuefineOn(null)// 是否启用违约金（1：启用；0：不启用）
				.overDuefineDay(null)// 违约金宽限天数
				.overDuefineRatio(BigDecimal.ZERO)// 违约金每天收取比例
				.overDuefineTopRatio(BigDecimal.ZERO)// 违约金封顶比例（与欠费金额相比）
				.ycdkType(null)// 预存抵扣方式（1：抄表后即时抵扣；2：人工发起抵扣）
				.build();

		return super.save(tenantConfig);
	}
	
}
