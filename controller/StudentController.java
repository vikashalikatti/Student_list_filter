package org.jsp.projet.controller;

import org.jsp.projet.dto.Student;
import org.jsp.projet.repository.StudentRepository;
import org.jsp.projet.studentSpecifications.StudentSpecifications;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StudentController {
	@Autowired
	private StudentRepository studentRepository;

	@GetMapping("/students")
	public ResponseEntity<Page<Student>> getStudents(@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "10") int size) {
		Pageable pageable = PageRequest.of(page, size);
		Page<Student> students = studentRepository.findAll(pageable);
		return ResponseEntity.ok(students);
	}

	@GetMapping("/students/filter")
	public ResponseEntity<Page<Student>> filterStudents(@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "10") int size, @RequestParam(required = false) String name,
			@RequestParam(required = false) Integer totalMarks) {
		Pageable pageable = PageRequest.of(page, size);
		Specification<Student> spec = Specification.where(StudentSpecifications.withName(name))
				.and(StudentSpecifications.withTotalMarks(totalMarks));
		Page<Student> students = studentRepository.findAll(spec, pageable);
		return ResponseEntity.ok(students);
	}
}
