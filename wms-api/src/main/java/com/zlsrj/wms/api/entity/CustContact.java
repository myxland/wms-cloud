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
@TableName("t_cust_contact")
@ApiModel(value = "CustContact对象", description = "用户联系人")
public class CustContact implements Serializable {

	private static final long serialVersionUID = 1012102851288134135L;

	@ApiModelProperty(value = "系统编号")
	@TableId(value = "id", type = IdType.INPUT)
	private String id;

	@ApiModelProperty(value = "租户编号")
	@TableField("tenant_id")
	private String tenantId;

	@ApiModelProperty(value = "用户编号")
	@TableField("cust_id")
	private String custId;

	@ApiModelProperty(value = "联系人姓名")
	@TableField("contact_name")
	private String contactName;

	@ApiModelProperty(value = "主联系人（1：是；0：否）")
	@TableField("main_on")
	private Integer mainOn;

	@ApiModelProperty(value = "性别（1：男；2：女）")
	@TableField("gender")
	private Integer gender;

	@ApiModelProperty(value = "生日")
	@TableField("birthday")
	private Date birthday;

	@ApiModelProperty(value = "手机号码")
	@TableField("mobile")
	private String mobile;

	@ApiModelProperty(value = "固定电话号码")
	@TableField("tel")
	private String tel;

	@ApiModelProperty(value = "邮箱地址")
	@TableField("email")
	private String email;

	@ApiModelProperty(value = "微信号")
	@TableField("personal_wx")
	private String personalWx;

	@ApiModelProperty(value = "QQ号")
	@TableField("qq")
	private String qq;

	@ApiModelProperty(value = "备注")
	@TableField("remark")
	private String remark;


}
