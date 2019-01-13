package com.tyrival.datasource.service.impl;

import com.tyrival.datasource.dao.DataSourceDAO;
import com.tyrival.datasource.service.DataSourceService;
import com.tyrival.entity.base.Result;
import com.tyrival.entity.datasource.DataSource;
import com.tyrival.entity.datasource.DataSourceQuery;
import com.tyrival.entity.datasource.api.ApiSource;
import com.tyrival.entity.datasource.db.Database;
import com.tyrival.entity.datasource.db.DbQuery;
import com.tyrival.entity.datasource.file.FileSource;
import com.tyrival.enums.base.ApiEnum;
import com.tyrival.enums.datasource.DataSourceTypeEnum;
import com.tyrival.utils.ExcelUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Service
public class DataSourceServiceImpl<T extends DataSource> implements DataSourceService<T> {

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    private DataSourceDAO dataSourceDAO;

    @Override
    public int create(T t) {
        if (t.getType().isApi()) {
            return this.createApi((ApiSource) t);
        }
        if (t.getType().isDatabase()) {
            return this.createDb((Database) t);
        }
        if (t.getType().isFile()) {
            return this.createFile((FileSource) t);
        }
        return 0;
    }

    @Override
    public int createDb(Database database) {
        return this.dataSourceDAO.insertDb(database);
    }

    @Override
    public int createApi(ApiSource apiSource) {
        return this.dataSourceDAO.insertApi(apiSource);
    }

    @Override
    public int createFile(FileSource fileSource) {
        return this.dataSourceDAO.insertFile(fileSource);
    }

    @Override
    public int update(T t) {
        if (t.getType().isApi()) {
            return this.updateApi((ApiSource) t);
        }
        if (t.getType().isDatabase()) {
            return this.updateDb((Database) t);
        }
        if (t.getType().isFile()) {
            return this.updateFile((FileSource) t);
        }
        return 0;
    }

    @Override
    public int updateDb(Database database) {
        return this.dataSourceDAO.updateDb(database);
    }

    @Override
    public int updateApi(ApiSource apiSource) {
        return this.dataSourceDAO.updateApi(apiSource);
    }

    @Override
    public int updateFile(FileSource fileSource) {
        return this.dataSourceDAO.updateFile(fileSource);
    }

    @Override
    public int delete(DataSourceQuery dataSourceQuery) {
        if (dataSourceQuery.getType().isApi()) {
            return this.dataSourceDAO.deleteApi(dataSourceQuery);
        }
        if (dataSourceQuery.getType().isDatabase()) {
            return this.dataSourceDAO.deleteDb(dataSourceQuery);
        }
        if (dataSourceQuery.getType().isFile()) {
            FileSource fileSource = this.dataSourceDAO.getFile(dataSourceQuery);
            StringBuilder sb = new StringBuilder()
                    .append(ApiEnum.ATTACHMENT_DELETE.getUrl())
                    .append("?id=").append(fileSource.getAttachmentId());
            this.restTemplate.getForEntity(sb.toString(), Result.class);
            return this.dataSourceDAO.deleteFile(dataSourceQuery);
        }
        return 0;
    }

    @Override
    public Object get(DataSourceQuery dataSourceQuery) {
        if (dataSourceQuery.getType().isApi()) {
            return this.dataSourceDAO.getApi(dataSourceQuery);
        }
        if (dataSourceQuery.getType().isDatabase()) {
            return this.dataSourceDAO.getDb(dataSourceQuery);
        }
        if (dataSourceQuery.getType().isFile()) {
            return this.dataSourceDAO.getFile(dataSourceQuery);
        }
        return null;
    }

    @Override
    public List listByUser(DataSourceQuery dataSourceQuery) {
        return this.dataSourceDAO.listByUser(dataSourceQuery);
    }

    @Override
    public List listDbByUser(DataSourceQuery dataSourceQuery) {
        return this.dataSourceDAO.listDbByUser(dataSourceQuery);
    }

    @Override
    public List listFileByUser(DataSourceQuery dataSourceQuery) {
        return this.dataSourceDAO.listFileByUser(dataSourceQuery);
    }

    @Override
    public List<Map<String, Object>> query(DataSourceQuery query) {
        Object dataSource = this.get(query);
        DataSourceTypeEnum type = ((DataSource) dataSource).getType();
        if (type.isApi()) {
            return this.queryApi((ApiSource) dataSource);
        }
        if (type.isDatabase()) {
            DbQuery dbQuery = new DbQuery((Database) dataSource, query.getSql());
            List<Map<String, Object>> list = this.queryDatabase(dbQuery);
            return list;
        }
        if (type.isFile()) {
            return this.queryFile((FileSource) dataSource);
        }
        return null;
    }

    @Override
    public List<String> listDb(Database database) {
        return this.dataSourceDAO.listDb(database);
    }

    public List<Map<String, Object>> queryDatabase(DbQuery dbQuery) {
        return this.dataSourceDAO.queryDb(dbQuery);
    }

    public List<Map<String, Object>> queryFile(FileSource fileSource) {
        StringBuilder sb = new StringBuilder()
                .append(ApiEnum.ATTACHMENT_GET.getUrl())
                .append("?id=").append(fileSource.getAttachmentId());
        ResponseEntity<Result> response = this.restTemplate.getForEntity(sb.toString(), Result.class);
        String absolutePath = (String) ((LinkedHashMap)response.getBody().getData()).get("absolutePath");
        List<Map<String, Object>> list = ExcelUtil.read(absolutePath);
        return list;
    }

    public List<Map<String, Object>> queryApi(ApiSource apiSource) {
        return null;
    }

}
