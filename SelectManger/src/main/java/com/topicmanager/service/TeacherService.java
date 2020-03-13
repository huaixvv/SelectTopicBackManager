package com.topicmanager.service;


import com.topicmanager.mapper.TeacherMapper;
import com.topicmanager.pojo.Teacher;
import com.topicmanager.pojo.Thesis;
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


    //修改教师信息
    public Integer editTeacherInfo(Teacher teacher) {
        return teacherMapper.updateByPrimaryKey(teacher);
    }

    //select teacher by id
    public Teacher getTeacherById(String teacherId) {
        return teacherMapper.selectByPrimaryKey(teacherId);
    }
}
