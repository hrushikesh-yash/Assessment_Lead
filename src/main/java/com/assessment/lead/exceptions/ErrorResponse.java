package com.assessment.lead.exceptions;

import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
public class ErrorResponse {
	private String code;
	private List<String> messages;
	
}
