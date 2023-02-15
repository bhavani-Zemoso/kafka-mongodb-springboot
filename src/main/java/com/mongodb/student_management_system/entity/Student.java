package com.mongodb.student_management_system.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;

@Document(collection = "student")
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Student {
    @Id
    private String id;
    private String name;
    @Field(name="mail")
    private String email;
    private Department department;
    private List<Subject> subjects;
}
