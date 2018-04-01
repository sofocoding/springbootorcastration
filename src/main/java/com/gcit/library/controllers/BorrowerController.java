/**
 * 
 */
package com.gcit.library.controllers;

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

import com.gcit.library.model.Loan;

/**
 * @author gcit
 *
 */
@RestController
public class BorrowerController {
	
	@Value("${borrower.url}")
	private String url;
	
//	private String url = "http://"+borrowerurl;
	
	@Autowired
	RestTemplate restTemplate;
	
	@RequestMapping(value="/validateCardNo/{cardNo}", method=RequestMethod.GET)
	public ResponseEntity<Object> validateCardNo(@RequestHeader(value="Accept") String ct,@PathVariable(value="cardNo") Integer cardNo) {
		UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url+"/validateCardNo/"+cardNo);
		HttpHeaders headers = new HttpHeaders();
		headers.set("Accept", ct);
		HttpEntity<?> entity = new HttpEntity<>(headers);
		ResponseEntity<Object> res = restTemplate.exchange(builder.build().encode().toUri(), HttpMethod.GET,entity,Object.class);
		return res;
	}
	
//	@RequestMapping(value="/borrowers", method=RequestMethod.GET)
//	public ResponseEntity<Object> getBorrowers(@RequestParam(value="pageNo",required=false) Integer pageNo,
//			@RequestParam(value="search",required=false) String search){
//		UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url+"/borrowers")
//											.queryParam("pageNo", pageNo)
//											.queryParam("search", search);
//		HttpHeaders headers = new HttpHeaders();
//		HttpEntity<?> entity = new HttpEntity<>(headers);
//		ResponseEntity<Object> res = restTemplate.exchange(builder.build().encode().toUri(), HttpMethod.GET,entity,Object.class);
//		return res;
//	}
	
	@RequestMapping(value="/borrowers/{cardNo}", method=RequestMethod.GET)
	public ResponseEntity<Object> getBorrowerHistory(@RequestHeader(value="Accept") String ct,@PathVariable(value="cardNo") Integer cardNo){
		UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url+"/borrowers/"+cardNo);
		HttpHeaders headers = new HttpHeaders();
		headers.set("Accept", ct);
		HttpEntity<?> entity = new HttpEntity<>(headers);
		ResponseEntity<Object> res = restTemplate.exchange(builder.build().toUri(), HttpMethod.GET,entity,Object.class);
		return res;
	}
	
	
//	@RequestMapping(value="/borrowers/count",method=RequestMethod.GET)
//	public ResponseEntity<Object> getBorrowerCount(@RequestParam(value="search",required=false)  String search)  {
//		UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url+"/borrowers/count/")
//				.queryParam("search", search);
//		HttpHeaders headers = new HttpHeaders();
//		headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);
//		HttpEntity<?> entity = new HttpEntity<>(headers);
//		ResponseEntity<Object> res = restTemplate.exchange(builder.build().toUri(), HttpMethod.GET,entity,Object.class);
//		return res;
//	}
	
	@RequestMapping(value="/borrowers/{cardNo}",method=RequestMethod.PUT, consumes = {"application/json"},produces= {"application/json"})
	public ResponseEntity<Object> returnBook(@RequestHeader(value="Accept") String ct,@RequestBody Loan loan, @PathVariable(value="cardNo") Integer cardNo)  {
		UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url+"/borrowers/"+cardNo);
		HttpHeaders headers = new HttpHeaders();
		headers.set("Accept", ct);
		HttpEntity<?> entity = new HttpEntity<>(loan,headers);
		ResponseEntity<Object> res = restTemplate.exchange(builder.build().toUri(), HttpMethod.PUT,entity,Object.class);
		return res;
	}
	
//	@RequestMapping(value="/borrowers/{cardNo}", method=RequestMethod.DELETE,produces= {"application/json"})
//	public ResponseEntity<Object> deleteBorrowerByPK(@PathVariable("cardNo") Integer cardNo)  {
//		UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url+"/borrowers/"+cardNo);
//		HttpHeaders headers = new HttpHeaders();
//		headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);
//		HttpEntity<?> entity = new HttpEntity<>(headers);
//		ResponseEntity<Object> res = restTemplate.exchange(builder.build().toUri(), HttpMethod.DELETE,entity,Object.class);
//		return res;
//	}
	
	@RequestMapping(value="/borrowers/{cardNo}", method=RequestMethod.POST,consumes= {"application/json"},produces= {"application/json"})
	public ResponseEntity<Object> checkOutBook(@RequestHeader(value="Accept") String ct,@RequestBody Loan loan,@PathVariable(value="cardNo") Integer cardNo)  {
		UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url+"/borrowers/"+cardNo);
		HttpHeaders headers = new HttpHeaders();
		headers.set("Accept", ct);
		HttpEntity<?> entity = new HttpEntity<>(loan,headers);
		ResponseEntity<Object> res = restTemplate.exchange(builder.build().toUri(), HttpMethod.POST,entity,Object.class);
		return res;
	}
}
