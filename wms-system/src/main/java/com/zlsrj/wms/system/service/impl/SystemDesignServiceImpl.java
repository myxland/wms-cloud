package com.zlsrj.wms.system.service.impl;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zlsrj.wms.api.entity.SystemDesign;
import com.zlsrj.wms.system.mapper.SystemDesignMapper;
import com.zlsrj.wms.system.service.ISystemDesignService;

@Service
public class SystemDesignServiceImpl extends ServiceImpl<SystemDesignMapper, SystemDesign> implements ISystemDesignService {

}
