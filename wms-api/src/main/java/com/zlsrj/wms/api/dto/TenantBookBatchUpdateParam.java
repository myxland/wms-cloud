package com.zlsrj.wms.api.dto;

import java.io.Serializable;
import java.util.Date;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel(value = "TenantBook批量更新参数", description = "表册信息")
public class TenantBookBatchUpdateParam implements Serializable {

	private static final long serialVersionUID = -4383561639304963145L;

	@ApiModelProperty(value = "批量更新表册ID")
	private String ids;

	@ApiModelProperty(value = "营销区域")
	private String bookMarketingAreaId;

	@ApiModelProperty(value = "欠费结转: 1 结转 0不结转")
	private Integer own = 0;

	@ApiModelProperty(value = "应收结转: 1 结转 0不结转")
	private Integer receivable = 0;

	@ApiModelProperty(value = "抄表记录结转:1结转 0不结转")
	private Integer meterread = 0;

	@ApiModelProperty(value = "实收结转:1结转 0不结转")
	private Integer pay = 0;
}
