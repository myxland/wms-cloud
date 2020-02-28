package com.zlsrj.wms.saas.service.impl;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zlsrj.wms.api.entity.TenantConfigWx;

import com.zlsrj.wms.saas.mapper.TenantConfigWxMapper;
import com.zlsrj.wms.saas.service.ITenantConfigWxService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class TenantConfigWxServiceImpl extends ServiceImpl<TenantConfigWxMapper, TenantConfigWx> implements ITenantConfigWxService {

	@Override
	public TenantConfigWx getAggregation(Wrapper<TenantConfigWx> wrapper) {
		return baseMapper.selectAggregation(wrapper);
	}
	
}
