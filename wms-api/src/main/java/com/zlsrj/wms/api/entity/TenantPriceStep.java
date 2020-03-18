package com.zlsrj.wms.api.entity;

import java.io.Serializable;
import java.math.BigDecimal;
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
@TableName("tenant_price_step")
@ApiModel(value = "TenantPriceStep对象", description = "阶梯明细")
public class TenantPriceStep implements Serializable {

	private static final long serialVersionUID = 1113721127569712549L;

	@ApiModelProperty(value = "阶梯明细ID")
	@TableId(value = "id", type = IdType.INPUT)
	private String id;

	@ApiModelProperty(value = "租户ID")
	@TableField("tenant_id")
	private String tenantId;

	@ApiModelProperty(value = "水价列表ID")
	@TableField("price_id")
	private String priceId;

	@ApiModelProperty(value = "费用项目ID")
	@TableField("price_item_id")
	private String priceItemId;

	@ApiModelProperty(value = "阶梯级次")
	@TableField("step_class")
	private Integer stepClass;

	@ApiModelProperty(value = "阶梯起始量")
	@TableField("start_code")
	private BigDecimal startCode;

	@ApiModelProperty(value = "阶梯终止量")
	@TableField("end_code")
	private BigDecimal endCode;

	@ApiModelProperty(value = "单价")
	@TableField("step_price")
	private BigDecimal stepPrice;

	@ApiModelProperty(value = "标准用水人数")
	@TableField("step_users")
	private Integer stepUsers;

	@ApiModelProperty(value = "超人数增补量")
	@TableField("step_users_add")
	private BigDecimal stepUsersAdd;

	@ApiModelProperty(value = "数据新增时间")
	@TableField("add_time")
	private Date addTime;

	@ApiModelProperty(value = "数据修改时间")
	@TableField("update_time")
	private Date updateTime;

}