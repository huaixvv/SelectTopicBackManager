package com.topicmanager.result;

public class CodeMsg {

    private int code;
    private String msg;

    //通用返回码
    public static CodeMsg SUCCESS = new CodeMsg(2000, "success");
    public static CodeMsg FAILED =  new CodeMsg(5000, "failed");

    public static CodeMsg FILE_UP_SUCCESS = new CodeMsg(2001, "文件上传成功！");
    public static CodeMsg FILE_DEL_SUCCESS = new CodeMsg(2002, "文件删除成功！");

    public static CodeMsg USER_NOT_FOUND =  new CodeMsg(4000, "用户不存在，请校正登录名和密码");
    public static CodeMsg PWD_WRONG =  new CodeMsg(4001, "用户密码错误，请重新输入密码");


    public static CodeMsg CHOOSE_WRONG =  new CodeMsg(6000, "已经成功选题，或请等待教师审核确认");



    private CodeMsg(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public CodeMsg fillArgs(Object... args){
        int code = this.code;
        String message = String.format(this.msg, args);
        return new CodeMsg(code, message);
    }


    public int getCode() {
        return code;
    }


    public String getMsg() {
        return msg;
    }
}
