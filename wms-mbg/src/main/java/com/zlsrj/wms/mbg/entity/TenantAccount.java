package com.zlsrj.wms.mbg.entity;

import java.io.Serializable;
import java.math.BigDecimal;

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
@TableName("t_op_tenant_account")
@ApiModel(value = "TenantAccount对象", description = "租户账户")
public class TenantAccount implements Serializable {

	private static final long serialVersionUID = 6014531112141312131L;

	@ApiModelProperty(value = "编号ID")
	@TableId(value = "id", type = IdType.INPUT)
	private Long id;

	@ApiModelProperty(value = "租房编号")
	@TableField("tenant_id")
	private Long tenantId;

	@ApiModelProperty(value = "账户余额")
	@TableField("account_balance")
	private BigDecimal accountBalance;

}
