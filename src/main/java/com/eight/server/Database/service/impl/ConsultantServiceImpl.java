package com.eight.server.Database.service.impl;

import com.eight.server.Database.entity.Consultant;
import com.eight.server.Database.dao.ConsultantMapper;
import com.eight.server.Database.service.IConsultantService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 咨询师基本表 服务实现类
 * </p>
 *
 * @author stylefeng
 * @since 2018-12-20
 */
@Service
public class ConsultantServiceImpl extends ServiceImpl<ConsultantMapper, Consultant> implements IConsultantService {

}
