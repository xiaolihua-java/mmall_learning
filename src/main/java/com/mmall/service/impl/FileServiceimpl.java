package com.mmall.service.impl;

import com.google.common.collect.Lists;
import com.mmall.service.IFileService;
import com.mmall.util.FTPUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

/**
 * Created by MAIBENBEN on 2018-07-21.
 */
@Service("iFileService")
public class FileServiceimpl implements IFileService {

    private Logger logger= LoggerFactory.getLogger(FileServiceimpl.class);

    public String upload(MultipartFile file,String path){
        String fileName=file.getOriginalFilename();//拿到上传文件的原始文件名
        //扩展名
        String  fileExtensionName=fileName.substring(fileName.lastIndexOf(".")+1);
        String uploadFileName= UUID .randomUUID().toString()+"."+fileExtensionName;
        logger.info("开始上传文件，上传文件的文件名:{},上传的路径：{}，新文件名：{}",fileName,path,uploadFileName);

        File fileDir=new File(path);
        if (!fileDir.exists()){//文件夹不存在就执行
            fileDir.setWritable(true);
            fileDir.mkdirs(); //批量创建夹子
        }
        File targetFile=new File(path,uploadFileName);

        try {
            file.transferTo(targetFile);//将文件file上传到targetFile位置

            //todo 将targetFile上传到我们的FTP服务器上
            FTPUtil.upload(Lists.newArrayList(targetFile));

            //todo  上传完后，删除upload下面的文件
            targetFile.delete();
        } catch (IOException e) {
          logger.error("上传文件异常",e);
            return null;
        }
        return targetFile.getName();
    }


}
