package com.topicmanager.service;


import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.topicmanager.mapper.ApplyThesisMapper;
import com.topicmanager.mapper.OrderInfoMapper;
import com.topicmanager.mapper.TeacherMapper;
import com.topicmanager.mapper.ThesisMapper;
import com.topicmanager.pojo.Applythesis;
import com.topicmanager.pojo.Orderinfo;
import com.topicmanager.pojo.Teacher;
import com.topicmanager.pojo.Thesis;
import com.topicmanager.result.ThesisResult;
import com.topicmanager.utils.IDgenerator;
import com.topicmanager.utils.ThesisStatus;
import com.topicmanager.vo.ThesisVo;
import org.apache.commons.collections.bag.SynchronizedSortedBag;
import org.mockito.cglib.core.ClassGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.annotation.Order;
import tk.mybatis.mapper.entity.Example;

import java.beans.Transient;
import java.util.Date;
import java.util.List;

@Service
public class ThesisService {


    @Autowired
    private ThesisMapper thesisMapper;

    @Autowired
    private TeacherMapper teacherMapper;

    @Autowired
    private ApplyThesisMapper applyThesisMapper;

    @Autowired
    private OrderInfoMapper orderInfoMapper;

    @Autowired
    private StudentService studentService;

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

    //教师添加课题
    @Transactional
    public Integer addThesis(ThesisVo thesisVo){
        Thesis thesis = thesisVo_thesis(thesisVo);
        return thesisMapper.insert(thesis);
    }

    //select thesis by id
    public Thesis getThesisById(String thesisId) {
        Thesis t = new Thesis();
        t.setThesisId(thesisId);
        return thesisMapper.selectByPrimaryKey(t);
    }


    //teacher 根据id修改课题
    @Transactional
    public Integer editThesis(ThesisVo thesisVo, String thesisId) {
        Thesis thesis = thesisVo_thesis(thesisVo);
        thesis.setThesisId(thesisId);
        return thesisMapper.updateByPrimaryKey(thesis);
    }

    //获取所有课题   分页查询
    public List<Thesis> getAllThesis(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        List<Thesis> thesisList = thesisMapper.selectAll();
        PageInfo<Thesis> info = new PageInfo<>(thesisList);
        return info.getList();
    }

    //获取课题总数
    public Integer getCountThesis() {
        return  thesisMapper.selectCount(new Thesis());
    }

    //模糊条件查询
    public ThesisResult getThesis(String sisName, String sisTeacher, String sisCollege, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        Example example = new Example(Thesis.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andLike("thesisName", "%" + sisName + "%")
                .andLike("teacher", "%" + sisTeacher + "%")
                .andLike("thesisCollege", "%" + sisCollege + "%")
                .andCondition("student is null");
        List<Thesis> list = thesisMapper.selectByExample(example);
        int count = thesisMapper.selectCountByExample(example);
        PageInfo<Thesis> info = new PageInfo<>(list);
        ThesisResult result = new ThesisResult(info.getList(), count);
        return result;
    }

    //学生申报课题
    @Transactional
    public Integer applyThesis(ThesisVo thesisVo, String studentId) {
        Integer isOk = studentService.isChoose(studentId);    //判断是否已经选题
        if(isOk == 1){
            Thesis thesis = thesisVo_thesis(thesisVo);
            Applythesis applythesis = thesisVo_applyThesis(thesisVo);
            Orderinfo orderinfo = thesisVo_Orderinfo(thesisVo, studentId);
            orderinfo.setSisNum(thesis.getThesisId());
            thesisMapper.insert(thesis);
            applyThesisMapper.insert(applythesis);
            return orderInfoMapper.insert(orderinfo);
        }else{
            return 0;   //选题失败
        }
    }



    public Thesis thesisVo_thesis(ThesisVo thesisVo){
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
        thesis.setStudent(thesisVo.getStudent());
        return thesis;
    }

    public Applythesis thesisVo_applyThesis(ThesisVo thesisVo) {
        Applythesis applythesis = new Applythesis();
        applythesis.setThesisId(IDgenerator.generatorStuSisId());
        applythesis.setThesisName(thesisVo.getThesisName());
        applythesis.setStudentName(thesisVo.getStudent());
        applythesis.setTeacher(thesisVo.getTeacher());
        applythesis.setThesisCollege(thesisVo.getThesisCollege());
        applythesis.setThesisDoc(thesisVo.getFilePath());
        applythesis.setThesisFrom(thesisVo.getThesisFrom());
        applythesis.setThesisStatus(ThesisStatus.PENDING);      //待审核状态
        applythesis.setThesisType(thesisVo.getThesisType());
        applythesis.setThesisDesc(thesisVo.getThesisDesc());
        Date date = new Date();
        applythesis.setThesisDate(date);
        return applythesis;
    }

    public Orderinfo thesisVo_Orderinfo(ThesisVo thesisVo, String studentId){
        Orderinfo o = new Orderinfo();
        o.setId(IDgenerator.generatorOrderId());
        o.setStuNum(studentId);
        o.setThesisName(thesisVo.getThesisName());
        o.setThesisCollege(thesisVo.getThesisCollege());
        o.setThesisType(thesisVo.getThesisType());
        o.setThesisFrom(thesisVo.getThesisFrom());
        o.setClassroom(thesisVo.getClassroom());
        o.setTeacher(thesisVo.getTeacher());
        o.setModel(thesisVo.getModel());
        o.setAllowSpecial(thesisVo.getAllowSpecial());
        o.setThesisDate(new Date());
        o.setThesisDoc(thesisVo.getFilePath());
        o.setThesisDesc(thesisVo.getThesisDesc());
        o.setStudent(thesisVo.getStudent());
        o.setCreateTime(new Date());
        o.setStatus(ThesisStatus.PENDING);
        return o;
    }

}
