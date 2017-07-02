package uk.co.engagetech.backend.controller;

public class ErrorResponse {

	private String errorCode;

	public ErrorResponse(String errorCode) {
		super();
		this.errorCode = errorCode;
	}

	public String getErrorCode() {
		return errorCode;
	}

}
