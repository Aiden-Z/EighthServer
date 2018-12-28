package com.eight.server.Database.service.impl;

import com.eight.server.Database.entity.Instructor;
import com.eight.server.Database.mapper.InstructorMapper;
import com.eight.server.Database.service.IInstructorService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 辅导员基本表 服务实现类
 * </p>
 *
 * @author Aiden_Z
 * @since 2018-12-28
 */
@Service
public class InstructorServiceImpl extends ServiceImpl<InstructorMapper, Instructor> implements IInstructorService {

}
