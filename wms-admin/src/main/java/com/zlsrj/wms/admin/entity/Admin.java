package com.zlsrj.wms.admin.entity;

import java.io.Serializable;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Admin implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1864692730887405248L;

	private Long id;

	private String username;

	private String password;

	private Integer status;

}
