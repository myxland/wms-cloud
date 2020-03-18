package com.zlsrj.wms.saas.mapper;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.zlsrj.wms.api.entity.TenantPriceItem;

public interface TenantPriceItemMapper extends BaseMapper<TenantPriceItem> {
	TenantPriceItem selectAggregation (@Param(Constants.WRAPPER) Wrapper<TenantPriceItem> wrapper);
}
