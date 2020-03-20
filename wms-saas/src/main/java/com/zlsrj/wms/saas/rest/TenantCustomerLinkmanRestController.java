package com.zlsrj.wms.saas.rest;

import java.util.Date;
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
import com.zlsrj.wms.api.dto.TenantCustomerLinkmanQueryParam;
import com.zlsrj.wms.api.entity.TenantInfo;
import com.zlsrj.wms.api.entity.TenantCustomerLinkman;
import com.zlsrj.wms.api.vo.TenantCustomerLinkmanVo;
import com.zlsrj.wms.common.api.CommonResult;
import com.zlsrj.wms.saas.service.IIdService;
import com.zlsrj.wms.saas.service.ITenantInfoService;
import com.zlsrj.wms.saas.service.ITenantCustomerLinkmanService;

import cn.hutool.core.date.DateUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

@Api(value = "用户联系人", tags = { "用户联系人操作接口" })
@RestController
@Slf4j
public class TenantCustomerLinkmanRestController {

	@Autowired
	private ITenantCustomerLinkmanService tenantCustomerLinkmanService;
	@Autowired
	private ITenantInfoService tenantInfoService;
	@Autowired
	private IIdService idService;

	@ApiOperation(value = "根据ID查询用户联系人")
	@RequestMapping(value = "/tenant-customer-linkmans/{id}", method = RequestMethod.GET)
	public TenantCustomerLinkmanVo getById(@PathVariable("id") String id) {
		TenantCustomerLinkman tenantCustomerLinkman = tenantCustomerLinkmanService.getById(id);

		return entity2vo(tenantCustomerLinkman);
	}

	@ApiOperation(value = "根据参数查询用户联系人列表")
	@RequestMapping(value = "/tenant-customer-linkmans", method = RequestMethod.GET)
	public Page<TenantCustomerLinkmanVo> page(@RequestBody TenantCustomerLinkmanQueryParam tenantCustomerLinkmanQueryParam,
			@RequestParam(value = "page", defaultValue = "1") int page, //
			@RequestParam(value = "rows", defaultValue = "10") int rows, //
			@RequestParam(value = "sort") String sort, // 排序列字段名
			@RequestParam(value = "order") String order // 可以是 'asc' 或者 'desc'，默认值是 'asc'
	) {
		IPage<TenantCustomerLinkman> pageTenantCustomerLinkman = new Page<TenantCustomerLinkman>(page, rows);
		QueryWrapper<TenantCustomerLinkman> queryWrapperTenantCustomerLinkman = new QueryWrapper<TenantCustomerLinkman>();
		queryWrapperTenantCustomerLinkman.orderBy(StringUtils.isNotEmpty(sort), "asc".equals(order), sort);
		queryWrapperTenantCustomerLinkman.lambda()
				.eq(tenantCustomerLinkmanQueryParam.getId() != null, TenantCustomerLinkman::getId, tenantCustomerLinkmanQueryParam.getId())
				.eq(tenantCustomerLinkmanQueryParam.getTenantId() != null, TenantCustomerLinkman::getTenantId, tenantCustomerLinkmanQueryParam.getTenantId())
				.eq(tenantCustomerLinkmanQueryParam.getCustomerId() != null, TenantCustomerLinkman::getCustomerId, tenantCustomerLinkmanQueryParam.getCustomerId())
				.eq(tenantCustomerLinkmanQueryParam.getLinkmanName() != null, TenantCustomerLinkman::getLinkmanName, tenantCustomerLinkmanQueryParam.getLinkmanName())
				.eq(tenantCustomerLinkmanQueryParam.getLinkmanAddress() != null, TenantCustomerLinkman::getLinkmanAddress, tenantCustomerLinkmanQueryParam.getLinkmanAddress())
				.eq(tenantCustomerLinkmanQueryParam.getLinkmanMainOn() != null, TenantCustomerLinkman::getLinkmanMainOn, tenantCustomerLinkmanQueryParam.getLinkmanMainOn())
				.eq(tenantCustomerLinkmanQueryParam.getLinkmanSex() != null, TenantCustomerLinkman::getLinkmanSex, tenantCustomerLinkmanQueryParam.getLinkmanSex())
				.eq(tenantCustomerLinkmanQueryParam.getLinkmanBirthday() != null, TenantCustomerLinkman::getLinkmanBirthday, tenantCustomerLinkmanQueryParam.getLinkmanBirthday())
				.ge(tenantCustomerLinkmanQueryParam.getLinkmanBirthdayStart() != null, TenantCustomerLinkman::getLinkmanBirthday,tenantCustomerLinkmanQueryParam.getLinkmanBirthdayStart() == null ? null: DateUtil.beginOfDay(tenantCustomerLinkmanQueryParam.getLinkmanBirthdayStart()))
				.le(tenantCustomerLinkmanQueryParam.getLinkmanBirthdayEnd() != null, TenantCustomerLinkman::getLinkmanBirthday,tenantCustomerLinkmanQueryParam.getLinkmanBirthdayEnd() == null ? null: DateUtil.endOfDay(tenantCustomerLinkmanQueryParam.getLinkmanBirthdayEnd()))
				.eq(tenantCustomerLinkmanQueryParam.getLinkmanMobile() != null, TenantCustomerLinkman::getLinkmanMobile, tenantCustomerLinkmanQueryParam.getLinkmanMobile())
				.eq(tenantCustomerLinkmanQueryParam.getLinkmanTel() != null, TenantCustomerLinkman::getLinkmanTel, tenantCustomerLinkmanQueryParam.getLinkmanTel())
				.eq(tenantCustomerLinkmanQueryParam.getLinkmanEmail() != null, TenantCustomerLinkman::getLinkmanEmail, tenantCustomerLinkmanQueryParam.getLinkmanEmail())
				.eq(tenantCustomerLinkmanQueryParam.getLinkmanPersonalWx() != null, TenantCustomerLinkman::getLinkmanPersonalWx, tenantCustomerLinkmanQueryParam.getLinkmanPersonalWx())
				.eq(tenantCustomerLinkmanQueryParam.getLinkmanQq() != null, TenantCustomerLinkman::getLinkmanQq, tenantCustomerLinkmanQueryParam.getLinkmanQq())
				.eq(tenantCustomerLinkmanQueryParam.getLinkmanRemark() != null, TenantCustomerLinkman::getLinkmanRemark, tenantCustomerLinkmanQueryParam.getLinkmanRemark())
				;

		IPage<TenantCustomerLinkman> tenantCustomerLinkmanPage = tenantCustomerLinkmanService.page(pageTenantCustomerLinkman, queryWrapperTenantCustomerLinkman);

		Page<TenantCustomerLinkmanVo> tenantCustomerLinkmanVoPage = new Page<TenantCustomerLinkmanVo>(page, rows);
		tenantCustomerLinkmanVoPage.setCurrent(tenantCustomerLinkmanPage.getCurrent());
		tenantCustomerLinkmanVoPage.setPages(tenantCustomerLinkmanPage.getPages());
		tenantCustomerLinkmanVoPage.setSize(tenantCustomerLinkmanPage.getSize());
		tenantCustomerLinkmanVoPage.setTotal(tenantCustomerLinkmanPage.getTotal());
		tenantCustomerLinkmanVoPage.setRecords(tenantCustomerLinkmanPage.getRecords().stream()//
				.map(e -> entity2vo(e))//
				.collect(Collectors.toList()));

		return tenantCustomerLinkmanVoPage;
	}

