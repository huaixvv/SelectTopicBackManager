package com.topicmanager.pojo;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.persistence.Id;
import java.util.Date;

@Data
public class StudentThesis {

//    private String studentId;
//    private String loginName;
    private String studentName;
//    private String studentPwd;
    private String sex;
    private String college;       //学院
    private String speciality;    //专业
    private String classNumber;   //班级
    private String phone;
    private String email;


//    private String id;
//    private String stuNum;         //外键关联学生
//    private String sisNum;         //外键关联课题
    private String thesisName;
    private String status;          //当前状态

    @JSONField(format="yyyy-MM-dd  HH:mm:ss")
    @JsonFormat(pattern="yyyy-MM-dd  HH:mm:ss" ,  timezone="GMT+8")
    private Date createTime;        //创建时间

//    private String thesisCollege;    //所属学院
//    private String thesisType;       //类型
//    private String thesisFrom;       //课题来源
//    private String classroom;        //教研室
//    private String teacher;
//    private String model;             //选择类型  双选/学生自拟
//    private String allowSpecial;      //可选专业

//    @JSONField(format="yyyy-MM-dd  HH:mm:ss")
//    @JsonFormat(pattern="yyyy-MM-dd  HH:mm:ss" ,  timezone="GMT+8")
//    private Date thesisDate;
//    private String thesisDoc;         //附件
//    private String student;           //所属学生
//    private String thesisDesc;        //简述
}
