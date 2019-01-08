package com.tyrival.coder.entity;

import com.tyrival.coder.enums.TemplateEnum;
import com.tyrival.coder.util.FileUtil;
import com.tyrival.coder.util.NameFormat;
import com.tyrival.coder.util.PathUtil;

import java.util.Map;

/**
 * @Description:
 * @Author: Zhou Chenyu
 * @Date: 2018/10/31
 * @Version: V1.0
 */
public class Template {

    public static String handler(TemplateEnum templateEnum, Placeholder placeholder) {
        String content = read(templateEnum);
        for (Map.Entry<String, String> entry : placeholder.getRules().entrySet()) {
            content = content.replaceAll(entry.getKey(), entry.getValue());
        }
        return content;
    }

    private static String read(TemplateEnum templateEnum) {
        String name = NameFormat.toUpperCamelName(templateEnum.name());
        StringBuilder path = new StringBuilder()
                .append(PathUtil.getTemplateRoot())
                .append(name);
        return FileUtil.read(path.toString());
    }
}
