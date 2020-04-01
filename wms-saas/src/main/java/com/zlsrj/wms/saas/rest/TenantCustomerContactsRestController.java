package com.zlsrj.wms.saas.rest;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zlsrj.wms.api.dto.TenantCustomerContactsAddParam;
import com.zlsrj.wms.api.dto.TenantCustomerContactsQueryParam;
import com.zlsrj.wms.api.dto.TenantCustomerContactsUpdateParam;
import com.zlsrj.wms.api.entity.TenantInfo;
import com.zlsrj.wms.api.entity.TenantCustomerContacts;
import com.zlsrj.wms.api.vo.TenantCustomerContactsVo;
import com.zlsrj.wms.common.api.CommonResult;
import com.zlsrj.wms.common.util.TranslateUtil;
import com.zlsrj.wms.saas.service.ITenantInfoService;
import com.zlsrj.wms.saas.service.ITenantCustomerContactsService;

import cn.hutool.core.date.DateUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

@Api(value = "用户联系方式", tags = { "用户联系方式操作接口" })
@RestController
@Slf4j
public class TenantCustomerContactsRestController {

	@Autowired
	private ITenantCustomerContactsService tenantCustomerContactsService;
	@Autowired
	private ITenantInfoService tenantInfoService;

	@ApiOperation(value = "根据ID查询用户联系方式")
	@RequestMapping(value = "/tenant-customer-contactss/{id}", method = RequestMethod.GET)
	public TenantCustomerContactsVo getById(@PathVariable("id") String id) {
		TenantCustomerContacts tenantCustomerContacts = tenantCustomerContactsService.getById(id);

		return entity2vo(tenantCustomerContacts);
	}

