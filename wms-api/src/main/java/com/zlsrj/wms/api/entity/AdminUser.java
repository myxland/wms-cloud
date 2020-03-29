package com.zlsrj.wms.api.entity;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AdminUser implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1864692730887405248L;

	private String id;

	private String username;

	private String password;

	private Integer status;
	
	private String icon;
	
	/**
	 * 租户ID
	 */
	private String tenantId;

}
