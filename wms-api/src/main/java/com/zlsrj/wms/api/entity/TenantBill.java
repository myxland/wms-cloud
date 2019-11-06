package com.zlsrj.wms.api.entity;

import java.io.Serializable;

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
@TableName("t_op_tenant_bill")
@ApiModel(value = "TenantBill对象", description = "租户账单配置")
public class TenantBill implements Serializable {

	private static final long serialVersionUID = 1012106140101413115L;

	@ApiModelProperty(value = "编号ID")
	@TableId(value = "id", type = IdType.INPUT)
	@JSONField(serializeUsing = ToStringSerializer.class)
	private Long id;

	@ApiModelProperty(value = "租户编号")
	@TableField("tenant_id")
	@JSONField(serializeUsing = ToStringSerializer.class)
	private Long tenantId;

	@ApiModelProperty(value = "用户发票开具方式（按实收开票/按应收开票）")
	@TableField("bill_print_type")
	private Integer billPrintType;

	@ApiModelProperty(value = "发票备注定义")
	@TableField("bill_remark")
	private String billRemark;

	@ApiModelProperty(value = "电子发票服务商（百望/航天信息）")
	@TableField("bill_service")
	private String billService;

	@ApiModelProperty(value = "接入代码")
	@TableField("bill_jrdm")
	private String billJrdm;

	@ApiModelProperty(value = "签名值参数")
	@TableField("bill_qmcs")
	private String billQmcs;

	@ApiModelProperty(value = "税控盘编号")
	@TableField("bill_skpbh")
	private String billSkpbh;

	@ApiModelProperty(value = "税控盘口令")
	@TableField("bill_skpkl")
	private String billSkpkl;

	@ApiModelProperty(value = "税务数字证书密码")
	@TableField("bill_keypwd")
	private String billKeypwd;


}
