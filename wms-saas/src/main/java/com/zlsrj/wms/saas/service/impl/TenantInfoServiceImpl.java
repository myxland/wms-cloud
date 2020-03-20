package com.zlsrj.wms.saas.service.impl;

import static java.util.stream.Collectors.toCollection;

import java.io.Serializable;
import java.lang.reflect.Modifier;
import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Stream;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zlsrj.wms.api.entity.TenantInfo;
import com.zlsrj.wms.common.annotation.DictionaryDescription;
import com.zlsrj.wms.common.annotation.DictionaryOrder;
import com.zlsrj.wms.common.annotation.DictionaryText;
import com.zlsrj.wms.common.annotation.DictionaryValue;
import com.zlsrj.wms.saas.mapper.TenantInfoMapper;
import com.zlsrj.wms.saas.service.ITenantInfoService;
import com.zlsrj.wms.saas.service.RedisService;

import cn.hutool.core.date.DateTime;

@Service
public class TenantInfoServiceImpl extends ServiceImpl<TenantInfoMapper, TenantInfo> implements ITenantInfoService {
	@Autowired
	private RedisService<String, String> redisService;

	@Override
	public boolean save(TenantInfo tenantInfo) {
		// 账户余额
		if (tenantInfo.getTenantBalance() == null) {
			tenantInfo.setTenantBalance(BigDecimal.ZERO);
		}
		// 注册时间
		if (tenantInfo.getTenantRegisterTime() == null) {
			tenantInfo.setTenantRegisterTime(new DateTime());
		}

		return super.save(tenantInfo);
	}

	@Override
	public TenantInfo getDictionaryById(Serializable id) {
		try {
			String entityJSONString = redisService.getValue(id.toString());
			if (StringUtils.isNotBlank(entityJSONString)) {
				TenantInfo entity = JSONObject.parseObject(entityJSONString, TenantInfo.class);
				return entity;
			}
		} catch (Exception e) {
			log.error("redis error", e);
		}
		
		List<String> fieldList = Stream.of(TenantInfo.class.getDeclaredFields())
                /* 过滤静态属性 */
                .filter(field -> !Modifier.isStatic(field.getModifiers()))
                /* 过滤 transient关键字修饰的属性 */
                .filter(field -> !Modifier.isTransient(field.getModifiers()))
                .filter(
                		field -> field.isAnnotationPresent(DictionaryValue.class)//
						|| field.isAnnotationPresent(DictionaryText.class)//
						|| field.isAnnotationPresent(DictionaryOrder.class)//
						|| field.isAnnotationPresent(DictionaryDescription.class)//
                		)
                .map(e->e.getName())
                .collect(toCollection(LinkedList::new));

		QueryWrapper<TenantInfo> queryWrapper = new QueryWrapper<>();
		queryWrapper//
				.lambda()//
				.select(TenantInfo.class,//
						e -> fieldList.contains(e.getProperty()))//
				.eq(TenantInfo::getId, id);
		TenantInfo entity = this.getOne(queryWrapper);
		// TenantInfo entity = this.getById(id);

		redisService.setValue(entity.getId(), JSON.toJSONString(entity));

		return entity;
	}
	
	@Override
    public boolean updateById(TenantInfo entity) {
        boolean success = super.updateById(entity);
        if(success) {
        	try {
    			redisService.remove(entity.getId());
    		} catch (Exception e) {
    			log.error("redis error", e);
    		}
        }
        
        return success;
    }

    @Override
    public boolean update(Wrapper<TenantInfo> updateWrapper) {
    	boolean success =  super.update(updateWrapper);
    	if(success) {
        	try {
        		TenantInfo entity = updateWrapper.getEntity();
    			redisService.remove(entity.getId());
    		} catch (Exception e) {
    			log.error("redis error", e);
    		}
        }
    	return success;
    }
    
    @Override
    public boolean removeById(Serializable id) {
    	boolean success =  super.removeById(id);
    	if(success) {
        	try {
    			redisService.remove(id.toString());
    		} catch (Exception e) {
    			log.error("redis error", e);
    		}
        }
    	return success;
    }
}
