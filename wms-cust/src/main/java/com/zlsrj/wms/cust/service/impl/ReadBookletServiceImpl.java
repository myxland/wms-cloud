package com.zlsrj.wms.cust.service.impl;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zlsrj.wms.api.entity.ReadBooklet;
import com.zlsrj.wms.cust.mapper.ReadBookletMapper;
import com.zlsrj.wms.cust.service.IReadBookletService;

@Service
public class ReadBookletServiceImpl extends ServiceImpl<ReadBookletMapper, ReadBooklet> implements IReadBookletService {

}
