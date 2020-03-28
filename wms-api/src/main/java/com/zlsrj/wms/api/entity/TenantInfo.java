package com.zlsrj.wms.api.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.zlsrj.wms.common.annotation.DictionaryEntity;
import com.zlsrj.wms.common.annotation.DictionaryText;
import com.zlsrj.wms.common.annotation.DictionaryValue;

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
@DictionaryEntity
@ApiModel(value = "TenantInfo对象", description = "租户表")
public class TenantInfo implements Serializable {

	private static final long serialVersionUID = 1483810642213014113L;

	@ApiModelProperty(value = "租户ID")
	@DictionaryValue
	@TableId(value = "id", type = IdType.INPUT)
	private String id;

	@ApiModelProperty(value = "租户名称")
	@DictionaryText
	@TableField("tenant_name")
	private String tenantName;

	@ApiModelProperty(value = "账户余额")
	@TableField("tenant_balance")
	private BigDecimal tenantBalance;

	@ApiModelProperty(value = "透支额度")
	@TableField("tenant_overdraw")
	private BigDecimal tenantOverdraw;

	@ApiModelProperty(value = "租户显示名称")
	@TableField("tenant_display_name")
	private String tenantDisplayName;

	@ApiModelProperty(value = "省")
	@TableField("tenant_province")
	private String tenantProvince;

	@ApiModelProperty(value = "市")
	@TableField("tenant_city")
	private String tenantCity;

	@ApiModelProperty(value = "区/县")
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

	@ApiModelProperty(value = "租户类型（1：使用单位；2：水表厂商；3：代收机构；4：内部运营；5：分销商）")
	@TableField("tenant_type")
	private Integer tenantType;

	@ApiModelProperty(value = "注册时间")
	@TableField("tenant_register_time")
	private Date tenantRegisterTime;

	@ApiModelProperty(value = "开票类别（1：普通纸制发票；2：普通电子发票；3：专用纸制发票）")
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
	
	@ApiModelProperty(value = "微信公众号APPID")
	@TableField("wx_appid")
	private String wxAppid;
	
	@ApiModelProperty(value = "微信公众号AppSecret")
	@TableField("wx_appsecret")
	private String wxAppsecret;
	
	@ApiModelProperty(value = "微信商户ID")
	@TableField("wx_account_id")
	private String wxAccountId;
	
	@ApiModelProperty(value = "微信商户API密钥")
	@TableField("wx_account_api_key")
	private String wxAccountApiKey;
	
	@ApiModelProperty(value = "短信签名")
	@TableField("sms_signature")
	private String smsSignature;
	  
	@ApiModelProperty(value = "租户KEY")
	@TableField("tenant_accesskey")
	private String tenantAccesskey;

	@ApiModelProperty(value = "部门结构")
	@TableField(exist = false)
	private Integer departmentStructure;
	
	@ApiModelProperty(value = "阶梯水价")
	@TableField(exist = false)
	private Integer priceStepOn;
	
	@ApiModelProperty(value = "营销机构类型（1：扁平型；2：复杂型）")
	@TableField(exist = false)
	private Integer marketingAreaType;
}
