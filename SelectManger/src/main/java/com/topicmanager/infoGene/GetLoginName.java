package com.topicmanager.infoGene;

import java.util.ArrayList;

//随机登录名
public class GetLoginName {

    public static String _teacher(){
        String first = "huast";
        int num = 2000;
        ArrayList<String> teacher_login = new ArrayList<>();
        for (int i = 0; i < 19 ; i++) {
             teacher_login.add(first + num++);
        }
        return first;
    }

    public static void _student(){

    }

    public static void main(String[] args) {
        _teacher();
    }
}
