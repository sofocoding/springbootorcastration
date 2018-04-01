/**
 * 
 */
package com.gcit.library.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * @author gcit
 *
 */

@Configuration
public class Template {
	
	@Bean
	public RestTemplate restTemplate(RestTemplateBuilder builder) {
	   return builder.build();
	}
	
	@Bean
	public List<MediaType> mediaType(){
		List<MediaType> list = new ArrayList<MediaType>();
		list.add(MediaType.APPLICATION_JSON);
		list.add(MediaType.APPLICATION_XML);
		return list;
	}
}
//@Service
//public class Template {
//
////	private RestTemplate restTemplate;
//
//	public RestTemplate MyBean(RestTemplateBuilder restTemplateBuilder) {
//		return restTemplateBuilder.build();
//	}
//
//}
