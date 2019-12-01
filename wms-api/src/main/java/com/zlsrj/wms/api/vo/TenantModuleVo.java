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
@ApiModel(value = "TenantModule对象", description = "租户模块")
public class TenantModuleVo implements Serializable {

	private static final long serialVersionUID = 7410561412141344212L;

	@ApiModelProperty(value = "系统ID")
	@JSONField(serializeUsing = ToStringSerializer.class)
	private Long id;

	@ApiModelProperty(value = "租户编号")
	@JSONField(serializeUsing = ToStringSerializer.class)
	private Long tenantId;

	@ApiModelProperty(value = "租户名称")
	private String tenantName;

	@ApiModelProperty(value = "模块编号")
	@JSONField(serializeUsing = ToStringSerializer.class)
	private Long moduleId;

	@ApiModelProperty(value = "模块名称")
	private String moduleName;

	@ApiModelProperty(value = "模块显示名称")
	private String moduleDisplayName;

	@ApiModelProperty(value = "模块排序")
	private Integer moduleOrder;

	@ApiModelProperty(value = "开通版本（1：基础版；2：高级版；3：旗舰版）")
	private Integer moduleEdition;

	@ApiModelProperty(value = "模块状态（1：开通；0：未开通）")
	private Integer moduleStatus;

	@ApiModelProperty(value = "价格体系（1：标准价格；2：指定价格）")
	private Integer modulePriceType;

	@ApiModelProperty(value = "开通时间")
	private Date moduleOpenDate;

	@ApiModelProperty(value = "到期时间")
	private Date moduleEndDate;

	@ApiModelProperty(value = "开始计费日期")
	private Date moduleStartChargeDate;

	@ApiModelProperty(value = "欠费金额")
	private BigDecimal moduleArrears;

	@ApiModelProperty(value = "透支额度")
	private BigDecimal moduleOverdraft;

}
