package com.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResponseBean<T> {
	public ResponseBean(String message, T data) {
		this.data=data;
		this.message=message;
	}
	T data;
	String message;
}
