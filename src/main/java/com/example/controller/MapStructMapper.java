package com.example.controller;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.example.repository.Student;

@Mapper(componentModel = "spring")
public interface MapStructMapper {

	MapStructMapper INSTANCE = Mappers.getMapper(MapStructMapper.class);

	StudentDTO toDTO(Student student);

	Student toEntity(StudentDTO studentDTO);

	List<StudentDTO> toDTOList(List<Student> studentList);

	List<Student> toEntityList(List<StudentDTO> studentDTOList);
}
