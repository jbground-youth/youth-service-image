package com.youth.image.infrastructure;

import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.ObjectMetadata;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.IOException;
import java.io.InputStream;

@Service
public class AmazonImageUploader {

    @Resource(type = AmazonS3Client.class)
    private AmazonS3Client amazonS3;

    @Value("${cloud.aws.s3.bucket}")
    private String bucket;

    public String save(MultipartFile multipartFile) throws IOException {
        String originalFilename = multipartFile.getOriginalFilename();

        ObjectMetadata metadata = new ObjectMetadata();
        metadata.setContentLength(multipartFile.getSize());
        metadata.setContentType(multipartFile.getContentType());

        amazonS3.putObject(bucket, originalFilename, multipartFile.getInputStream(), metadata);
        return amazonS3.getUrl(bucket, originalFilename).toString();
    }

    public void save(String originalFileName, InputStream input, ObjectMetadata metadata) {

        try{
            amazonS3.putObject(bucket, originalFileName, input, metadata);
        }catch (Exception e){
            throw e;
        }

    }

    public void findUrl(){

    }
}
