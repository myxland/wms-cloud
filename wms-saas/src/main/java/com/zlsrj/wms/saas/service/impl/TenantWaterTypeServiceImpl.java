package com.zlsrj.wms.saas.service.impl;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zlsrj.wms.api.entity.TenantWaterType;
import com.zlsrj.wms.api.entity.TenantInfo;

import com.zlsrj.wms.saas.mapper.TenantWaterTypeMapper;
import com.zlsrj.wms.saas.service.IIdService;
import com.zlsrj.wms.saas.service.ITenantWaterTypeService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class TenantWaterTypeServiceImpl extends ServiceImpl<TenantWaterTypeMapper, TenantWaterType> implements ITenantWaterTypeService {
	@Resource
	private IIdService idService;

}
