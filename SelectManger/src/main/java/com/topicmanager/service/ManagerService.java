package com.topicmanager.service;

import com.topicmanager.mapper.ManagerMapper;
import com.topicmanager.mapper.StudentMapper;
import com.topicmanager.mapper.TeacherMapper;
import com.topicmanager.pojo.Manager;
import com.topicmanager.pojo.Student;
import com.topicmanager.pojo.Teacher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ManagerService {

    @Autowired
    private ManagerMapper managerMapper;

    @Autowired
    private TeacherMapper teacherMapper;

    @Autowired
    private StudentMapper studentMapper;

    public Manager login(String loginName) {
        Manager manager = new Manager();
        manager.setLoginName(loginName);
        return managerMapper.selectOne(manager);
    }

    public List<Teacher> getAllteachers() {
        return teacherMapper.selectAll();
    }

    public Integer delTeachers(Teacher t) {
        return  teacherMapper.delete(t);
    }

    public List<Student> getAllStudents() {
        return studentMapper.selectAll();
    }

    public Integer delStudent(Student s) {
        return studentMapper.delete(s);
    }

    public Manager getInfo(String managerId) {
        Manager manager = new Manager();
        manager.setManagerId(managerId);
        return managerMapper.selectOne(manager);
    }

    public Integer editManager(Manager manager) {
        return  managerMapper.updateByPrimaryKey(manager);
    }
}