	@ApiOperation(value = "根据参数查询用户联系方式列表")
	@RequestMapping(value = "/tenant-customer-contactss/list", method = RequestMethod.GET)
	public List<TenantCustomerContactsVo> list(@RequestBody TenantCustomerContactsQueryParam tenantCustomerContactsQueryParam) {
		QueryWrapper<TenantCustomerContacts> queryWrapperTenantCustomerContacts = new QueryWrapper<TenantCustomerContacts>();
		queryWrapperTenantCustomerContacts.lambda()
				.eq(StringUtils.isNotEmpty(tenantCustomerContactsQueryParam.getId()), TenantCustomerContacts::getId, tenantCustomerContactsQueryParam.getId())
				.eq(StringUtils.isNotEmpty(tenantCustomerContactsQueryParam.getTenantId()), TenantCustomerContacts::getTenantId, tenantCustomerContactsQueryParam.getTenantId())
				.eq(StringUtils.isNotEmpty(tenantCustomerContactsQueryParam.getCustomerId()), TenantCustomerContacts::getCustomerId, tenantCustomerContactsQueryParam.getCustomerId())
				.eq(StringUtils.isNotEmpty(tenantCustomerContactsQueryParam.getCustomerCode()), TenantCustomerContacts::getCustomerCode, tenantCustomerContactsQueryParam.getCustomerCode())
				.eq(StringUtils.isNotEmpty(tenantCustomerContactsQueryParam.getContactsName()), TenantCustomerContacts::getContactsName, tenantCustomerContactsQueryParam.getContactsName())
				.eq(StringUtils.isNotEmpty(tenantCustomerContactsQueryParam.getContactsAddress()), TenantCustomerContacts::getContactsAddress, tenantCustomerContactsQueryParam.getContactsAddress())
				.eq(tenantCustomerContactsQueryParam.getContactsMain() != null, TenantCustomerContacts::getContactsMain, tenantCustomerContactsQueryParam.getContactsMain())
				.eq(tenantCustomerContactsQueryParam.getContactsSex() != null, TenantCustomerContacts::getContactsSex, tenantCustomerContactsQueryParam.getContactsSex())
				.eq(tenantCustomerContactsQueryParam.getContactsBirthday() != null, TenantCustomerContacts::getContactsBirthday, tenantCustomerContactsQueryParam.getContactsBirthday())
				.ge(tenantCustomerContactsQueryParam.getContactsBirthdayStart() != null, TenantCustomerContacts::getContactsBirthday,tenantCustomerContactsQueryParam.getContactsBirthdayStart() == null ? null: DateUtil.beginOfDay(tenantCustomerContactsQueryParam.getContactsBirthdayStart()))
				.le(tenantCustomerContactsQueryParam.getContactsBirthdayEnd() != null, TenantCustomerContacts::getContactsBirthday,tenantCustomerContactsQueryParam.getContactsBirthdayEnd() == null ? null: DateUtil.endOfDay(tenantCustomerContactsQueryParam.getContactsBirthdayEnd()))
				.eq(StringUtils.isNotEmpty(tenantCustomerContactsQueryParam.getContactsMobile()), TenantCustomerContacts::getContactsMobile, tenantCustomerContactsQueryParam.getContactsMobile())
				.eq(StringUtils.isNotEmpty(tenantCustomerContactsQueryParam.getContactsTel()), TenantCustomerContacts::getContactsTel, tenantCustomerContactsQueryParam.getContactsTel())
				.eq(StringUtils.isNotEmpty(tenantCustomerContactsQueryParam.getContactsEmail()), TenantCustomerContacts::getContactsEmail, tenantCustomerContactsQueryParam.getContactsEmail())
				.eq(StringUtils.isNotEmpty(tenantCustomerContactsQueryParam.getContactsWx()), TenantCustomerContacts::getContactsWx, tenantCustomerContactsQueryParam.getContactsWx())
				.eq(StringUtils.isNotEmpty(tenantCustomerContactsQueryParam.getContactsQq()), TenantCustomerContacts::getContactsQq, tenantCustomerContactsQueryParam.getContactsQq())
				.eq(StringUtils.isNotEmpty(tenantCustomerContactsQueryParam.getContactsMemo()), TenantCustomerContacts::getContactsMemo, tenantCustomerContactsQueryParam.getContactsMemo())
				.eq(tenantCustomerContactsQueryParam.getAddTime() != null, TenantCustomerContacts::getAddTime, tenantCustomerContactsQueryParam.getAddTime())
				.ge(tenantCustomerContactsQueryParam.getAddTimeStart() != null, TenantCustomerContacts::getAddTime,tenantCustomerContactsQueryParam.getAddTimeStart() == null ? null: DateUtil.beginOfDay(tenantCustomerContactsQueryParam.getAddTimeStart()))
				.le(tenantCustomerContactsQueryParam.getAddTimeEnd() != null, TenantCustomerContacts::getAddTime,tenantCustomerContactsQueryParam.getAddTimeEnd() == null ? null: DateUtil.endOfDay(tenantCustomerContactsQueryParam.getAddTimeEnd()))
				.eq(tenantCustomerContactsQueryParam.getUpdateTime() != null, TenantCustomerContacts::getUpdateTime, tenantCustomerContactsQueryParam.getUpdateTime())
				.ge(tenantCustomerContactsQueryParam.getUpdateTimeStart() != null, TenantCustomerContacts::getUpdateTime,tenantCustomerContactsQueryParam.getUpdateTimeStart() == null ? null: DateUtil.beginOfDay(tenantCustomerContactsQueryParam.getUpdateTimeStart()))
				.le(tenantCustomerContactsQueryParam.getUpdateTimeEnd() != null, TenantCustomerContacts::getUpdateTime,tenantCustomerContactsQueryParam.getUpdateTimeEnd() == null ? null: DateUtil.endOfDay(tenantCustomerContactsQueryParam.getUpdateTimeEnd()))
				;

		String[] queryCols = tenantCustomerContactsQueryParam.getQueryCol();
		String[] queryTypes = tenantCustomerContactsQueryParam.getQueryType();
		String[] queryValues = tenantCustomerContactsQueryParam.getQueryValue();
		if (queryCols != null && queryCols.length > 0) {
			for (int i = 0; i < queryCols.length; i++) {
				queryWrapperTenantCustomerContacts.lambda()//
						.apply("=".equals(queryTypes[i]), queryCols[i] + "={0}", queryValues[i])//
						.apply("like".equals(queryTypes[i]), queryCols[i] + " like CONCAT('%',{0},'%')", queryValues[i])//
				;
			}
		}
		
		List<TenantCustomerContacts> tenantCustomerContactsList = tenantCustomerContactsService.list(queryWrapperTenantCustomerContacts);

		List<TenantCustomerContactsVo> tenantCustomerContactsVoList = tenantCustomerContactsList.stream()//
				 .map(e -> entity2vo(e))//
				 .collect(Collectors.toList());

		return tenantCustomerContactsVoList;
	}
	
