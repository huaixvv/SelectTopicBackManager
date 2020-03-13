package com.topicmanager.controller;


import com.topicmanager.fastdfs.FastDFSClientWrapper;
import com.topicmanager.result.CodeMsg;
import com.topicmanager.result.Result;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@Controller
@RequestMapping("/file")
public class FdfsController {

    @Autowired
    private FastDFSClientWrapper fastDFSClient;

    /**
     * 上传文件
     * @param multipartFile
     * @return path  访问地址
     * @throws IOException
     */
    @PostMapping("/upload")
    @ResponseBody
    public Result<String> uploadFile(@RequestParam("file")MultipartFile multipartFile) throws IOException {
        String path = fastDFSClient.uploadFile(multipartFile);
//        System.out.println(path);
        path = "http://192.168.1.3/" + path;
        return Result.success(path);
    }


    /**
     * 删除文件
     * @param filePath  要del的文件路径
     * @return
     */
    @GetMapping("/delete")
    @ResponseBody
    public Result<CodeMsg> deleteFile(@Param("filePath") String filePath){
//        System.out.println(filePath);
        fastDFSClient.deleteFile(filePath);
        return Result.success(CodeMsg.FILE_DEL_SUCCESS);
    }
}
