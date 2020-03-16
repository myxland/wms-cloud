package com.zlsrj.wms.generator.entity;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PropertyOption implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2657101800568747857L;
	private String value;
	private String text;
}
