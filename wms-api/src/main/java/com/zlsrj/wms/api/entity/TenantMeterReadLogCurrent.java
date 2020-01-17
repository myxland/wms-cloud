package com.zlsrj.wms.api.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.serializer.ToStringSerializer;
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
@TableName("tenant_meter_read_log_current")
@ApiModel(value = "TenantMeterReadLogCurrent对象", description = "当期抄表计划")
public class TenantMeterReadLogCurrent implements Serializable {

	private static final long serialVersionUID = 2158112121216991391L;

	@ApiModelProperty(value = "抄表计划")
	@TableId(value = "id", type = IdType.INPUT)
	@JSONField(serializeUsing = ToStringSerializer.class)
	private Long id;

	@ApiModelProperty(value = "租户ID")
	@TableField("tenant_id")
	@JSONField(serializeUsing = ToStringSerializer.class)
	private Long tenantId;

	@ApiModelProperty(value = "结算月份")
	@TableField("read_month")
	private Date readMonth;

	@ApiModelProperty(value = "水表ID")
	@TableField("meter_id")
	@JSONField(serializeUsing = ToStringSerializer.class)
	private Long meterId;

	@ApiModelProperty(value = "结算前水表当年累计水量")
	@TableField("meter_year_total_waters_before")
	private BigDecimal meterYearTotalWatersBefore;

	@ApiModelProperty(value = "结算开始时间")
	@TableField("settle_start_time")
	private Date settleStartTime;

	@ApiModelProperty(value = "结算开始指针")
	@TableField("settle_start_pointer")
	private BigDecimal settleStartPointer;

	@ApiModelProperty(value = "本次抄表时间")
	@TableField("current_read_time")
	private Date currentReadTime;

	@ApiModelProperty(value = "本次抄表指针")
	@TableField("current_read_pointer")
	private BigDecimal currentReadPointer;

	@ApiModelProperty(value = "抄表员ID")
	@TableField("read_employee_id")
	@JSONField(serializeUsing = ToStringSerializer.class)
	private Long readEmployeeId;

	@ApiModelProperty(value = "表次抄表状况")
	@TableField("meter_status_id")
	@JSONField(serializeUsing = ToStringSerializer.class)
	private Long meterStatusId;

	@ApiModelProperty(value = "应结算水量")
	@TableField("settle_waters")
	private BigDecimal settleWaters;

	@ApiModelProperty(value = "应收水量")
	@TableField("receivable_waters")
	private BigDecimal receivableWaters;

	@ApiModelProperty(value = "抄表来源（1：移动抄表；2：人工入账；3：远传表导入；4：远传表接口）")
	@TableField("read_source")
	private Integer readSource;

	@ApiModelProperty(value = "抄表状态（0：未抄；1：已抄）")
	@TableField("read_status")
	private Integer readStatus;

	@ApiModelProperty(value = "检查结果（0：正常；1：异常）")
	@TableField("check_result")
	private Integer checkResult;

	@ApiModelProperty(value = "处理结果（1：已处理；2：未处理）")
	@TableField("process_reault")
	private Integer processReault;

	@ApiModelProperty(value = "处理人")
	@TableField("process_employee_id")
	@JSONField(serializeUsing = ToStringSerializer.class)
	private Long processEmployeeId;

	@ApiModelProperty(value = "处理时间")
	@TableField("process_time")
	private Date processTime;

	@ApiModelProperty(value = "处理方式（1：重新抄表；2：通过）")
	@TableField("process_type")
	private Integer processType;


}
