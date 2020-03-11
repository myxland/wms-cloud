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
@TableName("t_read_booklet")
@ApiModel(value = "ReadBooklet对象", description = "表册信息")
public class ReadBooklet implements Serializable {

	private static final long serialVersionUID = 1091351512157791081L;

	@ApiModelProperty(value = "系统编号")
	@TableId(value = "id", type = IdType.INPUT)
	private String id;

	@ApiModelProperty(value = "租户编号")
	@TableField("tenant_id")
	private String tenantId;

	@ApiModelProperty(value = "表册名称")
	@TableField("booklet_name")
	private String bookletName;

	@ApiModelProperty(value = "表册类型（1：远传表；2：机械表）")
	@TableField("booklet_type")
	private Integer bookletType;

	@ApiModelProperty(value = "抄表负责人编号")
	@TableField("read_emp_id")
	private Long readEmpId;

	@ApiModelProperty(value = "收费负责人编号")
	@TableField("pay_emp_id")
	private Long payEmpId;

	@ApiModelProperty(value = "抄表间隔周期_月")
	@TableField("calc_cycle_interval")
	private Integer calcCycleInterval;

	@ApiModelProperty(value = "最后一次抄表月份")
	@TableField("calc_month_last")
	private Date calcMonthLast;

	@ApiModelProperty(value = "下次抄表月份")
	@TableField("calc_month_next")
	private Date calcMonthNext;


}
