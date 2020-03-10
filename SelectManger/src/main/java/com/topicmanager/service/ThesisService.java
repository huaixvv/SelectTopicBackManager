package com.topicmanager.service;


import com.topicmanager.mapper.ThesisMapper;
import com.topicmanager.pojo.Thesis;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ThesisService {


    @Autowired
    private ThesisMapper thesisMapper;

    //教师id获取教师课题
    public List<Thesis> getThesisByTeacherId(String teacherId){
        return  thesisMapper.getThesisByTeacherId(teacherId);
    }

    //delete by id
    public  int deleteById(String thesisId){
        int r = thesisMapper.delThesisById(thesisId);
        System.out.println(r);
        return r;
    }
}
