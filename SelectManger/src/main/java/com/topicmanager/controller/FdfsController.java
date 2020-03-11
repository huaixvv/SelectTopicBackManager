package com.topicmanager.controller;


import com.topicmanager.fastdfs.FastDFSClientWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@Controller
@RequestMapping("/file")
public class FdfsController {

    @Autowired
    private FastDFSClientWrapper fastDFSClient;

    @PostMapping("/upload")
    public void uploadFile(@RequestParam("file")MultipartFile multipartFile) throws IOException {
        System.out.println(multipartFile);
        String path = fastDFSClient.uploadFile(multipartFile);
        System.out.println(path);
    }

}
