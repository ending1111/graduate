package com.prj.tools;

import org.apache.commons.lang.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.util.Random;

public class UploadFile {


    public static String uploadImage(MultipartFile multipartFile){
        String image="";
        try{
            if (multipartFile==null||multipartFile.isEmpty() || StringUtils.isBlank(multipartFile.getOriginalFilename())) {

            }else {
                String contentType = multipartFile.getContentType();

                if (multipartFile.getSize()<=0||multipartFile.getSize()>5242880) {

                }else{
                    String originalFilename = multipartFile.getOriginalFilename();
                    String substring = originalFilename.substring(originalFilename.lastIndexOf(".")).toLowerCase();
                    Random random = new Random();
                    String filename="/news/news_picture_"+random.nextInt(10000) + System.currentTimeMillis() + substring;
                    COSClientUtil cosClientUtil = new COSClientUtil();
                    String name = cosClientUtil.uploadFile2Cos(multipartFile,filename);
                    image = cosClientUtil.getImgUrl(name);
                }

            }

        }catch(Exception e){
            System.out.print(e.getMessage());

        }
        return image;

    }

}
