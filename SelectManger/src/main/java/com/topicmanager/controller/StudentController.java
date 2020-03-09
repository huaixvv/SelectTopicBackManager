package com.topicmanager.controller;


import com.topicmanager.pojo.Student;
import com.topicmanager.result.Result;
import com.topicmanager.service.StudentService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/student")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @GetMapping("login")
    @ResponseBody
    public Result<Student> login(@Param("loginName") String loginName){
        Student student = studentService.login(loginName);
        return  Result.success(student);
    }
}
