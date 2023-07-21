package com.lsp.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lsp.pojo.resource.entity.UserLike;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Author: LinShanPeng
 */
@Mapper
public interface LikeMapper extends BaseMapper<UserLike> {
}
