package com.eight.server.Database.service.impl;

import com.eight.server.Database.entity.Student;
import com.eight.server.Database.dao.StudentMapper;
import com.eight.server.Database.service.IStudentService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 来访者基本表 服务实现类
 * </p>
 *
 * @author stylefeng
 * @since 2018-12-20
 */
@Service
public class StudentServiceImpl extends ServiceImpl<StudentMapper, Student> implements IStudentService {

}
