package com.zlsrj.wms.employee.service.impl;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zlsrj.wms.api.entity.TenantDept;
import com.zlsrj.wms.api.entity.TenantEmployee;
import com.zlsrj.wms.api.entity.TenantInfo;
import com.zlsrj.wms.api.enums.EmployeeEnum;
import com.zlsrj.wms.employee.mapper.TenantEmployeeMapper;
import com.zlsrj.wms.employee.service.IIdService;
import com.zlsrj.wms.employee.service.ITenantDeptService;
import com.zlsrj.wms.employee.service.ITenantEmployeeService;
import com.zlsrj.wms.employee.service.RedisService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class TenantEmployeeServiceImpl extends ServiceImpl<TenantEmployeeMapper, TenantEmployee> implements ITenantEmployeeService {
	@Resource
	private RedisService<String, String> redisService;
	@Resource
	private IIdService idService;
	
	@Resource
	private ITenantDeptService tenantDeptService;

	@Override
	public TenantEmployee getById(Serializable id) {
		try {
			String jsonString = redisService.getValue(id.toString());
			if (StringUtils.isNotEmpty(jsonString)) {
				TenantEmployee tenantEmployee = JSON.parseObject(jsonString, TenantEmployee.class);
				return tenantEmployee;
			}
		} catch (Exception e) {
			// ex.printStackTrace();
			log.error("redis get value error", e);
		}

		TenantEmployee tenantEmployee = baseMapper.selectById(id);
		if (tenantEmployee != null) {
			redisService.setValue(id.toString(), JSON.toJSONString(tenantEmployee));
		}

		return tenantEmployee;
	}

	@Override
	public boolean update(TenantEmployee entity, Wrapper<TenantEmployee> updateWrapper) {
		boolean success = super.update(entity, updateWrapper);
		if (success) {
			try {
				Long id = updateWrapper.getEntity().getId();
				redisService.remove(Long.toString(id));
			} catch(Exception e) {
				//ex.printStackTrace();
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
			} catch(Exception e) {
				//ex.printStackTrace();
				log.error("redis remove error", e);
			}
		}
		return success;
	}
	
	@Override
	public boolean saveBatchByTenantInfo(TenantInfo tenantInfo) {
		QueryWrapper<TenantEmployee> queryWrapperTenantEmployee = new QueryWrapper<TenantEmployee>();
		queryWrapperTenantEmployee.lambda()//
				.eq(TenantEmployee::getTenantId, tenantInfo.getId())//
		;
		int count = super.count(queryWrapperTenantEmployee);
		if (count > 0) {
			log.error("根据租户信息初始化租户员工失败，租户类型已存在。");
			return false;
		}
		
		QueryWrapper<TenantDept> queryWrapperTenantDept = new QueryWrapper<TenantDept>();
		queryWrapperTenantDept.lambda()//
				.eq(TenantDept::getTenantId, tenantInfo.getId())//
				.isNull(TenantDept::getParentDeptId)
		;
		TenantDept tenantDept = tenantDeptService.getOne(queryWrapperTenantDept);

		List<TenantEmployee> tenantEmployeeList = new ArrayList<TenantEmployee>();
		for (EmployeeEnum employeeEnum : EmployeeEnum.values()) {
			TenantEmployee tenantEmployee = TenantEmployee.builder()//
					.id(idService.selectId())// 系统ID
					.tenantId(tenantInfo.getId())// 租户编号
					.build();
			log.info(tenantEmployee.toString());
			tenantEmployeeList.add(tenantEmployee);
			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		log.info(tenantEmployeeList.toString());
		return super.saveBatch(tenantEmployeeList);
	}

}
