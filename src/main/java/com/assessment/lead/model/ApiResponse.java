package com.assessment.lead.model;

import com.assessment.lead.exceptions.ErrorResponse;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
public class ApiResponse {
	private String status;
	private String data;
	
}
