package com.zlsrj.wms.saas.service.impl;

import java.io.Serializable;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zlsrj.wms.api.entity.ModuleInfo;
import com.zlsrj.wms.api.entity.ModulePrice;
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
	public boolean saveBatchByModuleInfo(ModuleInfo moduleInfo) {
		return false;
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public boolean save(ModuleInfo moduleInfo) {
		boolean success=false;
		// 保存模块信息
		success = super.save(moduleInfo);
		// 保存模块价格
		if(success) {
			String id = moduleInfo.getId();
			QueryWrapper<ModulePrice> queryWrapperModulePrice = new QueryWrapper<ModulePrice>();
			queryWrapperModulePrice.lambda().eq(ModulePrice::getModuleId, id);
			modulePriceService.remove(queryWrapperModulePrice);
			
			Integer basicEditionOn = moduleInfo.getBasicEditionOn();
			if (basicEditionOn != null && basicEditionOn.intValue() == 1) {
				List<ModulePrice> basicModulePriceList = moduleInfo.getBasicModulePriceList();
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
				List<ModulePrice> advanceModulePriceList = moduleInfo.getAdvanceModulePriceList();
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
				List<ModulePrice> ultimateModulePriceList = moduleInfo.getUltimateModulePriceList();
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
	
	@Override
    public ModuleInfo getById(Serializable id) {
		ModuleInfo moduleInfo = super.getById(id);
		if(moduleInfo!=null) {
			if(moduleInfo.getBasicEditionOn()!=null && moduleInfo.getBasicEditionOn().intValue() ==1) {
				if(moduleInfo.getBasicModulePriceList()==null || moduleInfo.getBasicModulePriceList().size()==0) {
					QueryWrapper<ModulePrice> queryWrapperModulePrice = new QueryWrapper<ModulePrice>();
					queryWrapperModulePrice.lambda()//
						.eq(ModulePrice::getModuleId, id)
						.eq(ModulePrice::getModuleEdition, 1);
					List<ModulePrice> modulePriceList = modulePriceService.list(queryWrapperModulePrice);
					moduleInfo.setBasicModulePriceList(modulePriceList);
				}
			}
			if(moduleInfo.getAdvanceEditionOn()!=null && moduleInfo.getAdvanceEditionOn().intValue() ==1) {
				if(moduleInfo.getAdvanceModulePriceList()==null || moduleInfo.getAdvanceModulePriceList().size()==0) {
					QueryWrapper<ModulePrice> queryWrapperModulePrice = new QueryWrapper<ModulePrice>();
					queryWrapperModulePrice.lambda()//
						.eq(ModulePrice::getModuleId, id)
						.eq(ModulePrice::getModuleEdition, 2);
					List<ModulePrice> modulePriceList = modulePriceService.list(queryWrapperModulePrice);
					moduleInfo.setAdvanceModulePriceList(modulePriceList);
				}
			}
			if(moduleInfo.getUltimateEditionOn()!=null && moduleInfo.getUltimateEditionOn().intValue() ==1) {
				if(moduleInfo.getUltimateModulePriceList()==null || moduleInfo.getUltimateModulePriceList().size()==0) {
					QueryWrapper<ModulePrice> queryWrapperModulePrice = new QueryWrapper<ModulePrice>();
					queryWrapperModulePrice.lambda()//
						.eq(ModulePrice::getModuleId, id)
						.eq(ModulePrice::getModuleEdition, 3);
					List<ModulePrice> modulePriceList = modulePriceService.list(queryWrapperModulePrice);
					moduleInfo.setUltimateModulePriceList(modulePriceList);
				}
			}
		}
		
		
        return moduleInfo;
    }
	
	@Override
	@Transactional(rollbackFor = Exception.class)
	public boolean update(Wrapper<ModuleInfo> updateWrapper) {
		boolean success = false;
		success = super.update(updateWrapper);
		
		if(success) {
			ModuleInfo moduleInfo = updateWrapper.getEntity();
			String id = moduleInfo.getId();
			QueryWrapper<ModulePrice> queryWrapperModulePrice = new QueryWrapper<ModulePrice>();
			queryWrapperModulePrice.lambda().eq(ModulePrice::getModuleId, id);
			modulePriceService.remove(queryWrapperModulePrice);
			
			Integer basicEditionOn = moduleInfo.getBasicEditionOn();
			if (basicEditionOn != null && basicEditionOn.intValue() == 1) {
				List<ModulePrice> basicModulePriceList = moduleInfo.getBasicModulePriceList();
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
				List<ModulePrice> advanceModulePriceList = moduleInfo.getAdvanceModulePriceList();
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
				List<ModulePrice> ultimateModulePriceList = moduleInfo.getUltimateModulePriceList();
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
