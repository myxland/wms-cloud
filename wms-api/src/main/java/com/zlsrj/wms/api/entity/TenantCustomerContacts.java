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
@TableName("tenant_customer_contacts")
@ApiModel(value = "TenantCustomerContacts对象", description = "用户联系方式")
public class TenantCustomerContacts implements Serializable {

	private static final long serialVersionUID = 1291231161114412126L;

	@ApiModelProperty(value = "用户联系ID")
	@TableId(value = "id", type = IdType.INPUT)
	private String id;

	@ApiModelProperty(value = "租户ID")
	@TableField("tenant_id")
	private String tenantId;

	@ApiModelProperty(value = "用户ID")
	@TableField("customer_id")
	private String customerId;

	@ApiModelProperty(value = "用户号")
	@TableField("customer_code")
	private String customerCode;

	@ApiModelProperty(value = "联系人姓名")
	@TableField("contacts_name")
	private String contactsName;

	@ApiModelProperty(value = "联系人地址")
	@TableField("contacts_address")
	private String contactsAddress;

	@ApiModelProperty(value = "主联系人（1：是；0：否）")
	@TableField("contacts_main")
	private Integer contactsMain;

	@ApiModelProperty(value = "性别（1：男；0：女）")
	@TableField("contacts_sex")
	private Integer contactsSex;

	@ApiModelProperty(value = "出生日期")
	@TableField("contacts_birthday")
	private Date contactsBirthday;

	@ApiModelProperty(value = "手机号码")
	@TableField("contacts_mobile")
	private String contactsMobile;

	@ApiModelProperty(value = "固定电话")
	@TableField("contacts_tel")
	private String contactsTel;

	@ApiModelProperty(value = "邮箱地址")
	@TableField("contacts_email")
	private String contactsEmail;

	@ApiModelProperty(value = "微信号")
	@TableField("contacts_wx")
	private String contactsWx;

	@ApiModelProperty(value = "QQ号")
	@TableField("contacts_qq")
	private String contactsQq;

	@ApiModelProperty(value = "备注")
	@TableField("contacts_memo")
	private String contactsMemo;

	@ApiModelProperty(value = "数据新增时间")
	@TableField("add_time")
	private Date addTime;

	@ApiModelProperty(value = "数据修改时间")
	@TableField("update_time")
	private Date updateTime;

}