package com.zlsrj.wms.api.vo;

import java.io.Serializable;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel(value = "TenantCaliber对象", description = "水表口径")
public class TenantCaliberVo implements Serializable {

	private static final long serialVersionUID = 6310443959001110146L;

	@ApiModelProperty(value = "口径ID")
	private String id;

	@ApiModelProperty(value = "租户ID")
	private String tenantId;

	@ApiModelProperty(value = "租户ID")
	private String tenantName;

	@ApiModelProperty(value = "口径名称")
	private String caliberName;

}
