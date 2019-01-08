package com.tyrival.system.attachment.dao;

import com.tyrival.entity.system.attachment.Attachment;
import com.tyrival.api.base.dao.BaseDAO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

/**
 * @Description:
 * @Author: Zhou Chenyu
 * @Date: 2018/10/25
 * @Version: V1.0
 */
@Mapper
@Component
public interface AttachmentDAO extends BaseDAO<Attachment> {
}
