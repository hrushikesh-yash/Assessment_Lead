package com.assessment.lead.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDate;
import javax.persistence.*;
import javax.validation.constraints.*;
import lombok.*;

@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
@Entity
@Builder
@Table(name = "LEAD_DETAILS")
public class Lead {

  @Id
  @Column(name = "LeadId", nullable = false, unique = true)
  @Min(value = 1, message = "LeadId should be greater than 0")
  private Integer leadId;

  @Column(name = "FirstName", nullable = false)
  @Pattern(regexp = "^[A-Za-z]*$", message = "FirstName should contain only alphabets")
  private String firstName;

  @Column(name = "MiddleName")
  @Pattern(regexp = "^[A-Za-z]*$", message = "MiddleName should contain only alphabets")
  private String middleName;

  @Column(name = "LastName", nullable = false)
  @Pattern(regexp = "^[A-Za-z]*$", message = "LastName should contain only alphabets")
  private String lastName;

  @Column(name = "MobileNumber", nullable = false)
  @Pattern(
      regexp = "^[6-9]\\d{9}$",
      message = "MobileNumber should be a valid 10-digit number starting with 6-9")
  private Long mobileNumber;

  @Column(name = "Gender", nullable = false)
  @Pattern(regexp = "^(Male|Female|Others)$", message = "Gender should be Male, Female, or Others")
  private String gender;

  @Column(name = "Dob", nullable = false)
  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
  private LocalDate dob;

  @Column(name = "Email", nullable = false)
  @Email(message = "Email should be valid")
  private String email;
}
