package com.zlsrj.wms.saas.service.impl;
import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zlsrj.wms.api.entity.ModuleInfo;
import com.zlsrj.wms.saas.mapper.ModuleInfoMapper;
import com.zlsrj.wms.saas.service.IIdService;
import com.zlsrj.wms.saas.service.IModuleInfoService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ModuleInfoServiceImpl extends ServiceImpl<ModuleInfoMapper, ModuleInfo> implements IModuleInfoService {
	@Resource
	private IIdService idService;

	@Override
	public boolean saveBatchByModuleInfo(ModuleInfo moduleInfo) {
		return false;
	}

}
