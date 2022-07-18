package com.code.blog.payloads;

import javax.persistence.Column;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@NoArgsConstructor
@Getter

@Setter
public class UserDto {

	
	private int id;
	@NotNull
	@Size(min=4 , message="Usernsme must be atleast 4 characters long")
	private String name;
	@Email(message="Email Address Not Valid!!")
	private String email;
	@NotNull
	@Size(min=3, max=10 , message="Password must be min 3 chars and max 10")
	
	private String password;
	@NotNull
	private String about;
}
