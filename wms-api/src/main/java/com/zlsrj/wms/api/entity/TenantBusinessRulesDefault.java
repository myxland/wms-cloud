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
@TableName("tenant_business_rules_default")
@ApiModel(value = "TenantBusinessRulesDefault对象", description = "业务规则")
public class TenantBusinessRulesDefault implements Serializable {

	private static final long serialVersionUID = 5961235831275870711L;

	@ApiModelProperty(value = "（1：使用单位；2：水表厂商；3：代收机构；4：内部运营；5：分销商）")
	@TableField("tenant_type")
	private Integer tenantType;

	@ApiModelProperty(value = "业务规则类型")
	@TableField("rules_type")
	private String rulesType;

	@ApiModelProperty(value = "结构化数据")
	@TableField("rules_data")
	private String rulesData;

	@ApiModelProperty(value = "数据新增时间")
	@TableField("add_time")
	private Date addTime;

	@ApiModelProperty(value = "数据修改时间")
	@TableField("update_time")
	private Date updateTime;

}