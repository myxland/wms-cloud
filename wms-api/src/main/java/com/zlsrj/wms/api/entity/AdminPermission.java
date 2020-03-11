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
public class AdminPermission implements Serializable {

	private static final long serialVersionUID = 8437060884934654368L;

	private String id;

	private String name;

	private String value;

	private String uri;

	private Integer status;
}
