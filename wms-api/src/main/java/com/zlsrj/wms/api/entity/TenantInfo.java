package com.zlsrj.wms.api.entity;

import java.io.Serializable;
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
@TableName("t_op_tenant_info")
@ApiModel(value = "TenantInfo对象", description = "租户")
public class TenantInfo implements Serializable {

	private static final long serialVersionUID = 1483810642213014113L;

	@ApiModelProperty(value = "租户编号")
	@TableId(value = "id", type = IdType.INPUT)
	@JSONField(serializeUsing = ToStringSerializer.class)
	private Long id;

	@ApiModelProperty(value = "租户名称")
	@TableField("tenant_name")
	private String tenantName;

	@ApiModelProperty(value = "显示名称")
	@TableField("display_name")
	private String displayName;

	@ApiModelProperty(value = "省")
	@TableField("tenant_province")
	private String tenantProvince;

	@ApiModelProperty(value = "市")
	@TableField("tenant_city")
	private String tenantCity;

	@ApiModelProperty(value = "区县")
	@TableField("tenant_area")
	private String tenantArea;

	@ApiModelProperty(value = "联系地址")
	@TableField("tenant_address")
	private String tenantAddress;

	@ApiModelProperty(value = "联系人")
	@TableField("tenant_contact")
	private String tenantContact;

	@ApiModelProperty(value = "手机号码")
	@TableField("tenant_mobile")
	private String tenantMobile;

	@ApiModelProperty(value = "单位电话")
	@TableField("tenant_tel")
	private String tenantTel;

	@ApiModelProperty(value = "邮箱")
	@TableField("tenant_email")
	private String tenantEmail;

	@ApiModelProperty(value = "QQ号码")
	@TableField("tenant_qq")
	private String tenantQq;

	@ApiModelProperty(value = "租户类型（1：使用单位；2：供应单位；3：内部运营）")
	@TableField("tenant_type")
	private Integer tenantType;

	@ApiModelProperty(value = "租户状态（1：正式；2：试用）")
	@TableField("tenant_status")
	private Integer tenantStatus;

	@ApiModelProperty(value = "注册时间")
	@TableField("reg_time")
	private Date regTime;

	@ApiModelProperty(value = "到期日期")
	@TableField("end_date")
	private Date endDate;


}
