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
@TableName("tenant_customer_meter_install")
@ApiModel(value = "TenantCustomerMeterInstall对象", description = "用户水表立户")
public class TenantCustomerMeterInstall implements Serializable {

	private static final long serialVersionUID = 8101310851062121546L;

	@ApiModelProperty(value = "水表立户ID")
	@TableId(value = "id", type = IdType.INPUT)
	private String id;

	@ApiModelProperty(value = "租户ID")
	@TableField("tenant_id")
	private String tenantId;

	@ApiModelProperty(value = "已经立户的水表ID")
	@TableField("meter_id")
	private String meterId;

	@ApiModelProperty(value = "已经立户的水表代码")
	@TableField("meter_code")
	private String meterCode;

	@ApiModelProperty(value = "用户名称")
	@TableField("cust_name")
	private String custName;

	@ApiModelProperty(value = "水表地址")
	@TableField("meter_address")
	private String meterAddress;

	@ApiModelProperty(value = "表身号码[钢印号等]")
	@TableField("meter_machine_code")
	private String meterMachineCode;

	@ApiModelProperty(value = "厂商ID")
	@TableField("manufactor_id")
	private String manufactorId;

	@ApiModelProperty(value = "水表类型（1：机械表；2：远传表；3：IC卡表）")
	@TableField("meter_type")
	private Integer meterType;

	@ApiModelProperty(value = "水表口径ID")
	@TableField("caliber_id")
	private String caliberId;

	@ApiModelProperty(value = "用水分类ID")
	@TableField("water_type_id")
	private String waterTypeId;

	@ApiModelProperty(value = "价格分类ID")
	@TableField("price_type_id")
	private String priceTypeId;

	@ApiModelProperty(value = "采集系统编号")
	@TableField("meter_iot_code")
	private String meterIotCode;

	@ApiModelProperty(value = "水表安装日期")
	@TableField("meter_install_date")
	private Date meterInstallDate;

	@ApiModelProperty(value = "最后一次结算时间")
	@TableField("meter_last_settle_time")
	private Date meterLastSettleTime;

	@ApiModelProperty(value = "最后一次结算指针")
	@TableField("meter_last_settle_pointer")
	private BigDecimal meterLastSettlePointer;

	@ApiModelProperty(value = "联系人手机号码")
	@TableField("linkman_mobile")
	private String linkmanMobile;

	@ApiModelProperty(value = "用户身份证编号")
	@TableField("custmer_id_no")
	private String custmerIdNo;

	@ApiModelProperty(value = "老系统编号")
	@TableField("old_code")
	private String oldCode;

	@ApiModelProperty(value = "录入方式（1：手工录入；2：文件导入）")
	@TableField("input_type")
	private Integer inputType;

	@ApiModelProperty(value = "录入日期")
	@TableField("input_time")
	private Date inputTime;

	@ApiModelProperty(value = "是否已经立户（1：是；0：否）")
	@TableField("create_on")
	private Integer createOn;


}
