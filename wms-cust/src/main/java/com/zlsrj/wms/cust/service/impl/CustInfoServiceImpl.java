package com.zlsrj.wms.cust.service.impl;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zlsrj.wms.api.entity.CustInfo;
import com.zlsrj.wms.cust.mapper.CustInfoMapper;
import com.zlsrj.wms.cust.service.ICustInfoService;

@Service
public class CustInfoServiceImpl extends ServiceImpl<CustInfoMapper, CustInfo> implements ICustInfoService {

}
