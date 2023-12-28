package com.assessment.lead.repository;

import com.assessment.lead.model.Lead;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LeadRepository extends JpaRepository<Lead, Integer> {
  @Override
  boolean existsById (Integer leadId);
  List<Lead> findByMobileNumber(String mobileNumber);
}
