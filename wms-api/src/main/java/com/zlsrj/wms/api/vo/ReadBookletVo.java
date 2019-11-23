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
@ApiModel(value = "ReadBooklet对象", description = "表册信息")
public class ReadBookletVo implements Serializable {

	private static final long serialVersionUID = 1082581191514669131L;

	@ApiModelProperty(value = "系统编号")
	@JSONField(serializeUsing = ToStringSerializer.class)
	private Long id;

	@ApiModelProperty(value = "租户编号")
	@JSONField(serializeUsing = ToStringSerializer.class)
	private Long tenantId;

	@ApiModelProperty(value = "租户名称")
	private String tenantName;

	@ApiModelProperty(value = "表册名称")
	private String bookletName;

	@ApiModelProperty(value = "表册类型（1：远传表；2：机械表）")
	private Integer bookletType;

	@ApiModelProperty(value = "抄表负责人编号")
	@JSONField(serializeUsing = ToStringSerializer.class)
	private Long readEmpId;

	@ApiModelProperty(value = "收费负责人编号")
	@JSONField(serializeUsing = ToStringSerializer.class)
	private Long payEmpId;

	@ApiModelProperty(value = "抄表间隔周期_月")
	private Integer calcCycleInterval;

	@ApiModelProperty(value = "最后一次抄表月份")
	private Date calcMonthLast;

	@ApiModelProperty(value = "下次抄表月份")
	private Date calcMonthNext;

}
