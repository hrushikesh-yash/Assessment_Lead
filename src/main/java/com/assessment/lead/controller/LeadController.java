package com.assessment.lead.controller;

import com.assessment.lead.exceptions.ApiError;
import com.assessment.lead.exceptions.ErrorResponse;
import com.assessment.lead.exceptions.LeadAlreadyExistsException;
import com.assessment.lead.model.ApiResponse;
import com.assessment.lead.model.Lead;
import com.assessment.lead.service.LeadService;
import java.util.Collections;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/leads")
public class LeadController {

  @Autowired private LeadService leadService;

  @PostMapping
  public ResponseEntity<Object> createLead(@RequestBody @Valid Lead lead) {
    try {
      leadService.createLead(lead);
      return ResponseEntity.ok()
          .body(ApiResponse.builder().status("success").data("Created Leads Successfully").build());
    } catch (LeadAlreadyExistsException ex) {
      ErrorResponse errorResponse =
          ErrorResponse.builder()
              .code("E10010")
              .messages(Collections.singletonList(ex.getMessage()))
              .build();
      return ResponseEntity.status(HttpStatus.BAD_REQUEST)
          .body(ApiError.builder().status("error").errorResponse(errorResponse).build());
    }
  }

  @GetMapping("/mobile/{mobileNumber}")
  public ResponseEntity<Object> getLeadsByMobileNumber(@PathVariable String mobileNumber) {
    List<Lead> leads = leadService.getLeadsByMobileNumber(mobileNumber);
    if (!leads.isEmpty()) {
      return ResponseEntity.ok()
          .body(ApiResponse.builder().status("success").data("Leads Found Successfully").build());
    } else {
      return ResponseEntity.status(HttpStatus.NOT_FOUND)
          .body(
              new ApiError(
                  "error",
                  ErrorResponse.builder()
                      .code("E10011")
                      .messages(Collections.singletonList("No Lead found with the Mobile Number :: "+ mobileNumber))
                      .build()));
    }
  }
}
