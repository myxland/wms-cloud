package com.zlsrj.wms.api.vo;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel(value = "TenantBook抄表员对象", description = "表册信息")
public class TenantBookReaderVo implements Serializable {

	private static final long serialVersionUID = -3141768199647978135L;

	@ApiModelProperty(value = "抄表员")
	private String bookReaderEmployeeId;
	
	@ApiModelProperty(value = "抄表员")
	private String bookReaderEmployeeName;

	@ApiModelProperty(value = "营销区域")
	private String bookMarketingAreaId;
	
	@ApiModelProperty(value = "营销区域")
	private String bookMarketingAreaName;

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((bookMarketingAreaId == null) ? 0 : bookMarketingAreaId.hashCode());
		result = prime * result + ((bookReaderEmployeeId == null) ? 0 : bookReaderEmployeeId.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TenantBookReaderVo other = (TenantBookReaderVo) obj;
		if (bookMarketingAreaId == null) {
			if (other.bookMarketingAreaId != null)
				return false;
		} else if (!bookMarketingAreaId.equals(other.bookMarketingAreaId))
			return false;
		if (bookReaderEmployeeId == null) {
			if (other.bookReaderEmployeeId != null)
				return false;
		} else if (!bookReaderEmployeeId.equals(other.bookReaderEmployeeId))
			return false;
		return true;
	}

}
