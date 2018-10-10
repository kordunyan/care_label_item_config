package com.itemconfiguration.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class IndexPageController {

	@GetMapping("/app/**")
	public String getIndexPage() {
		return "index";
	}
}
