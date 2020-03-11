package com.zlsrj.wms.api.entity;

import java.io.Serializable;


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
@ApiModel(value = "TenantModule对象批量", description = "租户模块批量")
public class TenantModuleBatch implements Serializable {

	private static final long serialVersionUID = 539050141821183731L;

	@ApiModelProperty(value = "租户编号批量，以英文逗号,分隔")
	private String tenantIds;

	@ApiModelProperty(value = "模块编号批量，以英文逗号,分隔")
	private String moduleIds;

}
