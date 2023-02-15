package com.mongodb.student_management_system.controller;

import com.mongodb.student_management_system.entity.Student;
import com.mongodb.student_management_system.kafka.StudentKafkaProducer;
import com.mongodb.student_management_system.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/student")
public class StudentController {
    @Autowired
    private StudentKafkaProducer studentProducer;

    @Autowired
    private StudentService studentService;

    @PostMapping("/")
    public Student createStudent(@RequestBody Student student) {
        return studentProducer.sendMessage(student);
    }

    @KafkaListener(topics = "student_management_system", groupId = "mystudents")
    @GetMapping("/{id}")
    public Student getStudentById(@PathVariable String id){
        return studentService.getStudentById(id);
    }
}
