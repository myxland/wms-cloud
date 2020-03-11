package com.zlsrj.wms.api.vo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel(value = "TenantCustomerMeterInstall对象", description = "用户水表立户")
public class TenantCustomerMeterInstallVo implements Serializable {

	private static final long serialVersionUID = 1113120136311821151L;

	@ApiModelProperty(value = "水表立户ID")
	private String id;

	@ApiModelProperty(value = "租户ID")
	private String tenantId;

	@ApiModelProperty(value = "租户ID")
	private String tenantName;

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

	@ApiModelProperty(value = "最后一次结算时间")
	private Date meterLastSettleTime;

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

	@ApiModelProperty(value = "是否已经立户（1：是；0：否）")
	private Integer createOn;

}
