package com.zlsrj.wms.saas.service.impl;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zlsrj.wms.api.dto.SystemDictionariesAddParam;
import com.zlsrj.wms.api.dto.SystemDictionariesUpdateParam;
import com.zlsrj.wms.api.entity.SystemDictionaries;
import com.zlsrj.wms.common.util.TranslateUtil;
import com.zlsrj.wms.saas.mapper.SystemDictionariesMapper;
import com.zlsrj.wms.saas.service.ISystemDictionariesService;
import com.zlsrj.wms.saas.service.IIdService;

@Service
public class SystemDictionariesServiceImpl extends ServiceImpl<SystemDictionariesMapper, SystemDictionaries> implements ISystemDictionariesService {
	@Resource
	private IIdService idService;

	@Override
	public String save(SystemDictionariesAddParam systemDictionariesAddParam) {
		SystemDictionaries systemDictionaries = TranslateUtil.translate(systemDictionariesAddParam,
				SystemDictionaries.class);
		if (systemDictionaries != null && StringUtils.isBlank(systemDictionaries.getId())) {
			systemDictionaries.setId(idService.selectId());
		}
		this.save(systemDictionaries);

		return systemDictionaries.getId();
	}

	@Override
	public boolean updateById(SystemDictionariesUpdateParam systemDictionariesUpdateParam) {
		SystemDictionaries systemDictionaries = TranslateUtil.translate(systemDictionariesUpdateParam,
				SystemDictionaries.class);

		return this.updateById(systemDictionaries);
	}
	
}
