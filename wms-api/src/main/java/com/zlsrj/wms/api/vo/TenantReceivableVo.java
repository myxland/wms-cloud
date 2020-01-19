package com.zlsrj.wms.api.vo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.serializer.ToStringSerializer;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel(value = "TenantReceivable对象", description = "应收明细")
public class TenantReceivableVo implements Serializable {

	private static final long serialVersionUID = 6900128131314103721L;

	@ApiModelProperty(value = "应收账ID")
	@JSONField(serializeUsing = ToStringSerializer.class)
	private Long id;

	@ApiModelProperty(value = "租户ID")
	@JSONField(serializeUsing = ToStringSerializer.class)
	private Long tenantId;

	@ApiModelProperty(value = "租户ID")
	private String tenantName;

	@ApiModelProperty(value = "应收账状态（1：正常；2：被冲正；3：冲正负记录）")
	private Integer receivableStatus;

	@ApiModelProperty(value = "应收类型（1：抄表；2：换表；3：追补）")
	private Integer receivableType;

	@ApiModelProperty(value = "部门ID")
	@JSONField(serializeUsing = ToStringSerializer.class)
	private Long departmentId;

	@ApiModelProperty(value = "表册ID")
	@JSONField(serializeUsing = ToStringSerializer.class)
	private Long bookletId;

	@ApiModelProperty(value = "表册代码")
	private String bookletCode;

	@ApiModelProperty(value = "用户ID")
	@JSONField(serializeUsing = ToStringSerializer.class)
	private Long customerId;

	@ApiModelProperty(value = "用户代码")
	private String customerCode;

	@ApiModelProperty(value = "用户名称")
	private String customerName;

	@ApiModelProperty(value = "用户地址")
	private String customerAddress;

	@ApiModelProperty(value = "水表ID")
	@JSONField(serializeUsing = ToStringSerializer.class)
	private Long meterId;

	@ApiModelProperty(value = "水表代码")
	private String meterCode;

	@ApiModelProperty(value = "表具地址")
	private String meterAddress;

	@ApiModelProperty(value = "抄表员ID")
	@JSONField(serializeUsing = ToStringSerializer.class)
	private Long readEmployeeId;

	@ApiModelProperty(value = "应收账时间")
	private Date receivableTime;

	@ApiModelProperty(value = "结算开始时间")
	private Date settleStartTime;

	@ApiModelProperty(value = "结算开始指针")
	private BigDecimal settleStartPointer;

	@ApiModelProperty(value = "结算截止时间")
	private Date settleEndTime;

	@ApiModelProperty(value = "结算截止指针")
	private BigDecimal settleEndPointer;

	@ApiModelProperty(value = "应结算水量")
	private BigDecimal settleWaters;

	@ApiModelProperty(value = "价格类别ID")
	@JSONField(serializeUsing = ToStringSerializer.class)
	private Long priceTypeId;

	@ApiModelProperty(value = "应收水量")
	private BigDecimal receivableWaters;

	@ApiModelProperty(value = "应收金额")
	private BigDecimal receivableMoney;

	@ApiModelProperty(value = "欠费金额")
	private BigDecimal arrearsMoney;

}
