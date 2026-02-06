package com.example.SpringTest.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.SpringTest.domain.Student;
import com.example.SpringTest.service.StudentService;

import jakarta.validation.Valid;



@RestController
@RequestMapping("/students")
public class StudentController {
    @Autowired
    private StudentService studentService;

    @GetMapping
    public ResponseEntity<List<Student>> getAllStudents() {
        List<Student> studentsList = studentService.findAllStudents();
        return ResponseEntity.ok(studentsList);
    }

@PostMapping
public ResponseEntity<Map<String, Object>> createStudent(@Valid @RequestBody Student student) {
   Map<String, Object> map =studentService.createStudent(student);
    return new ResponseEntity<>(map, HttpStatus.CREATED);
        
    }
//1 tane öğrenci getirme
@GetMapping("/{id}")
public ResponseEntity<Student> findStudentById(@PathVariable Long id) {
    return ResponseEntity.ok(studentService.findStudentById(id));
    } 

    // 1 tane öğrenci silme
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteStudentById(@PathVariable Long id) {
        studentService.deleteStudentById(id);
        return ResponseEntity.ok(id + " ID numaralı öğrenci silinmiştir.");
    }

    // 1 öğrenci güncelleme
   @PutMapping("/{id}")
    public ResponseEntity<Student> updateStudentById(
            @PathVariable Long id, 
            @Valid @RequestBody Student student) {
        return ResponseEntity.ok(studentService.updateStudent(id, student));
    }

}
