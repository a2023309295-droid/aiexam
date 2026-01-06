package com.example.aiexam.service.impl;

import com.example.aiexam.entity.Banner;
import com.example.aiexam.mapper.BannerMapper;
import com.example.aiexam.service.BannerService;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.aiexam.service.FileService;
import io.minio.errors.*;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;
import java.util.List;

/**
 * 轮播图服务实现类
 */
@Service
public class BannerServiceImpl extends ServiceImpl<BannerMapper, Banner> implements BannerService {

    @Autowired
    private FileService fileUploadService;
    public String uploadBannerImage(MultipartFile file) throws ServerException, InsufficientDataException, ErrorResponseException, IOException, NoSuchAlgorithmException, InvalidKeyException, InvalidResponseException, XmlParserException, InternalException {
      if (file==null||file.isEmpty()){
          throw new RuntimeException("上传文件不能为空");
      }
      String contendType = file.getContentType();
      if (ObjectUtils.isEmpty(contendType)||!contendType.startsWith("image")){
          throw new RuntimeException("上传文件格式错误");
      }
      String url = fileUploadService.uploadFile("banners/",file);
      return url;
    }
} 