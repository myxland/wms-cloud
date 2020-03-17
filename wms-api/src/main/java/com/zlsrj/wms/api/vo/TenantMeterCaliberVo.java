package com.zlsrj.wms.api.vo;

import java.io.Serializable;
import java.util.Date;

import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.serializer.ToStringSerializer;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel(value = "TenantMeterCaliber对象", description = "水表口径字典表")
public class TenantMeterCaliberVo implements Serializable {

	private static final long serialVersionUID = 1150621315023141361L;

	@ApiModelProperty(value = "")
	private String id;

	@ApiModelProperty(value = "租户ID")
	private String tenantId;

	@ApiModelProperty(value = "租户ID")
	private String tenantName;

	@ApiModelProperty(value = "名称")
	private String meterCaliberName;

	@ApiModelProperty(value = "结构化数据")
	private String meterCaliberData;

	@ApiModelProperty(value = "数据新增时间")
	private Date addTime;

	@ApiModelProperty(value = "数据修改时间")
	private Date updateTime;

}
