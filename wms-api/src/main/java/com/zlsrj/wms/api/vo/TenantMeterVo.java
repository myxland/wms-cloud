package com.zlsrj.wms.api.vo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.serializer.ToStringSerializer;

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
	@JSONField(serializeUsing = ToStringSerializer.class)
	private Long id;

	@ApiModelProperty(value = "租户ID")
	@JSONField(serializeUsing = ToStringSerializer.class)
	private Long tenantId;

	@ApiModelProperty(value = "租户ID")
	private String tenantName;

	@ApiModelProperty(value = "用户ID")
	@JSONField(serializeUsing = ToStringSerializer.class)
	private Long customerId;

	@ApiModelProperty(value = "水表表册ID")
	@JSONField(serializeUsing = ToStringSerializer.class)
	private Long meterBookletId;

	@ApiModelProperty(value = "上级水表ID")
	@JSONField(serializeUsing = ToStringSerializer.class)
	private Long meterParentId;

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
	@JSONField(serializeUsing = ToStringSerializer.class)
	private Long meterManufactorId;

	@ApiModelProperty(value = "水表类型（1：机械表；2：远传表；3：IC卡表）")
	private Integer meterType;

	@ApiModelProperty(value = "水表口径ID")
	@JSONField(serializeUsing = ToStringSerializer.class)
	private Long caliberId;

	@ApiModelProperty(value = "用水分类ID")
	@JSONField(serializeUsing = ToStringSerializer.class)
	private Long meterWaterTypeId;

	@ApiModelProperty(value = "价格分类ID")
	@JSONField(serializeUsing = ToStringSerializer.class)
	private Long priceTypeId;

	@ApiModelProperty(value = "采集系统代码")
	private String meterIotCode;

	@ApiModelProperty(value = "水表安装日期")
	private Date meterInstallDate;

	@ApiModelProperty(value = "水表建档日期")
	private Date meterRegisterTime;

	@ApiModelProperty(value = "水表状态（1：正常；2：暂停；3：拆表）")
	private Integer meterStatus;

	@ApiModelProperty(value = "年累计用水量")
	private BigDecimal meterYearTotalWaters;

	@ApiModelProperty(value = "最后一次结算日期")
	private Date meterLastSettleTime;

	@ApiModelProperty(value = "最后一次结算指针")
	private BigDecimal meterLastSettlePointer;

	@ApiModelProperty(value = "水表欠费总金额")
	private BigDecimal meterArrearsMoney;

	@ApiModelProperty(value = "子级水表信息列表")
	private List<TenantMeterVo> children;
	
	@ApiModelProperty(value = "是否包含子级水表信息")
	private boolean hasChildren;
	
}
