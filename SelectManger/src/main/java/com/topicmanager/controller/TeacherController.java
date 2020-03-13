package com.topicmanager.controller;


import com.alibaba.fastjson.JSON;
import com.topicmanager.pojo.Teacher;
import com.topicmanager.pojo.Thesis;
import com.topicmanager.result.CodeMsg;
import com.topicmanager.result.Result;
import com.topicmanager.service.TeacherService;
import com.topicmanager.service.ThesisService;
import com.topicmanager.vo.ThesisVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.ClassGenerator;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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
    public Result<List<Thesis>> getThesisByTeacherName(@Param("teacherName") String teacherName){
        List<Thesis> thesislist = thesisService.getThesisByTeacherName(teacherName);
//        System.out.println(thesislist);
        return  Result.success(thesislist);
    }

    @GetMapping("/delThesis")
    @ResponseBody
    public Result<CodeMsg> deleteById(@Param("thesisId") String thesisId){
        thesisService.deleteById(thesisId);
        return Result.success(CodeMsg.SUCCESS);
    }

    @PostMapping("/addThesis")
    @ResponseBody
    public void addThesis(@Param("thesisVo") String thesisVo){
        ThesisVo thesisVo1 = JSON.parseObject(thesisVo, ThesisVo.class);
        System.out.println(thesisVo1);
        thesisService.addThesis(thesisVo1);
    }
}
