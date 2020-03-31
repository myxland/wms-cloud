package com.zlsrj.wms.saas.mapper;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.zlsrj.wms.api.entity.TenantCustomer;

public interface TenantCustomerMapper extends BaseMapper<TenantCustomer> {
	TenantCustomer selectAggregation (@Param(Constants.WRAPPER) Wrapper<TenantCustomer> wrapper);
}
