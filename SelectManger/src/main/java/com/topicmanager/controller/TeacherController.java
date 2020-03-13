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

    @GetMapping("/getinfo")
    @ResponseBody
    public Result<Teacher> getTeacherById(@Param("teacherId") String teacherId){
        System.out.println(teacherId);
        Teacher teacher = teacherService.getTeacherById(teacherId);
        System.out.println(teacher);
        if (teacher == null) return Result.error(CodeMsg.USER_NOT_FOUND);
        return  Result.success(teacher);
    }

    @GetMapping("/teacherThesis")
    @ResponseBody
    public Result<List<Thesis>> getThesisByTeacherName(@Param("teacherName") String teacherName){
        List<Thesis> thesislist = thesisService.getThesisByTeacherName(teacherName);
        //System.out.println(thesislist);
        return  Result.success(thesislist);
    }

    @GetMapping("/delThesis")
    @ResponseBody
    public Result<CodeMsg> deleteById(@Param("thesisId") String thesisId){
        Integer res = thesisService.deleteById(thesisId);
        if (res != 1)  return Result.error(CodeMsg.FAILED);
        return Result.success(CodeMsg.SUCCESS);
    }

    @PostMapping("/addThesis")
    @ResponseBody
    public Result<CodeMsg> addThesis(@Param("thesisVo") String thesisVo){
        ThesisVo thesisVo1 = JSON.parseObject(thesisVo, ThesisVo.class);
        System.out.println(thesisVo1);
        Integer res = thesisService.addThesis(thesisVo1);
        if (res != 1)  return Result.error(CodeMsg.FAILED);
        return Result.success(CodeMsg.SUCCESS);
    }


    @GetMapping("/getThesis")
    @ResponseBody
    public Result<Thesis> getThesisById(@Param("thesisId") String thesisId){
        // System.out.println(thesisId);
        Thesis thesis = thesisService.getThesisById(thesisId);
        return Result.success(thesis);
    }


    @PostMapping("/editThesis")
    @ResponseBody
    public Result<CodeMsg> editThesis(@Param("thesisVo") String thesisVo, @Param("thesisId") String thesisId){
        ThesisVo thesisVo1 = JSON.parseObject(thesisVo, ThesisVo.class);
        // System.out.println(thesisVo1);
        Integer res = thesisService.editThesis(thesisVo1, thesisId);
        if (res != 1)  return Result.error(CodeMsg.FAILED);
        return Result.success(CodeMsg.SUCCESS);
    }

    @PostMapping("/editinfo")
    @ResponseBody
    public Result<CodeMsg> editTeacherInfo(@Param("teacher") String teacher){
        Teacher t = JSON.parseObject(teacher, Teacher.class);
        //  System.out.println(t);
        Integer res = teacherService.editTeacherInfo(t);
        if (res != 1)  return Result.error(CodeMsg.FAILED);
        return Result.success(CodeMsg.SUCCESS);
    }
}
