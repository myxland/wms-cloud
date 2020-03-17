package com.zlsrj.wms.saas.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zlsrj.wms.api.dto.TenantMeterReadSituationAddParam;
import com.zlsrj.wms.api.dto.TenantMeterReadSituationUpdateParam;
import com.zlsrj.wms.api.entity.TenantMeterReadSituation;

public interface ITenantMeterReadSituationService extends IService<TenantMeterReadSituation> {
	String save(TenantMeterReadSituationAddParam tenantCustomerTypeAddParam);

	boolean updateById(TenantMeterReadSituationUpdateParam tenantCustomerTypeUpdateParam);

}