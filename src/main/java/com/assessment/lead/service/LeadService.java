package com.assessment.lead.service;

import com.assessment.lead.exceptions.LeadAlreadyExistsException;
import com.assessment.lead.model.Lead;
import com.assessment.lead.repository.LeadRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LeadService {

  @Autowired
  private LeadRepository leadRepository;

  public Lead createLead(Lead lead) {
    if (leadRepository.existsById(lead.getLeadId())) {
      throw new LeadAlreadyExistsException("Lead Already Exists in the database with the lead id :: "+lead.getLeadId());
    }
    return leadRepository.save(lead);
  }
  
  public List<Lead> getLeadsByMobileNumber(String mobileNumber) {
    return leadRepository.findByMobileNumber(mobileNumber);
  }
}
