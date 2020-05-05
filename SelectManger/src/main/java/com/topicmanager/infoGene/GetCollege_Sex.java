package com.topicmanager.infoGene;


//随机学院  性别
public class GetCollege_Sex {

    public static String _college(){
        String[] colleges = {"计算机与电气工程学院","文史与法学学院","外国语学院","机械工程学院",
                            "经济与管理学院","艺术学院"};
        int n = (int) (Math.random()*(6-0)+0);
        return colleges[n];
    }

    public static String _sex(){
        String[] sexs = {"女","男"};
        int n = (int) (Math.random()*(2-0)+0);
        return sexs[n];
    }

    public static String _post(){
        String[] post = {"教研组长","教师","教授"};
        int n = (int) (Math.random()*(3-0)+0);
        return post[n];
    }


    public static void main(String[] args) {
        _college();
        _sex();
    }
}
