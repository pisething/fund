package com.piseth.ing.fund.exception;

public class ResourceNotFoundException extends RuntimeException{

	private static final long serialVersionUID = 3241930840808077048L;
	private static final String MESSAGE = "[%s] Not Found with Id = [%s]";
	
	public ResourceNotFoundException(String resourceName, String resourceId) {
		super(MESSAGE.formatted(resourceName,resourceId));
	}

}
