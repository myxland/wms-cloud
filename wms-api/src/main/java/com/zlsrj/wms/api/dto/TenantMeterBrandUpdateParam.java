package com.zlsrj.wms.api.dto;

import java.io.Serializable;
import java.util.Date;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel(value = "TenantMeterBrand更新参数", description = "水表品牌")
public class TenantMeterBrandUpdateParam implements Serializable {

	private static final long serialVersionUID = 6732915862101405224L;

	@ApiModelProperty(value = "水表品牌ID")
	private String id;

	@ApiModelProperty(value = "租户ID")
	private String tenantId;

	@ApiModelProperty(value = "名称")
	private String meterBrandName;

	@ApiModelProperty(value = "结构化数据")
	private String meterBrandData;

	@ApiModelProperty(value = "数据新增时间")
	private Date addTime;

	@ApiModelProperty(value = "数据修改时间")
	private Date updateTime;

}

