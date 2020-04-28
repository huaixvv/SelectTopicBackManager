package com.topicmanager.service;

import com.topicmanager.mapper.OrderInfoMapper;
import com.topicmanager.mapper.ThesisMapper;
import com.topicmanager.pojo.Orderinfo;
import com.topicmanager.pojo.Thesis;
import com.topicmanager.utils.ThesisStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class OrderInfoService {

    @Autowired
    private OrderInfoMapper orderInfoMapper;

    @Autowired
    private ThesisMapper thesisMapper;

    public Orderinfo getOrderByStuNum(String studentId) {
        Orderinfo o = new Orderinfo();
        o.setStuNum(studentId);
        return orderInfoMapper.selectOne(o);
    }

    public List<Orderinfo> getOrderByTeacherName(String teacherName) {
        Orderinfo o = new Orderinfo();
        o.setTeacher(teacherName);
        List<Orderinfo> orderinfos = orderInfoMapper.select(o);
        return orderinfos;
    }

    public Integer confirm(Orderinfo orderinfo) {
        orderinfo.setStatus(ThesisStatus.SUCESS);
        int i = orderInfoMapper.updateByPrimaryKey(orderinfo);
        return i;
    }

    @Transactional
    public Integer refuse(Orderinfo orderinfo) {
//        Thesis thesis = new Thesis();
//        thesis.setIschoose(null);
//        thesis.setThesisId(orderinfo.getSisNum());
        thesisMapper.changeChooseToNULL(orderinfo.getSisNum());
        orderinfo.setStatus(ThesisStatus.REJECT);
        int i = orderInfoMapper.updateByPrimaryKey(orderinfo);
        return i;
    }
}