	@ApiOperation(value = "根据参数查询用户联系方式数量")
	@RequestMapping(value = "/tenant-customer-contactss/count", method = RequestMethod.GET)
	public int count(@RequestBody TenantCustomerContactsQueryParam tenantCustomerContactsQueryParam) {
		QueryWrapper<TenantCustomerContacts> queryWrapperTenantCustomerContacts = new QueryWrapper<TenantCustomerContacts>();
		queryWrapperTenantCustomerContacts.lambda()
				.eq(StringUtils.isNotEmpty(tenantCustomerContactsQueryParam.getId()), TenantCustomerContacts::getId, tenantCustomerContactsQueryParam.getId())
				.eq(StringUtils.isNotEmpty(tenantCustomerContactsQueryParam.getTenantId()), TenantCustomerContacts::getTenantId, tenantCustomerContactsQueryParam.getTenantId())
				.eq(StringUtils.isNotEmpty(tenantCustomerContactsQueryParam.getCustomerId()), TenantCustomerContacts::getCustomerId, tenantCustomerContactsQueryParam.getCustomerId())
				.eq(StringUtils.isNotEmpty(tenantCustomerContactsQueryParam.getCustomerCode()), TenantCustomerContacts::getCustomerCode, tenantCustomerContactsQueryParam.getCustomerCode())
				.eq(StringUtils.isNotEmpty(tenantCustomerContactsQueryParam.getContactsName()), TenantCustomerContacts::getContactsName, tenantCustomerContactsQueryParam.getContactsName())
				.eq(StringUtils.isNotEmpty(tenantCustomerContactsQueryParam.getContactsAddress()), TenantCustomerContacts::getContactsAddress, tenantCustomerContactsQueryParam.getContactsAddress())
				.eq(tenantCustomerContactsQueryParam.getContactsMain() != null, TenantCustomerContacts::getContactsMain, tenantCustomerContactsQueryParam.getContactsMain())
				.eq(tenantCustomerContactsQueryParam.getContactsSex() != null, TenantCustomerContacts::getContactsSex, tenantCustomerContactsQueryParam.getContactsSex())
				.eq(tenantCustomerContactsQueryParam.getContactsBirthday() != null, TenantCustomerContacts::getContactsBirthday, tenantCustomerContactsQueryParam.getContactsBirthday())
				.ge(tenantCustomerContactsQueryParam.getContactsBirthdayStart() != null, TenantCustomerContacts::getContactsBirthday,tenantCustomerContactsQueryParam.getContactsBirthdayStart() == null ? null: DateUtil.beginOfDay(tenantCustomerContactsQueryParam.getContactsBirthdayStart()))
				.le(tenantCustomerContactsQueryParam.getContactsBirthdayEnd() != null, TenantCustomerContacts::getContactsBirthday,tenantCustomerContactsQueryParam.getContactsBirthdayEnd() == null ? null: DateUtil.endOfDay(tenantCustomerContactsQueryParam.getContactsBirthdayEnd()))
				.eq(StringUtils.isNotEmpty(tenantCustomerContactsQueryParam.getContactsMobile()), TenantCustomerContacts::getContactsMobile, tenantCustomerContactsQueryParam.getContactsMobile())
				.eq(StringUtils.isNotEmpty(tenantCustomerContactsQueryParam.getContactsTel()), TenantCustomerContacts::getContactsTel, tenantCustomerContactsQueryParam.getContactsTel())
				.eq(StringUtils.isNotEmpty(tenantCustomerContactsQueryParam.getContactsEmail()), TenantCustomerContacts::getContactsEmail, tenantCustomerContactsQueryParam.getContactsEmail())
				.eq(StringUtils.isNotEmpty(tenantCustomerContactsQueryParam.getContactsWx()), TenantCustomerContacts::getContactsWx, tenantCustomerContactsQueryParam.getContactsWx())
				.eq(StringUtils.isNotEmpty(tenantCustomerContactsQueryParam.getContactsQq()), TenantCustomerContacts::getContactsQq, tenantCustomerContactsQueryParam.getContactsQq())
				.eq(StringUtils.isNotEmpty(tenantCustomerContactsQueryParam.getContactsMemo()), TenantCustomerContacts::getContactsMemo, tenantCustomerContactsQueryParam.getContactsMemo())
				.eq(tenantCustomerContactsQueryParam.getAddTime() != null, TenantCustomerContacts::getAddTime, tenantCustomerContactsQueryParam.getAddTime())
				.ge(tenantCustomerContactsQueryParam.getAddTimeStart() != null, TenantCustomerContacts::getAddTime,tenantCustomerContactsQueryParam.getAddTimeStart() == null ? null: DateUtil.beginOfDay(tenantCustomerContactsQueryParam.getAddTimeStart()))
				.le(tenantCustomerContactsQueryParam.getAddTimeEnd() != null, TenantCustomerContacts::getAddTime,tenantCustomerContactsQueryParam.getAddTimeEnd() == null ? null: DateUtil.endOfDay(tenantCustomerContactsQueryParam.getAddTimeEnd()))
				.eq(tenantCustomerContactsQueryParam.getUpdateTime() != null, TenantCustomerContacts::getUpdateTime, tenantCustomerContactsQueryParam.getUpdateTime())
				.ge(tenantCustomerContactsQueryParam.getUpdateTimeStart() != null, TenantCustomerContacts::getUpdateTime,tenantCustomerContactsQueryParam.getUpdateTimeStart() == null ? null: DateUtil.beginOfDay(tenantCustomerContactsQueryParam.getUpdateTimeStart()))
				.le(tenantCustomerContactsQueryParam.getUpdateTimeEnd() != null, TenantCustomerContacts::getUpdateTime,tenantCustomerContactsQueryParam.getUpdateTimeEnd() == null ? null: DateUtil.endOfDay(tenantCustomerContactsQueryParam.getUpdateTimeEnd()))
				;

		String[] queryCols = tenantCustomerContactsQueryParam.getQueryCol();
		String[] queryTypes = tenantCustomerContactsQueryParam.getQueryType();
		String[] queryValues = tenantCustomerContactsQueryParam.getQueryValue();
		if (queryCols != null && queryCols.length > 0) {
			for (int i = 0; i < queryCols.length; i++) {
				queryWrapperTenantCustomerContacts.lambda()//
						.apply("=".equals(queryTypes[i]), queryCols[i] + "={0}", queryValues[i])//
						.apply("like".equals(queryTypes[i]), queryCols[i] + " like CONCAT('%',{0},'%')", queryValues[i])//
				;
			}
		}
		
		int count = tenantCustomerContactsService.count(queryWrapperTenantCustomerContacts);

		return count;
	}
	
