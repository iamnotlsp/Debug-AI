package com.lsp.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import com.lsp.pojo.user.entity.UserGroup;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Author: LinShanPeng
 */
@Mapper
public interface GroupMapper extends BaseMapper<UserGroup> {
    UserGroup selectByGroupId(Integer groupId);
}
