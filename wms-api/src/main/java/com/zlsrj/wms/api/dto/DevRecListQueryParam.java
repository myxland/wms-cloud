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
@ApiModel(value = "DevRecList查询参数", description = "应收流水")
public class DevRecListQueryParam implements Serializable {

	private static final long serialVersionUID = 1411312142521211020L;

	@ApiModelProperty(value = "系统编号")
	private String id;

	@ApiModelProperty(value = "租户编号")
	private String tenantId;

	@ApiModelProperty(value = "应收账标识（1：正常；2：被冲正；3：冲正负记录）")
	private Integer recFlag;

	@ApiModelProperty(value = "应收来源（1：抄表；2：换表）")
	private Integer recType;

	@ApiModelProperty(value = "部门编号")
	private Long deptId;

	@ApiModelProperty(value = "表册编号")
	private String bookletId;

	@ApiModelProperty(value = "用户编号")
	private Long custId;

	@ApiModelProperty(value = "用户名称")
	private String custName;

	@ApiModelProperty(value = "用户地址")
	private String custAddress;

	@ApiModelProperty(value = "表具系统编号")
	private String devId;

	@ApiModelProperty(value = "表具地址")
	private String devAddress;

	@ApiModelProperty(value = "应收月份")
	private Date readMonth;

	@ApiModelProperty(value = "应收月份开始")
	private Date readMonthStart;

	@ApiModelProperty(value = "应收月份结束")
	private Date readMonthEnd;

	@ApiModelProperty(value = "应收账务统计月份")
	private Date recMonth;

	@ApiModelProperty(value = "应收账务统计月份开始")
	private Date recMonthStart;

	@ApiModelProperty(value = "应收账务统计月份结束")
	private Date recMonthEnd;

	@ApiModelProperty(value = "业务（抄表、换表）流水号")
	private Long businessId;

	@ApiModelProperty(value = "抄表员编号")
	private Long reader;

	@ApiModelProperty(value = "计费时间")
	private Date calcDate;

	@ApiModelProperty(value = "计费时间开始")
	private Date calcDateStart;

	@ApiModelProperty(value = "计费时间结束")
	private Date calcDateEnd;

	@ApiModelProperty(value = "上次计费日期")
	private Date lastDate;

	@ApiModelProperty(value = "上次计费日期开始")
	private Date lastDateStart;

	@ApiModelProperty(value = "上次计费日期结束")
	private Date lastDateEnd;

	@ApiModelProperty(value = "起数")
	private BigDecimal lastCode;

	@ApiModelProperty(value = "当前计费日期")
	private Date currDate;

	@ApiModelProperty(value = "当前计费日期开始")
	private Date currDateStart;

	@ApiModelProperty(value = "当前计费日期结束")
	private Date currDateEnd;

	@ApiModelProperty(value = "止数")
	private BigDecimal currCode;

	@ApiModelProperty(value = "用量占比")
	private BigDecimal waterScale;

	@ApiModelProperty(value = "计费水量")
	private BigDecimal useNum;

	@ApiModelProperty(value = "价格编号")
	private String priceTypeId;

	@ApiModelProperty(value = "应收金额")
	private BigDecimal recMoney;

	@ApiModelProperty(value = "欠费金额")
	private BigDecimal dueMoney;

}

