package com.topicmanager.vo;

import lombok.Data;

import java.sql.Date;

@Data
public class ThesisVo {

    private String thesisName;
    private String teacher;
    private String thesisCollege;    //所属学院
    private String allowSpecial;      //可选专业
    private String classroom;        //教研室
    private String thesisType;       //类型
    private String thesisFrom;       //课题来源
    private String thesisDesc;        //简述
    private String model;             //选择类型  双选
    private String filePath;          //附件
}
