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
@TableName("tenant_info")
@ApiModel(value = "TenantInfo对象", description = "租户表")
public class TenantInfo implements Serializable {

	private static final long serialVersionUID = 1483810642213014113L;

	@ApiModelProperty(value = "租户ID")
	@TableId(value = "id", type = IdType.INPUT)
	@JSONField(serializeUsing = ToStringSerializer.class)
	private Long id;

	@ApiModelProperty(value = "租户名称")
	@TableField("tenant_name")
	private String tenantName;

	@ApiModelProperty(value = "账户余额")
	@TableField("tenant_balance")
	private BigDecimal tenantBalance;

	@ApiModelProperty(value = "透支额度")
	@TableField("teanant_overdraw")
	private BigDecimal teanantOverdraw;

	@ApiModelProperty(value = "租户显示名称")
	@TableField("tenant_display_name")
	private String tenantDisplayName;

	@ApiModelProperty(value = "省（下拉框）")
	@TableField("tenant_province")
	private String tenantProvince;

	@ApiModelProperty(value = "市（下拉框）")
	@TableField("tenant_city")
	private String tenantCity;

	@ApiModelProperty(value = "区县（下拉框）")
	@TableField("tenant_county_town")
	private String tenantCountyTown;

	@ApiModelProperty(value = "联系地址")
	@TableField("tenant_link_address")
	private String tenantLinkAddress;

	@ApiModelProperty(value = "联系人")
	@TableField("tenant_linkman")
	private String tenantLinkman;

	@ApiModelProperty(value = "手机号码")
	@TableField("tenant_linkman_mobile")
	private String tenantLinkmanMobile;

	@ApiModelProperty(value = "单位电话")
	@TableField("tenant_linkman_tel")
	private String tenantLinkmanTel;

	@ApiModelProperty(value = "邮箱")
	@TableField("tenant_linkman_email")
	private String tenantLinkmanEmail;

	@ApiModelProperty(value = "QQ号码")
	@TableField("tenant_linkman_qq")
	private String tenantLinkmanQq;

	@ApiModelProperty(value = "租户类型（1使用单位/2水表厂商/3代收机构/4内部运营/5分销商）")
	@TableField("tenant_type")
	private Integer tenantType;

	@ApiModelProperty(value = "注册时间")
	@TableField("tenant_register_time")
	private Date tenantRegisterTime;

	@ApiModelProperty(value = "开票类别（1普通纸制发票/2普通电子发票/3专用纸制发票）")
	@TableField("invoice_type")
	private Integer invoiceType;

	@ApiModelProperty(value = "开票名称")
	@TableField("invoice_name")
	private String invoiceName;

	@ApiModelProperty(value = "税号")
	@TableField("invoice_tax_no")
	private String invoiceTaxNo;

	@ApiModelProperty(value = "开票地址")
	@TableField("invoice_address")
	private String invoiceAddress;

	@ApiModelProperty(value = "开票电话")
	@TableField("invoice_tel")
	private String invoiceTel;

	@ApiModelProperty(value = "开户行行号")
	@TableField("invoice_bank_code")
	private String invoiceBankCode;

	@ApiModelProperty(value = "开户行名称")
	@TableField("invoice_bank_name")
	private String invoiceBankName;

	@ApiModelProperty(value = "开户账号")
	@TableField("invoice_bank_account_no")
	private String invoiceBankAccountNo;

	@ApiModelProperty(value = "租户KEY")
	@TableField("tenant_accesskey")
	private String tenantAccesskey;


}
