package com.topicmanager.controller;

import com.alibaba.fastjson.JSON;
import com.topicmanager.pojo.Orderinfo;
import com.topicmanager.pojo.Student;
import com.topicmanager.pojo.Thesis;
import com.topicmanager.result.CodeMsg;
import com.topicmanager.result.Result;
import com.topicmanager.result.ThesisResult;
import com.topicmanager.service.OrderInfoService;
import com.topicmanager.service.StudentService;
import com.topicmanager.service.ThesisService;
import com.topicmanager.vo.ThesisVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import tk.mybatis.mapper.annotation.Order;

import java.util.List;

@Controller
@RequestMapping("/student")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @Autowired
    private ThesisService thesisService;

    @Autowired
    private OrderInfoService orderInfoService;

    @PostMapping("/login")
    @ResponseBody
    public Result<Student> login(@Param("loginName") String loginName){
//        System.out.println("student");
        Student student = studentService.login(loginName);
        return  Result.success(student);
    }


    //获取已选课题
    @GetMapping("/choosedthesis")
    @ResponseBody
    public Result<Orderinfo> getChoosedThesis(@Param("studentId") String studentId){
        System.out.println(studentId);
        Orderinfo orderinfo = orderInfoService.getOrderByStuNum(studentId);
        return Result.success(orderinfo);
    }


    //获取学生信息
    @GetMapping("/getinfo")
    @ResponseBody
    public Result<Student> getStudent(@Param("studentId")String studentId){
        Student student = studentService.getStudent(studentId);
        return Result.success(student);
    }

    //修改学生信息
    @PostMapping("/editinfo")
    @ResponseBody
    public Result<CodeMsg> editStudent(@Param("student")String student){
        Student s = JSON.parseObject(student, Student.class);
        if(s.getStudentId().equals("")){
            Integer res = studentService.addStudent(s);
            if (res != 1)  return Result.error(CodeMsg.FAILED);
        }else{
            Integer res = studentService.editStudent(s);
            if (res != 1)  return Result.error(CodeMsg.FAILED);
        }
        return Result.success(CodeMsg.SUCCESS);
    }


    //获取所有未选课题
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

    //申报课题
    @PostMapping("/applyThesis")
    @ResponseBody
    public Result<CodeMsg> applyThesis(@Param("thesisVo")String thesisVo,
                                       @Param("studentId")String studentId
    ){
        System.out.println(thesisVo);
        ThesisVo thesisVo1 = JSON.parseObject(thesisVo, ThesisVo.class);
        Integer res = thesisService.applyThesis(thesisVo1, studentId);
        if(res == 0) return Result.error(CodeMsg.FAILED);
        return Result.success(CodeMsg.SUCCESS);
    }

    //选择课题
    @PostMapping("/choose")
    @ResponseBody
    public Result<CodeMsg> chooseThesis(@Param("thesis")String thesis,
                                        @Param("studentId")String studentId,
                                        @Param("studentName")String studentName){
        Thesis t = JSON.parseObject(thesis, Thesis.class);
        Integer res = studentService.chooseThesis(t, studentId, studentName);
        if(res!=1) return Result.error(CodeMsg.CHOOSE_WRONG);
        return Result.success(CodeMsg.SUCCESS);
    }
}
