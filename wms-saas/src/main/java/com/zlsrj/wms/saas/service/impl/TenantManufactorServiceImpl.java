package com.zlsrj.wms.saas.service.impl;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zlsrj.wms.api.entity.TenantManufactor;
import com.zlsrj.wms.api.entity.TenantInfo;

import com.zlsrj.wms.saas.mapper.TenantManufactorMapper;
import com.zlsrj.wms.saas.service.IIdService;
import com.zlsrj.wms.saas.service.ITenantManufactorService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class TenantManufactorServiceImpl extends ServiceImpl<TenantManufactorMapper, TenantManufactor> implements ITenantManufactorService {
	@Resource
	private IIdService idService;

}
