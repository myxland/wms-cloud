package com.zlsrj.wms.saas.service.impl;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import java.math.BigDecimal;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zlsrj.wms.api.entity.TenantReceivable;
import com.zlsrj.wms.api.entity.TenantInfo;

import com.zlsrj.wms.saas.mapper.TenantReceivableMapper;
import com.zlsrj.wms.saas.service.IIdService;
import com.zlsrj.wms.saas.service.ITenantReceivableService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class TenantReceivableServiceImpl extends ServiceImpl<TenantReceivableMapper, TenantReceivable> implements ITenantReceivableService {
	@Resource
	private IIdService idService;

}
