package com.tyrival.system.attachment.service;

import com.tyrival.entity.system.attachment.Attachment;
import com.tyrival.api.base.service.BaseService;
import com.tyrival.entity.system.user.User;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @Description:
 * @Author: Zhou Chenyu
 * @Date: 2018/10/25
 * @Version: V1.0
 */
public interface AttachmentService extends BaseService<Attachment> {

    Attachment upload(MultipartFile file);

    Attachment uploadProprietary(MultipartFile file, User user);

    List<Attachment> listProprietary(User user);

    Attachment deleteProprietary(String id);
}
