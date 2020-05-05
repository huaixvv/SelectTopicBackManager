package com.topicmanager.service;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.topicmanager.mapper.ManagerMapper;
import com.topicmanager.mapper.StudentMapper;
import com.topicmanager.mapper.TeacherMapper;
import com.topicmanager.pojo.Manager;
import com.topicmanager.pojo.Student;
import com.topicmanager.pojo.Teacher;
import com.topicmanager.pojo.Thesis;
import com.topicmanager.result.ListResult;
import com.topicmanager.utils.IDgenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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


    public Integer delTeachers(Teacher t) {
        return  teacherMapper.delete(t);
    }


    public ListResult getAllteachers(int pageNum, int pageSize) {
            PageHelper.startPage(pageNum, pageSize);
            List<Teacher> list = teacherMapper.selectAll();
            int count = teacherMapper.selectCount(new Teacher());
            PageInfo<Teacher> info = new PageInfo<>(list);
            ListResult listResult = new ListResult(info.getList(), count);
            return listResult;
    }

    public ListResult getAllStudents(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Student> list = studentMapper.selectAll();
        int count = studentMapper.selectCount(new Student());
        PageInfo<Student> info = new PageInfo<>(list);
        ListResult listResult = new ListResult(info.getList(), count);
        return listResult;
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

    public void ipmortTeachers(ArrayList<Teacher> teacherList) {
        for (int i = 0; i < teacherList.size(); i++) {
            teacherList.get(i).setTeacherId(IDgenerator.generatorTeaId());
            teacherMapper.insert(teacherList.get(i));
        }
    }

    public void ipmortStudents(ArrayList<Student> list) {
        for (int i = 0; i < list.size(); i++) {
            list.get(i).setStudentId(IDgenerator.generatorStuId());
            studentMapper.insert(list.get(i));
        }
    }
}