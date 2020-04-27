package com.topicmanager.service;

import com.topicmanager.mapper.OrderInfoMapper;
import com.topicmanager.pojo.Orderinfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderInfoService {

    @Autowired
    private OrderInfoMapper orderInfoMapper;


    public Orderinfo getOrderByStuNum(String studentId) {
        Orderinfo o = new Orderinfo();
        o.setStuNum(studentId);
        return orderInfoMapper.selectOne(o);
    }
}
