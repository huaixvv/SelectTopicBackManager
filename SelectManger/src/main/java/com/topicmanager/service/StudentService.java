package com.topicmanager.service;


import com.topicmanager.mapper.OrderInfoMapper;
import com.topicmanager.mapper.StudentMapper;
import com.topicmanager.pojo.Orderinfo;
import com.topicmanager.pojo.Student;
import com.topicmanager.pojo.Thesis;
import com.topicmanager.utils.IDgenerator;
import com.topicmanager.utils.ThesisStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service
public class StudentService {

    @Autowired
    private StudentMapper studentMapper;

    @Autowired
    private OrderInfoMapper orderInfoMapper;

    //login
    public Student login(String loginName){
        Student s = new Student();
        s.setLoginName(loginName);
        Student student = studentMapper.selectOne(s);
//        System.out.println(student);
        return student;
    }


    public Student getStudent(String studentId) {
        Student s = new Student();
        s.setStudentId(studentId);
        Student student = studentMapper.selectByPrimaryKey(s);
        return student;
    }

    public Integer editStudent(Student s) {
        return studentMapper.updateByPrimaryKey(s);
    }

    public Integer chooseThesis(Thesis thesis, String studentId) {
        Integer isOk = isChoose(studentId);
        if(isOk == 1){
            Orderinfo orderinfo = setOrderInfo(thesis, studentId);
//            System.out.println(orderinfo);
            return orderInfoMapper.insert(orderinfo);
        }else{
            return 0;   //选题失败
        }
    }


    @Transactional
    public Integer isChoose(String studentId){
        Orderinfo orderinfo = new Orderinfo();
        orderinfo.setStuNum(studentId);
//        System.out.println(orderinfo);
        Orderinfo o = orderInfoMapper.selectOne(orderinfo);
        if (o == null || o.getStatus().equals(ThesisStatus.REJECT)){
            orderInfoMapper.delete(o);
            return 1;        //未选/审核未通过 = 可以选题
        }else{
            System.out.println("buneng");
            return 0;        //不能选题
        }
    }

    public Orderinfo setOrderInfo(Thesis thesis, String studentId){
        Orderinfo orderinfo = new Orderinfo();
        orderinfo.setId(IDgenerator.generatorOrderId());
        orderinfo.setStuNum(studentId);
        orderinfo.setSisNum(thesis.getThesisId());
        orderinfo.setStatus(ThesisStatus.PENDING);
        orderinfo.setClassroom(thesis.getClassroom());
        orderinfo.setAllowSpecial(thesis.getAllowSpecial());
        orderinfo.setModel(thesis.getModel());
        orderinfo.setStudent(thesis.getStudent());
        orderinfo.setTeacher(thesis.getTeacher());
        orderinfo.setThesisCollege(thesis.getThesisCollege());
        orderinfo.setThesisDate(thesis.getThesisDate());
        orderinfo.setThesisDoc(thesis.getThesisDoc());
        orderinfo.setThesisFrom(thesis.getThesisFrom());
        orderinfo.setThesisName(thesis.getThesisName());
        orderinfo.setThesisType(thesis.getThesisType());
        orderinfo.setThesisDesc(thesis.getThesisDesc());
        orderinfo.setCreateTime(new Date());
        return orderinfo;
    }

}
