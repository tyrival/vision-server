package com.tyrival.feign.system.attachment.controller;

import com.tyrival.config.ControllerConfig;
import com.tyrival.entity.base.Result;
import com.tyrival.entity.system.attachment.Attachment;
import com.tyrival.api.feign.controller.FeignController;
import com.tyrival.api.feign.service.FeignService;
import com.tyrival.feign.system.attachment.service.AttachmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Description:
 * @Author: Zhou Chenyu
 * @Date: 2018/10/24
 * @Version: V1.0
 */
@RestController
@RequestMapping(ControllerConfig.ATTACHMENT)
public class AttachmentController extends FeignController<Attachment> {

    @Autowired
    private AttachmentService attachmentService;

    @Override
    public FeignService getService() {
        return attachmentService;
    }

    @RequestMapping(value = "/upload")
    public Result upload(HttpServletRequest request, HttpServletResponse response, MultipartFile file) {
        return attachmentService.upload(request, response, file);
    }

    @RequestMapping(value = "/upload_proprietary")
    public Result uploadProprietary(HttpServletRequest request, HttpServletResponse response, MultipartFile file) {
        return attachmentService.uploadProprietary(request, response, file);
    }

    @RequestMapping(value = "/list_proprietary")
    public Result listProprietary(HttpServletRequest request, HttpServletResponse response) {
        return attachmentService.listProprietary(request, response);
    }

    @RequestMapping(value = "/delete_proprietary")
    public Result deleteProprietary(HttpServletRequest request, HttpServletResponse response, String id) {
        return attachmentService.deleteProprietary(request, response, id);
    }

    @RequestMapping(value = "/download")
    public byte[] download(HttpServletRequest request, HttpServletResponse response, String id) {
        return attachmentService.download(request, response, id);
    }

}
