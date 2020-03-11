package com.zlsrj.wms.api.entity;

import java.io.Serializable;
import java.math.BigDecimal;

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
@TableName("t_op_system_price")
@ApiModel(value = "SystemPrice对象", description = "模块各版本价格定义表")
public class SystemPrice implements Serializable {

	private static final long serialVersionUID = 2413136410111475711L;

	@ApiModelProperty(value = "系统ID")
	@TableId(value = "id", type = IdType.INPUT)
	private String id;

	@ApiModelProperty(value = "模块编号")
	@TableField("sys_id")
	private Long sysId;

	@ApiModelProperty(value = "模块版本（1：基础版；2：高级版；3：旗舰版）")
	@TableField("sys_edition")
	private Integer sysEdition;

	@ApiModelProperty(value = "起始量")
	@TableField("start_num")
	private Integer startNum;

	@ApiModelProperty(value = "终止量")
	@TableField("end_num")
	private Integer endNum;

	@ApiModelProperty(value = "价格")
	@TableField("price")
	private BigDecimal price;


}
