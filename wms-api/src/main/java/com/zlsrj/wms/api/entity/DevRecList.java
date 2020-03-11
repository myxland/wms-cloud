package com.zlsrj.wms.api.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

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
@TableName("t_dev_rec_list")
@ApiModel(value = "DevRecList对象", description = "应收流水")
public class DevRecList implements Serializable {

	private static final long serialVersionUID = 1915102543131210151L;

	@ApiModelProperty(value = "系统编号")
	@TableId(value = "id", type = IdType.INPUT)
	private String id;

	@ApiModelProperty(value = "租户编号")
	@TableField("tenant_id")
	private String tenantId;

	@ApiModelProperty(value = "应收账标识（1：正常；2：被冲正；3：冲正负记录）")
	@TableField("rec_flag")
	private Integer recFlag;

	@ApiModelProperty(value = "应收来源（1：抄表；2：换表）")
	@TableField("rec_type")
	private Integer recType;

	@ApiModelProperty(value = "部门编号")
	@TableField("dept_id")
	private Long deptId;

	@ApiModelProperty(value = "表册编号")
	@TableField("booklet_id")
	private String bookletId;

	@ApiModelProperty(value = "用户编号")
	@TableField("cust_id")
	private Long custId;

	@ApiModelProperty(value = "用户名称")
	@TableField("cust_name")
	private String custName;

	@ApiModelProperty(value = "用户地址")
	@TableField("cust_address")
	private String custAddress;

	@ApiModelProperty(value = "表具系统编号")
	@TableField("dev_id")
	private String devId;

	@ApiModelProperty(value = "表具地址")
	@TableField("dev_address")
	private String devAddress;

	@ApiModelProperty(value = "应收月份")
	@TableField("read_month")
	private Date readMonth;

	@ApiModelProperty(value = "应收账务统计月份")
	@TableField("rec_month")
	private Date recMonth;

	@ApiModelProperty(value = "业务（抄表、换表）流水号")
	@TableField("business_id")
	private Long businessId;

	@ApiModelProperty(value = "抄表员编号")
	@TableField("reader")
	private Long reader;

	@ApiModelProperty(value = "计费时间")
	@TableField("calc_date")
	private Date calcDate;

	@ApiModelProperty(value = "上次计费日期")
	@TableField("last_date")
	private Date lastDate;

	@ApiModelProperty(value = "起数")
	@TableField("last_code")
	private BigDecimal lastCode;

	@ApiModelProperty(value = "当前计费日期")
	@TableField("curr_date")
	private Date currDate;

	@ApiModelProperty(value = "止数")
	@TableField("curr_code")
	private BigDecimal currCode;

	@ApiModelProperty(value = "用量占比")
	@TableField("water_scale")
	private BigDecimal waterScale;

	@ApiModelProperty(value = "计费水量")
	@TableField("use_num")
	private BigDecimal useNum;

	@ApiModelProperty(value = "价格编号")
	@TableField("price_type_id")
	private String priceTypeId;

	@ApiModelProperty(value = "应收金额")
	@TableField("rec_money")
	private BigDecimal recMoney;

	@ApiModelProperty(value = "欠费金额")
	@TableField("due_money")
	private BigDecimal dueMoney;


}
