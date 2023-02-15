package com.mongodb.student_management_system.repository;

import com.mongodb.student_management_system.entity.Student;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface StudentRepository extends MongoRepository<Student, String> {
}
