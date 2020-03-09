package com.topicmanager.pojo;

import lombok.Data;

import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Table(name = "teacher")
public class Teacher {

    @Id
    private Integer teacherId;
    private String loginName;
    private String teacherName;
    private String teacherPwd;
    private String college;      //教师所属学院
    private String sex;
    private String phone;
    private String email;
    private String teacherPost;   //教师职务
}
