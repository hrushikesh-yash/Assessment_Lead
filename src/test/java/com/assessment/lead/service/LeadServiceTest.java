package com.assessment.lead.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.assessment.lead.exceptions.LeadAlreadyExistsException;
import com.assessment.lead.model.Lead;
import com.assessment.lead.repository.LeadRepository;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class LeadServiceTest {

  @Mock private LeadRepository leadRepository;

  @InjectMocks private LeadService leadService;

  @Test
  void createLead_LeadDoesNotExist_Success() {
    Lead lead =createLead ();

    when(leadRepository.existsById(lead.getLeadId())).thenReturn(false);
    when(leadRepository.save(lead)).thenReturn(lead);

    Lead createdLead = leadService.createLead(lead);

    assertNotNull(createdLead);
    assertEquals(lead, createdLead);

    verify(leadRepository, times(1)).existsById(lead.getLeadId());
    verify(leadRepository, times(1)).save(lead);
  }

  @Test
  void createLead_LeadAlreadyExists_ExceptionThrown() {
    Lead lead = createLead ();

    when(leadRepository.existsById(lead.getLeadId())).thenReturn(true);

    assertThrows(LeadAlreadyExistsException.class, () -> leadService.createLead(lead));

    verify(leadRepository, times(1)).existsById(lead.getLeadId());
    verify(leadRepository, never()).save(any());
  }

  @Test
  void getLeadsByMobileNumber_ValidMobileNumber_ListReturned() {
    String mobileNumber = "8877887788";
    List<Lead> expectedLeads = Collections.singletonList(createLead ());

    when(leadRepository.findByMobileNumber(mobileNumber)).thenReturn(expectedLeads);

    List<Lead> leads = leadService.getLeadsByMobileNumber(mobileNumber);

    assertNotNull(leads);
    assertEquals(expectedLeads, leads);

    verify(leadRepository, times(1)).findByMobileNumber(mobileNumber);
  }

  public Lead createLead() {
    return Lead.builder()
        .leadId(5678)
        .firstName("Vineet")
        .middleName("")
        .lastName("Kv")
        .dob (LocalDate.parse("25/12/2023", DateTimeFormatter.ofPattern ("dd/MM/yyyy")))
        .gender("Male")
        .mobileNumber(Long.valueOf("8877887788"))
        .build();
  }
}
