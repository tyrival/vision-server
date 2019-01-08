package com.tyrival.coder.entity;

import com.tyrival.coder.util.NameFormat;

/**
 * @Description: JavaBean的属性类
 * @Author: Zhou Chenyu
 * @Date: 2017/7/19
 * @Version: V1.0
 */
public class Field {
    // 属性名
    private String name;
    // 属性类型
    private String type;
    // 属性注释
    private String comment;

    public Field(String name, String type) throws Exception {
        if (isEmpty(name) || isEmpty(type)) {
            throw new Exception("JavaBean的属性名和属性类型不可为空。");
        }
        this.name = NameFormat.toLowerCamelName(name);
        this.type = NameFormat.toUpperCamelName(type);
        this.comment = "";
    }

    public Field(String name, String type, String comment) throws Exception {
        this(name, type);
        this.comment = comment;
    }

    public String getColumnName() {
        return NameFormat.toLowerUnderscoreName(name);
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public String getComment() {
        return comment;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || !(obj instanceof Field)) {
            return false;
        }
        Field field = (Field) obj;
        if (this.getName().equals(field)) {
            return true;
        }
        return false;
    }

    private boolean isEmpty(String str) {
        return str == null || "".equals(str);
    }
}
