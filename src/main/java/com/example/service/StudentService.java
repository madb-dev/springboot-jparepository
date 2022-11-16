package com.example.service;

import java.util.List;

import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;

import com.example.repository.Student;

public interface StudentService {

	List<Student> findAll();

	Student findById(int id);

	Student findByName(String name);

	Student delete(int id);

	Student save(Student student);

	Student update(Student student) throws NotFoundException;
}
