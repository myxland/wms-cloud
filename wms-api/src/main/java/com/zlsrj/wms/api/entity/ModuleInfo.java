package com.zlsrj.wms.api.entity;

import java.io.Serializable;
import java.util.List;

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
@TableName("module_info")
@ApiModel(value = "ModuleInfo对象", description = "模块信息")
public class ModuleInfo implements Serializable {

	private static final long serialVersionUID = 1911413321312914105L;

	@ApiModelProperty(value = "服务模块ID")
	@TableId(value = "id", type = IdType.INPUT)
	private String id;

	@ApiModelProperty(value = "服务模块名称")
	@TableField("module_name")
	private String moduleName;

	@ApiModelProperty(value = "开放对象（1：使用单位；2：水表厂商；3：代收机构；4：内部运营；5：分销商）")
	@TableField("open_target")
	private Integer openTarget;

	@ApiModelProperty(value = "运行环境（1：PC端；2：移动端；3：微信端；4：支付宝端；5：API接口；6：自助终端）")
	@TableField("run_env")
	private Integer runEnv;

	@ApiModelProperty(value = "依赖模块ID")
	@TableField("rely_module_id")
	private String relyModuleId;

	@ApiModelProperty(value = "计费模式（1：默认开通；2：免费；3：按量付费；4：固定价格；5：阶梯价格）")
	@TableField("billing_mode")
	private Integer billingMode;

	@ApiModelProperty(value = "计费周期（1：实时；2：按天；3：按月；4：按年）")
	@TableField("billing_cycle")
	private Integer billingCycle;

	@ApiModelProperty(value = "开放基础版（1：开放；0：不开放）")
	@TableField("basic_edition_on")
	private Integer basicEditionOn;

	@ApiModelProperty(value = "开放高级版（1：开放；0：不开放）")
	@TableField("advance_edition_on")
	private Integer advanceEditionOn;

	@ApiModelProperty(value = "开放旗舰版（1：开放；0：不开放）")
	@TableField("ultimate_edition_on")
	private Integer ultimateEditionOn;

	@ApiModelProperty(value = "服务发布状态（1：发布 ；0：未发布）")
	@TableField("module_on")
	private Integer moduleOn;

	@ApiModelProperty(value = "应用APPID")
	@TableField("module_appid")
	private String moduleAppid;

	@ApiModelProperty(value = "基础模块价格列表")
	@TableField(exist = false)
	private List<ModulePrice> basicModulePriceList;

	@ApiModelProperty(value = "高级模块价格列表")
	@TableField(exist = false)
	private List<ModulePrice> advanceModulePriceList;

	@ApiModelProperty(value = "旗舰模块价格列表")
	@TableField(exist = false)
	private List<ModulePrice> ultimateModulePriceList;
}
