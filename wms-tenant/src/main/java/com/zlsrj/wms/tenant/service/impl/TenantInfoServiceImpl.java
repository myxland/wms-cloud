package com.zlsrj.wms.tenant.service.impl;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zlsrj.wms.api.entity.TenantInfo;
import com.zlsrj.wms.tenant.mapper.TenantInfoMapper;
import com.zlsrj.wms.tenant.service.ITenantAccountService;
import com.zlsrj.wms.tenant.service.ITenantBillService;
import com.zlsrj.wms.tenant.service.ITenantConfigService;
import com.zlsrj.wms.tenant.service.ITenantCustTypeService;
import com.zlsrj.wms.tenant.service.ITenantInfoService;
import com.zlsrj.wms.tenant.service.ITenantInvoiceService;
import com.zlsrj.wms.tenant.service.ITenantSmsService;
import com.zlsrj.wms.tenant.service.ITenantWaterTypeService;
import com.zlsrj.wms.tenant.service.RedisService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class TenantInfoServiceImpl extends ServiceImpl<TenantInfoMapper, TenantInfo> implements ITenantInfoService {

	@Autowired
	private RedisService<String, String> redisService;

	@Autowired
	private ITenantConfigService tenantConfigService;
	
	@Autowired
	private ITenantAccountService tenantAccountService;
	
	@Autowired
	private ITenantSmsService tenantSmsService;
	
	@Autowired
	private ITenantInvoiceService tenantInvoiceService;
	
	@Autowired
	private ITenantBillService tenantBillService;
	
	@Autowired
	private ITenantCustTypeService tenantCustTypeService;
	
	@Autowired
	private ITenantWaterTypeService tenantWaterTypeService;

	@Override
	public boolean save(TenantInfo tenantInfo) {
		boolean success = super.save(tenantInfo);
		// 创建租户时，默认创建租户相关配置信息
		// 1，同微服务默认配置信息，service调用
		// t_op_tenant_config
		try {
			tenantConfigService.saveByTenantInfo(tenantInfo);
		} catch (Exception e) {
			log.error("创建默认租户基础配置出错", e);
		}
		// t_op_tenant_account
		try {
			tenantAccountService.saveByTenantInfo(tenantInfo);
		} catch (Exception e) {
			log.error("创建默认租户账户出错", e);
		}
		// t_op_tenant_sms
		try {
			tenantSmsService.saveByTenantInfo(tenantInfo);
		} catch (Exception e) {
			log.error("创建默认租户短信配置出错", e);
		}
		// t_op_tenant_invoice
		try {
			tenantInvoiceService.saveByTenantInfo(tenantInfo);
		} catch (Exception e) {
			log.error("创建默认租户发票配置出错", e);
		}
		// t_op_tenant_bill
		try {
			tenantBillService.saveByTenantInfo(tenantInfo);
		} catch (Exception e) {
			log.error("创建默认租户发票配置出错", e);
		}
		
		try {
			tenantCustTypeService.saveBatchByTenantInfo(tenantInfo);
		} catch (Exception e) {
			log.error("创建默认租户用户类型出错", e);
		}
		
		try {
			tenantWaterTypeService.saveBatchByTenantInfo(tenantInfo);
		} catch (Exception e) {
			log.error("创建默认租户用水类型出错", e);
		}

		// 2,不同服务默认配置信息，mq消息通知
		return success;
	}

	@Override
	public TenantInfo getById(Serializable id) {
		try {
			String jsonString = redisService.getValue(id.toString());
			if (StringUtils.isNotEmpty(jsonString)) {
				TenantInfo tenantInfo = JSON.parseObject(jsonString, TenantInfo.class);
				return tenantInfo;
			}
		} catch (Exception e) {
			// ex.printStackTrace();
			log.error("redis get value error", e);
		}

		TenantInfo tenantInfo = baseMapper.selectById(id);
		if (tenantInfo != null) {
			redisService.setValue(id.toString(), JSON.toJSONString(tenantInfo));
		}

		return tenantInfo;
	}

	@Override
	public boolean update(TenantInfo entity, Wrapper<TenantInfo> updateWrapper) {
		boolean success = super.update(entity, updateWrapper);
		if (success) {
			try {
				Long id = updateWrapper.getEntity().getId();
				redisService.remove(Long.toString(id));
			} catch (Exception e) {
				// ex.printStackTrace();
				log.error("redis remove error", e);
			}
		}
		return success;
	}

	@Override
	public boolean removeById(Serializable id) {
		boolean success = super.removeById(id);
		if (success) {
			try {
				redisService.remove(id.toString());
			} catch (Exception e) {
				// ex.printStackTrace();
				log.error("redis remove error", e);
			}
		}
		return success;
	}
}
