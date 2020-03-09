package com.topicmanager.mapper;

import com.topicmanager.pojo.Student;
import com.topicmanager.pojo.Teacher;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

public interface TeacherMapper extends Mapper<Teacher> {

    @Select("select * from teacher where loginName = #{ loginName }")
    Teacher login(@Param("loginName") String loginName);
}
