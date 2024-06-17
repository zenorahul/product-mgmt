package com.rahul.productmgmt.client;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.rahul.productmgmt.model.response.RestPostById;

@Service
public class ClientRestService {

	RestTemplate restTemplate = new RestTemplate();
	String postsURL = "https://jsonplaceholder.typicode.com/posts/";

	public String getPostsAPI() {

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);

		try {
			HttpEntity<Object> requestEntity = new HttpEntity<>(null, headers);
			ResponseEntity<String> result = restTemplate.exchange(postsURL, HttpMethod.GET, requestEntity,
					String.class);

			HttpStatus statusCode = (HttpStatus) result.getStatusCode();
			System.out.println(statusCode);

			HttpHeaders responseHeaders = result.getHeaders();
			System.out.println(responseHeaders);

			return result.getBody();
		} catch (Exception e) {
			return "Error occurred: " + e.getMessage();
		}
	}

	public RestPostById getPostAPIById(String id) {

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);

		String url = postsURL.concat(id);
		HttpEntity<Object> requestEntity = new HttpEntity<>(null, headers);
		ResponseEntity<RestPostById> result = restTemplate.exchange(url, HttpMethod.GET, requestEntity,
				RestPostById.class);

		return result.getBody();
	}

}
