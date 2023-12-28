package com.assessment.lead.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

import com.assessment.lead.exceptions.LeadAlreadyExistsException;
import com.assessment.lead.model.Lead;
import com.assessment.lead.service.LeadService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@ExtendWith(MockitoExtension.class)
class LeadControllerTest {

  @InjectMocks private LeadController leadController;

  @Mock private LeadService leadService;

  @Test
  void testCreateLead_Success() {

    Lead lead = new Lead();

    when(leadService.createLead(any(Lead.class))).thenReturn(lead);

    ResponseEntity<Object> responseEntity = leadController.createLead(lead);

    assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
  }

  @Test
  void testCreateLead_AlreadyExists() {

    Lead lead = new Lead();

    doThrow(new LeadAlreadyExistsException("Lead Already Exists in the database with the lead id"))
        .when(leadService)
        .createLead(any(Lead.class));

    ResponseEntity<Object> responseEntity = leadController.createLead(lead);

    assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
  }
}
