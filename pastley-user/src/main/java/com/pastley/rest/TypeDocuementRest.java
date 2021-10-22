package com.pastley.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pastley.service.TypeDocumentService;

@RestController
@RequestMapping("type_document")
public class TypeDocuementRest {
	@Autowired TypeDocumentService typeDocumentService;
	

}
