package com.zlsrj.wms.api.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel(value = "TenantSystem查询参数", description = "租户开通模块清单")
public class TenantSystemQueryParam implements Serializable {

	private static final long serialVersionUID = 1913131161331511036L;

	@ApiModelProperty(value = "系统ID")
	private String id;

	@ApiModelProperty(value = "租户编号")
	private String tenantId;

	@ApiModelProperty(value = "模块编号")
	private Long sysId;

	@ApiModelProperty(value = "模块显示名称")
	private String sysDispName;

	@ApiModelProperty(value = "模块排序")
	private Integer sysOrder;

	@ApiModelProperty(value = "开通版本（1：基础版；2：高级版；3：旗舰版）")
	private Integer sysEdition;

	@ApiModelProperty(value = "模块状态（1：开通；0：未开通）")
	private Integer sysStatus;

	@ApiModelProperty(value = "价格体系（1：标准价格；2：指定价格）")
	private Integer sysPriceType;

	@ApiModelProperty(value = "开通时间")
	private Date sysOpenDate;

	@ApiModelProperty(value = "开通时间开始")
	private Date sysOpenDateStart;

	@ApiModelProperty(value = "开通时间结束")
	private Date sysOpenDateEnd;

	@ApiModelProperty(value = "到期时间")
	private Date sysEndDate;

	@ApiModelProperty(value = "到期时间开始")
	private Date sysEndDateStart;

	@ApiModelProperty(value = "到期时间结束")
	private Date sysEndDateEnd;

	@ApiModelProperty(value = "开始计费日期")
	private Date sysStartChargeDate;

	@ApiModelProperty(value = "开始计费日期开始")
	private Date sysStartChargeDateStart;

	@ApiModelProperty(value = "开始计费日期结束")
	private Date sysStartChargeDateEnd;

	@ApiModelProperty(value = "欠费金额")
	private BigDecimal sysArrears;

	@ApiModelProperty(value = "透支额度")
	private BigDecimal sysOverdraft;

}

