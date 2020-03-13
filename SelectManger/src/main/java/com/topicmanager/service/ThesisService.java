package com.topicmanager.service;


import com.topicmanager.mapper.TeacherMapper;
import com.topicmanager.mapper.ThesisMapper;
import com.topicmanager.pojo.Teacher;
import com.topicmanager.pojo.Thesis;
import com.topicmanager.utils.IDgenerator;
import com.topicmanager.vo.ThesisVo;
import org.mockito.cglib.core.ClassGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ThesisService {


    @Autowired
    private ThesisMapper thesisMapper;

    @Autowired
    private TeacherMapper teacherMapper;

    //教师姓名获取教师课题
    public List<Thesis> getThesisByTeacherName(String teacherName){
        Thesis thesis = new Thesis();
        thesis.setTeacher(teacherName);
        List<Thesis> thesises = thesisMapper.select(thesis);
//        System.out.println(thesises);
        return thesises;
    }

    //通过id删除课题
    public Integer deleteById(String thesisId){
        Thesis t = new Thesis();
        t.setThesisId(thesisId);
        return thesisMapper.deleteByPrimaryKey(t);
    }

    //add thesis
    public Integer addThesis(ThesisVo thesisVo){
        Thesis thesis = new Thesis();
        thesis.setThesisId(IDgenerator.generatorThesisId());
        thesis.setAllowSpecial(thesisVo.getAllowSpecial());
        thesis.setThesisName(thesisVo.getThesisName());
        thesis.setThesisCollege(thesisVo.getThesisCollege());
        thesis.setThesisType(thesisVo.getThesisType());
        thesis.setThesisFrom(thesisVo.getThesisFrom());
        thesis.setTeacher(thesisVo.getTeacher());
        thesis.setModel(thesisVo.getModel());
        Date date = new Date();
        thesis.setThesisDate(date);
        thesis.setThesisDoc(thesisVo.getFilePath());
        thesis.setThesisDesc(thesisVo.getThesisDesc());
        thesis.setStudentOf("zzx");
        return thesisMapper.insert(thesis);
    }

    //select thesis by id
    public Thesis getThesisById(String thesisId) {
        Thesis t = new Thesis();
        t.setThesisId(thesisId);
//        System.out.println("getbyid");
//        System.out.println(t);
        return thesisMapper.selectByPrimaryKey(t);
    }


    //根据id修改课题
    public Integer editThesis(ThesisVo thesisVo, String thesisId) {
        Thesis thesis = new Thesis();
        thesis.setThesisId(thesisId);
        thesis.setAllowSpecial(thesisVo.getAllowSpecial());
        thesis.setThesisName(thesisVo.getThesisName());
        thesis.setThesisCollege(thesisVo.getThesisCollege());
        thesis.setThesisType(thesisVo.getThesisType());
        thesis.setThesisFrom(thesisVo.getThesisFrom());
        thesis.setTeacher(thesisVo.getTeacher());
        thesis.setModel(thesisVo.getModel());
        Date date = new Date();
        thesis.setThesisDate(date);
        thesis.setThesisDoc(thesisVo.getFilePath());
        thesis.setThesisDesc(thesisVo.getThesisDesc());
        return thesisMapper.updateByPrimaryKey(thesis);
    }
}
