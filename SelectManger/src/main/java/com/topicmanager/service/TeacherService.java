package com.topicmanager.service;


import com.topicmanager.mapper.TeacherMapper;
import com.topicmanager.pojo.Teacher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TeacherService {

    @Autowired
    private TeacherMapper teacherMapper;

    public Teacher login(String loginName){
        Teacher teacher = teacherMapper.login(loginName);
        return teacher;
    }

}
