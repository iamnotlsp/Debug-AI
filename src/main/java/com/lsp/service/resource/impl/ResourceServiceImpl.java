package com.lsp.service.resource.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lsp.mapper.resource.ResourceMapper;
import com.lsp.pojo.resource.entity.Resource;
import com.lsp.service.resource.ResourceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author: LinShanPeng
 */
@Slf4j
@Service
public class ResourceServiceImpl implements ResourceService {

    @Autowired
    private ResourceMapper resourceMapper;

    @Override
    public Resource getResourceById(Integer resourceId) {
        QueryWrapper<Resource> wrapper = new QueryWrapper<>();
        wrapper.eq("resource_id",resourceId);
        Resource resource = resourceMapper.selectOne(wrapper);
        return resource;
    }
}
