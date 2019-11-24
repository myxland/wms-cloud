package com.zlsrj.wms.api.vo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.serializer.ToStringSerializer;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel(value = "TenantInfo对象", description = "租户")
public class TenantInfoVo implements Serializable {

	private static final long serialVersionUID = 1511010121441314139L;

	@ApiModelProperty(value = "租户编号")
	@JSONField(serializeUsing = ToStringSerializer.class)
	private Long id;

	@ApiModelProperty(value = "租户名称")
	private String tenantName;

	@ApiModelProperty(value = "显示名称")
	private String displayName;

	@ApiModelProperty(value = "省")
	private String tenantProvince;

	@ApiModelProperty(value = "市")
	private String tenantCity;

	@ApiModelProperty(value = "区县")
	private String tenantArea;

	@ApiModelProperty(value = "联系地址")
	private String tenantAddress;

	@ApiModelProperty(value = "联系人")
	private String tenantLinkman;

	@ApiModelProperty(value = "手机号码")
	private String tenantMobile;

	@ApiModelProperty(value = "单位电话")
	private String tenantTel;

	@ApiModelProperty(value = "邮箱")
	private String tenantEmail;

	@ApiModelProperty(value = "QQ号码")
	private String tenantQq;

	@ApiModelProperty(value = "租户类型（1：使用单位；2：供应单位；3：内部运营）")
	private Integer tenantType;

	@ApiModelProperty(value = "租户状态（1：正式；2：试用）")
	private Integer tenantStatus;

	@ApiModelProperty(value = "注册时间")
	private Date regTime;

	@ApiModelProperty(value = "到期日期")
	private Date endDate;

	@ApiModelProperty(value = "税号")
	private String creditNumber;

	@ApiModelProperty(value = "开票地址")
	private String invoiceAddress;

	@ApiModelProperty(value = "开户行行号")
	private String bankNo;

	@ApiModelProperty(value = "开户行名称")
	private String bankName;

	@ApiModelProperty(value = "开户账号")
	private String accountNo;

	@ApiModelProperty(value = "是否启用部分缴费（1：启用；0：不启用）")
	private Integer partChargeOn;

	@ApiModelProperty(value = "是否启用违约金（1：启用；0：不启用）")
	private Integer overDuefineOn;

	@ApiModelProperty(value = "违约金宽限天数")
	private Integer overDuefineDay;

	@ApiModelProperty(value = "违约金每天收取比例")
	private BigDecimal overDuefineRatio;

	@ApiModelProperty(value = "违约金封顶比例（与欠费金额相比）")
	private BigDecimal overDuefineTopRatio;

	@ApiModelProperty(value = "预存抵扣方式（1：抄表后即时抵扣；2：人工发起抵扣）")
	private Integer ycdkType;

}
