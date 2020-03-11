package com.zlsrj.wms.api.entity;

import java.io.Serializable;

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
@TableName("t_op_system_design")
@ApiModel(value = "SystemDesign对象", description = "系统定义")
public class SystemDesign implements Serializable {

	private static final long serialVersionUID = 1431311720108129233L;

	@ApiModelProperty(value = "系统ID")
	@TableId(value = "id", type = IdType.INPUT)
	private String id;

	@ApiModelProperty(value = "依赖模块编码")
	@TableField("rely_id")
	private Long relyId;

	@ApiModelProperty(value = "模块名称")
	@TableField("module_name")
	private String moduleName;

	@ApiModelProperty(value = "开放对象（1：使用单位；2：供应单位；3：内部运营）")
	@TableField("open_tenant_type")
	private Integer openTenantType;

	@ApiModelProperty(value = "运行环境（1：PC；2：移动端）")
	@TableField("run_env_type")
	private Integer runEnvType;

	@ApiModelProperty(value = "价格政策（0：免费；1：按量付费；2：固定价格）")
	@TableField("price_policy_type")
	private Integer pricePolicyType;

	@ApiModelProperty(value = "计费周期（1：实时；2：按天；3：按月；4：按年）")
	@TableField("bill_cycle_type")
	private Integer billCycleType;

	@ApiModelProperty(value = "开放基础版（1：开放；0：不开放）")
	@TableField("basic_on")
	private Integer basicOn;

	@ApiModelProperty(value = "开放高级版（1：开放；0：不开放）")
	@TableField("advance_on")
	private Integer advanceOn;

	@ApiModelProperty(value = "开放旗舰版（1：开放；0：不开放）")
	@TableField("ultimate_on")
	private Integer ultimateOn;

	@ApiModelProperty(value = "功能发布（1：已发布；0：未发布）")
	@TableField("module_release_on")
	private Integer moduleReleaseOn;


}
