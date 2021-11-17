package com.pastley.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pastley.model.service.ProductService;

@RestController
@RequestMapping("/product")
public class ProductRest {

	@Autowired
	private ProductService productService;

}


