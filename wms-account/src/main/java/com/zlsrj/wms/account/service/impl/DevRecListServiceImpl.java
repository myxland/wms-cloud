package com.zlsrj.wms.account.service.impl;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zlsrj.wms.api.entity.DevRecList;
import com.zlsrj.wms.account.mapper.DevRecListMapper;
import com.zlsrj.wms.account.service.IDevRecListService;

@Service
public class DevRecListServiceImpl extends ServiceImpl<DevRecListMapper, DevRecList> implements IDevRecListService {

}
