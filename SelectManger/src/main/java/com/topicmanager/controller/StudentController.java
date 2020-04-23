package com.topicmanager.controller;


import com.topicmanager.pojo.Student;
import com.topicmanager.pojo.Thesis;
import com.topicmanager.result.Result;
import com.topicmanager.result.ThesisResult;
import com.topicmanager.service.StudentService;
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
@RequestMapping("/student")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @Autowired
    private ThesisService thesisService;

    @PostMapping("/login")
    @ResponseBody
    public Result<Student> login(@Param("loginName") String loginName){
        System.out.println("student");
        Student student = studentService.login(loginName);
        return  Result.success(student);
    }

    //获取所有课题
    @GetMapping("/getAllThesis")
    @ResponseBody
    public Result<List> getAllThesis(@Param("pageNum")int pageNum, @Param("pageSize") int pageSize){
        List<Thesis> allThesis = thesisService.getAllThesis(pageNum, pageSize);
        return Result.success(allThesis);
    }

    //获取课题总数
    @GetMapping("/getCountThesis")
    @ResponseBody
    public Result<Integer> getAllThesis(){
        Integer count = thesisService.getCountThesis();
        return Result.success(count);
    }

    //条件查询课题
    @GetMapping("/getThesis")
    @ResponseBody
    public Result<ThesisResult> getThesis(@Param("sisName")String sisName,
                                  @Param("sisTeacher")String sisTeacher,
                                  @Param("sisCollege") String sisCollege,
                                  @Param("pageNum")int pageNum,
                                  @Param("pageSize") int pageSize){
        ThesisResult thesises = thesisService.getThesis(sisName, sisTeacher, sisCollege, pageNum, pageSize);
//        System.out.println(thesises);
        return Result.success(thesises);
    }
}
