package com.zlsrj.wms.saas.service.impl;
import static java.util.stream.Collectors.toCollection;

import java.io.Serializable;
import java.lang.reflect.Modifier;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Stream;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zlsrj.wms.api.dto.TenantDepartmentAddParam;
import com.zlsrj.wms.api.dto.TenantDepartmentUpdateParam;
import com.zlsrj.wms.api.entity.TenantDepartment;
import com.zlsrj.wms.api.entity.TenantInfo;
import com.zlsrj.wms.common.annotation.DictionaryDescription;
import com.zlsrj.wms.common.annotation.DictionaryOrder;
import com.zlsrj.wms.common.annotation.DictionaryText;
import com.zlsrj.wms.common.annotation.DictionaryValue;
import com.zlsrj.wms.saas.mapper.TenantDepartmentMapper;
import com.zlsrj.wms.saas.service.IIdService;
import com.zlsrj.wms.saas.service.ITenantDepartmentService;
import com.zlsrj.wms.saas.service.RedisService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class TenantDepartmentServiceImpl extends ServiceImpl<TenantDepartmentMapper, TenantDepartment> implements ITenantDepartmentService {
	@Resource
	private IIdService idService;
	@Autowired
	private RedisService<String, String> redisService;

	@Override
	public boolean saveBatchByTenantInfo(TenantInfo tenantInfo) {
		QueryWrapper<TenantDepartment> queryWrapperTenantDepartment = new QueryWrapper<TenantDepartment>();
		queryWrapperTenantDepartment.lambda()//
				.eq(TenantDepartment::getTenantId, tenantInfo.getId())//
		;
		int count = super.count(queryWrapperTenantDepartment);
		if (count > 0) {
			log.error("根据租户信息初始化部门信息失败，部门信息已存在。");
			return false;
		}
		
		TenantDepartment tenantDepartment = TenantDepartment.builder()//
				.id(idService.selectId())//
				.tenantId(tenantInfo.getId())//
				.departmentName(tenantInfo.getTenantName())//
				.departmentParentId(null)//
				.build();
		boolean success = this.save(tenantDepartment);

		return success;
	}
	
	@Override
	public boolean updateById(TenantDepartmentUpdateParam tenantDepartmentUpdateParam) {
		String jsonString = JSON.toJSONString(tenantDepartmentUpdateParam);
		TenantDepartment tenantDepartment = JSON.parseObject(jsonString, TenantDepartment.class);
		
		return this.updateById(tenantDepartment);
	}
	
	@Override
	public String save(TenantDepartmentAddParam tenantDepartmentAddParam) {
		String jsonString = JSON.toJSONString(tenantDepartmentAddParam);
		TenantDepartment tenantDepartment = JSON.parseObject(jsonString, TenantDepartment.class);
		
		if (tenantDepartment.getId() == null || tenantDepartment.getId().trim().length() == 0) {
			tenantDepartment.setId(idService.selectId());
		}
		
		this.save(tenantDepartment);
		
		return tenantDepartment.getId();
	}
	
	@Override
	public TenantDepartment getDictionaryById(Serializable id) {
		try {
			String entityJSONString = redisService.getValue(id.toString());
			if (StringUtils.isNotBlank(entityJSONString)) {
				TenantDepartment entity = JSONObject.parseObject(entityJSONString, TenantDepartment.class);
				return entity;
			}
		} catch (Exception e) {
			log.error("redis error", e);
		}
		
		List<String> fieldList = Stream.of(TenantDepartment.class.getDeclaredFields())
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

		QueryWrapper<TenantDepartment> queryWrapper = new QueryWrapper<>();
		queryWrapper//
				.lambda()//
				.select(TenantDepartment.class,//
						e -> fieldList.contains(e.getProperty()))//
				.eq(TenantDepartment::getId, id);
		TenantDepartment entity = this.getOne(queryWrapper);
		// TenantDepartment entity = this.getById(id);

		redisService.setValue(entity.getId(), JSON.toJSONString(entity));

		return entity;
	}
	
	@Override
    public boolean updateById(TenantDepartment entity) {
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
    public boolean update(Wrapper<TenantDepartment> updateWrapper) {
    	boolean success =  super.update(updateWrapper);
    	if(success) {
        	try {
        		TenantDepartment entity = updateWrapper.getEntity();
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
