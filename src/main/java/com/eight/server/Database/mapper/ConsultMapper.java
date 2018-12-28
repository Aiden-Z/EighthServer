package com.eight.server.Database.mapper;

import com.eight.server.Database.entity.Consult;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

/**
 * <p>
 * 咨询基本表 Mapper 接口
 * </p>
 *
 * @author Aiden_Z
 * @since 2018-12-27
 */
@Component
public interface ConsultMapper extends BaseMapper<Consult> {

}
