package com.tyrival.coder.entity;

import com.tyrival.coder.util.NameFormat;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Description: JavaBean定义类
 * @Author: Zhou Chenyu
 * @Date: 2017/7/20
 * @Version: V1.0
 */
public class JavaBean {
    // 模块名
    private String moduleName;
    // 包名
    private String packageName;
    // 类名
    private String className;
    // 类注释
    private String comment;
    // 属性集
    private List<Field> fields;
    // 表名
    private String tableName;

    private final static String PACKAGE_NAME_RULE = "^[A-Za-z0-9._]+$";
    private final static String CLASS_NAME_RULE = "^[A-Za-z0-9]+$";

    public JavaBean(String moduleName, String packageName, String className) throws Exception {
        validate(packageName, PACKAGE_NAME_RULE,
                "JavaBean构造失败：包名packageName不可为空，且只能包含点号(.)、下划线(_)和英文字母。");
        validate(className, CLASS_NAME_RULE,
                "JavaBean构造失败：类名className不可为空，且只能包含英文字母。");
        this.moduleName = NameFormat.toLowerCase(moduleName);
        this.packageName = NameFormat.toLowerCase(packageName);
        this.className = NameFormat.toUpperCamelName(className);
        this.tableName = NameFormat.toLowerUnderscoreName(className);
        this.comment = "";
        fields = new ArrayList<>();
    }

    public JavaBean(String moduleName, String packageName, String className, String comment) throws Exception {
        this(moduleName, packageName, className);
        this.comment = comment;
    }

    public JavaBean(String moduleName, String packageName, String className, String comment, String tableName) throws Exception {
        this(moduleName, packageName, className, comment);
        this.tableName = tableName;
    }

    /**
     * 校验
     *
     * @param name
     * @param rule
     * @param errMsg
     * @throws Exception
     */
    private static void validate(String name, String rule, String errMsg) throws Exception {
        if (name == null || "".equals(name)) {
            throw new Exception(errMsg);
        }
        Pattern pkgPtn = Pattern.compile(rule);
        Matcher pkgM = pkgPtn.matcher(name);
        if (!pkgM.find()) {
            throw new Exception(errMsg);
        }
    }

    /**
     * 增加一个属性
     *
     * @param field
     * @return
     */
    public JavaBean addField(Field field) {
        for (Field f : fields) {
            if (f.equals(field)) {
                fields.remove(f);
                break;
            }
        }
        fields.add(field);
        return this;
    }

    /**
     * 删除某个属性
     *
     * @param field
     * @return
     * @throws Exception
     */
    public JavaBean removeField(Field field) throws Exception {
        for (Field f : fields) {
            if (f.equals(field)) {
                fields.remove(f);
                return this;
            }
        }
        throw new Exception("无法移除属性 " + field.getName() + "，不存在此属性。");
    }

    public boolean isEmpty() {
        if (fields == null || fields.size() <= 0) {
            return true;
        }
        return false;
    }

    public List<Field> getFields() {
        return fields;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getPackageName() {
        return packageName;
    }

    public String getPackagePath() {
        return packageName.replaceAll("\\.", "\\/");
    }

    public String getClassName() {
        return className;
    }

    public String getTableName() {
        if (tableName == null || tableName == "") {
            return NameFormat.toUpperUnderscoreName(this.getClassName());
        }
        return tableName;
    }

    public String getUrlName() {
        return NameFormat.toLowerUnderscoreName(this.getClassName());
    }

    public String getInstanceName() {
        return NameFormat.toLowerCamelName(this.getClassName());
    }

    public String getModuleName() {
        return moduleName;
    }

    public String getMybatisColumns() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < this.fields.size(); i++) {
            Field field = fields.get(i);
            sb.append(field.getColumnName()).append(", ");
        }
        return sb.toString();
    }

    public String getMybatisValue() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < this.fields.size(); i++) {
            Field field = fields.get(i);
            sb.append("#{").append(field.getName()).append("}, ");
        }
        return sb.toString();
    }
}
