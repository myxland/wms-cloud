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
@ApiModel(value = "DevReadCurrHis查询参数", description = "历史抄表信息")
public class DevReadCurrHisQueryParam implements Serializable {

	private static final long serialVersionUID = 4111381410351272412L;

	@ApiModelProperty(value = "系统编号")
	private String id;

	@ApiModelProperty(value = "租户编号")
	private String tenantId;

	@ApiModelProperty(value = "抄表月份")
	private Date readMonth;

	@ApiModelProperty(value = "抄表月份开始")
	private Date readMonthStart;

	@ApiModelProperty(value = "抄表月份结束")
	private Date readMonthEnd;

	@ApiModelProperty(value = "表具系统编号")
	private String devId;

	@ApiModelProperty(value = "父表具编号")
	private Long parentDevId;

	@ApiModelProperty(value = "本次计费前当年累计水量")
	private BigDecimal yearUseNum;

	@ApiModelProperty(value = "上次计费日期")
	private Date lastCalcDate;

	@ApiModelProperty(value = "上次计费日期开始")
	private Date lastCalcDateStart;

	@ApiModelProperty(value = "上次计费日期结束")
	private Date lastCalcDateEnd;

	@ApiModelProperty(value = "上次计费止码")
	private BigDecimal lastCalcCode;

	@ApiModelProperty(value = "抄表人编号")
	private Long currReadEmpId;

	@ApiModelProperty(value = "本次抄表日期")
	private Date currReadDate;

	@ApiModelProperty(value = "本次抄表日期开始")
	private Date currReadDateStart;

	@ApiModelProperty(value = "本次抄表日期结束")
	private Date currReadDateEnd;

	@ApiModelProperty(value = "本次抄表止码")
	private BigDecimal currReadCode;

	@ApiModelProperty(value = "表次表具状况")
	private Integer currDevStatus;

	@ApiModelProperty(value = "本次抄表水量")
	private BigDecimal currUseNum;

	@ApiModelProperty(value = "本次计费水量")
	private BigDecimal currCalcUseNum;

	@ApiModelProperty(value = "抄表来源（1：移动抄表；2：人工入账；3：远传表）")
	private Integer readSource;

	@ApiModelProperty(value = "抄表状态（1：未抄；2：已抄）")
	private Integer readStatus;

	@ApiModelProperty(value = "审核状态（1：正常；2：异常）")
	private Integer checkResult;

	@ApiModelProperty(value = "处理状态（1：已计费；2：待审核；3：已审核）")
	private Integer procReault;

	@ApiModelProperty(value = "审核人")
	private Long procMan;

	@ApiModelProperty(value = "审核时间")
	private Date procTime;

	@ApiModelProperty(value = "审核时间开始")
	private Date procTimeStart;

	@ApiModelProperty(value = "审核时间结束")
	private Date procTimeEnd;

}

