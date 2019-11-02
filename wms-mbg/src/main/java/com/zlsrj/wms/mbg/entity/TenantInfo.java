package com.zlsrj.wms.mbg.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@Builder
@TableName("t_op_tenant_info")
@ApiModel(value = "TenantInfo对象", description = "租户")
public class TenantInfo implements Serializable {

	private static final long serialVersionUID = 1483810642213014113L;

	@ApiModelProperty(value = "租户编号")
	@TableId(value = "id", type = IdType.INPUT)
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
	@TableField("tenant_linkman")
	private String tenantLinkman;

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

	@ApiModelProperty(value = "租户状态（正式/试用）")
	@TableField("tenant_status")
	private Integer tenantStatus;

	@ApiModelProperty(value = "注册时间")
	@TableField("reg_time")
	private Date regTime;

	@ApiModelProperty(value = "到期日期")
	@TableField("end_date")
	private Date endDate;

	@ApiModelProperty(value = "税号")
	@TableField("credit_number")
	private String creditNumber;

	@ApiModelProperty(value = "开票地址")
	@TableField("invoice_address")
	private String invoiceAddress;

	@ApiModelProperty(value = "开户行行号")
	@TableField("bank_no")
	private String bankNo;

	@ApiModelProperty(value = "开户行名称")
	@TableField("bank_name")
	private String bankName;

	@ApiModelProperty(value = "开户账号")
	@TableField("account_no")
	private String accountNo;

	@ApiModelProperty(value = "是否启用部分缴费（启用/不启用）")
	@TableField("part_charge_on")
	private Integer partChargeOn;

	@ApiModelProperty(value = "是否启用违约金（启用/不启用）")
	@TableField("over_duefine_on")
	private Integer overDuefineOn;

	@ApiModelProperty(value = "违约金宽限天数（从计费之日开始）")
	@TableField("over_duefine_day")
	private Integer overDuefineDay;

	@ApiModelProperty(value = "违约金每天收取比例")
	@TableField("over_duefine_ratio")
	private BigDecimal overDuefineRatio;

	@ApiModelProperty(value = "违约金封顶比例（与欠费金额相比）")
	@TableField("over_duefine_top_ratio")
	private BigDecimal overDuefineTopRatio;

	@ApiModelProperty(value = "预存抵扣方式（抄表后即时抵扣/人工发起抵扣）")
	@TableField("ycdk_type")
	private Integer ycdkType;

}
