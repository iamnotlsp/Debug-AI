package com.lsp.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lsp.pojo.member.entity.UserCollection;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Author: LinShanPeng
 */
@Mapper
public interface CollectionsMapper extends BaseMapper<UserCollection> {
}
