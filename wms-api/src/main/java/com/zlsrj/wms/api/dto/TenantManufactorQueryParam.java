package com.zlsrj.wms.api.dto;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel(value = "TenantManufactor查询参数", description = "水表厂商")
public class TenantManufactorQueryParam implements Serializable {

	private static final long serialVersionUID = 1013610133156097141L;

	@ApiModelProperty(value = "制造商ID")
	private Long id;

	@ApiModelProperty(value = "租户ID")
	private Long tenantId;

	@ApiModelProperty(value = "制造商名称")
	private String manufactorName;

	@ApiModelProperty(value = "远传表接入APIKEY")
	private String manufactorApikey;

}

