package com.zlsrj.wms.api.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@Builder
@TableName("t_op_tenant_system")
@ApiModel(value = "TenantSystem对象", description = "租户开通模块清单")
public class TenantSystem implements Serializable {

	private static final long serialVersionUID = 1211711524791261015L;

	@ApiModelProperty(value = "系统ID")
	@TableId(value = "id", type = IdType.INPUT)
	private Long id;

	@ApiModelProperty(value = "租户编号")
	@TableField("tenant_id")
	private Long tenantId;

	@ApiModelProperty(value = "模块编号")
	@TableField("sys_id")
	private Long sysId;

	@ApiModelProperty(value = "模块显示名称")
	@TableField("sys_disp_name")
	private String sysDispName;

	@ApiModelProperty(value = "模块排序")
	@TableField("sys_order")
	private Integer sysOrder;

	@ApiModelProperty(value = "开通版本（基础版/高级版/旗舰版）")
	@TableField("sys_edition")
	private Integer sysEdition;

	@ApiModelProperty(value = "模块状态（1开通/0未开通）")
	@TableField("sys_status")
	private Integer sysStatus;

	@ApiModelProperty(value = "价格体系（标准价格/指定价格）")
	@TableField("sys_price_type")
	private Integer sysPriceType;

	@ApiModelProperty(value = "开通时间")
	@TableField("sys_open_date")
	private Date sysOpenDate;

	@ApiModelProperty(value = "到期时间")
	@TableField("sys_end_date")
	private Date sysEndDate;

	@ApiModelProperty(value = "开始计费日期")
	@TableField("sys_start_charge_date")
	private Date sysStartChargeDate;

	@ApiModelProperty(value = "欠费金额")
	@TableField("sys_arrears")
	private BigDecimal sysArrears;

	@ApiModelProperty(value = "透支额度")
	@TableField("sys_overdraft")
	private BigDecimal sysOverdraft;

}
