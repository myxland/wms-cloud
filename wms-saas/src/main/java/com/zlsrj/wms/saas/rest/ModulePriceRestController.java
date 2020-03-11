package com.zlsrj.wms.saas.rest;

import java.util.stream.Collectors;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zlsrj.wms.api.dto.ModulePriceQueryParam;
import com.zlsrj.wms.api.entity.ModuleInfo;
import com.zlsrj.wms.api.entity.ModulePrice;
import com.zlsrj.wms.api.vo.ModulePriceVo;
import com.zlsrj.wms.common.api.CommonResult;
import com.zlsrj.wms.saas.service.IIdService;
import com.zlsrj.wms.saas.service.IModulePriceService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

@Api(value = "模块价格", tags = { "模块价格操作接口" })
@RestController
@Slf4j
public class ModulePriceRestController {

	@Autowired
	private IModulePriceService modulePriceService;
	@Autowired
	private IIdService idService;

	@ApiOperation(value = "根据ID查询模块价格")
	@RequestMapping(value = "/module-prices/{id}", method = RequestMethod.GET)
	public ModulePriceVo getById(@PathVariable("id") String id) {
		ModulePrice modulePrice = modulePriceService.getById(id);

		return entity2vo(modulePrice);
	}

	@ApiOperation(value = "根据参数查询模块价格列表")
	@RequestMapping(value = "/module-prices", method = RequestMethod.GET)
	public Page<ModulePriceVo> page(@RequestBody ModulePriceQueryParam modulePriceQueryParam,
			@RequestParam(value = "page", defaultValue = "1") int page, //
			@RequestParam(value = "rows", defaultValue = "10") int rows, //
			@RequestParam(value = "sort") String sort, // 排序列字段名
			@RequestParam(value = "order") String order // 可以是 'asc' 或者 'desc'，默认值是 'asc'
	) {
		IPage<ModulePrice> pageModulePrice = new Page<ModulePrice>(page, rows);
		QueryWrapper<ModulePrice> queryWrapperModulePrice = new QueryWrapper<ModulePrice>();
		queryWrapperModulePrice.orderBy(StringUtils.isNotEmpty(sort), "desc".equals(order), sort);
		queryWrapperModulePrice.lambda()
				.eq(modulePriceQueryParam.getId() != null, ModulePrice::getId, modulePriceQueryParam.getId())
				.eq(modulePriceQueryParam.getModuleId() != null, ModulePrice::getModuleId, modulePriceQueryParam.getModuleId())
				.eq(modulePriceQueryParam.getModuleEdition() != null, ModulePrice::getModuleEdition, modulePriceQueryParam.getModuleEdition())
				.eq(modulePriceQueryParam.getStartNum() != null, ModulePrice::getStartNum, modulePriceQueryParam.getStartNum())
				.eq(modulePriceQueryParam.getEndNum() != null, ModulePrice::getEndNum, modulePriceQueryParam.getEndNum())
				.eq(modulePriceQueryParam.getPriceMoney() != null, ModulePrice::getPriceMoney, modulePriceQueryParam.getPriceMoney())
				;

		IPage<ModulePrice> modulePricePage = modulePriceService.page(pageModulePrice, queryWrapperModulePrice);

		Page<ModulePriceVo> modulePriceVoPage = new Page<ModulePriceVo>(page, rows);
		modulePriceVoPage.setCurrent(modulePricePage.getCurrent());
		modulePriceVoPage.setPages(modulePricePage.getPages());
		modulePriceVoPage.setSize(modulePricePage.getSize());
		modulePriceVoPage.setTotal(modulePricePage.getTotal());
		modulePriceVoPage.setRecords(modulePricePage.getRecords().stream()//
				.map(e -> entity2vo(e))//
				.collect(Collectors.toList()));

		return modulePriceVoPage;
	}

	@ApiOperation(value = "新增模块价格")
	@RequestMapping(value = "/module-prices", method = RequestMethod.POST)
	public ModulePriceVo save(@RequestBody ModulePrice modulePrice) {
		if (modulePrice.getId() == null || modulePrice.getId().trim().length() <= 0) {
			modulePrice.setId(idService.selectId());
		}
		boolean success = modulePriceService.save(modulePrice);
		if (success) {
			ModulePrice modulePriceDatabase = modulePriceService.getById(modulePrice.getId());
			return entity2vo(modulePriceDatabase);
		}
		log.info("save ModulePrice fail，{}", ToStringBuilder.reflectionToString(modulePrice, ToStringStyle.JSON_STYLE));
		return null;
	}

	@ApiOperation(value = "更新模块价格全部信息")
	@RequestMapping(value = "/module-prices/{id}", method = RequestMethod.PUT)
	public ModulePriceVo updateById(@PathVariable("id") String id, @RequestBody ModulePrice modulePrice) {
		modulePrice.setId(id);
		boolean success = modulePriceService.updateById(modulePrice);
		if (success) {
			ModulePrice modulePriceDatabase = modulePriceService.getById(id);
			return entity2vo(modulePriceDatabase);
		}
		log.info("update ModulePrice fail，{}", ToStringBuilder.reflectionToString(modulePrice, ToStringStyle.JSON_STYLE));
		return null;
	}

	@ApiOperation(value = "根据参数更新模块价格信息")
	@RequestMapping(value = "/module-prices/{id}", method = RequestMethod.PATCH)
	public ModulePriceVo updatePatchById(@PathVariable("id") String id, @RequestBody ModulePrice modulePrice) {
        ModulePrice modulePriceWhere = ModulePrice.builder()//
				.id(id)//
				.build();
		UpdateWrapper<ModulePrice> updateWrapperModulePrice = new UpdateWrapper<ModulePrice>();
		updateWrapperModulePrice.setEntity(modulePriceWhere);
		updateWrapperModulePrice.lambda()//
				//.eq(ModulePrice::getId, id)
				// .set(modulePrice.getId() != null, ModulePrice::getId, modulePrice.getId())
				.set(modulePrice.getModuleId() != null, ModulePrice::getModuleId, modulePrice.getModuleId())
				.set(modulePrice.getModuleEdition() != null, ModulePrice::getModuleEdition, modulePrice.getModuleEdition())
				.set(modulePrice.getStartNum() != null, ModulePrice::getStartNum, modulePrice.getStartNum())
				.set(modulePrice.getEndNum() != null, ModulePrice::getEndNum, modulePrice.getEndNum())
				.set(modulePrice.getPriceMoney() != null, ModulePrice::getPriceMoney, modulePrice.getPriceMoney())
				;

		boolean success = modulePriceService.update(updateWrapperModulePrice);
		if (success) {
			ModulePrice modulePriceDatabase = modulePriceService.getById(id);
			return entity2vo(modulePriceDatabase);
		}
		log.info("partial update ModulePrice fail，{}",
				ToStringBuilder.reflectionToString(modulePrice, ToStringStyle.JSON_STYLE));
		return null;
	}

	@ApiOperation(value = "根据ID删除模块价格")
	@RequestMapping(value = "/module-prices/{id}", method = RequestMethod.DELETE)
	public CommonResult<Object> removeById(@PathVariable("id") String id) {
		boolean success = modulePriceService.removeById(id);
		return success ? CommonResult.success(success) : CommonResult.failed();
	}

	private ModulePriceVo entity2vo(ModulePrice modulePrice) {
		if (modulePrice == null) {
			return null;
		}

		String jsonString = JSON.toJSONString(modulePrice);
		ModulePriceVo modulePriceVo = JSON.parseObject(jsonString, ModulePriceVo.class);
		return modulePriceVo;
	}

}
