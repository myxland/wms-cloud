package com.zlsrj.wms.api.entity;

import java.io.Serializable;

import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.serializer.ToStringSerializer;

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
@ApiModel(value = "TenantEmployeeRole对象批量", description = "员工角色批量")
public class TenantEmployeeRoleBatch implements Serializable {

	private static final long serialVersionUID = 2864687254877305847L;

	@ApiModelProperty(value = "租户编号")
	@JSONField(serializeUsing = ToStringSerializer.class)
	private Long tenantId;

	@ApiModelProperty(value = "员工编号批量，以英文逗号,分隔")
	private String empIds;

	@ApiModelProperty(value = "角色编号批量，以英文逗号,分隔")
	private String roleIds;

}
