package com.zlsrj.wms.saas.service.impl;
import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zlsrj.wms.api.entity.ModuleInfo;
import com.zlsrj.wms.api.entity.ModulePrice;
import com.zlsrj.wms.saas.mapper.ModulePriceMapper;
import com.zlsrj.wms.saas.service.IIdService;
import com.zlsrj.wms.saas.service.IModulePriceService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ModulePriceServiceImpl extends ServiceImpl<ModulePriceMapper, ModulePrice> implements IModulePriceService {
	@Resource
	private IIdService idService;

	@Override
	public boolean saveBatchByModuleInfo(ModuleInfo moduleInfo) {
		return false;
	}

}
