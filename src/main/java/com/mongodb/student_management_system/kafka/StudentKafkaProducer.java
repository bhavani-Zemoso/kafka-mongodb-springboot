package com.mongodb.student_management_system.kafka;

import com.mongodb.student_management_system.entity.Student;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@Service
public class StudentKafkaProducer {

    private static final Logger LOGGER = LoggerFactory.getLogger(StudentKafkaProducer.class);

    private KafkaTemplate<String, Student> kafkaTemplate;

    public StudentKafkaProducer(KafkaTemplate<String, Student> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public Student sendMessage(Student data){

        LOGGER.info(String.format("Message sent -> %s", data.toString()));
        Message<Student> message = MessageBuilder
                .withPayload(data)
                .setHeader(KafkaHeaders.TOPIC, "student_management_system")
                .build();
        kafkaTemplate.send(message);
        return data;
    }
}
