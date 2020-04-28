package com.topicmanager.mapper;

import com.topicmanager.pojo.Student;
import com.topicmanager.pojo.StudentThesis;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultType;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

import java.util.ArrayList;
import java.util.List;

public interface StudentMapper extends Mapper<Student> {

    @Select("SELECT s.student_name, s.sex, s.college, s.speciality, s.class_number, s.phone, s.email, o.thesis_name, o.`status`, o.create_time\n" +
            "from student s\n" +
            "INNER JOIN orderinfo o on s.student_id = o.stu_num \n" +
            "and o.teacher = #{ teacherName } \n" +
            "and o.status != '未通过'")
    @ResultType(List.class)
    List<StudentThesis> getStudentOfTeacher(@Param("teacherName") String teacherName);
}
