package com.zlsrj.wms.api.entity;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@Builder
@TableName("tenant_booklet")
@ApiModel(value = "TenantBooklet对象", description = "租户表册")
public class TenantBooklet implements Serializable {

	private static final long serialVersionUID = 1421039814107691546L;

	@ApiModelProperty(value = "表册ID")
	@TableId(value = "id", type = IdType.INPUT)
	private String id;

	@ApiModelProperty(value = "租户ID")
	@TableField("tenant_id")
	private String tenantId;

	@ApiModelProperty(value = "所属部门ID")
	@TableField("booklet_department_id")
	private String bookletDepartmentId;

	@ApiModelProperty(value = "所属供水区域ID")
	@TableField("booklet_water_area_id")
	private String bookletWaterAreaId;

	@ApiModelProperty(value = "表册代码")
	@TableField("booklet_code")
	private String bookletCode;

	@ApiModelProperty(value = "表册名称")
	@TableField("booklet_name")
	private String bookletName;

	@ApiModelProperty(value = "抄表员ID")
	@TableField("booklet_read_employee_id")
	private String bookletReadEmployeeId;

	@ApiModelProperty(value = "收费员ID")
	@TableField("booklet_charge_employee_id")
	private String bookletChargeEmployeeId;

	@ApiModelProperty(value = "结算间隔周期[月]")
	@TableField("booklet_settle_cycle_interval")
	private Integer bookletSettleCycleInterval;

	@ApiModelProperty(value = "最后一次结算月份")
	@TableField("booklet_last_settle_month")
	private Date bookletLastSettleMonth;

	@ApiModelProperty(value = "下次计划结算月份")
	@TableField("booklet_next_settle_month")
	private Date bookletNextSettleMonth;

	@ApiModelProperty(value = "表册状态（1：抄表进行中；2：抄表截止）")
	@TableField("booklet_status")
	private Integer bookletStatus;

	@ApiModelProperty(value = "表册可用状态（1：可用；0：禁用）")
	@TableField("booklet_on")
	private Integer bookletOn;


}
