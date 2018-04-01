/**
 * 
 */
package com.gcit.library.controllers;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.gcit.library.model.Branch;

/**
 * @author gcit
 *
 */

@RestController
public class LibrarianController {
	
	@Value("${librarian.url}")
	private String url;
	
//	private String url = "http://"+liburl;
	
	@Autowired
	RestTemplate restTemplate;
	
	@RequestMapping(value="/branches", method=RequestMethod.GET)
	public ResponseEntity<Object> getBranchs(@RequestHeader(value="Accept") String ct,@RequestParam(value="pageNo",required=false) Integer pageNo,
			@RequestParam(value="search",required=false) String search){
		UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url+"/branches")
											.queryParam("pageNo", pageNo)
											.queryParam("search", search);
		HttpHeaders headers = new HttpHeaders();
		headers.set("Accept", ct);
		HttpEntity<?> entity = new HttpEntity<>(headers);
		ResponseEntity<Object> res = restTemplate.exchange(builder.build().encode().toUri(), HttpMethod.GET,entity,Object.class);
		return res;
	}
	
	@RequestMapping(value="/branches/{branchId}", method=RequestMethod.GET)
	public ResponseEntity<Object> getBranchByPK(@RequestHeader(value="Accept") String ct, @PathVariable(value="branchId") Integer branchId){
		UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url+"/branches/"+branchId);
		HttpHeaders headers = new HttpHeaders();
		headers.set("Accept", ct);
		HttpEntity<?> entity = new HttpEntity<>(headers);
		ResponseEntity<Object> res = restTemplate.exchange(builder.build().toUri(), HttpMethod.GET,entity,Object.class);
		return res;
	}
	
	
//	@RequestMapping(value="/branches/count",method=RequestMethod.GET)
//	public ResponseEntity<Object> getBranchCount(@RequestParam(value="search",required=false)  String search)  {
//		UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url+"/branches/count/")
//				.queryParam("search", search);
//		HttpHeaders headers = new HttpHeaders();
//		headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);
//		HttpEntity<?> entity = new HttpEntity<>(headers);
//		ResponseEntity<Object> res = restTemplate.exchange(builder.build().toUri(), HttpMethod.GET,entity,Object.class);
//		return res;
//	}
	
	@RequestMapping(value="/branches/{branchId}",method=RequestMethod.PUT)
	public ResponseEntity<Object> updateBranch(@RequestHeader(value="Accept") String ct, @RequestBody Branch branch, @PathVariable(value="branchId") Integer branchId)  {
		UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url+"/branches/"+branchId);
		HttpHeaders headers = new HttpHeaders();
		headers.set("Accept", ct);
		HttpEntity<?> entity = new HttpEntity<>(branch,headers);
		ResponseEntity<Object> res = restTemplate.exchange(builder.build().toUri(), HttpMethod.PUT,entity,Object.class);
		return res;
	}
	
//	@RequestMapping(value="/branches/{branchId}", method=RequestMethod.DELETE,produces= {"application/json"})
//	public ResponseEntity<Object> deleteBranchByPK(@PathVariable("branchId") Integer branchId)  {
//		UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url+"/branches/"+branchId);
//		HttpHeaders headers = new HttpHeaders();
//		headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);
//		HttpEntity<?> entity = new HttpEntity<>(headers);
//		ResponseEntity<Object> res = restTemplate.exchange(builder.build().toUri(), HttpMethod.DELETE,entity,Object.class);
//		return res;
//	}
//	
//	@RequestMapping(value="/branches", method=RequestMethod.POST,consumes= {"application/json"},produces= {"application/json"})
//	public ResponseEntity<Object> addBranch(@RequestBody Branch branch)  {
//		UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url+"/branches");
//		HttpHeaders headers = new HttpHeaders();
//		headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);
//		HttpEntity<?> entity = new HttpEntity<>(branch,headers);
//		ResponseEntity<Object> res = restTemplate.exchange(builder.build().toUri(), HttpMethod.POST,entity,Object.class);
//		return res;
//	}
}
