package com.tyrival.entity.system.attachment;

import com.tyrival.entity.base.Base;
import org.springframework.web.multipart.MultipartFile;

/**
 * @Description:
 * @Author: Zhou Chenyu
 * @Date: 2018/10/25
 * @Version: V1.0
 */
public class Attachment extends Base {

    /**
     * 文件名
     */
    private String name;

    /**
     * 文件完整名称，包含后缀
     */
    private String fullName;

    /**
     * 后缀名
     */
    private String extensionName;

    /**
     * 文件大小
     */
    private Long size;

    /**
     * 储存路径
     */
    private String absolutePath;

    /**
     * 相对路径
     */
    private String relativePath;

    public Attachment() {}

    public Attachment(MultipartFile file) {
        this.fullName = file.getOriginalFilename();
        int dotIndex = this.fullName.lastIndexOf(".");
        this.name = this.fullName.substring(0, dotIndex);
        this.extensionName = this.fullName.substring(dotIndex + 1);
        this.size = file.getSize();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getExtensionName() {
        return extensionName;
    }

    public void setExtensionName(String extensionName) {
        this.extensionName = extensionName;
    }

    public Long getSize() {
        return size;
    }

    public void setSize(Long size) {
        this.size = size;
    }

    public String getAbsolutePath() {
        return absolutePath;
    }

    public void setAbsolutePath(String absolutePath) {
        this.absolutePath = absolutePath;
    }

    public String getRelativePath() {
        return relativePath;
    }

    public void setRelativePath(String relativePath) {
        this.relativePath = relativePath;
    }
}
