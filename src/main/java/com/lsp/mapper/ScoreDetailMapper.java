package com.lsp.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lsp.pojo.score.entity.ScoreDetail;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @Author: LinShanPeng
 */
@Mapper
public interface ScoreDetailMapper extends BaseMapper<ScoreDetail> {
    int selectToday(ScoreDetail sd);
}
