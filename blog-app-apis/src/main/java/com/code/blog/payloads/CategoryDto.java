package com.code.blog.payloads;

import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class CategoryDto {
	private Integer categoryId;
	@Size(min=3, max=20 , message="Title must be min 3 chars and max 20")
	private String categoryTitle;
	@Size(min=3, max=300 , message="Title must be min 3 chars and max 300")
	private String categoryDescription;

}
