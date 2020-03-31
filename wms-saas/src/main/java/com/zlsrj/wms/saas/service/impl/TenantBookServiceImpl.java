package com.zlsrj.wms.saas.service.impl;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zlsrj.wms.api.dto.TenantBookAddParam;
import com.zlsrj.wms.api.dto.TenantBookUpdateParam;
import com.zlsrj.wms.api.entity.TenantBook;
import com.zlsrj.wms.api.entity.TenantInfo;
import com.zlsrj.wms.common.util.TranslateUtil;
import com.zlsrj.wms.saas.config.CodeConfig;
import com.zlsrj.wms.saas.mapper.TenantBookMapper;
import com.zlsrj.wms.saas.mapper.TenantInfoMapper;
import com.zlsrj.wms.saas.service.IIdService;
import com.zlsrj.wms.saas.service.ITenantBookService;
import com.zlsrj.wms.saas.service.RedisService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class TenantBookServiceImpl extends ServiceImpl<TenantBookMapper, TenantBook> implements ITenantBookService {
	@Resource
	private IIdService idService;
	@Resource
	private RedisService redisService;
	@Resource
	private CodeConfig codeConfig;
	@Resource
	private TenantInfoMapper tenantInfoMapper;

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
		
		//表册编号
		TenantInfo tenantInfo = tenantInfoMapper.selectById(tenantBook.getTenantId());
		Integer tenantCode = tenantInfo.getTenantCode();
		String key = codeConfig.getBookCode()+tenantCode;
		Long value = redisService.increment(key);
		tenantBook.setBookCode(Long.toString(value));
		
		this.save(tenantBook);

		return tenantBook.getId();
	}

	@Override
	public boolean updateById(TenantBookUpdateParam tenantBookUpdateParam) {
		TenantBook tenantBook = TranslateUtil.translate(tenantBookUpdateParam,
				TenantBook.class);

		return this.updateById(tenantBook);
	}
	
	@Override
	public List<TenantBook> getMaxBookCode() {
		QueryWrapper<TenantBook> queryWrapperTenantBook = new QueryWrapper<TenantBook>();
		queryWrapperTenantBook//
				.select("tenant_id,max(book_code) as book_code")//
				.groupBy("tenant_id")//
				.orderByAsc("tenant_id")//
		;
		List<TenantBook> tenantBookList = this.list(queryWrapperTenantBook);

		return tenantBookList;
	}
}
