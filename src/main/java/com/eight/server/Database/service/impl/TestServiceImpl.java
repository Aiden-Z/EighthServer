package com.eight.server.Database.service.impl;

import com.eight.server.Database.entity.Test;
import com.eight.server.Database.dao.TestMapper;
import com.eight.server.Database.service.ITestService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 测试基本表 服务实现类
 * </p>
 *
 * @author stylefeng
 * @since 2018-12-18
 */
@Service
public class TestServiceImpl extends ServiceImpl<TestMapper, Test> implements ITestService {

}
