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
@ApiModel(value = "TenantReceivable查询参数", description = "应收明细")
public class TenantReceivableQueryParam implements Serializable {

	private static final long serialVersionUID = 1446101210512991311L;

	@ApiModelProperty(value = "应收账ID")
	private Long id;

	@ApiModelProperty(value = "租户ID")
	private Long tenantId;

	@ApiModelProperty(value = "应收账状态（1：正常；2：被冲正；3：冲正负记录）")
	private Integer receivableStatus;

	@ApiModelProperty(value = "应收类型（1：抄表；2：换表；3：追补）")
	private Integer receivableType;

	@ApiModelProperty(value = "部门ID")
	private Long departmentId;

	@ApiModelProperty(value = "表册ID")
	private Long bookletId;

	@ApiModelProperty(value = "表册代码")
	private String bookletCode;

	@ApiModelProperty(value = "用户ID")
	private Long customerId;

	@ApiModelProperty(value = "用户代码")
	private String customerCode;

	@ApiModelProperty(value = "用户名称")
	private String customerName;

	@ApiModelProperty(value = "用户地址")
	private String customerAddress;

	@ApiModelProperty(value = "水表ID")
	private Long meterId;

	@ApiModelProperty(value = "水表代码")
	private String meterCode;

	@ApiModelProperty(value = "表具地址")
	private String meterAddress;

	@ApiModelProperty(value = "抄表员ID")
	private Long readEmployeeId;

	@ApiModelProperty(value = "应收账时间")
	private Date receivableTime;

	@ApiModelProperty(value = "应收账时间开始")
	private Date receivableTimeStart;

	@ApiModelProperty(value = "应收账时间结束")
	private Date receivableTimeEnd;

	@ApiModelProperty(value = "结算开始时间")
	private Date settleStartTime;

	@ApiModelProperty(value = "结算开始时间开始")
	private Date settleStartTimeStart;

	@ApiModelProperty(value = "结算开始时间结束")
	private Date settleStartTimeEnd;

	@ApiModelProperty(value = "结算开始指针")
	private BigDecimal settleStartPointer;

	@ApiModelProperty(value = "结算截止时间")
	private Date settleEndTime;

	@ApiModelProperty(value = "结算截止时间开始")
	private Date settleEndTimeStart;

	@ApiModelProperty(value = "结算截止时间结束")
	private Date settleEndTimeEnd;

	@ApiModelProperty(value = "结算截止指针")
	private BigDecimal settleEndPointer;

	@ApiModelProperty(value = "应结算水量")
	private BigDecimal settleWaters;

	@ApiModelProperty(value = "价格类别ID")
	private Long priceTypeId;

	@ApiModelProperty(value = "应收水量")
	private BigDecimal receivableWaters;

	@ApiModelProperty(value = "应收金额")
	private BigDecimal receivableMoney;

	@ApiModelProperty(value = "欠费金额")
	private BigDecimal arrearsMoney;

}

