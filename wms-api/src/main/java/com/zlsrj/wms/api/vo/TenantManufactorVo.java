package com.zlsrj.wms.api.vo;

import java.io.Serializable;

import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.serializer.ToStringSerializer;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel(value = "TenantManufactor对象", description = "水表厂商")
public class TenantManufactorVo implements Serializable {

	private static final long serialVersionUID = 4121576149815951310L;

	@ApiModelProperty(value = "制造商ID")
	@JSONField(serializeUsing = ToStringSerializer.class)
	private Long id;

	@ApiModelProperty(value = "租户ID")
	@JSONField(serializeUsing = ToStringSerializer.class)
	private Long tenantId;

	@ApiModelProperty(value = "租户ID")
	private String tenantName;

	@ApiModelProperty(value = "制造商名称")
	private String manufactorName;

	@ApiModelProperty(value = "远传表接入APIKEY")
	private String manufactorApikey;

}
