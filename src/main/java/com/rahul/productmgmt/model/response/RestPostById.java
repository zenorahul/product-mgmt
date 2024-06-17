package com.rahul.productmgmt.model.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RestPostById {

	private int userId;
	private int id;
	private String title;
	private String body;

	@Override
	public String toString() {
		return "RestPostById [userId=" + userId + ", id=" + id + ", title=" + title + ", body=" + body + "]";
	}

}
