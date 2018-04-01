/**
 * 
 */
package com.gcit.library.controllers;

import java.util.List;

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

import com.gcit.library.model.Author;
import com.gcit.library.model.Book;

/**
 * @author gcit
 *
 */

@RestController
public class AdminController{
	
	@Autowired
	RestTemplate restTemplate;
	
	@Value("${admin.url}")
	private String url;
	
//	private String url = "http://"+adminUrl;
	
	@Autowired
	List<MediaType> mediaType;
	/*
	 * Admin Book Services
	 */
	
	@RequestMapping(value="/books", method=RequestMethod.GET)
	public ResponseEntity<Object> getBooks(@RequestHeader(value="Accept") String ct,@RequestParam(value="pageNo",required=false) Integer pageNo,
			@RequestParam(value="search",required=false) String search){
		UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url+"/books")
											.queryParam("pageNo", pageNo)
											.queryParam("search", search);
		HttpHeaders headers = new HttpHeaders();
		headers.set("Accept", ct);
		HttpEntity<?> entity = new HttpEntity<>(headers);
		ResponseEntity<Object> res = restTemplate.exchange(builder.build().encode().toUri(), HttpMethod.GET,entity,Object.class);
		return res;
	}
	
	@RequestMapping(value="/books/{bookId}", method=RequestMethod.GET)
	public ResponseEntity<Object> getBookByPK(@RequestHeader(value="Accept") String ct,@PathVariable(value="bookId") Integer bookId){
		UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url+"/books/"+bookId);
		HttpHeaders headers = new HttpHeaders();
		headers.set("Accept", ct);
		HttpEntity<?> entity = new HttpEntity<>(headers);
		ResponseEntity<Object> res = restTemplate.exchange(builder.build().toUri(), HttpMethod.GET,entity,Object.class);
		return res;
	}
	
	
	@RequestMapping(value="/books/count",method=RequestMethod.GET)
	public ResponseEntity<Object> getBookCount(@RequestHeader(value="Accept") String ct,@RequestParam(value="search",required=false)  String search)  {
		UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url+"/books/count/")
				.queryParam("search", search);
		HttpHeaders headers = new HttpHeaders();
		headers.set("Accept", ct);
		HttpEntity<?> entity = new HttpEntity<>(headers);
		ResponseEntity<Object> res = restTemplate.exchange(builder.build().toUri(), HttpMethod.GET,entity,Object.class);
		return res;
	}
	
	@RequestMapping(value="/books/{bookId}",method=RequestMethod.PUT)
	public ResponseEntity<Object> updateBook(@RequestHeader(value="Accept") String ct,@RequestBody Book book, @PathVariable(value="bookId") Integer bookId)  {
		UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url+"/books/"+bookId);
		HttpHeaders headers = new HttpHeaders();
		headers.set("Accept", ct);
		HttpEntity<?> entity = new HttpEntity<>(book,headers);
		ResponseEntity<Object> res = restTemplate.exchange(builder.build().toUri(), HttpMethod.PUT,entity,Object.class);
		return res;
	}
	
	@RequestMapping(value="/books/{bookId}", method=RequestMethod.DELETE)
	public ResponseEntity<Object> deleteBookByPK(@RequestHeader(value="Accept") String ct,@PathVariable("bookId") Integer bookId)  {
		UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url+"/books/"+bookId);
		HttpHeaders headers = new HttpHeaders();
		headers.set("Accept", ct);
		HttpEntity<?> entity = new HttpEntity<>(headers);
		ResponseEntity<Object> res = restTemplate.exchange(builder.build().toUri(), HttpMethod.DELETE,entity,Object.class);
		return res;
	}
	
	@RequestMapping(value="/books", method=RequestMethod.POST)
	public ResponseEntity<Object> addBook(@RequestHeader(value="Accept") String ct, @RequestBody Book book)  {
		UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url+"/books");
		HttpHeaders headers = new HttpHeaders();
		headers.set("Accept", ct);
		HttpEntity<?> entity = new HttpEntity<>(book,headers);
		ResponseEntity<Object> res = restTemplate.exchange(builder.build().toUri(), HttpMethod.POST,entity,Object.class);
		return res;
	}
	
	/*
	 * Admin Author
	 */
	@RequestMapping(value="/authors", method=RequestMethod.GET)
	public ResponseEntity<Object> getAuthors(@RequestHeader(value="Accept") String ct,@RequestParam(value="pageNo",required=false) Integer pageNo,
			@RequestParam(value="search",required=false) String search){
		UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url+"/authors")
											.queryParam("pageNo", pageNo)
											.queryParam("search", search);
		HttpHeaders headers = new HttpHeaders();
		headers.set("Accept", ct);
		HttpEntity<?> entity = new HttpEntity<>(headers);
		ResponseEntity<Object> res = restTemplate.exchange(builder.build().encode().toUri(), HttpMethod.GET,entity,Object.class);
		return res;
	}
	
	@RequestMapping(value="/authors/{authorId}", method=RequestMethod.GET)
	public ResponseEntity<Object> getAuthorByPK(@RequestHeader(value="Accept") String ct,@PathVariable(value="authorId") Integer authorId){
		UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url+"/authors/"+authorId);
		HttpHeaders headers = new HttpHeaders();
		headers.set("Accept", ct);
		HttpEntity<?> entity = new HttpEntity<>(headers);
		ResponseEntity<Object> res = restTemplate.exchange(builder.build().toUri(), HttpMethod.GET,entity,Object.class);
		return res;
	}
	
	
	@RequestMapping(value="/authors/count",method=RequestMethod.GET)
	public ResponseEntity<Object> getAuthorCount(@RequestHeader(value="Accept") String ct,@RequestParam(value="search",required=false)  String search)  {
		UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url+"/authors/count/")
				.queryParam("search", search);
		HttpHeaders headers = new HttpHeaders();
		headers.set("Accept", ct);
		HttpEntity<?> entity = new HttpEntity<>(headers);
		ResponseEntity<Object> res = restTemplate.exchange(builder.build().toUri(), HttpMethod.GET,entity,Object.class);
		return res;
	}
	
	@RequestMapping(value="/authors/{authorId}",method=RequestMethod.PUT)
	public ResponseEntity<Object> updateAuthor(@RequestHeader(value="Accept") String ct,@RequestBody Author author, @PathVariable(value="authorId") Integer authorId)  {
		UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url+"/authors/"+authorId);
		HttpHeaders headers = new HttpHeaders();
		headers.set("Accept", ct);
		HttpEntity<?> entity = new HttpEntity<>(author,headers);
		ResponseEntity<Object> res = restTemplate.exchange(builder.build().toUri(), HttpMethod.PUT,entity,Object.class);
		return res;
	}
	
	@RequestMapping(value="/authors/{authorId}", method=RequestMethod.DELETE)
	public ResponseEntity<Object> deleteAuthorByPK(@RequestHeader(value="Accept") String ct,@PathVariable("authorId") Integer authorId)  {
		UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url+"/authors/"+authorId);
		HttpHeaders headers = new HttpHeaders();
		headers.set("Accept", ct);
		HttpEntity<?> entity = new HttpEntity<>(headers);
		ResponseEntity<Object> res = restTemplate.exchange(builder.build().toUri(), HttpMethod.DELETE,entity,Object.class);
		return res;
	}
	
	@RequestMapping(value="/authors", method=RequestMethod.POST)
	public ResponseEntity<Object> addAuthor(@RequestHeader(value="Accept") String ct,@RequestBody Author author)  {
		UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url+"/authors");
		HttpHeaders headers = new HttpHeaders();
		headers.set("Accept", ct);
		HttpEntity<?> entity = new HttpEntity<>(author,headers);
		ResponseEntity<Object> res = restTemplate.exchange(builder.build().toUri(), HttpMethod.POST,entity,Object.class);
		return res;
	}
}
