package com.rahul.productmgmt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rahul.productmgmt.client.ClientRestService;
import com.rahul.productmgmt.model.response.RestPostById;

@RestController
@RequestMapping(value = "/rest")
public class ClientRestController {

	@Autowired
	ClientRestService productRestService;

	@GetMapping("/fetchposts")
	public String fetchPosts() throws Exception {
		String data = productRestService.getPostsAPI();
		return data;
	}

	@GetMapping("/fetchpostbyid/{id}")
	public RestPostById fetchPosts(@PathVariable("id") String id) throws Exception {
		RestPostById data = productRestService.getPostAPIById(id);
		return data;
	}

}
