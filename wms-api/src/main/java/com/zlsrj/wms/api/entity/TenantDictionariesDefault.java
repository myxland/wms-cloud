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
@TableName("tenant_dictionaries_default")
@ApiModel(value = "TenantDictionariesDefault对象", description = "基础字典表")
public class TenantDictionariesDefault implements Serializable {

	private static final long serialVersionUID = 1115541121054113140L;

	@ApiModelProperty(value = "租户类型（1：使用单位；2：水表厂商；3：代收机构；4：内部运营；5：分销商）")
	@TableField("tenant_type")
	private String tenantType;

	@ApiModelProperty(value = "字典编码")
	@TableField("dict_code")
	private Integer dictCode;

	@ApiModelProperty(value = "字典类型")
	@TableField("dict_type")
	private String dictType;

	@ApiModelProperty(value = "名称")
	@TableField("dict_name")
	private String dictName;

	@ApiModelProperty(value = "结构化数据")
	@TableField("dict_data")
	private String dictData;

	@ApiModelProperty(value = "数据新增时间")
	@TableField("add_time")
	private Date addTime;

	@ApiModelProperty(value = "数据修改时间")
	@TableField("update_time")
	private Date updateTime;

}