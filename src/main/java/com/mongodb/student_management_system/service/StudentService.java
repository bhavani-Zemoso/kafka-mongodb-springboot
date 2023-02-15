package com.mongodb.student_management_system.service;

import com.mongodb.student_management_system.entity.Student;
import com.mongodb.student_management_system.kafka.StudentKafkaProducer;
import com.mongodb.student_management_system.repository.StudentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {

    @Autowired
    StudentRepository studentRepository;

    @Autowired
    private StudentKafkaProducer studentProducer;

    private static final Logger LOGGER = LoggerFactory.getLogger(StudentService.class);

    @KafkaListener(topics = "student_management_system", groupId = "mystudents")
    public Student createStudent(Student student) {
        LOGGER.info(String.format("Message sent -> %s", student.toString()));
        return studentRepository.save(student);
    }

    public Student getStudentById(String id) {
        Student student = studentRepository.findById(id).get();
        return studentProducer.sendMessage(student);
    }

}
