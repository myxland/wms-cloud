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
@TableName("t_op_tenant_system_price")
@ApiModel(value = "TenantSystemPrice对象", description = "租户模块价格")
public class TenantSystemPrice implements Serializable {

	private static final long serialVersionUID = 1081157101131229149L;

	@ApiModelProperty(value = "系统ID")
	@TableId(value = "id", type = IdType.INPUT)
	@JSONField(serializeUsing = ToStringSerializer.class)
	private Long id;

	@ApiModelProperty(value = "租户编号")
	@TableField("tenant_id")
	@JSONField(serializeUsing = ToStringSerializer.class)
	private Long tenantId;

	@ApiModelProperty(value = "模块编号")
	@TableField("sys_id")
	@JSONField(serializeUsing = ToStringSerializer.class)
	private Long sysId;

	@ApiModelProperty(value = "模块版本（0：基础版；1：高级版；2：旗舰版）")
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
