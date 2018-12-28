package com.eight.server.Database.service.impl;

import com.eight.server.Database.entity.Test;
import com.eight.server.Database.mapper.TestMapper;
import com.eight.server.Database.service.ITestService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 测试基本表 服务实现类
 * </p>
 *
 * @author Aiden_Z
 * @since 2018-12-28
 */
@Service
public class TestServiceImpl extends ServiceImpl<TestMapper, Test> implements ITestService {

}
