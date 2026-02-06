package com.example.SpringTest.domain;

import java.time.LocalDateTime;


import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.AccessLevel;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Student {

   @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    private Long id;

    @Column(nullable = false,length = 32)
    @NotBlank(message = "First name is required")
    @Size(min=3,max = 32,message = "First name must be between {min} and {max} characters")
    private String firstName;
    
    @Column(nullable = false,length = 32)
    private String lastName;
    
  
    private Integer grade;
    
    @Column(nullable = false,unique = true)
    @Email(message = "Email should be valid")
    private String email;
   
    
    @Pattern(regexp = "^\\d{3}-\\d{3}-\\d{4}$", message = "Phone number must be in the format XXX-XXX-XXXX")    
    private String phoneNumber;
    
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm")
    @Setter(AccessLevel.NONE)
    private LocalDateTime createdAt=LocalDateTime.now();

}
