package com.application.services.exception;

public class DataInvalidInputException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public DataInvalidInputException(String message) {
		super(message);
	}

}