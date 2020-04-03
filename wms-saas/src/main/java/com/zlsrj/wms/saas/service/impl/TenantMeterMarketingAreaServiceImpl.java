package com.zlsrj.wms.saas.service.impl;
import static java.util.stream.Collectors.toCollection;

import java.io.Serializable;
import java.lang.reflect.Modifier;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Stream;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zlsrj.wms.api.dto.TenantMeterMarketingAreaAddParam;
import com.zlsrj.wms.api.dto.TenantMeterMarketingAreaUpdateParam;
import com.zlsrj.wms.api.entity.TenantEmployee;
import com.zlsrj.wms.api.entity.TenantEmployeeRole;
import com.zlsrj.wms.api.entity.TenantInfo;
import com.zlsrj.wms.api.entity.TenantMeterMarketingArea;
import com.zlsrj.wms.common.annotation.DictionaryDescription;
import com.zlsrj.wms.common.annotation.DictionaryOrder;
import com.zlsrj.wms.common.annotation.DictionaryText;
import com.zlsrj.wms.common.annotation.DictionaryValue;
import com.zlsrj.wms.common.util.TranslateUtil;
import com.zlsrj.wms.saas.mapper.TenantMeterMarketingAreaMapper;
import com.zlsrj.wms.saas.service.IIdService;
import com.zlsrj.wms.saas.service.ITenantMeterMarketingAreaService;
import com.zlsrj.wms.saas.service.RedisService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class TenantMeterMarketingAreaServiceImpl extends ServiceImpl<TenantMeterMarketingAreaMapper, TenantMeterMarketingArea> implements ITenantMeterMarketingAreaService {
	@Resource
	private IIdService idService;
	
	@Autowired
	private RedisService<String, String> redisService;

	@Override
	public boolean saveBatchByTenantInfo(TenantInfo tenantInfo) {
		QueryWrapper<TenantMeterMarketingArea> queryWrapperTenantMeterMarketingArea = new QueryWrapper<TenantMeterMarketingArea>();
		queryWrapperTenantMeterMarketingArea.lambda()//
				.eq(TenantMeterMarketingArea::getTenantId, tenantInfo.getId())//
		;
		int count = super.count(queryWrapperTenantMeterMarketingArea);
		if (count > 0) {
			log.error("根据租户信息初始化营销区域失败，营销区域已存在。");
			return false;
		}
		
		TenantMeterMarketingArea tenantMeterMarketingArea = TenantMeterMarketingArea.builder()//
				.id(idService.selectId())// 营销机构ID
				.tenantId(tenantInfo.getId())// 租户ID
				.marketingAreaType(0)// 区域类型（0：内部机构；1：代收机构）
				.marketingAreaName(tenantInfo.getTenantName())// 名称
				.marketingAreaParentId("")// 父级ID
				.marketingAreaData(null)// 结构化数据
				.build();

		boolean success = this.save(tenantMeterMarketingArea);

		return success;
	}
	
	@Override
	public boolean removeBatchByTenantInfo(TenantInfo tenantInfo) {
		boolean success = false;
		QueryWrapper<TenantMeterMarketingArea> queryWrapperTenantMeterMarketingArea = new QueryWrapper<TenantMeterMarketingArea>();
		queryWrapperTenantMeterMarketingArea.lambda()//
				.eq(TenantMeterMarketingArea::getTenantId, tenantInfo.getId())//
		;
		success = this.remove(queryWrapperTenantMeterMarketingArea);
		
		return success;
	}
	
	@Override
	public String save(TenantMeterMarketingAreaAddParam tenantMeterMarketingAreaAddParam) {
		TenantMeterMarketingArea tenantMeterMarketingArea = TranslateUtil.translate(tenantMeterMarketingAreaAddParam,
				TenantMeterMarketingArea.class);
		if (tenantMeterMarketingArea != null && StringUtils.isBlank(tenantMeterMarketingArea.getId())) {
			tenantMeterMarketingArea.setId(idService.selectId());
		}
		this.save(tenantMeterMarketingArea);

		return tenantMeterMarketingArea.getId();
	}

	@Override
	public boolean updateById(TenantMeterMarketingAreaUpdateParam tenantMeterMarketingAreaUpdateParam) {
		TenantMeterMarketingArea tenantMeterMarketingArea = TranslateUtil.translate(tenantMeterMarketingAreaUpdateParam,
				TenantMeterMarketingArea.class);
		
		boolean success =  this.updateById(tenantMeterMarketingArea);
		if(success) {
        	try {
    			redisService.remove(tenantMeterMarketingArea.getId());
    		} catch (Exception e) {
    			log.error("redis error", e);
    		}
        }
		
		return success;
	}
	
	@Override
    public boolean removeById(Serializable id) {
		boolean success = super.removeById(id);
    	if(success) {
        	try {
    			redisService.remove(id.toString());
    		} catch (Exception e) {
    			log.error("redis error", e);
    		}
        }
    	return success;
    }
	
	@Override
    public boolean remove(Wrapper<TenantMeterMarketingArea> wrapper) {
		List<TenantMeterMarketingArea> tenantMeterMarketingAreaList = this.list(wrapper);
		boolean success = super.remove(wrapper);
    	if(success) {
        	try {
        		tenantMeterMarketingAreaList.forEach(e->redisService.remove(e.getId()));
    		} catch (Exception e) {
    			log.error("redis error", e);
    		}
        }
    	return success;
    }
	
	@Override
	public TenantMeterMarketingArea getDictionaryById(Serializable id) {
		try {
			String entityJSONString = redisService.getValue(id.toString());
			if (StringUtils.isNotBlank(entityJSONString)) {
				TenantMeterMarketingArea entity = JSONObject.parseObject(entityJSONString, TenantMeterMarketingArea.class);
				return entity;
			}
		} catch (Exception e) {
			log.error("redis error", e);
		}
		
		List<String> fieldList = Stream.of(TenantMeterMarketingArea.class.getDeclaredFields())
                /* 过滤静态属性 */
                .filter(field -> !Modifier.isStatic(field.getModifiers()))
                /* 过滤 transient关键字修饰的属性 */
                .filter(field -> !Modifier.isTransient(field.getModifiers()))
                .filter(
                		field -> field.isAnnotationPresent(DictionaryValue.class)//
						|| field.isAnnotationPresent(DictionaryText.class)//
						|| field.isAnnotationPresent(DictionaryOrder.class)//
						|| field.isAnnotationPresent(DictionaryDescription.class)//
                		)
                .map(e->e.getName())
                .collect(toCollection(LinkedList::new));

		QueryWrapper<TenantMeterMarketingArea> queryWrapper = new QueryWrapper<>();
		queryWrapper//
				.lambda()//
				.select(TenantMeterMarketingArea.class,//
						e -> fieldList.contains(e.getProperty()))//
				.eq(TenantMeterMarketingArea::getId, id);
		TenantMeterMarketingArea entity = this.getOne(queryWrapper);
		// TenantMeterMarketingArea entity = this.getById(id);

		redisService.setValue(entity.getId(), JSON.toJSONString(entity));

		return entity;
	}
}
