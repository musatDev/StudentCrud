package com.example.SpringTest.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.SpringTest.domain.Student;

public interface IStudentRepository extends JpaRepository<Student, Long> {

    boolean existsByEmail(String email);



}
