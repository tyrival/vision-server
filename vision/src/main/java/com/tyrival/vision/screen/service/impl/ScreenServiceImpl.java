package com.tyrival.vision.screen.service.impl;

import com.tyrival.entity.vision.screen.Screen;
import com.tyrival.api.base.dao.BaseDAO;
import com.tyrival.api.base.service.impl.BaseServiceImpl;
import com.tyrival.redis.annotation.CacheExpire;
import com.tyrival.vision.screen.dao.ScreenDAO;
import com.tyrival.vision.screen.service.ScreenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class ScreenServiceImpl extends BaseServiceImpl<Screen> implements ScreenService {

    @Autowired
    private ScreenDAO screenDAO;

    @Override
    public BaseDAO<Screen> getDAO() {
        return screenDAO;
    }

    @Override
    @Cacheable(value = "screen", key = "#id")
    public Screen get(String id) {
        return this.getDAO().get(id);
    }

    @Override
    @Cacheable(value = "screen", key = "#screen.id")
    @CacheExpire(expire = 10)
    public Screen preview(Screen screen) {
        return screen;
    }
}
