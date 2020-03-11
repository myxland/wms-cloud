package com.zlsrj.wms.api.dto;

import java.io.Serializable;
import java.util.Date;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel(value = "ReadBooklet查询参数", description = "表册信息")
public class ReadBookletQueryParam implements Serializable {

	private static final long serialVersionUID = 2915125326613514995L;

	@ApiModelProperty(value = "系统编号")
	private String id;

	@ApiModelProperty(value = "租户编号")
	private String tenantId;

	@ApiModelProperty(value = "表册名称")
	private String bookletName;

	@ApiModelProperty(value = "表册类型（1：远传表；2：机械表）")
	private Integer bookletType;

	@ApiModelProperty(value = "抄表负责人编号")
	private Long readEmpId;

	@ApiModelProperty(value = "收费负责人编号")
	private Long payEmpId;

	@ApiModelProperty(value = "抄表间隔周期_月")
	private Integer calcCycleInterval;

	@ApiModelProperty(value = "最后一次抄表月份")
	private Date calcMonthLast;

	@ApiModelProperty(value = "最后一次抄表月份开始")
	private Date calcMonthLastStart;

	@ApiModelProperty(value = "最后一次抄表月份结束")
	private Date calcMonthLastEnd;

	@ApiModelProperty(value = "下次抄表月份")
	private Date calcMonthNext;

	@ApiModelProperty(value = "下次抄表月份开始")
	private Date calcMonthNextStart;

	@ApiModelProperty(value = "下次抄表月份结束")
	private Date calcMonthNextEnd;

}

