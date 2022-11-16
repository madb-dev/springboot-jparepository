package com.example.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import com.example.repository.Student;
import com.example.service.StudentService;
import com.google.gson.Gson;

@ExtendWith(MockitoExtension.class)
@SpringBootTest(classes = { MapStructMapperImpl.class, StudentDTO.class, Student.class })
public class StudentControllerTest {

	@InjectMocks
	private StudentController controller;

	@Mock
	private StudentService service;

	@Autowired
	private MapStructMapperImpl mapper;

	@BeforeEach
	public void setup() {
		controller = new StudentController(mapper, service);
	}

	@Test
	public void testGetAllEmployees() throws Exception {

		// prepare
		Student s1 = Student.builder().name("Prueba1").lastName("p1").nationality("EEUU").build();
		Student s2 = Student.builder().name("Prueba2").lastName("p2").nationality("EEUU").build();

		List<Student> studentList = new ArrayList<>();
		studentList.add(s1);
		studentList.add(s2);
		when(service.findAll()).thenReturn(studentList);

		// act
		ResponseEntity<List<StudentDTO>> result = controller.getStudents();

		// finally
		assertThat(new Gson().toJson(studentList)).isEqualTo(new Gson().toJson(result.getBody()));
	}

}
