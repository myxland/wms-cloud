package com.zlsrj.wms.cust.service.impl;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zlsrj.wms.api.entity.CustContact;
import com.zlsrj.wms.cust.mapper.CustContactMapper;
import com.zlsrj.wms.cust.service.ICustContactService;

@Service
public class CustContactServiceImpl extends ServiceImpl<CustContactMapper, CustContact> implements ICustContactService {

}
