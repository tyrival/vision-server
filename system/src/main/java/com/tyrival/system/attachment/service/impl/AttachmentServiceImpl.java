package com.tyrival.system.attachment.service.impl;

import com.tyrival.entity.system.attachment.Attachment;
import com.tyrival.entity.system.user.User;
import com.tyrival.enums.base.BaseStateEnum;
import com.tyrival.exceptions.CommonException;
import com.tyrival.exceptions.ExceptionEnum;
import com.tyrival.system.attachment.dao.AttachmentDAO;
import com.tyrival.system.attachment.service.AttachmentService;
import com.tyrival.api.base.dao.BaseDAO;
import com.tyrival.api.base.service.impl.BaseServiceImpl;
import com.tyrival.utils.FileUtil;
import com.tyrival.utils.PathUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Calendar;
import java.util.List;
import java.util.UUID;

/**
 * @Description:
 * @Author: Zhou Chenyu
 * @Date: 2018/10/25
 * @Version: V1.0
 */
@Service
@RefreshScope
public class AttachmentServiceImpl extends BaseServiceImpl<Attachment> implements AttachmentService {

    private final static String SEPARATOR_CHAR = "/";

    @Value("${attachment.rootPath}")
    String rootPath;

    @Autowired
    private AttachmentDAO attachmentDAO;

    @Override
    public BaseDAO<Attachment> getDAO() {
        return attachmentDAO;
    }

    @Override
    public Attachment upload(MultipartFile file) {
        if (file.isEmpty()) {
            throw new CommonException(ExceptionEnum.UPLOAD_FAIL);
        }
        try {
            Attachment attachment = new Attachment(file);
            Calendar cal = Calendar.getInstance();
            Integer year = cal.get(Calendar.YEAR);
            Integer month = cal.get(Calendar.MONTH) + 1;
            StringBuilder document = new StringBuilder()
                    .append(year).append(SEPARATOR_CHAR)
                    .append(month).append(SEPARATOR_CHAR);
            StringBuilder relativePath = new StringBuilder(SEPARATOR_CHAR)
                    .append(rootPath).append(SEPARATOR_CHAR)
                    .append(document);
            StringBuilder absolutePath = new StringBuilder()
                    .append(PathUtil.getRootPath())
                    .append(rootPath).append(SEPARATOR_CHAR)
                    .append(document);
            String id = UUID.randomUUID().toString();
            StringBuilder name = new StringBuilder()
                    .append(id).append(".")
                    .append(attachment.getExtensionName());
            FileUtil.save(file.getBytes(), absolutePath.toString(), name.toString());
            attachment.setId(id);
            attachment.setAbsolutePath(absolutePath.append(name).toString());
            attachment.setRelativePath(relativePath.append(name).toString());
            attachment.setCreateTime(cal.getTime());
            attachment.setDelFlag(BaseStateEnum.ACTIVE);
            attachment = this.create(attachment);
            return attachment;
        } catch (Exception e) {
            throw new CommonException(ExceptionEnum.UPLOAD_FAIL);
        }
    }

    @Override
    public Attachment uploadProprietary(MultipartFile file, User user) {
        if (file.isEmpty()) {
            throw new CommonException(ExceptionEnum.UPLOAD_FAIL);
        }
        try {
            Attachment attachment = new Attachment(file);
            Calendar cal = Calendar.getInstance();
            Integer year = cal.get(Calendar.YEAR);
            Integer month = cal.get(Calendar.MONTH) + 1;
            StringBuilder document = new StringBuilder()
                    .append(year).append(SEPARATOR_CHAR)
                    .append(month).append(SEPARATOR_CHAR);
            StringBuilder relativePath = new StringBuilder(document);
            StringBuilder absolutePath = new StringBuilder(rootPath).append(SEPARATOR_CHAR)
                    .append(document);
            String id = UUID.randomUUID().toString();
            StringBuilder name = new StringBuilder()
                    .append(id).append(".")
                    .append(attachment.getExtensionName());
            FileUtil.save(file.getBytes(), absolutePath.toString(), name.toString());
            attachment.setId(id);
            attachment.setAbsolutePath(absolutePath.append(name).toString());
            attachment.setRelativePath(relativePath.append(name).toString());
            attachment.setCreateUserId(user.getId());
            attachment.setCreateTime(cal.getTime());
            attachment.setDelFlag(BaseStateEnum.ACTIVE);
            attachment = this.create(attachment);
            return attachment;
        } catch (Exception e) {
            throw new CommonException(ExceptionEnum.UPLOAD_FAIL);
        }
    }

    @Override
    public List<Attachment> listProprietary(User user) {
        return this.attachmentDAO.listProprietary(user);
    }

    @Override
    public int delete(String id) {
        Attachment attachment = this.attachmentDAO.get(id);
        FileUtil.delete(attachment.getAbsolutePath());
        return this.attachmentDAO.delete(id);
    }
}
