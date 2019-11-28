package com.zlsrj.wms.employee.service.impl;
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
import com.zlsrj.wms.api.entity.TenantDept;
import com.zlsrj.wms.api.entity.TenantInfo;
import com.zlsrj.wms.api.enums.DeptEnum;
import com.zlsrj.wms.employee.mapper.TenantDeptMapper;
import com.zlsrj.wms.employee.service.IIdService;
import com.zlsrj.wms.employee.service.ITenantDeptService;
import com.zlsrj.wms.employee.service.RedisService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class TenantDeptServiceImpl extends ServiceImpl<TenantDeptMapper, TenantDept> implements ITenantDeptService {
	@Resource
	private RedisService<String, String> redisService;
	@Resource
	private IIdService idService;

	@Override
	public TenantDept getById(Serializable id) {
		try {
			String jsonString = redisService.getValue(id.toString());
			if (StringUtils.isNotEmpty(jsonString)) {
				TenantDept tenantDept = JSON.parseObject(jsonString, TenantDept.class);
				return tenantDept;
			}
		} catch (Exception e) {
			// ex.printStackTrace();
			log.error("redis get value error", e);
		}

		TenantDept tenantDept = baseMapper.selectById(id);
		if (tenantDept != null) {
			redisService.setValue(id.toString(), JSON.toJSONString(tenantDept));
		}

		return tenantDept;
	}

	@Override
	public boolean update(TenantDept entity, Wrapper<TenantDept> updateWrapper) {
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
		QueryWrapper<TenantDept> queryWrapperTenantDept = new QueryWrapper<TenantDept>();
		queryWrapperTenantDept.lambda()//
				.eq(TenantDept::getTenantId, tenantInfo.getId())//
		;
		int count = super.count(queryWrapperTenantDept);
		if (count > 0) {
			log.error("根据租户信息初始化租户部门失败，租户类型已存在。");
			return false;
		}

		List<TenantDept> tenantDeptList = new ArrayList<TenantDept>();
		for (DeptEnum deptEnum : DeptEnum.values()) {
			TenantDept tenantDept = TenantDept.builder()//
					.id(idService.selectId())// 系统ID
					.parentDeptId(null)// 上级部门
					.tenantId(tenantInfo.getId())// 租户编号
					.deptName(deptEnum.getText())// 部门名称
					.build();
			log.info(tenantDept.toString());
			tenantDeptList.add(tenantDept);
			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		log.info(tenantDeptList.toString());
		return super.saveBatch(tenantDeptList);
	}

}
