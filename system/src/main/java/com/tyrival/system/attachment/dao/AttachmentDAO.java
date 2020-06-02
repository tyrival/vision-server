package com.tyrival.system.attachment.dao;

import com.tyrival.entity.system.attachment.Attachment;
import com.tyrival.api.base.dao.BaseDAO;
import com.tyrival.entity.system.user.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Description:
 * @Author: Zhou Chenyu
 * @Date: 2018/10/25
 * @Version: V1.0
 */
@Mapper
@Component
public interface AttachmentDAO extends BaseDAO<Attachment> {
    List<Attachment> listProprietary(User user);
}
