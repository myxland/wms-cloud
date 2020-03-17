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
@ApiModel(value = "TenantMeterReadSituation对象", description = "抄表表况")
public class TenantMeterReadSituationVo implements Serializable {

	private static final long serialVersionUID = 1131010568141101146L;

	@ApiModelProperty(value = "抄表表况ID")
	private String id;

	@ApiModelProperty(value = "租户ID")
	private String tenantId;

	@ApiModelProperty(value = "租户ID")
	private String tenantName;

	@ApiModelProperty(value = "名称")
	private String readSituationName;

	@ApiModelProperty(value = "结构化数据")
	private String readSituationData;

	@ApiModelProperty(value = "数据新增时间")
	private Date addTime;

	@ApiModelProperty(value = "数据修改时间")
	private Date updateTime;

}
