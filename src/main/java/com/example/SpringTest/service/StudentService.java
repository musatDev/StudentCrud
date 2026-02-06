package com.example.SpringTest.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Service;

import com.example.SpringTest.domain.Student;
import com.example.SpringTest.exception.ConflictException;
import com.example.SpringTest.exception.ResourceNotFoundException;
import com.example.SpringTest.repository.IStudentRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor    
public class StudentService {

    private final IStudentRepository studentRepository;
 

    public List<Student> findAllStudents() {
        return studentRepository.findAll();
    }


    public Map<String, Object> createStudent(Student student) {
        if(studentRepository.existsByEmail(student.getEmail())) {
           throw new ConflictException("Email already exists.");
        }
        Student savedStudent = studentRepository.save(student);
        Map<String, Object> map = new HashMap<>();
        map.put("message", "Student created successfully");
        map.put("student", savedStudent);
        return map;
        
    }


    public Student findStudentById(Long id) {
        return studentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Student not found with id: " + id));
    }


    public void deleteStudentById(Long id) {
        if (!studentRepository.existsById(id)) {
            throw new ResourceNotFoundException("Student not found with id: " + id);
        }
        studentRepository.deleteById(id);
       
    }


    @Transactional 
    public Student updateStudent(Long id, Student studentDetails) {
        // Öğrenciyi bul, yoksa hata fırlat
        Student existingStudent = studentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Student not found with id: " + id));

        // Güncelleme mantığı
        updateStudentFields(existingStudent, studentDetails);

        return studentRepository.save(existingStudent);
    }
    private void updateStudentFields(Student existing, Student updated) {
        existing.setFirstName(updated.getFirstName());
        existing.setLastName(updated.getLastName());
        existing.setEmail(updated.getEmail());
        existing.setPhoneNumber(updated.getPhoneNumber());
        existing.setGrade(updated.getGrade());

   }
}