	@ApiOperation(value = "新增用户联系人")
	@RequestMapping(value = "/tenant-customer-linkmans", method = RequestMethod.POST)
	public TenantCustomerLinkmanVo save(@RequestBody TenantCustomerLinkman tenantCustomerLinkman) {
		if (tenantCustomerLinkman.getId() == null || tenantCustomerLinkman.getId().trim().length() <= 0) {
			tenantCustomerLinkman.setId(idService.selectId());
		}
		boolean success = tenantCustomerLinkmanService.save(tenantCustomerLinkman);
		if (success) {
			TenantCustomerLinkman tenantCustomerLinkmanDatabase = tenantCustomerLinkmanService.getById(tenantCustomerLinkman.getId());
			return entity2vo(tenantCustomerLinkmanDatabase);
		}
		log.info("save TenantCustomerLinkman fail，{}", ToStringBuilder.reflectionToString(tenantCustomerLinkman, ToStringStyle.JSON_STYLE));
		return null;
	}

	@ApiOperation(value = "更新用户联系人全部信息")
	@RequestMapping(value = "/tenant-customer-linkmans/{id}", method = RequestMethod.PUT)
	public TenantCustomerLinkmanVo updateById(@PathVariable("id") String id, @RequestBody TenantCustomerLinkman tenantCustomerLinkman) {
		tenantCustomerLinkman.setId(id);
		boolean success = tenantCustomerLinkmanService.updateById(tenantCustomerLinkman);
		if (success) {
			TenantCustomerLinkman tenantCustomerLinkmanDatabase = tenantCustomerLinkmanService.getById(id);
			return entity2vo(tenantCustomerLinkmanDatabase);
		}
		log.info("update TenantCustomerLinkman fail，{}", ToStringBuilder.reflectionToString(tenantCustomerLinkman, ToStringStyle.JSON_STYLE));
		return null;
	}

