package com.example.FirstCICD;

import java.util.List;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class StudentService {
	
	private final StudentRepository studentRepository;
	
	public List<Student> getAll() {
		return studentRepository.findAll();
	}

	public void save(Student student) {
		studentRepository.save(student);
	}
}
