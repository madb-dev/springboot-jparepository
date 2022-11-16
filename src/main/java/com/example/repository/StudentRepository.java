package com.example.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Integer> {

	List<Student> findAll();

	Student findById(int Id);

	Student findByName(String name);

	Student deleteById(int id);

	@SuppressWarnings("unchecked")
	Student save(Student student);
}
