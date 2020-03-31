package com.zlsrj.wms.api.dto;

import java.io.Serializable;
import java.util.Date;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel(value = "TenantBook查询参数", description = "表册信息")
public class TenantBookQueryParam implements Serializable {

	private static final long serialVersionUID = 1564461330141112134L;

	@ApiModelProperty(value = "表册ID")
	private String id;

	@ApiModelProperty(value = "租户ID")
	private String tenantId;

	@ApiModelProperty(value = "表册编号")
	private String bookCode;

	@ApiModelProperty(value = "表册名称")
	private String bookName;

	@ApiModelProperty(value = "抄表员")
	private String bookReaderEmployeeId;

	@ApiModelProperty(value = "收费员")
	private String bookChargeEmployeeId;

	@ApiModelProperty(value = "营销区域")
	private String bookMarketingAreaId;

	@ApiModelProperty(value = "抄表周期")
	private Integer bookReadCycle;

	@ApiModelProperty(value = "最后一次抄表月份")
	private String bookLastMonth;

	@ApiModelProperty(value = "下次抄表月份")
	private String bookReadMonth;

	@ApiModelProperty(value = "结算周期")
	private Integer bookSettleCycle;

	@ApiModelProperty(value = "最后一次结算月份")
	private String bookSettleLastMonth;

	@ApiModelProperty(value = "下次结算月份")
	private String bookSettleMonth;

	@ApiModelProperty(value = "有效状态（1：可用；0：禁用）")
	private Integer bookStatus;

	@ApiModelProperty(value = "表册状态（1：抄表进行中；2：抄表截止）")
	private Integer bookReadStatus;

	@ApiModelProperty(value = "级次")
	private Integer priceClass;

	@ApiModelProperty(value = "备注")
	private String priceMemo;

	@ApiModelProperty(value = "数据新增时间")
	private Date addTime;

	@ApiModelProperty(value = "数据新增时间开始")
	private Date addTimeStart;

	@ApiModelProperty(value = "数据新增时间结束")
	private Date addTimeEnd;

	@ApiModelProperty(value = "数据修改时间")
	private Date updateTime;

	@ApiModelProperty(value = "数据修改时间开始")
	private Date updateTimeStart;

	@ApiModelProperty(value = "数据修改时间结束")
	private Date updateTimeEnd;

	@ApiModelProperty(value = "查询字段")
	private String queryCol;
	
	@ApiModelProperty(value = "查询条件 like = 等")
	private String queryType="=";
	
	@ApiModelProperty(value = "查询值")
	private String queryValue;
}

