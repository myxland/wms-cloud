package com.zlsrj.wms.api.vo;

import java.io.Serializable;
import java.math.BigDecimal;

import com.alibaba.fastjson.annotation.JSONField;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel(value = "TenantInfo对象", description = "租户表")
public class TenantInfoDataVo implements Serializable {

	private static final long serialVersionUID = 1511010121441314139L;

	@ApiModelProperty(value = "租户名称")
	@JSONField(name="tenant_name")
	private String tenantName;
	
	@ApiModelProperty(value = "租户显示名称")
	@JSONField(name="tenant_display_name")
	private String tenantDisplayName;

	@ApiModelProperty(value = "账户余额")
	@JSONField(name="tenant_balance")
	private BigDecimal tenantBalance;

	@ApiModelProperty(value = "透支额度")
	@JSONField(name="tenant_overdraw")
	private BigDecimal tenantOverdraw;

}
