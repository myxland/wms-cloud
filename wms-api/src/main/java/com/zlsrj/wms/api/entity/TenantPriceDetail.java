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
@TableName("tenant_price_detail")
@ApiModel(value = "TenantPriceDetail对象", description = "水价明细")
public class TenantPriceDetail implements Serializable {

	private static final long serialVersionUID = 4946512119311115151L;

	@ApiModelProperty(value = "水价明细ID")
	@TableId(value = "id", type = IdType.INPUT)
	private String id;

	@ApiModelProperty(value = "租户ID")
	@TableField("tenant_id")
	private String tenantId;

	@ApiModelProperty(value = "水表列表ID")
	@TableField("price_id")
	private String priceId;

	@ApiModelProperty(value = "费用项目")
	@TableField("price_item_id")
	private String priceItemId;

	@ApiModelProperty(value = "计费规则")
	@TableField("price_rule")
	private Integer priceRule;

	@ApiModelProperty(value = "单价")
	@TableField("detail_price")
	private BigDecimal detailPrice;

	@ApiModelProperty(value = "数据新增时间")
	@TableField("add_time")
	private Date addTime;

	@ApiModelProperty(value = "数据修改时间")
	@TableField("update_time")
	private Date updateTime;

}