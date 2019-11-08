package com.zlsrj.wms.api.vo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.serializer.ToStringSerializer;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TenantInfoVo implements Serializable {

	private static final long serialVersionUID = 240221946239149514L;

	@JSONField(serializeUsing = ToStringSerializer.class)
	private Long id;

	private String tenantName;

	private String displayName;

	private String tenantProvince;

	private String tenantCity;

	private String tenantArea;

	private String tenantAddress;

	private String tenantLinkman;

	private String tenantMobile;

	private String tenantTel;

	private String tenantEmail;

	private String tenantQq;

	private Integer tenantType;

	private Integer tenantStatus;

	private Date regTime;

	private Date endDate;

	private String creditNumber;

	private String invoiceAddress;

	private String bankNo;

	private String bankName;

	private String accountNo;

	private Integer partChargeOn;

	private Integer overDuefineOn;

	private Integer overDuefineDay;

	private BigDecimal overDuefineRatio;

	private BigDecimal overDuefineTopRatio;

	private Integer ycdkType;

}
