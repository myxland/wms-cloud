package com.zlsrj.wms.saas.service.impl;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zlsrj.wms.api.dto.TenantBookAddParam;
import com.zlsrj.wms.api.dto.TenantBookUpdateParam;
import com.zlsrj.wms.api.entity.TenantBook;
import com.zlsrj.wms.common.util.TranslateUtil;
import com.zlsrj.wms.saas.mapper.TenantBookMapper;
import com.zlsrj.wms.saas.service.ITenantBookService;
import com.zlsrj.wms.saas.service.IIdService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class TenantBookServiceImpl extends ServiceImpl<TenantBookMapper, TenantBook> implements ITenantBookService {
	@Resource
	private IIdService idService;

	@Override
	public TenantBook getAggregation(Wrapper<TenantBook> wrapper) {
		return baseMapper.selectAggregation(wrapper);
	}
	
	@Override
	public String save(TenantBookAddParam tenantBookAddParam) {
		TenantBook tenantBook = TranslateUtil.translate(tenantBookAddParam,
				TenantBook.class);
		if (tenantBook != null && StringUtils.isBlank(tenantBook.getId())) {
			tenantBook.setId(idService.selectId());
		}
		this.save(tenantBook);

		return tenantBook.getId();
	}

	@Override
	public boolean updateById(TenantBookUpdateParam tenantBookUpdateParam) {
		TenantBook tenantBook = TranslateUtil.translate(tenantBookUpdateParam,
				TenantBook.class);

		return this.updateById(tenantBook);
	}
	
}
