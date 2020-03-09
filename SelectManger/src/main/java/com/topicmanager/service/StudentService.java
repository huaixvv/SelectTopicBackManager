package com.topicmanager.service;


import com.topicmanager.mapper.StudentMapper;
import com.topicmanager.pojo.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentService {

    @Autowired
    private StudentMapper studentMapper;

    //login
    public Student login(String loginName){
        Student student = studentMapper.login(loginName);
        System.out.println(student);
        return student;
    }
}
