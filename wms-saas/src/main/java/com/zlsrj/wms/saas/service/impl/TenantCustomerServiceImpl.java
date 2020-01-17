package com.zlsrj.wms.saas.service.impl;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import java.math.BigDecimal;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zlsrj.wms.api.entity.TenantCustomer;
import com.zlsrj.wms.api.entity.TenantInfo;

import com.zlsrj.wms.saas.mapper.TenantCustomerMapper;
import com.zlsrj.wms.saas.service.IIdService;
import com.zlsrj.wms.saas.service.ITenantCustomerService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class TenantCustomerServiceImpl extends ServiceImpl<TenantCustomerMapper, TenantCustomer> implements ITenantCustomerService {
	@Resource
	private IIdService idService;

}
