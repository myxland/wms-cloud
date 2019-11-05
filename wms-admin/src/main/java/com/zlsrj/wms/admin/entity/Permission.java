package com.zlsrj.wms.admin.entity;

import java.io.Serializable;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Permission implements Serializable {

	private static final long serialVersionUID = 8437060884934654368L;

	private Long id;

	private String name;

	private String value;

	private String uri;

	private Integer status;
}
