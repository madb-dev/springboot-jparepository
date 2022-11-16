package com.example.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.repository.Student;
import com.example.service.StudentService;

@RestController
@RequestMapping("/students")
public class StudentController {
	private static final Logger LOGGER = LoggerFactory.getLogger(StudentController.class);

	@Autowired
	private MapStructMapper studentMapper;
	@Autowired
	private StudentService studentService;

	public StudentController(MapStructMapper studentMapper, StudentService studentService) {
		this.studentMapper = studentMapper;
		this.studentService = studentService;
	}

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<StudentDTO>> getStudents() {
		ResponseEntity<List<StudentDTO>> entities = null;
		List<StudentDTO> students = studentMapper.toDTOList(studentService.findAll());

		entities = (students != null) ? new ResponseEntity<List<StudentDTO>>(students, HttpStatus.OK)
				: new ResponseEntity<List<StudentDTO>>(students, HttpStatus.NOT_FOUND);

		return entities;
	}

	@RequestMapping(path = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<StudentDTO> getStudentById(@PathVariable(value = "id") int id) {
		ResponseEntity<StudentDTO> entity = null;
		StudentDTO student = studentMapper.toDTO(studentService.findById(id));

		entity = (student != null) ? new ResponseEntity<StudentDTO>(student, HttpStatus.OK)
				: new ResponseEntity<StudentDTO>(student, HttpStatus.NOT_FOUND);

		return entity;
	}

	@RequestMapping(path = "/student/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<StudentDTO> deleteStudentById(@PathVariable(value = "id") int id) {
		ResponseEntity<StudentDTO> entity = null;
		StudentDTO student = studentMapper.toDTO(studentService.findById(id));

		entity = (student != null)
				? new ResponseEntity<StudentDTO>(studentMapper.toDTO(studentService.delete(id)), HttpStatus.ACCEPTED)
				: new ResponseEntity<StudentDTO>(student, HttpStatus.NOT_FOUND);

		return entity;
	}

	@RequestMapping(path = "/student", method = RequestMethod.POST)
	public ResponseEntity<StudentDTO> createStudent(@RequestBody Student student) {
		return new ResponseEntity<StudentDTO>(studentMapper.toDTO(studentService.save(student)), HttpStatus.CREATED);
	}

	@RequestMapping(path = "/student", method = RequestMethod.PUT)
	public ResponseEntity<StudentDTO> updateStudent(@RequestBody Student student) throws NotFoundException {
		StudentDTO studentToUpdate = null;
		HttpStatus status = null;
		try {
			studentToUpdate = studentMapper.toDTO(studentService.update(student));
			status = HttpStatus.ACCEPTED;
		} catch (NotFoundException e) {
			LOGGER.error("Id of the entity to update: " + e.getLocalizedMessage());
			status = HttpStatus.NOT_FOUND;
		}

		return new ResponseEntity<StudentDTO>(studentToUpdate, status);
	}

	@GetMapping("/test")
	public String getHello() {
		return "call ok!";
	}
}
