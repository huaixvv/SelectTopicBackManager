package com.topicmanager.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;


@Data
@Table(name = "thesis")
public class Thesis {

    @Id
    private String thesisId;
    private String thesisName;
    private String thesisCollege;    //所属学院
    private String thesisType;       //类型
    private String thesisFrom;       //课题来源
    private String classroom;        //教研室
    private String teacher;
    private String model;             //选择类型  双选
    private String allowSpecial;      //可选专业

    @JsonFormat(pattern="yyyy-MM-dd  HH:mm:ss" ,  timezone="GMT+8")
    private Date thesisDate;
    private String thesisDoc;         //附件
    private String studentOf;
    private String thesisDesc;      //简述
}
