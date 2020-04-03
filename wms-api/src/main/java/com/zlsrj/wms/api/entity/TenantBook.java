package com.zlsrj.wms.api.entity;

import java.io.Serializable;
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
@TableName("tenant_book")
@ApiModel(value = "TenantBook对象", description = "表册信息")
public class TenantBook implements Serializable {

	private static final long serialVersionUID = 1012535341314081110L;

	@ApiModelProperty(value = "表册ID")
	@TableId(value = "id", type = IdType.INPUT)
	private String id;

	@ApiModelProperty(value = "租户ID")
	@TableField("tenant_id")
	private String tenantId;

	@ApiModelProperty(value = "表册编号")
	@TableField("book_code")
	private String bookCode;

	@ApiModelProperty(value = "表册名称")
	@TableField("book_name")
	private String bookName;

	@ApiModelProperty(value = "抄表员")
	@TableField("book_reader_employee_id")
	private String bookReaderEmployeeId;

	@ApiModelProperty(value = "收费员")
	@TableField("book_charge_employee_id")
	private String bookChargeEmployeeId;

	@ApiModelProperty(value = "营销区域")
	@TableField("book_marketing_area_id")
	private String bookMarketingAreaId;

	@ApiModelProperty(value = "抄表周期")
	@TableField("book_read_cycle")
	private Integer bookReadCycle;

	@ApiModelProperty(value = "最后一次抄表月份")
	@TableField("book_last_month")
	private String bookLastMonth;

	@ApiModelProperty(value = "下次抄表月份")
	@TableField("book_read_month")
	private String bookReadMonth;

	@ApiModelProperty(value = "结算周期")
	@TableField("book_settle_cycle")
	private Integer bookSettleCycle;

	@ApiModelProperty(value = "最后一次结算月份")
	@TableField("book_settle_last_month")
	private String bookSettleLastMonth;

	@ApiModelProperty(value = "下次结算月份")
	@TableField("book_settle_month")
	private String bookSettleMonth;

	@ApiModelProperty(value = "有效状态（1：可用；0：禁用）")
	@TableField("book_status")
	private Integer bookStatus;

	@ApiModelProperty(value = "表册状态（1：抄表进行中；2：抄表截止）")
	@TableField("book_read_status")
	private Integer bookReadStatus;

	@ApiModelProperty(value = "备注")
	@TableField("book_memo")
	private String bookMemo;

	@ApiModelProperty(value = "数据新增时间")
	@TableField("add_time")
	private Date addTime;

	@ApiModelProperty(value = "数据修改时间")
	@TableField("update_time")
	private Date updateTime;

}