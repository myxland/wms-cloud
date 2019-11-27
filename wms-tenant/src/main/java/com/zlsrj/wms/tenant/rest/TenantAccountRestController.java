package com.zlsrj.wms.tenant.rest;

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
import com.zlsrj.wms.api.dto.TenantAccountQueryParam;
import com.zlsrj.wms.api.entity.TenantInfo;
import com.zlsrj.wms.api.entity.TenantAccount;
import com.zlsrj.wms.api.vo.TenantAccountVo;
import com.zlsrj.wms.common.api.CommonResult;
import com.zlsrj.wms.tenant.service.IIdService;
import com.zlsrj.wms.tenant.service.ITenantInfoService;
import com.zlsrj.wms.tenant.service.ITenantAccountService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

@Api(value = "租户账户", tags = { "租户账户操作接口" })
@RestController
@Slf4j
public class TenantAccountRestController {

	@Autowired
	private ITenantAccountService tenantAccountService;
	@Autowired
	private ITenantInfoService tenantInfoService;
	@Autowired
	private IIdService idService;

	@ApiOperation(value = "根据ID查询租户账户")
	@RequestMapping(value = "/tenant-accounts/{id}", method = RequestMethod.GET)
	public TenantAccountVo getById(@PathVariable("id") Long id) {
		TenantAccount tenantAccount = tenantAccountService.getById(id);

		return entity2vo(tenantAccount);
	}

	@ApiOperation(value = "根据ID查询租户账户")
	@RequestMapping(value = "/tenant-accounts/tenant-id/{tenant-id}", method = RequestMethod.GET)
	public TenantAccountVo getByTenantId(@PathVariable("tenant-id") Long tenantId) {
		QueryWrapper<TenantAccount> queryWrapperTenantAccount = new QueryWrapper<TenantAccount>();
		queryWrapperTenantAccount.lambda().eq(TenantAccount::getTenantId, tenantId);
		TenantAccount tenantAccount = tenantAccountService.getOne(queryWrapperTenantAccount);

		return entity2vo(tenantAccount);
	}

	@ApiOperation(value = "根据参数查询租户账户列表")
	@RequestMapping(value = "/tenant-accounts", method = RequestMethod.GET)
	public Page<TenantAccountVo> page(@RequestBody TenantAccountQueryParam tenantAccountQueryParam,
			@RequestParam(value = "page", defaultValue = "1") int page, //
			@RequestParam(value = "rows", defaultValue = "10") int rows, //
			@RequestParam(value = "sort") String sort, // 排序列字段名
			@RequestParam(value = "order") String order // 可以是 'asc' 或者 'desc'，默认值是 'asc'
	) {
		IPage<TenantAccount> pageTenantAccount = new Page<TenantAccount>(page, rows);
		QueryWrapper<TenantAccount> queryWrapperTenantAccount = new QueryWrapper<TenantAccount>();
		queryWrapperTenantAccount.orderBy(StringUtils.isNotEmpty(sort), "desc".equals(order), sort);
		queryWrapperTenantAccount.lambda()
				.eq(tenantAccountQueryParam.getId() != null, TenantAccount::getId, tenantAccountQueryParam.getId())
				.eq(tenantAccountQueryParam.getTenantId() != null, TenantAccount::getTenantId,
						tenantAccountQueryParam.getTenantId())
				.eq(tenantAccountQueryParam.getAccountBalance() != null, TenantAccount::getAccountBalance,
						tenantAccountQueryParam.getAccountBalance());

		IPage<TenantAccount> tenantAccountPage = tenantAccountService.page(pageTenantAccount,
				queryWrapperTenantAccount);

		Page<TenantAccountVo> tenantAccountVoPage = new Page<TenantAccountVo>(page, rows);
		tenantAccountVoPage.setCurrent(tenantAccountPage.getCurrent());
		tenantAccountVoPage.setPages(tenantAccountPage.getPages());
		tenantAccountVoPage.setSize(tenantAccountPage.getSize());
		tenantAccountVoPage.setTotal(tenantAccountPage.getTotal());
		tenantAccountVoPage.setRecords(tenantAccountPage.getRecords().stream()//
				.map(e -> entity2vo(e))//
				.collect(Collectors.toList()));

		return tenantAccountVoPage;
	}

