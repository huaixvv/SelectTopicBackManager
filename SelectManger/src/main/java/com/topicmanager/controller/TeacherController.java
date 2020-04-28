package com.topicmanager.controller;


import com.alibaba.fastjson.JSON;
import com.sun.org.apache.regexp.internal.RE;
import com.topicmanager.pojo.Orderinfo;
import com.topicmanager.pojo.StudentThesis;
import com.topicmanager.pojo.Teacher;
import com.topicmanager.pojo.Thesis;
import com.topicmanager.result.CodeMsg;
import com.topicmanager.result.Result;
import com.topicmanager.service.OrderInfoService;
import com.topicmanager.service.StudentService;
import com.topicmanager.service.TeacherService;
import com.topicmanager.service.ThesisService;
import com.topicmanager.vo.ThesisVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.ClassGenerator;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/teacher")
public class TeacherController {


    @Autowired
    private TeacherService teacherService;

    @Autowired
    private ThesisService thesisService;

    @Autowired
    private OrderInfoService orderInfoService;

    @Autowired
    private StudentService studentService;

    @PostMapping("/login")
    @ResponseBody
    public Result<Teacher> login(@Param("loginName") String loginName, @Param("teacherPwd") String teacherPwd){
        Teacher teacher = teacherService.login(loginName);
        System.out.println(teacher);
        if (teacher == null) return Result.error(CodeMsg.USER_NOT_FOUND);
        if(!teacherPwd.equals(teacher.getTeacherPwd())) return Result.error(CodeMsg.PWD_WRONG);
        return  Result.success(teacher);
    }

    //获取教师信息
    @GetMapping("/getinfo")
    @ResponseBody
    public Result<Teacher> getTeacherById(@Param("teacherId") String teacherId){
        System.out.println(teacherId);
        Teacher teacher = teacherService.getTeacherById(teacherId);
        System.out.println(teacher);
        if (teacher == null) return Result.error(CodeMsg.USER_NOT_FOUND);
        return  Result.success(teacher);
    }


    //获取教师信息
    @GetMapping("/teacherByName")
    @ResponseBody
    public Result<Teacher> getTeacherByName(@Param("teacherName") String teacherName){
        Teacher teacher = teacherService.getTeacherByName(teacherName);
        System.out.println(teacher);
        if (teacher == null) return Result.error(CodeMsg.USER_NOT_FOUND);
        return  Result.success(teacher);
    }

    //获取教师名下课题
    @GetMapping("/teacherThesis")
    @ResponseBody
    public Result<List<Thesis>> getThesisByTeacherName(@Param("teacherName") String teacherName){
        List<Thesis> thesislist = thesisService.getThesisByTeacherName(teacherName);
        //System.out.println(thesislist);
        return  Result.success(thesislist);
    }

    //删除课题
    @GetMapping("/delThesis")
    @ResponseBody
    public Result<CodeMsg> deleteById(@Param("thesisId") String thesisId,
                                      @Param("thesisName") String thesisName){
        Integer res = thesisService.deleteById(thesisId, thesisName);
        if (res == 0)  return Result.error(CodeMsg.FAILED);
        return Result.success(CodeMsg.SUCCESS);
    }

    //添加课题
    @PostMapping("/addThesis")
    @ResponseBody
    public Result<CodeMsg> addThesis(@Param("thesisVo") String thesisVo){
        ThesisVo thesisVo1 = JSON.parseObject(thesisVo, ThesisVo.class);
        System.out.println(thesisVo1);
        Integer res = thesisService.addThesis(thesisVo1);
        if (res != 1)  return Result.error(CodeMsg.FAILED);
        return Result.success(CodeMsg.SUCCESS);
    }

    //获取课题  thesisId
    @GetMapping("/getThesis")
    @ResponseBody
    public Result<Thesis> getThesisById(@Param("thesisId") String thesisId){
        // System.out.println(thesisId);
        Thesis thesis = thesisService.getThesisById(thesisId);
        return Result.success(thesis);
    }


    //获取学生已经选择的课题
    @GetMapping("/getorder")
    @ResponseBody
    public Result<List> getOrderByTeacherName(@Param("teacherName") String teacherName){
        List<Orderinfo> orderInfos = orderInfoService.getOrderByTeacherName(teacherName);
        return Result.success(orderInfos);
    }


    //编辑课题
    @PostMapping("/editThesis")
    @ResponseBody
    public Result<CodeMsg> editThesis(@Param("thesisVo") String thesisVo, @Param("thesisId") String thesisId){
        ThesisVo thesisVo1 = JSON.parseObject(thesisVo, ThesisVo.class);
        // System.out.println(thesisVo1);
        Integer res = thesisService.editThesis(thesisVo1, thesisId);
        if (res != 1)  return Result.error(CodeMsg.FAILED);
        return Result.success(CodeMsg.SUCCESS);
    }

    //修改教师信息
    @PostMapping("/editinfo")
    @ResponseBody
    public Result<CodeMsg> editTeacherInfo(@Param("teacher") String teacher){
        Teacher t = JSON.parseObject(teacher, Teacher.class);
        if (t.getTeacherId().equals("")){
            Integer res = teacherService.addTeacher(t);
            if (res != 1)  return Result.error(CodeMsg.FAILED);
        }else{
            Integer res = teacherService.editTeacherInfo(t);
            if (res != 1)  return Result.error(CodeMsg.FAILED);
        }
        return Result.success(CodeMsg.SUCCESS);
    }

    //通过选题
    @PostMapping("/confirm")
    @ResponseBody
    public Result<CodeMsg> confirm(@Param("order") String order){
        Orderinfo orderinfo = JSON.parseObject(order, Orderinfo.class);
        Integer res = orderInfoService.confirm(orderinfo);
        if (res != 1)  return Result.error(CodeMsg.FAILED);
        return Result.success(CodeMsg.SUCCESS);
    }


    //拒绝选题
    @PostMapping("/refuse")
    @ResponseBody
    public Result<CodeMsg> refuse(@Param("order") String order){
        Orderinfo orderinfo = JSON.parseObject(order, Orderinfo.class);
        Integer res = orderInfoService.refuse(orderinfo);
        if (res != 1)  return Result.error(CodeMsg.FAILED);
        return Result.success(CodeMsg.SUCCESS);
    }

    //获取名下学生信息
    @GetMapping("/getstudent")
    @ResponseBody
    public Result<List> getStudent(@Param("teacherName") String teacherName){
        List<StudentThesis> students = teacherService.getStudent(teacherName);
        return Result.success(students);
    }
}
