package com.zlsrj.wms.saas.service.impl;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import java.math.BigDecimal;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zlsrj.wms.api.entity.TenantCustomerMeterChangeLog;
import com.zlsrj.wms.api.entity.TenantInfo;

import com.zlsrj.wms.saas.mapper.TenantCustomerMeterChangeLogMapper;
import com.zlsrj.wms.saas.service.IIdService;
import com.zlsrj.wms.saas.service.ITenantCustomerMeterChangeLogService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class TenantCustomerMeterChangeLogServiceImpl extends ServiceImpl<TenantCustomerMeterChangeLogMapper, TenantCustomerMeterChangeLog> implements ITenantCustomerMeterChangeLogService {
	@Resource
	private IIdService idService;

}
