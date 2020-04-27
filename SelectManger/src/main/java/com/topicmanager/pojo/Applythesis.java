package com.topicmanager.pojo;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;


@Data
@Table(name = "applythesis")
public class Applythesis {

    @Id
    private String thesisId;
    private String thesisName;
    private String thesisCollege;    //所属学院
    private String thesisType;       //类型
    private String thesisFrom;       //课题来源
    private String studentName;      //申请课题的学生名称

    @JSONField(format="yyyy-MM-dd  HH:mm:ss")
    @JsonFormat(pattern="yyyy-MM-dd  HH:mm:ss" ,  timezone="GMT+8")
    private Date thesisDate;
    private String thesisDoc;         //附件
    private String teacher;           //指定教师
    private String thesisStatus;      //审核状态
    private String thesisDesc;        //简述
}
