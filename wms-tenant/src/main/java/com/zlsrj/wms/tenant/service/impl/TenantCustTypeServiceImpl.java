package com.zlsrj.wms.tenant.service.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zlsrj.wms.api.entity.TenantCustType;
import com.zlsrj.wms.api.entity.TenantInfo;
import com.zlsrj.wms.api.enums.CustTypeEnum;
import com.zlsrj.wms.tenant.mapper.TenantCustTypeMapper;
import com.zlsrj.wms.tenant.service.IIdService;
import com.zlsrj.wms.tenant.service.ITenantCustTypeService;
import com.zlsrj.wms.tenant.service.RedisService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class TenantCustTypeServiceImpl extends ServiceImpl<TenantCustTypeMapper, TenantCustType>
		implements ITenantCustTypeService {
	@Resource
	private RedisService<String, String> redisService;
	
	@Resource
	private IIdService idService;

	@Override
	public TenantCustType getById(Serializable id) {
		try {
			String jsonString = redisService.getValue(id.toString());
			if (StringUtils.isNotEmpty(jsonString)) {
				TenantCustType tenantCustType = JSON.parseObject(jsonString, TenantCustType.class);
				return tenantCustType;
			}
		} catch (Exception e) {
			// ex.printStackTrace();
			log.error("redis get value error", e);
		}

		TenantCustType tenantCustType = baseMapper.selectById(id);
		if (tenantCustType != null) {
			redisService.setValue(id.toString(), JSON.toJSONString(tenantCustType));
		}

		return tenantCustType;
	}

	@Override
	public boolean update(TenantCustType entity, Wrapper<TenantCustType> updateWrapper) {
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

	@Override
	public boolean saveBatchByTenantInfo(TenantInfo tenantInfo) {
		QueryWrapper<TenantCustType> queryWrapperTenantCustType = new QueryWrapper<TenantCustType>();
		queryWrapperTenantCustType.lambda()//
				.eq(TenantCustType::getTenantId, tenantInfo.getId())//
		;
		int count = super.count(queryWrapperTenantCustType);
		if (count > 0) {
			log.error("根据租户信息初始化用户类型失败，租户类型已存在。");
			return false;
		}

		List<TenantCustType> tenantCustTypeList = new ArrayList<TenantCustType>();
		for (CustTypeEnum custTypeEnum : CustTypeEnum.values()) {
			TenantCustType tenantCustType = TenantCustType.builder()//
					.id(idService.selectId())//
					.tenantId(tenantInfo.getId())//
					.custTypeName(custTypeEnum.getText())//
					.build();
			log.info(tenantCustType.toString());
			tenantCustTypeList.add(tenantCustType);
			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		log.info(tenantCustTypeList.toString());
		return super.saveBatch(tenantCustTypeList);
	}

}
