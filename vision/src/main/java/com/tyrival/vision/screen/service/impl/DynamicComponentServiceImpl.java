package com.tyrival.vision.screen.service.impl;

import com.tyrival.api.base.dao.BaseDAO;
import com.tyrival.api.base.service.impl.BaseServiceImpl;
import com.tyrival.entity.vision.screen.DynamicComponent;
import com.tyrival.vision.screen.dao.DynamicComponentDAO;
import com.tyrival.vision.screen.service.DynamicComponentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DynamicComponentServiceImpl extends BaseServiceImpl<DynamicComponent> implements DynamicComponentService {

    @Autowired
    private DynamicComponentDAO dynamicComponentDAO;

    @Override
    public BaseDAO<DynamicComponent> getDAO() {
        return dynamicComponentDAO;
    }
}
