package com.zlsrj.wms.api.entity;

import java.io.Serializable;
import java.math.BigDecimal;

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
@TableName("module_price")
@ApiModel(value = "ModulePrice对象", description = "模块价格")
public class ModulePrice implements Serializable {

	private static final long serialVersionUID = 3111269699421124131L;

	@ApiModelProperty(value = "模块价格ID")
	@TableId(value = "id", type = IdType.INPUT)
	@JSONField(serializeUsing = ToStringSerializer.class)
	private Long id;

	@ApiModelProperty(value = "模块ID")
	@TableField("module_id")
	@JSONField(serializeUsing = ToStringSerializer.class)
	private Long moduleId;

	@ApiModelProperty(value = "模块版本（1：基础版；2：高级版；3：旗舰版）")
	@TableField("module_edition")
	private Integer moduleEdition;

	@ApiModelProperty(value = "起始量")
	@TableField("start_num")
	private Integer startNum;

	@ApiModelProperty(value = "终止量")
	@TableField("end_num")
	private Integer endNum;

	@ApiModelProperty(value = "价格")
	@TableField("price_money")
	private BigDecimal priceMoney;


}
