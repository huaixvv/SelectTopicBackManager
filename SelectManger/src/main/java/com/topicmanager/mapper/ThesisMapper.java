package com.topicmanager.mapper;


import com.topicmanager.pojo.Thesis;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.base.delete.DeleteByPrimaryKeyMapper;
import tk.mybatis.spring.annotation.MapperScan;

import java.util.List;

@MapperScan
public interface ThesisMapper extends Mapper<Thesis> {

    @Select("select * from thesis where thesis.teacherOf = #{ teacherId }")
    List<Thesis> getThesisByTeacherId(@Param("teacherId") String teacherId);

    @Delete("delete from thesis where thesisId = #{ thesisId }")
    int delThesisById(@Param("thesisId") String thesisId);


    @Update("update thesis set ischoose = null where thesis_id = #{ thesisId }")
    void changeChooseToNULL(String thesisId);

    @Update("update thesis set ischoose = 'yes' where thesis_id = #{ thesisId }")
    void changeChooseToYES(String thesisId);
}
