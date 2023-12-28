package com.assessment.lead.exceptions;

public class LeadAlreadyExistsException extends RuntimeException {
	public LeadAlreadyExistsException(String message) {
		super(message);
	}
}
