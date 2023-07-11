package com.lsp.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lsp.pojo.study.entity.StudyPlanEvent;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Author: LinShanPeng
 */
@Mapper
public interface StudyEventMapper extends BaseMapper<StudyPlanEvent> {
}
