package com.code.blog.controllers;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.code.blog.entities.Post;
import com.code.blog.payloads.ApiResponse;
import com.code.blog.payloads.PostDto;
import com.code.blog.payloads.PostResponse;
import com.code.blog.payloads.UserDto;
import com.code.blog.services.FileService;
import com.code.blog.services.PostService;
@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/")

public class PostController {
	@Autowired
	private PostService postService;
	@Autowired
	private FileService fileService;
	@Value("${project.image}")
	private String path;
	
	//create a post
	@PostMapping("/user/{userId}/category/{categoryId}/posts")
	public ResponseEntity<PostDto> createPost(@RequestBody PostDto postDto ,@PathVariable Integer userId,@PathVariable Integer categoryId)
	{
	       PostDto createdPost = this.postService.createPost(postDto, userId, categoryId);
	       return new ResponseEntity<PostDto>(createdPost,HttpStatus.CREATED);
	}
	
	//get all posts by a user
	@GetMapping("/user/{userId}/posts")
	public ResponseEntity<List<PostDto>> getPostsByUser(@PathVariable Integer userId)
	{
		List<PostDto> posts =this.postService.getPostsByUser(userId);
	    return new ResponseEntity<List<PostDto>>(posts,HttpStatus.OK);
	
	}
	
	//get all posts by a category
	@GetMapping("/category/{categoryId}/posts")
	public ResponseEntity<List<PostDto>> getPostsByCategory(@PathVariable Integer categoryId)
	{
		List<PostDto> posts =this.postService.getPostsByCategory(categoryId);
	    return new ResponseEntity<List<PostDto>>(posts,HttpStatus.OK);
	
	}
	//parameter se nikalne ke liye
	//get all posts
	@GetMapping("/posts")
	public ResponseEntity<PostResponse> getAllPost(
			@RequestParam(value="pageNumber", defaultValue="0" , required= false)Integer pageNumber,
			@RequestParam(value="pageSize", defaultValue="10" , required= false) Integer pageSize,
	        @RequestParam(value="sortBy", defaultValue="postId" , required= false)String sortBy,
	        @RequestParam(value="sortDir", defaultValue="asc" , required= false)String sortDir
	        )
	
	{
		PostResponse  postResponse=	this.postService.getAllPost(pageNumber,pageSize,sortBy,sortDir);
	return new ResponseEntity<PostResponse>(postResponse,HttpStatus.OK);
	
	}
	
	
	//get  posts by id
	
	@GetMapping("/posts/{postId}")
	public ResponseEntity<PostDto> getPostById(@PathVariable Integer postId)
	{
	PostDto postDto = this.postService.getPostById(postId);
	return new ResponseEntity<PostDto>(postDto,HttpStatus.OK);
	
	}
	
	
	//delete by id 
	@DeleteMapping("/posts/{postId}")
	public ApiResponse deletePost(@PathVariable Integer postId )
	{
		this.postService.deletePost(postId);
		return new ApiResponse("Post Deleted Successfully", true);
	}
	//update post
	
	
	@PutMapping("/posts/{postId}")
	public ResponseEntity<PostDto> updatePost(@RequestBody PostDto postDto,@PathVariable Integer postId )
	{
		PostDto updatePost = this.postService.updatePost(postDto,postId);
		return ResponseEntity.ok(updatePost);
	}
	
	//search
	@GetMapping("/posts/search/{keywords}")
	public ResponseEntity<List<PostDto>> searchPostByTitle(@PathVariable("keywords") String keywords)
	{
	List<PostDto> searchPosts = this.postService.searchPosts(keywords);
	return new ResponseEntity<List<PostDto>>(searchPosts,HttpStatus.OK);
	}

	
	//Post image upload
	@PostMapping("/post/image/upload/{postId}")
	public ResponseEntity<PostDto> uploadPostImage(
			@RequestParam("image") MultipartFile image, @PathVariable Integer postId) throws IOException 
	{
		PostDto postDto = this.postService.getPostById(postId);
		String uploadImage = this.fileService.uploadImage(path , image);
	
	postDto.setImageName(uploadImage);
	PostDto updatePost = this.postService.updatePost(postDto, postId);
	return new   ResponseEntity<PostDto>(updatePost,HttpStatus.OK);
	}
	
	@GetMapping(value="/post/image/{imageName}",produces=MediaType.IMAGE_JPEG_VALUE)
	
	 public void downloadImage(
			 @PathVariable("imageName") String imageName,
			 HttpServletResponse response
			 )throws IOException
	 {
		 InputStream resource =this.fileService.getResource(path, imageName);
		 response.setContentType(MediaType.IMAGE_JPEG_VALUE);
		 StreamUtils.copy(resource, response.getOutputStream());
	 }
	 
	
	
}
