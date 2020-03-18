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
@TableName("tenant_price")
@ApiModel(value = "TenantPrice对象", description = "水价列表")
public class TenantPrice implements Serializable {

	private static final long serialVersionUID = 1063150718451013961L;

	@ApiModelProperty(value = "")
	@TableId(value = "id", type = IdType.INPUT)
	private String id;

	@ApiModelProperty(value = "租户ID")
	@TableField("tenant_id")
	private String tenantId;

	@ApiModelProperty(value = "排序")
	@TableField("price_order")
	private Integer priceOrder;

	@ApiModelProperty(value = "水价名称")
	@TableField("price_name")
	private String priceName;

	@ApiModelProperty(value = "父级ID")
	@TableField("price_parent_id")
	private String priceParentId;

	@ApiModelProperty(value = "水价版本")
	@TableField("price_version")
	private String priceVersion;

	@ApiModelProperty(value = "版本说明")
	@TableField("price_version_memo")
	private String priceVersionMemo;

	@ApiModelProperty(value = "营销区域")
	@TableField("marketing_area_id")
	private String marketingAreaId;

	@ApiModelProperty(value = "备注")
	@TableField("price_memo")
	private String priceMemo;

	@ApiModelProperty(value = "数据新增时间")
	@TableField("add_time")
	private Date addTime;

	@ApiModelProperty(value = "数据修改时间")
	@TableField("update_time")
	private Date updateTime;

}