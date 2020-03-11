package com.zlsrj.wms.api.dto;

import java.io.Serializable;
import java.util.Date;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel(value = "TenantBooklet查询参数", description = "租户表册")
public class TenantBookletQueryParam implements Serializable {

	private static final long serialVersionUID = 1334121534102810113L;

	@ApiModelProperty(value = "表册ID")
	private String id;

	@ApiModelProperty(value = "租户ID")
	private String tenantId;

	@ApiModelProperty(value = "所属部门ID")
	private String bookletDepartmentId;

	@ApiModelProperty(value = "所属供水区域ID")
	private String bookletWaterAreaId;

	@ApiModelProperty(value = "表册代码")
	private String bookletCode;

	@ApiModelProperty(value = "表册名称")
	private String bookletName;

	@ApiModelProperty(value = "抄表员ID")
	private String bookletReadEmployeeId;

	@ApiModelProperty(value = "收费员ID")
	private String bookletChargeEmployeeId;

	@ApiModelProperty(value = "结算间隔周期[月]")
	private Integer bookletSettleCycleInterval;

	@ApiModelProperty(value = "最后一次结算月份")
	private Date bookletLastSettleMonth;

	@ApiModelProperty(value = "最后一次结算月份开始")
	private Date bookletLastSettleMonthStart;

	@ApiModelProperty(value = "最后一次结算月份结束")
	private Date bookletLastSettleMonthEnd;

	@ApiModelProperty(value = "下次计划结算月份")
	private Date bookletNextSettleMonth;

	@ApiModelProperty(value = "下次计划结算月份开始")
	private Date bookletNextSettleMonthStart;

	@ApiModelProperty(value = "下次计划结算月份结束")
	private Date bookletNextSettleMonthEnd;

	@ApiModelProperty(value = "表册状态（1：抄表进行中；2：抄表截止）")
	private Integer bookletStatus;

	@ApiModelProperty(value = "表册可用状态（1：可用；0：禁用）")
	private Integer bookletOn;

}

