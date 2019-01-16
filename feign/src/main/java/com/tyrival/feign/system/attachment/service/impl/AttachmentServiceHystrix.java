package com.tyrival.feign.system.attachment.service.impl;

import com.tyrival.entity.base.Result;
import com.tyrival.entity.system.attachment.Attachment;
import com.tyrival.exceptions.CommonException;
import com.tyrival.exceptions.ExceptionEnum;
import com.tyrival.api.feign.service.impl.FeignServiceHystrix;
import com.tyrival.feign.system.attachment.service.AttachmentService;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Description:
 * @Author: Zhou Chenyu
 * @Date: 2018/10/24
 * @Version: V1.0
 */
@Component
public class AttachmentServiceHystrix extends FeignServiceHystrix<Attachment> implements AttachmentService {

    @Override
    public Result upload(HttpServletRequest request, HttpServletResponse response, MultipartFile file) {
        return new Result(ExceptionEnum.HYSTRIX);
    }

    @Override
    public byte[] download(HttpServletRequest request, HttpServletResponse response, String id) {
        return null;
    }
}
