package com.topicmanager.controller;


import com.topicmanager.pojo.Teacher;
import com.topicmanager.result.CodeMsg;
import com.topicmanager.result.Result;
import com.topicmanager.service.TeacherService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/teacher")
public class TeacherController {


    @Autowired
    private TeacherService teacherService;

    @GetMapping("/login")
    @ResponseBody
    public Result<Teacher> login(@Param("loginName") String loginName, @Param("teacherPwd") String teacherPwd){
        Teacher teacher = teacherService.login(loginName);
        System.out.println(teacher);
        if (teacher == null) return Result.error(CodeMsg.USER_NOT_FOUND);
        if(!teacherPwd.equals(teacher.getTeacherPwd())) return Result.error(CodeMsg.PWD_WRONG);
        return  Result.success(teacher);
    }
}
