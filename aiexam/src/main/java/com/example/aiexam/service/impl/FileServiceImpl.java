package com.example.aiexam.service.impl;

import com.example.aiexam.config.properties.MinioProperties;
import com.example.aiexam.service.FileService;
import io.minio.*;
import io.minio.errors.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

@Service
@Slf4j
public class FileServiceImpl implements FileService {
    @Autowired
    private MinioProperties minioproperties;
    @Autowired
    private MinioClient minioClient;
    @Override
    public String uploadFile(String folder, MultipartFile file) throws ServerException, InsufficientDataException, ErrorResponseException, IOException, NoSuchAlgorithmException, InvalidKeyException, InvalidResponseException, XmlParserException, InternalException {
        boolean bucketExists = minioClient.bucketExists(BucketExistsArgs.builder().bucket(minioproperties.getBucketName()).build());
        if(!bucketExists){
            String policy = """
            {
                "Version": "2012-10-17",
                "Statement": [
                    {
                        "Effect": "Allow",
                        "Principal": {"AWS": ["*"]},
                        "Action": ["s3:GetObject"],
                        "Resource": ["arn:aws:s3:::%s/*"]
                    }
                ]
            }
            """.formatted(minioproperties.getBucketName());
            minioClient.makeBucket(MakeBucketArgs.builder().bucket(minioproperties.getBucketName()).build());
            minioClient.setBucketPolicy(SetBucketPolicyArgs.builder().bucket(minioproperties.getBucketName()).config(policy).build());
        }

        // 确保 folder 不以斜杠结尾
        String normalizedFolder = folder.endsWith("/") ? folder.substring(0, folder.length() - 1) : folder;
        String objectName = normalizedFolder + "/" + new SimpleDateFormat("yyyyMMdd").format(new Date()) +
                "/" + UUID.randomUUID().toString().replace("-","") + file.getOriginalFilename();


        minioClient.putObject(PutObjectArgs.builder()
                   .bucket(minioproperties.getBucketName())
                   .contentType(file.getContentType())
                   .object(objectName)
                   .stream(file.getInputStream(), file.getSize(), -1).build());

        String url = String.join("/",minioproperties.getEndpoint(),minioproperties.getBucketName(),objectName);
        log.info("完成{}文件上传，返回地址为：{}",objectName,url);
        return url;
    }
}
