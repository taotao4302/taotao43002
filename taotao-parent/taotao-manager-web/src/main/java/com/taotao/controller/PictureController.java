package com.taotao.controller;

import com.taotao.utils.FastDFSClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import com.alibaba.fastjson.JSON;
import java.util.HashMap;
import java.util.Map;

/**
 * @author sqt
 */
/*
@Value("${IMAGE_SERVER_URL}")是为了注入我们在配置文件resource.properties中配置的图片访问前缀。
 */
@Controller
public class PictureController {
    @Value("${IMAGE_SERVER_URL}")
    private String IMAGE_SERVER_URL;

    /*
    common.js var TT 指定上传文件请求的url
     */
    @RequestMapping("/pic/upload")
    @ResponseBody
    public Map uploadFile(MultipartFile uploadFile){
        Map result = new HashMap<>();
        try {
            //1.接收上传的文件
            //2.获取扩展名
            String orignalName = uploadFile.getOriginalFilename();
            String extName = orignalName.substring(orignalName.lastIndexOf(".")+1);
            //3.上传到图片服务器
            FastDFSClient fastDFSClient = new FastDFSClient("classpath:resource/client.conf");
            String url = fastDFSClient.uploadFile(uploadFile.getBytes(), extName);
            url = IMAGE_SERVER_URL + url;
            result.put("error", 0);
            result.put("url", url);
            return result;
        }  catch (Exception e) {
            e.printStackTrace();
            result.put("error", 0);
            result.put("message", "上传图片失败！");
            return result;
        }
    }
}
