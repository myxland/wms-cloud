package com.zlsrj.wms.saas.service.impl;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zlsrj.wms.api.entity.TenantCustomerLinkman;
import com.zlsrj.wms.api.entity.TenantInfo;

import com.zlsrj.wms.saas.mapper.TenantCustomerLinkmanMapper;
import com.zlsrj.wms.saas.service.IIdService;
import com.zlsrj.wms.saas.service.ITenantCustomerLinkmanService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class TenantCustomerLinkmanServiceImpl extends ServiceImpl<TenantCustomerLinkmanMapper, TenantCustomerLinkman> implements ITenantCustomerLinkmanService {
	@Resource
	private IIdService idService;

}
