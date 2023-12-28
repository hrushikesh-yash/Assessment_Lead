package com.assessment.lead.exceptions;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
public class ApiError {
	private String status;
	private ErrorResponse errorResponse;
}
