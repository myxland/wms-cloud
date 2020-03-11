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
@TableName("tenant_module")
@ApiModel(value = "TenantModule对象", description = "租户开通模块清单")
public class TenantModule implements Serializable {

	private static final long serialVersionUID = 3610815111712451512L;

	@ApiModelProperty(value = "租户模块ID")
	@TableField("id")
	private String id;

	@ApiModelProperty(value = "租户ID")
	@TableField("tenant_id")
	private String tenantId;

	@ApiModelProperty(value = "模块ID")
	@TableField("module_id")
	private String moduleId;

	@ApiModelProperty(value = "开通版本（1：基础版；2：高级版；3：旗舰版）")
	@TableField("module_edition")
	private Integer moduleEdition;

	@ApiModelProperty(value = "开通时间")
	@TableField("module_open_time")
	private Date moduleOpenTime;


}
