package com.zlsrj.wms.saas.service.impl;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zlsrj.wms.api.dto.TenantCustomerAddParam;
import com.zlsrj.wms.api.dto.TenantCustomerUpdateParam;
import com.zlsrj.wms.api.entity.TenantCustomer;
import com.zlsrj.wms.api.entity.TenantInfo;
import com.zlsrj.wms.common.util.TranslateUtil;
import com.zlsrj.wms.saas.config.CodeConfig;
import com.zlsrj.wms.saas.mapper.TenantCustomerMapper;
import com.zlsrj.wms.saas.mapper.TenantInfoMapper;
import com.zlsrj.wms.saas.service.IIdService;
import com.zlsrj.wms.saas.service.ITenantCustomerService;
import com.zlsrj.wms.saas.service.RedisService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class TenantCustomerServiceImpl extends ServiceImpl<TenantCustomerMapper, TenantCustomer> implements ITenantCustomerService {
	@Resource
	private IIdService idService;
	@Resource
	private RedisService<String, String> redisService;
	@Resource
	private CodeConfig codeConfig;
	@Resource
	private TenantInfoMapper tenantInfoMapper;

	@Override
	public TenantCustomer getAggregation(Wrapper<TenantCustomer> wrapper) {
		return baseMapper.selectAggregation(wrapper);
	}
	
	@Override
	public String save(TenantCustomerAddParam tenantCustomerAddParam) {
		TenantCustomer tenantCustomer = TranslateUtil.translate(tenantCustomerAddParam,
				TenantCustomer.class);
		if (tenantCustomer != null && StringUtils.isBlank(tenantCustomer.getId())) {
			tenantCustomer.setId(idService.selectId());
		}
		
		//用户编号
		TenantInfo tenantInfo = tenantInfoMapper.selectById(tenantCustomer.getTenantId());
		Integer tenantCode = tenantInfo.getTenantCode();
		String key = codeConfig.getCustomerCode()+tenantCode;
		Long value = redisService.increment(key);
		tenantCustomer.setCustomerCode(Long.toString(value));
		
		this.save(tenantCustomer);

		return tenantCustomer.getId();
	}

	@Override
	public boolean updateById(TenantCustomerUpdateParam tenantCustomerUpdateParam) {
		TenantCustomer tenantCustomer = TranslateUtil.translate(tenantCustomerUpdateParam,
				TenantCustomer.class);

		return this.updateById(tenantCustomer);
	}
	
	@Override
	public List<TenantCustomer> getMaxCustomerCode() {
		QueryWrapper<TenantCustomer> queryWrapperTenantCustomer = new QueryWrapper<TenantCustomer>();
		queryWrapperTenantCustomer//
				.select("tenant_id,max(customer_code) as customer_code")//
				.groupBy("tenant_id")//
				.orderByAsc("tenant_id")//
		;
		List<TenantCustomer> tenantCustomerList = this.list(queryWrapperTenantCustomer);

		return tenantCustomerList;
	}
}
