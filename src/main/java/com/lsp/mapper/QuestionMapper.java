package com.lsp.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lsp.pojo.question.entity.Question;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Author: LinShanPeng
 */
@Mapper
public interface QuestionMapper extends BaseMapper<Question> {
}
