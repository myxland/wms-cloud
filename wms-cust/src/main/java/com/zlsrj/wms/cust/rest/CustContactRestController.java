package com.zlsrj.wms.cust.rest;

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
import com.zlsrj.wms.api.dto.CustContactQueryParam;
import com.zlsrj.wms.api.entity.CustContact;
import com.zlsrj.wms.api.vo.CustContactVo;
import com.zlsrj.wms.common.api.CommonResult;
import com.zlsrj.wms.cust.service.IIdService;
import com.zlsrj.wms.cust.service.ICustContactService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

@Api(value = "用户联系人", tags = { "用户联系人操作接口" })
@RestController
@Slf4j
public class CustContactRestController {

	@Autowired
	private ICustContactService custContactService;
	@Autowired
	private IIdService idService;

	@ApiOperation(value = "根据ID查询用户联系人")
	@RequestMapping(value = "/cust-contacts/{id}", method = RequestMethod.GET)
	public CustContactVo getById(@PathVariable("id") Long id) {
		CustContact custContact = custContactService.getById(id);

		return entity2vo(custContact);
	}

	@ApiOperation(value = "根据参数查询用户联系人列表")
	@RequestMapping(value = "/cust-contacts", method = RequestMethod.GET)
	public Page<CustContactVo> page(@RequestBody CustContactQueryParam custContactQueryParam,
			@RequestParam(value = "page", defaultValue = "1") int page, //
			@RequestParam(value = "rows", defaultValue = "10") int rows, //
			@RequestParam(value = "sort") String sort, // 排序列字段名
			@RequestParam(value = "order") String order // 可以是 'asc' 或者 'desc'，默认值是 'asc'
	) {
		IPage<CustContact> pageCustContact = new Page<CustContact>(page, rows);
		QueryWrapper<CustContact> queryWrapperCustContact = new QueryWrapper<CustContact>();
		queryWrapperCustContact.orderBy(StringUtils.isNotEmpty(sort), "desc".equals(order), sort);
		queryWrapperCustContact.lambda()
				.eq(custContactQueryParam.getId() != null, CustContact::getId, custContactQueryParam.getId())
				.eq(custContactQueryParam.getTenantId() != null, CustContact::getTenantId, custContactQueryParam.getTenantId())
				.eq(custContactQueryParam.getCustId() != null, CustContact::getCustId, custContactQueryParam.getCustId())
				.eq(custContactQueryParam.getContactName() != null, CustContact::getContactName, custContactQueryParam.getContactName())
				.eq(custContactQueryParam.getMainOn() != null, CustContact::getMainOn, custContactQueryParam.getMainOn())
				.eq(custContactQueryParam.getGender() != null, CustContact::getGender, custContactQueryParam.getGender())
				.eq(custContactQueryParam.getBirthday() != null, CustContact::getBirthday, custContactQueryParam.getBirthday())
				.eq(custContactQueryParam.getMobile() != null, CustContact::getMobile, custContactQueryParam.getMobile())
				.eq(custContactQueryParam.getTel() != null, CustContact::getTel, custContactQueryParam.getTel())
				.eq(custContactQueryParam.getEmail() != null, CustContact::getEmail, custContactQueryParam.getEmail())
				.eq(custContactQueryParam.getPersonalWx() != null, CustContact::getPersonalWx, custContactQueryParam.getPersonalWx())
				.eq(custContactQueryParam.getQq() != null, CustContact::getQq, custContactQueryParam.getQq())
				.eq(custContactQueryParam.getRemark() != null, CustContact::getRemark, custContactQueryParam.getRemark())
				;

		IPage<CustContact> custContactPage = custContactService.page(pageCustContact, queryWrapperCustContact);

		Page<CustContactVo> custContactVoPage = new Page<CustContactVo>(page, rows);
		custContactVoPage.setCurrent(custContactPage.getCurrent());
		custContactVoPage.setPages(custContactPage.getPages());
		custContactVoPage.setSize(custContactPage.getSize());
		custContactVoPage.setTotal(custContactPage.getTotal());
		custContactVoPage.setRecords(custContactPage.getRecords().stream()//
				.map(e -> entity2vo(e))//
				.collect(Collectors.toList()));

		return custContactVoPage;
	}

