package com.zlsrj.wms.saas.service.impl;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import java.math.BigDecimal;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zlsrj.wms.api.entity.TenantMeterReadLogCurrent;
import com.zlsrj.wms.api.entity.TenantInfo;

import com.zlsrj.wms.saas.mapper.TenantMeterReadLogCurrentMapper;
import com.zlsrj.wms.saas.service.IIdService;
import com.zlsrj.wms.saas.service.ITenantMeterReadLogCurrentService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class TenantMeterReadLogCurrentServiceImpl extends ServiceImpl<TenantMeterReadLogCurrentMapper, TenantMeterReadLogCurrent> implements ITenantMeterReadLogCurrentService {
	@Resource
	private IIdService idService;

}
