package com.topicmanager.infoGene;


import com.topicmanager.mapper.StudentMapper;
import com.topicmanager.mapper.TeacherMapper;
import com.topicmanager.pojo.Student;
import com.topicmanager.pojo.Teacher;
import com.topicmanager.utils.IDgenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;

@RequestMapping("/i")
@Controller
public class InsertTeacher {

    @Autowired
    public TeacherMapper teacherMapper;

    @Autowired
    public StudentMapper studentMapper;

    @GetMapping("/t")
    public void insertTea(){
        String _tea = "huast";
        int num = 2007;
        for (int i = 0; i < 13; i++) {
            Teacher t = new Teacher();
            t.setTeacherId(IDgenerator.generatorTeaId());
            t.setLoginName(_tea+ num++);
            t.setTeacherName(GetName.boyName());
            t.setCollege(GetCollege_Sex._college());
            t.setSex("男");
            t.setPhone(GetPhone_Email.getTel());
            t.setEmail(GetPhone_Email.getEmail(6,9));
            t.setTeacherPost(GetCollege_Sex._post());
            t.setTeacherPwd("888888");
            teacherMapper.insert(t);
        }
    }


    @GetMapping("/s")
    public void insertStu(){
        int num = 20160004;
        for (int i = 0; i < 6; i++) {
            Student s = new Student();
            s.setStudentId(IDgenerator.generatorStuId());
            s.setLoginName(num++ +"");
            s.setStudentPwd("888888");
            s.setStudentName(GetName.boyName());
            s.setSex("男");
            s.setCollege(GetCollege_Sex._college());
            s.setPhone(GetPhone_Email.getTel());
            s.setEmail(GetPhone_Email.getEmail(6,9));
            studentMapper.insert(s);
        }
    }
}
