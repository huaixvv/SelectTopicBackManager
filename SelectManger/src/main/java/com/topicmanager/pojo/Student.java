package com.topicmanager.pojo;

import lombok.Data;

import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Table(name = "student")
public class Student {

    @Id
    private Integer studentId;
    private String loginName;
    private String studentName;
    private String studentPwd;
    private String sex;
    private String college;       //学院
    private String speciality;    //专业
    private String classNumber;   //班级
    private String phone;
    private String email;

}
