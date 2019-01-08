package com.tyrival.entity.datasource.file;

import com.tyrival.entity.datasource.DataSource;

/**
 * @Description:
 * @Author: Zhou Chenyu
 * @Date: 2018/12/13
 * @Version: V1.0
 */
public class FileSource extends DataSource {

    /**
     * 静态文件ID
     */
    private String attachmentId;

    public String getAttachmentId() {
        return attachmentId;
    }

    public void setAttachmentId(String attachmentId) {
        this.attachmentId = attachmentId;
    }
}
