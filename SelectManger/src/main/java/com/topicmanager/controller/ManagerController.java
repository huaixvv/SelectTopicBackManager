package com.topicmanager.controller;

import com.alibaba.fastjson.JSON;
import com.topicmanager.pojo.Manager;
import com.topicmanager.pojo.Student;
import com.topicmanager.pojo.Teacher;
import com.topicmanager.result.CodeMsg;
import com.topicmanager.result.Result;
import com.topicmanager.service.ManagerService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/manager")
public class ManagerController {

    @Autowired
    private ManagerService managerService;


    @PostMapping("/login")
    @ResponseBody
    public Result<Manager> login(@Param("loginName") String loginName, @Param("pwd") String pwd){
        Manager manager = managerService.login(loginName);
        if (manager == null) return Result.error(CodeMsg.USER_NOT_FOUND);
        if(!pwd.equals(manager.getManagerPwd())) return Result.error(CodeMsg.PWD_WRONG);
        return  Result.success(manager);
    }

    //获取管理员信息
    @PostMapping("/managerinfo")
    @ResponseBody
    public Result<Manager> getInfo(@Param("managerId") String managerId){
        Manager manager = managerService.getInfo(managerId);
        return  Result.success(manager);
    }

    //修改管理员信息
    @PostMapping("/editmanager")
    @ResponseBody
    public Result<CodeMsg> editManager(@Param("manager") String manager){
        Manager m = JSON.parseObject(manager, Manager.class);
        Integer res = managerService.editManager(m);
        if(res != 1) return Result.error(CodeMsg.FAILED);
        return  Result.success(CodeMsg.SUCCESS);
    }

    //获取所有教师信息
    @GetMapping("/allteachers")
    @ResponseBody
    public Result<List> getAllTeachers(){
        List<Teacher> teachers = managerService.getAllteachers();
//        System.out.println(teachers);
        return Result.success(teachers);
    }


    //获取所有学生信息
    @GetMapping("/allstudents")
    @ResponseBody
    public Result<List> getAllStudents(){
        List<Student> students = managerService.getAllStudents();
        System.out.println(students);
        return Result.success(students);
    }

    //删除教师
    @PostMapping("/delteacher")
    @ResponseBody
    public Result<CodeMsg> delTeacher(@Param("teacher") String teacher){
        Teacher t = JSON.parseObject(teacher, Teacher.class);
        Integer res = managerService.delTeachers(t);
        if (res != 1)  return Result.error(CodeMsg.FAILED);
        return Result.success(CodeMsg.SUCCESS);
    }


    //删除学生
    @PostMapping("/delstudent")
    @ResponseBody
    public Result<CodeMsg> delStudent(@Param("student") String student){
        Student s = JSON.parseObject(student, Student.class);
        Integer res = managerService.delStudent(s);
        if (res != 1)  return Result.error(CodeMsg.FAILED);
        return Result.success(CodeMsg.SUCCESS);
    }
}
