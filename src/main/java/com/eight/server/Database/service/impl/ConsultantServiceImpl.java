package com.eight.server.Database.service.impl;

import com.eight.server.Database.entity.Consultant;
import com.eight.server.Database.mapper.ConsultantMapper;
import com.eight.server.Database.service.IConsultantService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 咨询师基本表 服务实现类
 * </p>
 *
 * @author Aiden_Z
 * @since 2018-12-28
 */
@Service
public class ConsultantServiceImpl extends ServiceImpl<ConsultantMapper, Consultant> implements IConsultantService {

}
