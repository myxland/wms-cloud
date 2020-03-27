package com.zlsrj.wms.saas.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zlsrj.wms.api.dto.ModuleInfoAddParam;
import com.zlsrj.wms.api.dto.ModuleInfoUpdateParam;
import com.zlsrj.wms.api.entity.ModuleInfo;
import com.zlsrj.wms.api.entity.ModulePrice;
import com.zlsrj.wms.common.util.TranslateUtil;
import com.zlsrj.wms.saas.mapper.ModuleInfoMapper;
import com.zlsrj.wms.saas.service.IIdService;
import com.zlsrj.wms.saas.service.IModuleInfoService;
import com.zlsrj.wms.saas.service.IModulePriceService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ModuleInfoServiceImpl extends ServiceImpl<ModuleInfoMapper, ModuleInfo> implements IModuleInfoService {
	@Resource
	private IIdService idService;

	@Resource
	private IModulePriceService modulePriceService;

	@Override
	@Transactional
	public String save(ModuleInfoAddParam moduleInfoAddParam) {
		boolean success = false;
		// 保存模块信息
		ModuleInfo moduleInfo = TranslateUtil.translate(moduleInfoAddParam, ModuleInfo.class);
		if (moduleInfo != null && StringUtils.isBlank(moduleInfo.getId())) {
			moduleInfo.setId(idService.selectId());
		}
		success = super.save(moduleInfo);
		// 保存模块价格
		if (success) {
			String id = moduleInfo.getId();
			QueryWrapper<ModulePrice> queryWrapperModulePrice = new QueryWrapper<ModulePrice>();
			queryWrapperModulePrice.lambda().eq(ModulePrice::getModuleId, id);
			modulePriceService.remove(queryWrapperModulePrice);

			Integer basicEditionOn = moduleInfo.getBasicEditionOn();
			if (basicEditionOn != null && basicEditionOn.intValue() == 1) {
				List<ModulePrice> basicModulePriceList = moduleInfoAddParam.getBasicModulePriceList();
				if (basicModulePriceList != null && basicModulePriceList.size() > 0) {
					for (ModulePrice modulePrice : basicModulePriceList) {
						modulePrice.setId(idService.selectId());
						modulePrice.setModuleId(id);
						modulePrice.setModuleEdition(1);
						modulePriceService.save(modulePrice);
					}
				}

			}

			Integer advanceEditionOn = moduleInfo.getAdvanceEditionOn();
			if (advanceEditionOn != null && advanceEditionOn.intValue() == 1) {
				List<ModulePrice> advanceModulePriceList = moduleInfoAddParam.getAdvanceModulePriceList();
				if (advanceModulePriceList != null && advanceModulePriceList.size() > 0) {
					for (ModulePrice modulePrice : advanceModulePriceList) {
						modulePrice.setId(idService.selectId());
						modulePrice.setModuleId(id);
						modulePrice.setModuleEdition(2);
						modulePriceService.save(modulePrice);
					}
				}
			}
			Integer ultimateEditionOn = moduleInfo.getUltimateEditionOn();
			if (ultimateEditionOn != null && ultimateEditionOn.intValue() == 1) {
				List<ModulePrice> ultimateModulePriceList = moduleInfoAddParam.getUltimateModulePriceList();
				if (ultimateModulePriceList != null && ultimateModulePriceList.size() > 0) {
					for (ModulePrice modulePrice : ultimateModulePriceList) {
						modulePrice.setId(idService.selectId());
						modulePrice.setModuleId(id);
						modulePrice.setModuleEdition(3);
						modulePriceService.save(modulePrice);
					}
				}

			}
		}

		return moduleInfo.getId();
	}

	@Override
	@Transactional
	public boolean updateById(ModuleInfoUpdateParam moduleInfoUpdateParam) {
		boolean success = false;
		ModuleInfo moduleInfo = TranslateUtil.translate(moduleInfoUpdateParam, ModuleInfo.class);
		success = this.updateById(moduleInfo);

		if (success) {
			String id = moduleInfo.getId();
			QueryWrapper<ModulePrice> queryWrapperModulePrice = new QueryWrapper<ModulePrice>();
			queryWrapperModulePrice.lambda().eq(ModulePrice::getModuleId, id);
			modulePriceService.remove(queryWrapperModulePrice);

			Integer basicEditionOn = moduleInfo.getBasicEditionOn();
			if (basicEditionOn != null && basicEditionOn.intValue() == 1) {
				List<ModulePrice> basicModulePriceList = moduleInfoUpdateParam.getBasicModulePriceList();
				if (basicModulePriceList != null && basicModulePriceList.size() > 0) {
					for (ModulePrice modulePrice : basicModulePriceList) {
						modulePrice.setId(idService.selectId());
						modulePrice.setModuleId(id);
						modulePrice.setModuleEdition(1);
						modulePriceService.save(modulePrice);
					}
				}

			}

			Integer advanceEditionOn = moduleInfo.getAdvanceEditionOn();
			if (advanceEditionOn != null && advanceEditionOn.intValue() == 1) {
				List<ModulePrice> advanceModulePriceList = moduleInfoUpdateParam.getAdvanceModulePriceList();
				if (advanceModulePriceList != null && advanceModulePriceList.size() > 0) {
					for (ModulePrice modulePrice : advanceModulePriceList) {
						modulePrice.setId(idService.selectId());
						modulePrice.setModuleId(id);
						modulePrice.setModuleEdition(2);
						modulePriceService.save(modulePrice);
					}
				}

			}

			Integer ultimateEditionOn = moduleInfo.getUltimateEditionOn();
			if (ultimateEditionOn != null && ultimateEditionOn.intValue() == 1) {
				List<ModulePrice> ultimateModulePriceList = moduleInfoUpdateParam.getUltimateModulePriceList();
				if (ultimateModulePriceList != null && ultimateModulePriceList.size() > 0) {
					for (ModulePrice modulePrice : ultimateModulePriceList) {
						modulePrice.setId(idService.selectId());
						modulePrice.setModuleId(id);
						modulePrice.setModuleEdition(3);
						modulePriceService.save(modulePrice);
					}
				}

			}
		}

		return success;
	}
	
}
