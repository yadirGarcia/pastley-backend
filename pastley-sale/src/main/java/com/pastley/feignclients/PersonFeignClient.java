package com.pastley.feignclients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.pastley.model.PersonModel;

@FeignClient(name = "pastley-user")
@RequestMapping("/person")
public interface PersonFeignClient {

	@GetMapping(value = { "/findByDocument/{document}" })
	public PersonModel findByDocument(@PathVariable("document") Long document);
}