	@ApiOperation(value = "新增用户联系人")
	@RequestMapping(value = "/cust-contacts", method = RequestMethod.POST)
	public CustContactVo save(@RequestBody CustContact custContact) {
		if (custContact.getId() == null || custContact.getId().compareTo(0L) <= 0) {
			custContact.setId(idService.selectId());
		}
		boolean success = custContactService.save(custContact);
		if (success) {
			CustContact custContactDatabase = custContactService.getById(custContact.getId());
			return entity2vo(custContactDatabase);
		}
		log.info("save CustContact fail，{}", ToStringBuilder.reflectionToString(custContact, ToStringStyle.JSON_STYLE));
		return null;
	}

	@ApiOperation(value = "更新用户联系人全部信息")
	@RequestMapping(value = "/cust-contacts/{id}", method = RequestMethod.PUT)
	public CustContactVo updateById(@PathVariable("id") Long id, @RequestBody CustContact custContact) {
		custContact.setId(id);
		boolean success = custContactService.updateById(custContact);
		if (success) {
			CustContact custContactDatabase = custContactService.getById(id);
			return entity2vo(custContactDatabase);
		}
		log.info("update CustContact fail，{}", ToStringBuilder.reflectionToString(custContact, ToStringStyle.JSON_STYLE));
		return null;
	}

	@ApiOperation(value = "根据参数更新用户联系人信息")
	@RequestMapping(value = "/cust-contacts/{id}", method = RequestMethod.PATCH)
	public CustContactVo updatePatchById(@PathVariable("id") Long id, @RequestBody CustContact custContact) {
		UpdateWrapper<CustContact> updateWrapperCustContact = new UpdateWrapper<CustContact>();
		updateWrapperCustContact.lambda()//
				.eq(CustContact::getId, id)
				// .set(custContact.getId() != null, CustContact::getId, custContact.getId())
				.set(custContact.getTenantId() != null, CustContact::getTenantId, custContact.getTenantId())
				.set(custContact.getCustId() != null, CustContact::getCustId, custContact.getCustId())
				.set(custContact.getContactName() != null, CustContact::getContactName, custContact.getContactName())
				.set(custContact.getMainOn() != null, CustContact::getMainOn, custContact.getMainOn())
				.set(custContact.getGender() != null, CustContact::getGender, custContact.getGender())
				.set(custContact.getBirthday() != null, CustContact::getBirthday, custContact.getBirthday())
				.set(custContact.getMobile() != null, CustContact::getMobile, custContact.getMobile())
				.set(custContact.getTel() != null, CustContact::getTel, custContact.getTel())
				.set(custContact.getEmail() != null, CustContact::getEmail, custContact.getEmail())
				.set(custContact.getPersonalWx() != null, CustContact::getPersonalWx, custContact.getPersonalWx())
				.set(custContact.getQq() != null, CustContact::getQq, custContact.getQq())
				.set(custContact.getRemark() != null, CustContact::getRemark, custContact.getRemark())
				;

		boolean success = custContactService.update(updateWrapperCustContact);
		if (success) {
			CustContact custContactDatabase = custContactService.getById(id);
			return entity2vo(custContactDatabase);
		}
		log.info("partial update CustContact fail，{}",
				ToStringBuilder.reflectionToString(custContact, ToStringStyle.JSON_STYLE));
		return null;
	}

	@ApiOperation(value = "根据ID删除用户联系人")
	@RequestMapping(value = "/cust-contacts/{id}", method = RequestMethod.DELETE)
	public CommonResult<Object> removeById(@PathVariable("id") Long id) {
		boolean success = custContactService.removeById(id);
		return success ? CommonResult.success(success) : CommonResult.failed();
	}

	private CustContactVo entity2vo(CustContact custContact) {
		String jsonString = JSON.toJSONString(custContact);
		CustContactVo custContactVo = JSON.parseObject(jsonString, CustContactVo.class);
		return custContactVo;
	}

}