	@ApiOperation(value = "新增租户账户")
	@RequestMapping(value = "/tenant-accounts", method = RequestMethod.POST)
	public TenantAccountVo save(@RequestBody TenantAccount tenantAccount) {
		if (tenantAccount.getId() == null || tenantAccount.getId().compareTo(0L) <= 0) {
			tenantAccount.setId(idService.selectId());
		}
		boolean success = tenantAccountService.save(tenantAccount);
		if (success) {
			TenantAccount tenantAccountDatabase = tenantAccountService.getById(tenantAccount.getId());
			return entity2vo(tenantAccountDatabase);
		}
		log.info("save TenantAccount fail，{}",
				ToStringBuilder.reflectionToString(tenantAccount, ToStringStyle.JSON_STYLE));
		return null;
	}

	@ApiOperation(value = "更新租户账户全部信息")
	@RequestMapping(value = "/tenant-accounts/{id}", method = RequestMethod.PUT)
	public TenantAccountVo updateById(@PathVariable("id") Long id, @RequestBody TenantAccount tenantAccount) {
		tenantAccount.setId(id);
		boolean success = tenantAccountService.updateById(tenantAccount);
		if (success) {
			TenantAccount tenantAccountDatabase = tenantAccountService.getById(id);
			return entity2vo(tenantAccountDatabase);
		}
		log.info("update TenantAccount fail，{}",
				ToStringBuilder.reflectionToString(tenantAccount, ToStringStyle.JSON_STYLE));
		return null;
	}

	@ApiOperation(value = "根据参数更新租户账户信息")
	@RequestMapping(value = "/tenant-accounts/{id}", method = RequestMethod.PATCH)
	public TenantAccountVo updatePatchById(@PathVariable("id") Long id, @RequestBody TenantAccount tenantAccount) {
		UpdateWrapper<TenantAccount> updateWrapperTenantAccount = new UpdateWrapper<TenantAccount>();
		updateWrapperTenantAccount.lambda()//
				.eq(TenantAccount::getId, id)
				// .set(tenantAccount.getId() != null, TenantAccount::getId,
				// tenantAccount.getId())
				.set(tenantAccount.getTenantId() != null, TenantAccount::getTenantId, tenantAccount.getTenantId())
				.set(tenantAccount.getAccountBalance() != null, TenantAccount::getAccountBalance,
						tenantAccount.getAccountBalance());

		boolean success = tenantAccountService.update(updateWrapperTenantAccount);
		if (success) {
			TenantAccount tenantAccountDatabase = tenantAccountService.getById(id);
			return entity2vo(tenantAccountDatabase);
		}
		log.info("partial update TenantAccount fail，{}",
				ToStringBuilder.reflectionToString(tenantAccount, ToStringStyle.JSON_STYLE));
		return null;
	}

	@ApiOperation(value = "根据ID删除租户账户")
	@RequestMapping(value = "/tenant-accounts/{id}", method = RequestMethod.DELETE)
	public CommonResult<Object> removeById(@PathVariable("id") Long id) {
		boolean success = tenantAccountService.removeById(id);
		return success ? CommonResult.success(success) : CommonResult.failed();
	}

	private TenantAccountVo entity2vo(TenantAccount tenantAccount) {
		if (tenantAccount == null) {
			return null;
		}
		String jsonString = JSON.toJSONString(tenantAccount);
		TenantAccountVo tenantAccountVo = JSON.parseObject(jsonString, TenantAccountVo.class);
		if (StringUtils.isEmpty(tenantAccountVo.getTenantName())) {
			TenantInfo tenantInfo = tenantInfoService.getById(tenantAccount.getTenantId());
			if (tenantInfo != null) {
				tenantAccountVo.setTenantName(tenantInfo.getTenantName());
			}
		}
		return tenantAccountVo;
	}

}
