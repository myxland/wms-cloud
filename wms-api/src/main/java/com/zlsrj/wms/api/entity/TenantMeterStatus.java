package com.zlsrj.wms.api.entity;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

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
@TableName("tenant_meter_status")
@ApiModel(value = "TenantMeterStatus对象", description = "水表状况")
public class TenantMeterStatus implements Serializable {

	private static final long serialVersionUID = 1106711615191214121L;

	@ApiModelProperty(value = "表况ID")
	@TableId(value = "id", type = IdType.INPUT)
	private String id;

	@ApiModelProperty(value = "租户ID")
	@TableField("tenant_id")
	private String tenantId;

	@ApiModelProperty(value = "表况名称")
	@TableField("meter_status_name")
	private String meterStatusName;

	@ApiModelProperty(value = "水量计算方式（1：自动计算；2：手工输入）")
	@TableField("usenum_calc_type")
	private Integer usenumCalcType;

	@ApiModelProperty(value = "生成工单类型（0：不生成；1：故障换表；3：周期换表）")
	@TableField("work_bill_type")
	private Integer workBillType;

	@ApiModelProperty(value = "创建方式（1：平台创建；2：客户自建）")
	@TableField("create_type")
	private Integer createType;


}
