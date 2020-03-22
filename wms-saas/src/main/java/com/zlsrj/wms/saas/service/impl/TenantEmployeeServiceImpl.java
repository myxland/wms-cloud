package com.zlsrj.wms.saas.service.impl;

import static java.util.stream.Collectors.toCollection;

import java.io.Serializable;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Stream;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zlsrj.wms.api.dto.TenantEmployeeAddParam;
import com.zlsrj.wms.api.dto.TenantEmployeeBatchUpdateParam;
import com.zlsrj.wms.api.dto.TenantEmployeeUpdateParam;
import com.zlsrj.wms.api.dto.TenantRoleAddParam;
import com.zlsrj.wms.api.dto.TenantRoleUpdateParam;
import com.zlsrj.wms.api.entity.TenantEmployee;
import com.zlsrj.wms.api.entity.TenantEmployeeRole;
import com.zlsrj.wms.api.entity.TenantInfo;
import com.zlsrj.wms.common.annotation.DictionaryDescription;
import com.zlsrj.wms.common.annotation.DictionaryOrder;
import com.zlsrj.wms.common.annotation.DictionaryText;
import com.zlsrj.wms.common.annotation.DictionaryValue;
import com.zlsrj.wms.saas.mapper.TenantEmployeeMapper;
import com.zlsrj.wms.saas.mapper.TenantEmployeeRoleMapper;
import com.zlsrj.wms.saas.service.IIdService;
import com.zlsrj.wms.saas.service.ITenantEmployeeService;
import com.zlsrj.wms.saas.service.RedisService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class TenantEmployeeServiceImpl extends ServiceImpl<TenantEmployeeMapper, TenantEmployee>
		implements ITenantEmployeeService {
	@Resource
	private IIdService idService;
	
	@Autowired
	private RedisService<String, String> redisService;

	@Resource
	private TenantEmployeeRoleMapper tenantEmployeeRoleMapper;
	
	@Override
	public boolean saveBatchByTenantInfo(TenantInfo tenantInfo) {
		return false;
	}

	@Override
	@Transactional
	public String save(TenantEmployeeAddParam tenantEmployeeAddParam) {
		String jsonString = JSON.toJSONString(tenantEmployeeAddParam);
		TenantEmployee tenantEmployee = JSON.parseObject(jsonString, TenantEmployee.class);
		if (tenantEmployee.getId() == null || tenantEmployee.getId().trim().length() == 0) {
			tenantEmployee.setId(idService.selectId());
		}
		this.save(tenantEmployee);

		QueryWrapper<TenantEmployeeRole> wrapperTenantEmployeeRole = new QueryWrapper<TenantEmployeeRole>();
		wrapperTenantEmployeeRole.lambda()//
				.eq(TenantEmployeeRole::getTenantId, tenantEmployee.getTenantId())
				.eq(TenantEmployeeRole::getEmployeeId, tenantEmployee.getId());
		tenantEmployeeRoleMapper.delete(wrapperTenantEmployeeRole);

		List<TenantRoleAddParam> tenantRoleAddParamList = tenantEmployeeAddParam.getTenantRoleList();
		if (tenantRoleAddParamList != null && tenantRoleAddParamList.size() > 0) {
			for (TenantRoleAddParam tenantRoleAddParam : tenantRoleAddParamList) {
				TenantEmployeeRole tenantEmployeeRole = TenantEmployeeRole.builder()//
						.id(idService.selectId())//
						.tenantId(tenantEmployee.getTenantId())//
						.employeeId(tenantEmployee.getId())//
						.roleId(tenantRoleAddParam.getId())//
						.build();
				tenantEmployeeRoleMapper.insert(tenantEmployeeRole);
			}
		}else {
			log.info("新增员工未配角角色信息");
		}

		return tenantEmployee.getId();
	}
	
	@Override
	@Transactional
    public boolean removeById(Serializable id) {
		QueryWrapper<TenantEmployeeRole> wrapperTenantEmployeeRole = new QueryWrapper<TenantEmployeeRole>();
		wrapperTenantEmployeeRole.lambda()//
				.eq(TenantEmployeeRole::getEmployeeId, id);
		tenantEmployeeRoleMapper.delete(wrapperTenantEmployeeRole);
		
		boolean success = super.removeById(id);
    	if(success) {
        	try {
    			redisService.remove(id.toString());
    		} catch (Exception e) {
    			log.error("redis error", e);
    		}
        }
    	return success;
    }
	
	@Override
	@Transactional
    public boolean removeByIds(Collection<? extends Serializable> idList) {
		QueryWrapper<TenantEmployeeRole> wrapperTenantEmployeeRole = new QueryWrapper<TenantEmployeeRole>();
		wrapperTenantEmployeeRole.lambda()//
				.in(TenantEmployeeRole::getEmployeeId, idList);
		tenantEmployeeRoleMapper.delete(wrapperTenantEmployeeRole);
		
		boolean success = super.removeByIds(idList);
		if(success) {
        	try {
        		idList.forEach(id->redisService.remove(id.toString()));;
    		} catch (Exception e) {
    			log.error("redis error", e);
    		}
        }
    	return success;
    }
	
	@Override
	@Transactional
    public boolean updateById(String id,TenantEmployeeUpdateParam tenantEmployeeUpdateParam) {
		boolean success = false;
		String jsonString = JSON.toJSONString(tenantEmployeeUpdateParam);
		TenantEmployee tenantEmployee = JSON.parseObject(jsonString, TenantEmployee.class);
		tenantEmployee.setId(id);
		this.updateById(tenantEmployee);
		
		QueryWrapper<TenantEmployeeRole> wrapperTenantEmployeeRole = new QueryWrapper<TenantEmployeeRole>();
		wrapperTenantEmployeeRole.lambda()//
				.eq(TenantEmployeeRole::getTenantId, tenantEmployee.getTenantId())
				.eq(TenantEmployeeRole::getEmployeeId, tenantEmployee.getId());
		tenantEmployeeRoleMapper.delete(wrapperTenantEmployeeRole);

		List<TenantRoleUpdateParam> tenantRoleUpdateParamList = tenantEmployeeUpdateParam.getTenantRoleList();
		if (tenantRoleUpdateParamList != null && tenantRoleUpdateParamList.size() > 0) {
			for (TenantRoleUpdateParam tenantRoleUpdateParam : tenantRoleUpdateParamList) {
				TenantEmployeeRole tenantEmployeeRole = TenantEmployeeRole.builder()//
						.id(idService.selectId())//
						.tenantId(tenantEmployee.getTenantId())//
						.employeeId(tenantEmployee.getId())//
						.roleId(tenantRoleUpdateParam.getId())//
						.build();
				tenantEmployeeRoleMapper.insert(tenantEmployeeRole);
			}
		}else {
			log.info("更新员工未配角角色信息");
		}
		
		success = true;
		
		return success;
    }
	
	@Override
	@Transactional
	public boolean update(String id,TenantEmployeeUpdateParam tenantEmployeeUpdateParam) {
		boolean success = false;
		String jsonString = JSON.toJSONString(tenantEmployeeUpdateParam);
		TenantEmployee tenantEmployee = JSON.parseObject(jsonString, TenantEmployee.class);
		tenantEmployee.setId(id);
		
		TenantEmployee tenantEmployeeWhere = TenantEmployee.builder()//
				.id(id)//
				.build();
		UpdateWrapper<TenantEmployee> updateWrapperTenantEmployee = new UpdateWrapper<TenantEmployee>();
		updateWrapperTenantEmployee.setEntity(tenantEmployeeWhere);
		updateWrapperTenantEmployee.lambda()//
				//.eq(TenantEmployee::getId, id)
				// .set(tenantEmployee.getId() != null, TenantEmployee::getId, tenantEmployee.getId())
				.set(tenantEmployee.getTenantId() != null, TenantEmployee::getTenantId, tenantEmployee.getTenantId())
				.set(tenantEmployee.getEmployeeName() != null, TenantEmployee::getEmployeeName, tenantEmployee.getEmployeeName())
				.set(tenantEmployee.getEmployeePassword() != null, TenantEmployee::getEmployeePassword, tenantEmployee.getEmployeePassword())
				.set(tenantEmployee.getEmployeeDepartmentId() != null, TenantEmployee::getEmployeeDepartmentId, tenantEmployee.getEmployeeDepartmentId())
				.set(tenantEmployee.getEmployeeLoginOn() != null, TenantEmployee::getEmployeeLoginOn, tenantEmployee.getEmployeeLoginOn())
				.set(tenantEmployee.getEmployeeStatus() != null, TenantEmployee::getEmployeeStatus, tenantEmployee.getEmployeeStatus())
				.set(tenantEmployee.getEmployeeMobile() != null, TenantEmployee::getEmployeeMobile, tenantEmployee.getEmployeeMobile())
				.set(tenantEmployee.getEmployeeEmail() != null, TenantEmployee::getEmployeeEmail, tenantEmployee.getEmployeeEmail())
				.set(tenantEmployee.getEmployeePersonalWx() != null, TenantEmployee::getEmployeePersonalWx, tenantEmployee.getEmployeePersonalWx())
				.set(tenantEmployee.getEmployeeEnterpriceWx() != null, TenantEmployee::getEmployeeEnterpriceWx, tenantEmployee.getEmployeeEnterpriceWx())
				.set(tenantEmployee.getEmployeeDingding() != null, TenantEmployee::getEmployeeDingding, tenantEmployee.getEmployeeDingding())
				.set(tenantEmployee.getEmployeeCreateType() != null, TenantEmployee::getEmployeeCreateType, tenantEmployee.getEmployeeCreateType())
				;

		super.update(updateWrapperTenantEmployee);
		
		QueryWrapper<TenantEmployeeRole> wrapperTenantEmployeeRole = new QueryWrapper<TenantEmployeeRole>();
		wrapperTenantEmployeeRole.lambda()//
				.eq(TenantEmployeeRole::getTenantId, tenantEmployee.getTenantId())
				.eq(TenantEmployeeRole::getEmployeeId, tenantEmployee.getId());
		tenantEmployeeRoleMapper.delete(wrapperTenantEmployeeRole);

		List<TenantRoleUpdateParam> tenantRoleUpdateParamList = tenantEmployeeUpdateParam.getTenantRoleList();
		if (tenantRoleUpdateParamList != null && tenantRoleUpdateParamList.size() > 0) {
			for (TenantRoleUpdateParam tenantRoleUpdateParam : tenantRoleUpdateParamList) {
				TenantEmployeeRole tenantEmployeeRole = TenantEmployeeRole.builder()//
						.id(idService.selectId())//
						.tenantId(tenantEmployee.getTenantId())//
						.employeeId(tenantEmployee.getId())//
						.roleId(tenantRoleUpdateParam.getId())//
						.build();
				tenantEmployeeRoleMapper.insert(tenantEmployeeRole);
			}
		}else {
			log.info("更新员工未配角角色信息");
		}
		
		success = true;
		
		return success;
	}
	
	@Override
	@Transactional
	public boolean updateByIds(String[] ids,TenantEmployeeBatchUpdateParam tenantEmployeeBatchUpdateParam) {
		boolean success = false;
		String jsonString = JSON.toJSONString(tenantEmployeeBatchUpdateParam);
		TenantEmployee tenantEmployee = JSON.parseObject(jsonString, TenantEmployee.class);
		
		UpdateWrapper<TenantEmployee> updateWrapperTenantEmployee = new UpdateWrapper<TenantEmployee>();
		updateWrapperTenantEmployee.lambda()//
				.in(TenantEmployee::getId, Arrays.asList(ids))
				// .set(tenantEmployee.getId() != null, TenantEmployee::getId, tenantEmployee.getId())
				.set(tenantEmployee.getTenantId() != null, TenantEmployee::getTenantId, tenantEmployee.getTenantId())
				.set(tenantEmployee.getEmployeeName() != null, TenantEmployee::getEmployeeName, tenantEmployee.getEmployeeName())
				.set(tenantEmployee.getEmployeePassword() != null, TenantEmployee::getEmployeePassword, tenantEmployee.getEmployeePassword())
				.set(tenantEmployee.getEmployeeDepartmentId() != null, TenantEmployee::getEmployeeDepartmentId, tenantEmployee.getEmployeeDepartmentId())
				.set(tenantEmployee.getEmployeeLoginOn() != null, TenantEmployee::getEmployeeLoginOn, tenantEmployee.getEmployeeLoginOn())
				.set(tenantEmployee.getEmployeeStatus() != null, TenantEmployee::getEmployeeStatus, tenantEmployee.getEmployeeStatus())
				.set(tenantEmployee.getEmployeeMobile() != null, TenantEmployee::getEmployeeMobile, tenantEmployee.getEmployeeMobile())
				.set(tenantEmployee.getEmployeeEmail() != null, TenantEmployee::getEmployeeEmail, tenantEmployee.getEmployeeEmail())
				.set(tenantEmployee.getEmployeePersonalWx() != null, TenantEmployee::getEmployeePersonalWx, tenantEmployee.getEmployeePersonalWx())
				.set(tenantEmployee.getEmployeeEnterpriceWx() != null, TenantEmployee::getEmployeeEnterpriceWx, tenantEmployee.getEmployeeEnterpriceWx())
				.set(tenantEmployee.getEmployeeDingding() != null, TenantEmployee::getEmployeeDingding, tenantEmployee.getEmployeeDingding())
				.set(tenantEmployee.getEmployeeCreateType() != null, TenantEmployee::getEmployeeCreateType, tenantEmployee.getEmployeeCreateType())
				;

		success = super.update(updateWrapperTenantEmployee);
		
		if(success) {
        	try {
        		Arrays.asList(ids).forEach(id->redisService.remove(id.toString()));;
    		} catch (Exception e) {
    			log.error("redis error", e);
    		}
        }
		
		return success;
	}
	
	@Override
	public boolean updatePassword(String id,String password) {
		boolean success = false;
		
		TenantEmployee tenantEmployeeWhere = TenantEmployee.builder()//
				.id(id)//
				.build();
		UpdateWrapper<TenantEmployee> updateWrapperTenantEmployee = new UpdateWrapper<TenantEmployee>();
		updateWrapperTenantEmployee.setEntity(tenantEmployeeWhere);
		updateWrapperTenantEmployee.lambda()//
				//.eq(TenantEmployee::getId, id)
				// .set(tenantEmployee.getId() != null, TenantEmployee::getId, tenantEmployee.getId())
				.set(TenantEmployee::getEmployeePassword, password)
				;

		this.update(updateWrapperTenantEmployee);
		
		success = true;
		
		return success;
	}
	
	@Override
	public TenantEmployee getDictionaryById(Serializable id) {
		try {
			String entityJSONString = redisService.getValue(id.toString());
			if (StringUtils.isNotBlank(entityJSONString)) {
				TenantEmployee entity = JSONObject.parseObject(entityJSONString, TenantEmployee.class);
				return entity;
			}
		} catch (Exception e) {
			log.error("redis error", e);
		}
		
		List<String> fieldList = Stream.of(TenantEmployee.class.getDeclaredFields())
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

		QueryWrapper<TenantEmployee> queryWrapper = new QueryWrapper<>();
		queryWrapper//
				.lambda()//
				.select(TenantEmployee.class,//
						e -> fieldList.contains(e.getProperty()))//
				.eq(TenantEmployee::getId, id);
		TenantEmployee entity = this.getOne(queryWrapper);
		// TenantEmployee entity = this.getById(id);

		redisService.setValue(entity.getId(), JSON.toJSONString(entity));

		return entity;
	}
	
	@Override
    public boolean updateById(TenantEmployee entity) {
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
    public boolean update(Wrapper<TenantEmployee> updateWrapper) {
    	boolean success =  super.update(updateWrapper);
    	if(success) {
        	try {
        		TenantEmployee entity = updateWrapper.getEntity();
    			redisService.remove(entity.getId());
    		} catch (Exception e) {
    			log.error("redis error", e);
    		}
        }
    	return success;
    }
	
}
