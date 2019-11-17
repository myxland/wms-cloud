package com.zlsrj.wms.system.service.impl;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zlsrj.wms.api.entity.SystemPrice;
import com.zlsrj.wms.system.mapper.SystemPriceMapper;
import com.zlsrj.wms.system.service.ISystemPriceService;

@Service
public class SystemPriceServiceImpl extends ServiceImpl<SystemPriceMapper, SystemPrice> implements ISystemPriceService {

}
