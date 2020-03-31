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
@TableName("tenant_customer_archives")
@ApiModel(value = "TenantCustomerArchives对象", description = "用户档案")
public class TenantCustomerArchives implements Serializable {

	private static final long serialVersionUID = 5315014870415151111L;

	@ApiModelProperty(value = "档案ID")
	@TableId(value = "id", type = IdType.INPUT)
	private String id;

	@ApiModelProperty(value = "租户ID")
	@TableField("tenant_id")
	private String tenantId;

	@ApiModelProperty(value = "用户ID")
	@TableField("customer_id")
	private String customerId;

	@ApiModelProperty(value = "用户号")
	@TableField("customer_code")
	private String customerCode;

	@ApiModelProperty(value = "档案名称")
	@TableField("archives_name")
	private String archivesName;

	@ApiModelProperty(value = "创建时间")
	@TableField("archives_create_time")
	private Date archivesCreateTime;

	@ApiModelProperty(value = "创建日期")
	@TableField("archives_create_date")
	private Date archivesCreateDate;

	@ApiModelProperty(value = "存储文件名称JSON串")
	@TableField("archives_filename")
	private String archivesFilename;

	@ApiModelProperty(value = "证件信息JSON串，例如身份证号、地址等")
	@TableField("archives_information")
	private String archivesInformation;

	@ApiModelProperty(value = "证件号码，例如身份证号等")
	@TableField("archives_code")
	private String archivesCode;

	@ApiModelProperty(value = "数据新增时间")
	@TableField("add_time")
	private Date addTime;

	@ApiModelProperty(value = "数据修改时间")
	@TableField("update_time")
	private Date updateTime;

}