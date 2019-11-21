package com.zlsrj.wms.cust.service.impl;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zlsrj.wms.api.entity.CustDev;
import com.zlsrj.wms.cust.mapper.CustDevMapper;
import com.zlsrj.wms.cust.service.ICustDevService;

@Service
public class CustDevServiceImpl extends ServiceImpl<CustDevMapper, CustDev> implements ICustDevService {

}
