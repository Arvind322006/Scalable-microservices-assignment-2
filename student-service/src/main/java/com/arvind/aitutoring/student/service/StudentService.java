package com.arvind.aitutoring.student.service;

import com.arvind.aitutoring.student.dto.CreateStudentRequest;
import com.arvind.aitutoring.student.dto.StudentResponse;
import com.arvind.aitutoring.student.dto.UpdateStudentRequest;
import com.arvind.aitutoring.student.entity.Student;
import com.arvind.aitutoring.student.exception.DuplicateResourceException;
import com.arvind.aitutoring.student.exception.ResourceNotFoundException;
import com.arvind.aitutoring.student.repository.StudentRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Transactional
    public StudentResponse createStudent(CreateStudentRequest request) {
        if (studentRepository.existsByEmail(request.getEmail())) {
            throw new DuplicateResourceException("Student already exists with email: " + request.getEmail());
        }

        String tier = (request.getTier() != null && !request.getTier().isBlank()) ? request.getTier().toUpperCase() : "FREE";
        Student student = new Student(request.getName(), request.getEmail(), "INACTIVE", tier);
        Student saved = studentRepository.save(student);
        return StudentResponse.fromEntity(saved);
    }

    @Transactional(readOnly = true)
    public StudentResponse getStudentById(Long id) {
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Student not found with id: " + id));
        return StudentResponse.fromEntity(student);
    }

    @Transactional(readOnly = true)
    public List<StudentResponse> getAllStudents() {
        return studentRepository.findAll().stream()
                .map(StudentResponse::fromEntity)
                .toList();
    }

    @Transactional
    public StudentResponse updateStudent(Long id, UpdateStudentRequest request) {
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Student not found with id: " + id));

        if (!student.getEmail().equalsIgnoreCase(request.getEmail()) && studentRepository.existsByEmail(request.getEmail())) {
            throw new DuplicateResourceException("Email already in use: " + request.getEmail());
        }

        student.setName(request.getName());
        student.setEmail(request.getEmail());
        if (request.getSubscriptionStatus() != null) {
            student.setSubscriptionStatus(request.getSubscriptionStatus().toUpperCase());
        }
        if (request.getTier() != null) {
            student.setTier(request.getTier().toUpperCase());
        }

        Student updated = studentRepository.save(student);
        return StudentResponse.fromEntity(updated);
    }

    @Transactional
    public void deleteStudent(Long id) {
        if (!studentRepository.existsById(id)) {
            throw new ResourceNotFoundException("Student not found with id: " + id);
        }
        studentRepository.deleteById(id);
    }
}
