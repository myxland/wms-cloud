package com.zlsrj.wms.api.entity;

import java.io.Serializable;
import java.math.BigDecimal;

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
@TableName("t_op_tenant_config")
@ApiModel(value = "TenantConfig对象", description = "租户基础配置")
public class TenantConfig implements Serializable {

	private static final long serialVersionUID = 2107861412111272410L;

	@ApiModelProperty(value = "租户编号")
	@TableId(value = "id", type = IdType.INPUT)
	private String id;

	@ApiModelProperty(value = "租户编号")
	@TableField("tenant_id")
	private String tenantId;

	@ApiModelProperty(value = "是否启用部分缴费（1：启用；0：不启用）")
	@TableField("part_charge_on")
	private Integer partChargeOn;

	@ApiModelProperty(value = "是否启用违约金（1：启用；0：不启用）")
	@TableField("over_duefine_on")
	private Integer overDuefineOn;

	@ApiModelProperty(value = "违约金宽限天数")
	@TableField("over_duefine_day")
	private Integer overDuefineDay;

	@ApiModelProperty(value = "违约金每天收取比例")
	@TableField("over_duefine_ratio")
	private BigDecimal overDuefineRatio;

	@ApiModelProperty(value = "违约金封顶比例（与欠费金额相比）")
	@TableField("over_duefine_top_ratio")
	private BigDecimal overDuefineTopRatio;

	@ApiModelProperty(value = "预存抵扣方式（1：抄表后即时抵扣；2：人工发起抵扣）")
	@TableField("ycdk_type")
	private Integer ycdkType;


}
