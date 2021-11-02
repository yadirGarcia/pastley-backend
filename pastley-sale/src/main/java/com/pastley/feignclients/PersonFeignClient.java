package com.pastley.feignclients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.pastley.model.PersonModel;

@FeignClient(name = "pastley-user", url = "http://localhost:8080")
@RequestMapping("/person")
public interface PersonFeignClient {

	@GetMapping
	public PersonModel findByDocument(Long document);
}