	@ApiOperation(value = "根据参数查询用户联系方式列表")
	@RequestMapping(value = "/tenant-customer-contactss", method = RequestMethod.GET)
	public Page<TenantCustomerContactsVo> page(@RequestBody TenantCustomerContactsQueryParam tenantCustomerContactsQueryParam,
			@RequestParam(value = "page", defaultValue = "1") int page, //
			@RequestParam(value = "rows", defaultValue = "10") int rows, //
			@RequestParam(value = "sort", required = false) String sort, // 排序列字段名
			@RequestParam(value = "order", required = false) String order // 可以是 'asc' 或者 'desc'，默认值是 'asc'
	) {
		IPage<TenantCustomerContacts> pageTenantCustomerContacts = new Page<TenantCustomerContacts>(page, rows);
		QueryWrapper<TenantCustomerContacts> queryWrapperTenantCustomerContacts = new QueryWrapper<TenantCustomerContacts>();
		queryWrapperTenantCustomerContacts.orderBy(StringUtils.isNotBlank(sort), "asc".equals(order), sort);
		queryWrapperTenantCustomerContacts.lambda()
				.eq(StringUtils.isNotEmpty(tenantCustomerContactsQueryParam.getId()), TenantCustomerContacts::getId, tenantCustomerContactsQueryParam.getId())
				.eq(StringUtils.isNotEmpty(tenantCustomerContactsQueryParam.getTenantId()), TenantCustomerContacts::getTenantId, tenantCustomerContactsQueryParam.getTenantId())
				.eq(StringUtils.isNotEmpty(tenantCustomerContactsQueryParam.getCustomerId()), TenantCustomerContacts::getCustomerId, tenantCustomerContactsQueryParam.getCustomerId())
				.eq(StringUtils.isNotEmpty(tenantCustomerContactsQueryParam.getCustomerCode()), TenantCustomerContacts::getCustomerCode, tenantCustomerContactsQueryParam.getCustomerCode())
				.eq(StringUtils.isNotEmpty(tenantCustomerContactsQueryParam.getContactsName()), TenantCustomerContacts::getContactsName, tenantCustomerContactsQueryParam.getContactsName())
				.eq(StringUtils.isNotEmpty(tenantCustomerContactsQueryParam.getContactsAddress()), TenantCustomerContacts::getContactsAddress, tenantCustomerContactsQueryParam.getContactsAddress())
				.eq(tenantCustomerContactsQueryParam.getContactsMain() != null, TenantCustomerContacts::getContactsMain, tenantCustomerContactsQueryParam.getContactsMain())
				.eq(tenantCustomerContactsQueryParam.getContactsSex() != null, TenantCustomerContacts::getContactsSex, tenantCustomerContactsQueryParam.getContactsSex())
				.eq(tenantCustomerContactsQueryParam.getContactsBirthday() != null, TenantCustomerContacts::getContactsBirthday, tenantCustomerContactsQueryParam.getContactsBirthday())
				.ge(tenantCustomerContactsQueryParam.getContactsBirthdayStart() != null, TenantCustomerContacts::getContactsBirthday,tenantCustomerContactsQueryParam.getContactsBirthdayStart() == null ? null: DateUtil.beginOfDay(tenantCustomerContactsQueryParam.getContactsBirthdayStart()))
				.le(tenantCustomerContactsQueryParam.getContactsBirthdayEnd() != null, TenantCustomerContacts::getContactsBirthday,tenantCustomerContactsQueryParam.getContactsBirthdayEnd() == null ? null: DateUtil.endOfDay(tenantCustomerContactsQueryParam.getContactsBirthdayEnd()))
				.eq(StringUtils.isNotEmpty(tenantCustomerContactsQueryParam.getContactsMobile()), TenantCustomerContacts::getContactsMobile, tenantCustomerContactsQueryParam.getContactsMobile())
				.eq(StringUtils.isNotEmpty(tenantCustomerContactsQueryParam.getContactsTel()), TenantCustomerContacts::getContactsTel, tenantCustomerContactsQueryParam.getContactsTel())
				.eq(StringUtils.isNotEmpty(tenantCustomerContactsQueryParam.getContactsEmail()), TenantCustomerContacts::getContactsEmail, tenantCustomerContactsQueryParam.getContactsEmail())
				.eq(StringUtils.isNotEmpty(tenantCustomerContactsQueryParam.getContactsWx()), TenantCustomerContacts::getContactsWx, tenantCustomerContactsQueryParam.getContactsWx())
				.eq(StringUtils.isNotEmpty(tenantCustomerContactsQueryParam.getContactsQq()), TenantCustomerContacts::getContactsQq, tenantCustomerContactsQueryParam.getContactsQq())
				.eq(StringUtils.isNotEmpty(tenantCustomerContactsQueryParam.getContactsMemo()), TenantCustomerContacts::getContactsMemo, tenantCustomerContactsQueryParam.getContactsMemo())
				.eq(tenantCustomerContactsQueryParam.getAddTime() != null, TenantCustomerContacts::getAddTime, tenantCustomerContactsQueryParam.getAddTime())
				.ge(tenantCustomerContactsQueryParam.getAddTimeStart() != null, TenantCustomerContacts::getAddTime,tenantCustomerContactsQueryParam.getAddTimeStart() == null ? null: DateUtil.beginOfDay(tenantCustomerContactsQueryParam.getAddTimeStart()))
				.le(tenantCustomerContactsQueryParam.getAddTimeEnd() != null, TenantCustomerContacts::getAddTime,tenantCustomerContactsQueryParam.getAddTimeEnd() == null ? null: DateUtil.endOfDay(tenantCustomerContactsQueryParam.getAddTimeEnd()))
				.eq(tenantCustomerContactsQueryParam.getUpdateTime() != null, TenantCustomerContacts::getUpdateTime, tenantCustomerContactsQueryParam.getUpdateTime())
				.ge(tenantCustomerContactsQueryParam.getUpdateTimeStart() != null, TenantCustomerContacts::getUpdateTime,tenantCustomerContactsQueryParam.getUpdateTimeStart() == null ? null: DateUtil.beginOfDay(tenantCustomerContactsQueryParam.getUpdateTimeStart()))
				.le(tenantCustomerContactsQueryParam.getUpdateTimeEnd() != null, TenantCustomerContacts::getUpdateTime,tenantCustomerContactsQueryParam.getUpdateTimeEnd() == null ? null: DateUtil.endOfDay(tenantCustomerContactsQueryParam.getUpdateTimeEnd()))
				;

		String[] queryCols = tenantCustomerContactsQueryParam.getQueryCol();
		String[] queryTypes = tenantCustomerContactsQueryParam.getQueryType();
		String[] queryValues = tenantCustomerContactsQueryParam.getQueryValue();
		if (queryCols != null && queryCols.length > 0) {
			for (int i = 0; i < queryCols.length; i++) {
				queryWrapperTenantCustomerContacts.lambda()//
						.apply("=".equals(queryTypes[i]), queryCols[i] + "={0}", queryValues[i])//
						.apply("like".equals(queryTypes[i]), queryCols[i] + " like CONCAT('%',{0},'%')", queryValues[i])//
				;
			}
		}
		
		IPage<TenantCustomerContacts> tenantCustomerContactsPage = tenantCustomerContactsService.page(pageTenantCustomerContacts, queryWrapperTenantCustomerContacts);

		Page<TenantCustomerContactsVo> tenantCustomerContactsVoPage = new Page<TenantCustomerContactsVo>(page, rows);
		tenantCustomerContactsVoPage.setCurrent(tenantCustomerContactsPage.getCurrent());
		tenantCustomerContactsVoPage.setPages(tenantCustomerContactsPage.getPages());
		tenantCustomerContactsVoPage.setSize(tenantCustomerContactsPage.getSize());
		tenantCustomerContactsVoPage.setTotal(tenantCustomerContactsPage.getTotal());
		tenantCustomerContactsVoPage.setRecords(tenantCustomerContactsPage.getRecords().stream()//
				.map(e -> entity2vo(e))//
				.collect(Collectors.toList()));

		return tenantCustomerContactsVoPage;
	}
	
