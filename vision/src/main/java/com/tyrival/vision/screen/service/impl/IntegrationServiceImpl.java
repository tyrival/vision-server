package com.tyrival.vision.screen.service.impl;

import com.tyrival.api.base.dao.BaseDAO;
import com.tyrival.api.base.service.impl.BaseServiceImpl;
import com.tyrival.entity.vision.screen.Integration;
import com.tyrival.redis.annotation.CacheExpire;
import com.tyrival.vision.screen.dao.IntegrationDAO;
import com.tyrival.vision.screen.service.IntegrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IntegrationServiceImpl extends BaseServiceImpl<Integration> implements IntegrationService {

    @Autowired
    private IntegrationDAO integrationDAO;

    @Override
    public BaseDAO<Integration> getDAO() {
        return integrationDAO;
    }

    @Override
    @Cacheable(value = "integration", key = "#id")
    public Integration get(String id) {
        return this.getDAO().get(id);
    }

    @Override
    @Cacheable(value = "integration", key = "#integration.id")
    @CacheExpire(expire = 10)
    public Integration preview(Integration integration) {
        return integration;
    }

    @Override
    public List<Integration> listByUser(String id) {
        return this.integrationDAO.listByUser(id);
    }
}
