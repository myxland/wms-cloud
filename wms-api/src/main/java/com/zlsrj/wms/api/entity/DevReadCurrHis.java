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
@TableName("t_dev_read_curr_his")
@ApiModel(value = "DevReadCurrHis对象", description = "历史抄表信息")
public class DevReadCurrHis implements Serializable {

	private static final long serialVersionUID = 4312782159010121267L;

	@ApiModelProperty(value = "系统编号")
	@TableId(value = "id", type = IdType.INPUT)
	@JSONField(serializeUsing = ToStringSerializer.class)
	private Long id;

	@ApiModelProperty(value = "租户编号")
	@TableField("tenant_id")
	@JSONField(serializeUsing = ToStringSerializer.class)
	private Long tenantId;

	@ApiModelProperty(value = "抄表月份")
	@TableField("read_month")
	private Date readMonth;

	@ApiModelProperty(value = "表具系统编号")
	@TableField("dev_id")
	@JSONField(serializeUsing = ToStringSerializer.class)
	private Long devId;

	@ApiModelProperty(value = "父表具编号")
	@TableField("parent_dev_id")
	@JSONField(serializeUsing = ToStringSerializer.class)
	private Long parentDevId;

	@ApiModelProperty(value = "本次计费前当年累计水量")
	@TableField("year_use_num")
	private BigDecimal yearUseNum;

	@ApiModelProperty(value = "上次计费日期")
	@TableField("last_calc_date")
	private Date lastCalcDate;

	@ApiModelProperty(value = "上次计费止码")
	@TableField("last_calc_code")
	private BigDecimal lastCalcCode;

	@ApiModelProperty(value = "抄表人编号")
	@TableField("curr_read_emp_id")
	@JSONField(serializeUsing = ToStringSerializer.class)
	private Long currReadEmpId;

	@ApiModelProperty(value = "本次抄表日期")
	@TableField("curr_read_date")
	private Date currReadDate;

	@ApiModelProperty(value = "本次抄表止码")
	@TableField("curr_read_code")
	private BigDecimal currReadCode;

	@ApiModelProperty(value = "表次表具状况")
	@TableField("curr_dev_status")
	private Integer currDevStatus;

	@ApiModelProperty(value = "本次抄表水量")
	@TableField("curr_use_num")
	private BigDecimal currUseNum;

	@ApiModelProperty(value = "本次计费水量")
	@TableField("curr_calc_use_num")
	private BigDecimal currCalcUseNum;

	@ApiModelProperty(value = "抄表来源（1：移动抄表；2：人工入账；3：远传表）")
	@TableField("read_source")
	private Integer readSource;

	@ApiModelProperty(value = "抄表状态（1：未抄；2：已抄）")
	@TableField("read_status")
	private Integer readStatus;

	@ApiModelProperty(value = "审核状态（1：正常；2：异常）")
	@TableField("check_result")
	private Integer checkResult;

	@ApiModelProperty(value = "处理状态（1：已计费；2：待审核；3：已审核）")
	@TableField("proc_reault")
	private Integer procReault;

	@ApiModelProperty(value = "审核人")
	@TableField("proc_man")
	@JSONField(serializeUsing = ToStringSerializer.class)
	private Long procMan;

	@ApiModelProperty(value = "审核时间")
	@TableField("proc_time")
	private Date procTime;


}
