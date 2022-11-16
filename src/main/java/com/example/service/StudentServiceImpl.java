package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.stereotype.Service;

import com.example.repository.Student;
import com.example.repository.StudentRepository;

@Service
public class StudentServiceImpl implements StudentService {

	@Autowired
	StudentRepository studentRepository;

	@Override
	public List<Student> findAll() {
		return studentRepository.findAll();
	}

	@Override
	public Student findById(int id) {
		return studentRepository.findById(id);
	}

	@Override
	public Student findByName(String name) {
		return studentRepository.findByName(name);
	}

	@Override
	public Student delete(int id) {
		return studentRepository.deleteById(id);
	}

	@Override
	public Student save(Student student) {
		Integer idStudent = studentRepository.findAll().size() + 1;
		student = Student.builder().name(student.getName()).lastName(student.getLastName())
				.nationality(student.getNationality()).id(idStudent).build();

		return studentRepository.save(student);
	}

	@Override
	public Student update(Student student) throws NotFoundException {
		Student studentToUpdate = studentRepository.findById(student.getId());

		if (studentToUpdate != null) {
			if (student.getName() != null) {
				studentToUpdate.setName(student.getName());
			} else if (student.getLastName() != null) {
				studentToUpdate.setLastName(student.getLastName());
			} else if (student.getNationality() != null) {
				studentToUpdate.setNationality(student.getNationality());
			}
		} else {
			throw new NotFoundException();
		}

		return studentRepository.save(studentToUpdate);
	}
}
