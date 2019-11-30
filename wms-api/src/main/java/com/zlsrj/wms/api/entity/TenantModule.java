package com.zlsrj.wms.api.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.serializer.ToStringSerializer;
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
@TableName("t_op_tenant_module")
@ApiModel(value = "TenantModule对象", description = "租户模块")
public class TenantModule implements Serializable {

	private static final long serialVersionUID = 3610815111712451512L;

	@ApiModelProperty(value = "系统ID")
	@TableId(value = "id", type = IdType.INPUT)
	@JSONField(serializeUsing = ToStringSerializer.class)
	private Long id;

	@ApiModelProperty(value = "租户编号")
	@TableField("tenant_id")
	@JSONField(serializeUsing = ToStringSerializer.class)
	private Long tenantId;

	@ApiModelProperty(value = "模块显示名称")
	@TableField("module_display_name")
	private String moduleDisplayName;

	@ApiModelProperty(value = "模块排序")
	@TableField("module_order")
	private Integer moduleOrder;

	@ApiModelProperty(value = "开通版本（1：基础版；2：高级版；3：旗舰版）")
	@TableField("module_edition")
	private Integer moduleEdition;

	@ApiModelProperty(value = "模块状态（1：开通；0：未开通）")
	@TableField("module_status")
	private Integer moduleStatus;

	@ApiModelProperty(value = "价格体系（1：标准价格；2：指定价格）")
	@TableField("module_price_type")
	private Integer modulePriceType;

	@ApiModelProperty(value = "开通时间")
	@TableField("module_open_date")
	private Date moduleOpenDate;

	@ApiModelProperty(value = "到期时间")
	@TableField("module_end_date")
	private Date moduleEndDate;

	@ApiModelProperty(value = "开始计费日期")
	@TableField("module_start_charge_date")
	private Date moduleStartChargeDate;

	@ApiModelProperty(value = "欠费金额")
	@TableField("module_arrears")
	private BigDecimal moduleArrears;

	@ApiModelProperty(value = "透支额度")
	@TableField("module_overdraft")
	private BigDecimal moduleOverdraft;


}
