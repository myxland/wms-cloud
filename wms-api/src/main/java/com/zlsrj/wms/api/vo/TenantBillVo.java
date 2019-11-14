package com.zlsrj.wms.api.vo;

import java.io.Serializable;

import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.serializer.ToStringSerializer;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel(value = "TenantBill对象", description = "租户账单配置")
public class TenantBillVo implements Serializable {

	private static final long serialVersionUID = 1112158141096121212L;

	@ApiModelProperty(value = "编号ID")
	@JSONField(serializeUsing = ToStringSerializer.class)
	private Long id;

	@ApiModelProperty(value = "租户编号")
	@JSONField(serializeUsing = ToStringSerializer.class)
	private Long tenantId;

	@ApiModelProperty(value = "租户名称")
	private String tenantName;

	@ApiModelProperty(value = "用户发票开具方式（1：按实收开票；2：按应收开票）")
	private Integer billPrintType;

	@ApiModelProperty(value = "发票备注定义")
	private String billRemark;

	@ApiModelProperty(value = "电子发票服务商（百望/航天信息）")
	private String billService;

	@ApiModelProperty(value = "接入代码")
	private String billJrdm;

	@ApiModelProperty(value = "签名值参数")
	private String billQmcs;

	@ApiModelProperty(value = "税控盘编号")
	private String billSkpbh;

	@ApiModelProperty(value = "税控盘口令")
	private String billSkpkl;

	@ApiModelProperty(value = "税务数字证书密码")
	private String billKeypwd;

}
