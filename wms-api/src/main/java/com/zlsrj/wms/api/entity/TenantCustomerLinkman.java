package com.zlsrj.wms.api.entity;

import java.io.Serializable;
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
@TableName("tenant_customer_linkman")
@ApiModel(value = "TenantCustomerLinkman对象", description = "用户联系人")
public class TenantCustomerLinkman implements Serializable {

	private static final long serialVersionUID = 5713311957832812107L;

	@ApiModelProperty(value = "联系人ID")
	@TableId(value = "id", type = IdType.INPUT)
	private String id;

	@ApiModelProperty(value = "租户ID")
	@TableField("tenant_id")
	private String tenantId;

	@ApiModelProperty(value = "客户ID")
	@TableField("customer_id")
	private String customerId;

	@ApiModelProperty(value = "联系人姓名")
	@TableField("linkman_name")
	private String linkmanName;

	@ApiModelProperty(value = "联系人地址")
	@TableField("linkman_address")
	private String linkmanAddress;

	@ApiModelProperty(value = "主联系人（1：是；0：否）")
	@TableField("linkman_main_on")
	private Integer linkmanMainOn;

	@ApiModelProperty(value = "性别（1：男；2：女）")
	@TableField("linkman_sex")
	private Integer linkmanSex;

	@ApiModelProperty(value = "出生日期")
	@TableField("linkman_birthday")
	private Date linkmanBirthday;

	@ApiModelProperty(value = "手机号码")
	@TableField("linkman_mobile")
	private String linkmanMobile;

	@ApiModelProperty(value = "固定电话号码")
	@TableField("linkman_tel")
	private String linkmanTel;

	@ApiModelProperty(value = "邮箱地址")
	@TableField("linkman_email")
	private String linkmanEmail;

	@ApiModelProperty(value = "微信号")
	@TableField("linkman_personal_wx")
	private String linkmanPersonalWx;

	@ApiModelProperty(value = "QQ号")
	@TableField("linkman_qq")
	private String linkmanQq;

	@ApiModelProperty(value = "备注")
	@TableField("linkman_remark")
	private String linkmanRemark;


}
