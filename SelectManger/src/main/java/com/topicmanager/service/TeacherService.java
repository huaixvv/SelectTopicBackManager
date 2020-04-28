package com.topicmanager.service;


import com.topicmanager.mapper.StudentMapper;
import com.topicmanager.mapper.TeacherMapper;
import com.topicmanager.pojo.StudentThesis;
import com.topicmanager.pojo.Teacher;
import com.topicmanager.pojo.Thesis;
import com.topicmanager.utils.IDgenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TeacherService {

    @Autowired
    private TeacherMapper teacherMapper;

    @Autowired
    private StudentMapper studentMapper;

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


    public List<StudentThesis> getStudent(String teacherName) {
        List<StudentThesis> studentOfTeachers = studentMapper.getStudentOfTeacher(teacherName);
        System.out.println(studentOfTeachers);
        return studentOfTeachers;
    }

    public Teacher getTeacherByName(String teacherName) {
        Teacher teacher = new Teacher();
        teacher.setTeacherName(teacherName);
        return  teacherMapper.selectOne(teacher);
    }


    public Integer addTeacher(Teacher t) {
        t.setTeacherId(IDgenerator.generatorTeaId());
        return teacherMapper.insert(t);
    }
}
