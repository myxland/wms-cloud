package com.zlsrj.wms.api.vo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel(value = "TenantMeter对象", description = "水表信息")
public class TenantMeterVo implements Serializable {

	private static final long serialVersionUID = 6811151153711249601L;

	@ApiModelProperty(value = "水表ID")
	private String id;

	@ApiModelProperty(value = "租户ID")
	private String tenantId;

	@ApiModelProperty(value = "租户ID")
	private String tenantName;

	@ApiModelProperty(value = "用户IＤ")
	private String customerId;

	@ApiModelProperty(value = "用户编号")
	private String customerCode;

	@ApiModelProperty(value = "水表编号")
	private String meterCode;

	@ApiModelProperty(value = "水表地址")
	private String meterAddress;

	@ApiModelProperty(value = "表册ID")
	private String meterBookId;

	@ApiModelProperty(value = "用水人数")
	private Integer meterPeoples;

	@ApiModelProperty(value = "水表状态（1：正常；2：暂停；3：拆表）")
	private Integer meterStatus;

	@ApiModelProperty(value = "水表厂家")
	private String meterBrandId;

	@ApiModelProperty(value = "水表口径")
	private String meterCaliberId;

	@ApiModelProperty(value = "水表类型")
	private String meterTypeId;

	@ApiModelProperty(value = "水表型号")
	private String meterModelId;

	@ApiModelProperty(value = "营销区域")
	private String meterMarketingAreaId;

	@ApiModelProperty(value = "供水区域")
	private String meterSupplyAreaId;

	@ApiModelProperty(value = "行业分类")
	private String meterIndustryId;

	@ApiModelProperty(value = "水表用途（1：计量计费；2：计量不计费；3：考核表计量）")
	private Integer meterUseType;

	@ApiModelProperty(value = "节水标志（1：节水；0：无）")
	private Integer meterSaveWater;

	@ApiModelProperty(value = "新表标志（1：新表；0：旧表）")
	private Integer meterNewFlag;

	@ApiModelProperty(value = "gps x坐标")
	private String meterGpsX;

	@ApiModelProperty(value = "gps y坐标")
	private String meterGpsY;

	@ApiModelProperty(value = "表身码")
	private String meterMachineCode;

	@ApiModelProperty(value = "远传表号")
	private String meterRemoteCode;

	@ApiModelProperty(value = "水表安装日期")
	private Date meterInstallDate;

	@ApiModelProperty(value = "建档时间")
	private Date meterRegisterTime;

	@ApiModelProperty(value = "装表人员")
	private String meterInstallPer;

	@ApiModelProperty(value = "抄表顺序")
	private Integer meterReadOrder;

	@ApiModelProperty(value = "最近抄码")
	private Integer meterReadCode;

	@ApiModelProperty(value = "最近抄表日期")
	private Date meterReadDate;

	@ApiModelProperty(value = "最近计费抄码")
	private Integer meterSettleCode;

	@ApiModelProperty(value = "最近计费日期")
	private Date meterSettleDate;

	@ApiModelProperty(value = "欠费金额")
	private BigDecimal meterOweAmt;

	@ApiModelProperty(value = "违约金")
	private BigDecimal meterPenaltyAmt;

	@ApiModelProperty(value = "年用水总量")
	private BigDecimal meterYearTotalWaters;

	@ApiModelProperty(value = "历史用水总量")
	private BigDecimal meterTotalWaters;

	@ApiModelProperty(value = "阶梯起算日")
	private Date meterPriceStepDate;

	@ApiModelProperty(value = "阶梯使用量")
	private BigDecimal meterPriceStepWaters;

	@ApiModelProperty(value = "备注")
	private String meterMemo;

	@ApiModelProperty(value = "数据新增时间")
	private Date addTime;

	@ApiModelProperty(value = "数据修改时间")
	private Date updateTime;

	@ApiModelProperty(value = "水表计费")
	private List<TenantMeterPriceVo> tenantMeterPriceList;
}
