package com.tyrival.system.attachment.controller;

import com.tyrival.config.ControllerConfig;
import com.tyrival.entity.base.Result;
import com.tyrival.entity.system.attachment.Attachment;
import com.tyrival.exceptions.CommonException;
import com.tyrival.exceptions.ExceptionEnum;
import com.tyrival.api.base.controller.BaseController;
import com.tyrival.system.attachment.service.AttachmentService;
import com.tyrival.api.base.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.List;

/**
 * @Description:
 * @Author: Zhou Chenyu
 * @Date: 2018/10/25
 * @Version: V1.0
 */
@RestController
@RequestMapping(ControllerConfig.ATTACHMENT)
public class AttachmentController extends BaseController<Attachment> {

    @Autowired
    private AttachmentService attachmentService;

    @Override
    public BaseService getService() {
        return attachmentService;
    }

    @RequestMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public Result upload(@RequestPart(value = "file") MultipartFile file) {
        try {
            Attachment attachment = attachmentService.upload(file);
            return new Result(attachment);
        } catch (CommonException e) {
            return new Result(e);
        } catch (Exception e) {
            return new Result(ExceptionEnum.UPLOAD_FAIL);
        }
    }

    @PostMapping(value = "/upload_proprietary", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public Result upload_proprietary(@RequestPart(value = "file") MultipartFile file) {
        try {
            Attachment attachment = attachmentService.uploadProprietary(file, this.getCurrentUser());
            return new Result(attachment);
        } catch (CommonException e) {
            return new Result(e);
        }
    }

    @GetMapping(value = "/list_proprietary")
    public Result list_proprietary() {
        try {
            List<Attachment> list = attachmentService.listProprietary(this.getCurrentUser());
            return new Result(list);
        } catch (CommonException e) {
            return new Result(e);
        }
    }

    @GetMapping(value = "/delete_proprietary")
    public Result delete_proprietary(@RequestParam("id") String id) {
        try {
            Attachment attachment = this.attachmentService.deleteProprietary(id);
            return new Result(attachment);
        } catch (CommonException e) {
            return new Result(e);
        }
    }

    @RequestMapping(value = "/download")
    public HttpServletResponse download(HttpServletResponse response, @RequestParam("id") String id) {
        try {
            Attachment attachment = this.attachmentService.get(id);
            String absolutePath = attachment.getAbsolutePath();
            // path是指欲下载的文件的路径。
            File file = new File(absolutePath);
            // 取得文件名。
            String filename = file.getName();
            // 取得文件的后缀名。
            String ext = filename.substring(filename.lastIndexOf(".") + 1).toUpperCase();

            // 以流的形式下载文件。
            InputStream fis = new BufferedInputStream(new FileInputStream(absolutePath));
            byte[] buffer = new byte[fis.available()];
            fis.read(buffer);
            fis.close();
            // 清空response
            response.reset();
            // 设置response的Header
            response.addHeader("Content-Disposition", "attachment;filename=" + new String(filename.getBytes()));
            response.addHeader("Content-Length", "" + file.length());
            OutputStream outputStream = new BufferedOutputStream(response.getOutputStream());
            response.setContentType("application/octet-stream");
            outputStream.write(buffer);
            outputStream.flush();
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return response;
    }
}
