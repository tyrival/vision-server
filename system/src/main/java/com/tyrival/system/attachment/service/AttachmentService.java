package com.tyrival.system.attachment.service;

import com.tyrival.entity.system.attachment.Attachment;
import com.tyrival.api.base.service.BaseService;
import org.springframework.web.multipart.MultipartFile;

/**
 * @Description:
 * @Author: Zhou Chenyu
 * @Date: 2018/10/25
 * @Version: V1.0
 */
public interface AttachmentService extends BaseService<Attachment> {

    Attachment upload(MultipartFile file);
}
