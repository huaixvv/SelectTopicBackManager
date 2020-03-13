package com.topicmanager.utils;

import org.springframework.cglib.core.ClassGenerator;

import java.util.*;

public class IDgenerator {


    public static String getUUID(){
        UUID uuid = UUID.randomUUID() ;
        String str = uuid.toString();
        String id = str.replace("-","").substring(0,6);
        System.out.println(id);
        return id;
    }


//    随机生成课题id
    public static String generatorThesisId(){
        return "sis-" + getUUID();
    }

//    随机生成学生id
    public static String generatorStuId(){
        return "stu-" + getUUID();
    }

//    随机生成教师id
    public static String generatorTeaId(){
        return "tea-" + getUUID();
    }


//    public static void main(String[] args) {
//        Map map = new HashMap<>();
//        for (int i = 0; i < 1000; i++) {
//            String id = generatorThesisId();
//            if (map.containsKey(id)){
//                System.out.println( id + ": 重复了");
//                System.out.println(i);
//                break;
//            }else {
//               map.put(id, 1);
//            }
//        }
//        System.out.println(map);
//    }
}

