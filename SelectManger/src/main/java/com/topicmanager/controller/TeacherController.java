package com.topicmanager.controller;


import com.topicmanager.pojo.Teacher;
import com.topicmanager.pojo.Thesis;
import com.topicmanager.result.CodeMsg;
import com.topicmanager.result.Result;
import com.topicmanager.service.TeacherService;
import com.topicmanager.service.ThesisService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/teacher")
public class TeacherController {


    @Autowired
    private TeacherService teacherService;

    @Autowired
    private ThesisService thesisService;

    @PostMapping("/login")
    @ResponseBody
    public Result<Teacher> login(@Param("loginName") String loginName, @Param("teacherPwd") String teacherPwd){
        Teacher teacher = teacherService.login(loginName);
        System.out.println(teacher);
        if (teacher == null) return Result.error(CodeMsg.USER_NOT_FOUND);
        if(!teacherPwd.equals(teacher.getTeacherPwd())) return Result.error(CodeMsg.PWD_WRONG);
        return  Result.success(teacher);
    }

    @GetMapping("/teacherThesis")
    @ResponseBody
    public Result<List<Thesis>> getThesisByTeacherId(@Param("teacherId") String teacherId){
        List<Thesis> thesislist = thesisService.getThesisByTeacherId(teacherId);
        System.out.println(thesislist);
        return  Result.success(thesislist);
    }

    @GetMapping("/delThesis")
    @ResponseBody
    public Result<CodeMsg> deleteById(@Param("thesisId") String thesisId){
        thesisService.deleteById(thesisId);
        return Result.success(CodeMsg.SUCCESS);
    }

}
