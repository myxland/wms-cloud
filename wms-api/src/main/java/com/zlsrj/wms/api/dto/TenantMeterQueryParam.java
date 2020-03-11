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
@ApiModel(value = "TenantMeter查询参数", description = "水表信息")
public class TenantMeterQueryParam implements Serializable {

	private static final long serialVersionUID = 1798991141511102158L;

	@ApiModelProperty(value = "水表ID")
	private String id;

	@ApiModelProperty(value = "租户ID")
	private String tenantId;

	@ApiModelProperty(value = "用户ID")
	private String customerId;

	@ApiModelProperty(value = "水表表册ID")
	private String meterBookletId;

	@ApiModelProperty(value = "上级水表ID")
	private String meterParentId;

	@ApiModelProperty(value = "水表编号")
	private String meterCode;

	@ApiModelProperty(value = "水表地址")
	private String meterAddress;

	@ApiModelProperty(value = "家庭人数")
	private Integer meterPeoples;

	@ApiModelProperty(value = "表身号码[钢印号等]")
	private String meterMachineCode;

	@ApiModelProperty(value = "用途（1：水费结算；2：水量考核）")
	private Integer meterUseType;

	@ApiModelProperty(value = "厂商ID")
	private String meterManufactorId;

	@ApiModelProperty(value = "水表类型（1：机械表；2：远传表；3：IC卡表）")
	private Integer meterType;

	@ApiModelProperty(value = "水表口径ID")
	private String caliberId;

	@ApiModelProperty(value = "用水分类ID")
	private String meterWaterTypeId;

	@ApiModelProperty(value = "价格分类ID")
	private String priceTypeId;

	@ApiModelProperty(value = "采集系统代码")
	private String meterIotCode;

	@ApiModelProperty(value = "水表安装日期")
	private Date meterInstallDate;

	@ApiModelProperty(value = "水表安装日期开始")
	private Date meterInstallDateStart;

	@ApiModelProperty(value = "水表安装日期结束")
	private Date meterInstallDateEnd;

	@ApiModelProperty(value = "水表建档日期")
	private Date meterRegisterTime;

	@ApiModelProperty(value = "水表建档日期开始")
	private Date meterRegisterTimeStart;

	@ApiModelProperty(value = "水表建档日期结束")
	private Date meterRegisterTimeEnd;

	@ApiModelProperty(value = "水表状态（1：正常；2：暂停；3：拆表）")
	private Integer meterStatus;

	@ApiModelProperty(value = "年累计用水量")
	private BigDecimal meterYearTotalWaters;

	@ApiModelProperty(value = "最后一次结算日期")
	private Date meterLastSettleTime;

	@ApiModelProperty(value = "最后一次结算日期开始")
	private Date meterLastSettleTimeStart;

	@ApiModelProperty(value = "最后一次结算日期结束")
	private Date meterLastSettleTimeEnd;

	@ApiModelProperty(value = "最后一次结算指针")
	private BigDecimal meterLastSettlePointer;

	@ApiModelProperty(value = "水表欠费总金额")
	private BigDecimal meterArrearsMoney;

	@ApiModelProperty(value = "父级ID")
	private String parentId;
	
}

