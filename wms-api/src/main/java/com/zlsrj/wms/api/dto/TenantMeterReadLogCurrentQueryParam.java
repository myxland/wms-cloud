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
@ApiModel(value = "TenantMeterReadLogCurrent查询参数", description = "当期抄表计划")
public class TenantMeterReadLogCurrentQueryParam implements Serializable {

	private static final long serialVersionUID = 3151015911273760114L;

	@ApiModelProperty(value = "抄表计划")
	private Long id;

	@ApiModelProperty(value = "租户ID")
	private Long tenantId;

	@ApiModelProperty(value = "结算月份")
	private Date readMonth;

	@ApiModelProperty(value = "结算月份开始")
	private Date readMonthStart;

	@ApiModelProperty(value = "结算月份结束")
	private Date readMonthEnd;

	@ApiModelProperty(value = "水表ID")
	private Long meterId;

	@ApiModelProperty(value = "结算前水表当年累计水量")
	private BigDecimal meterYearTotalWatersBefore;

	@ApiModelProperty(value = "结算开始时间")
	private Date settleStartTime;

	@ApiModelProperty(value = "结算开始时间开始")
	private Date settleStartTimeStart;

	@ApiModelProperty(value = "结算开始时间结束")
	private Date settleStartTimeEnd;

	@ApiModelProperty(value = "结算开始指针")
	private BigDecimal settleStartPointer;

	@ApiModelProperty(value = "本次抄表时间")
	private Date currentReadTime;

	@ApiModelProperty(value = "本次抄表时间开始")
	private Date currentReadTimeStart;

	@ApiModelProperty(value = "本次抄表时间结束")
	private Date currentReadTimeEnd;

	@ApiModelProperty(value = "本次抄表指针")
	private BigDecimal currentReadPointer;

	@ApiModelProperty(value = "抄表员ID")
	private Long readEmployeeId;

	@ApiModelProperty(value = "表次抄表状况")
	private Long meterStatusId;

	@ApiModelProperty(value = "应结算水量")
	private BigDecimal settleWaters;

	@ApiModelProperty(value = "应收水量")
	private BigDecimal receivableWaters;

	@ApiModelProperty(value = "抄表来源（1：移动抄表；2：人工入账；3：远传表导入；4：远传表接口）")
	private Integer readSource;

	@ApiModelProperty(value = "抄表状态（0：未抄；1：已抄）")
	private Integer readStatus;

	@ApiModelProperty(value = "检查结果（0：正常；1：异常）")
	private Integer checkResult;

	@ApiModelProperty(value = "处理结果（1：已处理；2：未处理）")
	private Integer processReault;

	@ApiModelProperty(value = "处理人")
	private Long processEmployeeId;

	@ApiModelProperty(value = "处理时间")
	private Date processTime;

	@ApiModelProperty(value = "处理时间开始")
	private Date processTimeStart;

	@ApiModelProperty(value = "处理时间结束")
	private Date processTimeEnd;

	@ApiModelProperty(value = "处理方式（1：重新抄表；2：通过）")
	private Integer processType;

}

