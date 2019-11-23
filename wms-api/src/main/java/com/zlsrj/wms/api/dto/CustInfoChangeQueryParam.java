package com.zlsrj.wms.api.dto;

import java.io.Serializable;
import java.util.Date;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel(value = "CustInfoChange查询参数", description = "用户变更")
public class CustInfoChangeQueryParam implements Serializable {

	private static final long serialVersionUID = 8136551242118156661L;

	@ApiModelProperty(value = "系统编号")
	private Long id;

	@ApiModelProperty(value = "租户编号")
	private Long tenantId;

	@ApiModelProperty(value = "用户编号")
	private String custId;

	@ApiModelProperty(value = "变更日期")
	private Date changeDate;

	@ApiModelProperty(value = "变更日期开始")
	private Date changeDateStart;

	@ApiModelProperty(value = "变更日期结束")
	private Date changeDateEnd;

	@ApiModelProperty(value = "变更人")
	private Long changer;

	@ApiModelProperty(value = "用户信息变更（1：是；0：否）")
	private Integer baseChange;

	@ApiModelProperty(value = "开票信息变更（1：是；0：否）")
	private Integer billChange;

	@ApiModelProperty(value = "用户状态变更（1：是；0：否）")
	private Integer statusChange;

	@ApiModelProperty(value = "用户名称")
	private String custName;

	@ApiModelProperty(value = "变更前用户名称")
	private String custNameHis;

	@ApiModelProperty(value = "用户地址")
	private String custAddress;

	@ApiModelProperty(value = "变更前用户地址")
	private String custAddressHis;

	@ApiModelProperty(value = "用户类别编号")
	private Long custTypeId;

	@ApiModelProperty(value = "变更前用户类别编号")
	private Long custTypeIdHis;

	@ApiModelProperty(value = "立户日期")
	private Date custRegistDate;

	@ApiModelProperty(value = "立户日期开始")
	private Date custRegistDateStart;

	@ApiModelProperty(value = "立户日期结束")
	private Date custRegistDateEnd;

	@ApiModelProperty(value = "变更前立户日期")
	private Date custRegistDateHis;

	@ApiModelProperty(value = "变更前立户日期开始")
	private Date custRegistDateHisStart;

	@ApiModelProperty(value = "变更前立户日期结束")
	private Date custRegistDateHisEnd;

	@ApiModelProperty(value = "用户状态（1：正常；2：暂停；3：消户）")
	private Integer custStatus;

	@ApiModelProperty(value = "变更前用户状态（1：正常；2：暂停；3：消户）")
	private Integer custStatusHis;

	@ApiModelProperty(value = "收费方式（1：坐收；2：走收；3：代扣；4：托收）")
	private Integer payType;

	@ApiModelProperty(value = "变更前收费方式（1：坐收；2：走收；3：代扣；4：托收）")
	private Integer payTypeHis;

	@ApiModelProperty(value = "开票类别（1：普通纸制发票；2：普通电子发票；3：专用纸制发票）")
	private Integer billType;

	@ApiModelProperty(value = "变更前开票类别（1：普通纸制发票；2：普通电子发票；3：专用纸制发票）")
	private Integer billTypeHis;

	@ApiModelProperty(value = "开票名称")
	private String billName;

	@ApiModelProperty(value = "变更前开票名称")
	private String billNameHis;

	@ApiModelProperty(value = "税号")
	private String billTaxnum;

	@ApiModelProperty(value = "变更前税号")
	private String billTaxnumHis;

	@ApiModelProperty(value = "开票地址")
	private String billAddress;

	@ApiModelProperty(value = "变更前开票地址")
	private String billAddressHis;

	@ApiModelProperty(value = "开票电话")
	private String billTel;

	@ApiModelProperty(value = "变更前开票电话")
	private String billTelHis;

	@ApiModelProperty(value = "银行名称")
	private String billBank;

	@ApiModelProperty(value = "变更前银行名称")
	private String billBankHis;

	@ApiModelProperty(value = "开户行行号")
	private String billBankId;

	@ApiModelProperty(value = "变更前开户行行号")
	private String billBankIdHis;

	@ApiModelProperty(value = "开户行名称")
	private String billBankName;

	@ApiModelProperty(value = "变更前开户行名称")
	private String billBankNameHis;

	@ApiModelProperty(value = "开户行账号")
	private String billBankAccount;

	@ApiModelProperty(value = "变更前开户行账号")
	private String billBankAccountHis;

}

