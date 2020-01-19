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
@TableName("tenant_receivable")
@ApiModel(value = "TenantReceivable对象", description = "应收明细")
public class TenantReceivable implements Serializable {

	private static final long serialVersionUID = 1315151512051094211L;

	@ApiModelProperty(value = "应收账ID")
	@TableId(value = "id", type = IdType.INPUT)
	@JSONField(serializeUsing = ToStringSerializer.class)
	private Long id;

	@ApiModelProperty(value = "租户ID")
	@TableField("tenant_id")
	@JSONField(serializeUsing = ToStringSerializer.class)
	private Long tenantId;

	@ApiModelProperty(value = "应收账状态（1：正常；2：被冲正；3：冲正负记录）")
	@TableField("receivable_status")
	private Integer receivableStatus;

	@ApiModelProperty(value = "应收类型（1：抄表；2：换表；3：追补）")
	@TableField("receivable_type")
	private Integer receivableType;

	@ApiModelProperty(value = "部门ID")
	@TableField("department_id")
	@JSONField(serializeUsing = ToStringSerializer.class)
	private Long departmentId;

	@ApiModelProperty(value = "表册ID")
	@TableField("booklet_id")
	@JSONField(serializeUsing = ToStringSerializer.class)
	private Long bookletId;

	@ApiModelProperty(value = "表册代码")
	@TableField("booklet_code")
	private String bookletCode;

	@ApiModelProperty(value = "用户ID")
	@TableField("customer_id")
	@JSONField(serializeUsing = ToStringSerializer.class)
	private Long customerId;

	@ApiModelProperty(value = "用户代码")
	@TableField("customer_code")
	private String customerCode;

	@ApiModelProperty(value = "用户名称")
	@TableField("customer_name")
	private String customerName;

	@ApiModelProperty(value = "用户地址")
	@TableField("customer_address")
	private String customerAddress;

	@ApiModelProperty(value = "水表ID")
	@TableField("meter_id")
	@JSONField(serializeUsing = ToStringSerializer.class)
	private Long meterId;

	@ApiModelProperty(value = "水表代码")
	@TableField("meter_code")
	private String meterCode;

	@ApiModelProperty(value = "表具地址")
	@TableField("meter_address")
	private String meterAddress;

	@ApiModelProperty(value = "抄表员ID")
	@TableField("read_employee_id")
	@JSONField(serializeUsing = ToStringSerializer.class)
	private Long readEmployeeId;

	@ApiModelProperty(value = "应收账时间")
	@TableField("receivable_time")
	private Date receivableTime;

	@ApiModelProperty(value = "结算开始时间")
	@TableField("settle_start_time")
	private Date settleStartTime;

	@ApiModelProperty(value = "结算开始指针")
	@TableField("settle_start_pointer")
	private BigDecimal settleStartPointer;

	@ApiModelProperty(value = "结算截止时间")
	@TableField("settle_end_time")
	private Date settleEndTime;

	@ApiModelProperty(value = "结算截止指针")
	@TableField("settle_end_pointer")
	private BigDecimal settleEndPointer;

	@ApiModelProperty(value = "应结算水量")
	@TableField("settle_waters")
	private BigDecimal settleWaters;

	@ApiModelProperty(value = "价格类别ID")
	@TableField("price_type_id")
	@JSONField(serializeUsing = ToStringSerializer.class)
	private Long priceTypeId;

	@ApiModelProperty(value = "应收水量")
	@TableField("receivable_waters")
	private BigDecimal receivableWaters;

	@ApiModelProperty(value = "应收金额")
	@TableField("receivable_money")
	private BigDecimal receivableMoney;

	@ApiModelProperty(value = "欠费金额")
	@TableField("arrears_money")
	private BigDecimal arrearsMoney;


}
