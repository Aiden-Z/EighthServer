package com.eight.server.Database.mapper;

import com.eight.server.Database.entity.Instructor;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

/**
 * <p>
 * 辅导员基本表 Mapper 接口
 * </p>
 *
 * @author Aiden_Z
 * @since 2018-12-28
 */
@Component
public interface InstructorMapper extends BaseMapper<Instructor> {

}
