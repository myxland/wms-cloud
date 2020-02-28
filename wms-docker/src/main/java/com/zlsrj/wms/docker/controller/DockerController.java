package com.zlsrj.wms.docker.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DockerController {

	@RequestMapping(value = "/index")
	public Object index() {
		return "Hello Docker " + new java.util.Date();
	}

}
