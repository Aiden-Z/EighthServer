package com.eight.server.Database.service.impl;

import com.eight.server.Database.entity.Instructor;
import com.eight.server.Database.dao.InstructorMapper;
import com.eight.server.Database.service.IInstructorService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 辅导员基本表 服务实现类
 * </p>
 *
 * @author stylefeng
 * @since 2018-12-20
 */
@Service
public class InstructorServiceImpl extends ServiceImpl<InstructorMapper, Instructor> implements IInstructorService {

}
