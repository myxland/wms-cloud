package com.zlsrj.wms.mbg.service.impl;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zlsrj.wms.api.entity.TenantBill;
import com.zlsrj.wms.mbg.mapper.TenantBillMapper;
import com.zlsrj.wms.mbg.service.ITenantBillService;

@Service
public class TenantBillServiceImpl extends ServiceImpl<TenantBillMapper, TenantBill> implements ITenantBillService {

}
