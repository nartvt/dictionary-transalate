package com.program.dictionary.exception;

import lombok.Data;

@Data
public class InvalidKeyWordException extends Exception {
	private static final long serialVersionUID = 1L;
	
	private String message;

}
