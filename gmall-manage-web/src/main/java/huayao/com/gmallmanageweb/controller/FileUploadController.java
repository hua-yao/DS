package huayao.com.gmallmanageweb.controller;

import org.apache.commons.lang3.StringUtils;
import org.csource.common.MyException;
import org.csource.fastdfs.ClientGlobal;
import org.csource.fastdfs.StorageClient;
import org.csource.fastdfs.TrackerClient;
import org.csource.fastdfs.TrackerServer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * @program: dainShangDemo
 * @description: 测试
 * @author: HuaYao
 * @create: 2020-02-05 15:09
 **/
@Controller
public class FileUploadController {

    @Value("${fileServer.url}")
    String fileUrl;

    @PostMapping("/fileUpload")
    @ResponseBody
    public String upload(MultipartFile file) throws IOException, MyException {
        String imgUrl=fileUrl;
        if(file!=null){
            System.out.println("multipartFile = " + file.getName()+"|"+file.getSize());
            //获取resources目录下的tracker.conf文件
            String configFile = this.getClass().getResource("/tracker.conf").getFile();
            //fdfs初始化配置文件
            ClientGlobal.init(configFile);
            //创建tracker实例
            TrackerClient trackerClient=new TrackerClient();
            //创建tracker服务器连接
            TrackerServer trackerServer=trackerClient.getTrackerServer();
            //创建storage实例
            StorageClient storageClient=new StorageClient(trackerServer,null);
            //获取完整文件名
            String filename=    file.getOriginalFilename();
            //分割后缀名
            String extName = StringUtils.substringAfterLast(filename, ".");
            //上传文件到fdfs
            String[] upload_file = storageClient.upload_file(file.getBytes(), extName, null);
            imgUrl=fileUrl ;
            //拼接
            for (int i = 0; i < upload_file.length; i++) {
                String path = upload_file[i];
                imgUrl+="/"+path;
            }
        }
        System.out.println(imgUrl);
        return imgUrl;
    }

}
































