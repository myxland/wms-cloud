package com.zlsrj.wms.api.entity;

import java.io.Serializable;
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
@TableName("t_cust_info_change")
@ApiModel(value = "CustInfoChange对象", description = "用户变更")
public class CustInfoChange implements Serializable {

	private static final long serialVersionUID = 1314882671271023731L;

	@ApiModelProperty(value = "系统编号")
	@TableId(value = "id", type = IdType.INPUT)
	@JSONField(serializeUsing = ToStringSerializer.class)
	private Long id;

	@ApiModelProperty(value = "租户编号")
	@TableField("tenant_id")
	@JSONField(serializeUsing = ToStringSerializer.class)
	private Long tenantId;

	@ApiModelProperty(value = "用户编号")
	@TableField("cust_id")
	private String custId;

	@ApiModelProperty(value = "变更日期")
	@TableField("change_date")
	private Date changeDate;

	@ApiModelProperty(value = "变更人")
	@TableField("changer")
	@JSONField(serializeUsing = ToStringSerializer.class)
	private Long changer;

	@ApiModelProperty(value = "用户信息变更（1：是；0：否）")
	@TableField("base_change")
	private Integer baseChange;

	@ApiModelProperty(value = "开票信息变更（1：是；0：否）")
	@TableField("bill_change")
	private Integer billChange;

	@ApiModelProperty(value = "用户状态变更（1：是；0：否）")
	@TableField("status_change")
	private Integer statusChange;

	@ApiModelProperty(value = "用户名称")
	@TableField("cust_name")
	private String custName;

	@ApiModelProperty(value = "变更前用户名称")
	@TableField("cust_name_his")
	private String custNameHis;

	@ApiModelProperty(value = "用户地址")
	@TableField("cust_address")
	private String custAddress;

	@ApiModelProperty(value = "变更前用户地址")
	@TableField("cust_address_his")
	private String custAddressHis;

	@ApiModelProperty(value = "用户类别编号")
	@TableField("cust_type_id")
	@JSONField(serializeUsing = ToStringSerializer.class)
	private Long custTypeId;

	@ApiModelProperty(value = "变更前用户类别编号")
	@TableField("cust_type_id_his")
	@JSONField(serializeUsing = ToStringSerializer.class)
	private Long custTypeIdHis;

	@ApiModelProperty(value = "立户日期")
	@TableField("cust_regist_date")
	private Date custRegistDate;

	@ApiModelProperty(value = "变更前立户日期")
	@TableField("cust_regist_date_his")
	private Date custRegistDateHis;

	@ApiModelProperty(value = "用户状态（1：正常；2：暂停；3：消户）")
	@TableField("cust_status")
	private Integer custStatus;

	@ApiModelProperty(value = "变更前用户状态（1：正常；2：暂停；3：消户）")
	@TableField("cust_status_his")
	private Integer custStatusHis;

	@ApiModelProperty(value = "收费方式（1：坐收；2：走收；3：代扣；4：托收）")
	@TableField("pay_type")
	private Integer payType;

	@ApiModelProperty(value = "变更前收费方式（1：坐收；2：走收；3：代扣；4：托收）")
	@TableField("pay_type_his")
	private Integer payTypeHis;

	@ApiModelProperty(value = "开票类别（1：普通纸制发票；2：普通电子发票；3：专用纸制发票）")
	@TableField("bill_type")
	private Integer billType;

	@ApiModelProperty(value = "变更前开票类别（1：普通纸制发票；2：普通电子发票；3：专用纸制发票）")
	@TableField("bill_type_his")
	private Integer billTypeHis;

	@ApiModelProperty(value = "开票名称")
	@TableField("bill_name")
	private String billName;

	@ApiModelProperty(value = "变更前开票名称")
	@TableField("bill_name_his")
	private String billNameHis;

	@ApiModelProperty(value = "税号")
	@TableField("bill_taxnum")
	private String billTaxnum;

	@ApiModelProperty(value = "变更前税号")
	@TableField("bill_taxnum_his")
	private String billTaxnumHis;

	@ApiModelProperty(value = "开票地址")
	@TableField("bill_address")
	private String billAddress;

	@ApiModelProperty(value = "变更前开票地址")
	@TableField("bill_address_his")
	private String billAddressHis;

	@ApiModelProperty(value = "开票电话")
	@TableField("bill_tel")
	private String billTel;

	@ApiModelProperty(value = "变更前开票电话")
	@TableField("bill_tel_his")
	private String billTelHis;

	@ApiModelProperty(value = "银行名称")
	@TableField("bill_bank")
	private String billBank;

	@ApiModelProperty(value = "变更前银行名称")
	@TableField("bill_bank_his")
	private String billBankHis;

	@ApiModelProperty(value = "开户行行号")
	@TableField("bill_bank_id")
	private String billBankId;

	@ApiModelProperty(value = "变更前开户行行号")
	@TableField("bill_bank_id_his")
	private String billBankIdHis;

	@ApiModelProperty(value = "开户行名称")
	@TableField("bill_bank_name")
	private String billBankName;

	@ApiModelProperty(value = "变更前开户行名称")
	@TableField("bill_bank_name_his")
	private String billBankNameHis;

	@ApiModelProperty(value = "开户行账号")
	@TableField("bill_bank_account")
	private String billBankAccount;

	@ApiModelProperty(value = "变更前开户行账号")
	@TableField("bill_bank_account_his")
	private String billBankAccountHis;


}
