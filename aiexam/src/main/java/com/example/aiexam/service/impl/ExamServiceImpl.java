package com.example.aiexam.service.impl;

import com.example.aiexam.entity.ExamRecord;
import com.example.aiexam.mapper.ExamRecordMapper;
import com.example.aiexam.service.ExamService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


/**
 * 考试服务实现类
 */
@Service
@Slf4j
public class ExamServiceImpl extends ServiceImpl<ExamRecordMapper, ExamRecord> implements ExamService {

} 