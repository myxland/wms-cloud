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
@ApiModel(value = "TenantCustomerMeterInstall查询参数", description = "用户水表立户")
public class TenantCustomerMeterInstallQueryParam implements Serializable {

	private static final long serialVersionUID = 1012151015113070312L;

	@ApiModelProperty(value = "水表立户ID")
	private String id;

	@ApiModelProperty(value = "租户ID")
	private String tenantId;

	@ApiModelProperty(value = "已经立户的水表ID")
	private String meterId;

	@ApiModelProperty(value = "已经立户的水表代码")
	private String meterCode;

	@ApiModelProperty(value = "用户名称")
	private String custName;

	@ApiModelProperty(value = "水表地址")
	private String meterAddress;

	@ApiModelProperty(value = "表身号码[钢印号等]")
	private String meterMachineCode;

	@ApiModelProperty(value = "厂商ID")
	private String manufactorId;

	@ApiModelProperty(value = "水表类型（1：机械表；2：远传表；3：IC卡表）")
	private Integer meterType;

	@ApiModelProperty(value = "水表口径ID")
	private String caliberId;

	@ApiModelProperty(value = "用水分类ID")
	private String waterTypeId;

	@ApiModelProperty(value = "价格分类ID")
	private String priceTypeId;

	@ApiModelProperty(value = "采集系统编号")
	private String meterIotCode;

	@ApiModelProperty(value = "水表安装日期")
	private Date meterInstallDate;

	@ApiModelProperty(value = "水表安装日期开始")
	private Date meterInstallDateStart;

	@ApiModelProperty(value = "水表安装日期结束")
	private Date meterInstallDateEnd;

	@ApiModelProperty(value = "最后一次结算时间")
	private Date meterLastSettleTime;

	@ApiModelProperty(value = "最后一次结算时间开始")
	private Date meterLastSettleTimeStart;

	@ApiModelProperty(value = "最后一次结算时间结束")
	private Date meterLastSettleTimeEnd;

	@ApiModelProperty(value = "最后一次结算指针")
	private BigDecimal meterLastSettlePointer;

	@ApiModelProperty(value = "联系人手机号码")
	private String linkmanMobile;

	@ApiModelProperty(value = "用户身份证编号")
	private String custmerIdNo;

	@ApiModelProperty(value = "老系统编号")
	private String oldCode;

	@ApiModelProperty(value = "录入方式（1：手工录入；2：文件导入）")
	private Integer inputType;

	@ApiModelProperty(value = "录入日期")
	private Date inputTime;

	@ApiModelProperty(value = "录入日期开始")
	private Date inputTimeStart;

	@ApiModelProperty(value = "录入日期结束")
	private Date inputTimeEnd;

	@ApiModelProperty(value = "是否已经立户（1：是；0：否）")
	private Integer createOn;

}

