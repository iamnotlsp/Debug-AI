package com.lsp.mapper.member;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lsp.pojo.user.entity.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Author: LinShanPeng
 */
@Mapper
public interface MemberMapper extends BaseMapper<User> {
}
