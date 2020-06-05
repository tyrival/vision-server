package com.tyrival.feign.system.attachment.service;

import com.tyrival.entity.base.Result;
import com.tyrival.entity.system.attachment.Attachment;
import com.tyrival.api.feign.service.FeignService;
import com.tyrival.feign.system.attachment.service.impl.AttachmentServiceHystrix;
import feign.codec.Encoder;
import feign.form.spring.SpringFormEncoder;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.support.SpringEncoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Description:
 * @Author: Zhou Chenyu
 * @Date: 2018/10/24
 * @Version: V1.0
 */
@FeignClient(value = "server-system",
        path = "/attachment",
        configuration = AttachmentService.MultipartSupportConfig.class,
        fallback = AttachmentServiceHystrix.class)
public interface AttachmentService extends FeignService<Attachment> {

    @Configuration
    class MultipartSupportConfig {
        @Autowired
        private ObjectFactory<HttpMessageConverters> messageConverters;

        @Bean
        public Encoder feignFormEncoder() {
            return new SpringFormEncoder(new SpringEncoder(messageConverters));
        }
    }

    @RequestMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    Result upload(@RequestParam(value = "request") HttpServletRequest request,
                  @RequestParam(value = "response") HttpServletResponse response,
                  @RequestPart(value = "file") MultipartFile file);

    @RequestMapping(value = "/upload_proprietary", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    Result uploadProprietary(@RequestParam(value = "request") HttpServletRequest request,
                             @RequestParam(value = "response") HttpServletResponse response,
                             @RequestPart(value = "file") MultipartFile file);

    @RequestMapping(value = "/list_proprietary", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    Result listProprietary(@RequestParam(value = "request") HttpServletRequest request,
                           @RequestParam(value = "response") HttpServletResponse response);

    @RequestMapping(value = "/delete_proprietary")
    Result deleteProprietary(@RequestParam(value = "request") HttpServletRequest request,
                             @RequestParam(value = "response") HttpServletResponse response,
                             @RequestParam(value = "id") String id);

    @RequestMapping(value = "/download")
    byte[] download(@RequestParam(value = "request") HttpServletRequest request,
                    @RequestParam(value = "response") HttpServletResponse response,
                    @RequestParam(value = "id") String id);

}
