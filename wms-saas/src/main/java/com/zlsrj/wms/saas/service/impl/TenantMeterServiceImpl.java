package com.zlsrj.wms.saas.service.impl;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zlsrj.wms.api.dto.TenantMeterAddParam;
import com.zlsrj.wms.api.dto.TenantMeterBatchUpdateParam;
import com.zlsrj.wms.api.dto.TenantMeterUpdateParam;
import com.zlsrj.wms.api.entity.TenantInfo;
import com.zlsrj.wms.api.entity.TenantMeter;
import com.zlsrj.wms.common.util.TranslateUtil;
import com.zlsrj.wms.saas.config.CodeConfig;
import com.zlsrj.wms.saas.mapper.TenantInfoMapper;
import com.zlsrj.wms.saas.mapper.TenantMeterMapper;
import com.zlsrj.wms.saas.service.IIdService;
import com.zlsrj.wms.saas.service.ITenantMeterService;
import com.zlsrj.wms.saas.service.RedisService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class TenantMeterServiceImpl extends ServiceImpl<TenantMeterMapper, TenantMeter> implements ITenantMeterService {
	@Resource
	private IIdService idService;
	@Resource
	private RedisService<String, String> redisService;
	@Resource
	private CodeConfig codeConfig;
	@Resource
	private TenantInfoMapper tenantInfoMapper;

	@Override
	public TenantMeter getAggregation(Wrapper<TenantMeter> wrapper) {
		return baseMapper.selectAggregation(wrapper);
	}
	
	@Override
	public String save(TenantMeterAddParam tenantMeterAddParam) {
		TenantMeter tenantMeter = TranslateUtil.translate(tenantMeterAddParam,
				TenantMeter.class);
		if (tenantMeter != null && StringUtils.isBlank(tenantMeter.getId())) {
			tenantMeter.setId(idService.selectId());
		}
		
		//水表编号
		TenantInfo tenantInfo = tenantInfoMapper.selectById(tenantMeter.getTenantId());
		Integer tenantCode = tenantInfo.getTenantCode();
		String key = codeConfig.getMeterCode()+tenantCode;
		Long value = redisService.increment(key);
		tenantMeter.setMeterCode(Long.toString(value));
		
		this.save(tenantMeter);

		return tenantMeter.getId();
	}

	@Override
	public boolean updateById(TenantMeterUpdateParam tenantMeterUpdateParam) {
		TenantMeter tenantMeter = TranslateUtil.translate(tenantMeterUpdateParam,
				TenantMeter.class);

		return this.updateById(tenantMeter);
	}
	
	@Override
	@Transactional
	public boolean updateByIds(TenantMeterBatchUpdateParam tenantMeterBatchUpdateParam) {
		boolean success = false;
		String[] id = tenantMeterBatchUpdateParam.getId();
		if(id!=null && id.length>0) {
			for(int i=0;i<id.length;i++) {
				TenantMeter tenantMeter = TenantMeter.builder()
						.id(id[i])
						.meterBookId(tenantMeterBatchUpdateParam.getMeterBookId()[i])
						.meterReadOrder(tenantMeterBatchUpdateParam.getMeterReadOrder()[i])
						.build();
				 this.updateById(tenantMeter);
			}
			
		}
		success = true;
		return success;
	}
	
	@Override
	public List<TenantMeter> getMaxMeterCode() {
		QueryWrapper<TenantMeter> queryWrapperTenantMeter = new QueryWrapper<TenantMeter>();
		queryWrapperTenantMeter//
				.select("tenant_id,max(meter_code) as meter_code")//
				.groupBy("tenant_id")//
				.orderByAsc("tenant_id")//
		;
		List<TenantMeter> tenantMeterList = this.list(queryWrapperTenantMeter);

		return tenantMeterList;
	}
}