	@ApiOperation(value = "根据参数更新用户联系人信息")
	@RequestMapping(value = "/tenant-customer-linkmans/{id}", method = RequestMethod.PATCH)
	public TenantCustomerLinkmanVo updatePatchById(@PathVariable("id") String id, @RequestBody TenantCustomerLinkman tenantCustomerLinkman) {
        TenantCustomerLinkman tenantCustomerLinkmanWhere = TenantCustomerLinkman.builder()//
				.id(id)//
				.build();
		UpdateWrapper<TenantCustomerLinkman> updateWrapperTenantCustomerLinkman = new UpdateWrapper<TenantCustomerLinkman>();
		updateWrapperTenantCustomerLinkman.setEntity(tenantCustomerLinkmanWhere);
		updateWrapperTenantCustomerLinkman.lambda()//
				//.eq(TenantCustomerLinkman::getId, id)
				// .set(tenantCustomerLinkman.getId() != null, TenantCustomerLinkman::getId, tenantCustomerLinkman.getId())
				.set(tenantCustomerLinkman.getTenantId() != null, TenantCustomerLinkman::getTenantId, tenantCustomerLinkman.getTenantId())
				.set(tenantCustomerLinkman.getCustomerId() != null, TenantCustomerLinkman::getCustomerId, tenantCustomerLinkman.getCustomerId())
				.set(tenantCustomerLinkman.getLinkmanName() != null, TenantCustomerLinkman::getLinkmanName, tenantCustomerLinkman.getLinkmanName())
				.set(tenantCustomerLinkman.getLinkmanAddress() != null, TenantCustomerLinkman::getLinkmanAddress, tenantCustomerLinkman.getLinkmanAddress())
				.set(tenantCustomerLinkman.getLinkmanMainOn() != null, TenantCustomerLinkman::getLinkmanMainOn, tenantCustomerLinkman.getLinkmanMainOn())
				.set(tenantCustomerLinkman.getLinkmanSex() != null, TenantCustomerLinkman::getLinkmanSex, tenantCustomerLinkman.getLinkmanSex())
				.set(tenantCustomerLinkman.getLinkmanBirthday() != null, TenantCustomerLinkman::getLinkmanBirthday, tenantCustomerLinkman.getLinkmanBirthday())
				.set(tenantCustomerLinkman.getLinkmanMobile() != null, TenantCustomerLinkman::getLinkmanMobile, tenantCustomerLinkman.getLinkmanMobile())
				.set(tenantCustomerLinkman.getLinkmanTel() != null, TenantCustomerLinkman::getLinkmanTel, tenantCustomerLinkman.getLinkmanTel())
				.set(tenantCustomerLinkman.getLinkmanEmail() != null, TenantCustomerLinkman::getLinkmanEmail, tenantCustomerLinkman.getLinkmanEmail())
				.set(tenantCustomerLinkman.getLinkmanPersonalWx() != null, TenantCustomerLinkman::getLinkmanPersonalWx, tenantCustomerLinkman.getLinkmanPersonalWx())
				.set(tenantCustomerLinkman.getLinkmanQq() != null, TenantCustomerLinkman::getLinkmanQq, tenantCustomerLinkman.getLinkmanQq())
				.set(tenantCustomerLinkman.getLinkmanRemark() != null, TenantCustomerLinkman::getLinkmanRemark, tenantCustomerLinkman.getLinkmanRemark())
				;

		boolean success = tenantCustomerLinkmanService.update(updateWrapperTenantCustomerLinkman);
		if (success) {
			TenantCustomerLinkman tenantCustomerLinkmanDatabase = tenantCustomerLinkmanService.getById(id);
			return entity2vo(tenantCustomerLinkmanDatabase);
		}
		log.info("partial update TenantCustomerLinkman fail，{}",
				ToStringBuilder.reflectionToString(tenantCustomerLinkman, ToStringStyle.JSON_STYLE));
		return null;
	}

	@ApiOperation(value = "根据ID删除用户联系人")
	@RequestMapping(value = "/tenant-customer-linkmans/{id}", method = RequestMethod.DELETE)
	public CommonResult<Object> removeById(@PathVariable("id") String id) {
		boolean success = tenantCustomerLinkmanService.removeById(id);
		return success ? CommonResult.success(success) : CommonResult.failed();
	}

	private TenantCustomerLinkmanVo entity2vo(TenantCustomerLinkman tenantCustomerLinkman) {
		if (tenantCustomerLinkman == null) {
			return null;
		}

		String jsonString = JSON.toJSONString(tenantCustomerLinkman);
		TenantCustomerLinkmanVo tenantCustomerLinkmanVo = JSON.parseObject(jsonString, TenantCustomerLinkmanVo.class);
		if (StringUtils.isEmpty(tenantCustomerLinkmanVo.getTenantName())) {
			TenantInfo tenantInfo = tenantInfoService.getDictionaryById(tenantCustomerLinkman.getTenantId());
			if (tenantInfo != null) {
				tenantCustomerLinkmanVo.setTenantName(tenantInfo.getTenantName());
			}
		}
		return tenantCustomerLinkmanVo;
	}

}