	@ApiOperation(value = "新增用户联系方式")
	@RequestMapping(value = "/tenant-customer-contactss", method = RequestMethod.POST)
	public String save(@RequestBody TenantCustomerContactsAddParam tenantCustomerContactsAddParam) {
		return tenantCustomerContactsService.save(tenantCustomerContactsAddParam);
	}

	@ApiOperation(value = "更新用户联系方式全部信息")
	@RequestMapping(value = "/tenant-customer-contactss/{id}", method = RequestMethod.PUT)
	public boolean updateById(@PathVariable("id") String id, @RequestBody TenantCustomerContactsUpdateParam tenantCustomerContactsUpdateParam) {
		tenantCustomerContactsUpdateParam.setId(id);
		return tenantCustomerContactsService.updateById(tenantCustomerContactsUpdateParam);
	}

	@ApiOperation(value = "根据ID删除用户联系方式")
	@RequestMapping(value = "/tenant-customer-contactss/{id}", method = RequestMethod.DELETE)
	public CommonResult<Object> removeById(@PathVariable("id") String id) {
		boolean success = tenantCustomerContactsService.removeById(id);
		return success ? CommonResult.success(success) : CommonResult.failed();
	}

	private TenantCustomerContactsVo entity2vo(TenantCustomerContacts tenantCustomerContacts) {
		if (tenantCustomerContacts == null) {
			return null;
		}

		TenantCustomerContactsVo tenantCustomerContactsVo = TranslateUtil.translate(tenantCustomerContacts, TenantCustomerContactsVo.class);
		if (StringUtils.isEmpty(tenantCustomerContactsVo.getTenantName())) {
			TenantInfo tenantInfo = tenantInfoService.getDictionaryById(tenantCustomerContacts.getTenantId());
			if (tenantInfo != null) {
				tenantCustomerContactsVo.setTenantName(tenantInfo.getTenantName());
			}
		}
		return tenantCustomerContactsVo;
	}

}
