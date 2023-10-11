package com.example.FirstCICD;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/student")
@AllArgsConstructor
public class StudentController {
	
	private final StudentService studentService;
	
	@PostMapping
	public ResponseEntity<String> getAllStudent(@RequestParam String email, @RequestParam String name) {
		studentService.save(Student.builder()
					.email(email)
					.name(name)
					.build()
				);
		return ResponseEntity.ok("save successfully");
	}
	
	@GetMapping
	public ResponseEntity<List<Student>> getAll() {
		return ResponseEntity.ok(studentService.getAll());
	}
}
