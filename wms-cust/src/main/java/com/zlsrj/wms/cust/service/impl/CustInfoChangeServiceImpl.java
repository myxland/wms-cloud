package com.zlsrj.wms.cust.service.impl;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zlsrj.wms.api.entity.CustInfoChange;
import com.zlsrj.wms.cust.mapper.CustInfoChangeMapper;
import com.zlsrj.wms.cust.service.ICustInfoChangeService;

@Service
public class CustInfoChangeServiceImpl extends ServiceImpl<CustInfoChangeMapper, CustInfoChange> implements ICustInfoChangeService {

}
