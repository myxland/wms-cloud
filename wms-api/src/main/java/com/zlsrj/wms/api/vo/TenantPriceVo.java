package com.zlsrj.wms.api.vo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.serializer.ToStringSerializer;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel(value = "TenantPrice对象", description = "水价列表")
public class TenantPriceVo implements Serializable {

	private static final long serialVersionUID = 2013300610891513441L;

	@ApiModelProperty(value = "")
	private String id;

	@ApiModelProperty(value = "租户ID")
	private String tenantId;

	@ApiModelProperty(value = "租户ID")
	private String tenantName;

	@ApiModelProperty(value = "排序")
	private Integer priceOrder;

	@ApiModelProperty(value = "水价名称")
	private String priceName;

	@ApiModelProperty(value = "父级ID")
	private String priceParentId;

	@ApiModelProperty(value = "水价版本")
	private String priceVersion;

	@ApiModelProperty(value = "版本说明")
	private String priceVersionMemo;

	@ApiModelProperty(value = "营销区域")
	private String marketingAreaId;

	@ApiModelProperty(value = "备注")
	private String priceMemo;

	@ApiModelProperty(value = "数据新增时间")
	private Date addTime;

	@ApiModelProperty(value = "数据修改时间")
	private Date updateTime;

	@ApiModelProperty(value = "子级水价列表列表")
	private List<TenantPriceVo> children;
	
	@ApiModelProperty(value = "是否包含子级水价列表")
	private boolean hasChildren;
	
}
