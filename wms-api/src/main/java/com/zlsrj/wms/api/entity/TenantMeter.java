package com.zlsrj.wms.api.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

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
@TableName("tenant_meter")
@ApiModel(value = "TenantMeter对象", description = "水表信息")
public class TenantMeter implements Serializable {

	private static final long serialVersionUID = 5111153106408130121L;

	@ApiModelProperty(value = "水表ID")
	@TableId(value = "id", type = IdType.INPUT)
	private String id;

	@ApiModelProperty(value = "租户ID")
	@TableField("tenant_id")
	private String tenantId;

	@ApiModelProperty(value = "用户IＤ")
	@TableField("customer_id")
	private String customerId;

	@ApiModelProperty(value = "用户编号")
	@TableField("customer_code")
	private String customerCode;

	@ApiModelProperty(value = "水表编号")
	@TableField("meter_code")
	private String meterCode;

	@ApiModelProperty(value = "水表地址")
	@TableField("meter_address")
	private String meterAddress;

	@ApiModelProperty(value = "表册ID")
	@TableField("meter_book_id")
	private String meterBookId;

	@ApiModelProperty(value = "用水人数")
	@TableField("meter_peoples")
	private Integer meterPeoples;

	@ApiModelProperty(value = "水表状态（1：正常；2：暂停；3：拆表）")
	@TableField("meter_status")
	private Integer meterStatus;

	@ApiModelProperty(value = "水表厂家")
	@TableField("meter_brand_id")
	private String meterBrandId;

	@ApiModelProperty(value = "水表口径")
	@TableField("meter_caliber_id")
	private String meterCaliberId;

	@ApiModelProperty(value = "水表类型")
	@TableField("meter_type_id")
	private String meterTypeId;

	@ApiModelProperty(value = "水表型号")
	@TableField("meter_model_id")
	private String meterModelId;

	@ApiModelProperty(value = "营销区域")
	@TableField("meter_marketing_area_id")
	private String meterMarketingAreaId;

	@ApiModelProperty(value = "供水区域")
	@TableField("meter_supply_area_id")
	private String meterSupplyAreaId;

	@ApiModelProperty(value = "行业分类")
	@TableField("meter_industry_id")
	private String meterIndustryId;

	@ApiModelProperty(value = "水表用途（1：计量计费；2：计量不计费；3：考核表计量）")
	@TableField("meter_use_type")
	private Integer meterUseType;

	@ApiModelProperty(value = "节水标志（1：节水；0：无）")
	@TableField("meter_save_water")
	private Integer meterSaveWater;

	@ApiModelProperty(value = "新表标志（1：新表；0：旧表）")
	@TableField("meter_new_flag")
	private Integer meterNewFlag;

	@ApiModelProperty(value = "gps x坐标")
	@TableField("meter_gps_x")
	private String meterGpsX;

	@ApiModelProperty(value = "gps y坐标")
	@TableField("meter_gps_y")
	private String meterGpsY;

	@ApiModelProperty(value = "表身码")
	@TableField("meter_machine_code")
	private String meterMachineCode;

	@ApiModelProperty(value = "远传表号")
	@TableField("meter_remote_code")
	private String meterRemoteCode;

	@ApiModelProperty(value = "水表安装日期")
	@TableField("meter_install_date")
	private Date meterInstallDate;

	@ApiModelProperty(value = "建档时间")
	@TableField("meter_register_time")
	private Date meterRegisterTime;

	@ApiModelProperty(value = "装表人员")
	@TableField("meter_install_per")
	private String meterInstallPer;

	@ApiModelProperty(value = "抄表顺序")
	@TableField("meter_read_order")
	private Integer meterReadOrder;

	@ApiModelProperty(value = "最近抄码")
	@TableField("meter_read_code")
	private Integer meterReadCode;

	@ApiModelProperty(value = "最近抄表日期")
	@TableField("meter_read_date")
	private Date meterReadDate;

	@ApiModelProperty(value = "最近计费抄码")
	@TableField("meter_settle_code")
	private Integer meterSettleCode;

	@ApiModelProperty(value = "最近计费日期")
	@TableField("meter_settle_date")
	private Date meterSettleDate;

	@ApiModelProperty(value = "欠费金额")
	@TableField("meter_owe_amt")
	private BigDecimal meterOweAmt;

	@ApiModelProperty(value = "违约金")
	@TableField("meter_penalty_amt")
	private BigDecimal meterPenaltyAmt;

	@ApiModelProperty(value = "年用水总量")
	@TableField("meter_year_total_waters")
	private BigDecimal meterYearTotalWaters;

	@ApiModelProperty(value = "历史用水总量")
	@TableField("meter_total_waters")
	private BigDecimal meterTotalWaters;

	@ApiModelProperty(value = "阶梯起算日")
	@TableField("meter_price_step_date")
	private Date meterPriceStepDate;

	@ApiModelProperty(value = "阶梯使用量")
	@TableField("meter_price_step_waters")
	private BigDecimal meterPriceStepWaters;

	@ApiModelProperty(value = "备注")
	@TableField("meter_memo")
	private String meterMemo;

	@ApiModelProperty(value = "数据新增时间")
	@TableField("add_time")
	private Date addTime;

	@ApiModelProperty(value = "数据修改时间")
	@TableField("update_time")
	private Date updateTime;

}