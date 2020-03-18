package com.zlsrj.wms.saas.mapper;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.zlsrj.wms.api.entity.TenantPrice;

public interface TenantPriceMapper extends BaseMapper<TenantPrice> {
	TenantPrice selectAggregation (@Param(Constants.WRAPPER) Wrapper<TenantPrice> wrapper);
}
