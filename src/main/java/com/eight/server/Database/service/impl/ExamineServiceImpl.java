package com.eight.server.Database.service.impl;

import com.eight.server.Database.entity.Examine;
import com.eight.server.Database.dao.ExamineMapper;
import com.eight.server.Database.service.IExamineService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 月排查表基本表 服务实现类
 * </p>
 *
 * @author stylefeng
 * @since 2018-12-18
 */
@Service
public class ExamineServiceImpl extends ServiceImpl<ExamineMapper, Examine> implements IExamineService {

}
