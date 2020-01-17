package com.zlsrj.wms.api.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.serializer.ToStringSerializer;
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
	@JSONField(serializeUsing = ToStringSerializer.class)
	private Long id;

	@ApiModelProperty(value = "租户ID")
	@TableField("tenant_id")
	@JSONField(serializeUsing = ToStringSerializer.class)
	private Long tenantId;

	@ApiModelProperty(value = "用户ID")
	@TableField("customer_id")
	@JSONField(serializeUsing = ToStringSerializer.class)
	private Long customerId;

	@ApiModelProperty(value = "水表表册ID")
	@TableField("meter_booklet_id")
	@JSONField(serializeUsing = ToStringSerializer.class)
	private Long meterBookletId;

	@ApiModelProperty(value = "上级水表ID")
	@TableField("meter_parent_id")
	@JSONField(serializeUsing = ToStringSerializer.class)
	private Long meterParentId;

	@ApiModelProperty(value = "水表编号")
	@TableField("meter_code")
	private String meterCode;

	@ApiModelProperty(value = "水表地址")
	@TableField("meter_address")
	private String meterAddress;

	@ApiModelProperty(value = "家庭人数")
	@TableField("meter_peoples")
	private Integer meterPeoples;

	@ApiModelProperty(value = "表身号码[钢印号等]")
	@TableField("meter_machine_code")
	private String meterMachineCode;

	@ApiModelProperty(value = "用途（1：水费结算；2：水量考核）")
	@TableField("meter_use_type")
	private Integer meterUseType;

	@ApiModelProperty(value = "厂商ID")
	@TableField("meter_manufactor_id")
	@JSONField(serializeUsing = ToStringSerializer.class)
	private Long meterManufactorId;

	@ApiModelProperty(value = "水表类型（1：机械表；2：远传表；3：IC卡表）")
	@TableField("meter_type")
	private Integer meterType;

	@ApiModelProperty(value = "水表口径ID")
	@TableField("caliber_id")
	@JSONField(serializeUsing = ToStringSerializer.class)
	private Long caliberId;

	@ApiModelProperty(value = "用水分类ID")
	@TableField("meter_water_type_id")
	@JSONField(serializeUsing = ToStringSerializer.class)
	private Long meterWaterTypeId;

	@ApiModelProperty(value = "价格分类ID")
	@TableField("price_type_id")
	@JSONField(serializeUsing = ToStringSerializer.class)
	private Long priceTypeId;

	@ApiModelProperty(value = "采集系统代码")
	@TableField("meter_iot_code")
	private String meterIotCode;

	@ApiModelProperty(value = "水表安装日期")
	@TableField("meter_install_date")
	private Date meterInstallDate;

	@ApiModelProperty(value = "水表建档日期")
	@TableField("meter_register_time")
	private Date meterRegisterTime;

	@ApiModelProperty(value = "水表状态（1：正常；2：暂停；3：拆表）")
	@TableField("meter_status")
	private Integer meterStatus;

	@ApiModelProperty(value = "年累计用水量")
	@TableField("meter_year_total_waters")
	private BigDecimal meterYearTotalWaters;

	@ApiModelProperty(value = "最后一次结算日期")
	@TableField("meter_last_settle_time")
	private Date meterLastSettleTime;

	@ApiModelProperty(value = "最后一次结算指针")
	@TableField("meter_last_settle_pointer")
	private BigDecimal meterLastSettlePointer;

	@ApiModelProperty(value = "水表欠费总金额")
	@TableField("meter_arrears_money")
	private BigDecimal meterArrearsMoney;


}
