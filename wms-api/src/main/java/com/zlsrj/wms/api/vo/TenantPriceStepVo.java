package com.zlsrj.wms.api.vo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.serializer.ToStringSerializer;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel(value = "TenantPriceStep对象", description = "阶梯明细")
public class TenantPriceStepVo implements Serializable {

	private static final long serialVersionUID = 1471110365931151053L;

	@ApiModelProperty(value = "阶梯明细ID")
	private String id;

	@ApiModelProperty(value = "租户ID")
	private String tenantId;

	@ApiModelProperty(value = "租户ID")
	private String tenantName;

	@ApiModelProperty(value = "水价明细ID")
	private String priceDetailId;

	@ApiModelProperty(value = "阶梯级次")
	private Integer stepClass;

	@ApiModelProperty(value = "阶梯起始量")
	private BigDecimal startCode;

	@ApiModelProperty(value = "阶梯终止量")
	private BigDecimal endCode;

	@ApiModelProperty(value = "单价")
	private BigDecimal stepPrice;

	@ApiModelProperty(value = "标准用水人数")
	private Integer stepUsers;

	@ApiModelProperty(value = "超人数增补量")
	private BigDecimal stepUsersAdd;

	@ApiModelProperty(value = "数据新增时间")
	private Date addTime;

	@ApiModelProperty(value = "数据修改时间")
	private Date updateTime;

}
