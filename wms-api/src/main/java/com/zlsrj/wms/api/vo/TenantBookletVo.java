package com.zlsrj.wms.api.vo;

import java.io.Serializable;
import java.util.Date;

import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.serializer.ToStringSerializer;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel(value = "TenantBooklet对象", description = "租户表册")
public class TenantBookletVo implements Serializable {

	private static final long serialVersionUID = 3151088412941011240L;

	@ApiModelProperty(value = "表册ID")
	@JSONField(serializeUsing = ToStringSerializer.class)
	private Long id;

	@ApiModelProperty(value = "租户ID")
	@JSONField(serializeUsing = ToStringSerializer.class)
	private Long tenantId;

	@ApiModelProperty(value = "租户ID")
	private String tenantName;

	@ApiModelProperty(value = "所属部门ID")
	@JSONField(serializeUsing = ToStringSerializer.class)
	private Long bookletDepartmentId;

	@ApiModelProperty(value = "所属供水区域ID")
	@JSONField(serializeUsing = ToStringSerializer.class)
	private Long bookletWaterAreaId;

	@ApiModelProperty(value = "表册代码")
	private String bookletCode;

	@ApiModelProperty(value = "表册名称")
	private String bookletName;

	@ApiModelProperty(value = "抄表员ID")
	@JSONField(serializeUsing = ToStringSerializer.class)
	private Long bookletReadEmployeeId;

	@ApiModelProperty(value = "收费员ID")
	@JSONField(serializeUsing = ToStringSerializer.class)
	private Long bookletChargeEmployeeId;

	@ApiModelProperty(value = "结算间隔周期[月]")
	private Integer bookletSettleCycleInterval;

	@ApiModelProperty(value = "最后一次结算月份")
	private Date bookletLastSettleMonth;

	@ApiModelProperty(value = "下次计划结算月份")
	private Date bookletNextSettleMonth;

	@ApiModelProperty(value = "表册状态（1：抄表进行中；2：抄表截止）")
	private Integer bookletStatus;

	@ApiModelProperty(value = "表册可用状态（1：可用；0：禁用）")
	private Integer bookletOn;

}
